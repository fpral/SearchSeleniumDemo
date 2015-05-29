package org.jahia.selenium.scripts.elasticsearch;

import org.jahia.selenium.scripts.TestBase;
import org.jahia.selenium.modules.search.SearchResult;
import org.jahia.selenium.sites.searchdemo.HomePage;
import org.jahia.selenium.sites.searchdemo.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Prerequisites
 * <br>
 * You need to import the site searchsite_demo.zip in your Digital Factory server and have it as the default site.
 * You need the template set sample-bootstrap-template. The site comes with 3 users: jcr1, jcr2 and jcr3, all with the password: password
 * <br>
 * The site has the following structure:<br>
 *<br>
 * Home<br>
 * |- Permissions (page visible only by users jcr1 and jcr2)<br>
 * |- Bootstrap rich text ("Selenium demonstration at Jahia One!"): content only visible by the user jcr1<br>
 * |- Visibility<br>
 * |- Bootstrap rich text ("Digital Factory includes Elastic Search!") only visible from Monday to Friday<br>
 * |- Bootstrap rich text ("Text visible on Sundays only") only visible on Sundays<br>
 *
 */
public class ElasticSearchTests extends TestBase{

    private static final String JCR_USER_PASSWORD = "password";
    private static final String RICHTEXT = "Selenium demonstration at Jahia One";
    private static final String PERMISSION_PAGE = "Permissions";
    private static final String RICHTEXT_VISIBLE = "Digital Factory includes Elastic Search";
    private static final String RICHTEXT_NOT_VISIBLE = "Text visible on Sundays only";
    private static final String VISIBILITY_PAGE = "Visibility";


    @BeforeTest
    public void openHomePage(){
        //update it to go on the home page of the site
        driver.get("http://localhost:8080");
    }

    /**
     * Verify that search takes user permissions into account when showing the results
     */
    @Test
    public void permissionTest(){
        SearchResult textSearchResult = new SearchResult(driver,RICHTEXT, PERMISSION_PAGE);
        SearchResult pageSearchResult = new SearchResult(driver,PERMISSION_PAGE, PERMISSION_PAGE);

        HomePage home = new HomePage(driver);
        //Search with jcr1 who has all reading permissions on every content
        home.loginAs("jcr1", JCR_USER_PASSWORD);
        SearchResultPage searchResultPage = home.search(RICHTEXT);
        verifyResult(textSearchResult, true);
        searchResultPage.logout();

        //Search with jcr2 who has no reading permissions on the text
        searchResultPage.loginAs("jcr2", JCR_USER_PASSWORD);
        searchResultPage.search(RICHTEXT);
        verifyResult(textSearchResult, false);
        searchResultPage.search(PERMISSION_PAGE);
        verifyResult(pageSearchResult, true);
        searchResultPage.logout();

        //Search with jcr3 who has no permissions
        searchResultPage.loginAs("jcr3", JCR_USER_PASSWORD);
        searchResultPage.search(RICHTEXT);
        verifyResult(textSearchResult, false);
        searchResultPage.search(PERMISSION_PAGE);
        verifyResult(pageSearchResult, false);
        searchResultPage.logout();

        //Search with guest who has no permissions
        searchResultPage.search(RICHTEXT);
        verifyResult(textSearchResult, false);
        searchResultPage.search(PERMISSION_PAGE);
        verifyResult(pageSearchResult, false);
    }

    /**
     * Verify that search takes visibility conditions into account when showing the results
     */
    @Test
    public void visibilityTest() {
        SearchResult visibleTextSearchResult = new SearchResult(driver,RICHTEXT_VISIBLE, VISIBILITY_PAGE);
        SearchResult invisibleTextSearchResult = new SearchResult(driver,RICHTEXT_NOT_VISIBLE, VISIBILITY_PAGE);

        HomePage home = new HomePage(driver);
        SearchResultPage searchResultPage = home.search(RICHTEXT_VISIBLE);
        verifyResult(visibleTextSearchResult, true);
        searchResultPage.search(RICHTEXT_NOT_VISIBLE);
        verifyResult(invisibleTextSearchResult, false);
    }

    private void verifyResult(SearchResult searchResult, boolean displayed){
        if(displayed){
            Assert.assertTrue(searchResult.isDisplayed(), "The search result is not displayed");
        }
        else{
            Assert.assertFalse(searchResult.isDisplayed(), "The search result should not be displayed");
        }
    }



}

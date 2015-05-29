package org.jahia.selenium.sites.searchdemo;

import org.jahia.selenium.ActionBot;
import org.jahia.selenium.modules.LoginForm;
import org.jahia.selenium.modules.search.SimpleSearchForm;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * Provide methods usable by any page, such as login, search, navigation menu... Basically the top banner of the site
 */
public abstract class PageBase {
    protected ActionBot bot;
    protected WebDriver driver;
    private LoginForm login;
    private SimpleSearchForm searchForm;

    public PageBase(WebDriver driver) {
       this.driver = driver;
        this.bot = new ActionBot(driver);
        this.login = new LoginForm(driver);
        this.searchForm = new SimpleSearchForm(driver);
    }

    public SearchResultPage search(String textToSearch){
        searchForm.search(textToSearch);
        return new SearchResultPage(driver);
    }

    public PageBase loginAs(String username, String password) {
        login.loginAs(username, password);
        return this;
    }

    public PageBase logout(){
        login.logout();
        return this;
    }

    public VisibilityPage clickOnVisibilityLink(){
        bot.click(By.linkText(VisibilityPage.VISIBILITY_LINK));
        return new VisibilityPage(driver);
    }

    public PermissionsPage clickOnPermissionsLink(){
        bot.click(By.linkText(PermissionsPage.PERMISSIONS_LINK));
        return new PermissionsPage(driver);
    }
}

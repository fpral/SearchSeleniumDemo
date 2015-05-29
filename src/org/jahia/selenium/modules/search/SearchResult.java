package org.jahia.selenium.modules.search;

import org.jahia.selenium.ActionBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class to interact with a search result element
 */
public class SearchResult {
    private WebDriver driver;
    private ActionBot bot;
    private String text;
    private String pageLink;

    public SearchResult(WebDriver driver, String text, String pageLink) {
        this.driver = driver;
        this.bot = new ActionBot(driver);
        this.text = text;
        this.pageLink = pageLink;
    }

    public boolean isDisplayed(){
        return(bot.isElementDisplayed(By.linkText(pageLink)) && bot.isTextPresent(text));
    }
}

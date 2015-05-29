package org.jahia.selenium.modules.search;

import org.jahia.selenium.ActionBot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Class to interact with the Simple Search Form
 */
public class SimpleSearchForm {

    protected ActionBot bot;
    protected WebDriver driver;
    By formLocator = By.id("searchTerm");

    public SimpleSearchForm(WebDriver driver) {
        this.driver = driver;
        this.bot = new ActionBot(driver);
    }

    public void type(String textToSearch){
        bot.type(formLocator, textToSearch);
    }

    public void search(String textToSearch) {
        type(textToSearch);
        driver.findElement(formLocator).sendKeys(Keys.RETURN);
    }
}

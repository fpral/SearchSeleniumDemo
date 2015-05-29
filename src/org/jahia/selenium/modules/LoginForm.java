package org.jahia.selenium.modules;

import org.jahia.selenium.ActionBot;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Class to interact with the login form
 */
public class LoginForm {
    protected ActionBot bot;
    protected WebDriver driver;
    By userNameLocator = By.id("username");
    By passwordLocator = By.id("password");
    By submitButtonLocator = By.cssSelector("button.btn.btn-primary");

    public LoginForm(WebDriver driver) {
        this.driver = driver;
        this.bot = new ActionBot(driver);
    }

    public void openLoginForm(){
        bot.click(By.cssSelector("div.login a.btn.btn-primary"));
    }

    public void typeUsername(String username){
       bot.type(userNameLocator, username);
    }

    public void typePassword(String password){
        bot.type(passwordLocator,password);
    }

    public void submit(){
        bot.click(submitButtonLocator);
    }

    /**
     * Open the login form, type username and password then submit
     * @param username
     * @param password
     */
    public void loginAs(String username, String password){
        openLoginForm();
        bot.waitForElement(userNameLocator);
        typeUsername(username);
        typePassword(password);
        submit();
    }

    public void logout() {
        bot.click(By.cssSelector("div.user-box.dropdown a.dropdown-toggle"));
        bot.click(By.linkText("Logout"));
    }
}

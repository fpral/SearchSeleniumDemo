package org.jahia.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

/**
 * From https://code.google.com/p/selenium/wiki/BotStyleTests
 *
 * This class allows you to customize some low level selenium actions such as typing, clicking, etc.
 */
public class ActionBot {

    public static final long WEB_DRIVER_TIMEOUT = 20; //in seconds
    private final WebDriver driver;

    public ActionBot(WebDriver driver) {
        this.driver = driver;
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void doubleClick(By by){
        WebElement elem = driver.findElement(by);
        new Actions(driver).doubleClick(elem).perform();
    }

    public void submit(By locator) {
        driver.findElement(locator).submit();
    }

    /**
     * Type something into an input field. WebDriver doesn't normally clear these
     * before typing, so this method does that first. It also sends a return key
     * to move the focus out of the element.
     */
    public void type(By locator, String text) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    /**
     * Wait for an element to appear in the DOM
     * @param locator
     */
    public void waitForElement(By locator){
        org.openqa.selenium.support.ui.Wait<WebDriver> wait = new WebDriverWait(driver, WEB_DRIVER_TIMEOUT);
        wait.until(visibilityOfElementLocated(locator));
    }

    /**
     * Test if an element is displayed
     * @param locator
     * @return true if the element is displayed on the page, otherwise false
     */
    public boolean isElementDisplayed(By locator) {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(locator);
        if (elements.size() > 0) {
            //some element might be present but not displayed
            for (WebElement web : elements) {
                if (web.isDisplayed()) {
                    driver.manage().timeouts().implicitlyWait(WEB_DRIVER_TIMEOUT, TimeUnit.SECONDS);
                    return true;
                }
            }
        }
        driver.manage().timeouts().implicitlyWait(WEB_DRIVER_TIMEOUT, TimeUnit.SECONDS);
        return false;
    }

    /**
     * Test if a text is displayed
     * @param text
     * @return true if the text is displayed on the page, otherwise false
     */
    public boolean isTextPresent(String text){
        String bodyText = driver.findElement(By.cssSelector("BODY")).getText();
        return bodyText.contains(text);
    }
}

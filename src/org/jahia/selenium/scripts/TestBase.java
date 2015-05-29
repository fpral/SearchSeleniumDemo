package org.jahia.selenium.scripts;

import org.jahia.selenium.ActionBot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

/**
 * Base for test classes
 * Edit this class if you want to use a different WebDriver or if you want to add any feature to your tests
 * (eg. take a screenshot on failure)
 */
public class TestBase {
    protected WebDriver driver;
    protected ActionBot bot;

    @BeforeTest
    public void setUpTest(){
        if (driver == null) {
            driver = new FirefoxDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            bot = new ActionBot(driver);
        }
    }

    @AfterClass
    public void tearDown() throws Exception{
        driver.quit();
    }

    /**
     * This method is called after every method tagged with @Test and before @AfterClass.
     * Use it to take a screenshot if a test fail, to add logs, etc.
     * @param _result
     */
    @AfterMethod(alwaysRun=true)
    public void endAppender(ITestResult _result){
        try {
            if (!(_result.getThrowable() != null &&
                    _result.getThrowable().getMessage().equals("Internal Server Error")))
            {
                if (!_result.isSuccess()) {
                    //Code to execute if the test failed
                } else{
                    //Code to execute if the test was successful
                }
            }
        } finally {
            //Code to execute after any test
        }
    }
}

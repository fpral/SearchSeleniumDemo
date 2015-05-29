# SearchSeleniumDemo
This is an example of a selenium project to test a site / module implementing PageObjects. It's written in Java with Selenium WebDriver and TestNG.


## Prerequisites
You need the selenium-java-2.45.0.jar and selenium-server-standalone-2.45.0.jar librairies, as well as testng-6.8.5.jar

You need to import the site searchsite_demo.zip (in the resources folder) in your Digital Factory 7.0 or 7.1 server. You need the template set sample-bootstrap-template. The site comes with 3 users: jcr1, jcr2 and jcr3, all with the password: password
The site has the following structure:
```
Home
 |- Permissions (page visible only by users jcr1 and jcr2)
        |- Bootstrap rich text ("Selenium demonstration at Jahia One!"): content only visible by the user jcr1
 |- Visibility
        |- Bootstrap rich text ("Digital Factory includes Elastic Search!") only visible from Monday to Friday
        |- Bootstrap rich text ("Text visible on Sundays only") only visible on Sundays
```

## Description
This project implements the PageObjects and also BotStyleTests concepts (https://code.google.com/p/selenium/wiki/DesignPatterns). The class ActionBot allows you to customize some low level selenium actions which can be use anywhere in your selenium project (it's pretty much the same idea as the Selenium object in Selenium RC).

The main idea is to provide on one hand the classes/methods to interract with your site and on the other hand the testing logic.

### Package org.jahia.selenium.sites.searchdemo
This package contains the classes which corresponds to the pages of the site "searchsite". All the packages extend the abstract class PageBase which provides methods usable by any page of the site (eg. login, search, etc.)

### Package org.jahia.selenium.modules
This package contains the code to interact with your modules with selenium. In this example there are the LoginForm and the SimpleSearchForm classes. This classes are used in PageBase so you will be able to log in from any page, or perform a simple search from any page.

### Package org.jahia.selenium.scripts
This package which contains your test scripts. It has the class TestBase which provides the default functions for your tests (setUp, tearDown...)

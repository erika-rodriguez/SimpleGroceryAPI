<!-- PROJECT LOGO -->
<div align="center">
  <h1 align="center">Simple Grocery API</h1>
  <p align="center">API Testing Project</p>
</div>

<!-- TABLE OF CONTENTS -->
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
    <li><a href="#workflow">Workflow</a></li>
    <li><a href="#useful documentation">Useful Documentation</a></li>
  </ol>

<!-- ABOUT THE PROJECT -->
## About The Project

The project focuses on testing the Simple Grocery API, using core testing tools such as Java, and Rest-assured. The testing process is integrated with Jenkins and using TestRail to provide a comprehensive testing experience. The goal of the project is to improve testing skills and gain experience in web testing.
![Alt text](img.png)
### Built With

* Programming Language: Java
* CI Tools: Jenkins
* Frameworks: Carina, Rest-assured
* Test Management Tools: TestRail

<!-- GETTING STARTED -->

### Getting started
* Install and configure JDK 11
* Install and configure [Apache Maven 3.6.0+](http://maven.apache.org/)
* Download and start the latest [Selenium standalone server](http://www.seleniumhq.org/download/)
* Download the latest version of [Eclipse](http://www.eclipse.org/downloads/) and install [TestNG plugin](http://testng.org/doc/download.html)
* [Read Carina documentation](https://zebrunner.github.io/carina/)


### Prerequisites

- [ ] Install Java +11
- [ ] Add the carina-api dependency to the pom.xml 
- [ ] Install Jenkins

### Installation
- [ ] Clone the repo.
- [ ] Modify the -config.properties.
- [ ] Add a _testdata.properties file with TestRail's credentials data (testRailUrl, testRailUsername, testRailPassword).

<!-- USAGE EXAMPLES AND STUDY CASES-->
## Usage

- [ ] Run tests from testng.xml files to create automatically a test run in TestRail.

## Implementation details

### Study case: LOGGER implementation
- Logging is a powerful aid for understanding and debugging a program’s run-time behavior. Logs capture and persist the important data and make it available for analysis at any point in time.
```
import java.util.logging.Logger;

public static final Logger LOGGER= Logger.getLogger(String.valueOf(HomePage.class));
```
### Study case: Static Dropdown
- Selenium can handle static dropdowns with the help of the Select class. A dropdown is identified with **select** tagname and its options are represented with the tagname **option**. 
- The statement - from selenium.webdriver.support.select import Select should be added to work with Select class.

```
Identify the <select> WebElement
private WebElement currencyDropdown= driver.findElement(By.xpath("locator));

Create a Select object from org.openqa.selenium.support.ui.Select and start using all its methods to select by Value, Index or Text
Select staticDropdown =new Select(currencyDropdown);
staticDropdown.selectByIndex(3);
```
### Study case: Dynamic Dropdown
- Some dropdowns are dynamic in nature which means after clicking or selecting any option, the dropdowns values would populate accordingly. And in some cases, you need to mouse hover on an element in Selenium to see the drop-down options.
- To solve this, we need to use parent-child locators to uniquely identify the elements.

```
Identify the elements uniquely, using parent-child locators. For example:
WebElement dubaiOption= driver.findElement(By.xpath("//div[@id='ctl00_mainContent_ddl_destinationStation1_CTNR']/descendant::div[@class='dropdownDiv']/ul/li/a[text()=' Dubai, All Airports(DWC) (DXB)']"));
```
### Study case: AutoSuggestive Dropdown
- Auto-suggestion or auto-complete functionality offers potential suggestions to users based on their input in a search box or any other input field on a webpage. These suggestions aim to assist users in finding relevant information or completing their input more efficiently. These are implemented as a drop-down list with a list of possible suggestions as the user proceeds to input. This helps the user select the desired term from the list without entering it completely.
- Dynamic lists of suggestions change with user input, facilitated by AJAX (Asynchronous JavaScript and XML) requests. This ensures a seamless user experience as they type, with suggestions fetched from a server or API in the background.
- The auto-suggestion list depends on asynchronous calls to show results. Sometimes, this can lead to timing issues if Selenium tries to interact with it instantly, and the suggestion list may not have fully loaded.
```
1. Enter the search term in the search box.
2. Wait for the auto-suggestion list to be available.
3. Fetch WebElement reference to all the auto-suggestions and store in a List variable of type WebElement.
4. Create a list of WebElements to catch the results of the search.
    List<WebElement> results=driver.findElements(By.xpath("//li[@class='ui-menu-item']/a"));
5.Start a loop to traverse each WebElement from the list.
    for (WebElement e:results) {
         System.out.println(e.getText());
         if (e.getText().toLowerCase().contains(match)){
            return true;
         }
    }
6. Compare if the auto-suggestion matches the required term. 
7. Assert that the search term on the results page is the same as expected or not.   
```
<!-- WORKFLOW -->
## Workflow

1. Check tickets assigned to you defined in the Project Management Tool (e.g. Trello)
2. Check TestRail's test case: preconditions, steps, expected results
3. Create your Feature Branch following naming convention [TCXX]-testName
4. Add components and pages as needed, implementing Page Object Model Design Pattern using Page Factory.
5. Commit your changes, push them to your branch and create a pull request assigning a reviewer
6. Merge changes to master branch

<!-- USEFUL DOCUMENTATION -->
## Useful Documentation

* [Selenium Github Example](https://github.com/SeleniumHQ/seleniumhq.github.io/tree/trunk/examples)
* [TestRail](https://support.gurock.com/hc/en-us)
* [SimpleGroceryAPI Documentation](https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md)
* [TestNG](https://testng.org/doc/documentation-main.html)
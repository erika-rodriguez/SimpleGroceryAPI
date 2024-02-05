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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
```
### Study case: REST service call domain object (Declarative approach)
- Approach based on implicit instantiation of the AbstractApiMethod. 
- It allows to: more convenient and efficiently organize description of endpoints;- have all carina api methods for the same URL pattern be defined within single class; - reduce time for the implementation of the desired AbstractApiMethod; - flexibly configure all api methods with Java annotations.
- In this approach it is possible to use these annotations not only on the class but also on the method level.
1. Create an **Interface** to use it as a template. 
   - Use the @EndpointTemplate annotation to set the basic endpoint shared with various methods.
   - Use the @EndpointTemplateMethod to set the specific url and method type.
   - Add @SuccessfulHttpStatus to define a status code for a PASSED condition. This will allow to use the callAPIExpectSuccess() in Test Class.
   - If required, it is possible to add Path Variables using (@PathParam(key = "requiredKey") int key) as a method parameter.
```
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.EndpointTemplate;
import com.zebrunner.carina.api.annotation.EndpointTemplateMethod;
import com.zebrunner.carina.api.annotation.PathParam;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@EndpointTemplate(url = "${config.env.api_url}/products")
public interface IProduct {
    @EndpointTemplateMethod(url = "/${productId}", methodType = HttpMethodType.GET)
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getAProduct(@PathParam(key = "productId") int productId);
}
```
2. Create a class that implements the interface
   - For more customization on api method definition level you can implement the interface and use the proxy class inside.
   - Now you can invoke a prepareTemplate method from TemplateFactory to use proxy implementation in the test.
```
import com.solvd.carina.demo.api.common.IProduct;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;

public class ProductAPI implements IProduct {
    private final IProduct productTemplate;

    public ProductAPI(IProduct productTemplate) {
        this.productTemplate = TemplateFactory.prepareTemplate(IProduct.class);
    }
    
    @Override
    public AbstractApiMethodV2 getAProduct(int productId) {
        AbstractApiMethodV2 apiMethod=productTemplate.getAProduct(productId);
        return apiMethod;
    }
}
```
3. Create a Test Class that implements IAbstractTest
   - Invoke a prepareTemplate method from TemplateFactory to use proxy implementation in the test.
   - The parameters can be found in **api.properties**.
   - Specify the expected HTTP status.
   - Call the API.
   - Validate the response by a template or parse some data by JSON path.
   - Make further calls using the data from the previous call if needed.
```
public class GroceryAPITest implements IAbstractTest {
   @Test
   public void testGetAProduct(){
   IProduct template= TemplateFactory.prepareTemplate(IProduct.class);
   AbstractApiMethodV2 api=template.getAProduct(Integer.parseInt(R.API.get("productId")));
   api.callAPIExpectSuccess();
   api.validateResponseAgainstSchema("api/grocery/_get/rs_getAProduct.schema");
   }
}
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

* [RestAssured](https://rest-assured.io/)
* [TestRail](https://support.gurock.com/hc/en-us)
* [SimpleGroceryAPI Documentation](https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md)
* [TestNG](https://testng.org/doc/documentation-main.html)
* [JsonSchema Generator](https://www.liquid-technologies.com/online-json-to-schema-converter)
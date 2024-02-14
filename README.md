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
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
   <li><a href="#implementation">Implementation details</a></li>
   <li><a href="#useful documentation">Useful Documentation</a></li>
  </ol>

<!-- ABOUT THE PROJECT -->
## About The Project

The project focuses on testing the Simple Grocery API, using core testing tools such as Java, and Carina API Framework. The goal of the project is to improve testing skills and gain experience in api testing.

### Built With

* Programming Language: Java
* Build Tool: Maven
* Frameworks: Carina, Rest-assured

### Prerequisites

- [ ] Install Java +11
- [ ] Install and configure [Apache Maven 3.6.0+](http://maven.apache.org/)
- [ ] Add the carina-api dependency to the pom.xml

### Installation

- [ ] Clone the repo.
- [ ] Modify the -config.properties.
- [ ] Run mvn clean install in terminal.

<!-- IMPLEMENTATION DETAILS -->
## Implementation details

### LOGGER implementation
- Logging is a powerful aid for understanding and debugging a program’s run-time behavior. Logs capture and persist the important data and make it available for analysis at any point in time.
```
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
```
### REST service call domain object (Declarative approach)
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
### JsonSchema Validation
- When you need to validate response structure regardless of the actual values, you may use validation by JSON schema.
1. In this case, you need an actual response from the service.
2. Now we need to generate a schema, you can use an <a href="https://www.liquid-technologies.com/online-json-to-schema-converter">online JSON schema Generator</a>. You need to provide the original JSON from the response, then choose some schema options (allow the additional properties in objects, mark the current object properties as required, hard-code some expected values, etc.) and then generate the schema. 
3. Copy-paste the generated schema into test resources as a .schema file, and you're ready to use it in the test providing the file path.

### Authorization
- Some endpoints may require authentication. To submit or view an order, you need to register your API client and obtain an access token. The endpoints that require authentication expect a bearer token sent in the Authorization header.
- Once you get your Bearer Token, you can send it in Header Request.
```
@Header(key = "Authorization", value = token)
```

<!-- USEFUL DOCUMENTATION -->
## Useful Documentation

* [SimpleGroceryAPI Documentation](https://github.com/vdespa/Postman-Complete-Guide-API-Testing/blob/main/simple-grocery-store-api.md)
* [Read Carina documentation](https://zebrunner.github.io/carina/)
* [RestAssured](https://rest-assured.io/)
* [TestNG](https://testng.org/doc/documentation-main.html)
* [JsonSchema Generator](https://www.liquid-technologies.com/online-json-to-schema-converter)
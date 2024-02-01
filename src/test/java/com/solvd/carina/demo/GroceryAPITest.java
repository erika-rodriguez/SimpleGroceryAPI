package com.solvd.carina.demo;

import com.solvd.carina.demo.api.GetAllProductsMethod;
import com.solvd.carina.demo.api.GetStatusMethod;
import com.solvd.carina.demo.api.PostCreateACartMethod;
import com.solvd.carina.demo.api.PostUserMethod;
import com.zebrunner.carina.core.IAbstractTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

public class GroceryAPITest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void testAPIStatus(){
        GetStatusMethod status=new GetStatusMethod();
        status.getStatusOfAPI();
        status.callAPIExpectSuccess();
        status.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }

    @Test
    public void testGetAllProducts(){
        GetAllProductsMethod products=new GetAllProductsMethod();
        products.getProducts();
        products.callAPI();
        products.validateResponseAgainstSchema("api/users/_get/rs.schema");
    }

    @Test
    public void testCreateNewCart(){
        PostCreateACartMethod cart=new PostCreateACartMethod();
        cart.createANewCart();
        cart.callAPI();
    }
}

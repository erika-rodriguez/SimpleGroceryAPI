package com.solvd.carina.demo;

import com.solvd.carina.demo.api.*;
import com.solvd.carina.demo.api.common.IProduct;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.utils.R;
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
        status.validateResponseAgainstSchema("api/grocery/_get/rs.schema");
    }

    @Test
    public void testCreateNewCart(){
        PostCreateACartMethod cart=new PostCreateACartMethod();
        cart.createANewCart();
        cart.callAPI();
        cart.validateResponseAgainstSchema("api/grocery/_post/rs_createCart.schema");
    }

    @Test
    public void testGetACart(){
        GetACartMethod api=new GetACartMethod();
        api.addUrlParameter("cartId",R.API.get("cartId"));
        api.callAPI();

    }

    @Test
    public void testGetAProduct(){
        IProduct template= TemplateFactory.prepareTemplate(IProduct.class);
        AbstractApiMethodV2 api=template.getAProduct(Integer.parseInt(R.API.get("productId")));
        api.callAPIExpectSuccess();
        api.validateResponseAgainstSchema("api/grocery/_get/rs_getAProduct.schema");

    }

    @Test
    public void testGetAllProducts(){
        IProduct template= TemplateFactory.prepareTemplate(IProduct.class);
        AbstractApiMethodV2 api=template.getAllProducts();
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponseAgainstSchema("api/grocery/_get/rs_getAllProducts.schema");
    }
}

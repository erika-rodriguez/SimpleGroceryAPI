package com.solvd.carina.demo;

import com.solvd.carina.demo.api.*;
import com.solvd.carina.demo.api.common.ICart;
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

    @Test
    public void testCreateNewCart(){
        ICart template=TemplateFactory.prepareTemplate(ICart.class);
        AbstractApiMethodV2 api= template.createNewCart();
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
    }

    @Test
    public void testGetACart(){
        ICart template=TemplateFactory.prepareTemplate(ICart.class);
        AbstractApiMethodV2 api= template.getACart(R.API.get("cartId"));
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponseAgainstSchema("api/grocery/_get/rs_getACart.schema");
    }

    @Test
    public void testAddItemToCart(){
        ICart template=TemplateFactory.prepareTemplate(ICart.class);
        AbstractApiMethodV2 api= template.addItemToCart(R.API.get("cartId"));
        api.expectResponseStatus(HttpResponseStatusType.CREATED_201);
        api.callAPI();
    }

    @Test
    public void testGetCartsItems(){
        ICart template=TemplateFactory.prepareTemplate(ICart.class);
        AbstractApiMethodV2 api= template.getCartsItems(R.API.get("cartId"));
        api.expectResponseStatus(HttpResponseStatusType.OK_200);
        api.callAPI();
        api.validateResponseAgainstSchema("api/grocery/_get/rs_getCartsItems.schema");
    }

    @Test
    public void testModifyItemInCart(){
        ICart template=TemplateFactory.prepareTemplate(ICart.class);
        AbstractApiMethodV2 api= template.modifyItemInCart(R.API.get("cartId"), R.API.get("itemId"));
        api.expectResponseStatus(HttpResponseStatusType.NO_CONTENT_204);
        api.callAPI();
    }


}

package com.solvd.carina.demo.api.common;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;

@EndpointTemplate(url="${config.env.api_url}/carts")
public interface ICart {

    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.POST)
    AbstractApiMethodV2 createNewCart();
    @EndpointTemplateMethod(url = "/${cartId}", methodType = HttpMethodType.GET)
    AbstractApiMethodV2 getACart(@PathParam(key = "cartId") String cartId);
    @EndpointTemplateMethod(url = "/${cartId}/items", methodType = HttpMethodType.POST)
    @RequestTemplatePath(path = "api/grocery/_post/rq_postAddItemToCart.json")
    AbstractApiMethodV2 addItemToCart(@PathParam(key = "cartId") String cartId);
    @EndpointTemplateMethod(url = "/${cartId}/items", methodType = HttpMethodType.GET)
    AbstractApiMethodV2 getCartsItems(@PathParam(key = "cartId") String cartId);




}

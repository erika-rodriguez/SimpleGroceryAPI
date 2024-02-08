package com.solvd.carina.demo.api.common;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;

@EndpointTemplate(url="${config.env.api_url}/orders")
public interface IOrder {
    public static final String token="Bearer fab020";

    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.POST)
    @HideRequestHeadersInLogs(headers = "Authorization")
    @Header(key = "Authorization", value = token)
    @RequestTemplatePath(path = "api/grocery/_post/rq_postCreateNewOrder.json")
    AbstractApiMethodV2 createNewOrder();

    @EndpointTemplateMethod(url = "/${orderId}", methodType = HttpMethodType.PATCH)
    @HideRequestHeadersInLogs(headers = "Authorization")
    @Header(key = "Authorization", value = token)
    @RequestTemplatePath(path = "api/grocery/_post/rq_patchUpdateAnOrder.json")
    AbstractApiMethodV2 updateAnOrder(@PathParam(key = "orderId") String orderId);

    @EndpointTemplateMethod(url = "/${orderId}", methodType = HttpMethodType.GET)
    @HideRequestHeadersInLogs(headers = "Authorization")
    @Header(key = "Authorization", value = token)
    @RequestTemplatePath(path = "api/grocery/_post/rq_patchUpdateAnOrder.json")
    AbstractApiMethodV2 getAnOrder(@PathParam(key = "orderId") String orderId);

    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.GET)
    @HideRequestHeadersInLogs(headers = "Authorization")
    @Header(key = "Authorization", value = token)
    AbstractApiMethodV2 getAllOrders();
}

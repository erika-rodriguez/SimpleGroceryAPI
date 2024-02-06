package com.solvd.carina.demo.api.common;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.*;
import com.zebrunner.carina.api.http.HttpMethodType;

@EndpointTemplate(url="${config.env.api_url}/orders")
public interface IOrder {
    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.POST)
    @HideRequestHeadersInLogs(headers = "Authorization")
    @Header(key = "Authorization", value = "Bearer fab020ceefa61d6b5dc276df571d06c43e07533f21ac8269c4c88271dc54cf32")
    @RequestTemplatePath(path = "api/grocery/_post/rq_postCreateNewOrder.json")
    AbstractApiMethodV2 createNewOrder();
}

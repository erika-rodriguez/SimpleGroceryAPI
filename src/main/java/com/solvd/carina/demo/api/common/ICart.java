package com.solvd.carina.demo.api.common;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.EndpointTemplate;
import com.zebrunner.carina.api.annotation.EndpointTemplateMethod;
import com.zebrunner.carina.api.http.HttpMethodType;

@EndpointTemplate(url="${config.env.api_url}/carts")
public interface ICart {

    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.POST)
    AbstractApiMethodV2 createNewCart();


}

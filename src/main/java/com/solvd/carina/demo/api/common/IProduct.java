package com.solvd.carina.demo.api.common;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.EndpointTemplate;
import com.zebrunner.carina.api.annotation.EndpointTemplateMethod;
import com.zebrunner.carina.api.annotation.PathParam;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;

@EndpointTemplate(url = "${config.env.api_url}/products")
public interface IProduct {
    @EndpointTemplateMethod(url = "", methodType = HttpMethodType.GET)
    AbstractApiMethodV2 getAllProducts();
    @EndpointTemplateMethod(url = "/${productId}", methodType = HttpMethodType.GET)
    @SuccessfulHttpStatus(status = HttpResponseStatusType.OK_200)
    AbstractApiMethodV2 getAProduct(@PathParam(key = "productId") int productId);
}

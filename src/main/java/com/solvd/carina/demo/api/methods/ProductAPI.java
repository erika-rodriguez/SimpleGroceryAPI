package com.solvd.carina.demo.api.methods;

import com.solvd.carina.demo.api.common.IProduct;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;

public class ProductAPI implements IProduct {
    private final IProduct productTemplate;

    public ProductAPI(IProduct productTemplate) {
        this.productTemplate = TemplateFactory.prepareTemplate(IProduct.class);
    }

    @Override
    public AbstractApiMethodV2 getAllProducts() {
        AbstractApiMethodV2 apiMethod=productTemplate.getAllProducts();
        return apiMethod;
    }

    @Override
    public AbstractApiMethodV2 getAProduct(int productId) {
        AbstractApiMethodV2 apiMethod=productTemplate.getAProduct(productId);
        return apiMethod;
    }
}

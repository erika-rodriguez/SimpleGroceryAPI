package com.solvd.carina.demo.api.methods;

import com.solvd.carina.demo.api.common.ICart;
import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.binding.TemplateFactory;

public class CartAPI implements ICart {
    private final ICart cartTemplate;

    public CartAPI(ICart cartTemplate) {
        this.cartTemplate = cartTemplate;
    }

    @Override
    public AbstractApiMethodV2 createNewCart() {
        AbstractApiMethodV2 apiMethod=cartTemplate.createNewCart();
        return apiMethod;
    }
}

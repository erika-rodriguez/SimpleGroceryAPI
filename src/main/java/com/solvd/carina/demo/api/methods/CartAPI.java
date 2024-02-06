package com.solvd.carina.demo.api.methods;

import com.solvd.carina.demo.api.common.ICart;
import com.zebrunner.carina.api.AbstractApiMethodV2;

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

    @Override
    public AbstractApiMethodV2 getACart(String cartId) {
        AbstractApiMethodV2 apiMethod=cartTemplate.getACart(cartId);
        return apiMethod;
    }

    @Override
    public AbstractApiMethodV2 addItemToCart(String cartId) {
        AbstractApiMethodV2 apiMethod=cartTemplate.addItemToCart(cartId);
        return apiMethod;
    }

    @Override
    public AbstractApiMethodV2 getCartsItems(String cartId) {
        AbstractApiMethodV2 apiMethod=cartTemplate.getCartsItems(cartId);
        return apiMethod;
    }
}

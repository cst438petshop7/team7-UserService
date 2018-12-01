package edu.csumb.cst438.userservice.api.users;

import java.util.List;

public class Cart {

    private List<String> cart;

    public Cart(){
        super();
    }

    public Cart(List<String> cart){
        super();
        this.cart = cart;
    }

    public void setCart(List<String> cart){
        this.cart=cart;
    }

    public List<String> getCart(){
        return this.cart;
    }
}
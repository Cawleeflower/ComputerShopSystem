/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.ArrayList;

/**
 *
 * @author Ng Pheng Loong
 */
public class Cart {
    private String cartId;
    private ArrayList<String> productName = new ArrayList<String>();
    private ArrayList<Double> totalPrice = new ArrayList<Double>();
    private ArrayList<Integer> quantity = new ArrayList<Integer>();
    private double subTotalPrice;

    public Cart() {
        this("","",0.0,0,0.0);
    }

    public Cart(String cartId, String productName, double totalPrice, int quantity, double subTotalPrice) {
        this.cartId = cartId;
        this.subTotalPrice = subTotalPrice;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public double getSubTotalPrice() {
        return subTotalPrice;
    }

    public void setSubTotalPrice(double subTotalPrice) {
        this.subTotalPrice = subTotalPrice;
    }
    
    public void addToCart(String newProductName, double newTotalPrice, int newQuantity) {
        this.productName.add(newProductName);
        this.totalPrice.add(newTotalPrice);
        this.quantity.add(newQuantity);
    }
    
    public void removeFromCart(String newProductName, double newTotalPrice, int newQuantity) {
        this.productName.remove(newProductName);
        this.totalPrice.remove(newTotalPrice);
        this.quantity.remove(newQuantity);
    }
    
    public void checkout() {
        
    }
    
    public void removeAllFromCart() {
        this.productName.clear();
        this.totalPrice.clear();
        this.quantity.clear();
    }
    
    public void changeQuantity(String productName, int newQuantity) {
        int index = this.productName.indexOf(productName);
        this.quantity.set(index, newQuantity);
    }

    @Override
    public String toString() {
        return "CartId=" + cartId + "\nproductName=" + productName + "\ntotalPrice=" + totalPrice + "\nquantity=" + quantity + "\nsubTotalPrice=" + subTotalPrice;
    }
    
}

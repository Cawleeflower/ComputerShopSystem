/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;

/**
 *
 * @author user
 */
public class OrderDetail {
    private String product;
    private int quantity;
    private double subTotal,total;
    private Cart cart;
    
    public OrderDetail(){
    
    }

    public OrderDetail(String product, int quantity, double subTotal, double total) {
        this.product = product;
        this.quantity = quantity;
        this.cart=cart;
        this.subTotal = subTotal;
        this.total = total;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void cartList(Cart cart,Connection con){
      System.out.print("\n"+cart.toString(con));
    
    }
}

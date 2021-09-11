/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;
import java.util.Date;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class Order {
    private DeliveryClass delivery;
    private CustomerClass customer;
    private Cart cart;
    private Order order;
    private String orderId,productName,customerId,cutomerName,customerContent,customerStates;
    private static Double amount;
    private Double deliveryFee,totalAmount,discount,totalProductPrice;
    protected String orderDate;
    private int productQuantity;

  
    public Order(){
      
    }

    public Order(DeliveryClass delivery, CustomerClass customer, Cart cart, Order order, String orderId, Double deliveryFee, Double totalAmount, Double discount, String paymentDate) {
        this.delivery = delivery;
        this.customer = customer;
        this.cart = cart;
        this.order = order;
        this.orderId = orderId;
        this.deliveryFee = deliveryFee;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.orderDate = orderDate;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    
    
    public CustomerClass getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerClass customer) {
        this.customer = customer;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public DeliveryClass getDelivery() {
        return delivery;
    }

    public void setDelivery(DeliveryClass delivery) {
        this.delivery = delivery;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String OrderId) {
        this.orderId = OrderId;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Double getAmount() {
        return amount;
    }

    public Double getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(Double deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
    
     public String autoDate(){
    
                      Date date = Calendar.getInstance().getTime();  
                      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
                      String strDate = dateFormat.format(date);  
                      System.out.println("Today Date: " + strDate); 
                      return strDate;
    }
    
    public void autoCreateOrderId(Order order, String OrderId)
    { 
     order.setOrderId("");
       if (order.getOrderId()== "") {
                            order.setOrderId("O1000");
                            
                        } else {
                            OrderId = order.getOrderId();
                            int id, num;
                            OrderId = OrderId.substring(1, 5);
                            id = (Integer.parseInt(OrderId)) + 1;
                            order.setOrderId("O" + id);
 
                        }
             String query = "Select * from Order WHERE OrderId ='" + OrderId + "DESC LIMIT 1";
             System.out.println("\nOrder ID:"+order.getOrderId());
    }
        
    public void CalSubTotal(int discount,Cart cart,Order order,CustomerClass customer,DeliveryClass delivery,String cartList){
          
            try{
              
                  cartList = cartList.substring(1, cartList.length()-1);           //remove curly brackets
                  String[] keyValuePairs = cartList.split(">");              //split the string to creat key-value pairs
                  Map<String,String> map = new HashMap<>();

              for(String pair : keyValuePairs)                        //iterate over the pairs
             {
                 String[] entry = pair.split("=");                   //split the pairs to get key and value 
                 map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
             }
                cart.setSubTotalPrice(Double.parseDouble(map.get("subTotalPrice")));
                System.out.println("\n-----------------------------------------");   
                System.out.println("|The Amount:           |RM"+cart.getSubTotalPrice()+"\t\t|");
           
           deliveryFee=order.deliveryFee(delivery, customer);
           
           amount=cart.getSubTotalPrice();
             if(amount<=200.00){
                discount=0;
             }
             else if(amount>=500 && amount<=1000)
             {
                discount=20;
             }
             else{
               discount=50;
             }
               System.out.print("\n|Discount:             |RM"+discount+"\t\t|");
               totalAmount=amount+deliveryFee-discount;
               System.out.println("\n-----------------------------------------"); 
               System.out.print("|SubTotal:             |RM"+totalAmount+"\t\t|");
               System.out.println("\n-----------------------------------------");  
          }
          catch(Exception ex){
            System.out.print(ex);
          }
    }
    
    public Double deliveryFee(DeliveryClass delivery,CustomerClass customer){
      if(customer.getStates()==delivery.getStates()){
         System.out.print("|Delivery Fee:         |RM"+delivery.getDeliveryFees()+"\t\t|");
         
      }
      return delivery.getDeliveryFees();
    }
    
    public void runInsertQuery(Connection con,String newOrderId,String newDate,String newCustomerId,String newCustomerName,String newCustomerContent,String newCustomerStates,String newProductName,int newQuantity,Double newTotalProductPrice,Double newAmount,Double newDelivery,Double newDiscount,Double newTotalAmount){        
        this.orderId=newOrderId;
        this.orderDate=newDate;
        this.customerId=newCustomerId;
        this.cutomerName=newCustomerName;
        this.customerContent=newCustomerContent;
        this.customerStates=newCustomerStates;
        this.productName=newProductName;
        this.productQuantity=newQuantity;
        this.totalProductPrice=newTotalProductPrice;
        this.amount=newAmount;
        this.deliveryFee=newDelivery;
        this.discount=newDiscount;
        this.totalAmount=newTotalAmount;
        String insertNewSql = "INSERT INTO ORDER(ORDERID,ORDERDATE,CUSTID,CUSTNAME,CUSTCONTENT,CUSTSTATES,PRODNAME,PRODQUANTITY,PRODTOTALPRICE,AMOUNT,DELIVERYFEE,DISCOUNT,TOTALAMOUNT)" + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
        Statement insertStmt = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
        preparedStatement.setString(1, orderId);
        preparedStatement.setString(2,orderDate);
        preparedStatement.setString(3, customerId);
        preparedStatement.setString(4, cutomerName);
        preparedStatement.setString(5, customerContent);
        preparedStatement.setString(6, customerStates);
        preparedStatement.setString(7, productName);
        preparedStatement.setInt(8, productQuantity);
        preparedStatement.setDouble(9, totalProductPrice);
        preparedStatement.setDouble(10, amount);
        preparedStatement.setDouble(11, deliveryFee);
        preparedStatement.setDouble(12, discount);
        preparedStatement.setDouble(13, totalAmount);
      
        preparedStatement.executeUpdate();   
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

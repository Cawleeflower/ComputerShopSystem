/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.time.LocalDateTime; 
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class Payment {
    private String paymentMethod,paymentId;
    private Order order;
    private PaymentMethod payMethod;
    protected Date dateCreated;
    
    public Payment(){
    
    }
    
    public Payment(String paymentMethod){
       this.paymentMethod=paymentMethod;
       this.order=order;
       this.paymentId=paymentId;
       this.payMethod=payMethod;
       dateCreated = new Date();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
    
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public PaymentMethod getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(PaymentMethod payMethod) {
        this.payMethod = payMethod;
    }
    
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void autoCreatePaymentId(Payment payment, String PaymentId)
    { 
     payment.setPaymentId("");
       if (payment.getPaymentId()== "") {
                            payment.setPaymentId("P1000");
                            
                        } else {
                            PaymentId = payment.getPaymentId();
                            int id, num;
                            PaymentId = PaymentId.substring(1, 5);
                            id = (Integer.parseInt(PaymentId)) + 1;
                            payment.setPaymentId("P" + id);
 
                        }
             String query = "Select * from Payment WHERE PaymentId ='" + PaymentId + "DESC LIMIT 1";
             System.out.println("\nPayment ID:"+payment.getPaymentId());
    }
    
    public void outPutPayment(Payment payment,Order order,Customer customer,Cart cart){
        System.out.println("\n\t\tComputer Shop");
        System.out.println("\t1/7 Taman PL, 1/5 Jalan KaXiang");
        System.out.println("  \tKuala Lao,Ampang 68000");
        System.out.println("------------------------------------------------------");
        System.out.println("|\t\tPayment Receipt");
        System.out.println("|Payment ID:"+payment.getPaymentId()+"Order ID:"+order.getOrderId());
        System.out.println("|Customer ID:"+"Date:"+payment.getDateCreated());
       // System.out.println("|"+cart.toString(con));
        System.out.println("|Total:"+order.getSubTotal());
        System.out.println("|Payment Method:"+payment);
        System.out.println("--------------------------------------------------------");
        System.out.println("");
        System.out.println("");
       
    }
    
//     public void runInsertQuery(Connection con,String newPaymentId,String newOrderId,String newCustomerId,String newCustomerName,String newCustomerContent,String newCustomerStates,String newProductName,int newQuantity,Double newAmount,Double newDelivery,Double newDiscount,Double newSubTotal){        
//        this.orderId.add(newOrderId);
//        this.productDetail.add(newProductDetail);
//        this.
//        stringpaymentId = String.join(",", this.paymentId);
//        stringTotalPrice = totalPrice.stream().map(Object::toString).collect(Collectors.joining(","));
//        stringQuantity = quantity.stream().map(Object::toString).collect(Collectors.joining(","));
//        String insertNewSql = "INSERT INTO Order(CartId, ProductName, TotalPrice, Quantity, SubTotalPrice)" + "VALUES (?,?,?,?,?)";
//        try {
//        Statement insertStmt = con.createStatement();
//        PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
//        preparedStatement.setString(1, this.cartId);
//        preparedStatement.setString(2, stringProductName);
//        preparedStatement.setString(3, stringTotalPrice);
//        preparedStatement.setString(4, stringQuantity);
//        preparedStatement.setDouble(5, subTotalPrice);
//        preparedStatement.executeUpdate();   
//        } catch(SQLException ex) {
//            ex.printStackTrace();
//        }
//    }
}

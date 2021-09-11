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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;

/**
 *
 * @author user
 */
public class Payment {
    private String paymentMethod,paymentId;
    private Order order;
    private Double paymentAmount;
    private PaymentMethod payMethod;
    protected String paymentDate;
    
    public Payment(){
    
    }
    
    public Payment(String paymentMethod,String paymentID,Double paymentAmount){
       this.paymentMethod=paymentMethod;
       this.order=order;
       this.paymentId=paymentId;
       this.payMethod=payMethod;
       this.paymentAmount=paymentAmount;
       this.paymentDate=paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
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
    
    public String autoDate(){
    
                      Date date = Calendar.getInstance().getTime();  
                      DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");  
                      String strDate = dateFormat.format(date);  
                    
                      return strDate;
    }
    
    public void outPutPayment(Database database, Payment payment,Order order,CustomerClass customer,Cart cart){
        System.out.println("\n\t\tComputer Shop");
        System.out.println("\t1/7 Taman PL, 1/5 Jalan KaXiang");
        System.out.println("  \tKuala Lao,Ampang 68000");
        System.out.println("------------------------------------------------------");
        System.out.println("|\t\tPayment Receipt");
        System.out.println("|Payment ID:"+payment.getPaymentId()+"Order ID:"+order.getOrderId());
        System.out.println("|Customer ID:"+customer.getCustID()+"Date:"+payment.autoDate());
        System.out.println("|"+cart.toString(database.getCon()));
        System.out.println("|Total:"+order.getTotalAmount());
        System.out.println("|Payment Method:"+payment);
        System.out.println("--------------------------------------------------------");
        System.out.println("");
        System.out.println("");
       
    }
    
     public void runInsertQuery(Connection con,String newPaymentId,String newPaymentDate,Double newPaymentAmount,String newPaymentMethod){        
        this.paymentId=newPaymentId;
        this.paymentAmount=newPaymentAmount;
        this.paymentMethod=newPaymentMethod;
        this.paymentDate=newPaymentDate;
        String insertNewSql = "INSERT INTO PAYMENT(PAYMENTID, PAYMENTDATE, PAYMENTAMOUNT, PAYMENTMETHOD)" + "VALUES (?,?,?,?)";
        try {
        Statement insertStmt = con.createStatement();
        PreparedStatement preparedStatement = con.prepareStatement(insertNewSql);   
        preparedStatement.setString(1, this.paymentId);
        preparedStatement.setString(2, paymentDate);
        preparedStatement.setDouble(3, paymentAmount);
        preparedStatement.setString(4, paymentMethod);
        preparedStatement.executeUpdate();   
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class PaymentMethod extends Payment{
    private int paymentType;
    private String debitCard,creditCard;
    
    public PaymentMethod() {
    }

    public PaymentMethod(int paymentType, String debitCard, String creditCard, String paymentMethod) {
      
        this.paymentType = paymentType;
        this.debitCard = debitCard;
        this.creditCard = creditCard;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public String getDebitCard() {
        return debitCard;
    }

    public void setDebitCard(String debitCard) {
        this.debitCard = debitCard;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    
    public void SelectPaymentMethods(Database database){
       int method;
       method=method(database);
       
    }
     public int method(Database database){
       int method=0;
     
       Scanner d=new Scanner(System.in);
       System.out.println("------------------");
       System.out.println("Payment Methods:");
       System.out.println("------------------");
       System.out.println("1.Debit Card");
       System.out.println("2.Credit Card");
       System.out.println("------------------");
       System.out.print("Select the Method:");
       try{
          method = d.nextInt();
          if(method==1){ 
              
              debitCard(database);
            }
          else if(method==2){ 
              creditCard(database);
       
           }
          else{
            System.out.print("\nOnly can Select 1 or 2.\n");
            method(database);
           }
       }catch(InputMismatchException e){
          System.out.print("\nOnly can enter Integer\n");
          method(database);
       }
       return method;
    }
   
    
    public String debitCard(Database database){
     String debitCard = null;
     Payment payment=new Payment();
     CustomerClass customer=new CustomerClass();
     Cart cart=new Cart();
     Order order = new Order();
     Scanner d=new Scanner(System.in);
     System.out.print("Debit Card");
     System.out.print("\nEnter your Card Number(xxxx-xxxx-xxxx-xxxx):");
          try{
           debitCard = d.nextLine();
           System.out.print("\nSuccesful Pay");
      
          payment.outPutPayment(database,payment, order, customer, cart);
           
          }catch(InputMismatchException e){
            System.out.print("\nCard number cannot more than 20 value.");
            debitCard(database);
          }
          return debitCard;
    }
    
    public String creditCard(Database database){
    String creditCard = null;
    Payment payment=new Payment();
    CustomerClass customer=new CustomerClass();
     Cart cart=new Cart();
     Order order = new Order();
     Scanner c=new Scanner(System.in);
     System.out.print("Credit Card");
     System.out.print("\nEnter your Card Number(xxxx-xxxx-xxxx-xxxx):");
          try{
           creditCard = c.nextLine();
           System.out.print("\nSuccesful Pay");
        payment.outPutPayment(database,payment, order, customer, cart);
           
          }catch(InputMismatchException e){
            System.out.print("\nCard number cannot more than 20 value.");
            creditCard(database);
          }
          return creditCard;}
}

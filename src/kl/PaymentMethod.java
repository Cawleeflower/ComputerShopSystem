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
        super(paymentMethod);
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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
    
    public void SelectPaymentMethods(){
       int method;
       method=method();
       
    }
     public int method(){
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
              
              debitCard();
            }
          else if(method==2){ 
              creditCard();
       
           }
          else{
            System.out.print("\nOnly can Select 1 or 2.\n");
            method();
           }
       }catch(InputMismatchException e){
          System.out.print("\nOnly can enter Integer\n");
          method();
       }
       return method;
    }
   
    
    public String debitCard(){
     String debitCard = null;
     Payment payment=new Payment();
     Scanner d=new Scanner(System.in);
     System.out.print("Debit Card");
     System.out.print("\nEnter your Card Number(xxxx-xxxx-xxxx-xxxx):");
          try{
           debitCard = d.nextLine();
           System.out.print("\nSuccesful Pay");
            payment.outPutPayment(payment, order);
           
          }catch(InputMismatchException e){
            System.out.print("\nCard number cannot more than 20 value.");
            debitCard();
          }
          return debitCard;
    }
    
    public String creditCard(){
    String creditCard = null;
    Payment payment=new Payment();
     Scanner c=new Scanner(System.in);
     System.out.print("Credit Card");
     System.out.print("\nEnter your Card Number(xxxx-xxxx-xxxx-xxxx):");
          try{
           creditCard = c.nextLine();
           System.out.print("\nSuccesful Pay");
           payment.outPutPayment(payment, order);
           
          }catch(InputMismatchException e){
            System.out.print("\nCard number cannot more than 20 value.");
            creditCard();
          }
          return creditCard;}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;
import java.util.Scanner;
import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.stream.Collectors;
/**
 *
 * @author user
 */
public class Test2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Database dbComputerShop = new Database();
       Order order= new Order();
       Payment payment= new Payment();
       PaymentMethod payMethod= new PaymentMethod();
       Register register= new Register();
       DeliveryClass delivery= new DeliveryClass();
       Cart cart = new Cart();
       OrderDetail orderDetail= new OrderDetail();
       OrderCustomerDetail orderCustomerDetail= new OrderCustomerDetail();
       CancelOrder cancel= new CancelOrder();
      
       orderCustomerDetail.CustomerDetail(register);
       String OrderId=null;
       order.autoCreateOrderId(order,OrderId);
       cart.setCartId("b@gmail.com");
       orderDetail.setCart(cart);
       orderDetail.cartList(cart,dbComputerShop.getCon());
       register.setStates("Johor");
       delivery.setStates("Johor");
       order.CalSubTotal(0, cart, order, register, delivery, cart.toString(dbComputerShop.getCon()));
       Confirm();
       
    }
    
    public static void Confirm(){  
        char status=0;
         PaymentMethod payMethod= new PaymentMethod();
        CancelOrder cancel= new CancelOrder();
        Scanner s= new Scanner(System.in);
        System.out.print("\nConfirm Order (Y/N):");
         status = s.next().charAt(0);
         if(status=='Y' || status=='y'){
          payMethod.SelectPaymentMethods();
         }
         else if(status=='N'|| status=='n'){
          cancel.Cancel();
         }
         else{
          System.out.print("\nOnly can enter Y or N");
          Confirm();         
         }
       
    }
    
}

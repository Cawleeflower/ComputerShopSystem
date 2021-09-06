/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author user
 */
public class CustomerHome {
    public static void main(String[] args) {
       Customer CustInf = new Customer();
       
       Scanner s1 = new Scanner(System.in); 
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
       }catch (SQLException e) {
            e.printStackTrace();
       }
         
          int optional = Customer.mainPage();
        
        if (optional == 1){
            int opt = Customer.DisplayMenu();
            Customer.displayInfor();
        }
        else if(optional == 2)
            Customer.updateInfor(Email, Password, CustName, Address, States, DateOfBirth, optional, myConObj);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author user
 */
public class TestLogin {
    public static void main(String[] args){
        Login log = new Login();
        Register reg = new Register();
        
       Scanner s1 = new Scanner(System.in); 
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
         
         while(true){
             int optional = Login.mainPage();
        
        if (optional == 1){
            Login.DisplayMenu(reg);   
        }
        else if(optional == 2){
            reg = Register.CustRegister();
        }
      }
    }
}

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
 * @author User
 */
public class StaffHomePage {
    public static void main(String[] args) {
       Staff staff = new Staff();
       Purchase pch = new Purchase();
       Scanner s1 = new Scanner(System.in);
       StaffClass sc = new StaffClass();
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
       }catch (SQLException e) {
            e.printStackTrace();
       }
      
       int optional = Staff.mainPage();
        
       if(optional ==1){
           Staff.addStaff();
       }
        if (optional == 2){
            int opt = Staff.subMenu();
            Staff.printStaffInformation(opt);
        }
        if (optional == 3 ){
            Delivery.delivery();
           
    }
         if(optional == 4){
             Purchase.purchasesMenu();
         }
          if(optional == 5){
             sc.displayDataStaff();
         }
           if(optional == 6){
             
             //UpdateStaff.updateLogoutTime(myConObj, StaffID);
         }
    }
}

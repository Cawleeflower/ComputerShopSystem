/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
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
       Purchases pch = new Purchases();
       Scanner s1 = new Scanner(System.in); 
       Connection myConObj = null;
         try{
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
       }catch (SQLException e) {
            e.printStackTrace();
       }
      
       int optional = Staff.mainPage();
        
        if (optional == 1){
            int opt = Staff.subMenu();
            Staff.printStaffInformation(opt);
        }
        if (optional == 2 ){
            Delivery.delivery();
           
    }
         if(optional == 3){
             Purchases.purchasesMenu();
         }
          if(optional == 4){
             
         }
           if(optional == 5){
             
             //UpdateStaff.updateLogoutTime(myConObj, StaffID);
         }
    }
}

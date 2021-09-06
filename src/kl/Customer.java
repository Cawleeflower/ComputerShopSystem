/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 *
 * @author user
 */
public class Customer {
    public static int mainPage(){
        Scanner s1 = new Scanner(System.in); 
        int optional = 0;
        System.out.println("***************************************************************");
        System.out.println("*                     1 Customer Information                  *");
        System.out.println("*                     2 Product                               *");
        System.out.println("*                     3 Purchasing                            *");
        System.out.println("*                     4 Logout                                *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1,2,3,4,5) : ");
        optional = s1.nextInt();
        return optional;
   }
    
    public static int DisplayMenu(){
        Scanner s1 = new Scanner(System.in);
        
        System.out.println("--------------------------------");
        System.out.println("1. Display Information          ");
        System.out.println("2. Update Infotmation           ");
        System.out.println("Select your choose :");
        int choose = s1.nextInt();
        
        if(choose == 1){
            displayInfor();
        }
        if(choose == 2){
            updateInfor();
        }
        return choose;
    }
    
    public static void displayInfor(){
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "Select * from Register";
        String url = "";


        try {
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);
            
            while (myResObj.next()){
                String email = myResObj.getString("Email");
                String password = myResObj.getString("Password");
                String name = myResObj.getString("CustName");
                String address = myResObj.getString("Address");
                String states = myResObj.getString("States");
                int phone = myResObj.getInt("PhoneNumber");
                String dateOfBirth = myResObj.getString("DateOfBirth");
                System.out.println("Email:"+ email +"\nPassword:"+ password +"\nName"+ name +"\nAddress:"+ address +"\nStates:"+ states +"\nPhone Number:"+ phone +"\nDate of Birth:"+ dateOfBirth);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateInfor(String Email, String Password, String CustName, String Address, String States, String DateOfBirth, int phoneNo, Connection myConObj){
        Register reg = new Register();
        String updateSql = "UPDATE Register SET " + Password + "=" + Password+ ",CustName= '" + CustName +"'"+",Address= '" + Address +"'"+",States= '" + States +"'"+",DateOfBirth= '" + DateOfBirth +"'"+",PhoneNumber= '" + phoneNo +"'"+ " WHERE Email = '" + Email +"'";
         try {
         Statement mystatObj = myConObj.createStatement();
         mystatObj.execute(updateSql);
        } catch (Exception ex) {
            System.out.println(ex);
        }
         System.out.println("Update Sucessfull");
         System.out.println("==================");
         printInf(reg);
    }
    
    private static void printInf(Register reg){
        System.out.print(reg.toString());
    }

    }
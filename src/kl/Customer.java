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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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
        System.out.println("*                     3 Cart                                  *");
        System.out.println("*                     4 History                               *");
        System.out.println("*                     5 Logout                                *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1-5) : ");
        optional = s1.nextInt();
        return optional;
   }
    
    public static int DisplayCustomerMenu(String Email){
        Scanner s1 = new Scanner(System.in);
        String Password="",CustName="",Address="",States="",DateOfBirth="",CustID="";
        int phoneNo=0;
        Connection myConObj=null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "Select * from Register";
        String url = "";
        try {
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
                       query = "Select * from Register WHERE Email ='" + Email + "'";
                       myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                       mystatObj = myConObj.createStatement();
                       ResultSet rs = mystatObj.executeQuery(query);
                       ResultSetMetaData rsMetaData = rs.getMetaData();
                       int count = rsMetaData.getColumnCount();
                       if(rs.next()){
                           System.out.println("******************************************************************");
                           System.out.println("*                           Customer Information                 *");
                           System.out.println("******************************************************************");
                           System.out.println("*Customer ID           :"+rs.getString(8)+"                                         *");
                           System.out.println("*Customer Name         :"+rs.getString(3)+"                                         *");
                           System.out.println("*Customer Email        :"+rs.getString(1)+"                                         *");
                           System.out.println("*Customer Password     :"+rs.getString(2)+"                                         *");
                           System.out.println("*Customer Address      :"+rs.getString(4)+"                                         *");
                           System.out.println("*Customer States       :"+rs.getString(5)+"                                         *");
                           System.out.println("*Customer Phone Number :"+rs.getString(6)+"                                         *");
                           System.out.println("*Customer Date of Birth:"+rs.getString(7)+"                                         *");
                           System.out.println("******************************************************************");
                       }
            }catch(SQLException e){
                e.printStackTrace();
            }
        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("1. Update Infotmation           ");
        System.out.println("2. Exit                         ");
        System.out.println("Select your choose :");
        int choose = s1.nextInt();
        
        if(choose == 1){
            updateInfor(Email);
        }
        else if(choose == 2){
            mainPage();
        }
        else{
            System.out.println("Invalid number please insert again");
            DisplayCustomerMenu(Email);
        }
        return choose;
    }
    
    public static void updateInfor(String Email){
        Register reg = new Register();
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        String updateSql ="";
        Connection myConObj = null;
        System.out.println("--------------------------------");
        System.out.println("1. Change Password              ");
        System.out.println("2. Change Address               ");
        System.out.println("3. Change States                ");
        System.out.println("4. Change Phone Number          ");
        System.out.println("5. Exit                         ");
        System.out.println("Select your choose (1-5):");
        int choose = s1.nextInt();
        if(choose == 1){
            System.out.println("Please Enter your new Password :");
            String Password = s2.nextLine();
            updateSql = "UPDATE Register SET Password ='" + Password + "' WHERE Email = '" + Email +"'";
        } 
        else if(choose == 2){
            System.out.println("Please Enter your new Address :");
            String Address = s2.nextLine();
            updateSql = "UPDATE Register SET Address ='" + Address + "' WHERE Email = '" + Email +"'";
        }
        else if(choose == 3){
            System.out.println("Please Enter your new States :");
            String States = s2.nextLine();
            updateSql = "UPDATE Register SET States ='" + States + "' WHERE Email = '" + Email +"'";
        }
        else if(choose == 4){
            System.out.println("Please Enter your new Phone Number :");
            String PhoneNo = s2.nextLine();
            updateSql = "UPDATE Register SET PhoneNo ='" + PhoneNo + "' WHERE Email = '" + Email +"'";
        }
        else if(choose == 5){
            DisplayCustomerMenu(Email);
        }
        System.out.println("Are you sure you want update (y/n)");
        String choice = s2.nextLine();
        if(choice.equals("y") || choice.equals("Y")){
         try {
             myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
         Statement mystatObj = myConObj.createStatement();
         mystatObj.execute(updateSql);
        } catch (Exception ex) {
            System.out.println(ex);
        }
         System.out.println("Update Sucessfull");
         System.out.println("==================");
         updateInfor(Email);
        }
        else if(choice.equals("n") || choice.equals("N")){
            System.out.println("Update Unsucessfull");
         System.out.println("==================");
         updateInfor(Email);
        }
    }


    public static String CustLogin(){
        Scanner s1 = new Scanner(System.in);
        Register reg = new Register();
        String Email ="";
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        ResultSet rs;
        try{
            do{
                       System.out.print("Email : ");
                       myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
                       Email = s1.next();
                       System.out.print("Password : ");
                       String Password = s1.next();
                       String query = "Select * from Register WHERE Email ='" + Email + "' and Password='"+ Password +"'";
                       query = "Select * from Register WHERE Password ='" + Password + "'";
                       mystatObj = myConObj.createStatement();
                       rs = mystatObj.executeQuery(query);
            }while(!rs.next());
            }catch (SQLException e) {
                       e.printStackTrace();
            }
        return Email;

    }
   
    }
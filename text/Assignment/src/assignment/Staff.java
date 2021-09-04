/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
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
import java.time.format.DateTimeFormatter;  
/**
 *
 * @author User
 */
public class Staff {

    /**
     * @param args the command line arguments
     */
   public static void printStaffInformation(int optional){
       UpdateStaff upstf = new UpdateStaff();
      // StaffHomePage stfhp = new StaffHomePage();
       
       Connection myConObj = null;
       Statement mystatObj = null;
       ResultSet myResObj = null;
       try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
       }catch (SQLException e) {
            e.printStackTrace();
       }
      
       //String query = "Select * from computershop.Staff";
       String url = "";
       Scanner s1 = new Scanner(System.in); 
       Scanner s2 = new Scanner(System.in); 
      
       
       if (optional == 1){
          try {  
            System.out.println("StaffID :");
            String StaffID = s1.nextLine();
            System.out.println("");
            System.out.println("Staff Information for : " + StaffID);
            String query = "Select * from Staff WHERE StaffID='" + StaffID + "'";
            mystatObj = myConObj.createStatement();
            ResultSet rs = mystatObj.executeQuery("select * from Staff");
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            myResObj = mystatObj.executeQuery(query);
            if(myResObj.next()){
               for (int i = 1; i <= count; i++) {
                    String columnValue = myResObj.getString(i);
                    System.out.println("=======================================");
                    System.out.println(rsMetaData.getColumnName(i)+" : "+ columnValue);
                    System.out.println("=======================================");
                }
               int opt = Staff.subMenu();
               printStaffInformation(opt);
               
            }else
               System.out.println("Not match account found. Please contact the HR department");
               System.out.println("==========================================================");
               int opt = Staff.subMenu();
               printStaffInformation(opt);
        } catch (SQLException e) {
            e.printStackTrace();
        }
           
         }
         if(optional == 2){
             System.out.println("============================");
             System.out.println("Please Enter Your Staff ID:");
             System.out.println("============================");
             String StaffID = s1.next();
             System.out.println("============================");
             System.out.println("Please Enter Your New Email");
             System.out.println("============================");
             String Email = s1.next();
             System.out.println("============================");
             System.out.println("Please Enter Your New Contact Number");
             System.out.println("============================");
             int ContactNumber = s2.nextInt();
             
            upstf.updateStaff(myConObj,"ContactNumber", ContactNumber, StaffID, Email);
            int opt = Staff.subMenu();
            printStaffInformation(opt);
        }           
         if(optional == 3){
            int opt = Staff.mainPage();
            printStaffInformation(opt);
         }
           
        
        
   }
   public static int mainPage(){
        Scanner s1 = new Scanner(System.in); 
        int optional = 0;
        System.out.println("***************************************************************");
        System.out.println("*                     1 Staff Information                     *");
        System.out.println("*                     2 Delivery Status                       *");
        System.out.println("*                     3 Purchasing                            *");
        System.out.println("*                     4 Staff                                 *");
        System.out.println("*                     5 Logout                                *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1,2,3,4,5) : ");
        optional = s1.nextInt();
        return optional;
   }
   public static int subMenu(){
       Scanner s1 = new Scanner(System.in); 
       int optional = 0;
       System.out.println("***************************************************************");
       System.out.println("*                    1 Staff Information                      *");
       System.out.println("*                    2 Staff Update                           *");
       System.out.println("*                    3 Back To Staff Main Page                *");
       System.out.println("***************************************************************");
       System.out.println("Please Enter Your Option(1,2,3) : ");
       optional = s1.nextInt();
       return optional;
   }
}


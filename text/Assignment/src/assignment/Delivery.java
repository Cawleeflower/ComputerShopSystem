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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
/**
 *
 * @author User
 */
public class Delivery {
    public static void delivery(){
        Scanner s1 = new Scanner(System.in);
        Scanner s2 = new Scanner(System.in);
        //StaffHomePage stfhp = new StaffHomePage();
        DeliveryClass dc = new DeliveryClass();
       // Staff stf = new Staff();
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
            try{
            myConObj = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/computershop", "ngphengloong", "lolhaha123");
            }catch (SQLException e) {
            e.printStackTrace();
            }
       String url = "";
       int opt=0;
       System.out.println("1. Create Delivery Information");
       System.out.println("2. Update Delivery Information");
       System.out.println("Pleases Enter Your Option(1,2):");
       opt = s1.nextInt();
       
      if(opt == 1){
                
                  try{
                       System.out.print("Customer name :");
                       String name = s2.nextLine();
                       String query = "Select * from Register WHERE CustName ='" + name + "'";
                       mystatObj = myConObj.createStatement();
                       ResultSet rs = mystatObj.executeQuery("select * from Register");
                       ResultSetMetaData rsMetaData = rs.getMetaData();
                       int count = rsMetaData.getColumnCount();
                       myResObj = mystatObj.executeQuery(query);
                       if(myResObj.next()){
                         
                          String Address = myResObj.getString("Address");
                          String States = myResObj.getString("States");
                          System.out.println("Address : "+ Address);
                          dc.setAddress(Address);
                          System.out.println("States : "+ States);
                          dc.setStates(States);
                          double deliveryFees = dc.calculateState();
                          dc.setDeliveryFees(deliveryFees);
                          System.out.println("-----------------------------");
                          
                        }
                           }catch (SQLException e) {
                           e.printStackTrace();
                           }
                 
               //TODO Generate Number Of Delivery Number
                        Random rd=new Random();
                        HashSet<Integer> set= new HashSet<Integer>();
                        while(set.size()<1)
                        {
                            int ran=rd.nextInt(99999)+100000;
                            set.add(ran);
                        }
                        int len = 6;
                        String random=String.valueOf(len);
                        for(int  random1:set)
                        {
                            System.out.println("Delivery ID :" + random1);
                            random=Integer.toString(random1);
                            dc.setDeliveryID(random);
                            System.out.println("===============================");
                        }
                        //TODO to Enter the Date Delivery
                        String str[] = {"year", "month", "day" };
                        String date = "";

                        for(int i=0; i<3; i++) {
                            System.out.println("Enter " + str[i] + ": ");
                            date = date + s1.next() + "/";
                        }
                        date = date.substring(0, date.length()-1);
                        System.out.println("date: "+ date); 
                         
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                        Date parsedDate = null;
                        dc.setDateDelivery(date);
                        System.out.println("===============================");
        
                        try {
                            parsedDate = dateFormat.parse(date);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                       //TODO to Optional Delivery Company
                       System.out.println("===============================");
                       System.out.println("PosLaju");
                       System.out.println("Skynet");
                       System.out.println("Gdex");
                       System.out.println("J&T Express");
                       System.out.println("");
                       System.out.println("Please Enter The Company Name");
                       String DeliveryComapany = s2.next();
                       dc.setDeliveryCompany(DeliveryComapany);
                       System.out.println("===============================");
                       
                       System.out.println("Details");
                       String Details = s1.next();
                       dc.setDetails(Details);
                       System.out.println("===============================");
               
                       DeliveryClass.insertDelivery(myConObj,dc.getDeliveryID(), dc.getDateDelivery(), dc.getDeliveryCompany(), dc.getDetails(), dc.getAddress(), dc.getStates(), dc.getDeliveryFees());
           
   
      }
      else{
           
            System.out.println("Enter Your Delivery ID: ");
            String deliveryID = s2.nextLine();
            
            System.out.println("Please Enter You New Details");
            String details = s2.nextLine();
            DeliveryClass.updateDelivery(myConObj, deliveryID, details);
      }
    }
     
    
      
}
     
          


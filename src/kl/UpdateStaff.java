/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class UpdateStaff {
    
    public static void updateStaff(Connection con, String columnToUpdate, int ContactNumber , String StaffID, String Email) {
        Staff stf = new Staff();
        String updateSql = "UPDATE Staff SET " + columnToUpdate + "=" + ContactNumber+ ",Email= '" + Email +"'"+ " WHERE StaffID = '" + StaffID +"'";
         try {
         Statement mystatObj = con.createStatement();
         mystatObj.execute(updateSql);
        } catch (Exception ex) {
            System.out.println(ex);
        }
         System.out.println("Update Sucessfull");
         System.out.println("==================");
         int opt = Staff.subMenu();
         stf.printStaffInformation(opt);
    }
    public static void updateLogoutTime(Connection con, String StaffID){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime now = LocalDateTime.now(); 
        Timestamp timestamp = Timestamp.valueOf(now);
        String updateSql = "UPDATE Staff SET " + "LogouTime" + "=" +  timestamp + " WHERE StaffID = '" + StaffID +"'";
         try {
         Statement mystatObj = con.createStatement();
         mystatObj.execute(updateSql);
        } catch (Exception ex) {
          System.out.println(ex);
        }
    }
}

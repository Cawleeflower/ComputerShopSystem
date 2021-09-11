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
import java.util.Date;
import java.util.HashSet;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author user
 */
public class Register {
    protected String Email,Password;
    String CustName;
    String phoneNo;

    public Register() {
        this("","","","");
    }

    public Register(String Email, String Password, String CustName, String phoneNo) {
        this.Email = Email;
        this.Password = Password;
        this.CustName = CustName;
        this.phoneNo = phoneNo;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setCustName(String CustName) {
        this.CustName = CustName;
    }
    
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
   
    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public String getCustName() {
        return CustName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    @Override
    public String toString() {
        return "Email=" + Email + "\nPassword=" + Password + "\nCustomer Name=" + CustName + "\nPhone No=" + phoneNo;
    }
    
    
    public static void insert(String Email, String Password, String CustName,String phoneNo,Connection myConObj){
    try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            String insertNewUserSQL = "INSERT INTO REGISTER (EMAIL,PASSWORD,CUSTNAME,PHONENUMBER)" + " VALUES (?,?,?,?)";
            String updateUserSQL = "UPDATE REGISTER SET EMAIL = ?" + " WHERE EMAIL = ?";
            PreparedStatement pstmt = myConObj.prepareStatement(insertNewUserSQL);
            
            pstmt.setString(1,Email);
            pstmt.setString(2, Password);
            pstmt.setString(3, CustName);
            pstmt.setString(4, phoneNo);

            
            
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
}
    public static Boolean ActivationCode(String Email) {

        final String username = "lol1234abd@gmail.com";
        final String password = "abc@abc123";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        String random="";
        Scanner s1 = new Scanner(System.in);
        
        javax.mail.Session session = javax.mail.Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lol1234abd@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(Email));
            message.setSubject("Activation Code");
            Random rd=new Random();
                        HashSet<Integer> set= new HashSet<Integer>();
                        while(set.size()<1)
                        {
                            int ran=rd.nextInt(9999)+10000;
                            set.add(ran);
                        }
                        int len = 5;
                        random=String.valueOf(len);
                        for(int  random1:set)
                        {
                            random=Integer.toString(random1);
                        }
            message.setText("Dear Mail Crawler,"
                + "\n\n Here is your activation code, Activation code:"+random+".");

            javax.mail.Transport.send(message);


        } catch (MessagingException e) {
                e.printStackTrace();
        }
        System.out.println("Enter Activation code:");
        String code = s1.nextLine();
        
        if(code == random){
            return true;
        }else
            return false;
        
    }
    
    public static Register CustRegister(String random){
        Connection myConObj = null;
        Statement mystatObj = null;
        ResultSet myResObj = null;
        String query = "SELECT * FROM REGISTER";
        String url = "";
        
        try {
            myConObj = DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
            mystatObj = myConObj.createStatement();
            myResObj = mystatObj.executeQuery(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        Register register = new Register();
        Scanner s1 = new Scanner(System.in);

        System.out.print("Enter Your Email:");
        String Email = s1.nextLine();
        register.setEmail(Email);
        System.out.print("Enter Your Password:");
        String Password = s1.nextLine();
        register.setPassword(Password);
        System.out.print("Enter Your Name:");
        String CustName = s1.nextLine();
        register.setCustName(CustName);
        System.out.print("Enter Your Phone Number:");
        String phoneNo = s1.nextLine();
        register.setPhoneNo(phoneNo);
        
        ActivationCode(Email);
        
        if(true){
            Register.insert(register.getEmail(),register.getPassword(),register.getCustName(),register.getPhoneNo(),myConObj);
            System.out.println("Register Successfully\n");
        }else{
            System.out.println("Activation code invalid\n");
        }
        return register;
    }
    
    public static void ForgotPassword(Register reg) {

        final String username = "lol1234abd@gmail.com";
        final String password = "abc@abc123";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Scanner s1 = new Scanner(System.in);
        String replacedPassword= "";
        
        
        System.out.println("Enter your Email:");
        String Email = s1.nextLine();
        
        javax.mail.Session session = javax.mail.Session.getInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
          });

        try {
            
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("lol1234abd@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
            InternetAddress.parse(Email));
            message.setSubject("Your Password");

            try {
                Connection myConObj=DriverManager.getConnection("jdbc:derby://localhost:1527/test", "ngphengloong", "123");
                String query = "SELECT * FROM REGISTER WHERE EMAIL = '" + Email + "'";
                Statement stmt = myConObj.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    replacedPassword= rs.getString("Password");
                }
            }catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            message.setText("Dear Mail Crawler,"
                + "\n\n Here is your Password, Password:"+replacedPassword+".");

            javax.mail.Transport.send(message);


        } catch (MessagingException e) {
                e.printStackTrace();
        }
        
    }
}

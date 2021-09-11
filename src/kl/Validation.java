/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;
import java.util.Scanner;
import java.util.regex.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author User
 */
public class Validation {
    public String validContact(String phone){
        Scanner s1 = new Scanner(System.in); 
        String ph = "\\d{3}-\\d{8}";
       if(phone.length()>=12 || phone == ph){
       } else{
         phone = "";
         System.out.println("Invalid, Plaese Follow This (011-23456789)");
         System.out.print("Plase Enter Phone Number : ");
         phone = s1.nextLine();
         return phone;
       }
         
     return phone;
    }
   
    public String validPass(String password){
        Scanner s1 = new Scanner(System.in);
        String pattern = "(?=.*[A-Z])(?=.*[0-9])(?=.*[a-z]).{8,}";
        if(password != pattern ){
             password = "";
             System.out.println("The Password Lenght Must Have 8 And Include One Uppercast And Digit");
             System.out.print("Please Enter Passwords : ");
             password = s1.next();
             return password;
        }
        else{
        }
        return password;
    }
    public String validEmail(String email){
        Scanner s1 = new Scanner(System.in);
        String expression = "[A-Za-z]+@+[A-Za-z]+\\.+[A-Za-z]{2,4}+$";
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(email);
        if(!m.matches()){
            email = "";
            System.out.print("Error, Plaeses Enter Again Email : ");
            email = s1.next();
            return email;
        }
        return email;
    }
    public int validAge(int Age){
        Scanner s1 = new Scanner(System.in);
        int age = 17;
        if(Age <= age){
            Age = 0;
            System.out.println("Error, You not enough age");
            System.out.print("Plases Enter Your Real Age :");
            Age = s1.nextInt();
            return Age;
        }
        else{
        }
        return Age;
    }
    public String validCard(String number, String type){
        Scanner s2 = new Scanner(System.in);
        String master = "5[1-5][0-9]{14}";
        String visa = "?:4[0-9]{12}(?:[0-9]{3})?";
        if (number != visa && type.equals("Visa")){
            number = "";
           System.out.println("Plases Enter The Correct Number(4xxx xxxx xxxx xxxx)");
           System.out.print("Plases Enter The Card Number");
           number = s2.nextLine();
           return number;
        } 
           if(number != master && type.equals("Master")){
               number = "";
                System.out.println("Plases Enter The Correct Number(5xxx xxxx xxxx xxxx)");
                System.out.print("Plases Enter The Card Number");
                number = s2.nextLine();
                return number;
           }
            else{
        }
            return number;
        }
       
       
    
    public String validCvv(String cvv){
        Scanner s1 = new Scanner(System.in);
        if(cvv.length()>=3){
        }
        else {
            cvv = "";
            System.out.println("Error! Plases Enter Correct CVV number in your back card");
            System.out.println("Plases Enter CVV number : ");
            cvv = s1.next();
            return cvv;
        }
        return cvv;
    }
    public String validExpDate(String ed){
          Scanner s1 = new Scanner(System.in);
          String Ed = "^[0-9]+[/]+[0-9]+";
          if(ed != Ed){
              System.out.println("Erorr! Plases Uncorrect Format For Expired Date Card (dd/mm) ");
              System.out.println("Pleses Enter Your Expired Date Card : ");
              ed = s1.next();
              return ed;
          }
          return ed;
    }
}
    

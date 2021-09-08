/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

import java.util.Scanner;

/**
 *
 * @author user
 */
public class Login {
    public static int mainPage(){
        Scanner s1 = new Scanner(System.in); 
        int optional = 0;
        System.out.println("***************************************************************");
        System.out.println("*                     1 Login                                 *");
        System.out.println("*                     2 Register                              *");
        System.out.println("***************************************************************");
        System.out.println("Please Enter Your Option(1,2) : ");
        optional = s1.nextInt();
        return optional;
   }
    
    public static int DisplayMenu(){
        Scanner s1 = new Scanner(System.in);
        
        System.out.println("--------------------------------");
        System.out.println("1. Customer Login               ");
        System.out.println("2. Staff Login                  ");
        System.out.println("Select your choose :");
        int choose = s1.nextInt();
        
        if(choose == 1){
            Customer.CustLogin();
            System.out.println("Login Successfully!");
            System.out.println("Welcome to computer shop!!");
            int optional = Customer.mainPage();
        
        if (optional == 1){
            Customer.DisplayMenu();    
        }
        }
        else if(choose == 2){
            
        }
        return choose;
    }
}

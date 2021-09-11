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
public class LoginClass extends Register{

    public LoginClass() {
        this("","","","");
    }

    public LoginClass(String Email, String Password, String CustName,String phoneNo) {
        super(Email, Password, CustName,phoneNo);
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
    
    @Override
    public String toString() {
        return "Email =" + Email + "\nPassword =" + Password ;
    }
    
}

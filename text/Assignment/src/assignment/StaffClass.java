/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

/**
 *
 * @author User
 */
public class StaffClass {
    private String staffID;
    private String name;
    private int age;
    private String gmail;
    private int contactNumber;
    

    public StaffClass() {
       this("","",0,"",0);
    }
    
    public StaffClass( String staffID, String name, int age, String gmail, int contactNumber) {
        this.staffID = staffID;
        this.name = name;
        this.age = age;
        this.gmail = gmail;
        this.contactNumber = contactNumber;
    }

    public int getAge() {
        return age;
    }

    public String getGmail() {
        return gmail;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    @Override
    public String toString() {
        return "staffID=" + staffID + ", name=" + name + ", age=" + age + ", gmail=" + gmail + ", contactNumber=" + contactNumber ;
    }
    public static void insertStaff(){
        
    }
    
    
}

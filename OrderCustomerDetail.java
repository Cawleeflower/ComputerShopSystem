/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kl;

/**
 *
 * @author user
 */
public class OrderCustomerDetail {
    private String customerName,email,address,status;
    private int phoneNo;
    private Register register;

    public OrderCustomerDetail() {
    }

    public OrderCustomerDetail(String customerName, String email, String address, String status, int phoneNo) {
        this.customerName = customerName;
        this.email = email;
        this.register=register;
        this.address = address;
        this.status = status;
        this.phoneNo = phoneNo;
    }

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }
    
    public void CustomerDetail(Register register){
      System.out.println("------------------------------------------------------");
      System.out.print("Content Name:"+register.getCustName()+"\t\t\tCustomer Id:"+register.getCustID());
      System.out.print("\nAddress:"+register.getAddress()+"\t\t\tStates:"+register.getStates());
      System.out.print("\nContent Number:"+register.getPhoneNo());
      System.out.println("\n------------------------------------------------------");
    }
}

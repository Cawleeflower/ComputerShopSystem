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
    private CustomerClass customer;

    public OrderCustomerDetail() {
    }

    public OrderCustomerDetail(String customerName, String email, String address, String status, int phoneNo) {
        this.customerName = customerName;
        this.email = email;
        this.customer=customer;
        this.address = address;
        this.status = status;
        this.phoneNo = phoneNo;
    }

    public CustomerClass getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerClass customer) {
        this.customer = customer;
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
    
    public void CustomerDetail(CustomerClass customer){
      System.out.println("------------------------------------------------------");
      System.out.print("Content Name:"+customer.getCustName()+"\t\t\tCustomer Id:"+customer.getCustID());
      System.out.print("\nAddress:"+customer.getAddress()+"\t\t\tStates:"+customer.getStates());
      System.out.print("\nContent Number:"+customer.getPhoneNo());
      System.out.println("\n------------------------------------------------------");
      
        System.out.println(""+customer.toString());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author N
 */
public class Registration 
{
    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private String name;
    private String address;
    private String dob;
    private String dor;
    private String status;
    private double balance = 0;
    
    public Registration(){}
    public Registration(String password,String firstName,String lastName,String address,String dob){
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = generateID(firstName.toLowerCase(),lastName.toLowerCase());
        this.address = address;
        this.dob = dob;
    }
    
    private String generateID(String firstName, String lastName){
        String generatedID = firstName.substring(0, 1) + "-" + lastName;
        return generatedID;
    }

    public String getID()
    {
        return id;
    }

    public void setID(String newID) 
    {
        this.id = newID;
    }
    
    public String getName(){
        return (this.firstName+ " " +this.lastName);
    }
    
    public String getAddress()
    {
        return address;
    }
      
    public void setAddress(String newAddress)
    {
        this.address = newAddress;
    }
      
    public String getDOB()
    {
        return dob;
    }
      
    public void setDOB(String newDOB)
    {
        this.dob = newDOB;
    }
    
    public String getDOR()
    {
        return dor;
    }
      
    public void setDOR(String newDOR)
    {
        this.dor = newDOR;
    }
      
    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String newPassword) 
    {
        this.password = newPassword;
    }
	
    public String getStatus()
    {
        return status;
    }

    public void setStatus(String newStatus) 
    {
        this.status = newStatus;
    }
    
    public double getBalance()
    {
        return balance;
    }
    
    public void setBalance(double newBalance)
    {
        this.balance = newBalance;
    }
}

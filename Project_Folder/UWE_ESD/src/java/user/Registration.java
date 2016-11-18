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
    private String ID;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String initials;
    private String name;
    private String status;
    private String DOB;
    private String DOR;
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String newName)
    {
        newName = "" +firstName + " "+lastName;
        this.name = newName;
    }

    public String getID()
    {
        return ID;
    }

    public void setID(String newID) 
    {
        newID = "" +initials + "-"+lastName; 
        this.ID = newID;
    }
      
    public String getInitials()
    {
        return initials;
    }
     
    public void setInitials(String newInitials) 
    {
        newInitials = firstName.substring(0, 3);
        initials = newInitials;
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
        return DOB;
    }
      
    public void setDOB(String newDOB)
    {
        this.DOB = newDOB;
    }
      
    public String getDOR()
    {
        return DOR;
    }
      
    public void setDOR(String newDOR)
    {
        DateFormat df = new SimpleDateFormat("ddMMyy");
        Date date = new Date();
        newDOR = df.format(date);
        this.DOR = newDOR;
    }
      
    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String newPassword) 
    {
        newPassword = DOB;
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
}

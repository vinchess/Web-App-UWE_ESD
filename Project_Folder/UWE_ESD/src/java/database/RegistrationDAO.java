/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import user.Registration;
import java.awt.Component;


/**
 *
 * @author N
 */
public class RegistrationDAO extends JDBC
{
    public void RegisterUser(String id, String NAME)
    {
        Component frame = null;
        conn = getConnection();
        String sql = "SELECT * FROM members where ID'"+ id +"' AND name='"+ NAME +"';";
        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                if(rs.getString("ID").equals(id) && rs.getString("name").equals(NAME))
                {
                     JOptionPane.showMessageDialog(frame, "User already exist", "Error", JOptionPane.ERROR_MESSAGE);
                }
                else
                {
                    status.getStatus() = "APPLIED";
                    JOptionPane.showMessageDialog(frame, "You have successfully registered as a Provisional Member, please process to payment to become an official member. The password for you account is you Date Of Birth in 'DDMMYY' format ", "Congratulations", JOptionPane.PLAIN_MESSAGE);
                    DateFormat df = new SimpleDateFormat("ddMMyy");
                    Date date = new Date();
                    password = df.format(date);
                    System.out.print("\nInserting records into table...");
                    stmt = conn.createStatement();
                    String SQL = "INSERT INTO members " + "VALUES ("+ ID +", "+ name +", "+ address +", "+ DOB +", "+ DOR +", "+ status +", "+ balance +")";
                    stmt.executeUpdate(SQL);
                    SQL = "INSERT INTO members " + "VALUES ("+ ID +", "+ name +", "+ password +", "+ status +")";
                    stmt.executeUpdate(SQL);          
                }
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
    }       
}

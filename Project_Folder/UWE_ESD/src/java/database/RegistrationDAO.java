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
import user.Registration;

/**
 *
 * @author N
 */
public class RegistrationDAO extends JDBC
{
    public void RegisterUser(String id, String NAME)
    {
        String result = "false";
        conn = getConnection();
        String sql = "SELECT * FROM members where id'"+ id +"' AND name='"+ NAME +"';";
        try
        {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next())
            {
                if(rs.getString("ID").equals(id) && rs.getString("name").equals(NAME))
                {
                    result = "false";
                }
                else
                {
                    Registration registration = new Registration();
                    registration.setStatus("APPLIED");
                    DateFormat df = new SimpleDateFormat("ddMMyy");
                    Date date = new Date();
                    registration.setPassword(df.format(date));
                    System.out.print("\nInserting records into table...");
                    stmt = conn.createStatement();
                    String SQL = "INSERT INTO members " + "VALUES ("+ registration.getID() +", "+ registration.getName()+", "+ registration.getAddress() +", "+ registration.getDOB() +", "+ registration.getDOR() +", "+ registration.getStatus() +", "+ registration.getBalance() +")";
                    stmt.executeUpdate(SQL);
                    SQL = "INSERT INTO users " + "VALUES ("+ registration.getID() +", "+ registration.getName() +", "+ registration.getPassword() +", "+ registration.getStatus() +")";
                    stmt.executeUpdate(SQL);
                    result = "true";
                }
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
    }       
}

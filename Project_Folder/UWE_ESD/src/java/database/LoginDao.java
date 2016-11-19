/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import user.User;

/**
 *
 * @author Richard
 */
public class LoginDao extends JDBC{
    public String authenticateUser (User userInput)
    {
        String result = "false";
        
        String username = userInput.getID();
        String passwordinput = userInput.getPassword();
        
        String sql = "SELECT * FROM users;";
               
        conn = getConnection();
        
        try
        {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String usernameDB = rs.getString("id");
                String passwordDB = rs.getString("password");
                if (username.equals(usernameDB) && passwordinput.equals(passwordDB)){
                    result = "SUCCESS";
                }
            }
            rs.close();
            conn.close();
            
        }catch(SQLException se)
            {
                System.out.println("SQL error occurred.");
            }
        
        return result;
    }
}

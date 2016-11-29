package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import user.User;

/**
 *
 * @author Richard
 */
public class LoginDao extends JDBC{
    //authenticate username and password from user input
    public String authenticateUser (User userInput){
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
                    if(rs.getString("status").equals("ADMIN"))
                        result = "ADMIN";
                    else
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
    }//end authenticateUser
}

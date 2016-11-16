/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import user.User;

/**
 *
 * @author Richard
 */
public class LoginDao {
    public String authenticateUser(User userInput)
    {
        String username = userInput.getID();
        String passwordinput = userInput.getPassword();
        
        //create connection with JDBC
        //call JDBC
        //JDBC return true or false on connection
        
        //resultSet = statement.getQeury("select userid, password from users");
        
        //loop resultSet
//        while(resultSet.next())
//        {
//            String usernameDB = resultSet.getString("userid");
//            String passwordDB = resultSet.getString("password");
//            
//            if (username.equals(usernameDB) && passwordinput.equals(passwordDB)){
//                return "SUCCESS";
//            }
//            
//        }
return "false";
    }
}

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
public class RegistrationDAO extends JDBC{
    
    public boolean checkExist(String id) throws SQLException{
        boolean result = false;
        conn = getConnection();
        String sql = "SELECT * FROM users where id='"+ id +"';";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("id").equals(id)){
                result = true;
            }
        }
        return result;
    }
    
    public String RegisterUser(Registration newUser) throws SQLException{
        String result = "false";
        conn = getConnection();
        
        stmt = conn.createStatement();
        
        conn.setAutoCommit(false);

        stmt.addBatch("INSERT INTO users VALUES('" + newUser.getID() + "','" + 
                newUser.getPassword() + "','APPLIED');");
        stmt.addBatch("INSERT INTO members VALUES('" + newUser.getID() + "','" + 
                newUser.getName()+ "','" + newUser.getAddress() + "','" + newUser.getDOB() + "',now(),'APPLIED','0.00');");

        int[] updateResult = stmt.executeBatch();

        if(checkResult(updateResult)){
            conn.commit();
            result = "User successfully registered. Proceed to login.";
        }else{
            conn.close();
            throw new SQLException("Commit Failed");
        }
            
        conn.close();

        return result;
    }
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }//end checkResult 
}

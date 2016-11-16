package database;
import java.sql.*;
/**
 *
 * @author Vincent
 */
public class AdminLoginDAO extends JDBC{
    public boolean authenticate(String username,String password){
        boolean result = false;
        String sql = "SELECT * FROM users where id='"+ username +"';";
        
        conn = getConnection();
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                if(rs.getString("id").equals(username) && rs.getString("password").equals(password))
                    result = true;
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
        return result;
    }
}

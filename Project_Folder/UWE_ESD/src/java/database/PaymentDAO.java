package database;
import java.sql.*;
import user.User;
/**
 *
 * @author Vincent
 */
public class PaymentDAO extends JDBC{
    public boolean updatePayment(User user, double payment){
        boolean result = false;
        //this line
        String sql = "UPDATE * FROM users where id='"+ user.getID() +"';";
        
        conn = getConnection();
        
        try{
            stmt = conn.createStatement();

            int updateResult = stmt.executeUpdate(sql);
            System.out.println(updateResult);
            //if ()
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
        return result;
    }
}

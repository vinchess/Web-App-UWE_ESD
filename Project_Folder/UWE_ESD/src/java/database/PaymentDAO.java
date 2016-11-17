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
        String sql = /*"UPDATE members SET balance=balance+"+ payment +
                            " WHERE id='"+ user.getID() + "';\n" +*/
                     "INSERT INTO payments(mem_id,type_of_payment,amount,date) " +
                            "VALUES('" + user.getID() + "','PAYMENT'," + payment + ",now());";
        
        conn = getConnection();
        
        try{
            stmt = conn.createStatement();

            int updateResult = stmt.executeUpdate(sql);
            if (updateResult==1) result = true;
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
        return result;
    }
}

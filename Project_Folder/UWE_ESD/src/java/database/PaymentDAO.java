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
        
        conn = getConnection();
        
        try{
            stmt = conn.createStatement();
            conn.setAutoCommit(false);

            stmt.addBatch("UPDATE members SET balance=balance+"+ payment +
                            " WHERE id='"+ user.getID() + "';");
            stmt.addBatch("INSERT INTO payments(mem_id,type_of_payment,amount,date) " +
                            "VALUES('" + user.getID() + "','PAYMENT'," + payment + ",now());");
            
            int[] updateResult = stmt.executeBatch();
            
            if(checkResult(updateResult)){
                conn.commit();
            }else{
                conn.close();
                throw new SQLException("Commit Failed");
            }
            
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return result;
    }
    
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }
}

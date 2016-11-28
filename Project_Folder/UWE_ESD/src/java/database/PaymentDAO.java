package database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import user.User;
import user.Payments;
/**
 *
 * @author Vincent
 */
public class PaymentDAO extends JDBC{
    //updates Payment table with new payment made
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
                result = true;
            }else{
                conn.close();
                throw new SQLException("Commit Failed");
            }
            
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return result;
    }//end updatePayment
    //query list of all payment records
    public List getAllRecords(){
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM payments;";
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                list.add(new Payments(rs.getString("mem_id"),rs.getString("type_of_payment"),rs.getString("amount"),rs.getString("date")));
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return list;
    }//end getAllRecords
    //query payment records of specific id
    public List getRecordsById(User user){
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM payments WHERE mem_id='"+ user.getID() +"';";
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                list.add(new Payments(rs.getString("mem_id"),rs.getString("type_of_payment"),rs.getString("amount"),rs.getString("date")));
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred (getRecordsById). " + se.getMessage());
        }
        return list;
    }//end getRecordsById
    //charge payment to list of uses from list
    public void chargePayment(List list,double amount)throws SQLException{
        conn = getConnection();
        
        stmt = conn.createStatement();
        conn.setAutoCommit(false);
        
        for(int i = 0; i < list.size(); i++){
            User user = (User)list.get(i);
            if(!user.isUserValid().equals("DELETED")){
                stmt.addBatch("UPDATE members SET balance=balance-"+ amount +
                            " WHERE id='"+ user.getID() + "';");
            }
        }
            
        int[] updateResult = stmt.executeBatch();
            
        if(checkResult(updateResult)){
            conn.commit();
        }else{
            conn.close();
            throw new SQLException("Commit Failed");
        }
    } //end chargePayment
    //check if all sql statement is successfull
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }//end checkResult 
    
}//end PaymentDAO

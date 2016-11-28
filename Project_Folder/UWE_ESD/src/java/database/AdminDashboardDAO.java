package database;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Vincent
 */
public class AdminDashboardDAO extends JDBC{
    public void findByStatus(String status){
        String sql = "SELECT * FROM users where status='"+ status +"';";
        
        conn = getConnection();
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                //code here. put into a list
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred.");
        }
    }//end findByStatus
}

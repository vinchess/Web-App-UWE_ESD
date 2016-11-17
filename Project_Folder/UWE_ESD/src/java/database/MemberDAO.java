package database;

import java.sql.*;
import java.util.*;
import user.User;

/**
 *
 * @author Vincent
 */
public class MemberDAO extends JDBC{
    public List getAllRecords(){
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM users;";
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                /*list.add(new User(
                        rs.getString("mem_id"),
                        rs.getString("type_of_payment"),
                        rs.getString("amount"),
                        rs.getString("date")));*/
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return list;
    }//end getAllRecords
}

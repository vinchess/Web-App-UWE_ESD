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
        String sql = "SELECT * FROM members;";
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                list.add(new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getString("address"),
                        rs.getString("dob"),
                        rs.getString("dor"),
                        Double.parseDouble(rs.getString("balance"))));
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return list;
    }//end getAllRecords
}

package database;
import user.Claim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import user.User;
/**
 *
 * 
 * @author Jayson
 */
public class ClaimDAO extends JDBC {
    
    public List getAllClaims() throws SQLException{
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM claims";
        
        stmt=conn.createStatement();

        //execute query 
        ResultSet rs=stmt.executeQuery(sql);

        //loop through the result set to get data 
        while(rs.next()){
            //get all data fields in Claims table
            list.add(new Claim(
                    Integer.parseInt(rs.getString("id")),
                    rs.getString("mem_id"),
                    rs.getString("date"),
                    rs.getString("rationale"),
                    rs.getString("status"),
                    Double.parseDouble(rs.getString("amount"))));
        }
        rs.close();
        conn.close();

        return list;
    } 
    public List getClaimsById(User user)throws SQLException{
        conn = getConnection();
        String sql = "SELECT * FROM Claims WHERE mem_id='"+ user.getID() +"';";
        List list = new ArrayList();

        stmt=conn.createStatement();

        //execute query
        ResultSet rs=stmt.executeQuery(sql);

        //loop through the result set to get data 
        while(rs.next()){
            //get all data fields in Claims table
            list.add(new Claim(
                    Integer.parseInt(rs.getString("id")),
                    rs.getString("mem_id"),
                    rs.getString("date"),
                    rs.getString("rationale"),
                    rs.getString("status"),
                    Double.parseDouble(rs.getString("amount"))));
        }
        rs.close();
        conn.close();
            
        return list;
    }   
    public boolean add_claim(String userid, Double claimAmount, String claimRational){
        try{
            //get connection
            conn=getConnection();
            stmt=conn.createStatement();

            //insert into the fields
            String sql = "INSERT INTO Claims(mem_id,date,rationale,status,amount) VALUES('" + userid + "',now(),'" + claimRational + "','SUBMITTED'," + claimAmount + ");";

            //execute the insert
            int i = stmt.executeUpdate(sql);
            
            //return true when insert is successful
            if(i==1)return true;
            
        }catch(SQLException ex){
            System.out.println("SQL error occurred. " + ex.getMessage());
        }
        //else return false if unsuccessfull
        return false;
    }
    public void updateClaim(int id, String status){
        conn = getConnection();
        String sql = "UPDATE claims SET status='" + status + "' WHERE id="+ id + ";";
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
        }catch(SQLException se){
            System.out.println("OPS");
        }
    }
}

                       
                                            
                      
         
            
               

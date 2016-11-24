package database;

import java.sql.*;
import java.util.*;
import user.User;

/**
 *
 * @author Vincent
 */
public class MemberDAO<E> extends JDBC{
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
    public Object getSingleById(String id){
        User user = null;
        conn = getConnection();
        String sql = "SELECT * FROM members WHERE id='" + id + "';";
        
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
                user = new User(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getString("address"),
                        rs.getString("dob"),
                        rs.getString("dor"),
                        Double.parseDouble(rs.getString("balance")));
            
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return user;
    }//end getSingleById
    public List getRecordsById(String id){
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM members WHERE id='" + id + "';";
        
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
    }//end getRecordsById
    public List getRecordsByStatus(String status){
        List list = new ArrayList();
        conn = getConnection();
        String sql = "SELECT * FROM members WHERE status='" + status + "';";
        
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
    }//end getRecordsByStatus
    public String getColumn(String id,String column){
        conn = getConnection();
        String sql = "SELECT " + column + " FROM members WHERE id='" + id + "';";
        String result = "";
        try{
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            
            while(rs.next()){
                result = rs.getString(column);
            }
            rs.close();
            conn.close();
        }catch(SQLException se){
            System.out.println("SQL error occurred. " + se.getMessage());
        }
        return result;
    }//end getColumn
    public void updateStatus(String user, String status){
        conn = getConnection();
        String sql = "UPDATE members SET status='" + status + "' WHERE id='"+ user + "';";
        try{
            stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            conn.close();
        }catch(SQLException se){
            System.out.println("OPS");
        }
    }//end updateStatus
    
    public String deleteUser(String id)throws SQLException{
        String result = "";
        conn = getConnection();
        
        stmt = conn.createStatement();
        
        conn.setAutoCommit(false);

        stmt.addBatch("DELETE FROM users WHERE id='" + id + "';");
        stmt.addBatch("UPDATE members SET status='DELETED' WHERE id='" + id + "';");

        int[] updateResult = stmt.executeBatch();

        if(checkResult(updateResult)){
            conn.commit();
            result = "User " + id + " has been deleted.";
        }else{
            conn.close();
            throw new SQLException("Commit Failed");
        }
        conn.close();
        return result;
    }//end deleteUser
    
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }//end checkResult 
}

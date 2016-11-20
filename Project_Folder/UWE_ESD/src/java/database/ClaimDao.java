/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;
import user.Claim;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import user.User;
/**
 *
 * @author Jayson
 */
public class ClaimDao extends JDBC {
   Connection conn=null;
   Statement stmt=null;
   
   String sql = "";
  
   
    public void getAllClaims(){
        
        sql = "SELECT * FROM Claims";
        List list = new ArrayList();

        try{
            //get connection
            conn=getConnection();
            stmt=conn.createStatement();
            
            //execute query 
            ResultSet rs=stmt.executeQuery(sql);
            
            //loop through the result set to get data 
            while(rs.next()){
                //get all data fields in Claims table
                list.add(new Claim(rs.getString("mem_id"),rs.getString("date"),rs.getString("rationale"),rs.getString("status"),rs.getString("amount")));
            }
            rs.close();
            conn.close();

        }catch(SQLException ex){
            System.out.println("SQL error occurred. " + ex.getMessage());
        }
    } 
    
    
    public List getClaimsById(User user){
        
        sql = "SELECT * FROM Claims WHERE mem_id='"+ user.getID() +"';";
        List list = new ArrayList();

        try{
            //get connection
            conn=getConnection();
            stmt=conn.createStatement();
            
            //execute query
            ResultSet rs=stmt.executeQuery(sql);
            
            //loop through the result set to get data 
            while(rs.next()){
                //get all data fields in Claims table
                list.add(new Claim(rs.getString("mem_id"),rs.getString("date"),rs.getString("rationale"),rs.getString("status"),rs.getString("amount")));
            }
            rs.close();
            conn.close();

        }catch(SQLException ex){
            System.out.println("SQL error occurred. " + ex.getMessage());
        }
        return list;
    }   
    
    
    public boolean add_claim(String userid, Double claimAmount, String claimRational){
        try{
            //get connection
            conn=getConnection();
            stmt=conn.createStatement();

            //insert into the fields
            sql = "INSERT INTO Claims(mem_id,date,rationale,status,amount) VALUES('" + userid + "',now(),'" + claimRational + "','SUBMITTED'," + claimAmount + ");";

            //execute the insert
            stmt.executeUpdate(sql);
            
            //return true when insert is successful
            return true;
            
        }catch(SQLException ex){
            System.out.println("SQL error occurred. " + ex.getMessage());
        }
        //else return false if unsuccessfull
        return false;
    }
}

                       
                                            
                      
         
            
               

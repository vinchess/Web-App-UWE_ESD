/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package database;
import user.Claim;
import database.JDBC;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author Jayson
 */
public class ClaimDao extends JDBC {
   Connection conn=null;
   Statement stmt=null;
   
   boolean result= false;
   int totalRows = 0;
   String sql;
   Claim tempClaim = new Claim();
   
   
    public void getAllClaims(){
        
        sql = "SELECT * FROM Members";
        List list = new ArrayList();

        try{
            conn=getConnection();
            stmt=conn.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            
            //loop through the result set to get data 
            while(rs.next()){
                list.add(new Claim(rs.getString("mem_id"),rs.getString("date"),rs.getString("rationale"),rs.getString("status"),rs.getString("amount")));
            }
            rs.close();
            conn.close();

        }catch(SQLException ex){
            System.out.println("SQL error occurred. " + ex.getMessage());
        }
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
    
//public void approve_payment(String status, double balance,int max_claim){
//    Claim claim = new Claim();
//               try{
//                Connection conn=null;
//                Statement stmt=null;
//                conn=getConnection();
//                stmt=conn.createStatement();
//                String sql="UPDATE Members"+"SET status = SUBMITTED";
//                stmt.executeUpdate(sql);
//                sql="SELECT status,balance FROM Members";
//                ResultSet rs=stmt.executeQuery(sql);
//                    while(rs.next()){
//                      String status_check=rs.getString("status");
//                      Double actual_balance=rs.getDouble("balance");
//                    
//                            if(status_check.contains("APPROVED")){
//                                remaining_balance =actual_balance-amount;
//                                sql= "INSERT INTO Members VALUES(?,?,?,?,?,?,?)";
//                                PreparedStatement pstmt = conn.prepareStatement(sql);
//                                pstmt.setDouble(7,remaining_balance);
//                                pstmt.executeUpdate();
//                                
//                            }
//                             claim.setMax_claim(+1);
//                            if(claim.getMax_claim()==2){
//                                System.out.println("Claim unsuccessful");
//                                      
//                            }
//                        else
//                           System.out.println("rejected");
//                    }
//                    
//                    conn.close();
//                    }catch(Exception ex){
//            } 
//           
//    }
}

                       
                                            
                      
         
            
               

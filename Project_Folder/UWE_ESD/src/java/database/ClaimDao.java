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
import java.util.Date;
/**
 *
 * @author Jayson
 */
public class ClaimDao extends JDBC {
   Connection conn=null;
   Statement stmt=null;
        public void claim_status(String dor,String status,double balance){
            boolean result=false;
            DateFormat df= new SimpleDateFormat("yyy-mm-dd");
            String current_date= df.format(new Date());
            String sql;
            Claim claim= new Claim();
            
            try{
                conn=getConnection();
                stmt=conn.createStatement();
                sql="SELECT dor,status,balance FROM Members";
                ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next()){
                 
                        claim=new Claim(
                        rs.getString("dor"),
                        rs.getString("status"),
                        Double.parseDouble(rs.getString("balance")));          
                     }
                    rs.close();
                    conn.close();
                    String rd=claim.getDor();
                    Date currentdate=df.parse(current_date);
                    Date registerdate=df.parse(rd);
                    int current=currentdate.getYear()*12+currentdate.getMonth();
                                int register=registerdate.getYear()*12+registerdate.getMonth();
                                    if((current-register)<6){
                                        System.out.println("Not capable to perform claim");
                                        
                                   
                                    }
                                    else 
                                      System.out.println("Valid to perform claim"); 
                    
            }catch(SQLException ex){
             }
            catch(Exception ex){
              System.out.println("invalid date");
            } 
        }               
public void approve_payment(String status, double balance,int max_claim){
    Claim claim = new Claim();
               try{
                conn=getConnection();
                stmt=conn.createStatement();
                String sql="UPDATE Members"+"SET status = SUBMITTED";
                stmt.executeUpdate(sql);
                sql="SELECT status,balance FROM Members";
                ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next()){
                        String status_check=rs.getString("status");
                        Double remaining_balance=rs.getDouble("balance");
                    }
                    rs.close();
                    
                            if(status.contains("APPROVED")){
                                double remain_balance =balance;
                                sql= "INSERT INTO Members VALUES(?,?,?,?,?,?,?)";
                                PreparedStatement pstmt = conn.prepareStatement(sql);
                                pstmt.setDouble(7,remain_balance);
                                pstmt.executeUpdate();
                                
                            }
                             claim.setMax_claim(+1);
                            if(claim.getMax_claim()==2){
                                System.out.println("Claim unsuccessful");
                                      
                            }
                        else
                           System.out.println("rejected");
                           
                    conn.close();
                    }catch(Exception ex){
            } 
               
}
}

                       
                                            
                      
         
            
               

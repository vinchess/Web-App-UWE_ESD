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
        public void claim_status(String dor,String status,double balance){
            Connection conn=null;
            Statement stmt=null;
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
public void approve_payment(double amount){
  Claim claim= new Claim();
  Double remaining_balance;
               try{
                Connection conn=null;
                Statement stmt=null;
                conn=getConnection();
                stmt=conn.createStatement();
                String sql="UPDATE Members"+"SET status = SUBMITTED";
                stmt.executeUpdate(sql);
                sql="SELECT status,balance FROM Members";
                ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next()){
                      String status_check=rs.getString("status");
                      Double actual_balance=rs.getDouble("balance");
                    
                            if(status_check.contains("APPROVED")){
                                remaining_balance =actual_balance-amount;
                                sql= "INSERT INTO Members VALUES(?,?,?,?,?,?,?)";
                                PreparedStatement pstmt = conn.prepareStatement(sql);
                                pstmt.setDouble(7,remaining_balance);
                                pstmt.executeUpdate();
                                
                            }
                             claim.setMax_claim(+1);
                            if(claim.getMax_claim()==2){
                                System.out.println("Claim unsuccessful");
                                      
                            }
                        else
                           System.out.println("rejected");
                    }
                    
                    conn.close();
                    }catch(Exception ex){
            } 
           
    }
}

                       
                                            
                      
         
            
               

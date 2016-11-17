/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package user;
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
public class ClaimDAO extends JDBC {
   private static int max_claim=0;
        public void claim_status(){
            Connection conn=null;
            Statement stmt=null;
            DateFormat df= new SimpleDateFormat("yyy-mm-dd");
            String current_date= df.format(new Date());
            String sql;
            
            try{
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql");
                stmt=conn.createStatement();
                sql="SELECT dor FROM Members";
                ResultSet rs=stmt.executeQuery(sql);
                    while(rs.next()){
                        String dor=rs.getString("dor");
                       // String status=rs.getString("status");
                       //double balance = rs.getDouble("balance");
                            try{
                                Date currentdate=df.parse(current_date);
                                Date registerdate=df.parse(dor);
                                int current=currentdate.getYear()*12+currentdate.getMonth();
                                int register=registerdate.getYear()*12+registerdate.getMonth();
                                    if((current-register)<6){
                                        System.out.println("Not capable to perform claim");
                                        System.exit(0);
                                    }
                                    if((current-register)>6){
                                        System.out.print("Capable to perform claim");
                                     }
                            }
                    
                            catch(Exception ex){
                                 System.out.println("invalid date");
                    
                            } 
                            finally{
                                try{
                                    if((stmt!=null)&&(conn!=null)){
                                        conn.close();
                                    }
                                }
                                catch(SQLException ex){
                                }
                             }
                    }    
                Scanner scanner = new Scanner(System.in);
                Double input_amount=scanner.nextDouble();
                System.out.println("Submiting & Update Status...");
                sql="UPDATE Members"+"SET status = SUBMITTED";
                stmt.executeUpdate(sql);
                sql="SELECT status,balance FROM Members";
                ResultSet rs1=stmt.executeQuery(sql);
                    while(rs1.next()){
                        String status=rs1.getString("status");
                        Double balance=rs1.getDouble("balance");
                            if(status.contains("APPROVED")){
                                double remain_balance =balance-input_amount;
                                sql= "INSERT INTO Members VALUES(?,?,?,?,?,?,?)";
                                PreparedStatement pstmt = conn.prepareStatement(sql);
                                pstmt.setDouble(7,remain_balance);
                                pstmt.executeUpdate();
                            }
                        max_claim =+1 ;
                            if(max_claim>2){
                                System.out.println("Claim unsuccessful");
                                     break;
                            }
                        if(status.contains("REJECTED"))
                           System.out.println("Rejected");
                                            
                    }
                    
            }                     
            catch(SQLException ex){
            }     
            catch(Exception e){
                System.out.print("An error has occured");
            }
            finally{
                                try{
                                    if((stmt!=null)&&(conn!=null)){
                                        conn.close();
                                    }
                                }
                                catch(SQLException ex){
                                }
                             }
 }              
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.*;

/**
 *
 * @author Vincent
 */
public class testDB
{
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    //static final String USER = "root";
    static final String PASS = "";

    
    
    public static void main(String args[]){
         Connection conn = null;
         Statement stmt = null;
         String USER = "root";
         String database = "xyz_assoc";
        String sql = "SELECT * FROM users WHERE id='vince';";
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("password"));
            }
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if(stmt!=null)
                    conn.close();
            }catch(SQLException se){}// do nothing
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        
        
    }
}

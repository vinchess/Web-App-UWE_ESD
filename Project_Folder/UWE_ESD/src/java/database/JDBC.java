package database;
import java.sql.*;
import java.util.*;
/**
 *
 * @author Vincent
 */
public class JDBC{
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/";

    static final String USER = "root";
    static final String PASS = "";
    final String database = "xyz_assoc";

    public Connection conn = null;
    public Statement stmt = null;
    

    public JDBC(){}
    public Connection getConnection(){
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            
        }catch(SQLException se){
            System.out.println("SQL Connection Error...");
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
    /*
    public void create(String sql){
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            stmt = conn.createStatement();

            int update = stmt.executeUpdate(sql);

        }catch(SQLException se){
            System.out.println("SQL Connection Error...");
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
                System.out.println("Failed to close connection...");
            }//end finally try
        }
    }
    public ResultSet read(String sql){
        ResultSet rs = null;
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);
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
        return rs;
    }
    public void update(String sql){
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            stmt = conn.createStatement();

            int update = stmt.executeUpdate(sql);

        }catch(SQLException se){
            System.out.println("SQL Connection Error...");
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
                System.out.println("Failed to close connection...");
            }//end finally try
        }
    }
    public void delete(String sql){
        try{
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL + database, USER, PASS);
            stmt = conn.createStatement();

            int update = stmt.executeUpdate(sql);

        }catch(SQLException se){
            System.out.println("SQL Connection Error...");
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
                System.out.println("Failed to close connection...");
            }//end finally try
        }
    }
    */
}
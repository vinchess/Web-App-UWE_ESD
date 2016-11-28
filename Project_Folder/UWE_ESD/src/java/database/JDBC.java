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
            System.out.println("JDBC Error...");
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
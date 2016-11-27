package database;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import user.Registration;
import user.User;

/**
 *
 * @author Vincent
 */
public class MemberDAO extends JDBC{
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
            System.out.println("SQL error occurred (getAllRecords). " + se.getMessage());
        }
        return list;
    }//end getAllRecords
    public User getSingleById(String id){
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
            System.out.println("SQL error occurred (getSingleById). " + se.getMessage());
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
            System.out.println("SQL Error.");
        }
    }//end updateStatus
    public String editDetails(User user)throws SQLException,IOException{
        String result = "";
        String sql = "UPDATE members SET " + 
                     "name='" + user.getFirstName() + " " + user.getLastName() + "'," + 
                     "address='"+ user.getAddress() +"'," +
                     "dob='"+ user.getDOB() +"'" +
                     "WHERE id='"+ user.getID() + "';";
        conn = getConnection();
        
        boolean geoCheck = geolocationCheck(user);
        //GeoLocation check 
        if(!geoCheck) //if address is invalid 
        {
            conn.close(); //close DB connection
            throw new IOException("Address entered invalid");   //throw IOException to catch 
        }
        stmt = conn.createStatement();  //create SQL statement

        stmt.executeUpdate(sql);

        conn.close(); //close connection
        
        result = "User details successfully updated.";
        
        return result;
    }   
    
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
    
    public boolean geolocationCheck(User user){
        try {
            String userAddress = user.getAddress(); //get user address from User class
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/xml?address=" + URLEncoder.encode(userAddress, "UTF-8") + "&key=AIzaSyDA2g2qxIuSTRXP69avdduFwJocW5zozQE");
            String out = new Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next();
             
             Pattern pattern = Pattern.compile("<status>(\\w+)</status>"); //compile Regex pattern 
             Matcher match = pattern.matcher(out); //execute Regex match
             
             while (match.find()) {     //loop through results
                String find = match.group(1);
                if (find.equals("OK")){ //if found <status>OK</status>
                    return true;
                }// end IF
            }//end WHILE
        } catch (Exception ex) {
            System.out.println("URL CHECK ERROR");
        } //END TRY
        return false; //if address is invalid 
    }
}

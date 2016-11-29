package database;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import user.Registration;

/**
 *
 * @author N
 */
public class RegistrationDAO extends JDBC{
    
    //method to check if user already exists
    public boolean checkExist(String id) throws SQLException{
        boolean result = false;
        conn = getConnection();
        String sql = "SELECT * FROM users where id='"+ id +"';";
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while(rs.next()){
            if(rs.getString("id").equals(id)){
                result = true;
            }
        }
        return result;
    } //end checkExist
    //method to register new user into database
    public String RegisterUser(Registration newUser) throws SQLException, IOException{
        String result = "false";
        conn = getConnection();
        
        boolean geoCheck = geolocationCheck(newUser);
        //GeoLocation check 
        if(!geoCheck) //if address is invalid 
        {
            conn.close(); //close DB connection
            throw new IOException("Address entered invalid");   //throw IOException to catch 
        }
        stmt = conn.createStatement();  //create SQL statement
        
        conn.setAutoCommit(false); // do not commmit SQL statement immediately 

        stmt.addBatch("INSERT INTO users VALUES('" + newUser.getID() + "','" + 
                newUser.getPassword() + "','APPLIED');");
        stmt.addBatch("INSERT INTO members VALUES('" + newUser.getID() + "','" + 
                newUser.getName()+ "','" + newUser.getAddress() + "','" + newUser.getDOB() + "',now(),'APPLIED','-10.00');");

        int[] updateResult = stmt.executeBatch();

        if(checkResult(updateResult)){
            conn.commit();
            result = "Successfully registered. Username : " + newUser.getID() + " Password : " + newUser.getPassword() + ".Proceed to login.";
        }else{
            conn.close();
            throw new SQLException("Commit Failed");
        }
            
        conn.close(); //close connection

        return result;
    } //end RegisterUser
    //method to check all batch statement to be executed for SQL purposes
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }//end checkResult 
    //Web Services implementation for location verification
    public boolean geolocationCheck(Registration newUser){
        try {
            String userAddress = newUser.getAddress(); //get user address from User class
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
    } //end geolocationCheck
}

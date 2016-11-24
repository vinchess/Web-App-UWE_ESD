package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import user.Registration;

/**
 *
 * @author N
 */
public class RegistrationDAO extends JDBC{
    
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
    }
    
    public String RegisterUser(Registration newUser) throws SQLException, IOException{
        String result = "false";
        conn = getConnection();
        
        boolean geoCheck = geolocationCheck(newUser);
        //GeoLocation check 
        if(!geoCheck)
        {
            conn.close();
            throw new IOException("Address entered invalid");
        }
        stmt = conn.createStatement();
        
        conn.setAutoCommit(false);

        stmt.addBatch("INSERT INTO users VALUES('" + newUser.getID() + "','" + 
                newUser.getPassword() + "','APPLIED');");
        stmt.addBatch("INSERT INTO members VALUES('" + newUser.getID() + "','" + 
                newUser.getName()+ "','" + newUser.getAddress() + "','" + newUser.getDOB() + "',now(),'APPLIED','0.00');");

        int[] updateResult = stmt.executeBatch();

        if(checkResult(updateResult)){
            conn.commit();
            result = "User successfully registered. Proceed to login.";
        }else{
            conn.close();
            throw new SQLException("Commit Failed");
        }
            
        conn.close();

        return result;
    }
    private boolean checkResult(int[] updateResults){
        for(int i : updateResults) if(i!=1) return false;
        return true;
    }//end checkResult 
    
    public boolean geolocationCheck(Registration newUser){
        String url;
        try {
            url = "https://maps.googleapis.com/maps/api/geocode/xml?address=" + newUser.getAddress() + "&key=AIzaSyDA2g2qxIuSTRXP69avdduFwJocW5zozQE";
             
            String content = getText(url);
             
             Pattern pattern = Pattern.compile("<status>(\\w+)</status>");
             Matcher match = pattern.matcher(content);
             
             while (match.find()) {
                String find = match.group(1);
                if (find.equals("OK")){
                    return true;
                }
            }

        } catch (Exception ex) {
            System.out.println("URL CHECK ERROR");
        }
        return false;
    }
    
    public static String getText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null) 
            response.append(inputLine);

        in.close();

        return response.toString();
    }

}

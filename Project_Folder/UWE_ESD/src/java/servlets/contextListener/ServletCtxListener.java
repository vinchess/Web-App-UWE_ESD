/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.contextListener;

import javax.servlet.*;
import database.*;

/**
 *
 * @author Vincent
 */
public class ServletCtxListener implements ServletContextListener{

    @Override
    public void contextInitialized(ServletContextEvent event){
        ServletContext sc = event.getServletContext();
        String dbname = sc.getInitParameter("dbname");
        String dbuser = sc.getInitParameter("dbuser");
        JDBC jdbc = new JDBC();
        sc.setAttribute("dbConn", jdbc);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
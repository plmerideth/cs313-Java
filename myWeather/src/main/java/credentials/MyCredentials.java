/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package credentials;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mysql.DbConnect;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(name = "MyCredentials", urlPatterns = {"/MyCredentials"})
public class MyCredentials extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyCredentials</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyCredentials at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        Boolean completeForm = true;
        
        //Process login
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username.isEmpty() || password.isEmpty())
        {
            request.getRequestDispatcher("incompleteLogin.jsp").forward(request, response);
            completeForm = false;
        }

        if(completeForm)
        {
            try
            {
                try
                {
                    //Register mysql driver
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException ex)
                {
                    System.out.println("Error: unable to load driver class!");
                    System.exit(1);
                }

                // Select all the records and display them.
                String sql = "SELECT acct_id, first_name, last_name, email, username, password, datetime FROM myweatheraccount WHERE username = ?";

                //Code for Local
                //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs313", "admin", "loga2872");
                //End code for Local
                
                //Code for OpenShift
                String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");        
                String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                String OpenShiftUsername = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                String OpenShiftPassword = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cs313", OpenShiftUsername, OpenShiftPassword);                                                
                //End code for OpenShift
                
                PreparedStatement pstmt = connection.prepareStatement(sql);

                BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
                //String encryptedPassword = passwordEncryptor.encryptPassword(password);

                /*
                if (passwordEncryptor.checkPassword(password, encryptedPassword)) {
                    System.out.println("Password is good");    
                    // correct!
                } else {
                    System.out.println("Password failed");
                    // bad login!
                }                                                               
                */
                
                pstmt.setString(1, username);

                //STEP 5: Extract data from result set
                Boolean results = false;
                try (ResultSet rs = pstmt.executeQuery())
                {
                    //Extract data from result set
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();

                    while (rs.next())
                    {                                                
                        //Check password
                        String encryptedPassword = rs.getString("password");
                        if (passwordEncryptor.checkPassword(password, encryptedPassword))
                        {
                            results = true;
                        }    
                        //Retrieve by column name
                        /*
                        int param = rs.getInt("acct_id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        String fullName = first + " " + last;

                        //Display values
                        out.println("<p>Name: " + fullName + "</p>");
                        out.println("<p>UserName: " + rs.getString("username") + "</p>");
                        out.println("<p>Email: " + rs.getString("email") + "</p>");
                        out.println("<p>Password: " + rs.getString("password") + "</p>");
                        out.println("<p>DateTime: " + rs.getDate("datetime") + "</p>");
                        out.println("</br></br>");
                        */                        

                        if (!results) {
                            request.getRequestDispatcher("invalidLogin.jsp").forward(request, response);
                        }                        
                                                
                        //Successful login - do something here.                                                
                        request.getRequestDispatcher("successfulLogin.jsp").forward(request, response);
                        
                    }
                } catch (SQLException e) {
                    System.out.println("executeQuery failed");
                }
                pstmt.close();
            } catch (SQLException e)
            {
                System.out.println("Connection Failed");
                //Handle errors for JDBC            
            }
        }
    }                        

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

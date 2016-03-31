/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package registration;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jasypt.util.password.BasicPasswordEncryptor;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(name = "MyRegistration", urlPatterns = {"/MyRegistration"})
public class MyRegistration extends HttpServlet {

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
            out.println("<title>Servlet MyRegistration</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyRegistration at " + request.getContextPath() + "</h1>");
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

        System.out.println("Entered doGet() of MyRegistration Servlet");
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
        Boolean duplicate = false;
        Boolean completeForm = true;
        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        
        //Check to see if all fields are filled out
        //Check to see if username and password already exist
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String email = request.getParameter("email");        
        String username = request.getParameter("username");
        String password = request.getParameter("password");              
                
        if(firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || username.isEmpty() || password.isEmpty())
        {
            request.getRequestDispatcher("incompleteRegistration.jsp").forward(request, response);
            completeForm = false;
        }
        
        if(completeForm)
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

            String sql = "SELECT username, password FROM myweatheraccount WHERE password = ? AND username = ?";

            String encryptedPassword = passwordEncryptor.encryptPassword(password);            
            
            try
            {
                
                //Code for Local
                //Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs313","admin", "loga2872");
                //End code for Local
                
                //Code for OpenShift
                String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");        
                String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                String OpenShiftUsername = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                String OpenShiftPassword = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");

                Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cs313", OpenShiftUsername, OpenShiftPassword);                                                
                //End code for OpenShift
                                      
                
                PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, encryptedPassword);
                pstmt.setString(2, username);

                //STEP 5: Extract data from result set            
                try (ResultSet rs = pstmt.executeQuery())
                {
                    //Extract data from result set
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();

                    while (rs.next())
                    {
                        duplicate = true;
                    }

                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("executeQuery failed");
                }
            }catch(SQLException err)
            {
                System.out.println("Connection Failed");
            }

            if(!duplicate)
            {
                // Insert a new account.
                int year;

                //Get current date and time
                Date myDateTime = new Date();
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateTime = sdf.format(myDateTime);    

                sql = "INSERT INTO myweatheraccount (first_name, last_name, email, username, password, datetime) VALUES(?,?,?,?,?,?)";

                try
                {
                    
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

                    pstmt.setString(1, firstname);
                    pstmt.setString(2, lastname);
                    pstmt.setString(3, email);
                    pstmt.setString(4, username);
                    pstmt.setString(5, encryptedPassword);
                    pstmt.setString(6, currentDateTime);

                    //STEP 5: Write out to database
                    pstmt.executeUpdate();

                    request.getRequestDispatcher("successfulRegistration.jsp").forward(request, response);
                    
                    pstmt.close();
                } catch (SQLException err)
                {
                    System.out.println("Error = " + err);
                }
            }
            else //Duplicate record
            {
                System.out.println("Duplicate Record");
            }
        }
    }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }

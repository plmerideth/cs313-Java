/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import static javax.servlet.SessionTrackingMode.URL;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(urlPatterns = {"/mysqlConfig"})
public class mysqlConfig extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * @throws java.sql.SQLException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        String URL = "jdbc://@localhost:80:cs313";
        String USER = "admin";
        String PASS = "loga2872";
        Connection conn = (Connection) DriverManager.getConnection(URL, USER, PASS);
                
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet mysqlConfig</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet mysqlConfig at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException
    {
        try
        {
            processRequest(request, response);
        } catch (SQLException ex)
        {
            Logger.getLogger(mysqlConfig.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try
        {
            //Register mysql driver
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException ex)
        {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        /* Code for OpenShift Connection.  Currently broken.
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        */
        
	try
        (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs313","admin", "loga2872"))
//          (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cs313", username, password))  
        {                
            PreparedStatement pstmt;
            String sql;

            // Select all the records and display them.
            sql = "SELECT person_id, first_name, last_name, birthday FROM persons";
            
            pstmt = connection.prepareStatement(sql);
            
            //STEP 5: Extract data from result set
            try (ResultSet rs = pstmt.executeQuery(sql))
            {
                //Extract data from result set
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                
                while (rs.next())
                {
                    //Retrieve by column name
                    byte param = rs.getByte("person_id");
                    String first = rs.getString("first_name");
                    String last = rs.getString("last_name");
                    Date birthday = rs.getDate("birthday");
                    
                    String fullName = first + " " + last;                    
                    
                    //Display values
                    out.println("Name: " + "<a href=readNames?p=" + param + ">" + fullName + "</a>" );                   
                    out.println("<p>Birthday: " + birthday + "</p>");
                    out.println("</br></br>");
                }
                //STEP 6: Clean-up environment
            }
            pstmt.close();
        }catch(SQLException se)
        {
            //Handle errors for JDBC            
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

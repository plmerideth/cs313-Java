/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(urlPatterns = {"/readNames"})
public class readNames extends HttpServlet {

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
            out.println("<title>Servlet readNames</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet readNames at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();

        String param = request.getParameter("p");
        int p = Integer.parseInt(param);
        
        /* Code for OpenShift Connection.  Currently broken.
        String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
        String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
        String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
        */        

        String host = null;
        host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
        
        if(host != null)
        {
            String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
            String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
            String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
            
            try            
            (Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/cs313", username, password))  
            {                
                
                PreparedStatement pstmt;
                String sql;

                // Select all the records and display them.
                sql = "SELECT person_id, first_name, last_name, birthday FROM persons WHERE person_id=?";            

                pstmt = connection.prepareStatement(sql);
                //pstmt.setInt(1, 2);
                pstmt.setInt(1, p);

                //STEP 5: Extract data from result set
                try (ResultSet rs = pstmt.executeQuery())
                {
                    //Extract data from result set

                    while (rs.next())
                    {
                        //Retrieve by column name
                        byte person_id = rs.getByte("person_id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        Date birthday = rs.getDate("birthday");

                        String fullName = first + " " + last;                    

                        //Display values
                        out.println("Name: " + fullName + "</a>" );                   
                        out.println("<p>Birthday: " + birthday + "</p>");
                        out.println("</br></br>");
                    }                    
                }
                
                //STEP 6: Clean-up environment
                pstmt.close();
            }catch(SQLException se)
            {
                //Handle errors for JDBC            
            }
        }
        else
        {                
            try
            (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs313","admin", "loga2872"))
            {

                PreparedStatement pstmt;
                String sql;

                // Select all the records and display them.
                sql = "SELECT person_id, first_name, last_name, birthday FROM persons WHERE person_id=?";            

                pstmt = connection.prepareStatement(sql);
                //pstmt.setInt(1, 2);
                pstmt.setInt(1, p);

                //STEP 5: Extract data from result set
                try (ResultSet rs = pstmt.executeQuery())
                {
                    //Extract data from result set

                    while (rs.next())
                    {
                        //Retrieve by column name
                        byte person_id = rs.getByte("person_id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        Date birthday = rs.getDate("birthday");

                        String fullName = first + " " + last;                    

                        //Display values
                        out.println("Name: " + fullName + "</a>" );                   
                        out.println("<p>Birthday: " + birthday + "</p>");
                        out.println("</br></br>");
                    }                    
                }
                
                //STEP 6: Clean-up environment
                pstmt.close();
            }catch(SQLException se)
            {
                //Handle errors for JDBC            
            }
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
        //Write html to browser window using out object
        response.setContentType("text/html;charset=UTF-8");        
        PrintWriter out = response.getWriter();

        out.println("<h1>You are in doPost() of readNames.java</h1>");
                
        //processRequest(request, response);
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

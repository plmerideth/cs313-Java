/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package addPosts;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(name = "addUserPost", urlPatterns = {"/addUserPost"})
public class addUserPost extends HttpServlet {

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
        try (PrintWriter out = response.getWriter())
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addUserPost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addUserPost at " + request.getContextPath() + "</h1>");
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
        //Create session to have access to username
        HttpSession session = request.getSession(true);
        
        String userComment = request.getParameter("comment");       
        String months[] =
        {
            "Jan", "Feb", "Mar", "Apr",
            "May", "Jun", "Jul", "Aug",
            "Sep", "Oct", "Nov", "Dec"
        };
        int year;
        
        //Get current date and time
        Date myDateTime = new Date();
        SimpleDateFormat sdf;
        sdf = new SimpleDateFormat("E MMM dd yyyy hh:mm a zzz");
        String currentDateTime = sdf.format(myDateTime);
        
        /*
        String currentDateTime = months[gCalendar.get(Calendar.MONTH)] + " ";
        currentDateTime += gCalendar.get(Calendar.DATE) + ", ";
        currentDateTime += gCalendar.get(Calendar.YEAR) + ", ";
        currentDateTime += gCalendar.get(Calendar.HOUR_OF_DAY) + ":";
        currentDateTime += gCalendar.get(Calendar.MINUTE) + ":";
        */
                
        Boolean existingComment = false; //flag=true when comments already in comments.txt file
        String commentArr[] = null;
        
        if (new File("comments.txt").exists()) //Read contents
        {
            existingComment = true;
            
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("comments.txt"));
            
            String str;

            List<String> list = new ArrayList<String>();
            while((str = br.readLine()) != null)
            {
                list.add(str);
            }
                        
            br.close();
            
            commentArr = list.toArray(new String[0]);

            System.out.println("commentArray = ");
        }
        
        try
        {
            //Get username
            String userName = (String) session.getAttribute("username");

            //Don't append
            BufferedWriter writer = new BufferedWriter(new FileWriter("comments.txt", false));               

            // print to console while debugging
            //BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
            writer.write(userName);
            writer.write("<%>");  //Delimter
            writer.newLine();
            writer.write(currentDateTime);
            writer.write("<%>");
            writer.newLine();
            writer.write(userComment);
            writer.write("<%>");            
            writer.close();
            
            if(existingComment)
            {
                //Append to end of file
                writer = new BufferedWriter(new FileWriter("comments.txt", true));               
                
                int size = commentArr.length;
                for(int i=0; i<size; i++)
                {
                    writer.newLine();
                    writer.write(commentArr[i]);                    
                }
                writer.close();
            }
            
            /*
            String usersComment = "paul<%>3-6-2016<%>paul made these comments!<%>";
            String[] words = usersComment.split("<%>");
            for (String word: words)
            {
                writer.write(word);
                writer.newLine();
            } 
            */
        } catch(IOException e)
        {
            System.out.println("An I/O Error Occured: " + e);
        }                    

/*        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter())
        { */
            /* TODO output your page here. You may use following sample code.
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addUserPost</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>User Comments: " + userComment + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }        
        System.out.println(userComment);
  */      
        request.getRequestDispatcher("/displayPosts").forward(request, response);
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

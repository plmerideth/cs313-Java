/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package postDisplay;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
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
@WebServlet(name = "displayPosts", urlPatterns = {"/displayPosts"})
public class displayPosts extends HttpServlet {

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
            throws ServletException, IOException
    {
        //Create session to have access to username
        HttpSession session = request.getSession(true);
                
        String commentArr[] = null;
        
        response.setContentType("text/html;charset=UTF-8");
        //try (PrintWriter out = response.getWriter())
        //{
PrintWriter out = response.getWriter();

        if (new File("comments.txt").exists()) //Read contents
        {    
            BufferedReader br = null;
            br = new BufferedReader(new FileReader("comments.txt"));
            
            String str;
            String finalStr = "";

            List<String> list = new ArrayList<String>();
            while((str = br.readLine()) != null)
            {
                while(true)
                {
                    if(str.contains("<%>"))
                    {
                        str = str.substring(0, str.length() - 3);
                        finalStr += str;                        
                        break;
                    }
                    else
                    {
                        finalStr += str;
                        finalStr += "\n";
                        str = br.readLine();
                    }
                }                
                list.add(finalStr);
                finalStr = "";
            }
                        
            br.close();
            
            commentArr = list.toArray(new String[0]);
                        
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet displayPosts</title>");
            out.println("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\"/>");
            out.println("</head>");
            out.println("<body>");
            
            out.println("<table>");
            out.println("<caption align=\"top\">");
            out.println("User Comments");
            out.println("</caption>");
            out.println("<colgroup>");
            out.println("<col class=\"UserName\"/>");
            out.println("<col class=\"DateTime\"/>");
            out.println("<col class=\"Comment\"/>");
            out.println("</colgroup>");
            out.println("<thead> <tr>");
            out.println("<th scope=\"col\">User Name</th>");
            out.println("<th scope=\"col\">Date-Time Posted</th>");
            out.println("<th scope=\"col\">Comment</th>");
            out.println("</tr>");
            out.println("</thead>");
            out.println("<tbody>");
            
            int size = commentArr.length;
            for(int i=0; i<size; i+=3)
            {
                out.println("<tr>");
                out.println("<td>");
                out.println(commentArr[i]);
                out.println("</td>");
                out.println("<td>");
                out.println(commentArr[i+1]);
                out.println("</td>");
                out.println("<td>");
                out.println(commentArr[i+2]);
                out.println("</td>");
                out.println("</tr>");
            }
            out.println("</tbody>");
            out.println("</table>");                                    
            
            out.println("<br/><br/>");
            out.println("<div class=\"addPost\">");
            out.println("<form action=\"addPost.jsp\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"Add Post\" />");
            out.println("</form>");
            out.println("</div>");
            
            out.println("<form action=\"logout\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"Log Out\" />");
            out.println("</form>");        
            
            out.println("</body>");
            out.println("</html>");
        }
        else
        {
        //} catch(IOException e)
        //{
            //response.setContentType("text/html;charset=UTF-8");
            // PrintWriter out = response.getWriter();            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet displayPosts</title>");
            out.println("<link href=\"style.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\"/>");
            out.println("</head>");
            out.println("<body>");            
            out.println("<h1>No comments to show</h1>");
            
            out.println("<form action=\"logout\" method=\"post\">");
            out.println("<input type=\"submit\" value=\"Click here to login again\" />");
            out.println("</form>");        
                        
            out.println("</body>");
            out.println("</html>");                       
        //}                                
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
            throws ServletException, IOException {
        processRequest(request, response);
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

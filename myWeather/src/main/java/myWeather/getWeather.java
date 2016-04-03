/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myWeather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Paul Merideth
 */
@WebServlet(name = "getWeather", urlPatterns = {"/getWeather"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class getWeather extends HttpServlet {

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
            out.println("<title>Servlet getWeather</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getWeather at " + request.getContextPath() + "</h1>");
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
        
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zipcode = request.getParameter("zipcode");
 
        String wUnderground_URL = "http://api.wunderground.com/api/8ecffc3b8a9e0135/conditions/q/";
        if(!zipcode.isEmpty())
        {
            wUnderground_URL += zipcode + ".json";
        }
        else
        {
            wUnderground_URL += state + "/" + city + ".json";
        }
        
        wUnderground_URL = wUnderground_URL.replace(' ', '_');
        
        URL url = new URL(wUnderground_URL);        
        //URL url = new URL("http://api.wunderground.com/api/8ecffc3b8a9e0135/conditions/q/84065.json");                
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        MyWeatherData myData = new MyWeatherData();

        try
        {
            myData = mapper.readValue(url, MyWeatherData.class);
        }catch (Exception e)
        {
            System.out.println("INVALID CITY");
        }
        
        if(myData.current_observation == null) //Bad city location
        {
            request.getRequestDispatcher("badLocation.jsp").forward(request, response);                                                                         
        }
        else
        {
            request.setAttribute("weather", myData);
            request.getRequestDispatcher("displayWeather.jsp").forward(request, response);                                                                         
        }        
        
        /*
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("Full City: " + myData.current_observation.display_location.full + "<br/>");                    
        out.println("Latitude: " + myData.current_observation.observation_location.latitude + "<br/>");
        out.println("Temperature: " + myData.current_observation.forecast_url);                
        //processRequest(request, response);
        */
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

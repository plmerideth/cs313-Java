/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Paul Merideth
 * 
 * Returns a Connection object
 */
public class DbConnect
{    

    /**
     *
     */
    static public Connection getConnection()
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
                sql = "SELECT person_id, first_name, last_name, birthday FROM persons";

                pstmt = connection.prepareStatement(sql);

                //STEP 5: Extract data from result set
                try (ResultSet rs = pstmt.executeQuery(sql))
                {
                    /*
                    //Extract data from result set
                    while (rs.next())
                    {
                        //Retrieve by column name
                        byte param = rs.getByte("person_id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        Date birthday = rs.getDate("birthday");

                        String fullName = first + " " + last;                    

                        //Display values
                        System.out.println("Name: " + param + " " + fullName);                   
                        System.out.println("Birthday: " + birthday);
                        System.out.println("\n\n");
                    }                                       
                    */
                    pstmt.close();
                    return connection;
                }
            }catch(SQLException e)
            {
                //Handle errors for JDBC            
            }           
        }
        else
        {
            try
            (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/cs313","admin", "loga2872"))
            {                
                //PreparedStatement pstmt;
                //String sql;

                //sql = "SELECT acct_id, first_name, last_name, email, username, password, datetime FROM myweatheraccount";
                
                // Select all the records and display them.
                //sql = "SELECT person_id, first_name, last_name, birthday FROM persons";

                //pstmt = connection.prepareStatement(sql);

                //STEP 5: Extract data from result set
                /*
                try (ResultSet rs = pstmt.executeQuery(sql))
                {
                    
                    //Extract data from result set
                    while (rs.next())
                    {
                        //Retrieve by column name
                        //byte param = rs.getByte("person_id");
                        String first = rs.getString("first_name");
                        String last = rs.getString("last_name");
                        Date datetime = rs.getDate("datetime");

                        String fullName = first + " " + last;                    

                        //Display values
                        System.out.println("Name: " + fullName);                   
                        System.out.println("DateTime: " + datetime);
                        System.out.println("\n\n");
                    }
                    
                    
                    pstmt.close();
                    */
                    return connection;
            }                
            catch(SQLException se)
            {
               //Handle errors for JDBC            
            }                            
        }
        return null;
    }
}

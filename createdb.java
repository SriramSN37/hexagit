import java.io.*;
import java.sql.*;
 
class createdb {
    public static void main(String[] args) throws Exception
    {
        String url
            = "jdbc:mysql://localhost:3306/covid_vaccination"; // table details
        String username = "user"; // MySQL credentials
        String password = "summa";
        String query
            = "select *from users"; // query to be run
        Class.forName(
            "com.mysql.cj.jdbc.Driver"); // Driver name
        Connection con = DriverManager.getConnection(
            url, username, password);
        System.out.println(
            "Connection Established successfully");
        Statement st = con.createStatement();
        ResultSet rs
            = st.executeQuery(query); // Execute query
        rs.next();
        String name
            = rs.getString("name"); // Retrieve name from db
 
        System.out.println(name); // Print result on console
        st.close(); // close statement
        con.close(); // close connection
        System.out.println("Connection Closed....");
    }
}
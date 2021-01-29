package sk.kosickaakademia.kolesarova.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private String url="jdbc:mysql://localhost:3306/world_x";
    private String username="root";
    private String password="";

    public void showCities(String country){
        String query="SELECT City.Name, CountryCode " +
                "FROM city " +
                "INNER JOIN country ON country.code=city.CountryCode "+
                "WHERE country.name LIKE ?";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            if(connection!=null){
                System.out.println("Success");
                PreparedStatement ps= connection.prepareStatement(query);
                ps.setString(1,country);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    String city= rs.getString("Name");
                    String code= rs.getString("CountryCode");
                    System.out.println(city+" "+code);
                }
                connection.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}

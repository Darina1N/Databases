package sk.kosickaakademia.kolesarova.mysql;

import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.Country;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String url="jdbc:mysql://localhost:3306/world_x";
    private String username="root";
    private String password="";

    public List<City> getCities(String country){//metoda mi len najde v DB informacie a pripravi ich ako zoznam pre metodu ktora ich vypise
        String query="SELECT City.Name, JSON_EXTRACT(Info,'$.Population') AS Population " +//ak je info vo formate json musim davat extract a potom cez '' ktorú položku
                "FROM city " +
                "INNER JOIN country ON country.code=city.CountryCode "+
                "WHERE country.name LIKE ? ORDER BY Population DESC";//chcem ich usporiadat vzostupne
        List<City> list=new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            if(connection!=null){
                //System.out.println("Success");
                PreparedStatement ps= connection.prepareStatement(query);
                ps.setString(1,country);
                ResultSet rs= ps.executeQuery();
                while(rs.next()){
                    String city= rs.getString("Name");
                    int popul=rs.getInt("Population");
                    City cities=new City(city,popul);
                    list.add(cities);
                }
                connection.close();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return(list);
    }


    public Country getCountryInfo(String country){
       // String query="SELECT "

        return null;
    }


}

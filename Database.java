package sk.kosickaakademia.kolesarova.mysql;

import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.CapitalCity;
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
    private String url = "jdbc:mysql://localhost:3306/world_x";
    private String username = "root";
    private String password = "";

    public List<City> getCities(String country) {//metoda mi len najde v DB informacie a pripravi ich ako zoznam pre metodu ktora ich vypise
        String query = "SELECT City.Name, JSON_EXTRACT(Info,'$.Population') AS Population " +//ak je info vo formate json musim davat extract a potom cez '' ktorú položku
                "FROM city " +
                "INNER JOIN country ON country.code=city.CountryCode " +
                "WHERE country.name LIKE ? ORDER BY Population DESC";//chcem ich usporiadat vzostupne
        List<City> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                //System.out.println("Success");
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, country);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String city = rs.getString("Name");
                    int popul = rs.getInt("Population");
                    City cities = new City(city, popul);
                    list.add(cities);
                }
                connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (list);
    }


    public Country getCountryInfo(String country) {
        // String query="SELECT "

        return null;
    }

    public void updatePopulation(String country, String name, int popul) {
        if (popul > 0) {
            String query = "SELECT City.Name, JSON_EXTRACT(Info,'$.Population') AS Population " +
                    "FROM city " +
                    "INNER JOIN country ON country.code=city.CountryCode " +
                    "WHERE country.name LIKE ? AND City.name LIKE ?";
            ResultSet rs = null;
            try {
                Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, country);
                ps.setString(2, name);
                rs = ps.executeQuery();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs != null) {
                //String query1 = "UPDATE city SET Info =?"
            }

        }
    }

    public List<CapitalCity> getCapitalCities(String continent) {
        String query = "SELECT country.name, city.name, " +
                "JSON_EXTRACT(doc,'$.geography.Continent') AS Continent, " +
                "JSON_EXTRACT(Info,'$.Population') AS Population, " +
                "FROM country " +
                "INNER JOIN city ON country.Capital=city.ID " +
                "INNER JOIN countryInfo ON country.Code=countryinfo._id " +
                "WHERE JSON_UNQUOTE(JSON_EXTRACT(doc,'$.geography.Continent')) LIKE ?";
        List<CapitalCity> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, continent);
                ResultSet rs = ps.executeQuery();

                
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}


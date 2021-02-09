package sk.kosickaakademia.kolesarova.mysql;

import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.CapitalCity;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.Country;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.Monument;

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
            String query = "UPDATE city SET Info=? "+
                    "WHERE SELECT City.Name " +
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


        }
    }

    public List<CapitalCity> getCapitalCities(String continent) {
        String query = "SELECT country.name AS Country, city.name AS City, " +
                "JSON_EXTRACT(doc,'$.geography.Continent') AS Continent, " +
                "JSON_EXTRACT(Info,'$.Population') AS Population " +
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
                while(rs.next()){
                    String country=rs.getString("Country");
                    String city= rs.getString("City");
                    String contin= rs.getString("Continent");
                    int popul=rs.getInt("Population");
                    CapitalCity capitalCity=new CapitalCity(country,city,contin,popul);
                    list.add(capitalCity);
                }
            connection.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int existCity(String code3, String cityName) {//metóda či mesto v danej krajine existuje
        if (code3 == null || code3.equals("") || cityName == null || cityName.equals(""))
            return -1; //kontrola na prázdne hodnoty v stĺpcoch code a názov mesta

        String query = "SELECT id FROM city WHERE CountryCode LIKE ? AND name LIKE ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1, code3);
                ps.setString(2, cityName);
                ResultSet rs = ps.executeQuery();

                if(rs.next()){
                    int id=rs.getInt("id");
                    connection.close();
                    return id;
                }else {
                    connection.close();
                    return -1;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean  insertNewMonument( String code3, String city, String name ){
        if(name==null || name.equals(""))
            return false;
        int cityId=existCity(code3,city);
        if(cityId==-1)
            return false;
        String query="INSERT INTO monument(name, city) VALUES (?, ?)";//vloženie monumentu
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setString(1,name);
                ps.setInt(2,cityId);
                ps.executeUpdate();
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }

    public List<Monument> getMonuments(){
        String query="SELECT country.name AS Country, city.name AS City, monument.name AS Monument, monument.id "+
                "FROM monument "+
                "INNER JOIN city ON monument.city=city.ID "+
                "INNER JOIN country ON city.CountryCode=country.Code";
        List<Monument> list=new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                PreparedStatement ps = connection.prepareStatement(query);
                ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    String country=rs.getString("Country");
                    String city= rs.getString("City");
                    String name= rs.getString("Monument");
                    int id=rs.getInt("id");
                    Monument monument=new Monument(city,name,id,country);
                    list.add(monument);
                }
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}


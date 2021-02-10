package sk.kosickaakademia.kolesarova.mysql;

import org.json.simple.parser.ParseException;
import sk.kosickaakademia.kolesarova.mysql.json.Server;
import sk.kosickaakademia.kolesarova.mysql.output.Output;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.CapitalCity;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.Monument;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database=new Database();
        Output output=new Output();

       /* List<City> cities=database.getCities("Argentina");
        output.printCities(cities);

        List<CapitalCity> capitalCities=database.getCapitalCities("Europe");
        output.printCapitalCities(capitalCities);

        database.insertNewMonument("CZE","Praha","Staromestsky orloj");
        List<Monument> monuments=database.getMonuments();
        output.printMonuments(monuments);*/


        Server server=new Server();

        try {
            server.insertNewMonument("{\"code3\":\"ESP\",\"city\":\"Barcelona\",\"name\":\"Sagrada Familia\"}");

        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(server.getMonuments());
    }
}

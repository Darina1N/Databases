package sk.kosickaakademia.kolesarova.mysql;

import sk.kosickaakademia.kolesarova.mysql.output.Output;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.CapitalCity;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Database database=new Database();
        Output output=new Output();
        List<City> cities=database.getCities("Argentina");
        output.printCities(cities);
        List<CapitalCity> capitalCities=database.getCapitalCities("Europe");
        output.printCapitalCities(capitalCities);
        database.insertNewMonument("SVK","Humenne","Kastiel");
    }
}
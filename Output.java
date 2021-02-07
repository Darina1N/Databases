package sk.kosickaakademia.kolesarova.mysql.output;

import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.CapitalCity;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;

import java.util.List;

public class Output {
    public void printCities(List<City> cities){
        for(City c: cities){
            System.out.println(c.getName()+ "("+c.getPopul()+")");
        }
    }

    public void printCapitalCities(List<CapitalCity> capitalCity){
        for(CapitalCity c: capitalCity){
            System.out.println(c.getCountryName()+ c.getCapitalCity()+c.getContinent()+c.getPopul());
        }
    }
}

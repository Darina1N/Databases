import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.City;

import java.util.List;

public class Output {
    public void printCities(List<City> cities){
        for(City c: cities){
            System.out.println(c.getName()+ "("+c.getPopul()+")");
        }
    }
}
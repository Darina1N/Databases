package sk.kosickaakademia.kolesarova.mysql.pociatTriedy;

public class Country {
    private String name;
    private String code3Alfabeth;
    private String capitalCity;
    private String Continent;
    private int area;
    private String languages;

    public Country(String name, String code3Alfabeth, String capitalCity, String continent, int area, String languages) {
        this.name = name;
        this.code3Alfabeth = code3Alfabeth;
        this.capitalCity = capitalCity;
        Continent = continent;
        this.area = area;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public String getCode3Alfabeth() {
        return code3Alfabeth;
    }

    public String getCapitalCity() {
        return capitalCity;
    }

    public String getContinent() {
        return Continent;
    }

    public int getArea() {
        return area;
    }

    public String getLanguages() {
        return languages;
    }
}

ackage sk.kosickaakademia.kolesarova.mysql.pociatTriedy;

public class CapitalCity {
    private String countryName;
    private String code3Alfabeth;
    private String capitalCity;
    private String Continent;
    private int popul;

    public CapitalCity(String countryName, String code3Alfabeth, String capitalCity, String continent, int popul) {
        this.countryName = countryName;
        this.code3Alfabeth = code3Alfabeth;
        this.capitalCity = capitalCity;
        Continent = continent;
        this.popul = popul;
    }

    public String getCountryName() {
        return countryName;
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

    public int getPopul() {
        return popul;
    }
}

package sk.kosickaakademia.kolesarova.mysql.pociatTriedy;

public class Monument {
    private String code3;
    private String city;
    private String name;

    public Monument(String code3, String city, String name) {
        this.code3 = code3;
        this.city = city;
        this.name = name;
    }

    public String getCode3() {
        return code3;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}

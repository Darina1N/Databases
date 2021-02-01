package sk.kosickaakademia.kolesarova.mysql.pociatTriedy;

public class City { //trieda kde budem mať parametre ktoré používam naprieč metódami
    private String name;
    private int popul;

    public City(String name, int popul) {
        this.name = name;
        this.popul = popul;
    }

    public String getName() {
        return name;
    }

    public int getPopul() {
        return popul;
    }
}

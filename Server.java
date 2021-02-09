package sk.kosickaakademia.kolesarova.mysql.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.kosickaakademia.kolesarova.mysql.Database;
import sk.kosickaakademia.kolesarova.mysql.pociatTriedy.Monument;

import java.util.List;

public class Server {
    public String getMonuments(){
        String resultJSON="";
        List<Monument> list= new Database().getMonuments();//vytvorila som si priamo cez triedu bez vytvorenia premennej triedy Database
        if(list.isEmpty())
            return "{}";//ak je zoznam prázdny tak nech vráti prázdny string
        JSONObject jsonObject= new JSONObject();
        JSONArray jsonArray=new JSONArray();
        for(Monument m: list){
            JSONObject newItem= new JSONObject();
            newItem.put("country",m.getCountry());
            newItem.put("city",m.getCity());
            newItem.put("name",m.getName());
            newItem.put("id",m.getId());
            jsonArray.add(newItem);
        }
        jsonObject.put("monuments",jsonArray);

        resultJSON=jsonObject.toJSONString();
        return resultJSON;
    }

    public boolean insertNewMonument(String jsonText) throws ParseException {
        JSONParser parser=new JSONParser();
        JSONObject jsonObject=(JSONObject) parser.parse(jsonText);
        if(jsonObject.isEmpty())
            return false;
        String code3=(String)jsonObject.get("code3");
        String city=(String) jsonObject.get("city");
        String name=(String) jsonObject.get("name");
        if(new Database().insertNewMonument(code3,city,name))
            return true;

        return false;
    }
}

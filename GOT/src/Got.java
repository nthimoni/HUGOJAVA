/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

/**
 *
 * @author thimo
 */
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class Got {
    public static void main(String[] args) {
        DataGetter getter = new DataGetter();
        JSONArray array = getter.getArrayFromUrl("https://thronesapi.com/api/v2/Characters");
        for (Object obj : array)
            System.out.println(((JSONObject)obj).get("firstName"));
        array = getter.getArrayFromUrl("https://thronesapi.com/api/v2/Continents");
        for (Object obj : array)
            System.out.println(((JSONObject)obj).get("name"));
    }
    
}


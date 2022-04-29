/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nico
 */
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;

public class DataGetter {
    private ClientHttp client;

    public DataGetter() {
        client = new ClientHttp();
    }
    public JSONArray getArrayFromUrl(String url) {
        if (!client.seConnecter(url)) {
            System.out.println("Impossible de se connecter : " + url);
            return null;
        }
        JSONParser parser = new JSONParser();
        JSONArray array;
        try {
            array = (JSONArray)parser.parse(client.recevoir());
        } catch (ParseException e) {
            System.out.println("Erreur lors de la lecture des donnes");
            client.seDeconnecter();
            return null;
        }
        client.seDeconnecter();
        return array;
    }
    public void getImage(String url, String name) {
        if (!client.seConnecter(url)) {
            System.out.println("Impossible de se connecter : " + url);
            return ;
        }
        client.recevoirImage(name);
        client.seDeconnecter();
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author hugo
 */
package my.gotgui;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 *
 * @author prof
 */
public class ClientHttp {

    private HttpURLConnection connexion;
    private String reception;
    private String total = "";

    public ClientHttp() {
    }

    /**
     * La méthode seDeconnecter permet la déconnexion du client http
     */
    public void seDeconnecter() {
        connexion.disconnect();
    }

    /**
     * La méthode seConnecter permet la connexion au serveur Http. Le nom du
     * fichier à transférer est nécessaire.
     *
     * @param adresse : url complète du site. Exemple :
     * http://api.football-data.org/v2/competitions/PL/matches/?matchday=22
     * @return true si la connexion a été établie. false sinon
     * @author prof
     */
    public boolean seConnecter(String adresse) {
        URL url = null;
        try {
            url = new URL(adresse);
        } catch (MalformedURLException ex) {
            return false;
        }
        try {
            connexion = (HttpURLConnection) url.openConnection();
            connexion.setRequestMethod("GET");
            connexion.connect();
        } catch (IOException ex) {
            return false;
        }
        System.out.println("connexion OK");
        return true;
    }

    /**
     * La méthode recevoir permet la reception de données du serveur Http. La
     * connexion doit être établie au préalable.
     *
     * @return Une chaîne de caractères en provenance du serveur (format html,
     * json, xml ou autre).
     * @author prof
     */
    public String recevoir() {
        InputStream inp;
        total = "";
        try {
            inp = connexion.getInputStream();
        } catch (IOException ex) {
            System.out.println("reception pas OK");
            return null;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(inp));
        try {
            //int i = 0;
            while ((reception = in.readLine()) != null) {
                total += reception + "\n";
            }
        } catch (IOException ex) {
            System.out.println("erreur reception");
            return null;
        }
        return total;
    }

    /**
     * La méthode recevoirImage permet la reception d'une image du serveur Http.
     * La connexion doit être établie au préalable. L'image est ensuite
     * sauvegardée sur le disque dur
     *
     * @param nomFichier représente le nom du fichier sur le disque dur, une
     * fois le téléchargement effectué.
     * @author prof
     */
    public void recevoirImage(String nomFichier) {
        InputStream is = null;
        try {
            is = new BufferedInputStream(connexion.getInputStream());
            BufferedImage image = ImageIO.read(is);
            is.close();
            ImageIO.write(image, "jpg", new File(nomFichier));
        } catch (IOException ex) {
            System.out.println("erreur reception image");
        }
    }
}

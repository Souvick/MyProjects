/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pan_ai_2015;

import java.io.FileReader;
import java.util.Iterator;
import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Souvick
 */
public class ReadJSON {
    private static String language;
    
    public static String readJson(String file) { 
        JSONParser parser = new JSONParser(); 
        try { 
            FileReader fileReader = new FileReader(file); 
            JSONObject json = (JSONObject) parser.parse(fileReader); 
            
             language = (String) json.get("language"); 
            //System.out.println("Language: " + language); 
            
            JSONArray filenames = (JSONArray) json.get("problems"); 
            Iterator i = filenames.iterator(); 
            /*System.out.println("Filenames: "); 
            while (i.hasNext()) { 
                System.out.println(" " + i.next()); 
            } */
        } catch (Exception ex) { ex.printStackTrace(); } 
    return language;
    }
    
        
}

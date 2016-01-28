/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.util.HashMap;

/**
 *
 * @author Promita
 */
public class CompareHashMap {
    
     public int compareHashmaps(HashMap<String,Integer> hm1, HashMap<String,Integer> hm2){
        int match = 0;
        int count = hm1.size() + hm2.size();
        for (final String key : hm1.keySet()) {
            if (hm2.containsKey(key)) {
                match+=1;                
            }
        }
        float i= (float)(match)/count;
        int result= (int)(i*1000);
        return result;
    }
    
}

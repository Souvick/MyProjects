/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Promita
 */
public class Bigrams {
    
    // Returns hashmap of Bigram and their frequency
    public HashMap<String,Integer> getBigrams(String text){
        //System.out.println("Inside getBigrams");
        HashMap<String,Integer> hm = new HashMap<>();
        String w1="",w2="";
        Scanner scan = new Scanner(text);
        if (scan.hasNext()) {
            w1 = scan.next();
        }
        while(scan.hasNext()){
            w2 = scan.next();
            String k = w1+"_"+w2;
            if(hm.containsKey(k)){
                int i = hm.get(k);
                hm.put(k, i+1);
            }
            else{
                hm.put(k,1);
            }
            w1 = w2;
        }
        //System.out.println(hm.toString());
        SortHashMap shm= new SortHashMap();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns Bigram and their frequency in string form
    public String getBigrams_toString(String text){
        return getBigrams(text).toString();
    }
    
    // NEW METHOD
    // Calculates a similarity index of the two hashmaps
    public int compareHashmaps(HashMap<String,Integer> hm1, HashMap<String,Integer> hm2){
        int match = 0;
        int count = hm1.size() + hm2.size();
        for (final String key : hm1.keySet()) {
            if (hm2.containsKey(key)) {
                match+=1;                
            }
        }
        return (match/count)*100;
    }
    
    // NEW METHOD
    // Returns the bigram similarity measure for the 2 imput texts
    public int getBigramSimilarity(String input1, String input2){
        return compareHashmaps(getBigrams(input1),getBigrams(input2));
    }
    
}

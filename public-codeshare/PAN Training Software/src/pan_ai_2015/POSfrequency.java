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
public class POSfrequency {
    
    // Returns hashmap of POS frequency
    public HashMap<String,Integer> getPosFrequency(String text){
        //System.out.println("Inside getPosFrequency");
        HashMap<String,Integer> hm = new HashMap<>();
        Scanner scan = new Scanner(text);
        while(scan.hasNext()){
            String pos = scan.next();
            if(hm.containsKey(pos)){
                int i = hm.get(pos);
                hm.put(pos, i+1);
            }
            else{
                hm.put(pos,1);
            }
        }
        SortHashMap shm= new SortHashMap();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns POS frequency in string
    public String getPosFrequency_toString(String text){
        return getPosFrequency(text).toString();
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
    // Returns the Pos similarity of 2 input texts
    public int getPosSimilarity(String input1, String input2){
        return compareHashmaps(getPosFrequency(input1),getPosFrequency(input2));
    }
    
    
}

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
public class POSsequence {
     // Returns hashmap of POS Sequence frequency
    public HashMap<String,Integer> getPosSeqFrequency(String text){
        //System.out.println("Inside getPosSeqFrequency");
        HashMap<String,Integer> hm = new HashMap<>();
        String pos1="",pos2="";
        Scanner scan = new Scanner(text);
        if(scan.hasNext()){
            pos1 = scan.next();
        }
        while(scan.hasNext()){
            pos2 = scan.next();
            String ps = pos1+"_"+pos2;
            if(hm.containsKey(ps)){
                int i = hm.get(ps);
                hm.put(ps, i+1);
            }
            else{
                hm.put(ps,1);
            }
            pos1 = pos2;
        }
        //System.out.println(hm.toString());
        SortHashMap shm= new SortHashMap();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }

    // Returns POS Sequennce frequency in string
    public String getPosSeqFrequency_toString(String text){
        return getPosSeqFrequency(text).toString();
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
    // Returns the Pos Sequence similarity of 2 input texts
    public int getPosSeqSimilarity(String input1, String input2){
        return compareHashmaps(getPosSeqFrequency(input1),getPosSeqFrequency(input2));
    }
    
}

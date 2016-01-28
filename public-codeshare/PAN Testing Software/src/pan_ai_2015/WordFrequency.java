/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Promita
 */
public class WordFrequency {
    // Returns hashmap of used words
    // To eliminate hapax legomenons
    public HashMap<String,Integer> getWordFrequency(String text){
        HashMap<String,Integer> hm = new HashMap<>();
        Scanner scan = new Scanner(text);
        while(scan.hasNext()){
            String wd = scan.next();
            if(hm.containsKey(wd)){
                int i = hm.get(wd);
                hm.put(wd, i+1);
            }
            else{
                hm.put(wd,1);
            }
        }
        SortHashMap shm= new SortHashMap();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns word frequency in string
    public String getWordFrequency_toString(String text){
        return getWordFrequency(text).toString();
    }
    
    public float getUniqueWordRatio(String text)
    {
        StringTokenizer tokenizer = new StringTokenizer(text);
	float count= tokenizer.countTokens();
        HashMap hm= getWordFrequency(text);
        count= hm.size()/count;
        return count;
    }
}

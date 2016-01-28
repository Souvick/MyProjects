/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Souvick
 */
public class SortHashMapv2 {
    // To sort hasmap in descending order
    public LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
        //System.out.println("Inside sortHashMapByValuesD");
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Comparator cmp = Collections.reverseOrder(); 
        Collections.sort(mapValues,cmp);
        Collections.sort(mapKeys,cmp);
        LinkedHashMap sortedMap = new LinkedHashMap();
        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();
            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();
                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String)key,Integer.parseInt(val.toString()));
                    break;
                }
            }
        }
        return sortedMap;
     }
    
    // Extracts top 3 feature by frequency
    public String extractTop(String text){
        int count = 0;
        String out = "";
        String input = text.substring(1, text.length()-1);
        StringTokenizer st = new StringTokenizer(input,",");
        while(st.hasMoreElements()){
            String token = st.nextToken();
            count+=1;
            String[] split = token.split("=");
            if(split.length==2)
                out+= split[0]+"\t"+split[1];
            if (count ==3) break;
            out+= "\t";
        }
        //out +="#";
        return out;
    }
    
    public String hashToString(HashMap<String,Integer> hm){
        String words = "";
        Iterator iter  = hm.keySet().iterator();
        while(iter.hasNext()){
            words+= iter.next().toString()+";";
        }
        return words;
    }
    
    public static void main(String[] args){
        String text = "This is an amazing world with weird people. People are curious and eccentric at the same time";
        HashMap<String,Integer> hm = new HashMap<>();
        Statistics stat = new Statistics();
        ParserTagging pt = new ParserTagging();
        String to = pt.postag(text);
        GetWordAndPOSv2 gwp = new GetWordAndPOSv2();
        String words = gwp.getWords(to);
        System.out.println(words);
        Unigrams u = new Unigrams();
        System.out.println(u.getUnigrams(words));
        System.out.println(u.getUnigramsWF_toString(words));
                
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Souvick
 */
public class Bigrams {
    
    // Returns if the string is a punctuation 
    public boolean isPunctuation(String s){
        if(s.equals(",") || s.equals(";") || s.equals(".")|| s.equals("~"))
            return true;
        else return s.equals("/");
    }
    
    
    // Returns hashmap of Bigram and their frequency
    public HashMap<String,Integer> getBigramsWF(String text){
        //System.out.println("Inside getBigrams");
        HashMap<String,Integer> hm = new HashMap<>();
        String w1="",w2="";
        Scanner scan = new Scanner(text);
        if (scan.hasNext()) {
            w1 = scan.next();
        }
        while(scan.hasNext()){
            w2 = scan.next();
            if(w1.length()<1 || isPunctuation(w1)){
                w1 = w2;
                continue;
            }                
            if(w2.length()<1 || isPunctuation(w2)){
                continue;
            }
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
        SortHashMapv2 shm= new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;        
    }
    
    // Returns string of Bigrams without freq
    public String getBigrams(String text){
        //System.out.println("Inside getBigrams");
        HashMap<String,Integer> hm = new HashMap<>();
        String w1="",w2="";
        Scanner scan = new Scanner(text);
        if (scan.hasNext()) {
            w1 = scan.next();
        }
        while(scan.hasNext()){
            w2 = scan.next();
            if(w1.length()<1 || isPunctuation(w1)){
                w1 = w2;
                continue;
            }                
            if(w2.length()<1 || isPunctuation(w2)){
                continue;
            }
            String k = w1+"_"+w2;
            if(hm.containsKey(k)){                
            }
            else{
                hm.put(k,1);
            }
            w1 = w2;
        } 
        SortHashMapv2 shm= new SortHashMapv2();
        return shm.hashToString(hm);        
    }
    
    // Returns Bigram and their frequency in string form
    public String getBigramsWF_toString(String text){
        return getBigramsWF(text).toString();
    }
    
    // Calculates a similarity index of the two hashmaps
    public int compareHashmaps(HashMap<String,Integer> hm1, HashMap<String,Integer> hm2){
        int match = 0;
        //int count = hm1.size() + hm2.size();
        for (final String key : hm1.keySet()) {
            if (hm2.containsKey(key)) {
                match+=1;                
            }
        }
        return match;
    }
    
    // Returns the bigram similarity measure for the 2 imput texts
    public int getBigramSimilarity(String input1, String input2){
        return compareHashmaps(getBigramsWF(input1),getBigramsWF(input2));
    }
}

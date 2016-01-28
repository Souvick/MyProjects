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

public class Trigrams {

    // Returns if the string is a punctuation 
    public boolean isPunctuation(String s){
        if(s.equals(",") || s.equals(";") || s.equals(".")|| s.equals("~"))
            return true;
        else return s.equals("/");
    }
    
    // Returns Trigram and their frequency
    public HashMap<String,Integer> getTrigramsWF(String text){
        //System.out.println("Inside getTrigrams");
        HashMap<String,Integer> hm = new HashMap<>();
        String w1="",w2="",w3="";
        Scanner scan = new Scanner(text);
        if (scan.hasNext()) {
            w1 = scan.next();
        }
        if (scan.hasNext()) {
            w2 = scan.next();
        }
        while(scan.hasNext()){
            w3 = scan.next();
            if(w1.length()<1 || isPunctuation(w1)){
                w1 = w2;
                w2 = w3;
                continue;
            }                
            if(w2.length()<1 || isPunctuation(w2)){
                w2 = w3;
                continue;
            }
            if(w3.length()<1 || isPunctuation(w3)){
                continue;
            }
            
            String k = w1+"_"+w2+"_"+w3;
            if(hm.containsKey(k)){
                int i = hm.get(k);
                hm.put(k, i+1);
            }
            else{
                hm.put(k,1);
            }
            w1 = w2;
            w2 = w3;
        }
        //System.out.println(hm.toString());
        SortHashMapv2 shm= new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns Trigram and their frequency
    public String getTrigrams(String text){
        //System.out.println("Inside getTrigrams");
        HashMap<String,Integer> hm = new HashMap<>();
        String w1="",w2="",w3="";
        Scanner scan = new Scanner(text);
        if (scan.hasNext()) {
            w1 = scan.next();
        }
        if (scan.hasNext()) {
            w2 = scan.next();
        }
        while(scan.hasNext()){
            w3 = scan.next();
            if(w1.length()<1 || isPunctuation(w1)){
                w1 = w2;
                w2 = w3;
                continue;
            }                
            if(w2.length()<1 || isPunctuation(w2)){
                w2 = w3;
                continue;
            }
            if(w3.length()<1 || isPunctuation(w3)){
                continue;
            }
            
            String k = w1+"_"+w2+"_"+w3;
            if(hm.containsKey(k)){
                int i = hm.get(k);
                hm.put(k, i+1);
            }
            else{
                hm.put(k,1);
            }
            w1 = w2;
            w2 = w3;
        }
        //System.out.println(hm.toString());
        SortHashMapv2 shm= new SortHashMapv2();
        return shm.hashToString(hm);
    }
    
        
    // Returns Trigram and their frequency in string form
    public String getTrigramsWF_toString(String text){
        return getTrigramsWF(text).toString();
    }
    
    // NEW METHOD
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
    
    // Returns the trigram similarity measure for the 2 imput texts
    public int getBigramSimilarity(String input1, String input2){
        return compareHashmaps(getTrigramsWF(input1),getTrigramsWF(input2));
    }
}

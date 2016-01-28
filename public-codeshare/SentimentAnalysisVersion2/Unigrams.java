/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Souvick
 */
public class Unigrams {
    // Returns if the string is a punctuation 
    public boolean isPunctuation(String s){
        if(s.equals(",") || s.equals(";") || s.equals(".")|| s.equals("~"))
            return true;
        else return s.equals("/");
    }
    
    // Returns root words
    public String getWords(String text){
        //System.out.println("Inside getWords");
        String pos = "";
        String word = "";
        StringTokenizer st = new StringTokenizer(text," ");
        while(st.hasMoreTokens()){
            String wordandpos = st.nextToken();
            String[] split = wordandpos.split("/");
            if(split.length>1) {
                if(!split[1].isEmpty()){
                    String check = split[1];
                    if (check.equals(",")||check.equals("?")||check.equals(".")||check.equals("!"))
                        continue;
                    word += check+"\n";
                }
            }
        }
        //System.out.println(word);
        return word;
    }
    
    // NEW METHOD
    // Returns a hashmap of the unigram model of words present
    public HashMap<String,Integer> getUnigramsWF(String text){
        HashMap<String,Integer> hm = new HashMap();
        Scanner wordlist = new Scanner(text);
        while(wordlist.hasNext()){
            String word = wordlist.next();
            if(isPunctuation(word)) continue;
            if(hm.containsKey(word)){
                int i = hm.get(word);
                hm.put(word, i+1);
            }
            else{
                hm.put(word,1);
            }
        }        
        SortHashMapv2 shm= new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns a string of the unigrams without freq
    public String getUnigrams(String text){
        HashMap<String,Integer> hm = new HashMap();
        Scanner wordlist = new Scanner(text);
        while(wordlist.hasNext()){
            String word = wordlist.next();
            if(isPunctuation(word)) continue;
            if(hm.containsKey(word)){
            }
            else{
                hm.put(word,1);
            }
        }        
        SortHashMapv2 shm= new SortHashMapv2();
        return shm.hashToString(hm);
    }
    
    // Returns Bigrams and their frequency in string form
    public String getUnigramsWF_toString(String text){
        return getUnigramsWF(text).toString();
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
    
    // Returns the unigram similarity measure for the 2 imput texts
    public int getUnigramSimilarity(String input1, String input2){
        return compareHashmaps(getUnigramsWF(input1),getUnigramsWF(input2));
    }   
}

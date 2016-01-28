/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import SentimentAnalysisVersion2.SortHashMapv2;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Souvick
 */

public class GetWordAndPOSv2 {
    
    // Requires : Tagged text as input
    
    // Returns the list of pos
    public String getPOSTags(String text){
        //System.out.println("Inside getPOSTags");
        String pos = "";
        String word = "";
        StringTokenizer st = new StringTokenizer(text," ");
        while(st.hasMoreTokens()){
            String wordandpos = st.nextToken();
            //System.out.println(wordandpos);
            String[] split = wordandpos.split("/");
            if(split.length>1) {
                if(split[2].length()>1 && split[2].length()<4) 
                    pos += split[2]+"\n";
                //System.out.println(pos+":"+word);
            }
        }
        //System.out.println(pos);
        return pos;
    }
    
    // Returns the set of words
    public String getWords(String text){
        //System.out.println("Inside getWords");
        String pos = "";
        String word = "";
        StringTokenizer st = new StringTokenizer(text," ");
        while(st.hasMoreTokens()){
            String wordandpos = st.nextToken();
            String[] split = wordandpos.split("/");
            if(split.length>1) {
                if(!split[1].isEmpty())
                    word += split[1]+"\n";
            }
        }
        //System.out.println(word);
        return word;
    }
    
    // Returns the set of words
    public String getLemWord(String input){
        //System.out.println("Inside getWords");
        //SentimentAnalysisVersion2.Statistics stat = new SentimentAnalysisVersion2.Statistics();
        ParserTagging pt = new ParserTagging();
        String text = pt.postag(input);
        String pos = "";
        String word = "";
        String[] split = text.split("/");
        if(split.length>1) {
            if(!split[1].isEmpty())
                word = split[1];
        }
        //System.out.println(word);
        return word;
    }
    
    // Returns hashmap of used words to eliminate hapax legomenons
    // Input : Set of Words
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
        SortHashMapv2 shm= new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
    }
    
    // Returns word frequency in string
    public String getWordFrequency_toString(String text){
        return getWordFrequency(text).toString();
    }
    
    
    // Returns hashmap of the pos and their frequency
    // Input : Set of POS
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
        SortHashMapv2 shm= new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
        //System.out.println(hm.toString());
        //return hm.toString();
    }
    
    public String getPosFrequency_toString(String text){
        return getPosFrequency(text).toString();
    }
    
}

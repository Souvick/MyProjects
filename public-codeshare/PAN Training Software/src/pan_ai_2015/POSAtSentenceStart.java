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
public class POSAtSentenceStart {
        
     public String getBeginningofSentence(String content){
        int i;
        String output = "";
        SentenceRatio sr= new SentenceRatio();
        String[] sentencecollection= sr.getSentenceCollection(content);

        for (i=0; i<sentencecollection.length; i++){
            if (sentencecollection[i]!=null){
                StringTokenizer words = new StringTokenizer(sentencecollection[i],"\t\r\n\\ .,;:?!/\'\"");
                if(words.hasMoreTokens()){
                    String word = words.nextToken().toLowerCase();
                    output += word+"\n";
                }
            }
        }
        return output;
    }
    
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
    
    // Returns POS frequency at the start of the sentence
    public HashMap<String,Integer> getPosAtSentenceStart(String taggedtext){
        String pos = "";
        StringTokenizer sentences = new StringTokenizer(taggedtext,".;:?!");
        while(sentences.hasMoreTokens()){
            String sentence = sentences.nextToken();
            StringTokenizer tokens = new StringTokenizer(sentence);
            if (tokens.hasMoreTokens()){
                String wordandpos = tokens.nextToken();
                //System.out.println(wordandpos);
                String[] split = wordandpos.split("/");
                if(split.length>1) {
                    if(split[2].length()>1 && split[2].length()<4) 
                        pos += split[2]+"\n";
                }
            }
        }
        //System.out.println(pos);
        return getPosFrequency(pos);
    }
    
    // Returns the string corresponding to the hashmap above
    public String getPosAtSentenceStart_toString(String taggedtext){
        return getPosAtSentenceStart(taggedtext).toString();
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
    // Returns the similarity measure of pos at sentence start for the 2 imput texts
    public int getStartingPosSimilarity(String input1, String input2){
        return compareHashmaps(getPosAtSentenceStart(input1),getPosAtSentenceStart(input2));
    }
    
    // NEW METHOD
    // Returns the Pos similarity of 2 input texts
    public int getPosSimilarity(String input1, String input2){
        return compareHashmaps(getPosFrequency(input1),getPosFrequency(input2));
    }
    
}


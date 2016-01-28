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
public class RootWords {
    
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
    public HashMap<String,Integer> getWordList(String wordandpos){
        HashMap<String,Integer> hm = new HashMap();
        Scanner wordlist = new Scanner(getWords(wordandpos));
        while(wordlist.hasNext()){
            String word = wordlist.next();
            if(hm.containsKey(word)){
                int i = hm.get(word);
                hm.put(word, i+1);
            }
            else{
                hm.put(word,1);
            }
        }        
        SortHashMap shm= new SortHashMap();
        hm = shm.sortHashMapByValuesD(hm);
        return hm;
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
    // Returns the unigram similarity measure for the 2 imput texts
    public int getBigramSimilarity(String input1, String input2){
        return compareHashmaps(getWordList(input1),getWordList(input2));
    }   
    
}

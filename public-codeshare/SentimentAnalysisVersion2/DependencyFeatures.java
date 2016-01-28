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
public class DependencyFeatures {
    
    // Returns dependency output
    public HashMap<String,Integer> getDepOutput(String text){
        //System.out.println(text);
        String depword = "";
        String word = text.replaceAll("\\(.*?\\)", "");
        String word2 = word.replaceAll("[\\[\\]]","");
        //System.out.println("Word1:" + word +"Word2 :"+ word2);
        StringTokenizer st = new StringTokenizer(word2,"\", \"");
        while(st.hasMoreTokens()){
            String deptoken = st.nextToken();
            depword+= deptoken+"\n";            
        }
        return getDepFrequency(depword);
    }
    
    // Returns dependency frequency in a hashmap
    public HashMap<String,Integer> getDepFrequency(String text){
        //System.out.println("Inside getDepFrequency");
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
    }
    
    public String getDepFrequency_toString(String text){
        return getDepFrequency(text).toString();
    }
    
    // returns dependency relations in lemmatized form
    public String getDepRelations(String text){
        //System.out.println(text);
        String deprel="";
        String word = text.replaceAll("-[0-9]*", "");
        //System.out.println(word);
        String word1 = word.replaceAll("[\\[\\]]","");
        //System.out.println(word1);
        String word2 = word1.replaceAll("\\),",")#");
        //System.out.println(word2);
        String word3 = word2.replaceAll("\\n","# ");
        //System.out.println(word3);
        StringTokenizer st = new StringTokenizer(word3,"#");
        while(st.hasMoreTokens()){
            String deptoken = st.nextToken();
            //System.out.println(deptoken+"->");
            int ind1 = deptoken.indexOf("(");
            int ind2 = deptoken.indexOf(",");
            int ind3 = deptoken.indexOf(")");
            if(ind1>=0 && ind2>0 && ind2>ind1 && ind3>ind2){
                String w1 = deptoken.substring(0, ind1);
                //System.out.print(w1+"\t");
                String w2 = deptoken.substring(ind1+1, ind2);
                //System.out.print(w2+"\t");
                String w3 = deptoken.substring(ind2+1, ind3);
                //System.out.print(w3+"\n");
                if(w2.equalsIgnoreCase("~")) 
                    continue;
                if(w3.equalsIgnoreCase("~")) 
                    continue;
                GetWordAndPOSv2 gwp = new GetWordAndPOSv2();
                String lemword1 = w2, lemword2 = w3;
                if(w2.length()>0)
                    lemword1 = gwp.getLemWord(w2);
                if(w3.length()>0)
                    lemword2 = gwp.getLemWord(w3);
                String newword = w1+"("+lemword1+","+lemword2+")";
                deprel+= newword+"\n";            
                        
            }
        }
        //System.out.println(deprel);
        return deprel;        
    }
    
    public static void main(String[] args){
        ParserTagging pt = new ParserTagging();
        DependencyFeatures df = new DependencyFeatures();
        String text = pt.dependency("The quick brown fox jumped over the blue moon\nHe coughed and huffed.");
        //String text1 = df.getDepRelations(text);
        //String op = df.getDepFrequency_toString(text1);
        String op = df.getDepOutput(text).toString();
        System.out.println(op);
    }
    
}

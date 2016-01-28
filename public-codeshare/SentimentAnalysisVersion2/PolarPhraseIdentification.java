/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Souvick
 */
public class PolarPhraseIdentification {
    
    public HashMap<String,Integer> getPositivePolarityWordsHM(){
        Scanner scanner;
        HashMap<String,Integer> hm = new HashMap();
        File file = new File("Z:\\Research\\Citation Sentiment Analysis\\Others\\polar phrases\\positive_polar_phrases.txt");
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String pword = scanner.nextLine();
                if(hm.containsKey(pword)){
                }
                else{
                    hm.put(pword,1);
                }
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        }  
        return hm;
    }
    
    public HashMap<String,Integer> getNegativePolarityWordsHM(){
        Scanner scanner;
        HashMap<String,Integer> hm = new HashMap();
        File file = new File("Z:\\Research\\Citation Sentiment Analysis\\Others\\polar phrases\\negative_polar_phrases.txt");
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String nword = scanner.nextLine();
                if(hm.containsKey(nword)){
                }
                else{
                    hm.put(nword,1);
                }
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        }  
        return hm;
    }
    
    public void createPosNegHM(){
        Statistics s = new Statistics();
        PolarPhraseIdentification p = new PolarPhraseIdentification();
        s.hmnegatives = p.getNegativePolarityWordsHM();
        s.hmpositives = p.getPositivePolarityWordsHM();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.HashMap;

/**
 *
 * @author Souvick
 */
public class GetDepFeaturesForClassfn {
    
    public int isAmodPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("amod")){
            return 1;
        }
        else{
            return 0;
        }                
    }
    
    public int isAcompPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("acomp")){
            return 1;
        }
        else{
            return 0;
        }                
    }
    
    public int isAdvmodPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("advmod")){
            return 1;
        }
        else{
            return 0;
        }                
    }
        
}

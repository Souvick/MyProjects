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
public class GetPOSFeaturesForClassfn {
    
    public int isJJPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("JJ")){
            return 1;
        }
        else{
            return 0;
        }                
    }
    
    public int isFWPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("FW")){
            return 1;
        }
        else{
            return 0;
        }                
    }
    
    public int isRBPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("RB")){
            return 1;
        }
        else{
            return 0;
        }                
    }
    
    public int isRBJJPresent(HashMap<String,Integer> hm){
        if (hm.containsKey("RB_JJ")){
            return 1;
        }
        else{
            return 0;
        }                
    }  
    
}

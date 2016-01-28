package PrepareForCRF;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Souvick
 */

public class VariablesList {
    //Global Max
    private final int MAX = 30000;
    
    //Contains all the global variables
    
    String word[] = new String[MAX]; 
    String lang[] = new String[MAX]; 
    String post[] = new String[MAX]; 
    
    //Global Counter
    int globalcounter=0;

    public VariablesList() {
    
    }
    
}




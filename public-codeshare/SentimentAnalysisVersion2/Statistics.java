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
public class Statistics {
    //Global Max
    private final int MAX = 9000;
    
    //Contains all the global variables
    /*
    String source[] = new String[MAX];
    String target[] = new String[MAX];
    String sentence[] = new String[MAX];
    String msentiment[] = new String[MAX];
    int sent[] = new int[MAX];
    int selfcit[] = new int[MAX];
    String taggedoutput[] = new String[MAX];
    String dependency[] = new String[MAX];
    String words[] = new String[MAX];
    String postags[] = new String[MAX];
    String posfreq[] = new String[MAX];
    String posseq[] = new String[MAX];
    String bigrams[] = new String[MAX];
    String trigrams[] = new String[MAX];
    String depenrel[] = new String[MAX];
    String swnscore[] = new String[MAX];
    double autosentiment[] = new double[MAX];
    int positivewords[] = new int[MAX];
    int negativewords[] = new int[MAX];
    */
    int id[] = new int[MAX]; // source paper
    String source[] = new String[MAX]; // source paper
    String target[] = new String[MAX]; // targer paper
    String sentence[] = new String[MAX]; // citation sentence
    String msentiment[] = new String[MAX]; // sentiment annotated manually
    int sent[] = new int[MAX]; // sentiment
    int selfcit[] = new int[MAX];
    String taggedoutput[] = new String[MAX];
    String dependency[] = new String[MAX];
    String words[] = new String[MAX];
    String postags[] = new String[MAX];
    HashMap<String,Integer> posfreq[] = new HashMap[MAX];
    HashMap<String,Integer> posseq[] = new HashMap[MAX];
    HashMap<String,Integer> bigrams[] = new HashMap[MAX];
    HashMap<String,Integer> trigrams[] = new HashMap[MAX];
    HashMap<String,Integer> depenrel[] = new HashMap[MAX];
    String swnscore[] = new String[MAX];
    double autosentiment[] = new double[MAX];
    
    int unimatchp[] = new int[MAX];
    int bimatchp[] = new int[MAX];
    int trimatchp[] = new int[MAX];
    
    int unimatchn[] = new int[MAX];
    int bimatchn[] = new int[MAX];
    int trimatchn[] = new int[MAX];
    
    int positivewords[] = new int[MAX];
    int negativewords[] = new int[MAX];
    
    int isJJPresent[] = new int[MAX];
    int isFWPresent[] = new int[MAX];
    int isRBPresent[] = new int[MAX];
    int isRBJJPresent[] = new int[MAX];
    
    int isAmodPresent[] = new int[MAX];
    int isAcompPresent[] = new int[MAX];
    int isAdvmodPresent[] = new int[MAX];
    
    HashMap<String,Integer> hmpositives = new HashMap();
    HashMap<String,Integer> hmnegatives = new HashMap();
    
    //Global Counter
    int globalcounter=0;

    public Statistics() {
        
    }

    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

/**
 *
 * @author Souvick
 */
public class GetSelfCitation {
    private final int MAX = 9000;
    
    
    public void isSelfCitation(Statistics stat){
       for(int i=0; i<stat.globalcounter; i++){
           System.out.println(stat.source[i]+"-->"+stat.target[i]+"-->"+stat.sentence[i].length());
           if (stat.source[i].equalsIgnoreCase(stat.target[i])){
               stat.selfcit[i] = 1;
           }
       }
    }
    
    public static void main(String args[]){
       Statistics stat = new Statistics();
       SentenceExtractionv2 se2 = new SentenceExtractionv2();
       se2.extractWindow(stat);
       GetSelfCitation sc = new GetSelfCitation();
       sc.isSelfCitation(stat);
       System.out.println("Self Citation Check:");
       for(int i=0; i<stat.globalcounter; i++){
           if(stat.selfcit[i]==1)
               System.out.println(stat.source[i]);
       }
    }
}

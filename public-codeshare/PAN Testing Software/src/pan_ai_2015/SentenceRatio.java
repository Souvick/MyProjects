/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.util.StringTokenizer;

/**
 *
 * @author Promita
 */
public class SentenceRatio {
    private static final int MAX = 200;
    public String[] sentencecollection= new String[MAX];
    // Returns the ratio of long sentences to total sentences
    public double getLongSentenceRatio(int[] counter){
        int numoflongsent=0;
        double sum = 0.0;
        for(int i=0; i<counter.length; i++){
            //System.out.println(counter[i]);
            if (counter[i]>0){     
                if (counter[i]>12){
                    numoflongsent++;
                }
                sum +=1;
            }
        }
        //System.out.println(numoflongsent+" "+sum);
        double result = numoflongsent/sum;
        return result;
    }
    
    // Returns the ratio of short sentences to total sentences
    public double getShortSentenceRatio(int[] counter){
        int numofshortsent=0;
        double sum = 0.0;
        for(int i=0; i<counter.length; i++){
            //System.out.println(counter[i]);
            if (counter[i]>0){     
                if (counter[i]<6){
                    numofshortsent++;
                }
                sum +=1;
            }
        }
        //System.out.println(numofshortsent+" "+sum);
        double result = numofshortsent/sum;
        return result;
    }
    
    // Returns the number of sentences in the input text
    public int[] countSentences(String text, int[] counter){
        int sentencecount=0, wordcount;
	// Tokenize line to get sentences
        StringTokenizer sentences = new StringTokenizer(text,".;:?!");
        //while there are more sentences
        while(sentences.hasMoreTokens()){
            sentencecount++;
            String sentence = sentences.nextToken();
            sentencecollection[sentencecount] = sentence;
            //System.out.println(sentencecount+":"+sentence);
            wordcount = 0;
            StringTokenizer words = new StringTokenizer(sentence,"\t\r\n\\ .,;:?!/\'\"");
            while(words.hasMoreTokens()){
                wordcount++;
                String word = words.nextToken().toLowerCase();
                //System.out.println("Word "+wordcount+":"+word);
            }
            counter[sentencecount] = wordcount;
        }
        return counter;
    }
    
    public String[] getSentenceCollection(String text){
        int sentencecount=0, wordcount;
	// Tokenize line to get sentences
        StringTokenizer sentences = new StringTokenizer(text,".;:?!");
        //while there are more sentences
        while(sentences.hasMoreTokens()){
            sentencecount++;
            String sentence = sentences.nextToken();
            sentencecollection[sentencecount] = sentence;
        }
        return sentencecollection;
    }
}

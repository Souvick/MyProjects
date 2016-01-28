/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author Promita
 */
public class POStag {
   
    private StanfordCoreNLP pipeline= new StanfordCoreNLP();
    // Returns the token words with root and pos
    public String postag(String text){
        String output = "";
        Annotation document = new Annotation(text);
        try {
            pipeline.annotate(document);
        }
        catch (Exception e)
        {
            System.out.println("Exception while calling method annotate(Method postag):"+e);
        }
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String word = token.get(CoreAnnotations.TextAnnotation.class);
                String pos = token.get(CoreAnnotations.PartOfSpeechAnnotation.class);
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                if (word != null && !word.isEmpty() && !(word.toString().equals("null"))){
                    output+= word+"/"+lemma+"/"+pos+" ";
                }
            }
            output+="\n";
        }
        return output;
    }

    // Returns only pos tags present in text
    public String getPOSTags(String text){
        //System.out.println("Inside getPOSTags");
        String pos = "";
        String word = "";
        StringTokenizer st = new StringTokenizer(text," ");
        while(st.hasMoreTokens()){
            String wordandpos = st.nextToken();
            //System.out.println(wordandpos);
            String[] split = wordandpos.split("/");
            if(split.length>1) {
                String check = split[1];
                if (check.equals(",")||check.equals("?")||check.equals(".")||check.equals("!"))
                    continue;
                if(split[2].length()>1 && split[2].length()<4) 
                    pos += split[2]+"\n";
                //else
                //    System.out.println(pos+":"+word);
            }
        }
        //System.out.println(pos);
        return pos;
    }
    
}

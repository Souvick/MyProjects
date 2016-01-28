package Unconstrained;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.util.CoreMap;
import java.util.List;
import java.util.Properties;

public class ParserStanford {
    private final int MAX = 9000;
    private Properties prop;
    private StanfordCoreNLP pipeline;
    LexicalizedParser lp = LexicalizedParser.loadModel("Z:\\Research\\NLP Repository\\englishPCFG.ser.gz");
    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    GrammaticalStructureFactory gsf= tlp.grammaticalStructureFactory();
   
    // Constructor sets the properties file for StanfordCoreNLP
    public ParserStanford(){
        prop = new Properties();
        try{
            prop.setProperty("annotators","tokenize, ssplit, pos, lemma, ner, parse, dcoref");
            prop.setProperty("ssplit.eolonly", "true");
            this.pipeline = new StanfordCoreNLP(prop);
        }
        catch(Exception e){
            System.out.println("Error in setting properties file:"+e);
        }
    }
    
    // Returns tagged output with original word, rootwords and postag
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
                if (word != null && !word.isEmpty() && !(word.toString().equals("null"))){
                    output+= word+"\t"+pos+"\n";
                }
            }            
        }
        return output;    
    }
}
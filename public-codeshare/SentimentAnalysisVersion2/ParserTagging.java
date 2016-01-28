/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.process.CoreLabelTokenFactory;
import edu.stanford.nlp.process.PTBTokenizer;
import edu.stanford.nlp.process.TokenizerFactory;
import edu.stanford.nlp.trees.GrammaticalStructure;
import edu.stanford.nlp.trees.GrammaticalStructureFactory;
import edu.stanford.nlp.trees.PennTreebankLanguagePack;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.util.CoreMap;
import java.io.StringReader;
import java.util.Collection;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Souvick
 */
public class ParserTagging {
    private final int MAX = 9000;
    private Properties prop;
    private StanfordCoreNLP pipeline;
    LexicalizedParser lp = LexicalizedParser.loadModel("Z:\\Research\\NLP Repository\\englishPCFG.ser.gz");
    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    GrammaticalStructureFactory gsf= tlp.grammaticalStructureFactory();
   
    // Constructor sets the properties file for StanfordCoreNLP
    public ParserTagging(){
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
                String lemma = token.get(CoreAnnotations.LemmaAnnotation.class);
                if (word != null && !word.isEmpty() && !(word.toString().equals("null"))){
                    output+= word+"/"+lemma+"/"+pos+" ";
                }
            }
            output+="\n";
        }
        return output;
    }

    // Returns dependency 2
    public String dependency(String text){
        String output = "";
        Annotation document = new Annotation(text);
        try {
            pipeline.annotate(document);
        }
        catch (Exception e)
        {
            System.out.println("Exception while calling method annotate(Method dependency):"+e);
        }
        List<CoreMap> sentences = document.get(CoreAnnotations.SentencesAnnotation.class);
        for(CoreMap sentence: sentences) {
            TokenizerFactory tokenizerFactory = PTBTokenizer.factory(new CoreLabelTokenFactory(), "");
            List wordList = tokenizerFactory.getTokenizer(new StringReader(sentence.toString())).tokenize();
            //System.out.println(wordList.toString());
            Tree tree = lp.apply(wordList);
            GrammaticalStructure gs = gsf.newGrammaticalStructure(tree);
            Collection cc = gs.typedDependenciesCCprocessed(true);
            output +=cc.toString()+"\n";
        }
        //System.out.println(output);
        return output;
    }

    public static void main(String[] args){
        ParserTagging pt = new ParserTagging();
        //String out1 = pt.postag("This is an automation");
        String out1 = pt.postag("Best performing");
        System.out.println(out1);
        //String out2 = pt.dependency("This is an automation");
        String out2 = pt.dependency("Dasgupta and Ng (2007) improves over (Creutz, 2003) by suggesting a simpler approach.");
        System.out.println(out2);
    }
    
}

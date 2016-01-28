/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.trees.*;

import java.io.*;
import java.util.*;




/**
 *
 * @author Promita
 */
public class PAN_AI_2015 {
    private static String classifier_path;

    String op="\t", temp="", write_excel;
    static String input_path, output_path;
    Read_and_Write rw = new Read_and_Write();
    String content;
    private Properties prop;
    private StanfordCoreNLP pipeline;
    //LexicalizedParser lp = LexicalizedParser.loadModel("W:\\study\\PG course\\project\\PAN\\JU_CSE_PAN\\library\\stanford-corenlp-full-2014-08-27\\stanford-corenlp-3.4.1-models\\edu\\stanford\\nlp\\models\\lexparser\\englishPCFG.ser.gz");
    TreebankLanguagePack tlp = new PennTreebankLanguagePack();
    GrammaticalStructureFactory gsf= tlp.grammaticalStructureFactory();
    private int MAX=200;
    private String yn;
    
     String[] punctuationToWordRatio = new String[100000];
     String[] commaRatio = new String[100000];
     String[] semicolonRatio = new String[100000];
     String[] colonRatio = new String[100000];
     String[] stopRatio = new String[100000];
     String[] questionRatio = new String[100000];
     String[] exclamationRatio = new String[100000];
     String[] slashRatio = new String[100000];
     String[] dashRatio = new String[100000];
     String[] shortsentencebytotal = new String[100000];
     String[] longsentencebytotal = new String[100000];
     String[] uniqueword = new String[100000];
     private String auth_id;
     HashMap<String,Integer> posfreq_hm= new HashMap<>();
     HashMap<String,Integer> bigram_hm= new HashMap<>();
     HashMap<String,Integer> trigram_hm= new HashMap<>();
     HashMap<String, Integer> posseqfreq_hm= new HashMap<>();
    // HashMap<String, Integer> posatbeginnning_hm= new HashMap<>();
     
     HashMap<String,Integer> unknown_posfreq_hm= new HashMap<>();
     HashMap<String,Integer> unknown_bigram_hm= new HashMap<>();
     HashMap<String,Integer> unknown_trigram_hm= new HashMap<>();
     HashMap<String, Integer> unknown_posseqfreq_hm= new HashMap<>();
    // HashMap<String, Integer> unknown_posatbeginnning_hm= new HashMap<>();
     
    
    public PAN_AI_2015(){
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
    
    public static void main(String[] args) throws Exception {
        PAN_AI_2015 pan=new PAN_AI_2015();
        input_path= args[1]; //input path from command line argument
        classifier_path= args[3]; //classifier path from command line argument
        output_path= args[5]; //output path from command line argument
        pan.testTakeInputAllLanguage(input_path);
        
    }
    
   
    void handle_files(String content) throws FileNotFoundException
    {
        int sentencecounter[] = new int[MAX];
        double longsentbytotal,shortsentbytotal;
        String tagged_output,word,pos,wordfreq;
        
        POStag pt=new POStag();
        tagged_output = pt.postag(content);
        pos = pt.getPOSTags(tagged_output);
        
        RootWords rt=new RootWords();
        word = rt.getWords(tagged_output);
       
        WordFrequency wf= new WordFrequency();
        wordfreq = wf.getWordFrequency_toString(word);
        float uniqueword=wf.getUniqueWordRatio(word);
        //System.out.println(wordfreq);
              
        SentenceRatio st= new SentenceRatio();
        sentencecounter = st.countSentences(content,sentencecounter);
        longsentbytotal = st.getLongSentenceRatio(sentencecounter);
        shortsentbytotal = st.getShortSentenceRatio(sentencecounter);
         
        Punctuation p=new Punctuation();
        String punc_out= p.countPunctuationRatio(content);
        
        
        temp +=  punc_out+"\t";
        temp +=  shortsentbytotal+ "\t";
        temp +=  longsentbytotal + "\t";
        temp +=  uniqueword+"\t";
        
        /*POSAtSentenceStart pass= new POSAtSentenceStart();
        String beginofsent = pass.getBeginningofSentence(content);
        //System.out.println(beginofsent);
        
        String posatbeginnning= pass.getPosAtSentenceStart_toString(tagged_output);
        posatbeginnning_hm= pass.getPosAtSentenceStart(tagged_output);
        System.out.println(posatbeginnning); */
        
          
    }
    
    void handle_files_hashmap(String content) throws FileNotFoundException
    {
        int sentencecounter[] = new int[MAX];
        String tagged_output,word,pos,wordfreq,posfreq,posseqfreq,bigrams,trigrams;
        
        POStag pt=new POStag();
        tagged_output = pt.postag(content);
        pos = pt.getPOSTags(tagged_output);
        
        RootWords rt=new RootWords();
        word = rt.getWords(tagged_output);
       
        WordFrequency wf= new WordFrequency();
        //wordfreq = wf.getWordFrequency_toString(word);
        //float uniqueword=wf.getUniqueWordRatio(word);
        //System.out.println(wordfreq);
        
        POSfrequency pf= new POSfrequency();
        //posfreq = pf.getPosFrequency_toString(pos);
        posfreq_hm = pf.getPosFrequency(pos);
        //System.out.println(posfreq);
        
        POSsequence seq= new POSsequence();
        //posseqfreq= seq.getPosSeqFrequency_toString(pos);
        posseqfreq_hm= seq.getPosSeqFrequency(pos);
        
        Bigrams bg= new Bigrams();
        //bigrams = bg.getBigrams_toString(word);
        bigram_hm = bg.getBigrams(word);
        
        Trigrams tg= new Trigrams();
        //trigrams = tg.getTrigrams_toString(word);
        trigram_hm = tg.getTrigrams(word);
             
        /*POSAtSentenceStart pass= new POSAtSentenceStart();
        String beginofsent = pass.getBeginningofSentence(content);
        //System.out.println(beginofsent);
        
        String posatbeginnning= pass.getPosAtSentenceStart_toString(tagged_output);
        posatbeginnning_hm= pass.getPosAtSentenceStart(tagged_output);
        System.out.println(posatbeginnning); */
        
          
    }
    
    char checkAnswer(String auth_id)
    {
        char answer=yn.charAt(yn.indexOf(auth_id)+6);
        return answer;
    }
    
    float getAverage(String[] array, int count)
    {
        int i;
        float sum=0, result;
        for(i=0;i<count-1;i++){
            Float diff= Float.parseFloat(array[i])-Float.parseFloat(array[count-1]);
            sum+= diff;
        }
        result=sum/i;
        return result;
    }
    
    void testTakeInputAllLanguage(String input_path) throws FileNotFoundException, IOException, Exception
    {   //redirect to each language folder
      File dir = new File(input_path);
        String[] children = dir.list();
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory");
        }
        else {
            boolean flag= false;
            for (int i=0; i<children.length; i++) {
                String file_name = children[i];
                String check=file_name.substring(file_name.length()-4);
                if(check.equals("json")){
                        flag=true;
                        break;
                     }
            }
            if (flag==false)
            {
                for (int i=0; i<children.length; i++) {
                    String subdir = children[i];
                    String new_addr=input_path+"\\"+subdir;
                    testTakeInputSingleLanguage(new_addr);
                }
            }
            else
            {
                testTakeInputSingleLanguage(input_path);
            }
        }
        Weka weka= new Weka();
        weka.classifyTestSet(op, output_path, output_path);
        op="";
    }
    
    void testTakeInputSingleLanguage(String input_path) throws FileNotFoundException, IOException, Exception
    {
        String language="";
        File dir = new File(input_path);
        String[] children = dir.list();
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory");
        }
        else {
            for (int i=0; i<children.length; i++) {
                String file_name = children[i];
                String check=file_name.substring(file_name.length()-4);
                if(check.equals("json")){
                    if(file_name.equals("contents.json"))
                    {
                        ReadJSON rj= new ReadJSON();
                        language= rj.readJson(input_path+"\\"+file_name);
                        System.out.println(language);
                    }
                }
                else if(check.equals(".txt")){
                     continue;
                }
              }
            for (int i=0; i<children.length; i++) {
                String file_name = children[i];
                String check=file_name.substring(file_name.length()-4);
                if(check.equals("json"))
                    continue;
                else if(check.equals(".txt"))
                    continue;
                else
                    testTakeInput(language, input_path+"\\"+file_name);
            }
        }
    }
   
    void testTakeInput(String language, String input_path) throws FileNotFoundException, IOException, Exception
    {   //Read the contents of .txt files
        
        write_excel="";
        int i;
        File dir = new File(input_path);
        String[] children = dir.list();
        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory");
        }
        else {
                String file_name = children[children.length-1];
                String adrs=input_path+"\\"+file_name;
                content = rw.read_file(adrs).toLowerCase();
                handle_files_hashmap(content);
                
                unknown_posfreq_hm= posfreq_hm;
                unknown_bigram_hm= bigram_hm;
                unknown_trigram_hm= trigram_hm;
                unknown_posseqfreq_hm= posseqfreq_hm;
                //unknown_posatbeginnning_hm= posatbeginnning_hm;
                
                int posfreq_sum=0;
                int bigram_sum=0;
                int trigram_sum=0;
                int posseqfreq_sum=0;
                //int posatbeginning_sum=0;
            for (i=0; i<children.length-1; i++) {
                file_name = children[i];
                adrs=input_path+"\\"+file_name;
                content = rw.read_file(adrs).toLowerCase();
                handle_files_hashmap(content);
                handle_files(content);
                CompareHashMap chm= new CompareHashMap();
                posfreq_sum+=chm.compareHashmaps(unknown_posfreq_hm,posfreq_hm);
                bigram_sum+=chm.compareHashmaps(unknown_bigram_hm,bigram_hm);
                trigram_sum+=chm.compareHashmaps(unknown_trigram_hm,trigram_hm);
                posseqfreq_sum+=chm.compareHashmaps(unknown_posseqfreq_hm,posseqfreq_hm);
              //  posatbeginning_sum+=chm.compareHashmaps(unknown_posatbeginnning_hm,posatbeginnning_hm);
               }
            posfreq_sum= posfreq_sum/(children.length -1);
            bigram_sum= bigram_sum/(children.length -1);
            trigram_sum= trigram_sum/(children.length -1);
            posseqfreq_sum= posseqfreq_sum/(children.length -1);
           // posatbeginning_sum= posatbeginning_sum/(children.length -1);
                file_name = children[i];
                adrs=input_path+"\\"+file_name;
                content = rw.read_file(adrs).toLowerCase();
                handle_files(content);
            StringTokenizer tokenizer = new StringTokenizer(temp,"\t");
            for(i=0; i<children.length; i++)
                {
                punctuationToWordRatio[i]=tokenizer.nextToken();
                commaRatio[i]=tokenizer.nextToken();
                semicolonRatio[i]=tokenizer.nextToken();
                colonRatio[i]=tokenizer.nextToken();
                stopRatio[i]=tokenizer.nextToken();
                questionRatio[i]=tokenizer.nextToken();
                exclamationRatio[i]=tokenizer.nextToken();
                slashRatio[i]=tokenizer.nextToken();
                dashRatio[i]=tokenizer.nextToken();
                shortsentencebytotal[i]=tokenizer.nextToken();
                longsentencebytotal[i]=tokenizer.nextToken();
                uniqueword[i]=tokenizer.nextToken();
                } 
            
            
            op+= language+"\t";
            System.out.println(language);
            op+= getAverage(punctuationToWordRatio, i) +"\t";
            op+= getAverage(commaRatio, i)+"\t";
            op+= getAverage(semicolonRatio, i)+"\t";
            op+= getAverage(colonRatio, i)+"\t";
            op+= getAverage(stopRatio, i)+"\t";
            op+= getAverage(questionRatio, i)+"\t";
            op+= getAverage(exclamationRatio, i)+"\t";
            op+= getAverage(slashRatio, i)+"\t";
            op+= getAverage(dashRatio, i)+"\t";
            op+= getAverage(shortsentencebytotal, i)+"\t";
            op+= getAverage(longsentencebytotal, i)+"\t";
            op+= getAverage(uniqueword, i)+"\t";
            
            op+= posfreq_sum+"\t";
            op+= bigram_sum+"\t";
            op+= trigram_sum+"\t";
            op+= posseqfreq_sum+"\t";
            
            auth_id=input_path.substring(input_path.length()-5);
            op+= "Y"+"\t";
            op+= auth_id+"\t";
            temp="";
            /* write_excel+=op;
            op=" "; */
    }
        /*String excel_file=output_path+"\\\\compareHM.xls";
        System.out.println(excel_file);
        saveToExcelFile s= new saveToExcelFile();
        s.saveFile(auth_id, excel_file, write_excel);*/
        
        
}
}

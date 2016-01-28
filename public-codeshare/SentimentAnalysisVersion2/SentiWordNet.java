/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SentimentAnalysisVersion2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by Souvick on 19-10-2014.
 */

public class SentiWordNet {
    private final String pathToSWN;
    private HashMap<String, String> _dict;
    private HashMap<String, Double> sc;
    @SuppressWarnings("UseOfObsoleteCollectionType")
    private HashMap<String, Vector<Double>> _temp ;

    @SuppressWarnings({"Convert2Diamond", "UseOfObsoleteCollectionType", "CallToThreadDumpStack", "UseSpecificCatch", "BroadCatchBlock", "TooBroadCatch"})
    public SentiWordNet(){
        pathToSWN = "Z:\\Research\\NLP Repository\\SentiWordNet_3.0.0.txt";
        _dict = new HashMap<String, String>();
        _temp = new HashMap<String, Vector<Double>>();
        sc = new HashMap<String, Double>();
        try{
            BufferedReader csv =  new BufferedReader(new FileReader(pathToSWN));
            @SuppressWarnings("UnusedAssignment")
            String line = "";
            while((line = csv.readLine()) != null)
            {
                String[] data = line.split("\t");
                //System.out.println(data[2]+"\t"+data[3]);
                //System.out.println(line);
                Double score = Double.valueOf(data[2])-Double.valueOf(data[3]);
                String[] words = data[4].split(" ");
                for(String w:words)
                {
                    String[] w_n = w.split("#");
                    w_n[0] += "#"+data[0];
                    int index = Integer.parseInt(w_n[1])-1;
                    if(_temp.containsKey(w_n[0]))
                    {
                        Vector<Double> v = _temp.get(w_n[0]);
                        if(index>v.size())
                            for(int i = v.size();i<index; i++)
                                v.add(0.0);
                        v.add(index, score);
                        _temp.put(w_n[0], v);
                    }
                    else
                    {
                        Vector<Double> v = new Vector<Double>();
                        for(int i = 0;i<index; i++)
                            v.add(0.0);
                        v.add(index, score);
                        _temp.put(w_n[0], v);
                    }
                }
            }
            Set<String> temp = _temp.keySet();
            for (String word : temp) {
                Vector<Double> v = _temp.get(word);
                double score = 0.0;
                double sum = 0.0;
                for(int i = 0; i < v.size(); i++)
                    score += ((double)1/(double)(i+1))*v.get(i);
                for(int i = 1; i<=v.size(); i++)
                    sum += (double)1/(double)i;
                score /= sum;
                sc.put(word,new Double(score));
                @SuppressWarnings("UnusedAssignment")
                String sent = "";
                if(score>=0.75)
                    sent = "strong_positive";
                else if(score > 0.25 && score<=0.5)
                    sent = "positive";
                else if(score > 0 && score>=0.25)
                    sent = "weak_positive";
                else if(score < 0 && score>=-0.25)
                    sent = "weak_negative";
                else if(score < -0.25 && score>=-0.5)
                    sent = "negative";
                else if(score<=-0.75)
                    sent = "strong_negative";
                else
                    sent = "none";
                _dict.put(word, sent);
            }
        }
        catch(Exception e){e.printStackTrace();}
    }

    public double getScore(String word, String pos){
        if(sc.get(word+'#'+pos)!=null)
            return sc.get(word+"#"+pos).doubleValue();
        else {//System.out.println("S");
            return 0.0;}
    }

    public int isPresent(String word, String pos){
        if(sc.get(word+'#'+pos)!=null)
            return 1;
        else {//System.out.println("S");
            return 0;
        }
    }
    
    @SuppressWarnings({"BroadCatchBlock", "TooBroadCatch", "ConvertToTryWithResources"})
    public void writeToFile(String str,String path){
        try{
            PrintWriter out = new PrintWriter(path);
            out.println(str);
            out.close();
        }
        catch(Exception e){System.out.println(e);}
    }

    @SuppressWarnings("UnusedAssignment")
    public String obtainScore(String input){
        String op = "",pos="",word="";
        double score = 0.0;
        try{
            Scanner scanner = new Scanner(input);
            while(scanner.hasNext()){
                String posword = scanner.next();
                String[] parts = posword.split("/");
                word = parts[1];
                pos = parts[2].substring(0,1).toLowerCase();
                score = getScore(word,pos);
                if (score!=0)
                    op += word + "#" + pos + "\t\t\t\t\t\t"+ score + "\n";
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        writeToFile(op,"Z:\\Research\\Citation Sentiment Analysis\\Output\\op.txt");
        return op;
    }

    @SuppressWarnings("UnusedAssignment")
    public String obtainPresence(String input){
        String op = "",pos="",word="";
        try{
            Scanner scanner = new Scanner(input);
            while(scanner.hasNext()){
                String posword = scanner.next();
                String[] parts = posword.split("/");
                word = parts[1];
                pos = parts[2].substring(0,1).toLowerCase();
                int presence = isPresent(word,pos);
                if (presence==1)
                    op += word + "#" + pos + "\t\t\t\t\t\t"+ presence + "\n";
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return op;
    }

    public static void main(String[] args) {
        SentiWordNet swn = new SentiWordNet();
        //swn.obtainScore("inducing/induce/VBG syntactic/syntactic/JJ categories/category/NNS");
        swn.obtainScore("Dasgupta/Dasgupta/NNP and/and/CC Ng/Ng/NNP -LRB-/-lrb-/-LRB- 2007/2007/CD -RRB-/-rrb-/-RRB- improves/improve/VBZ over/over/IN -LRB-/-lrb-/-LRB- Creutz/Creutz/NNP ,/,/, 2003/2003/CD -RRB-/-rrb-/-RRB- by/by/IN suggesting/suggest/VBG a/a/DT simpler/simpler/JJR approach/approach/NN ././.");
    }
}

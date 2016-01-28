/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Souvick
 */
public class SentenceExtractionv2 {
    String path = "";
    
    // Constructor
    // Uses a JFileChiiser to open a input file and set the path
    public SentenceExtractionv2(){
        JFileChooser chooser = new JFileChooser();
        File homeDir = new File("Z:\\Research\\Citation Sentiment Analysis\\Corpus\\Annotated\\");
        chooser.setCurrentDirectory(homeDir);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text and Xml Files", "txt", "xml");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Open input file");
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
            System.out.println(chooser.getSelectedFile().toString());            
        } else {
            System.out.println("No Selection ");
        }
    }
    
    // Extracts various window features based on tag properties
    public void extractWindow(Statistics stat){
        Scanner scanner;
        String input = "";
        int count = 0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                count+=1;
                String newline = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(newline, "\t");
                String source = st.nextToken();
                String target = st.nextToken();                
                String msent = st.nextToken();
                String sentnc = st.nextToken();
                if(sentnc.length()<700){
                    stat.id[stat.globalcounter] = count;
                    stat.source[stat.globalcounter] = source;
                    stat.target[stat.globalcounter] = target;
                    stat.msentiment[stat.globalcounter] = msent;
                    stat.sentence[stat.globalcounter] = sentnc;
                    stat.globalcounter++;
                }
                else{
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        }                
    }
    
    public void getSentiment(Statistics stat){
        for(int i = 0; i < stat.globalcounter; i++){
            if (stat.msentiment[i].equalsIgnoreCase("o"))
                stat.sent[i] = 0;
            else if (stat.msentiment[i].equalsIgnoreCase("p"))
                stat.sent[i] = 1;
            else if (stat.msentiment[i].equalsIgnoreCase("n"))
                stat.sent[i] = -1;
            else
                stat.sent[i] = 0;     
        }
    }
    
    public static void main(String[] args){
        Statistics stat = new Statistics();
        SentenceExtractionv2 se2 = new SentenceExtractionv2();
        se2.extractWindow(stat);
        se2.getSentiment(stat);
        System.out.println("Source-->Target:Sentiment");
        for(int i=0 ; i<stat.globalcounter; i++){
            System.out.print(stat.source[i]+"-->"+stat.target[i]+":"+stat.sent[i]);
            if(stat.sentence[i].equalsIgnoreCase("Error!!")) System.out.print("-->Error");
            else System.out.println("");
        }            
    }
}

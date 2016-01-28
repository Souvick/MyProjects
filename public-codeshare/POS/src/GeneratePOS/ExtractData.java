package GeneratePOS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;


/**
 *
 * @author Souvick
 */
public class ExtractData {
    
    String path = "";
    
    // Constructor
    // Uses a JFileChiiser to open a input file and set the path
    public ExtractData(){
        JFileChooser chooser = new JFileChooser();
        File homeDir = new File("F:\\LEX\\");
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
    
    public String getData() throws IOException{
        ReaderWriter rw = new ReaderWriter();
        String input = rw.readFile("F:\\POS_Contest\\POS_Contest\\","result1","txt");
        return input;
    }
    
    // Extracts data
    public void extractWindow(VariablesList vl){
        Scanner scanner;
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="", s9="", s10="", s11="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>1)
                {
                    count+=1;
                    String word = st.nextToken();
                    String post = st.nextToken();
                    //System.out.println(word+"\t"+post);
                    vl.word[vl.globalcounter] = word;
                    vl.post[vl.globalcounter] = post;
                    if(post.startsWith("N")){
                        s1+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("V")){
                        s2+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("J")|| post.startsWith("ADJ")){
                        s3+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("D")){
                        s4+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("P")){
                        s5+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("R")|| post.startsWith("ADV")){
                        s6+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("Q")){
                        s7+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("U")){
                        s8+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("C")){
                        s9+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("X")){
                        s10+=word+"\t"+post+"\n";
                    }
                    else if(post.startsWith("$")){
                        s11+=word+"\t"+post+"\n";
                    }
                    else{
                        System.out.println(word+"\t"+post);
                        System.out.println("Error");
                    }
                    vl.globalcounter++;                
                }                              
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\", "Noun", "txt");
        rw.writeToFile(s2, "F:\\", "Verb", "txt");
        rw.writeToFile(s3, "F:\\", "Adjective", "txt");
        rw.writeToFile(s4, "F:\\", "Determiner", "txt");
        rw.writeToFile(s5, "F:\\", "Pronoun", "txt");
        rw.writeToFile(s6, "F:\\", "Adverb", "txt");
        rw.writeToFile(s7, "F:\\", "Q", "txt");
        rw.writeToFile(s8, "F:\\", "U", "txt");
        rw.writeToFile(s9, "F:\\", "Conj", "txt");
        rw.writeToFile(s10, "F:\\", "X", "txt");
        rw.writeToFile(s11, "F:\\", "Dollar", "txt");
                
    }
    
    public static void main(String args[]) throws IOException
    {
        VariablesList vl = new VariablesList();
        ExtractData ed = new ExtractData();
        ed.extractWindow(vl);   
    }
}

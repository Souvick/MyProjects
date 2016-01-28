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
public class EnglishExt {
    
    String path = "";
    
    // Constructor
    // Uses a JFileChiiser to open a input file and set the path
    public EnglishExt(){
        JFileChooser chooser = new JFileChooser();
        File homeDir = new File("F:\\POS_Contest\\POS_Contest\\");
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
    
    public void extractWindow(VariablesList vl){
        if(path.contains("BN_EN"))
        {
            extractWindow_file1(vl);
        }
        else if(path.contains("HI_EN"))
        {
            extractWindow_file2(vl);
        }
        else if(path.contains("TA_EN"))
        {
            extractWindow_file3(vl);
        }
        else{
            System.out.println("Error");
        }
    }
    
    // Extracts data
    public void extractWindow_file1(VariablesList vl){
        Scanner scanner;
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>2)
                {
                    count+=1;
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    String post = st.nextToken();
                    //System.out.println(word+"\t"+lang+"\t"+post);
                    vl.word[vl.globalcounter] = word;
                    vl.lang[vl.globalcounter] = lang;
                    vl.post[vl.globalcounter] = post;
                    if(lang.equalsIgnoreCase("en")){
                        s1+=word+"\t"+post+"\n";
                        flag = 0;//
                    }
                    else if(lang.equalsIgnoreCase("bn")){
                        s2+=word+"\t"+post+"\n";
                        //flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("ne")){
                        s3+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("univ")){
                        s4+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("hi")){
                        s5+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("acro")){
                        s6+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("ne+en_suffix")){
                        s1+=word+"\t"+post+"\n";
                        s3+=word+"\t"+post+"\n";
                        flag = 0;//
                    }
                    else if(lang.equalsIgnoreCase("undef")){
                        s7+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("ne+bn_suffix")){
                        s2+=word+"\t"+post+"\n";
                        s3+=word+"\t"+post+"\n";
                        //flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("acro+en_suffix")){
                        s6+=word+"\t"+post+"\n";
                        s1+=word+"\t"+post+"\n";
                        flag = 0;//
                    }
                    else if(lang.equalsIgnoreCase("en+bn_suffix")){
                        s1+=word+"\t"+post+"\n";
                        s2+=word+"\t"+post+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("acro+bn_suffix")){
                        s6+=word+"\t"+post+"\n";
                        s2+=word+"\t"+post+"\n";
                        //flag = 0;
                    }
                    else{
                        System.out.println(word+"\t"+lang+"\t"+post);
                        System.out.println("Error");
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s1+="\n";//
                        //s2+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\", "English", "txt");
        //rw.writeToFile(s2, "F:\\", "Bengali", "txt");
        //rw.writeToFile(s3, "F:\\", "NE", "txt");
        //rw.writeToFile(s4, "F:\\", "Univ", "txt");
        //rw.writeToFile(s5, "F:\\", "Hindi", "txt");
        //rw.writeToFile(s6, "F:\\", "Acronym", "txt");
        //rw.writeToFile(s7, "F:\\", "Undef", "txt");
    }
    
    // Extracts data
    public void extractWindow_file2(VariablesList vl){
        Scanner scanner;
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>2)
                {
                    count+=1;
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    String post = st.nextToken();
                    //System.out.println(word+"\t"+lang+"\t"+post);
                    vl.word[vl.globalcounter] = word;
                    vl.lang[vl.globalcounter] = lang;
                    vl.post[vl.globalcounter] = post;
                    if(lang.equalsIgnoreCase("en")){
                        s1+=word+"\t"+post+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("bn")){
                        s2+=word+"\t"+post+"\n";                        
                    }
                    else if(lang.equalsIgnoreCase("ne")){
                        s3+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("univ")){
                        s4+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("hi")){
                        s5+=word+"\t"+post+"\n";
                        //flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("acro")){
                        s6+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("ne+en_suffix")){
                        s1+=word+"\t"+post+"\n";
                        s3+=word+"\t"+post+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("undef")){
                        s7+=word+"\t"+post+"\n";
                    }
                    else if(lang.equalsIgnoreCase("ne+bn_suffix")){
                        s2+=word+"\t"+post+"\n";
                        s3+=word+"\t"+post+"\n";
                        //flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("acro+en_suffix")){
                        s6+=word+"\t"+post+"\n";
                        s1+=word+"\t"+post+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("en+bn_suffix")){
                        s1+=word+"\t"+post+"\n";
                        s2+=word+"\t"+post+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("mixed")){
                        s8+=word+"\t"+post+"\n";
                    }
                    //else if(lang.equalsIgnoreCase("")){
                     //   s6+=word+"\t"+post+"\n";
                      //  s2+=word+"\t"+post+"\n";
                    //}
                    else{
                        System.out.println(word+"\t"+lang+"\t"+post);
                        System.out.println("Error");
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s1+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\", "English", "txt");
        //rw.writeToFile(s2, "F:\\", "Bengali", "txt");
        //rw.writeToFile(s3, "F:\\", "NE", "txt");
        //rw.writeToFile(s4, "F:\\", "Univ", "txt");
        //rw.writeToFile(s5, "F:\\", "Hindi", "txt");
        //rw.writeToFile(s6, "F:\\", "Acronym", "txt");
        //rw.writeToFile(s7, "F:\\", "Undef", "txt");
        //rw.writeToFile(s8, "F:\\", "Mixed", "txt");
    }
    
    public void extractWindow_file3(VariablesList vl){
        Scanner scanner;
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="";
        int count = 0, flag =0, linenum=0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                linenum++;
                String newline = scanner.nextLine();
                StringTokenizer st = new StringTokenizer(newline, " ");
                //System.out.println(st.countTokens());
                if(st.countTokens()>1)
                {
                    count+=1;
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    vl.word[vl.globalcounter] = word;
                    vl.lang[vl.globalcounter] = lang;
                    if(lang.equalsIgnoreCase("E")){
                        s1+=word+"\n";
                        flag = 0;
                    }
                    else if(lang.equalsIgnoreCase("T")){
                        s2+=word+"\n";                        
                    }
                    else if(lang.equalsIgnoreCase("N")){
                        s3+=word+"\n";
                    }
                    else if(lang.equalsIgnoreCase("R")){
                        s4+=word+"\n";
                    }
                    else if(lang.equalsIgnoreCase("M")){
                        s5+=word+"\n";                        
                    }
                    else{
                        System.out.println(linenum+":"+lang);
                        System.out.println(word+"\t"+lang);
                        //System.out.println("Error");
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s1+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\", "English3", "txt");
        //rw.writeToFile(s2, "F:\\", "Tamil", "txt");
        //rw.writeToFile(s3, "F:\\", "NE", "txt");
        //rw.writeToFile(s4, "F:\\", "Univ", "txt");
        //rw.writeToFile(s5, "F:\\", "Mixed", "txt");        
    }
    
    public static void main(String args[]) throws IOException
    {
        VariablesList vl = new VariablesList();
        //ExtractData ed = new ExtractData();
        //ed.extractWindow(vl);   
        EnglishExt ee = new EnglishExt();
        ee.extractWindow(vl);
    }
}

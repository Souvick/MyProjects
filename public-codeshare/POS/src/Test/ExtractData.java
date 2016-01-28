package Test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        File homeDir = new File("F:\\Test\\");
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
        Tagger_BN_EN tbe = new Tagger_BN_EN();
        
        HashMap<String,String> hm_acro = new HashMap<>();
        HashMap<String,String> hm_ne = new HashMap<>();
        HashMap<String,String> hm_undef = new HashMap<>();
        HashMap<String,String> hm_univ = new HashMap<>();
        HashMap<String,String> hm_other = new HashMap<>();
        HashMap<String,String> hm_en = new HashMap<>();
        HashMap<String,String> hm_bn = new HashMap<>();
        HashMap<String,String> hm_hi = new HashMap<>();
        
        try {
            hm_en = tbe.createHash_EN();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_bn = tbe.createHash_BN();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_hi = tbe.createHash_HI();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_acro = tbe.createHash_Acronym();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_ne = tbe.createHash_NE();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_undef = tbe.createHash_Undef();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_univ = tbe.createHash_Univ();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_other = tbe.createHash_Other();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="",s9="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                count+=1;
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>1)
                {
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    String post;
                    //System.out.println(word+"\t"+lang+"\t"+post);
                    vl.word[count] = word;
                    vl.lang[count] = lang;
                    
                    if(lang.equalsIgnoreCase("en")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s1+=count+"\t"+word+"\tXXX\n";
                        } 
                    }
                    else if(lang.equalsIgnoreCase("bn")){
                        if(hm_bn.containsKey(word)){
                            post = hm_bn.get(word);
                            s2+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s2+=count+"\t"+word+"\tXXX\n";
                            flag = 0;
                        } 
                    }
                    else if(lang.equalsIgnoreCase("ne")){
                        if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s3+=count+"\t"+word+"\tN_NNP\n";
                        }                        
                    }
                    else if(lang.equalsIgnoreCase("univ")){
                        if(hm_univ.containsKey(word)){
                            post = hm_univ.get(word);
                            s4+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s4+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        }  
                    }
                    else if(lang.equalsIgnoreCase("hi")){
                        if(hm_hi.containsKey(word)){
                            post = hm_hi.get(word);
                            s5+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s5+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("acro")){
                        if(hm_acro.containsKey(word)){
                            post = hm_acro.get(word);
                            s6+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s6+=count+"\t"+word+"\tN_NNP\n";
                            System.out.println(count+"\t"+word);
                        }  
                    }
                    else if(lang.equalsIgnoreCase("ne+en_suffix")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s3+=count+"\t"+word+"\tN_NNP\n";
                            //s1+=count+"\t"+word+"\n";
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("undef")){
                        if(hm_undef.containsKey(word)){
                            post = hm_ne.get(word);
                            s7+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s7+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        }  
                    }
                    else if(lang.equalsIgnoreCase("ne+bn_suffix")){
                        if(hm_bn.containsKey(word)){
                            post = hm_bn.get(word);
                            s2+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            //s2+=count+"\t"+word+"\n";
                            //flag = 0;
                            s3+=count+"\t"+word+"\tN_NNP\n";
                            System.out.println(count+"\t"+word);
                        }                         
                    }
                    else if(lang.equalsIgnoreCase("acro+en_suffix")){
                        if(hm_acro.containsKey(word)){
                            post = hm_acro.get(word);
                            s6+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s6+=count+"\t"+word+"\tN_NNP\n";
                            //s1+=count+"\t"+word+"\n";
                            System.out.println(count+"\t"+word);
                        }
                    }
                    else if(lang.equalsIgnoreCase("en+bn_suffix")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_bn.containsKey(word)){
                            post = hm_bn.get(word);
                            s2+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s1+=count+"\t"+word+"\tXXX\n";
                            //s2+=count+"\t"+word+"\n";
                            //flag = 0;
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("acro+bn_suffix")){
                        if(hm_acro.containsKey(word)){
                            post = hm_acro.get(word);
                            s6+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_bn.containsKey(word)){
                            post = hm_bn.get(word);
                            s2+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s6+=count+"\t"+word+"\tN_NNP\n";
                            //s2+=count+"\t"+word+"\n";
                            //flag = 0;
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else{
                        //System.out.println(word+"\t"+lang);
                        //System.out.println("Error");
                        //s8+=count+"\t"+word+"\n";
                        if(hm_other.containsKey(word)){
                            post = hm_other.get(word);
                            s8+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s8+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s2+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\TR1\\BN_EN\\", "English", "txt");
        rw.writeToFile(s2, "F:\\TR1\\BN_EN\\", "Bengali", "txt");
        rw.writeToFile(s3, "F:\\TR1\\BN_EN\\", "NE", "txt");
        rw.writeToFile(s4, "F:\\TR1\\BN_EN\\", "Univ", "txt");
        rw.writeToFile(s5, "F:\\TR1\\BN_EN\\", "Hindi", "txt");
        rw.writeToFile(s6, "F:\\TR1\\BN_EN\\", "Acronym", "txt");
        rw.writeToFile(s7, "F:\\TR1\\BN_EN\\", "Undef", "txt");
        rw.writeToFile(s8, "F:\\TR1\\BN_EN\\", "Other", "txt");
        
        PrepareResults pr = new PrepareResults();
        String op="";
        try {
            op = pr.prepareTextFile(vl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        rw.writeToFile(op, "F:\\", "Output_BN_EN", "txt");
        
    }
    
    // Extracts data
    public void extractWindow_file2(VariablesList vl){
        Scanner scanner;
        Tagger_HI_EN the = new Tagger_HI_EN();
        
        HashMap<String,String> hm_acro = new HashMap<>();
        HashMap<String,String> hm_ne = new HashMap<>();
        HashMap<String,String> hm_undef = new HashMap<>();
        HashMap<String,String> hm_univ = new HashMap<>();
        HashMap<String,String> hm_mixed = new HashMap<>();
        HashMap<String,String> hm_en = new HashMap<>();
        HashMap<String,String> hm_hi = new HashMap<>();
        
        try {
            hm_en = the.createHash_EN();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_hi = the.createHash_HI();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_acro = the.createHash_Acronym();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_ne = the.createHash_NE();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_undef = the.createHash_Undef();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_univ = the.createHash_Univ();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_mixed = the.createHash_Mixed();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="",s9="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                count+=1;
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>1)
                {
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    String post;
                    //System.out.println(word+"\t"+lang+"\t"+post);
                    vl.word[count] = word;
                    vl.lang[count] = lang;
                    
                    if(lang.equalsIgnoreCase("en")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s1+=count+"\t"+word+"\tXXX\n";
                        } 
                    }
                    else if(lang.equalsIgnoreCase("ne")){
                        if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s3+=count+"\t"+word+"\tN_NNP\n";
                        }                        
                    }
                    else if(lang.equalsIgnoreCase("univ")){
                        if(hm_univ.containsKey(word)){
                            post = hm_univ.get(word);
                            s4+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            if(word.contains("#"))
                                s4+=count+"\t"+word+"\t#\n";
                            else{
                                s4+=count+"\t"+word+"\tXXX\n";
                                System.out.println(count+"\t"+word);
                            }
                        }  
                    }
                    else if(lang.equalsIgnoreCase("hi")){
                        if(hm_hi.containsKey(word)){
                            post = hm_hi.get(word);
                            s5+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s5+=count+"\t"+word+"\tXXX\n";
                            flag=0;
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("acro")){
                        if(hm_acro.containsKey(word)){
                            post = hm_acro.get(word);
                            s6+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s6+=count+"\t"+word+"\tN_NNP\n";
                            System.out.println(count+"\t"+word);
                        }  
                    }
                    else if(lang.equalsIgnoreCase("ne+en_suffix")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s3+=count+"\t"+word+"\tN_NNP\n";
                            //s1+=count+"\t"+word+"\n";
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("undef")){
                        if(hm_undef.containsKey(word)){
                            post = hm_undef.get(word);
                            s7+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s7+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        }  
                    }
                    else if(lang.equalsIgnoreCase("acro+en_suffix")){
                        if(hm_acro.containsKey(word)){
                            post = hm_acro.get(word);
                            s6+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s6+=count+"\t"+word+"\tN_NNP\n";
                            //s1+=count+"\t"+word+"\n";
                            System.out.println(count+"\t"+word);
                        }
                    }
                    else if(lang.equalsIgnoreCase("mixed")){
                        if(hm_mixed.containsKey(word)){
                            post = hm_mixed.get(word);
                            s8+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s8+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        }
                    }
                    else{
                        s8+=count+"\t"+word+"\tXXX\n";
                        System.out.println(count+"\t"+word);                        
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s2+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\TR1\\HI_EN\\", "English", "txt");
        //rw.writeToFile(s2, "F:\\TR1\\HI_EN\\", "Bengali", "txt");
        rw.writeToFile(s3, "F:\\TR1\\HI_EN\\", "NE", "txt");
        rw.writeToFile(s4, "F:\\TR1\\HI_EN\\", "Univ", "txt");
        rw.writeToFile(s5, "F:\\TR1\\HI_EN\\", "Hindi", "txt");
        rw.writeToFile(s6, "F:\\TR1\\HI_EN\\", "Acronym", "txt");
        rw.writeToFile(s7, "F:\\TR1\\HI_EN\\", "Undef", "txt");
        rw.writeToFile(s8, "F:\\TR1\\HI_EN\\", "Mixed", "txt");
        
        PrepareResults_HI pr = new PrepareResults_HI();
        String op="";
        try {
            op = pr.prepareTextFile(vl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        rw.writeToFile(op, "F:\\", "Output_HI_EN", "txt");
        
    }
    
   // Extracts data
    public void extractWindow_file3(VariablesList vl){
        Scanner scanner;
        Tagger_TA_EN tte = new Tagger_TA_EN();
        
        HashMap<String,String> hm_ne = new HashMap<>();
        HashMap<String,String> hm_univ = new HashMap<>();
        HashMap<String,String> hm_mixed = new HashMap<>();
        HashMap<String,String> hm_en = new HashMap<>();
        HashMap<String,String> hm_ta = new HashMap<>();
        HashMap<String,String> hm_all = new HashMap<>();
        
        try {
            hm_en = tte.createHash_EN();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_ta = tte.createHash_TA();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_ne = tte.createHash_NE();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_univ = tte.createHash_Univ();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_mixed = tte.createHash_Mixed();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            hm_all = tte.createHash_All();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="",s9="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                count+=1;
                StringTokenizer st = new StringTokenizer(newline, "\t");
                if(st.countTokens()>1)
                {
                    String word = st.nextToken();
                    String lang = st.nextToken(); 
                    String post;
                    //System.out.println(word+"\t"+lang+"\t"+post);
                    vl.word[count] = word;
                    vl.lang[count] = lang;
                    
                    if(lang.equalsIgnoreCase("E")){
                        if(hm_en.containsKey(word)){
                            post = hm_en.get(word);
                            s1+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s1+=count+"\t"+word+"\tXXX\n";
                        } 
                    }
                    else if(lang.equalsIgnoreCase("T")){
                        if(hm_ta.containsKey(word)){
                            post = hm_ta.get(word);
                            s2+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s2+=count+"\t"+word+"\tXXX\n";
                            flag=0;
                            System.out.println(count+"\t"+word);
                        } 
                    }
                    else if(lang.equalsIgnoreCase("N")){
                        if(hm_ne.containsKey(word)){
                            post = hm_ne.get(word);
                            s3+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            System.out.println(count+"\t"+word);
                            s3+=count+"\t"+word+"\tN_NNP\n";
                        }                        
                    }
                    else if(lang.equalsIgnoreCase("R")){
                        if(hm_univ.containsKey(word)){
                            post = hm_univ.get(word);
                            s4+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            if(word.contains("#"))
                                s4+=count+"\t"+word+"\t#\n";
                            else if(word.matches(".*\\d+.*")){
                                s4+=count+"\t"+word+"\tQT_QTO\n";
                            }
                            else{
                                s4+=count+"\t"+word+"\tXXX\n";
                                System.out.println(count+"\t"+word);
                            }
                        }  
                    }                    
                    else if(lang.equalsIgnoreCase("M")){
                        if(hm_mixed.containsKey(word)){
                            post = hm_mixed.get(word);
                            s5+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s5+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);
                        }
                    }
                    else{
                        if(hm_all.containsKey(word)){
                            post = hm_mixed.get(word);
                            s5+=count+"\t"+word+"\t"+post+"\n";
                        }
                        else{
                            s5+=count+"\t"+word+"\tXXX\n";
                            System.out.println(count+"\t"+word);                        
                        }                        
                    }
                    vl.globalcounter++;                
                }
                else{
                    if (flag==0)
                        s2+="\n";
                    flag =1;
                }                
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\TR1\\TA_EN\\", "English", "txt");
        rw.writeToFile(s2, "F:\\TR1\\TA_EN\\", "Tamil", "txt");
        rw.writeToFile(s3, "F:\\TR1\\TA_EN\\", "NE", "txt");
        rw.writeToFile(s4, "F:\\TR1\\TA_EN\\", "Univ", "txt");
        rw.writeToFile(s5, "F:\\TR1\\TA_EN\\", "Mixed", "txt");
        
        PrepareResults_TA pr = new PrepareResults_TA();
        String op="";
        try {
            op = pr.prepareTextFile(vl);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        rw.writeToFile(op, "F:\\", "Output_TA_EN", "txt");
        
    }
    
    
    
    public static void main(String args[]) throws IOException
    {
        VariablesList vl = new VariablesList();
        ExtractData ed = new ExtractData();
        ed.extractWindow(vl);   
    }
}

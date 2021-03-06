package GetOnlyWords;

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
    
    String path = "", ofname="";
    
    // Constructor
    // Uses a JFileChiiser to open a input file and set the path
    public ExtractData(){
        JFileChooser chooser = new JFileChooser();
        File homeDir = new File("F:\\");
        chooser.setCurrentDirectory(homeDir);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text and Xml Files", "txt", "xml");
        chooser.setFileFilter(filter);
        chooser.setDialogTitle("Open input file");
        //chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            path = chooser.getSelectedFile().toString();
            System.out.println(chooser.getSelectedFile().toString());            
            ofname = path.substring(3);
            System.out.println(ofname);
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
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="";
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
                    st.nextToken();
                    s1+=word+"\n";
                } 
            }                
        } catch (FileNotFoundException e) {
            System.out.println("Scanner error:"+e);
        } 
        
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(s1, "F:\\Wordlists\\", ofname, "");
        /*
        System.out.println("Sorting");
        Sort s = new Sort();
        vl = s.sorting(vl);
        
        for(int i=0; i<vl.globalcounter;i++){
            s2+=(vl.word[i]+"\t"+vl.post[i]+"\n");
        }
        */
        //System.out.println("Writing");
        //rw.writeToFile(s2, "F:\\LEX\\", "lexicon2", "txt");
    }
        
    public static void main(String args[]) throws IOException
    {
        VariablesList vl = new VariablesList();
        ExtractData ed = new ExtractData();
        ed.extractWindow(vl);   
    }
}

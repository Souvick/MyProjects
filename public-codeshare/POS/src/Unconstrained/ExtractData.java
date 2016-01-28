package Unconstrained;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
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
        //File homeDir = new File("F:\\For CRF\\");
        File homeDir = new File("F:\\Tag Compare\\");
        
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
    
    // Extracts data
    public void getText(){
        Scanner scanner;
        String input = "";
        String s1 = "", s2="", s3="", s4="", s5="", s6="", s7="", s8="",s9="";
        int count = 0, flag =0;
        File file = new File(path);
        try {
            scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                input+=newline+"\n";
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExtractData.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(input);
        ParserStanford ps = new ParserStanford();
        String out = ps.postag(input);
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(out, "F:\\", "Stanford tagged", "txt");        
    }
    
    
    public static void main(String args[]) throws IOException
    {
        ExtractData ed = new ExtractData();
        ed.getText();   
    }
}

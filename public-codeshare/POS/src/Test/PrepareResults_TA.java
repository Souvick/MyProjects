/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Souvick
 */
public class PrepareResults_TA {
    
    public String prepareTextFile(VariablesList vl) throws FileNotFoundException
    {
        int count = 0;
        String word[] = new String[5582]; 
        String post[] = new String[5582]; 
        Scanner scanner = new Scanner(new File("F:\\TR1\\TA_EN\\Univ.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>2){
                String c = st.nextToken();
                String w = st.nextToken();
                String p = st.nextToken(); 
                count = Integer.parseInt(c);
                //System.out.println(count+"\t"+w+"\t"+p);
                word[count]=w;
                post[count]=p;
            }
        }
        
        scanner = new Scanner(new File("F:\\TR1\\TA_EN\\Mixed.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>2){
                String c = st.nextToken();
                String w = st.nextToken();
                String p = st.nextToken(); 
                count = Integer.parseInt(c);
                //System.out.println(count+"\t"+w+"\t"+p);
                word[count]=w;
                post[count]=p;
            }
        }
        
        scanner = new Scanner(new File("F:\\TR1\\TA_EN\\NE.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>2){
                String c = st.nextToken();
                String w = st.nextToken();
                String p = st.nextToken(); 
                count = Integer.parseInt(c);
                //System.out.println(count+"\t"+w+"\t"+p);
                word[count]=w;
                post[count]=p;
            }
        }
        
        scanner = new Scanner(new File("F:\\TR1\\TA_EN\\English.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>2){
                String c = st.nextToken();
                String w = st.nextToken();
                String p = st.nextToken(); 
                count = Integer.parseInt(c);
                //System.out.println(count+"\t"+w+"\t"+p);
                word[count]=w;
                post[count]=p;
            }
        }
        
        scanner = new Scanner(new File("F:\\TR1\\TA_EN\\Tamil.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>2){
                String c = st.nextToken();
                String w = st.nextToken();
                String p = st.nextToken(); 
                count = Integer.parseInt(c);
                //System.out.println(count+"\t"+w+"\t"+p);
                word[count]=w;
                post[count]=p;
            }
        }
        
        String op="";
        for(int i=1; i<5582; i++){
            if(word[i]==null){
                //System.out.println(i);
                op+="\n";
            }
            else if (!word[i].equalsIgnoreCase(vl.word[i]))
            {
                System.out.print(word[i]+"\t");
                System.out.println(vl.word[i]);
            }
            else{
                if(post[i]==null){
                    //System.out.println(word[i]+"\t"+vl.lang[i]);
                    op+=word[i]+"\t"+vl.lang[i]+"\n";
                }
                else{
                    //System.out.println(word[i]+"\t"+vl.lang[i]+"\t"+post[i]);
                    op+=word[i]+"\t"+vl.lang[i]+"\t"+post[i]+"\n";
                }
            }
        }
            
        return op;
    }
    
}

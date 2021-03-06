/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Souvick
 */
public class Tagger_BN_EN {
        
    public HashMap<String,String> createHash_Acronym() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Training\\BN_EN\\Acronym.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_NE() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Training\\BN_EN\\NE.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_Undef() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Training\\BN_EN\\Undef.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_Univ() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Training\\BN_EN\\Univ.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    //System.out.println(word+"\t"+pos);
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_Other() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Training\\BN_EN\\Other.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    //System.out.println(word+"\t"+pos);
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_EN() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Annotated Data\\BN_EN_Final\\English Output.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    //System.out.println(word+"\t"+pos);
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_BN() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Annotated Data\\BN_EN_Final\\Bengali Output.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    //System.out.println(word+"\t"+pos);
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
    public HashMap<String,String> createHash_HI() throws FileNotFoundException{
        HashMap<String,String> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Annotated Data\\BN_EN_Final\\Hindi Output.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            StringTokenizer st = new StringTokenizer(newline, "\t");
            if(st.countTokens()>1){
                String word = st.nextToken();
                String pos = st.nextToken(); 
                if(hm.containsKey(word)){
                }
                else{
                    //System.out.println(word+"\t"+pos);
                    hm.put(word,pos);
                }
            }
        }
        return hm;
    }
    
}

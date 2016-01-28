/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.Scanner;

/**
 *
 * @author Souvick
 */
public class CalculateSentiment {
    
    // Extracts and returns automatic sentiment
    // Calculates automatic sentiment using sentiment words
    public int calculateAutoSentiment(String swnscores){
        Double score, total = 0.0 ;
        try{
            Scanner scanner = new Scanner(swnscores);
            while(scanner.hasNextLine()){
                String newline = scanner.nextLine();
                String[] parts = newline.split("\t\t\t\t\t\t");
                score = Double.parseDouble(parts[1]);
                total += score;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        
        total*=100;
        return total.intValue();
    }
    
}

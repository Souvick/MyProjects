/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TagCompare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;


/**
 *
 * @author Souvick
 */
public class TagC {
    public String createHash() throws FileNotFoundException{
        HashMap<String,Integer> hm = new HashMap<>();
        Scanner scanner = new Scanner(new File("F:\\Tag Compare\\Tag compare2.txt"));
        while(scanner.hasNextLine()){
            String newline = scanner.nextLine();
            if(hm.containsKey(newline)){
                int i = hm.get(newline);
                hm.put(newline,i+1);
            }
            else{
                hm.put(newline,1);
            }
        }
        SortHashMapv2 shm = new SortHashMapv2();
        hm = shm.sortHashMapByValuesD(hm);        
        return shm.hashToString(hm);
    }
    
    public static void main(String args[]) throws FileNotFoundException{
        TagC tc = new TagC();
        System.out.println(tc.createHash());
    }
}

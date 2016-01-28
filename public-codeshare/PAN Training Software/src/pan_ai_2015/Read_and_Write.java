/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author BRAJA
 */
public class Read_and_Write {
    public String read_file(String file_name) throws IOException{
       File file= new File(file_name);
        Scanner in = new Scanner(file);
        String  strline="";
        StringBuffer str= new StringBuffer();
        while(in.hasNext()){
            str=str.append(" ").append(in.next());
        }
        strline=str.toString();
        in.close();
        return strline;
    }
    public void write_file(String content, String file_name){
        try{
            File file= new File(file_name);
            PrintWriter out = new PrintWriter(file_name);
            out.append(content);
            out.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
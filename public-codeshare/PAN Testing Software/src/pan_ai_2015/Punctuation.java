/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.io.FileNotFoundException;
import java.util.StringTokenizer;

/**
 *
 * @author Promita
 */
public class Punctuation {
    
    String countPunctuationRatio(String content) throws FileNotFoundException
    {
        

      StringTokenizer tokenizer;
      String line, word;
      int  countword=0, count=0, count1=0, count2=0, count3=0, count4=0, count5=0, count6=0, count7=0, count8=0;
        
      
            tokenizer = new StringTokenizer (content);
	    count= tokenizer.countTokens();

	     for(int i=0; i<count; i++)
	      {
		word = tokenizer.nextToken();
                countword++;
		int length=word.length();
		for(int j=0; j<length; j++)
		 {
			char ch=word.charAt(j);

                        switch(ch)
			 {
			  case',':
				count1++;
			 	break;
			  case';':
				count2++;
			 	break;
			  case':':
				count3++;
			 	break;
			  case'.':
				count4++;
			 	break;
			  case'?':
				count5++;
                              break;
			  case'!':
				count6++;
			 	break;
			  case'/':
				count7++;
			 	break;
                          case'-':
				count8++;
			 	break;
			 }
		 }
		  
	      }
 	  
       
         int totalPunctuation=count1+count2+count3+count4+count5+count6+count7+count8;
         
         float punctuationToWordRatio= (float)totalPunctuation*100/countword;
         
         float commaRatio=(float)count1*100/totalPunctuation;
         float semicolonRatio=(float)count2*100/totalPunctuation;
         float colonRatio=(float)count3*100/totalPunctuation;
         float stopRatio=(float)count4*100/totalPunctuation;
         float questionRatio=(float)count5*100/totalPunctuation;
         float exclamationRatio=(float)count6*100/totalPunctuation;
         float slashRatio=(float)count7*100/totalPunctuation;
         float dashRatio=(float)count8*100/totalPunctuation;
         
         String ret=punctuationToWordRatio+"\t"+commaRatio+"\t"+semicolonRatio+ "\t"+colonRatio+ "\t"+stopRatio+ "\t"+questionRatio+ "\t"+exclamationRatio+ "\t"+slashRatio+ "\t"+dashRatio+ "\t";
         return ret;
           
   }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package SentimentAnalysisVersion2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Souvick
 */
public class FeatureTest {
    
    public void testfeatures(Statistics st){
        String op="",op1="";
        int id;
        op1+="Num\tSource\tTarget\tAutosentiment\tUnimatch\tBimatch\tTrimatch\tPositivematch\tUnimatchn\tBimatchn\tTrimatchn\t\tNegativematch\tFW\tJJ\tRB\tRB_JJ\tamod\tacomp\tadvmod\n";
        System.out.println("Num\tSource\tTarget\tAutosentiment\tUnimatchp\tBimatchp\tTrimatchp\tPositivematch\tUnimatchn\tBimatchn\tTrimatchn\t\tNegativematch\tFW\tJJ\tRB\tRB_JJ\tamod\tacomp\tadvmod\n");
        for(int i=0; i<st.globalcounter; i++){
            op1+=st.id[i]+"\t";
            
            System.out.print(st.source[i]+"\t");
            op1+=st.source[i]+"\t";
            
            System.out.print(st.target[i]+"\t");
            op1+=st.target[i]+"\t";
            
            System.out.print(st.autosentiment[i]+"\t");
            op+=st.autosentiment[i]+"\t";
            op1+=st.autosentiment[i]+"\t";
            
            System.out.print(st.unimatchp[i]+"\t");
            op+=st.unimatchp[i]+"\t";
            op1+=st.unimatchp[i]+"\t";
            
            System.out.print(st.bimatchp[i]+"\t");
            op+=st.bimatchp[i]+"\t";
            op1+=st.bimatchp[i]+"\t";
            
            System.out.print(st.trimatchp[i]+"\t");
            op+=st.trimatchp[i]+"\t";
            op1+=st.trimatchp[i]+"\t";
            
            System.out.print(st.positivewords[i]+"\t");
            op+=st.positivewords[i]+"\t";
            op1+=st.positivewords[i]+"\t";
            
            System.out.print(st.unimatchn[i]+"\t");
            op+=st.unimatchn[i]+"\t";
            op1+=st.unimatchn[i]+"\t";
            
            System.out.print(st.bimatchn[i]+"\t");
            op+=st.bimatchn[i]+"\t";
            op1+=st.bimatchn[i]+"\t";
            
            System.out.print(st.trimatchn[i]+"\t");
            op+=st.trimatchn[i]+"\t";
            op1+=st.trimatchn[i]+"\t";
            
            System.out.print(st.negativewords[i]+"\t");
            op+=st.negativewords[i]+"\t";
            op1+=st.negativewords[i]+"\t";
            
            System.out.print(st.isFWPresent[i]+"\t");
            op+=st.isFWPresent[i]+"\t";
            op1+=st.isFWPresent[i]+"\t";
            
            System.out.print(st.isJJPresent[i]+"\t");
            op+=st.isJJPresent[i]+"\t";
            op1+=st.isJJPresent[i]+"\t";
            
            System.out.print(st.isRBPresent[i]+"\t");
            op+=st.isRBPresent[i]+"\t";
            op1+=st.isRBPresent[i]+"\t";
            
            System.out.print(st.isRBJJPresent[i]+"\t");
            op+=st.isRBJJPresent[i]+"\t";
            op1+=st.isRBJJPresent[i]+"\t";
            
            System.out.print(st.isAmodPresent[i]+"\t");            
            op+=st.isAmodPresent[i]+"\t";
            op1+=st.isAmodPresent[i]+"\t";
            
            System.out.print(st.isAcompPresent[i]+"\t");           
            op+=st.isAcompPresent[i]+"\t";
            op1+=st.isAcompPresent[i]+"\t";
            
            System.out.print(st.isAdvmodPresent[i]+"\n");            
            op+=st.isAdvmodPresent[i]+"\t";
            op1+=st.isAdvmodPresent[i]+"\n";
            
            id = st.id[i];
            op+= "o"+"\t";
            op+= id+"\n";            
        }
        ReaderWriter rw = new ReaderWriter();
        rw.writeToFile(op1, "E:\\op\\CSS\\", "Stat", "Test" , "txt");
        Weka weka = new Weka();
        try {
            weka.classifyTestSet(op, "E:\\op\\CSS","E:\\op\\CSS\\");
        } catch (Exception ex) {
            Logger.getLogger(FeaturePrint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

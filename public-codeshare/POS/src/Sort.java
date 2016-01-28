/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Souvick
 */
public class Sort {
    public VariablesList sorting(VariablesList vl){
        System.out.println("GC:"+vl.globalcounter);
        for(int i=0; i<vl.globalcounter; i++){
            for(int j=0; i<vl.globalcounter; j++){
                if(vl.word[i].compareToIgnoreCase(vl.word[j])<0){
                    String temp1, temp2;
                    temp1 = vl.word[j];
                    temp2 = vl.post[j];
                    vl.word[j] = vl.word[i];
                    vl.post[j] = vl.post[i];
                    vl.word[i] = temp1;
                    vl.post[i] = temp2;
                }
            }
        }
        return vl;
    }
}

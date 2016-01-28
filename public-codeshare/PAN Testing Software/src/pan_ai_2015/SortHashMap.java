/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pan_ai_2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 *
 * @author Promita
 */
public class SortHashMap {
 // To sort hasmap in descending order
    public LinkedHashMap sortHashMapByValuesD(HashMap passedMap) {
        //System.out.println("Inside sortHashMapByValuesD");
        List mapKeys = new ArrayList(passedMap.keySet());
        List mapValues = new ArrayList(passedMap.values());
        Comparator cmp = Collections.reverseOrder(); 
        Collections.sort(mapValues,cmp);
        Collections.sort(mapKeys,cmp);
        LinkedHashMap sortedMap = new LinkedHashMap();
        Iterator valueIt = mapValues.iterator();
        while (valueIt.hasNext()) {
            Object val = valueIt.next();
            Iterator keyIt = mapKeys.iterator();
            while (keyIt.hasNext()) {
                Object key = keyIt.next();
                String comp1 = passedMap.get(key).toString();
                String comp2 = val.toString();
                if (comp1.equals(comp2)){
                    passedMap.remove(key);
                    mapKeys.remove(key);
                    sortedMap.put((String)key,Integer.parseInt(val.toString()));
                    break;
                }
            }
        }
        return sortedMap;
     }
}

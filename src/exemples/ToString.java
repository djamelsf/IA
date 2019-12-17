/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemples;

/**
 *Classe permettant de representer une Map Variable => Valeur (string) sous forme d'affichage console 
 */


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;
import representations.Variable;

/*
* CLasse permettant un affichage console plus lisible de differentes forme de resultats.
*/
public class ToString 
{
    public static String mapToString(Map<Variable, String> map)
    {
        String str = "";
        
        for(Map.Entry<Variable, String> keyset : map.entrySet())
            str += keyset.getKey().getNom() + " -> " + keyset.getValue() + "\n";

        return str;
    }
    
    
   
            
    public static String ArraymapToString( Map<ArrayList<Variable>, Integer> map)
    {
        String str = "";
        
        for(Map.Entry<ArrayList<Variable>,Integer> keyset : map.entrySet()){
            for (Variable var : keyset.getKey()) {
                str += var.getNom();
            }
            str+= " -> " + keyset.getValue() + "\n";
        }
        return str;
    }
    
    public static String ArrayRuleToString( ArrayList<Rule> map)
    {
        String str = "";
        
        for (int i = 0; i < map.size(); i++){
            Iterator<RestrictedDomain> iterator = map.get(i).premisse.iterator();
            while (iterator.hasNext()) {
                RestrictedDomain elt = iterator.next();
              
                str += elt.getNom();
            }
            
            str+= " -> ";
            
            Iterator<RestrictedDomain> iterator2 = map.get(i).conclusion.iterator();
            while (iterator2.hasNext()) {
                RestrictedDomain elt2 = iterator2.next();
                str += elt2.getNom();
            }
            str+="\n";
        }
        
        return str;
    }
    
    
}

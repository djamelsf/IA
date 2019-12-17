/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extraction;

import java.util.ArrayList;
import java.util.Map;
import representations.Variable;

/**
 * Classe  possédant un attribut de type (Map de( Set de (Variables, Integer)) représentant des itemsets fréquents 
 * et leurs fréquences, et une méthode permettant de calculer les règles d'association fréquentes et valides pour des seuils 
 * donnés de fréquence minimale et de confiance minimale
 */
public class AssociationRuleMiner {
    public Map<ArrayList<Variable>, Integer> itemsetsFrequents;
    public BooleanDatabase booleanDatabase;

    public AssociationRuleMiner(Map<ArrayList<Variable>, Integer> itemsetsFrequents, BooleanDatabase booleanDatabase) {
        this.itemsetsFrequents = itemsetsFrequents;
        this.booleanDatabase = booleanDatabase;
    }
     //
    public ArrayList<Rule2> getRules(double minConf){
        ArrayList<Rule2> regles=new ArrayList<>();
        for (Map.Entry<ArrayList<Variable>, Integer> entry : itemsetsFrequents.entrySet()) {
            ArrayList<Variable> key = entry.getKey();
            Integer value = entry.getValue();
            if(key.size()>1){
                ArrayList<ArrayList<Variable>> liste=new ArrayList<>();
                for (int i = 1; i < key.size(); i++) {
                    liste.addAll(getItemsLogeur(i, key));
                }
                for (ArrayList<Variable> premisse : liste) {
                    for (ArrayList<Variable> conclusion : liste) {
                        Rule2 rule=new Rule2(premisse, conclusion,key);
                        if (rule.isValid()) {
                            if (testConfiance(minConf, rule)) {
                                regles.add(rule);
                            }
                            
                        }
                        
                    }
                }
            }
            
        }
        return regles;
    }
    
    public boolean testConfiance(double minConf, Rule2 rule){
        double m=minConf/100.0;
        if ((double)frequence(rule.getScope())/(double)frequence(rule.getPremisse())>=m) {
            return true;
        }else{
            return false;
        }
    }

    
    
    
    public void generateRegles(double minConf){
        for (Map.Entry<ArrayList<Variable>, Integer> entry : itemsetsFrequents.entrySet()) {
            ArrayList<Variable> key = entry.getKey();
            Integer value = entry.getValue();
            if(key.size()>1){
                for (Variable variable : key) {
                    ArrayList<Variable> tmp=new ArrayList<>();
                    tmp.add(variable);
                    if(frequence(key)/frequence(tmp)>=minConf){
                        ArrayList<Variable> conc=new ArrayList<>();
                        conc.addAll(key);
                        conc.remove(variable);
                        System.out.print(variable.getNom()+"->");
                        for (Variable variable1 : conc) {
                            System.out.println(variable1.getNom()+" ");
                        }
                    }
                }
            }
            
        }
    }
  
    
    
    
    
    
    
    
    public int frequence(ArrayList<Variable> list_of_variables) {
        int frequency = 0;
        for (Map<Variable, String> map : this.booleanDatabase.getTransactions()) {
            boolean b = true;
            for (Variable var : list_of_variables) {
                if (!map.get(var).equals("1")) {
                    b = false;
                }
            }
            if (b) {
                frequency++;
            }
        }
        return frequency;
    }
    
    
    public ArrayList<ArrayList<Variable>> getItemsLogeur(int x,ArrayList<Variable> l) {
        
        int n = l.size();
        return printCombination(l, n, x);
    }

    ArrayList<ArrayList<Variable>> printCombination(ArrayList<Variable> l, int n, int r) {
        // A temporary array to store all combination one by one 
        Variable data[] = new Variable[r];
        //ArrayList<Variable> data=new ArrayList<>();
        ArrayList<ArrayList<Variable>> items = new ArrayList<>();
        // Print all combination using temprary array 'data[]' 
        return combinationUtil(l, data, 0, n - 1, 0, r, items);
    }

    ArrayList<ArrayList<Variable>> combinationUtil(ArrayList<Variable> arr, Variable[] data, int start,
            int end, int index, int r, ArrayList<ArrayList<Variable>> items) {
        // Current combination is ready to be printed, print it 
        if (index == r) {
            ArrayList<Variable> t = new ArrayList<>();
            for (int j = 0; j < r; j++) {
                //System.out.print(data[j].getNom()+" "); 
                t.add(data[j]);
            }
            items.add(t);
            //System.out.println(""); 
            return items;
        }

        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr.get(i);
            combinationUtil(arr, data, i + 1, end, index + 1, r, items);
        }
        return items;
    }
    
}

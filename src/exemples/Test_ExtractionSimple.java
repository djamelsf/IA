/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemples;

/**
 *
 * @author mac
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import extraction.AssociationRuleMiner;
import extraction.BooleanDatabase;
import extraction.FrequantITemsetMiner;
import extraction.Rule2;
import representations.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author mac
 */
public class Test_ExtractionSimple {
    public static void main(String[] args) {
        Set<String> dom=new HashSet<>();
        dom.add("0");
        dom.add("1");
        Variable A=new Variable("A", dom);
        Variable B=new Variable("B", dom);
        Variable C=new Variable("C", dom);
        Variable D=new Variable("D", dom);
        Variable E=new Variable("E", dom);
        ArrayList<Variable> variables=new ArrayList<>();
        variables.add(A);
        variables.add(B);
        variables.add(C);
        variables.add(D);
        variables.add(E);
        ArrayList<Map<Variable,String>> liste=new ArrayList<>();
        Map<Variable,String> t1=new HashMap<>();
        t1.put(A, "1");
        t1.put(B, "1");
        t1.put(C, "1");
        t1.put(D, "1");
        t1.put(E, "1");
        Map<Variable,String> t2=new HashMap<>();
        t2.put(A, "1");
        t2.put(B, "0");
        t2.put(C, "1");
        t2.put(D, "0");
        t2.put(E, "0");
        Map<Variable,String> t3=new HashMap<>();
        t3.put(A, "1");
        t3.put(B, "1");
        t3.put(C, "1");
        t3.put(D, "1");
        t3.put(E, "0");
        Map<Variable,String> t4=new HashMap<>();
        t4.put(A, "0");
        t4.put(B, "1");
        t4.put(C, "1");
        t4.put(D, "0");
        t4.put(E, "0");
        Map<Variable,String> t5=new HashMap<>();
        t5.put(A, "1");
        t5.put(B, "1");
        t5.put(C, "1");
        t5.put(D, "0");
        t5.put(E, "0");
        Map<Variable,String> t6=new HashMap<>();
        t6.put(A, "0");
        t6.put(B, "0");
        t6.put(C, "0");
        t6.put(D, "0");
        t6.put(E, "1");
        liste.add(t1);
        liste.add(t2);
        liste.add(t3);
        liste.add(t4);
        liste.add(t5);
        liste.add(t6);
        BooleanDatabase bd=new BooleanDatabase(variables, liste);
        FrequantITemsetMiner fI=new FrequantITemsetMiner(bd);
        Map<ArrayList<Variable>, Integer> r= fI.frequentItemsets(2);
        for (Map.Entry<ArrayList<Variable>, Integer> entry : r.entrySet()) {
            ArrayList<Variable> key = entry.getKey();
            for (Variable variable : key) {
                System.out.print(variable.getNom());
            }
            Integer value = entry.getValue();
            System.out.println("("+value+")");
            
        }
        System.out.println("------------------------ Les regles -----------------------------------");
        AssociationRuleMiner associationRuleMiner=new AssociationRuleMiner(r, bd);
        ArrayList<Rule2> table=associationRuleMiner.getRules(100);
         for (Rule2 rule2 : table) {
            System.out.println(rule2);
        }
        
       // fI.aa(4);
        
        
    }
    
}


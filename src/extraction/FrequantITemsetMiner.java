
package extraction;

import representations.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * une classe FrequentItemsetMiner, possédant un attribut de type BooleanDatabase et une méthode frequentItemsets prenant
 * en argument une fréquence minimale, et retournant une Map ayant pour clefs des itemsets (Set de Variables) et pour valeurs,
 * leurs fréquences
 */
public class FrequantITemsetMiner {

    private BooleanDatabase booleanDatabase;
    //Constructeur de la classe
    public FrequantITemsetMiner(BooleanDatabase booleanDatabase) {
        this.booleanDatabase = booleanDatabase;
    }
    
    public Map<ArrayList<Variable>, Integer> frequentItemsets(int minFr) {
        //on crée une liste qui contiendras les diferrents itemsets et leur fréquences
        Map<ArrayList<Variable>, Integer> listeItems=new HashMap<>();
        //on récupere la liste des variables 
        ArrayList<Variable> variables=this.booleanDatabase.getList_de_variables();
        //on parcour les variables de la BDD
        for (int i = 1; i < variables.size(); i++) {
            //Pour chaque taille d'itemset, on récupere toute les combinaisons de variables possibles (sans elagage)
            ArrayList<ArrayList<Variable>> items=getItemsLogeur(i);
           
            //on parcour chaque itemset generé
            for (ArrayList<Variable> item : items) {
                //pour chaque item set on calcule sa fréquence dans la base de donné
                int f=frequence(item);
                //on verifie si cette frequence est valide 
                if(f>=minFr){
                    //si oui on l'introduit au resultat final 
                    listeItems.put(item, f);
                }
            }
         //System.out.println(i+"____________________");   
        }
        
        
    return listeItems;
    }
    
    
    
    //Methode "Main" de la créeation de toutes les combinaisons d'itemsets 
    public ArrayList<ArrayList<Variable>> getItemsLogeur(int x) {
        //on reprend n comme etant la taille de la liste de variables de notre BDD
        ArrayList<Variable> l = this.booleanDatabase.getList_de_variables();
        int n = l.size();
        return printCombination(l, n, x);
    }

    //Methode qui genere les differentes listes necessaires au stockage des itemsets et lance la methode 
    ArrayList<ArrayList<Variable>> printCombination(ArrayList<Variable> l, int n, int r) {
        // Une liste temporaire pour conserver toutes les combinaisons d'itemset de taille r 
        Variable data[] = new Variable[r];
        
        ArrayList<ArrayList<Variable>> items = new ArrayList<>();
        // on recuprere toutes les combinsaison en utilisant la liste temporaire data [] 
        return combinationUtil(l, data, 0, n - 1, 0, r, items);
    }

    
    /*
    * Methode de generations de toutes les combinaisons d'itemset de taille r 
    * @param r : taille des itemsets a generer
    * @param arr : arraylist des variables de la base de donnée
    * @param data : Liste temporaire utile dans l'algorithme pour la recursivitée 
    */
    ArrayList<ArrayList<Variable>> combinationUtil(ArrayList<Variable> arr, Variable[] data, int start,
            int end, int index, int r, ArrayList<ArrayList<Variable>> items) {
        // Current combination is ready to be printed, print it 
        if (index == r) {
            ArrayList<Variable> t = new ArrayList<>();
            for (int j = 0; j < r; j++) {
                 
                t.add(data[j]);
            }
            items.add(t);
            
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

    
    
    
    
    
    
    //Methode de calcul de la frequence d'un itemset dans la base de donnée
    public int frequence(ArrayList<Variable> list_of_variables) {
        //on initialise une frequence a 0
        int frequency = 0;
        //on parcour toutes les transactions de la base de donnée
        for (Map<Variable, String> map : this.booleanDatabase.getTransactions()) {
            boolean b = true;
            for (Variable var : list_of_variables) {
                //on verifie la valeur de cet itemset dans la BDD 
                if (!map.get(var).equals("1")) {
                    b = false;
                    break;
                }
            }
            //si cette valeur est positive pour toutes les variables presente dans l'itemset, alors on incremente la frequence
            if (b) {
                frequency++;
            }
        }
      
        return frequency;
    }
    
    /* Methode simple pour retrouver une variable v dans une transaction "map"
    *  et retourner sa valeur (qui seras verifié dans la methode frequence)
    */
    public String getVar(Map<Variable, String> map,Variable v){
        for (Map.Entry<Variable, String> entry : map.entrySet()) {
            Variable key = entry.getKey();
            String value = entry.getValue();
            if(key.getNom().equals(v.getNom())){
                return value;
            }
            
        }
       
        return null;
    }

}

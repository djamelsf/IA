
package extraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import representations.Variable;

/**
 * Classe Database pour représenter une base de données non nécessairement booléenne
 *
 */

//La premiere partie est similaire a la classe Booleandatabase, en rajoutant des Setters
public class Database {
    private ArrayList<Variable> variables;
    private ArrayList<Map<Variable,String>> transactions;

    public Database(List<Variable> variables, List<Map<Variable, String>> transactions) {
        this.variables = (ArrayList<Variable>) variables;
        this.transactions = (ArrayList<Map<Variable, String>>) transactions;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public ArrayList<Map<Variable, String>> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Map<Variable, String>> transactions) {
        this.transactions = transactions;
    }
    

    

    //Methode permettant de passer d'une Database à une base donnée Booléenne
    
    public ArrayList<HashMap<Variable,String>> toBoolean(){
        
        ArrayList<Variable> allVariables = variables;
        
        ArrayList<HashMap<Variable,String>> bD = new ArrayList<>();
        
        for(Map<Variable,String> map : transactions){
            HashMap<Variable,String> transaction = new HashMap<>();
            /*Creating variables from (variable,value) and put them in the transaction like (variable,value) => "1"*/
            for(Map.Entry<Variable,String> entry : map.entrySet()){
                /*On verifie si les valeurs ne sont pas deja booléennes*/
                if(entry.getValue().equals("0") || entry.getValue().equals("1")){
                    transaction.put(entry.getKey(), entry.getValue());
                }
                /*
                Sinon, on transpose ces valeurs en valeurs booléennes, en passant leur valeur de niveaux dans leur nom de variable 
                Puis en passant les valeurs des autres niveaux concerné par cette variable a 0
                */
                else{
                    Variable a = new Variable(entry.getKey().getNom() + "_" + entry.getValue());
                    transaction.put(a,"1");
                    if("haute".equals(entry.getValue())){
                        Variable b = new Variable(entry.getKey().getNom() + "_" + "moyenne");
                        Variable c = new Variable(entry.getKey().getNom() + "_" + "basse");
                        transaction.put(b,"0");
                        transaction.put(c,"0");
                    }
                    if("moyenne".equals(entry.getValue())){
                        Variable b = new Variable(entry.getKey().getNom() + "_" + "haute");
                        Variable c = new Variable(entry.getKey().getNom() + "_" + "basse");
                        transaction.put(b,"0");
                        transaction.put(c,"0");
                    }
                    if("basse".equals(entry.getValue())){
                        Variable b = new Variable(entry.getKey().getNom() + "_" + "haute");
                        Variable c = new Variable(entry.getKey().getNom() + "_" + "moyenne");
                        transaction.put(b,"0");
                        transaction.put(c,"0");
                    }
                }
            }
            bD.add(transaction);            
        }
        
        return bD;
    }
    
}

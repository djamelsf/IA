
package extraction;

import representations.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * une classe BooleanDatabase dont une instance contient une liste de variables, et une liste de transactions, 
 * chacune représentée par un dictionnaire (Map) variable / valeur ;
 * Toute les valeurs representés sont booléennes 
 */
public class BooleanDatabase {
    private ArrayList<Variable> variables;
    private ArrayList<Map<Variable,String>> transactions;

    /*
    *Constructeur de la classe
    */
    public BooleanDatabase(ArrayList<Variable> variables, ArrayList<Map<Variable, String>> transactions) {
        this.variables = variables;
        this.transactions = transactions;
    }

    /*
    * Getters des Variables et transactions
    */

    public ArrayList<Map<Variable, String>> getTransactions() {
        return transactions;
    }
    
    public ArrayList<Variable> getList_de_variables() {
		return variables;
	}

	
    
    
    
    
    
    
}

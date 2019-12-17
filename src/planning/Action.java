
package planning;

import representations.RestrictedDomain;
import representations.Rule;
import representations.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *  Classe qui représente une action. Cette classe
 *  doit permettre d’exprimer des affectations de Variable représentant les préconditions
 *  et les effets
 */
public class Action {
    public Map<Variable,String> precondition;
    public Map<Variable,String> effet;
    public String nom;
    
    
    //Constructeur de la classe
    //Une action contient une precondition, etat de base, et un effet, etat final, et evidement un nom
    public Action(String nom,Map<Variable, String> precondition, Map<Variable, String> effet) {
        this.precondition = precondition;
        this.effet = effet;
        this.nom=nom;
    }

   
    //vérifie si un état donné (state) satisfait les états finaux(state2)
    public static boolean satisfies(State state,State state2){
        for(Map.Entry<Variable, String> entry : state2.getMap().entrySet()){
            if(/*!(state.contains(entry.getKey())) ||*/ (entry.getValue() != state.getMap().get(entry.getKey()))){
                return false;
            }
        }
        return true;
    }
    
    
    //vérifie si une action (a)  est applicable dans un état donné (s) ou non (boolean) 
    public static boolean is_applicable(Action a, State s){
        //on parcourt les preconditions de l'action
    	for(Map.Entry<Variable, String> entry : a.precondition.entrySet()) {
            HashMap<Variable, String> precon = new HashMap<>();
            precon.put(entry.getKey(), entry.getValue());
            //on utilise la methode satisfies pour verifier  si l'action est applicable
            if(!satisfies(s, new State(precon))){
        	return false;
            }
        }
        return true;
    }
    
    // applique une action donnée (a) dans un état donné (s)
    public static State apply(Action a, State s){
        //on créer le nouvel etat new_state
    	HashMap<Variable, String> news = new HashMap<>();
    	news.putAll(s.getMap());
        State new_state = new State(news);  
        //on applique les effet de l'action (a) a l'etat new_state  en parcourant notre action
        for(Map.Entry<Variable, String> entry : a.effet.entrySet()) 
        {
            new_state.getMap().put(entry.getKey(), entry.getValue()); 
        }            
        
        //on return le nouvel etat
        return new_state;   
    }
    
    //methode d'affichaque d'une action en console
    public String toString(){
        String s = "Action :"+nom+" : \n";
        s+="-Premisse-\n";
        for(Map.Entry<Variable, String> entry : this.precondition.entrySet()) {
            s += "[ " + entry.getKey().getNom() + " ===> " + entry.getValue() + "]\n";
            
        }
        s+="-Conclusion-\n";
        for(Map.Entry<Variable, String> entry : this.effet.entrySet()) {
            s += "[ " + entry.getKey().getNom() + " ===> " + entry.getValue() + "]\n";
        }
        s+="\n\n";
        return s;
    }

    //methode booléenne de comparaison de deux actions
    @Override
    public boolean equals(Object obj) {
        Action action=(Action) obj;
        if(this.effet.size()==action.effet.size() && this.precondition.size()==action.precondition.size()){
        
        for (Map.Entry<Variable, String> entry : precondition.entrySet()) {
            Variable key = entry.getKey();
            String value = entry.getValue();
            if(action.precondition.get(key)!=value){
                return false;
            }  
        }
        for (Map.Entry<Variable, String> entry : effet.entrySet()) {
            Variable key = entry.getKey();
            String value = entry.getValue();
            if(action.effet.get(key)!=value){
                return false;
            }  
        }
        return true;
            
        }else{
            return false;
        }
    }
    
    
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import representations.Variable;
import java.util.Map;

/**
 * Classe faisant la somme des niveaux des symptômes présent dans un etat donné (state)
 */
public class SimpleHeuristic implements Heuristic{
    State state;

    public SimpleHeuristic(State state) {
        this.state = state;
    }
    
    //Methode calculant la somme des niveaux des symptomes, servant d'heuristique
    @Override
    public int getHeuristic() {
        int cpt=0;
        //on parcourt l'etat donné initialement
        for (Map.Entry<Variable, String> en : state.getMap().entrySet()) {
            Variable key = en.getKey();
            String value = en.getValue();
            //si l'etat contient un symptome, on incremente notre resultat
            if(key.getNom()=="COUGH" || key.getNom()=="FEVER" || key.getNom()=="BUTTONS"){
                if(value!="none"){
                    cpt++;
                }
            }
        }
        return cpt;
    }
    
   
    
}


package planning;

import representations.Variable;
import java.util.Map;
import java.util.Set;

/**
 *Classe representant un état (dans notre exemple de cour, l'etat d'un patient) 
 * une classe represente differentes variables (maladies, symptomes) et leur valeur (actifs ou non, ou leur niveaux ) 
 */
public class State {
    
    Map<Variable,String> liste;
    //constructeur de la classe
    public State(Map<Variable, String> liste) {
        this.liste = liste;
    }

    public Map<Variable,String> getMap(){
        return this.liste;
    }
    
    //methode booléenne indiquant si une variable (a) est contenue dans l'etat présent 
    public Boolean contains(Variable a) {
        //on parcourt l'etat actuel
    	for(Map.Entry<Variable, String> entry : this.liste.entrySet()){
                //on verifie si la variable actuel correspond a la variable recherchée
    		if (entry.getKey().getNom() == a.getNom())
    			return true;
    	}
    	return false;
    }
        //methode booléenne de comparaison de deux etat, verifiant si ils sont éeaux ou non .
      @Override
      public boolean equals(Object state) {
        boolean retVal = false;
        return state instanceof State && this.liste.equals(((State)state).liste);
  }

    
    
}

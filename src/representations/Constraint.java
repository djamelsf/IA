
package representations;

import java.util.Map;
import java.util.Set;

/**
 *
 * Interface de contrainte permettant de créer des regles 
 */
public interface Constraint {
    
    //Obtenir le domaine d'une variables ( la liste des valeurs attribuables a celle-ci)
    Set<Variable> getScope();
    
    //Verifier si l'attributions variable/valeur confirme les contraintes données
    boolean isSatisfiedBy(Map<Variable, String> m);   
    
    
}

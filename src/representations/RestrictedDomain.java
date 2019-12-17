
package representations;

import java.util.Set;

/**
 *une classe RestrictedDomain, permettant de représenter un couple (variable, sous-domaine), c'est-à-dire possédant un attribut de type Variable et un attribut de type Set String
 */
public class RestrictedDomain {
    Variable variable;
    Set<String> sous_domaine; 

    public RestrictedDomain(Variable variable, Set<String> sous_domaine) {
        this.variable = variable;
        this.sous_domaine = sous_domaine;
    }
    
    
    public String getNom() {
        return variable.getNom();
    }
}

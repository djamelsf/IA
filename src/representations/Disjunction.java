
package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *une classe Disjunction, pour représenter les contraintes de la forme (z \in {e,f...} || t \in {g,h...} || ...)
 */
public class Disjunction extends Rule implements Constraint{
    //on remarque qu'une disjonction revien à utiliser uniquement la Conclusion d'une Rule classique
    // Il suffit donc d'override les methodes de Rule tout en substituant la premisse
    public Set<RestrictedDomain> conclusion;

    public Disjunction(Set<RestrictedDomain> conclusion) {
       super(null, conclusion);
       this.conclusion=conclusion;
    }
    

    @Override
    public Set<Variable> getScope() {
        Set<Variable> l=new HashSet<>();
        for (RestrictedDomain restrictedDomain : conclusion) {
            l.add(restrictedDomain.variable);
        }
        return l;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> m) {
        return super.isSatisfiedBy(m);
    }
    
}

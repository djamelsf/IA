
package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *une classe IncompatibilityConstraint, pour représenter les contraintes de la forme ! (x \in {a,b...} && y \in {c,d...} && ...) 
 */
public class IncompatibilityConstraint extends Rule implements Constraint{
    //on remarque qu'une IncompatibilityConstraint revien à utiliser uniquement la premisse d'une Rule classique
    // Il suffit donc d'override les methodes de Rule tout en substituant la conclusion
    public Set<RestrictedDomain> premisse;
    
    public IncompatibilityConstraint(Set<RestrictedDomain> premisse) {
        super(premisse, null);
        this.premisse=premisse;
    }

    @Override
    public Set<Variable> getScope() {
        Set<Variable> l=new HashSet<>();
        for (RestrictedDomain restrictedDomain : premisse) {
            l.add(restrictedDomain.variable);
        }
        return l;
    }

    @Override
    public boolean isSatisfiedBy(Map<Variable, String> m) {
        return super.isSatisfiedBy(m);
    }
    
    
}

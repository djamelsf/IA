package representations;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * une classe Rule, pour représenter les contraintes de la forme (x \in {a,b...}
 * && y \in {c,d...} && ...) -> (z \in {e,f...} || t \in {g,h...} || ...)
 */
public class Rule implements Constraint {

    //une Regle possede une premisse et une conclusion
    public Set<RestrictedDomain> premisse;
    public Set<RestrictedDomain> conclusion;

    public Rule(Set<RestrictedDomain> premisse, Set<RestrictedDomain> conclusion) {
        this.premisse = premisse;
        this.conclusion = conclusion;
    }

    //On recupere les Domaines restreints des Premisse et conclusion 
    @Override
    public Set<Variable> getScope() {
        Set<Variable> l = new HashSet<>();
        for (RestrictedDomain restrictedDomain : premisse) {
            l.add(restrictedDomain.variable);
        }
        for (RestrictedDomain restrictedDomain : conclusion) {
            l.add(restrictedDomain.variable);
        }
        return l;
    }

    
    @Override
    public boolean isSatisfiedBy(Map<Variable, String> m) {
        boolean bPremisse = true;
        //On verifie si il y a une premisse 
        if (premisse != null) {
            //on parcourt la Map donné en attribut 
            for (Map.Entry<Variable, String> entry : m.entrySet()) {
                Variable key = entry.getKey();
                String value = entry.getValue();
                
                //on parcourt la premisse 
                for (RestrictedDomain restrictedDomain : premisse) {
                    //on compare les variables 
                    if (restrictedDomain.variable.nom == key.nom) {
                        boolean bool = false;
                        //puis on compare les differentes valeurs 
                        for (String string : restrictedDomain.sous_domaine) {
                            if (string == value) {
                                bool = true;
                            }
                        }
                        if (bool == false) {
                            //return false;
                            bPremisse = false;
                            break;
                        }
                    }
                }
            }
        }

        //meme chose pour la conclusion
        boolean bConc = false;
        if (conclusion != null) {
            for (Map.Entry<Variable, String> entry : m.entrySet()) {
                Variable key = entry.getKey();
                String value = entry.getValue();
                for (RestrictedDomain restrictedDomain : conclusion) {
                    if (restrictedDomain.variable.nom == key.nom) {
                        for (String string : restrictedDomain.sous_domaine) {
                            if (string.equals(value)) {
                                
                                bConc = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
        //Si la premisse n'est pas confirmé, la regles ne concerne pas ce cas, donc on retourne True
        //Si la conclusion est confirmé, alors la regle est approuvé => True 
        //Sinon, on retourne False 
        return !(bPremisse && !bConc);
    }

}

package ppc;

import representations.Variable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import representations.Constraint;
import representations.Constraint;
import representations.Variable;

public class BackTracking 
{
    private final Set<Variable> variables;
    private final Set<Constraint> contraintes;
    
    public BackTracking(Set<Constraint> contraintes)
    {
        this.contraintes = contraintes;
        
        variables = new HashSet<>();
        
        contraintes.forEach((constraint) -> {
            variables.addAll(constraint.getScope());
        });
    
        int i = 1;
        
        for(Variable var : variables)
            i *= var.getDomaine().size();
        
        //System.out.println("BackTracking annonce " + i + " combinaisons au maximum");
    }
    
    public Set<Map<Variable, String>> allSolutions()
    {
        Set<Map<Variable, String>> all = new HashSet<>();
        
        Map<Variable, String> res_partial = new HashMap<>();
        Deque<Variable> unassignedVariables = new ArrayDeque<>(variables);
        
        this.solutions(res_partial, unassignedVariables, all);
        
        return all;
    }
    
    void solutions(Map<Variable, String> assign_part, Deque<Variable> unassigned, Set<Map<Variable, String>> all)
    {
        // si res_partial est devenue complete
        if (unassigned.isEmpty())
        {
            // on l'ajoute à la liste totale des solutions
            all.add(new HashMap<>(assign_part));
        }
        // si res_partial est toujours partielle
        else
        {
            // la liste des variables non assignées devient copie d'elle-meme, sinon elle va rester globale à la recursivité
            unassigned = new ArrayDeque(unassigned);
            
            // on tire la variable var dont on va parcourir les valeurs
            Variable var = unassigned.pollFirst();

            // parcours du domaine de var
            parcours_des_valeurs : for(String val : var.getDomaine())
            {
                // res_partial est cloné aussi
                Map<Variable, String> assign_part_clone = new HashMap(assign_part);    

                // on assigne au clone la valeur qu'on va tester
                assign_part_clone.put(var, val);
                
                // liste des variables qu'on a assigné dans la map en construction
                Set<Variable> keys = assign_part_clone.keySet();
                
                // avant de parcourir les variables suivantes, on vérifie que c'est bien la peine de continuer sur cette map
                // en parcourant chaque loi
                for(Constraint contr : contraintes)
                    // si une loi est vérifiable par res_partial, mais pas satisfaite on fait sauter l'itération à la valeur suivante
                    if (keys.containsAll(contr.getScope()) && !contr.isSatisfiedBy(assign_part_clone))
                        continue parcours_des_valeurs;
                
                // etape filtrage
                
                solutions(assign_part_clone, unassigned, all);
            }
        }
    }
}
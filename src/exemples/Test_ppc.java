
package exemples;

import ppc.BackTracking;
import representations.Rule;
import representations.Disjunction;
import representations.IncompatibilityConstraint;
import representations.Variable;
import representations.RestrictedDomain;
import representations.Catalogue;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public class Test_ppc 
{
    public static void main(String[] args)
    {
        
        //on instancie le catalogue, qui est la base de donnée utilisé pour ce package
        Catalogue c=new Catalogue();
        
        //on lance l'algorithme de backtracking
        BackTracking search = new BackTracking(c.AllConstraint());
        Set<Map<Variable, String>> solutions = search.allSolutions();
        
        int i = 0;
        
        //on affiche en console les differentes solutions trouvées
        for(Map<Variable, String> solution : solutions)
            System.out.println("map " + ++i + " :\n" + ToString.mapToString(solution));
    }
}

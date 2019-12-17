package exemples;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import ppc.*;
import representations.*;

/*
* une classe de test de l'algorithme Backtracking simplifié, pour comprendre le concept 
*/
public class Test_simple_Backtrack
{
    public static void main(String[] args)
    {
        //on instancie nos differentes variables et leur vaeurs

        Variable x = new Variable("x", new HashSet(Arrays.asList("1", "2", "3")));
        Variable y = new Variable("y", new HashSet(Arrays.asList("1", "2", "3")));

        Rule a = new Rule(
            new HashSet(Arrays.asList(new RestrictedDomain(x, new HashSet(Arrays.asList("3"))))),
            new HashSet(Arrays.asList(new RestrictedDomain(y, new HashSet(Arrays.asList("1", "2")))))
        );

        Rule b = new Rule(
            new HashSet(Arrays.asList(new RestrictedDomain(x, new HashSet(Arrays.asList("2"))))),
            new HashSet(Arrays.asList(new RestrictedDomain(y, new HashSet(Arrays.asList("1")))))
        );

        Rule c = new Rule(
            new HashSet(Arrays.asList(new RestrictedDomain(x, new HashSet(Arrays.asList("1"))))),
            new HashSet(Arrays.asList(new RestrictedDomain(y, new HashSet(Arrays.asList()))))
        );
        
        //on lance l'algorithme de Backtracking
        Set<Map<Variable, String>> maps = new BackTracking(new HashSet(Arrays.asList(a, b, c))).allSolutions();
        int i = 0;
        
        
        //on affiche le resultat de l'algorithme en console
        for(Map<Variable, String> map : maps)
            System.out.println("map" + ++i + "\n" + ToString.mapToString(map));
    }
}

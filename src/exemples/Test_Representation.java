package exemples;

import representations.Rule;
import representations.Variable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ppc.BackTracking;
import representations.Catalogue;
import sun.swing.BakedArrayList;

/**
 *
 * Classe de test et de presentation de la partie "Représentation des objets et
 * contraintes"
 */
public class Test_Representation {

    public static void main(String[] args) {
        //on crée une instance de notre Catalogue (Classe Generant toutes les variables et Regles necessaires )
        Catalogue c = new Catalogue();

        //On instancie 10 patients qui representerons chacun un état de maladie precis 
        Map<Variable, String> patient1 = new HashMap<>();
        Map<Variable, String> patient2 = new HashMap<>();
        Map<Variable, String> patient3 = new HashMap<>();
        Map<Variable, String> patient4 = new HashMap<>();
        Map<Variable, String> patient5 = new HashMap<>();
        Map<Variable, String> patient6 = new HashMap<>();
        Map<Variable, String> patient7 = new HashMap<>();
        Map<Variable, String> patient8 = new HashMap<>();
        Map<Variable, String> patient9 = new HashMap<>();
        Map<Variable, String> patient10 = new HashMap<>();

        //Pour chaque exemple du TP fil rouge, on genere les maladies chez le patient puis on teste si les contraintes sont bien respectés
        System.out.println("L'angine provoque une fièvre haute ou moyenne");
        patient1.put(c.angine, "oui");
        patient1.put(c.fievre, "haute");
        System.out.println(c.angineRule1.isSatisfiedBy(patient1));
        System.out.println("-------------");

        System.out.println("L'angine provoque la toux");
        patient2.put(c.angine, "oui");
        patient2.put(c.toux, "oui");
        System.out.println(c.angineRule2.isSatisfiedBy(patient2));
        System.out.println("-------------");

        System.out.println("Une grippe, en l'absence de vaccination, provoque une fièvre haute");
        patient3.put(c.grippe, "oui");
        patient3.put(c.vaccination, "non");
        patient3.put(c.fievre, "haute");
        System.out.println(c.grippeRule1.isSatisfiedBy(patient3));
        System.out.println("-------------");

        System.out.println("Une grippe, en l'absence de vaccination, provoque la fatigue");
        patient4.put(c.grippe, "oui");
        patient4.put(c.vaccination, "non");
        patient4.put(c.fatigue, "oui");
        System.out.println(c.grippeRule2.isSatisfiedBy(patient4));
        System.out.println("-------------");

        System.out.println("L'angine peut ou non être provoquée par un virus");
        patient5.put(c.angine, "oui");
        patient5.put(c.virus, "oui");
        System.out.println(c.angineRule3.isSatisfiedBy(patient5));
        System.out.println("-------------");

        //contre exemple " peut ou non " 
        System.out.println("L'angine peut ou non être provoquée par un virus");
        patient6.put(c.angine, "oui");
        patient6.put(c.virus, "non");
        System.out.println(c.angineRule3.isSatisfiedBy(patient6));
        System.out.println("-------------");

        System.out.println("Une grippe est toujours provoquée par un virus");
        patient7.put(c.grippe, "oui");
        patient7.put(c.virus, "oui");
        System.out.println(c.grippeRule3.isSatisfiedBy(patient7));
        System.out.println("-------------");

        System.out.println("La prise de sirop avec une allergie moyenne au sucre provoque des boutons");
        patient8.put(c.sirop, "oui");
        patient8.put(c.allergieSurcre, "moyenne");
        patient8.put(c.bouton, "oui");
        System.out.println(c.allergieRule1.isSatisfiedBy(patient8));
        System.out.println("-------------");

        System.out.println("La prise de sirop avec une allergie haute au sucre provoque un œdème");
        patient9.put(c.sirop, "oui");
        patient9.put(c.allergieSurcre, "haute");
        patient9.put(c.odeme, "oui");
        System.out.println(c.allergieRule2.isSatisfiedBy(patient9));
        System.out.println("-------------");

        System.out.println("On ne peut pas à la fois avoir une fièvre haute ou moyenne et être en hypothermie");
        patient10.put(c.fievre, "haute");
        patient10.put(c.hypothermie, "non");
        System.out.println(c.hypo.isSatisfiedBy(patient10));
        System.out.println("-------------");

    }

}

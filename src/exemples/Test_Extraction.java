
package exemples;

import extraction.AssociationRuleMiner;
import extraction.BooleanDatabase;
import extraction.DBReader;
import extraction.Database;
import extraction.FrequantITemsetMiner;
import extraction.Rule2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import representations.Variable;

/**
 *Classe de test du package extraction, implementant toutes les variables necessaires et la base de donné utilisée
 */
public class Test_Extraction {

    public static void main(String[] args) throws IOException {
    Set<String> domFievre=new HashSet<>();
    Set<String> domAllergie=new HashSet<>();
    
    Set<String> ouiNon=new HashSet<>();
    Set<String> oui=new HashSet<>();
    Set<String> non=new HashSet<>();
    Set<String> fievreHaute=new HashSet<>();
    Set<String> fievreMoyenne=new HashSet<>();
    Variable toux;
    Variable angine;
    Variable fievre;
    Variable virus;
    Variable hypothermie;
    Variable grippe;
    Variable vaccination;
    Variable fatigue;
    Variable sirop;
    Variable allergieSurcre;
    Variable bouton;
    Variable odeme;
    domFievre.add("basse");
        domFievre.add("moyenne");
        domFievre.add("haute");
        domAllergie.add("basse");
        domAllergie.add("moyenne");
        domAllergie.add("haute");
        ouiNon.add("oui");
        ouiNon.add("non");
        oui.add("oui");
        non.add("non");
        fievreHaute.add("haute");
        fievreMoyenne.add("moyenne");
        
        toux=new Variable("toux", ouiNon);
        angine=new Variable("angine", ouiNon);
        fievre=new Variable("fievre", domFievre);
        virus=new Variable("virus", ouiNon);
        hypothermie=new Variable("hypo", ouiNon);
        grippe=new Variable("grippe", ouiNon);
        vaccination=new Variable("vaccination", ouiNon);
        fatigue=new Variable("fatigue", ouiNon);
        sirop=new Variable("sirop", ouiNon);
        allergieSurcre=new Variable("allergie", domAllergie);
        bouton=new Variable("bouton", ouiNon);
        odeme=new Variable("odeme", ouiNon);
        
        Set<Variable> l=new HashSet<>();
        l.add(toux);
        l.add(angine);
        l.add(fievre);
        l.add(virus);
        l.add(hypothermie);
        l.add(grippe);
        l.add(vaccination);
        l.add(fatigue);
        l.add(sirop);
        l.add(allergieSurcre);
        l.add(bouton);
        l.add(odeme);
        ArrayList<Variable> variables=new ArrayList<>(l);
        
        
        //on integere la base de donnée sous forme de Database grace a la classe DB reader.
        DBReader db = new DBReader(l);
        Database db2 = db.importDB("src/exemples/db_b1_n1000_p01.csv");
        ArrayList<HashMap<Variable,String>> u=new ArrayList<>();
        
        //on passe la Database integré sous forme de base de donnée booléenne ( BooleanDatabase).
        u=db2.toBoolean();
        ArrayList<Map<Variable,String>> f=new ArrayList<>(u);
        
        ArrayList<Variable> vari=new ArrayList<>();
        //on ressort les variables concerné par la base BDD dans une arraylist Vari
        for (Map<Variable, String> map : f) {
            boolean b=true;
            for (Map.Entry<Variable, String> entry : map.entrySet()) {
                Variable key = entry.getKey();
                String value = entry.getValue();
                vari.add(key);
            }
            if(b){
                break;
            }
           
        }
        
        
        //on instancie la base de donnée 
        BooleanDatabase bd=new BooleanDatabase(vari, f);
        
        
        //on ressort les itemsets frequent de notre BDD avec comme frequence minimal 300
        FrequantITemsetMiner fr=new FrequantITemsetMiner(bd);
        Map<ArrayList<Variable>, Integer> liste= fr.frequentItemsets(300);
        
        
        //on parcour notre resultat pour l'afficher en Console
        for (Map.Entry<ArrayList<Variable>, Integer> entry : liste.entrySet()) {
            ArrayList<Variable> key = entry.getKey();
            for (Variable variable : key) {
                System.out.print(variable.getNom()+" ");
            }
            Integer value = entry.getValue();
            System.out.println("("+value+")");
            
        }
        
        //on crée les regles extraites de notre base de donnée
        AssociationRuleMiner associationRuleMiner=new AssociationRuleMiner(liste, bd);
        //associationRuleMiner.generateRegles(1);
        
        //puis on affiche ces regles en console
        //System.out.println(associationRuleMiner.getRules(60));
        System.out.println("------------------------ Les regles -----------------------------------");
        for (Rule2 rule : associationRuleMiner.getRules(60)) {
            System.out.println(rule);
        }
        
        
        
        
        
        
        
        
      
       
    }
    
    
}

package exemples;

import representations.Variable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import planning.Action;
import planning.State;

/**
 * Classe servant de base de donnée aux Package Planning
 *
 */
public class HealthCare {

    //initialisation de toutes les variables d'action ( correspondant a des medicaments)
    ArrayList<String> bool;
    ArrayList<String> domain;
    Variable ANGINA;
    Variable FLU;
    Variable POX;
    Variable PLAGUE;
    Variable FEVER;
    Variable COUGH;
    Variable BUTTONS;
    Action SYRUP_BUTTONS_HIGH;
    Action SYRUP_BUTTONS_MEDIUM;
    Action SYRUP_BUTTONS_LOW;
    Action SYRUP_FEVRER_HIGH;
    Action SYRUP_FEVRER_MEDIUM;
    Action SYRUP_FEVRER_LOW;
    Action SYRUP_COUGH_HIGH;
    Action SYRUP_COUGH_MEDIUM;
    Action SYRUP_COUGH_LOW;
    Action ActionGuerison;
    State intialState;
    State goalState;
    ArrayList<Action> actions;

    /**
     * Constructeur de classe, appel toutes les autres methodes de generation
     */
    public HealthCare() {
        generateDomaines();
        generateVariables();
        generateSYRUP();
        generateActionGuerison();
        generateStates();
        getActions();
    }

    /**
     * getters d'etats
     *
     * @return les differents etats demandées
     */
    public State getIntialState() {
        return intialState;
    }

    public State getGoalState() {
        return goalState;
    }

    /**
     * Genere les differents domaines utilisables ( boolean ou a niveaux)
     */
    void generateDomaines() {
        bool = new ArrayList<>();
        bool.add("oui");
        bool.add("non");

        domain = new ArrayList<>();
        domain.add("high");
        domain.add("medium");
        domain.add("low");
        domain.add("none");
    }

    /**
     * genere les variables de maladie (etats du patiens) et leurs differents
     * etats (domaines)
     */
    void generateVariables() {
        Set<String> d1 = new HashSet<>(bool);
        ANGINA = new Variable("ANGINA", d1);
        FLU = new Variable("FLU", d1);
        POX = new Variable("POX", d1);
        PLAGUE = new Variable("PLAGUE", d1);

        Set<String> d2 = new HashSet<>(domain);
        FEVER = new Variable("FEVER", d2);
        COUGH = new Variable("COUGH", d2);
        BUTTONS = new Variable("BUTTONS", d2);

    }

    /**
     * getter des variables initialement crée
     *
     * @return Arrayliste de variables de toutes les maladies
     */
    ArrayList<Variable> getVariables() {
        ArrayList<Variable> liste = new ArrayList<>();
        liste.add(ANGINA);
        liste.add(FLU);
        liste.add(POX);
        liste.add(PLAGUE);
        liste.add(FEVER);
        liste.add(COUGH);
        liste.add(BUTTONS);
        return liste;
    }

    /**
     * getter des symptomes (variables ayant des domaines a niveaux)
     *
     * @return ArrayList de ces symptomes
     */
    ArrayList<Variable> getSymptomes() {
        ArrayList<Variable> liste = new ArrayList<>();
        liste.add(FEVER);
        liste.add(COUGH);
        liste.add(BUTTONS);
        return liste;
    }

    /**
     * getter des maladies (variables ayant des domaines boolean)
     *
     * @return ArrayList de ces maladies
     */
    ArrayList<Variable> getMaladies() {
        ArrayList<Variable> liste = new ArrayList<>();
        liste.add(ANGINA);
        liste.add(FLU);
        liste.add(POX);
        liste.add(PLAGUE);
        return liste;
    }

    /**
     * Genere les actions de prise de sirop ( pour les symptomes ) Chaque action
     * a une premisse ( etat initial) et un effet (etat final )
     *
     * Ces symptomes etant des variables a niveaux, plusieurs actions par
     * symptomes sont necessaires (Passer de High à Medium.. etc)
     */
    void generateSYRUP() {
        Map<Variable, String> pre = new HashMap<>();
        pre.put(BUTTONS, "high");

        Map<Variable, String> eff = new HashMap<>();
        eff.put(BUTTONS, "medium");
        SYRUP_BUTTONS_HIGH = new Action("SYRUP_BUTTONS_HIGH", pre, eff);

        Map<Variable, String> pr2 = new HashMap<>();
        pr2.put(BUTTONS, "medium");
        Map<Variable, String> eff2 = new HashMap<>();
        eff2.put(BUTTONS, "low");
        SYRUP_BUTTONS_MEDIUM = new Action("SYRUP_BUTTONS_MEDIUM", pr2, eff2);

        Map<Variable, String> pr3 = new HashMap<>();
        pr3.put(BUTTONS, "low");
        Map<Variable, String> eff3 = new HashMap<>();
        eff3.put(BUTTONS, "none");
        SYRUP_BUTTONS_LOW = new Action("SYRUP_BUTTONS_LOW", pr3, eff3);
        ///////////
        Map<Variable, String> preF = new HashMap<>();
        preF.put(FEVER, "high");

        Map<Variable, String> effF = new HashMap<>();
        effF.put(FEVER, "medium");
        SYRUP_FEVRER_HIGH = new Action("SYRUP_FEVRER_HIGH", preF, effF);

        Map<Variable, String> pr2F = new HashMap<>();
        pr2F.put(FEVER, "medium");
        Map<Variable, String> eff2F = new HashMap<>();
        eff2F.put(FEVER, "low");
        SYRUP_FEVRER_MEDIUM = new Action("SYRUP_FEVRER_MEDIUM", pr2F, eff2F);

        Map<Variable, String> pr3F = new HashMap<>();
        pr3F.put(FEVER, "low");
        Map<Variable, String> eff3F = new HashMap<>();
        eff3F.put(FEVER, "none");
        SYRUP_FEVRER_LOW = new Action("SYRUP_FEVRER_LOW", pr3F, eff3F);
        ////////

        Map<Variable, String> preC = new HashMap<>();
        preC.put(COUGH, "high");

        Map<Variable, String> effC = new HashMap<>();
        effC.put(COUGH, "medium");
        SYRUP_COUGH_HIGH = new Action("SYRUP_COUGH_HIGH", preC, effC);

        Map<Variable, String> pr2C = new HashMap<>();
        pr2C.put(COUGH, "medium");
        Map<Variable, String> eff2C = new HashMap<>();
        eff2C.put(COUGH, "low");
        SYRUP_COUGH_MEDIUM = new Action("SYRUP_COUGH_MEDIUM", pr2C, eff2C);

        Map<Variable, String> pr3C = new HashMap<>();
        pr3C.put(COUGH, "low");
        Map<Variable, String> eff3C = new HashMap<>();
        eff3C.put(COUGH, "none");
        SYRUP_COUGH_LOW = new Action("SYRUP_COUGH_LOW", pr3C, eff3C);

    }

    /**
     * des actions qui représentant n médicaments expérimentaux générés
     * aléatoirement qui traitent les symptomes
     *
     * @param n : nombre de medicaments a generer
     * @return Une arraylist d'action representant les differents medicaments a
     * utiliser
     */
    ArrayList<Action> generateActions(int n) {
        //on instancie la liste de medicaments a retourner
        ArrayList<Action> medicaments = new ArrayList<>();

        boolean bool = true;
        //pour chaque medicament a generer
        for (int i = 0; i < n; i++) {
            //on créer une action pour ce medicament
            Action action1;
            do {
                bool = true;
                //on instancie leur premisse et effet
                Map<Variable, String> pre = new HashMap<>();
                Map<Variable, String> eff = new HashMap<>();
                ArrayList<Variable> list = getSymptomes();

                int x;
                //on cherche des effets aleatoiress
                do {
                    x = (int) (Math.random() * 100);
                } while (x >= list.size());

                Variable u = list.get(x);
                eff.put(list.get(x), "none");

                for (Variable variable : list) {
                    if (variable != u) {

                        ArrayList<String> s = new ArrayList<>(variable.getDomaine());
                        do {
                            x = (int) (Math.random() * 10);
                        } while (x >= s.size());
                        eff.put(variable, s.get(x));
                    }
                }
                
                //on crée la maladie avec l'action au effet aleatoires
                action1 = new Action("", pre, eff);
                
                //on cherche le bon effet a pour cette maladie 
                for (Action medicament : medicaments) {
                    if (action1.effet.equals(medicament.effet)) {
                        bool = false;
                    }
                }

            } while (bool == false);
            Action ac = new Action("medicament" + i, action1.precondition, action1.effet);
            //on ajoute l'action du medicament a notre resultat
            medicaments.add(ac);

        }
        return medicaments;
    }

    /**
     * Generation d'une action de Guerison Basique, mettant tout les symptomes et maladies  sur non . 
     */
    void generateActionGuerison() {
        Map<Variable, String> pre = new HashMap<>();
        pre.put(FEVER, "none");
        pre.put(COUGH, "none");
        pre.put(BUTTONS, "none");

        Map<Variable, String> eff = new HashMap<>();
        eff.put(ANGINA, "non");
        eff.put(FLU, "non");
        eff.put(POX, "non");
        eff.put(PLAGUE, "non");

        ActionGuerison = new Action("ActionGuerison", pre, eff);
    }

    /**
     * Methode qui génère aléatoirement un état intial : une maladie et un
     * ensemble de symptomes avec un certain niveau
     */
    void generateStates() {
        //on genere tout d'abord l'etat initial : une maladie et des symptomes 
        ArrayList<Variable> syp = getSymptomes();
        Map<Variable, String> p1 = new HashMap<>();
        //on parcourt tout les symptomes pour en choisir de facon aleatoire
        for (Variable variable : syp) {
            ArrayList<String> dom = new ArrayList<>(variable.getDomaine());
            Random rand = new Random();
            String val = dom.get(rand.nextInt(dom.size()));
            p1.put(variable, val);
        }
        //on ajoute également une maladie aleatoire a notre etat initial 
        ArrayList<Variable> mal = getMaladies();
        Random rand = new Random();
        Variable var = mal.get(rand.nextInt(mal.size()));
        p1.put(var, "oui");
        
        //on verifie de bien desactiver les autres maladies en les instanciant 
        for (Variable variable : mal) {
            if (variable.getNom() != var.getNom()) {
                p1.put(variable, "non");
            }
        }

        
        //on genere enfin l'etat final mettant tout les symptomes et maladie sur l'etat negatif 
        Map<Variable, String> p2 = new HashMap<>();
        p2.put(ANGINA, "non");
        p2.put(FLU, "non");
        p2.put(POX, "non");
        p2.put(PLAGUE, "non");
        p2.put(FEVER, "none");
        p2.put(COUGH, "none");
        p2.put(BUTTONS, "none");

        //on applique nos deux etat a l'etat initial et final de la classe 
        this.intialState = new State(p1);
        this.goalState = new State(p2);

    }

    /**
     * Getter des differents actions generé 
     */
    void getActions() {
        ArrayList<Action> a = new ArrayList<>();
        a.add(ActionGuerison);
        a.add(SYRUP_BUTTONS_HIGH);
        a.add(SYRUP_BUTTONS_MEDIUM);
        a.add(SYRUP_BUTTONS_LOW);
        a.add(SYRUP_COUGH_HIGH);
        a.add(SYRUP_COUGH_MEDIUM);
        a.add(SYRUP_COUGH_LOW);
        a.add(SYRUP_FEVRER_HIGH);
        a.add(SYRUP_FEVRER_MEDIUM);
        a.add(SYRUP_FEVRER_LOW);
        //37 combinaison
        ArrayList<Action> t = generateActions(37);
        a.addAll(t);
        this.actions = a;
    }

    public ArrayList<Action> getAllActions() {
        return this.actions;
    }

}

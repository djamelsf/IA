/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package representations;

import java.util.HashSet;
import java.util.Set;

/**
 *Cette classe est la base de donnée de notre Package representations, reutilisé dans beaucoup d'autres classes exterieures
 * 
 * On instancie toutes les variables, domaines, valeurs, et regles que nous aurons a utiliser dans cet exemple 
 */
public class Catalogue {
    Set<String> domFievre=new HashSet<>();
    Set<String> domAllergie=new HashSet<>();
    
    Set<String> ouiNon=new HashSet<>();
    Set<String> oui=new HashSet<>();
    Set<String> non=new HashSet<>();
    Set<String> fievreHaute=new HashSet<>();
    Set<String> fievreMoyenne=new HashSet<>();
    public Variable toux;
    public Variable angine;
    public Variable fievre;
    public Variable virus;
    public Variable hypothermie;
    public Variable grippe;
    public Variable vaccination;
    public Variable fatigue;
    public Variable sirop;
    public Variable allergieSurcre;
    public Variable bouton;
    public Variable odeme;
    public Rule angineRule1;
    public Rule angineRule2;
    public Rule grippeRule1;
    public Rule grippeRule2;
    public Rule grippeRule3;
    public Rule angineRule3;
    public Rule hypo;
    public Rule allergieRule1;
    public Rule allergieRule2;
//constructeur de la classe, qui lance toutes les autres methodes de generation
    public Catalogue() {
        generateDomaines();
        generateVariables();
        generateRA1();
        generateRA2();
        generateRA3();
        generateHypo();
        generateGrippe();
        generateGrippe2();
        generateGrippe3();
        generateAllergie1();
        generateAllergie2();
        generateHypo();
    }
    //on ajoute les valeurs correspondantes a chaque domaine crée
    public void generateDomaines(){
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
    }
    
    //on attribue à chaque variable le domaine correspondant
    
    public void generateVariables(){
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
    }
    
    //Getter de toutes les variables disponibles
    Set<Variable> AllVariables(){
        Set<Variable> a=new HashSet<>();
        a.add(toux);
        a.add(angine);
        a.add(fievre);
        a.add(virus);
        a.add(hypothermie);
        a.add(grippe);
        a.add(vaccination);
        a.add(fatigue);
        a.add(sirop);
        // 3 last
        // 2 
        a.add(bouton);
        a.add(allergieSurcre);
        
        a.add(odeme);
        return a;
    }
    
    
    //Getter de toutes les contraintes disponibles 
    public Set<Constraint> AllConstraint(){
        Set<Constraint> a=new HashSet<>();
        a.add(angineRule1);
        a.add(angineRule2);
        a.add(angineRule3);
        a.add(grippeRule1);
        a.add(grippeRule2);
        a.add(hypo);
        a.add(grippeRule3);
        // 3 last
        //2
        a.add(allergieRule1);
        a.add(allergieRule2);
       
        return a;
    }
    
    //on genere toutes les regles avec leur domaines et leur variables 
    
    void generateRA1(){
        Set<String> domF=new HashSet<>();
        domF.add("moyenne");
        domF.add("haute");
        Set<RestrictedDomain> premisse=new HashSet<>();
        premisse.add(new RestrictedDomain(angine,oui));

        Set<RestrictedDomain> conclusion=new HashSet<>();
        conclusion.add(new RestrictedDomain(fievre,domF));
        
        angineRule1=new Rule(premisse, conclusion);
    }
    
    void generateRA2(){
        Set<RestrictedDomain> premisse2=new HashSet<>();
        premisse2.add(new RestrictedDomain(angine, oui));
        
        Set<RestrictedDomain> conclusion2=new HashSet<>();
        conclusion2.add(new RestrictedDomain(toux, oui));
        
        angineRule2=new Rule(premisse2, conclusion2);
    }
    
    void generateGrippe3(){
        Set<RestrictedDomain> premisse2=new HashSet<>();
        premisse2.add(new RestrictedDomain(grippe, oui));
        
        Set<RestrictedDomain> conclusion2=new HashSet<>();
        conclusion2.add(new RestrictedDomain(virus, oui));
        
        grippeRule3=new Rule(premisse2, conclusion2);
    }
    
    void generateGrippe(){
        Set<RestrictedDomain> premisse=new HashSet<>();
        premisse.add(new RestrictedDomain(grippe, oui));
        premisse.add(new RestrictedDomain(vaccination, non));
        
        Set<RestrictedDomain> conclusion=new HashSet<>();
        conclusion.add(new RestrictedDomain(fievre, fievreHaute));
        
        grippeRule1=new Rule(premisse, conclusion);   
    }
    
    void generateGrippe2(){
        Set<RestrictedDomain> premisse=new HashSet<>();
        premisse.add(new RestrictedDomain(grippe, oui));
        premisse.add(new RestrictedDomain(vaccination, non));
        
        Set<RestrictedDomain> conc=new HashSet<>();
        conc.add(new RestrictedDomain(fatigue, oui));
        
        grippeRule2=new Rule(premisse, conc);    
    }
    
    void generateAllergie1(){
        Set<String> domF=new HashSet<>();
        domF.add("moyenne");
        
        Set<RestrictedDomain> premisse=new HashSet<>();
        premisse.add(new RestrictedDomain(sirop, oui));
        premisse.add(new RestrictedDomain(allergieSurcre,domF));
        
        Set<RestrictedDomain> conc=new HashSet<>();
        conc.add(new RestrictedDomain(bouton, oui));
        
        this.allergieRule1=new Rule(premisse, conc);
    }
    
    void generateAllergie2(){
        Set<String> domF=new HashSet<>();
        domF.add("haute");
        
        Set<RestrictedDomain> premisse=new HashSet<>();
        premisse.add(new RestrictedDomain(sirop, oui));
        premisse.add(new RestrictedDomain(allergieSurcre,domF));
        
        Set<RestrictedDomain> conc=new HashSet<>();
        conc.add(new RestrictedDomain(odeme, oui));
        
        this.allergieRule2=new Rule(premisse, conc);
    }
    
     void generateRA3(){
        Set<RestrictedDomain> pre=new HashSet<>();
        pre.add(new RestrictedDomain(angine, oui));
        
        Set<RestrictedDomain> conc=new HashSet<>();
        conc.add(new RestrictedDomain(virus, ouiNon));
        
        this.angineRule3=new Rule(pre, conc);   
    }
     
     void generateHypo(){
        Set<String> domF=new HashSet<>();
        domF.add("haute");
        domF.add("moyenne");
        
        Set<RestrictedDomain> pre=new HashSet<>();
        pre.add(new RestrictedDomain(fievre, domF));
        pre.add(new RestrictedDomain(hypothermie, oui));
        
        this.hypo=new IncompatibilityConstraint(pre);
          
    }
    
}

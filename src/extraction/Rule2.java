
package extraction;

import java.util.ArrayList;
import representations.Variable;

/**
 *cette classe est une réédition de la Classe rule du Package Representations
 * Cette reedition est utile dans la création de regles extraite de base de donnée (Assocation Rule MIner)
 */
public class Rule2 {
    //on supprime le concept de "Restricted Domain" pour ne laisser que des ArrayList, plus facilement manipulables
    ArrayList<Variable> premisse;
    ArrayList<Variable> conclusion;
    ArrayList<Variable> all;

    public Rule2(ArrayList<Variable> premisse, ArrayList<Variable> conclusion,ArrayList<Variable> all) {
        this.premisse = premisse;
        this.conclusion = conclusion;
        this.all=all;
    }
    
    ArrayList<Variable> getScope(){
        ArrayList<Variable> a=new ArrayList<>();
        a.addAll(premisse);
        a.addAll(conclusion);
        return  a;
    }
    
    //Getters et Setters correspondant au variables 
    public ArrayList<Variable> getPremisse() {
        return premisse;
    }

    public void setPremisse(ArrayList<Variable> premisse) {
        this.premisse = premisse;
    }

    public ArrayList<Variable> getConclusion() {
        return conclusion;
    }

    public void setConclusion(ArrayList<Variable> conclusion) {
        this.conclusion = conclusion;
    }
    
    // cette methode est un equivalent de "isSatisfiedBy", verifiant si la regle instanciée est valide ou non. 
    public boolean isValid(){
        ArrayList<Variable> t=new ArrayList<>();
        t.addAll(premisse);
        t.addAll(conclusion);
        
        boolean bool=true;
        for (Variable variable : premisse) {
            if(conclusion.contains(variable)){
                bool=false;
                break;
            }
        }
        for (Variable variable : conclusion) {
            if(premisse.contains(variable)){
                bool=false;
                break;
            }
        }
        
        if(t.size()!=this.all.size() || bool==false){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        String s="";
        for (Variable variable : premisse) {
            s+=" "+variable.getNom()+" ";
        }
        s+="--->";
        for (Variable variable : conclusion) {
            s+=" "+variable.getNom()+" ";
        }
        return s;
    }
    
    
    

    
    
    
    
}

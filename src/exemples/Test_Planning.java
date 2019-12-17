/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exemples;

import representations.Variable;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import exemples.HealthCare;
import planning.PlanningProblem;
import planning.PlanningProblemWithCost;
import planning.State;

/**
 *Classe de test du Package Planning
 */
public class Test_Planning {
    public static void main(String[] args) {
        //on instancie la classe Healthcare, la base de donnée utilisé dans ce package 
        HealthCare h=new HealthCare();
        PlanningProblem p1=new PlanningProblem(h.getIntialState(), h.getGoalState(), h.getAllActions());
        Stack plan=new Stack();
        ArrayList<State> closed=new ArrayList<>();
        PlanningProblemWithCost p2=new PlanningProblemWithCost(h.getIntialState(), h.getGoalState(), h.getAllActions());
        //afficher l'etat initiale
        System.out.println("---------Intial State---------");
        for (Map.Entry<Variable, String> en : h.getIntialState().getMap().entrySet()) {
            Variable key = en.getKey();
            String value = en.getValue();
            System.out.println(key.getNom()+"=="+value);
        }
        System.out.println("------------------------------");
        
        
        //Ci-dessous les differents algorithme decommentables à tester. 
       
        //System.out.println(p1.BFS());
        //System.out.println(p1.DFS(h.getIntialState(), plan, closed));
        //System.out.println(p2.Dijkstra());
        System.out.println(p2.aStar());
    }
    
}

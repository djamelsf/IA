/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *Classe implement les algorithmes de recherche BFS et DFS
 */
public class PlanningProblem {
    State initialState;
    State finalState;
    ArrayList<Action> actions;

    public PlanningProblem(State initialState, State finalState, ArrayList<Action> actions) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.actions = actions;
    }
    /*
    * Dfs est un algoritme de recherche en profondeur dans un arbe 
    * @param state : etat initial 
    * @param plan : Stack vide servant de  depot temporaire dans l'algorithme 
    * @param closed : Arraylist vide, representant les chemin fermés (inutiles) 
    */
    public Stack DFS(State state, Stack plan, ArrayList<State> closed){
            Stack subplan;
            State next;            
            if(Action.satisfies(state, this.finalState)){
                return plan;
            }
            else{
                for(Action action : this.actions){
                    if(Action.is_applicable(action,state)){  
                        next = Action.apply(action,state);
                        if(!closed.contains(next)){
                            plan.push(action);
                            closed.add(next);
                            subplan = DFS(next,plan,closed);
                            if(!subplan.isEmpty()){
                                return subplan;
                            }
                            else{    
                                plan.pop();
                            }
                        }
                    }
                }
                return null;
            }
        }
    
    /*
    * Bfs est un algoritme de recherche en largeur dans un arbe 
    * @param state : etat initial 
    * @param open  : liste representant les chemin encore ouverts a parcourir
    * @param closed : Arraylist vide, representant les chemin fermés (inutiles) 
    */
     public ArrayList<Action> BFS(){             
        State next;
        State state;
        HashMap<State,State> father = new HashMap<>();
        HashMap<State,Action> plan = new HashMap<>();
        ArrayList<State> closed = new ArrayList<>();
        Queue<State> open = new LinkedList<>();           
            open.add(this.initialState);
            father.put(this.initialState,null);
            while(!open.isEmpty()){
                state = open.remove(); 
                closed.add(state);
                for(Action action : this.actions){
                    if(Action.is_applicable(action, state)){
                        next = Action.apply(action, state);
                        if(!closed.contains(next) && !open.contains(next)){
                            father.put(next, state);
                            plan.put(next, action);
                            if(Action.satisfies(next,this.finalState)){
                                return get_bfs_plan(father,plan,next);
                            }
                            else{
                                open.add(next);
                            }
                        }
                    }
                }
            }
            return null;
        }
     
     //methode appelé a la fin de l'algorithme BFS, permettant de representer les actions a effectuer dans l'ordre . 
     public static ArrayList<Action> get_bfs_plan(HashMap<State,State> father, HashMap<State,Action> action, State goal ){
            ArrayList<Action> plan = new ArrayList<>();
            while(goal != null){
                plan.add(action.get(goal));
                goal = father.get(goal);
            }
            Collections.reverse(plan); 
            return plan;
        } 
    
}

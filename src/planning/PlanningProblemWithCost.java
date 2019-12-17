/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

import representations.Variable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *  Classe implement les algorithmes de recherche Dijkstra et A*
 */
public class PlanningProblemWithCost {
    State initialState;
    State finalState;
    ArrayList<Action> actions;

    public PlanningProblemWithCost(State initialState, State finalState, ArrayList<Action> actions) {
        this.initialState = initialState;
        this.finalState = finalState;
        this.actions = actions;
    }
    //Chaque plan a un cout , representant la sommes des cout des actions ( on admet que les sirops coutent 2 et les medicaments 1)
    int cost(Action action){
        if(action.effet.size()==1){
            return 2;
        }else{
            return 1;
        }
    }
    
    
     //methode appelé a la fin de l'algorithme Dijsktra, permettant de representer les actions a effectuer dans l'ordre . 
    public ArrayList<Action> get_dijkstra_plan(HashMap<State,State> father,HashMap<State,Action> actions, ArrayList<State> goals, HashMap<State,Integer> distance){
            ArrayList<Action> plan = new ArrayList<>();
            State goal;
            goal = argmin(distance,goals );
            while(goal!=null){
                plan.add(actions.get(goal));
                goal = father.get(goal);
            }
            Collections.reverse(plan);
            return plan;
        }
    
    
    
     public State argmin(HashMap<State,Integer> distance, ArrayList<State> open){
            State s = open.get(0);
            int argmin = distance.get(s);
            
            for(State state : open) {
                if(distance.containsKey(state)){
                    if(distance.get(state)<argmin){
                        argmin = distance.get(state);
                        s = state;
                    }
                }
            }
            return s;
        }
    /*
     * Algorithme de recherche(implementant des couts) 
     
     * @param state : etat initial
     * @param next : etat suivant dans le deroulement dans l'algorithme
     * @param open  : liste representant les chemin encore ouverts a parcourir
     * @param distance : Arraylist representant les deifferents etat suivant et leur distant (le cout pour les atteindre)
     * @param father : ArrayList representant l'etat precedent dans le deroulement de l'algorithme 
     * @param goals : etats fianux a atteindre 
     */
    public ArrayList<Action> Dijkstra(){
      State next;
      State state;
      HashMap<State,Integer> distance = new HashMap<>();
      HashMap<State,State> father = new HashMap<>();
      HashMap<State,Action> plan = new HashMap<>();
      ArrayList<State> goals = new ArrayList<>();
      ArrayList<State> open = new ArrayList<>();
      open.add(this.initialState);
      distance.put(this.initialState,0);
      father.put(this.initialState,null);
      while(!open.isEmpty()){

          state = argmin(distance,open);
          open.remove(state);
          if(Action.satisfies(state,this.finalState)){
              goals.add(state);   
          }
          for(Action action : this.actions){                        
              if(Action.is_applicable(action,state)){
                  next = Action.apply(action, state);
                  if(!containsNext(distance, next)){
                      distance.put(next, Integer.MAX_VALUE);
                  } 
                  if(distance.get(getNext(distance, next)) > (distance.get(state)+cost(action))){                           
                      distance.put(next,(distance.get(state)+cost(action)));
                      father.put(next,state);
                      plan.put(next, action);
                      open.add(next);
                  }
              }
          }
       }
      return get_dijkstra_plan(father,plan,goals,distance);
    }
         
      //methode qui verifie si les etats suivant (distance )à parcourir dans un algorithe contiennet l'etat recherché (next) mit en attribut   
      boolean containsNext(HashMap<State,Integer> distance,State next){
          //on parcourt les etats suivants
          for (Map.Entry<State, Integer> entry : distance.entrySet()) {
              State key = entry.getKey();
              Integer value = entry.getValue();
              //on verifie la presence de l'etat next. 
              if(key.equals(next)){
                  return true;
              }
          }
          return false;
      }
      
      //methode qui retourne l'etat recherché (next) si il est contenu dans les etats suivants de l'algorithme (distance)
      State getNext(HashMap<State,Integer> distance,State next){
          for (Map.Entry<State, Integer> entry : distance.entrySet()) {
              State key = entry.getKey();
              Integer value = entry.getValue();
              if(key.equals(next)){
                  return key;
              }
          }
          return null;
          
      }
      
      
      
      /*
     * Algorithme de recherche heuristique (implementant des couts) optimisé
     * @param value : arraylist implement le concept d'heuristique dans les couts avec la methode getheuristic().
     * @param open  : liste representant les chemin encore ouverts a parcourir
     * @param state : etat initial
     * @param distance : Arraylist representant les deifferents etat suivant et leur distant (le cout pour les atteindre)
     * @param father : ArrayList representant l'etat precedent dans le deroulement de l'algorithme 
      
     */
       public ArrayList<Action> aStar(){
            ArrayList<State> open = new ArrayList<>();
            open.add(this.initialState);
            HashMap<State,Integer> distance = new HashMap<>();
            distance.put(initialState,0);
            HashMap<State,State> father = new HashMap<>();
            father.put(initialState, null);
            HashMap<State,Integer> value = new HashMap<>();
            SimpleHeuristic sH=new SimpleHeuristic(initialState);
            value.put(initialState, sH.getHeuristic());
            HashMap<State,Action> plan = new HashMap<>();
            while(!open.isEmpty()){
                //System.out.println(open.size());
                State state = argmin(distance,open);
                if(Action.satisfies(state,this.finalState)){
                   return PlanningProblem.get_bfs_plan(father, plan , state );
                }
                else{
                    open.remove(state);
                    for(Action action : this.actions){
                        if(Action.is_applicable(action, state)){
                            State next = Action.apply(action,state);
                            if(!containsNext(distance, next)){
                                distance.put(next, Integer.MAX_VALUE);
                            }
                            if(distance.get(getNext(distance, next))>distance.get(state)+cost(action)){
                                distance.put(next, distance.get(state)+cost(action));
                                SimpleHeuristic sHs=new SimpleHeuristic(next);
                                value.put(next, distance.get(next)+sHs.getHeuristic());
                                father.put(next,state);
                                plan.put(next,action);
                                open.add(next);
                            }
                        }
                    }
                }
            }
            return null;
            
        }
       
       
       
         //methode appelé a la fin de l'algorithme aStar, permettant de representer les actions a effectuer dans l'ordre . 
        public ArrayList<Action> get_aStar_plan(HashMap<State,State> father, HashMap<State,Action> action, State goal ){
            ArrayList<Action> plan = new ArrayList<>();
            while(goal != null){
                plan.add(action.get(goal));
                goal = father.get(goal);
            }
            Collections.reverse(plan);
            return plan;
        }
         
         
         
    
}

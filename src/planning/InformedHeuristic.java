/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planning;

/**
 *
 */
public class InformedHeuristic implements Heuristic{
    State state;

    public InformedHeuristic(State state) {
        this.state = state;
    }
    

    @Override
    public int getHeuristic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}

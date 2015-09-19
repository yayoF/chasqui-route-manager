/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

/**
 *
 * @author Joca
 */
public class TabuSearch {
    
    protected int maxIteration = 10000;
    
    public TabuSearch(int maxIteration) {
        this.maxIteration = maxIteration;
    }
    
    public void execute () {
        
        int iteration = 0;
        
        while (! this.stoppingCondition(iteration)) {
            
            
            System.out.println(" iteration " + iteration);
           
            iteration ++;
          
        }                        
        
    }
    
    protected boolean stoppingCondition(int iteration) {
        
        return iteration >= this.maxIteration;
    
    }
}

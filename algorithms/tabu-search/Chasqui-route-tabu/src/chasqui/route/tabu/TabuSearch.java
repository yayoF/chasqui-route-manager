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
    
    protected int maxIteration = 1000;
    protected SolutionCandidate candidate;
    
    public TabuSearch(int maxIteration) {
        
        this.maxIteration = maxIteration;
        
    }
    
    public void execute () {
        
        int iteration = 0;
        
        //generate a optimize initial solutioncandidate
        candidate = generateInitialSolution();
        
        while (! this.stoppingCondition(iteration)) {
                                    
            System.out.println(" iteration " + iteration);
           
            iteration ++;
          
        }                        
        
    }
    
    protected SolutionCandidate generateInitialSolution() {
        return new SolutionCandidate();
    }
    
    protected boolean stoppingCondition(int iteration) {
        
        return iteration >= this.maxIteration;
    
    }
    
    protected boolean evaluateFitnessValue(int fitness1, int fitness2) {
        
       return fitness1 > fitness2;
       
    }
}

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
public class ChasquiRouteTabu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
 

        TabuSearch chasquiOracle = new TabuSearch(0);
        
        chasquiOracle.execute();
        
        SolutionCandidate initialSolution = chasquiOracle.getSolution();
        
        for (Route route : initialSolution.getRoutes()) {
            for (Node node : route.getNodeList()) {
                System.out.print(node.getId() + " - ");
            }
            System.out.println("");
        }
        // TODO code application logic here
    }
    
}

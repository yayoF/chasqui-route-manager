/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Joca
 */
public class ChasquiRouteTabu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

          Node a = new Node(1, 0, 0, 0);
          Node b = new Node(2, 0, 0, 0);
          Route r = new Route(a);
          r.addNode(b);
          SolutionCandidate s = new SolutionCandidate();
          SolutionCandidate s2 = new SolutionCandidate();
          s.addRoute(r);

           ArrayList<SolutionCandidate> tabuList = new ArrayList();
           tabuList.add(s);
           
           System.out.println(tabuList.contains(s));
           System.out.println(tabuList.contains(s2));
          
          

//        TabuSearch chasquiOracle = new TabuSearch(0);
//
//        chasquiOracle.execute();
//
//        SolutionCandidate initialSolution = chasquiOracle.getSolution();
//
//        for (Route route : initialSolution.getRoutes()) {
//            for (Node node : route.getNodeList()) {
//                System.out.print(node.getId() + " - ");
//            }
//            System.out.println("");
//        }
        // TODO code application logic here
    }

}

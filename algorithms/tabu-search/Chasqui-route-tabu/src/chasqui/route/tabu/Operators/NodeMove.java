/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu.Operators;

import chasqui.route.tabu.Node;
import chasqui.route.tabu.Route;
import chasqui.route.tabu.SolutionCandidate;
import utils.Random;
/**
 *
 * @author Joca
 */
public class NodeMove implements NeighborGenerator{

    @Override
    public SolutionCandidate generateNeighbor(SolutionCandidate s) {

        SolutionCandidate neighbor = new SolutionCandidate(s);

        //choose two routes randomly
        int randIndx1 = Random.getRandomInt(0, s.getRoutes().size());
        int randIndx2 = Random.getRandomIntDiff(0, s.getRoutes().size(), randIndx1);

        //find the smallest distance between two nodes
        // n1-> route1 , n2 -> route2
        Route route1 = neighbor.getRoutes().get(randIndx1);
        Route route2 = neighbor.getRoutes().get(randIndx2);
        int n1 = 0, n2 = 0, minDist = Integer.MAX_VALUE;
        int currentDistance;
        
        for (int i = 0; i < route1.getNodeList().size(); i++) {
            for (int j = 0; j < route2.getNodeList().size(); j++) {
                currentDistance = route1.getNodeList().get(i).getPos().distance(route2.getNodeList().get(j).getPos());
                if(currentDistance == 0 && currentDistance < minDist) {
                    minDist = currentDistance;
                    n1 = i;
                    n2 = j;
                }
            }
        }

        //relocates n1 before n2


        return neighbor;
    }


}

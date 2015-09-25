/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu.Operators;

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
        int randIndx1 = Random.getRandomInt(0, s.getRoutes().size());
        int randIndx2 = Random.getRandomIntDiff(0, s.getRoutes().size(), randIndx1);
        
        
        
        return neighbor;
    } 
   
}

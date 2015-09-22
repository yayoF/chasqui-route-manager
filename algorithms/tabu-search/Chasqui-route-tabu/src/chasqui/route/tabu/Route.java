/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import java.util.ArrayList;

/**
 *
 * @author Joca
 */
public class Route {
    
    private ArrayList<Node> nodeList = new ArrayList<>();
    
    public Route(Node depotNode) {
        this.nodeList.add(depotNode); //first node
        this.nodeList.add(depotNode); //last node
    }
    
    
    
}

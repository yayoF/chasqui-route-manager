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
public class SolutionCandidate {
    
    private ArrayList<Route> routes = new ArrayList<>();
    
            
    public int fitness() 
    {
        return 0;
    }
    
    public void addRoute(Route r) {
        this.routes.add(r);
    }
}

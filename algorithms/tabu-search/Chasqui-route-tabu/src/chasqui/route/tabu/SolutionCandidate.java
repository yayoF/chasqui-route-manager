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
    
    private ArrayList<Route> routes;
    
    public SolutionCandidate() {
        this.routes = new ArrayList();
    }
    
    public SolutionCandidate SolutionCandidate(SolutionCandidate s) {
        SolutionCandidate c = new SolutionCandidate();
        
        return c;
    }
            
    public int fitness() 
    {
        return 0;
    }
    
    public void addRoute(Route r) {
        this.getRoutes().add(r);
    }

    /**
     * @return the routes
     */
    public ArrayList<Route> getRoutes() {
        return routes;
    }

    /**
     * @param routes the routes to set
     */
    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
}

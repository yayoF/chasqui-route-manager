/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.dp1.gas.model.bean;

import java.util.ArrayList;

/**
 *
 * @author luiss
 */
public class SolutionCandidate {
     private ArrayList<Route> routes = new ArrayList<>();
     
     public int fitness(){
         return 0;
     }
     
     public void addRoute(Route r){
         this.getRoutes().add(r);
     }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }
    
}

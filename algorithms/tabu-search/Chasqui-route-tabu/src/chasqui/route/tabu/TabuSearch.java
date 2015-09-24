/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import chasqui.route.tabu.Operators.NeighborGenerator;
import java.util.ArrayList;

/**
 *
 * @author Joca
 */
public class TabuSearch {
    //tabu-search-dependant
    protected int maxIteration = 1000, maxTabuSize = 10;
    protected SolutionCandidate sCandidate, bestCandidate, sBest;
    protected ArrayList<SolutionCandidate> tabuList = new ArrayList();
    protected ArrayList<SolutionCandidate> sNeighborhood = new ArrayList();
    protected ArrayList<NeighborGenerator> tabuoOperators = new ArrayList();
    
    //specific-problem-dependant
    protected ArrayList<Node> customersList = new ArrayList();
    protected Node depotNode;
    protected ArrayList<Vehicle> vehicleList = new ArrayList();

    public TabuSearch(int maxIteration) {

        this.maxIteration = maxIteration;

    }

    public void execute () {
        int iteration = 0;

        //generate a optimize initial solutioncandidate
        sBest = sCandidate = generateInitialSolution();

        while (! this.stoppingCondition(iteration)) {

            sNeighborhood = generateNeighborhood(sCandidate);

            //obtain best candidate in the neighborhood of solutions generated
            for (SolutionCandidate actualNeighbor : sNeighborhood) {
                if( (! this.tabuList.contains(actualNeighbor) ) &&
                        (actualNeighbor.fitness() > bestCandidate.fitness() ) ) {

                    bestCandidate = actualNeighbor;

                }
            }

            //evaluate best candidate obtained this interation
            if( bestCandidate.fitness() > sBest.fitness() ) {
                sBest = bestCandidate;
            } else {
                //not exist a better solution 
                iteration ++;
            }
            
            //update Tabu List
            addCandidateTabuList(bestCandidate);

            

        }

    }

    protected void addCandidateTabuList(SolutionCandidate s) {

        this.tabuList.add(s);

        if( this.tabuList.size() > this.maxTabuSize ) {
            tabuList.remove(0);
        }


    }

    protected ArrayList<SolutionCandidate> generateNeighborhood(SolutionCandidate sCandidate) {
        
        ArrayList<SolutionCandidate> neighbors  = new ArrayList(); 
    
        for (NeighborGenerator operator: tabuoOperators) {
            SolutionCandidate neighbor = operator.generateNeighbor(sCandidate);
            neighbors.add(neighbor);
        }
                
        return neighbors;
    }

    protected SolutionCandidate generateInitialSolution() {

        int routedCustomers = 0;
        SolutionCandidate initialSolution = new SolutionCandidate();
        Vehicle currentVehicle;
        Node nearestCustomer;
        ArrayList<Integer> visitedCustomers = new ArrayList();
        
        while (customersList.size() - routedCustomers == 0){

            Route r = new Route(this.depotNode);
            
            currentVehicle = getAvailableVehicle();

            if( currentVehicle == null ) {
                break;
            }

            while(currentVehicle.getCapacity(r) > 0) {
                nearestCustomer = findNearestCustomer(currentVehicle, r, visitedCustomers);

                if(nearestCustomer == null) {
                    break;
                }

                if( currentVehicle.getCapacity(r) > nearestCustomer.getDemand() ){
                    r.addNode(nearestCustomer);
                    routedCustomers ++;
                }

            }

            initialSolution.addRoute(r);

        } //until all customers are assigned a route

        return initialSolution;

    }

    protected Node findNearestCustomer(Vehicle v, Route r, ArrayList<Integer> visitedCustomers) {
        
        Position currPos = r.getLastAddedNode().getPos();
        boolean customerHasBeenVisited = false;
        
        for (int i=0; i<customersList.size(); i++){
            
            for (int m=0; m<visitedCustomers.size(); m++){
                if ( customersList.get(i).getId() == visitedCustomers.get(m) ){
                    customerHasBeenVisited = true;
                    break;
                }
            }
            
            if (customerHasBeenVisited){
                continue;
            }else{
                
                //compare distances between customer and vehicle
                
                
            }
                
        }
            
        
        
        
        return this.customersList.get(0);
    }

    protected Vehicle getAvailableVehicle() {
        for (Vehicle vehicle : this.vehicleList) {
            if(vehicle.isInUse()) {
                vehicle.setInUse(true);
                return vehicle;
            }
        }
        return null;
    }

    protected boolean stoppingCondition(int iteration) {

        return iteration >= this.maxIteration;

    }
    
    protected SolutionCandidate getSolution() {
        return sBest;
    }

}

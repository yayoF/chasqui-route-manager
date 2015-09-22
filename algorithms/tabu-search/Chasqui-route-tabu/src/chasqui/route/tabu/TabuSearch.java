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
public class TabuSearch { 
    //tabu-search-dependant
    protected int maxIteration = 1000, maxTabuSize = 10;
    protected SolutionCandidate sCandidate, bestCandidate, s, sBest;
    protected ArrayList<SolutionCandidate> tabuList = new ArrayList();
    protected ArrayList<SolutionCandidate> sNeighborhood = new ArrayList();
    
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
        sCandidate = generateInitialSolution();

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
            }

            //update Tabu List
            addCandidateTabuList(bestCandidate);

            iteration ++;

        }

    }

    protected void addCandidateTabuList(SolutionCandidate s) {

        this.tabuList.add(s);

        if( this.tabuList.size() > this.maxTabuSize ) {
            tabuList.remove(0);
        }


    }

    protected ArrayList<SolutionCandidate> generateNeighborhood(SolutionCandidate sCandidate) {

        
        
        return new ArrayList<SolutionCandidate>();
        
        
        
        

    }

    protected SolutionCandidate generateInitialSolution() {

        int routedCustomers = 0;
        SolutionCandidate initialSolution = new SolutionCandidate(); 
        Vehicle currentVehicle;
        Node nearestCustomer;
        
        while (customersList.size() - routedCustomers == 0){
            
            Route r = new Route(this.depotNode);
            
            currentVehicle = getAvailableVehicle();
            
            while(currentVehicle.getCapacity(r) > 0) {
                nearestCustomer = findNearestCustomer(currentVehicle);
                
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
    
    protected Node findNearestCustomer(Vehicle v) {
        return this.customersList.get(0);
    }
    
    protected Vehicle getAvailableVehicle() {
        return this.vehicleList.get(0);
    }

    protected boolean stoppingCondition(int iteration) {

        return iteration >= this.maxIteration;

    }

}

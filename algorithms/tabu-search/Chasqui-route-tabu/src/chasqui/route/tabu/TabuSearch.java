/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import chasqui.route.tabu.Operators.NeighborGenerator;
import java.util.ArrayList;
import javafx.scene.input.KeyCode;
import utils.Random;

/**
 *
 * @author Joca
 */
public class TabuSearch {
    private int maxIteration = 1000;
    private int maxTabuSize = 10;
    private SolutionCandidate sCandidate;
    
    //specific-problem-dependant
    private SolutionCandidate bestCandidate;
    private SolutionCandidate sBest;
    private ArrayList<SolutionCandidate> tabuList = new ArrayList();
    private ArrayList<SolutionCandidate> sNeighborhood = new ArrayList();
    private ArrayList<NeighborGenerator> tabuoOperators = new ArrayList();
    private ArrayList<Node> customersList = new ArrayList();
    private Node depotNode;
    private ArrayList<Vehicle> vehicleList = new ArrayList();

    public TabuSearch(int maxIteration) {

        this.maxIteration = maxIteration;

    }

    public void execute () {
        int iteration = 0;
        boolean b = false;

        //generate a optimize initial solutioncandidate
        setsBest(sCandidate = generateInitialSolution());

        while (b) {

            setsNeighborhood(generateNeighborhood(getsCandidate()));

            //obtain best candidate in the neighborhood of solutions generated
            for (SolutionCandidate actualNeighbor : getsNeighborhood()) {
                if( (! this.tabuList.contains(actualNeighbor) ) &&
                        (actualNeighbor.fitness() > getBestCandidate().fitness() ) ) {

                    setBestCandidate(actualNeighbor);

                }
            }

            //evaluate best candidate obtained this interation
            if( getBestCandidate().fitness() > getsBest().fitness() ) {
                setsBest(getBestCandidate());
            } else {
                //not exist a better solution 
                iteration ++;
            }
            
            //update Tabu List
            addCandidateTabuList(getBestCandidate());

            

        }

    }

    protected void addCandidateTabuList(SolutionCandidate s) {

        this.getTabuList().add(s);

        if( this.getTabuList().size() > this.getMaxTabuSize() ) {
            getTabuList().remove(0);
        }


    }

    protected ArrayList<SolutionCandidate> generateNeighborhood(SolutionCandidate sCandidate) {
        
        ArrayList<SolutionCandidate> neighbors  = new ArrayList(); 
    
        for (NeighborGenerator operator: getTabuoOperators()) {
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
        
        while (getCustomersList().size() - routedCustomers == 0){

            Route r = new Route(this.getDepotNode());
            
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
        
        if (r.getNodeList().size() == (visitedCustomers.size() + 2) ){
            return null;
        }
        ArrayList<Node> hornyCustomers = getHornyCustomers(getCustomersList(), visitedCustomers);
        if (hornyCustomers != null){
            if (hornyCustomers.size() == 1){
                return hornyCustomers.get(0);
            }
        }
        
        int rIndA = Random.getRandomInt(0, hornyCustomers.size()-1);
        int rIndB = Random.getRandomIntDiff(0, hornyCustomers.size()-1, rIndA);
        
        Node chosenCustomer = dummyPicker(hornyCustomers.get(rIndA), hornyCustomers.get(rIndB), currPos);
        
        return chosenCustomer; 
    }

    protected Vehicle getAvailableVehicle() {
        for (Vehicle vehicle : this.getVehicleList()) {
            if(vehicle.isInUse()) {
                vehicle.setInUse(true);
                return vehicle;
            }
        }
        return null;
    }

    protected boolean stoppingCondition(int iteration) {

        return iteration >= this.getMaxIteration();

    }
    
    protected SolutionCandidate getSolution() {
        return getsBest();
    }

    private Node dummyPicker(Node candidateA, Node candidateB, Position currPos) {
        
        Node chosenOne = null;
        if (  (candidateA.getPos().distance(currPos)) < (candidateB.getPos().distance(currPos))  ){
            return candidateA;
        }else{
            return candidateB;
        }
        //returns closest candidate to current position
    }
    
    private ArrayList<Node> getHornyCustomers(ArrayList<Node> customersList, ArrayList<Integer> visitedCustomers) {
        
        ArrayList<Node> hornyOnes = null;
        boolean foundCustomer = false;
        
        for (int i=1; i< customersList.size(); i++){ //customersList includes the customer representing de Depot (pos 0)
            for (int j=0; j<visitedCustomers.size(); j++){
                if (customersList.get(i).getId() == visitedCustomers.get(j)){
                    foundCustomer = true;
                }
            }
            
            if (!foundCustomer) {
                hornyOnes.add(customersList.get(i));
            }
            
        }
        return hornyOnes;
    }

    /**
     * @return the maxIteration
     */
    public int getMaxIteration() {
        return maxIteration;
    }

    /**
     * @param maxIteration the maxIteration to set
     */
    public void setMaxIteration(int maxIteration) {
        this.maxIteration = maxIteration;
    }

    /**
     * @return the maxTabuSize
     */
    public int getMaxTabuSize() {
        return maxTabuSize;
    }

    /**
     * @param maxTabuSize the maxTabuSize to set
     */
    public void setMaxTabuSize(int maxTabuSize) {
        this.maxTabuSize = maxTabuSize;
    }

    /**
     * @return the sCandidate
     */
    public SolutionCandidate getsCandidate() {
        return sCandidate;
    }

    /**
     * @param sCandidate the sCandidate to set
     */
    public void setsCandidate(SolutionCandidate sCandidate) {
        this.sCandidate = sCandidate;
    }

    /**
     * @return the bestCandidate
     */
    public SolutionCandidate getBestCandidate() {
        return bestCandidate;
    }

    /**
     * @param bestCandidate the bestCandidate to set
     */
    public void setBestCandidate(SolutionCandidate bestCandidate) {
        this.bestCandidate = bestCandidate;
    }

    /**
     * @return the sBest
     */
    public SolutionCandidate getsBest() {
        return sBest;
    }

    /**
     * @param sBest the sBest to set
     */
    public void setsBest(SolutionCandidate sBest) {
        this.sBest = sBest;
    }

    /**
     * @return the tabuList
     */
    public ArrayList<SolutionCandidate> getTabuList() {
        return tabuList;
    }

    /**
     * @param tabuList the tabuList to set
     */
    public void setTabuList(ArrayList<SolutionCandidate> tabuList) {
        this.tabuList = tabuList;
    }

    /**
     * @return the sNeighborhood
     */
    public ArrayList<SolutionCandidate> getsNeighborhood() {
        return sNeighborhood;
    }

    /**
     * @param sNeighborhood the sNeighborhood to set
     */
    public void setsNeighborhood(ArrayList<SolutionCandidate> sNeighborhood) {
        this.sNeighborhood = sNeighborhood;
    }

    /**
     * @return the tabuoOperators
     */
    public ArrayList<NeighborGenerator> getTabuoOperators() {
        return tabuoOperators;
    }

    /**
     * @param tabuoOperators the tabuoOperators to set
     */
    public void setTabuoOperators(ArrayList<NeighborGenerator> tabuoOperators) {
        this.tabuoOperators = tabuoOperators;
    }

    /**
     * @return the customersList
     */
    public ArrayList<Node> getCustomersList() {
        return customersList;
    }

    /**
     * @param customersList the customersList to set
     */
    public void setCustomersList(ArrayList<Node> customersList) {
        this.customersList = customersList;
    }

    /**
     * @return the depotNode
     */
    public Node getDepotNode() {
        return depotNode;
    }

    /**
     * @param depotNode the depotNode to set
     */
    public void setDepotNode(Node depotNode) {
        this.depotNode = depotNode;
    }

    /**
     * @return the vehicleList
     */
    public ArrayList<Vehicle> getVehicleList() {
        return vehicleList;
    }

    /**
     * @param vehicleList the vehicleList to set
     */
    public void setVehicleList(ArrayList<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
    
}

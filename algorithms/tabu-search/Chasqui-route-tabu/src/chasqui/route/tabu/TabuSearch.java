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
    //tabu-search-dependant
    private int maxIteration = 10;
    //tabu-search-dependant
    private int maxTabuSize = 10;
    protected SolutionCandidate sCandidate, bestCandidate;
    private SolutionCandidate sBest;
    protected ArrayList<SolutionCandidate> tabuList = new ArrayList();
    protected ArrayList<SolutionCandidate> sNeighborhood = new ArrayList();
    protected ArrayList<NeighborGenerator> tabuoOperators = new ArrayList();

    //specific-problem-dependant
    private ArrayList<Node> customersList = new ArrayList();
    private Node depotNode;
    private ArrayList<Vehicle> vehicleList = new ArrayList();

    public TabuSearch(int maxIteration) {

        this.maxIteration = maxIteration;

    }

    public void execute () {
        int iteration = 0;

        //generate a optimize initial solutioncandidate
        bestCandidate = sBest = sCandidate = generateInitialSolution();

        while (iteration < this.maxIteration) {

            sNeighborhood = generateNeighborhood(sCandidate);

            //obtain best candidate in the neighborhood of solutions generated
            for (SolutionCandidate actualNeighbor : sNeighborhood) {
                if( (! this.tabuList.contains(actualNeighbor) ) &&
                        (actualNeighbor.fitness() > bestCandidate.fitness() ) ) {

                    bestCandidate = actualNeighbor;

                }
            }

            //evaluate best candidate obtained this interation
            if( bestCandidate.fitness() > getsBest().fitness() ) {
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

        if( this.tabuList.size() > this.getMaxTabuSize() ) {
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

        while (getCustomersList().size() - routedCustomers > 0){

            Route r = new Route(this.getDepotNode());
            currentVehicle = getAvailableVehicle();

            if( currentVehicle == null ) {
                break;
            } else {
                currentVehicle.setInUse(true);
            }

            //System.out.println(currentVehicle);

            while(currentVehicle.getCapacity(r) > 0) {
                nearestCustomer = findNearestCustomer(currentVehicle, r, visitedCustomers);

                if(nearestCustomer == null) {
                    break;
                }

                //System.out.println(currentVehicle + " intento agregar : " + nearestCustomer.getId() + " con vehicle con capacidad " + currentVehicle.getCapacity(r));

                if( currentVehicle.getCapacity(r) > nearestCustomer.getDemand() ){
                    r.addNode(nearestCustomer);
                    visitedCustomers.add(nearestCustomer.getId());
                    routedCustomers ++;
                } else {
                    break;
                }

            }

            initialSolution.addRoute(r);

        } //until all customers are assigned a route

        return initialSolution;

    }

    protected Node findNearestCustomer(Vehicle v, Route r, ArrayList<Integer> visitedCustomers) {

        Position currPos = r.getLastAddedNode().getPos();

        if (getCustomersList().size() == (visitedCustomers.size() + 2) ){
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
            if(! vehicle.isInUse()) {
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

        if ( (candidateA.getPos().distance(currPos)) < (candidateB.getPos().distance(currPos)) ){
            return candidateA;
        }else{
            return candidateB;
        }
        //returns closest candidate to current position
    }

    private ArrayList<Node> getHornyCustomers(ArrayList<Node> customersList, ArrayList<Integer> visitedCustomers) {

        ArrayList<Node> hornyOnes = new ArrayList();
        boolean foundCustomerInVisitedCustomers = false;

        for (int i=0; i< customersList.size(); i++){ //customersList includes the customer representing de Depot (pos 0)
            for (int j=0; j<visitedCustomers.size(); j++){
                if (customersList.get(i).getId() == visitedCustomers.get(j)){
                    foundCustomerInVisitedCustomers = true;
                }
            }

            if (!foundCustomerInVisitedCustomers) {
                hornyOnes.add(customersList.get(i));
            }
            foundCustomerInVisitedCustomers = false;

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
     * @return the sBest
     */
    public SolutionCandidate getsBest() {
        return sBest;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.dp1.gas.algorithm.grasp;

import java.util.ArrayList;
import java.util.Random;
import pe.edu.pucp.dp1.gas.model.bean.Node;
import pe.edu.pucp.dp1.gas.model.bean.Route;
import pe.edu.pucp.dp1.gas.model.bean.SolutionCandidate;
import pe.edu.pucp.dp1.gas.model.bean.Vehicle;

/**
 *
 * @author luiss
 */
public class GasGrasp {
    
    /*Constantes GRASP*/

    public static final int NUMITERATIONSGRASP = 10000;
    public static final double ALPHA = 0.2f; // Relaxation constant
    
    /*Atributos*/
    
    //private SolutionCandidate solution = new SolutionCandidate();
    //private SolutionCandidate solution2OPT = new SolutionCandidate();
    
    public SolutionCandidate Execute(ArrayList<Vehicle>listVehicles, ArrayList<Node>listNodes, Node depotNode){
        
        /*for (int i=0; i<NUMITERATIONSGRASP; i++){
            solution = ConstructivePhase();
            LocalSearch();
            solution = UpdateBestSolution(solution);
        }*/
        
        SolutionCandidate solution = new SolutionCandidate();
        SolutionCandidate solution2OPT = new SolutionCandidate();
        
        //solution = ConstructivePhase(listVehicles, listNodes, depotNode);
        
          
        for (int i=0; i<NUMITERATIONSGRASP; i++){
            solution = ConstructivePhase(listVehicles, listNodes, depotNode); // Greedy Algorithm - Heuristic
            solution2OPT = Procedure2OPT(solution, listVehicles, listNodes, depotNode);
            if (i==0){
                solution = solution2OPT;
                //update(solution, solution2OPT)
            }
            else{
                if (CostCVRP(solution2OPT) < CostCVRP(solution)){
                    solution = solution2OPT;
                    //update(solution, solution2OPT)
                }
            }
            
            System.out.println("Simulacion " + (i+1) + " realizada.");
        }
        
        return solution; // MUST BE BEST SOLUTIONs
        
    }
    
    public double TotalTime(SolutionCandidate solution){
        double totalDistance = 0;
        ArrayList<Route> routes = solution.getRoutes();
        
        for (int i=0; i<routes.size(); i++){
            ArrayList<Node> nodes = routes.get(i).getNodeList();
            for (int j=0; j<nodes.size()-1; j++){
                totalDistance += GetDistanceBetweenNodes(nodes.get(j), nodes.get(j+1)); 
            }
        }
        
        return totalDistance/50;
    }
    
    public double CostCVRP(SolutionCandidate solution){
        double cost = 0;
        ArrayList<Route> routes = solution.getRoutes();
        
        for(int i=0; i<routes.size(); i++){
            ArrayList<Node> nodes = routes.get(i).getNodeList();
            for (int j=1; j<nodes.size()-1; j++){
                cost+= GetDistanceBetweenNodes(nodes.get(j), nodes.get(j+1)); 
            }
        }
        
        return cost;
        
    }
    
    public void Initialize(ArrayList<Vehicle> listVehicles, ArrayList<Node> listNodes){
        
        for (Vehicle vehicle: listVehicles){
            vehicle.setCurrentCapacity(vehicle.getMaxCapacity());
            vehicle.setInUse(false);
        }
        
        for (Node node: listNodes){
            node.setAssigned(false);
            node.setDelivered(false);
        }
        
    }
    
    public SolutionCandidate ConstructivePhase(ArrayList<Vehicle>listVehicles, ArrayList<Node>listNodes, Node depotNode){
        
        /*NOTE: THIS ALGORITHM IS TAKEN FROM PUCP GRASP THESIS AND ITS SOLUTION IS ABOUT A LIST OF VEHICLES WITH ASSIGNED NODES
          SO, WE NEED TO FIT THIS SOLUTION TO OUR OWN SOLUTION (LIST OF ROUTES)*/
        
        /**ASSUMPTIONS**/
        /* 1. DEMAND ATTRIBUTE FROM NODE WILL BE UPDTADED TO "0" WHEN DEMAND IS DELIVERED SUCCESFULY --> CHECK DELIVERALLGASDEMANDS METHOD*/
        
        SolutionCandidate posSolution = new SolutionCandidate();
        for (Vehicle vehicle: listVehicles){
            Route route = new Route(depotNode);
            posSolution.addRoute(route);
        }
        int indexVehicle = 0;
        Vehicle vehicle = new Vehicle();
        vehicle = listVehicles.get(indexVehicle);
        
        Initialize(listVehicles, listNodes);
        
        /*While Solution is not complete*/
        while (!DeliverAllGasDemands(listNodes)){
            while (!CapacityIsFull(posSolution, indexVehicle, listVehicles) || StillCanDeliverGas(posSolution, vehicle, listNodes)){
                ArrayList<Node> rcl = new ArrayList<Node>(); 
                rcl = GenerateRCL(posSolution, listNodes, indexVehicle, vehicle, depotNode); // Generate list of candidates (nodes)
                Node selectedNode = SelectRandomNodeFromRCL(rcl);  // Select node from RCL at random;
                if (selectedNode != null)
                    AssignNodeToVehicle(posSolution, vehicle, indexVehicle, selectedNode, listNodes); //Assign selectedNode (Client) to Vehicle
                //UpdateVehicleCapacity(vehicle, selectedNode);
                if (AllDemandFromNodeAreDelivered(selectedNode)){
                    if (selectedNode!= null)
                        UpdateDeliveredNode(posSolution, indexVehicle, selectedNode, listNodes);
                    if (DeliverAllGasDemands(listNodes)){
                        //UpdateVehicleList(listVehicles, vehicle);
                        return posSolution; // Must be a SolutionCandidate object instead of a List of Vehicles
                    }
                }
                if (selectedNode == null)
                    break;
            }
            //UpdateVehicleList(listVehicles, vehicle);
            indexVehicle++;
            if (indexVehicle == listVehicles.size())
                break;
            else{
                vehicle = listVehicles.get(indexVehicle);
                //System.out.println("VEHICULO " + indexVehicle + "procesado");
            }
                
        }
        return posSolution; // Must be a SolutionCandidate object instead of a List of Vehicles
    }
    
    public SolutionCandidate Procedure2OPT(SolutionCandidate solution, ArrayList<Vehicle>listVehicles, ArrayList<Node>listNodes, Node depotNode){
        
        SolutionCandidate posSolution = new SolutionCandidate();
        int numVehicles = listVehicles.size();
        ArrayList<Node> listNodesProcedure = new ArrayList<Node>();
        ArrayList<Node> listBestNodesProcedure = new ArrayList<Node>(); 
        int numNodes;
        double bestCost;
        
        for (int i=0; i<numVehicles; i++){
            listNodesProcedure = GetAssignedNodesFromVehicle(solution, i);
            listBestNodesProcedure = listNodesProcedure;
            numNodes = listNodesProcedure.size();
            bestCost = GetCostRoute(listNodesProcedure, posSolution);
            for (int j=1; j<numNodes-1; j++){
                for (int k=j+1; k<numNodes-1; k++){
                    Node nodeK = listNodesProcedure.get(k);
                    DeleteNode(listNodesProcedure, k);
                    InsertNode(listNodesProcedure, j, nodeK);
                    double cost = GetCostRoute(listNodesProcedure, posSolution);
                    if (cost < bestCost){
                        listBestNodesProcedure = listNodesProcedure;
                        bestCost = cost;
                    }
                }
                listNodesProcedure = GetAssignedNodesFromVehicle(solution, i);
            }
            AssignNewNodeListToVehicle(solution, i, listBestNodesProcedure, depotNode);
        }
        posSolution = solution;
        return posSolution;
    }
    
    
    /*METHODS FOR CONSTRUCTIVE PHASE*/
    
    public boolean DeliverAllGasDemands(ArrayList<Node> listNodes){
        
        /*for (int i=0; i<listNodes.size(); i++){
            if (listNodes.get(i).getDemand() > 0)
                return false;
        }
        return true;*/
        boolean assigned;
        for (Node node: listNodes){
            if (!(assigned = node.isAssigned()))
                return false;
        }
        
        return true;
        
    }
    
    public boolean CapacityIsFull(SolutionCandidate posSolution, int indexVehicle, ArrayList<Vehicle> listVehicles){
        
        Vehicle vehicle = listVehicles.get(indexVehicle);
        ArrayList<Route> solutionRoutes = posSolution.getRoutes();
        
        /*if (solutionRoutes.size() != 0){
            Route route = posSolution.getRoutes().get(indexVehicle); // Get route for vehicle at indexVehicle position
            //Vehicle's method getCapacity is 0 when vehicle is full already.
            if (vehicle.getCapacity(route) == 0)
                return true;
            else
                return false;
        }
        else{
            return false;
        }*/
        
        if (vehicle.getCurrentCapacity() > 0)
            return false;
        else
            return true;
    }
    
    public boolean StillCanDeliverGas(SolutionCandidate posSolution, Vehicle vehicle, ArrayList<Node> listNodes){
        
        for(Node node: listNodes){
            if (node.isAssigned() == false && node.getDemand() <= vehicle.getCurrentCapacity())
                return true;
        }
        
        return false;
        
        /*Route route = posSolution.getRoutes().get(indexVehicle); //Get Route from Vehicle at indexVehicle
        ArrayList<Node> nodesAssignedAlready = route.getNodeList(); //Get Nodes according to the Route
        int currentCapacity = listVehicles.get(indexVehicle).getCapacity(route); //Current vehicle's capacity
        //MUST BE IMPROVED!! O(N^2) IS NOT EFFICIENT
        
        for (int i=0; i< listNodes.size(); i++){
            for (int j=0; j<nodesAssignedAlready.size(); j++){
                //Comparison should be with ID
                if (nodesAssignedAlready.get(j).getPos() != listNodes.get(i).getPos()) {
                    //This is a node that has not been assigned yet. So, I need to check if the vehicle can deliver his demand at least.
                    Node nodeNotAssigned = listNodes.get(i);
                    if (nodeNotAssigned.getDemand() < currentCapacity){
                        //It means that this vehicle can deliver still one demand from some node at least
                        return true;
                    }
                }
            }
        }*/
        
        //return false;
        
    }
    
    public void AssignNodeToVehicle(SolutionCandidate posSolution, Vehicle vehicle, int indexVehicle, Node selectedNode, ArrayList<Node> listNodes){
        
        vehicle.setCurrentCapacity(vehicle.getCurrentCapacity()-selectedNode.getDemand());
        vehicle.setInUse(true);
        posSolution.getRoutes().get(indexVehicle).addNode(selectedNode);
        for(Node node: listNodes){
            if (node.getId()==selectedNode.getId()){
                node.setAssigned(true);
            }
        }
        
    }
    
    public void UpdateVehicleCapacity(Vehicle vehicle, Node selectedNode){
        
        /*THIS METHOD COULD NOT BE NECESSARY IF AN AVALAIBLECAPACITY FOR VEHICLE CLASS IS REQUIRED FOR TRACKING AVAILABILITY*/
        
    }
    
    public boolean AllDemandFromNodeAreDelivered(Node selectedNode){
        
        /*NOTE: THIS METHOD FOR NUMERICAL EXPERIMENTATION IS IRREVELANT**/
        
        return true;
        
        /*if (selectedNode.getDemand() == 0)
            return true;
        else
            return false;*/
        
    }
    
    public void UpdateDeliveredNode(SolutionCandidate posSolution, int indexVehicle, Node selectedNode, ArrayList<Node> listNodes){
        
        Route route = posSolution.getRoutes().get(indexVehicle);
        ArrayList<Node> nodesFromRoute = route.getNodeList();
        
        for (Node node : nodesFromRoute){
            /*MUST BE ID ISNTEAD OF POSITION*/
            if (node.getId() == selectedNode.getId()){
                node.setDelivered(true);
                break;
            }
        }       
        
        /*SHOULD UPDATE LIST OF NODES AS WELL*/
        
        for (Node node: listNodes){
            if (node.getId() == selectedNode.getId()){
                node.setDelivered(true);
                break;
            }
        }
        
    }
    
    public double FindBestValue(Vehicle vehicle, ArrayList<Node> listNodes, Node depotNode){
        
        /*NOTE: ITS JUST AN GREEDY ALGORITHM ABOUT SHORTEST DISTANCE*/
        
        
        int maxCapacity = vehicle.getMaxCapacity();
        double distance, bestDistance = 0;
        double minDistance = Double.MAX_VALUE;
        double minDemand = Integer.MAX_VALUE;
        //From depot
        Node initNode = depotNode;
        int indexSaved = -1;
        
        for (int i=0; i<listNodes.size(); i++){
            //Get shortest distance
            distance =  GetDistanceBetweenNodes(initNode, listNodes.get(i));
            if (distance < minDistance){
                minDistance = distance;
                indexSaved = i;
            }
            //maxDemand = listNodes.get(i).getDemand();
            //indexSaved = i;
        }
            
            //Update capacity
            
        if (indexSaved != -1){
            //bestDistance += minDistance;
            /*initNode = listNodes.get(indexSaved);
            listNodes.remove(indexSaved);
            maxCapacity -= initNode.getDemand();
            indexSaved=-1;
            minDistance = Double.MAX_VALUE;
            minDemand = Integer.MAX_VALUE;*/
            return minDistance;
        }
        else
            return -1;
                
        
        /*while (true){
            int maxDemand = 0;
            for (int i=0; i<listNodes.size(); i++){
                if (listNodes.get(i).getDemand() <= maxCapacity){
                    //Get shortest distance
                    distance =  GetDistanceBetweenNodes(initNode, listNodes.get(i));
                    if (distance < minDistance){
                        minDistance = distance;
                        indexSaved = i;
                    }
                    else if (distance == minDistance){
                        if (listNodes.get(indexSaved).getDemand() > listNodes.get(i).getDemand()){
                            minDistance = distance;
                            indexSaved = i;
                        }
                    }
                    //maxDemand = listNodes.get(i).getDemand();
                    //indexSaved = i;
                }
            }
            
            //Update capacity
            
            if (indexSaved != -1){
                bestDistance += minDistance;
                initNode = listNodes.get(indexSaved);
                listNodes.remove(indexSaved);
                maxCapacity -= initNode.getDemand();
                indexSaved=-1;
                minDistance = Double.MAX_VALUE;
                minDemand = Integer.MAX_VALUE;
            }
            else
                break;
        }
        */
        //return minDistance;
    }
    
    public double FindWorstValue(Vehicle vehicle, ArrayList<Node> listNodes, Node depotNode){
        
        int maxCapacity = vehicle.getMaxCapacity();
        double worstDistance = 0;
        double maxDistance = 0;
        //From depot
        Node initNode = depotNode;
        /*int indexSaved = -1;
        
        for (int i=0; i<listNodes.size(); i++){
            double distance = GetDistanceBetweenNodes(initNode, listNodes.get(i));
            if (distance > maxDistance){
                maxDistance = distance;
                indexSaved = i;
            }
        }
        
        if (indexSaved != -1)
            return maxDistance;
        else
            return -1;*/
        
        for (Node node: listNodes){
            worstDistance += GetDistanceBetweenNodes(initNode, node);
            initNode = node;
        }
        
        return worstDistance;
        
    }
    
    public double GetDistanceBetweenNodes(Node nodeA, Node nodeB){
        
        int xA,yA,xB,yB;
        
        xA = nodeA.getPos().getCoordX();
        yA = nodeA.getPos().getCoordY();
        xB = nodeB.getPos().getCoordX();
        yB = nodeB.getPos().getCoordY();
        
        double aux = Math.pow(xA-xB, 2) + Math.pow(yA-yB, 2);
        
        return Math.sqrt(aux);
        
    }
    
    public ArrayList<Node> GenerateRCL(SolutionCandidate posSol, ArrayList<Node> listNodes, int indexVehicle, Vehicle vehicle, Node depotNode){
        
        ArrayList<Node> rcl = new ArrayList<Node>();
        
        /*Verify if all nodes are assigned already*/
        
        /*boolean assigned = false;
        for (Node node: listNodes){
            if (!(assigned =node.isAssigned()))
                break;
        }
        if (assigned){
            return null; //It means there are no nodes for assigment
        }*/
        
        
        
        
        
        Route route = posSol.getRoutes().get(indexVehicle);
        ArrayList<Node> listNodeFromRoute = route.getNodeList();
        int sizeListNode = listNodeFromRoute.size();
        
        Node lastNode = listNodeFromRoute.get(sizeListNode-1);
        
        double bestValue = FindBestValue(vehicle, listNodes, lastNode);
        double worstValue = FindWorstValue(vehicle, listNodes, lastNode);
        
        double limitSup = bestValue + ALPHA*(worstValue-bestValue);
        double limitInf = bestValue;
        
        double distance = 0;
        
        //Tengo que conseguir a partir del nodo final de la ruta que tengo actualmente el nodo con demanda que puedo llevar y que sea la menor distancia
        
        //double currentCapacity = vehicle.getCapacity(route);
        double currentCapacity = vehicle.getCurrentCapacity();
        
        for (Node node : listNodes) {
            
            if (!(node.isAssigned())){
                //Verify if vehicle has enough capacity            
                //There are 2 nodes (depot) in the list
                if (node.getDemand() <= currentCapacity){
                    //Verify if Route is empty
                    distance = GetDistanceBetweenNodes(lastNode, node);

                    if (distance >= limitInf && distance <= limitSup){
                        rcl.add(node);
                        //currentCapacity -= node.getDemand();
                    }
                }
            }           
            
        }
        
        return rcl;
    }
    
    public Node SelectRandomNodeFromRCL(ArrayList<Node> rcl){
        
        if (rcl.size() == 0)
            return null;
        else{
            Random random = new Random();
            int limit = rcl.size();
            int randomIndex = random.nextInt(limit);

            return rcl.get(randomIndex);
        }
    }
    
    
    /*METHODS FOR 2OPT PROCEDURE*/
    
    public ArrayList<Node> GetAssignedNodesFromVehicle(SolutionCandidate solution, int indexVehicle){
        return solution.getRoutes().get(indexVehicle).getNodeList();
    }
    
    public double GetCostRoute(ArrayList<Node> listNodesProcedure, SolutionCandidate solution){
        
        double distance = 0;
        for(int i=0; i<listNodesProcedure.size()-1; i++){
            distance += GetDistanceBetweenNodes(listNodesProcedure.get(i), listNodesProcedure.get(i+1));
        }
        return distance;
        
    }
    
    public void DeleteNode(ArrayList<Node> listNodesProcedure, int posK){
        listNodesProcedure.remove(posK);
    }
    
    public void InsertNode(ArrayList<Node> listNodesProcedure, int posJ, Node nodeK){
        listNodesProcedure.add(posJ, nodeK);
    }
    
    public void AssignNewNodeListToVehicle(SolutionCandidate solution, int indexVehicle, ArrayList<Node> listBestNodesProcedure, Node depotNode){
        /*Route route = new Route(depotNode);
        for(int i=0; i<listBestNodesProcedure.size(); i++){
            route.addNode(listBestNodesProcedure.get(i));
        }
        
        solution.getRoutes().set(indexVehicle, route);*/
        ArrayList<Route> routes = solution.getRoutes();
        routes.remove(indexVehicle);
        
        Route route = new Route(depotNode);
        
        for (int i=1; i<listBestNodesProcedure.size()-1; i++){
            route.addNode(listBestNodesProcedure.get(i));
        }
        
        routes.add(indexVehicle, route);
    }
}
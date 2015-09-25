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
    private int demand;
    
    public Route(Node depotNode) {
        this.nodeList.add(depotNode); //first node
        this.nodeList.add(depotNode); //last node
        this.demand = 0;
    }
    
    public Route(Route r) {
        this.setNodeList(r.getNodeList());
    }
    
    public int getDemand() {
        return demand;
    }
    
    public Node getLastAddedNode(){
        int positionlastAdded = getNodeList().size() - 1;
        return getNodeList().get(positionlastAdded);
    }
    
    public void addNode(Node n) {
        this.getNodeList().add(this.getNodeList().size() - 1, n);
        this.setDemand(this.getDemand() + n.getDemand());
    }

    /**
     * @return the nodeList
     */
    public ArrayList<Node> getNodeList() {
        return nodeList;
    }

    /**
     * @param nodeList the nodeList to set
     */
    public void setNodeList(ArrayList<Node> nodeList) {
        this.nodeList = nodeList;
    }

    /**
     * @param demand the demand to set
     */
    public void setDemand(int demand) {
        this.demand = demand;
    }
    
    
}

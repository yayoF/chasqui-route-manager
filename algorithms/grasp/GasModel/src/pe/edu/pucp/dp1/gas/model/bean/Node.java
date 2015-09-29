/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.dp1.gas.model.bean;

/**
 *
 * @author luiss
 */
public class Node {
    private int id;
    private Position pos;
    private int demand;
    private boolean delivered;
    private boolean assigned;
    
    public Node(int id, int x, int y, int demand){
        this.id = id;
        this.pos = new Position(x,y);
        this.demand = demand;
        this.assigned = false;
        this.delivered = false; //Default not delivered
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isAssigned() {
        return assigned;
    }

    public void setAssigned(boolean assigned) {
        this.assigned = assigned;
    }
}

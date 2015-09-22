/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

/**
 *
 * @author yayo
 */
public class Vehicle {
    
    private int maxCapacity;
    private boolean inUse;

    public Vehicle() {
        this.inUse = false;
    }
    
    /**
     * @param r
     * @return the capacity
     */
    public int getCapacity(Route r) {
        return this.maxCapacity - r.getDemand();
    }

    /**
     * @return the inUse
     */
    public boolean isInUse() {
        return inUse;
    }

    /**
     * @param inUse the inUse to set
     */
    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    /**
     * @return the maxCapacity
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * @param maxCapacity the maxCapacity to set
     */
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
    
}

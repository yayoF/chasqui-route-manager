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
public class Vehicle {
    private int maxCapacity;
    private boolean inUse;
    private int currentCapacity;
    
    public Vehicle(){
        this.inUse = false;
    }
    
    public int getCapacity(Route r){
        return this.getMaxCapacity() - r.getDemand();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }
    
    
}

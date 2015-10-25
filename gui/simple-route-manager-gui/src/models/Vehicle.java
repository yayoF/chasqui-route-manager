/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import javax.persistence.*;

/**
 *
 * @author Joca
 */

@Entity
public class Vehicle {

    private int id;
    private int maxCapacity;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

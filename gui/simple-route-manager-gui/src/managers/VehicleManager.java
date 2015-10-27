/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import jdk.nashorn.internal.runtime.regexp.joni.constants.CCVALTYPE;
import models.Vehicle;
import org.hibernate.Session;


/**
 *
 * @author Joca
 */
public class VehicleManager extends Manager {



    public void addVehicle(Vehicle v)
    {
        Session s = this.getSession();

        s.beginTransaction();

        s.save(v);

        s.getTransaction().commit();

        s.clear();
    }



}

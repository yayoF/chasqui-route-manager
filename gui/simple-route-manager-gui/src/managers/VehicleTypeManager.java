/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import models.VehicleType;
import org.hibernate.Session;

/**
 *
 * @author Joca
 */
public class VehicleTypeManager extends Manager{


    public VehicleType findById(int id)
    {
        VehicleType vt = new VehicleType();

        Session s = this.getSession();;

        try {

            vt = (VehicleType) s.get(VehicleType.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }

         s.close();

        return vt;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.route.manager.gui;
import javax.swing.JFrame;
import javax.swing.UIManager;
import managers.*;
import models.*;

public class SimpleRouteManagerGui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here\\


        /*
        VehicleManager vM = new VehicleManager();
        VehicleTypeManager vtM = new VehicleTypeManager();

        VehicleType vt = vtM.findById(1);

        Vehicle v = new Vehicle();
        v.setMaxCapacity(20);
        v.setVehicleType(vt);
        vM.addEntity(v);

        */


       JFrame loginFrame = new loginWindow();
       loginFrame.setVisible(true);
    }

}

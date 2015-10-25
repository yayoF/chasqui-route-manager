/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.route.manager.gui;
import javax.swing.JFrame;
import javax.swing.UIManager;
import models.Vehicle;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;

public class SimpleRouteManagerGui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here\\

        AnnotationConfiguration config = new AnnotationConfiguration();
        config.addAnnotatedClass(Vehicle.class );
        config.configure();
        new SchemaExport(config).create(true, true);

        //JFrame loginFrame = new loginWindow();
        //loginFrame.setVisible(true);
    }

}

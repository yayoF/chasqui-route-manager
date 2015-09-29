/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Joca
 */
public class ChasquiRouteTabu {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        String path = "../../in/data1.txt";
        FileInputStream fstream = new FileInputStream(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String strLine;
     
        strLine = br.readLine();
        
        int nVehicleTypes = Integer.parseInt(strLine);
        int nVehicles, capacity;
        ArrayList<Vehicle> vehicleList = new ArrayList();
        
        for (int i = 0; i < nVehicleTypes; i++) {
            strLine = br.readLine();
            String[] split = strLine.split("\\s");
            nVehicles = Integer.parseInt(split[0]);
            capacity = Integer.parseInt(split[1]);
            
            for (int j = 0; j < nVehicles; j++) {
                vehicleList.add(new Vehicle(capacity));
            }
  
        }
        
       System.out.println(vehicleList.size());
        
        
        
       

        br.close();


//        TabuSearch chasquiOracle = new TabuSearch(0);
//
//        chasquiOracle.execute();
//
//        SolutionCandidate initialSolution = chasquiOracle.getSolution();
//
//        for (Route route : initialSolution.getRoutes()) {
//            for (Node node : route.getNodeList()) {
//                System.out.print(node.getId() + " - ");
//            }
//            System.out.println("");
//        }
        // TODO code application logic here
    }

}

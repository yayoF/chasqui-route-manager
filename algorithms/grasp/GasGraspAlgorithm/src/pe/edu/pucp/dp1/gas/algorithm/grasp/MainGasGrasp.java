/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.pucp.dp1.gas.algorithm.grasp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.dp1.gas.model.bean.Node;
import pe.edu.pucp.dp1.gas.model.bean.Route;
import pe.edu.pucp.dp1.gas.model.bean.SolutionCandidate;
import pe.edu.pucp.dp1.gas.model.bean.Vehicle;

/**
 *
 * @author luiss
 */
public class MainGasGrasp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        GasGrasp gasApp = new GasGrasp();
        Node depotNode = new Node(0,100,100,0);
        ArrayList<Node> listNodes = new ArrayList<Node>();
        ArrayList<Vehicle> listVehicles = new ArrayList<Vehicle>();        
        ReadingInput(listNodes, listVehicles);
        /*Test INPUT*/
        TestInput(listNodes, listVehicles);
        
        SolutionCandidate solutionGrasp = gasApp.Execute(listVehicles, listNodes, depotNode);
        //TestSolution(solutionGrasp, listVehicles); Prints each vehicle's route
        
        double totalTime = Math.round(gasApp.TotalTime(solutionGrasp) * 100.0)/100.0; //round up to 2 decimals
        
        System.out.println("TIEMPO TOTAL RECORRIDO: " + totalTime + " horas");
        
    }
    
    public static void TestSolution(SolutionCandidate solutionGrasp, ArrayList<Vehicle> listVehicles){
        
        ArrayList<Route> routes = solutionGrasp.getRoutes();
        
        System.out.println();
        System.out.println();
        System.out.println("================================================");
        System.out.println("SOLUTION INFO:");
        System.out.println("================================================");
        
        for (int i=0; i<routes.size(); i++){
            if (listVehicles.get(i).isInUse()){
                ArrayList<Node> nodes = routes.get(i).getNodeList();
                System.out.print("Ruta para el Vehiculo No " + (i+1) + ": " );
                for (int j=0; j<nodes.size(); j++){
                   System.out.print("Nodo " + nodes.get(j).getId());
                   if (j<nodes.size()-1)
                       System.out.print("->");
                   else
                       System.out.println();
                }
            }
            else{
                System.out.println("Ruta para el Vehiculo No " + (i+1) + ": Vehiculo sin usar" );
            }
            
        }
        
    }
    
    public static void TestInput(ArrayList<Node> listNodes, ArrayList<Vehicle> listVehicles){
        System.out.println("================================================");
        System.out.println("VEHICLES INFO:");
        System.out.println("================================================");
        for(int i=0; i<listVehicles.size(); i++){
            System.out.println("Vehicle No " + (i+1) + " - Capacidad: " + listVehicles.get(i).getMaxCapacity());            
        }
        System.out.println("================================================");
        System.out.println();
        
        System.out.println("================================================");
        System.out.println("COSTUMERS INFO:");
        System.out.println("================================================");
        for (int i=0; i<listNodes.size(); i++){
            System.out.println("Customer No " + (i+1) + " - Possition: (" + listNodes.get(i).getPos().getCoordX() + "," + 
                    listNodes.get(i).getPos().getCoordY() + ") - Demand: " + listNodes.get(i).getDemand() + 
                    " Asignado? = " + listNodes.get(i).isAssigned() + " Entregado? = " + listNodes.get(i).isDelivered());            
        }
    }
    
    public static void ReadingInput(ArrayList<Node> listNodes, ArrayList<Vehicle> listVehicles){
        
        try {
            
            String fileName = "input.txt";
            String line = null;
            int numVehicleTypes = 0; 
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            /*1. READ NUMBER OF VEHICLES TYPES*/
            
            if ((line = bufferedReader.readLine()) != null){
                numVehicleTypes = Integer.parseInt(line);
            }
            
            /*2. READ VEHICLES'DATA*/
            
            for (int i=0; i<numVehicleTypes; i++){
                if ((line = bufferedReader.readLine()) != null){
                    //Process line to extract vehicle's data
                    String[] data = line.split(" ");
                    int numVehicleXType = Integer.parseInt(data[0]);
                    int capacity = Integer.parseInt(data[1]);
                    for (int j=0; j<numVehicleXType; j++){
                        Vehicle vehicle = new Vehicle();
                        vehicle.setMaxCapacity(capacity);
                        vehicle.setCurrentCapacity(capacity);
                        listVehicles.add(vehicle);
                    }
                }
            }
            
            /*3. READ CUSTOMER DATA*/
            
            int numNodes = 0;
            
            if ((line = bufferedReader.readLine()) != null){
                numNodes = Integer.parseInt(line);
            }
            
            for (int i=0; i<numNodes; i++){
                if ((line = bufferedReader.readLine()) != null){
                    //FORMAT: X Y DEMAND
                    String[] data = line.split(" ");
                    int coordX = Integer.parseInt(data[0]);
                    int coordY = Integer.parseInt(data[1]);
                    int demand = Integer.parseInt(data[2]);
                    
                    Node node = new Node(i+1, coordX, coordY, demand);
                    listNodes.add(node);
                }
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainGasGrasp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainGasGrasp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

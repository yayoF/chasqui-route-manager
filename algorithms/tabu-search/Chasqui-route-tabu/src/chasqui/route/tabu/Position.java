/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chasqui.route.tabu;
import utils.Constants;

/**
 *
 * @author yayo
 */
public class Position {
    
    private int coordX;
    private int coordY;
    
    public Position() {
        this.coordX = 0;
        this.coordY = 0;
    }

    public Position(int x, int y) {
        this.coordX = x;
        this.coordY = y;
    }
        
    /**
     * @return the coordX
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * @param coordX the coordX to set
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * @return the coordY
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * @param coordY the coordY to set
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    
    
    public int distance(Position y) {
 
        int xDistance = (this.coordX - y.coordX)*Constants.blockDistance;
        int yDistance = (this.coordY - y.coordY)*Constants.blockDistance;
        
        return Math.abs(xDistance) + Math.abs(yDistance);
    }
    
    public static int distanceFromTwoPoints(Position x, Position y) {
        return x.distance(y);
    }
}

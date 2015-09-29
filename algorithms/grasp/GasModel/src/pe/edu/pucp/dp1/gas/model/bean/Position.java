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
public class Position {
    private int coordX;
    private int coordY;
    
    public Position(){
        this.coordX = 0;
        this.coordY = 0;
    }
    
    public Position(int x, int y){
        this.coordX = x;
        this.coordY = y;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
    
    
    
}

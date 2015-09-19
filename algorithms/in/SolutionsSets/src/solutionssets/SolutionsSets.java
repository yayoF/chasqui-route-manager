/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solutionssets;
import java.util.Random;

/**
 *
 * @author yayo
 */
public class SolutionsSets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
         Random rand = new Random();
         
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((9 - 1) + 1) + 1;

        System.out.println(randomNum);
        
    }
    
}

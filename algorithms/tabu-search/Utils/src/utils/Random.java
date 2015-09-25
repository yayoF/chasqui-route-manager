/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author Joca
 */
public class Random {
    
    public static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
    
    public static int getRandomIntDiff(int min, int max, int diff) {
        int randomInt = getRandomInt(min, max);
        
        while ( randomInt == diff) {
            randomInt = getRandomInt(min, max);
        }
        
        return randomInt;
    }
    
}

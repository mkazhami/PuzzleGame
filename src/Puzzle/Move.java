/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Puzzle;

public class Move {
    public static int[][] moveRight(int [][] array, int column, int row){
        int[][] newArray = ArrayOperations.copyArray(array);
        if((column-1) >= 0){
                newArray[row][column] = newArray[row][column-1];
                newArray[row][column-1] = 0;
            }
        return newArray;
    }
    
    public static int[][] moveLeft(int [][] array, int column, int row){
        int[][] newArray = ArrayOperations.copyArray(array);
        if((column+1) < Puzzle.arrayWidth){
                newArray[row][column] = newArray[row][column+1];
                newArray[row][column+1] = 0;
            }
        return newArray;
    }
    
    public static int[][] moveUp(int [][] array, int column, int row){
        int[][] newArray = ArrayOperations.copyArray(array);
        if((row+1) < Puzzle.arrayHeight){
                newArray[row][column] = newArray[row+1][column];
                newArray[row+1][column] = 0;
            }
        return newArray;
    }
    
    public static int[][] moveDown(int [][] array, int column, int row){
        int[][] newArray = ArrayOperations.copyArray(array);
        if((row-1) >= 0){
                newArray[row][column] = newArray[row-1][column];
                newArray[row-1][column] = 0;
            }
        return newArray;
    }
    
}

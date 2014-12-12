/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Puzzle;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mikhail
 */
public class ArrayOperations {
    
    //this functions builds a shuffled array
    //it begins with a solved array (refer to Puzzle.isSolved())
    //and makes 70 to 100 random up, down, left, and right moves to shuffle
    public static int[][] buildArray2(){
        int[][] array = new int[Puzzle.arrayHeight][Puzzle.arrayWidth];
        int counter;
        //create proper solved board
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            if(i == 0){
                counter = 1;
            }
            else if(i%2 == 1){
                counter = Puzzle.arrayWidth*(i+1);
            }
            else{
                counter = Puzzle.arrayWidth*i + 1;
            }
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                if(i == (Puzzle.arrayHeight - 1) && j == (Puzzle.arrayWidth - 1)){
                    array[i][j] = 0;
                }
                else{
                    array[i][j] = counter;
                }
                if(i%2 == 1){
                    counter--;
                }
                else{
                    counter++;
                }
            }
        }
        int randomize = (int) (Math.random() * 30) + 70; //number of times to shuffle puzzle
        int column;
        int row;
        int random = 0;
        String prev = "";
        String cur = "";
        //fill queue of random moves for shuffling the board
        for(int i = 0; i < randomize; i++){
            prev = cur;
            column = ArrayOperations.findEmptyColumn(array);
            row = ArrayOperations.findEmptyRow(array);
            while(prev.equals(cur)){
                random = (int) (Math.random() * 4);
                if(random == 0){ cur = "up"; }
                else if(random == 1){ cur = "down"; }
                else if(random == 2){ cur = "left"; }
                else{ cur = "right"; }
            }
            Puzzle.shuffled.add(array);
            if(random == 0){
                array = Move.moveUp(array, column, row);
            }
            else if(random == 1){
                array = Move.moveDown(array, column, row);
            }
            else if(random == 2){
                array = Move.moveLeft(array, column, row);
            }
            else{
                array = Move.moveRight(array, column, row);
            }
        }
        return array;
    }
    
    public static int[][] copyArray(int[][] source){
        int[][] newArray = new int[Puzzle.arrayHeight][Puzzle.arrayWidth];
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                newArray[i][j] = source[i][j];
            }
        }
        return newArray;
    }
    
    public static int findEmptyColumn(int [][] array){
        int col = 0;
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                if(array[i][j] == 0){
                    col = j;
                    break;
                }
            }
        }
        return col;
    }
    
    public static int findEmptyRow(int [][] array){
        int row = 0;
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                if(array[i][j] == 0){
                    row = i;
                    break;
                }
            }
        }
        return row;
    }
    
    public static boolean compareArray(int [][] array, int [][] array2){
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                if(array[i][j] != array2[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static boolean intMember(int [][] array, int item){
        for(int i = 0; i < Puzzle.arrayHeight; i++){
            for(int j = 0; j < Puzzle.arrayWidth; j++){
                if(array[i][j] == item){
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean arrayMember(int [][] array, ArrayList list, int size){
        for(int i = 0; i < size; i++){
            if(compareArray(array, (int[][]) list.get(i)) == true){
                return true;
            }
        }
        return false;
    }
    
}

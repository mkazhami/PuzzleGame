/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import java.awt.Font;
/**
 *
 * @author Mikhail
 */
public class Puzzle extends BasicGame{
    
    final static int width = 900;
    final static int height = 600;
    final static boolean fullscreen = false;
    final static boolean showFPS = false;
    final static String title = "Puzzle";
    final static int fpslimit = 60;
    
    Input input;
    int[][] array;
    ArrayList list;
    public static final int arrayHeight = 3;
    public static final int arrayWidth = 3;
    int row;
    int column;
    private final float boxHeight = 190;
    private final float boxWidth = 290;
    private boolean solved = false;
    public static Queue shuffled;
    Font font;
    TrueTypeFont newFont;
    
    
    //if the numbers are in the proper positions in the 2d array, it is solved
    //solved array should look like:
    //      1  2  3
    //      6  5  4
    //      7  8
    private boolean isSolved(int [][] array){
        int counter;
        for(int i = 0; i < arrayHeight; i++){
            if(i == 0){
                counter = 1;
            }
            else if(i%2 == 1){
                counter = arrayWidth * (i + 1);
            }
            else{
                counter = (arrayWidth * i) + 1;
            }
            for(int j = 0; j < arrayWidth; j++){
                if(i == (arrayHeight - 1) && j == (arrayWidth - 1) && array[i][j] == 0){
                    return true;
                }
                if(array[i][j] != counter){
                    return false;
                }
                if(i%2 == 1){
                    counter--;
                }
                else{
                    counter++;
                }
            }
        }
        return true;
    }
    
    
    public Puzzle(String title){
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException{
        input = gc.getInput();
        shuffled = new LinkedList<>();
        array = ArrayOperations.buildArray2();
        list = new ArrayList<>();
        font = new Font ("Times New Roman", Font.BOLD , 40);
        newFont = new TrueTypeFont(font, false);
    }
    
    @Override
    public void update(GameContainer gc, int delta){
        column = ArrayOperations.findEmptyColumn(array);
        row = ArrayOperations.findEmptyRow(array);
        //shuffle the board pieces by taking moves from queue of random moves
        //sleep is to show player the shuffling, but is not enough for them to keep track
        while(shuffled.size() > 0){
            array = (int[][]) shuffled.remove();
            gc.sleep(70);
            return;
        }
        if(isSolved(array)){
            solved = true;
        }
        else{
            //detect up, down, left, right key presses
            if(input.isKeyPressed(Keyboard.KEY_DOWN)){
                array = Move.moveDown(array, column, row);
            }
            else if(input.isKeyPressed(Keyboard.KEY_UP)){
                array = Move.moveUp(array, column, row);
            }
            else if(input.isKeyPressed(Keyboard.KEY_RIGHT)){
                array = Move.moveRight(array, column, row);
            }
            else if(input.isKeyPressed(Keyboard.KEY_LEFT)){
                array = Move.moveLeft(array, column, row);
            }
        }
    }
    
    @Override
    public void render(GameContainer gc, Graphics g){
        float drawHeight = 5;
        float drawWidth = 5;
        g.setColor(Color.blue);
        //draw the puzzle pieces
        for(int i = 0; i < arrayHeight; i++){
            for(int j = 0; j < arrayWidth; j++){
                if(array[i][j] == 0){
                    g.setColor(Color.pink);
                }
                else{
                    g.setColor(Color.gray);
                }
                g.fillRect(drawWidth, drawHeight, boxWidth, boxHeight);
                drawWidth += boxWidth + 10;
            }
            drawHeight += boxHeight + 10;
            drawWidth = 5;
        }
        drawHeight = 0;
        drawWidth = 5;
        g.setColor(Color.white);
        g.setFont(newFont);
        //draw the numbers inside the puzzle pieces
        for(int i = 0; i < arrayHeight; i++){
            for(int j = 0; j < arrayWidth; j++){
                if(array[i][j] != 0){
                    g.drawString(String.valueOf(array[i][j]), (drawWidth + (drawWidth + boxWidth))/2 - 10, (drawHeight + (drawHeight + boxHeight))/2 - 10);
                }
                drawWidth += boxWidth + 10; 
                           
            }
            drawHeight += boxHeight + 10;
            drawWidth = 5;
        }
        if(solved){
            g.drawString("SOLVED", width/2 - 40, height/2 - 35);
        }
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Puzzle(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(true);
        app.setTargetFrameRate(fpslimit);
        app.setShowFPS(showFPS);
        app.start();
    }
    
    
}

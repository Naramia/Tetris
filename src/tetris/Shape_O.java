/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Naramia Wijaya
 */
public class Shape_O extends Tetromino{
    public Shape_O(){
        cells[0] = new Cell(0,4, Tetromino.O);
        cells[1] = new Cell(0,5, Tetromino.O);
        cells[2] = new Cell(1,4, Tetromino.O);
        cells[3] = new Cell(1,5, Tetromino.O);
    }
    
    @Override
    public void rotate(){ //rotasi untuk o tidak ada controller karena bila dirotasikan sama saja
        
        cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.O);
        cells[1] = new Cell(cells[1].getRow(),cells[1].getCol(), Tetromino.O);
        cells[2] = new Cell(cells[2].getRow(),cells[2].getCol(), Tetromino.O);
        cells[3] = new Cell(cells[3].getRow(),cells[3].getCol(), Tetromino.O);
    }
}

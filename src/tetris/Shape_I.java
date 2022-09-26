/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Naramia Wijaya
 */
public class Shape_I extends Tetromino{
    public Shape_I(){
        cells[0] = new Cell(0,4, Tetromino.I);
        cells[1] = new Cell(0,3, Tetromino.I);
        cells[2] = new Cell(0,5, Tetromino.I);
        cells[3] = new Cell(0,6, Tetromino.I);
    }
    @Override
    public void rotate(){
        controller = (controller + 1) % 2; //rotasi untuk i cukup 2 saja
        switch (controller){
            
            case 1:{cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.I);
                    cells[1] = new Cell(cells[0].getRow()-1,cells[0].getCol(), Tetromino.I);
                    cells[2] = new Cell(cells[0].getRow()+1,cells[0].getCol(), Tetromino.I);
                    cells[3] = new Cell(cells[0].getRow()+2,cells[0].getCol(), Tetromino.I);
                    break;
            }
            case 0:{cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.I);
                    cells[1] = new Cell(cells[0].getRow(),cells[0].getCol()-1, Tetromino.I);
                    cells[2] = new Cell(cells[0].getRow(),cells[0].getCol()+1, Tetromino.I);
                    cells[3] = new Cell(cells[0].getRow(),cells[0].getCol()+2, Tetromino.I);
                    break;
            }
        }
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tetris;

/**
 *
 * @author Naramia Wijaya
 */
public class Shape_J extends Tetromino{
    public Shape_J(){
        cells[0] = new Cell(0,4, Tetromino.J);
        cells[1] = new Cell(0,3, Tetromino.J);
        cells[2] = new Cell(0,5, Tetromino.J);
        cells[3] = new Cell(1,5, Tetromino.J);
    }

    @Override
    public void rotate(){
        controller = (controller + 1) % 4;
        switch (controller){
            case 1 :cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.J);
                    cells[1] = new Cell(cells[0].getRow()-1,cells[0].getCol(), Tetromino.J);
                    cells[2] = new Cell(cells[0].getRow()+1,cells[0].getCol(), Tetromino.J);
                    cells[3] = new Cell(cells[0].getRow()+1,cells[0].getCol()-1, Tetromino.J);
                    break;
            case 2 :cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.J);
                    cells[1] = new Cell(cells[0].getRow(),cells[0].getCol()+1, Tetromino.J);
                    cells[2] = new Cell(cells[0].getRow(),cells[0].getCol()-1, Tetromino.J);
                    cells[3] = new Cell(cells[0].getRow()-1,cells[0].getCol()-1, Tetromino.J);
                    break;
            case 3 :cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.J);
                    cells[1] = new Cell(cells[0].getRow()+1,cells[0].getCol(), Tetromino.J);
                    cells[2] = new Cell(cells[0].getRow()-1,cells[0].getCol(), Tetromino.J);
                    cells[3] = new Cell(cells[0].getRow()-1,cells[0].getCol()+1, Tetromino.J);
                    break;
            case 0 :cells[0] = new Cell(cells[0].getRow(), cells[0].getCol(), Tetromino.J);
                    cells[1] = new Cell(cells[0].getRow(),cells[0].getCol()-1, Tetromino.J);
                    cells[2] = new Cell(cells[0].getRow(),cells[0].getCol()+1, Tetromino.J);
                    cells[3] = new Cell(cells[0].getRow()+1,cells[0].getCol()+1, Tetromino.J);
                    break;
        }
    }
}

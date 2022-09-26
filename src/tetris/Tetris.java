/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tetris;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Naramia Wijaya
 */
public class Tetris extends JPanel {

    private Tetromino current = Tetromino.randomOne();
    private Tetromino next = Tetromino.randomOne();
    
    private final int row = 20;
    private final int col = 10;
    private int score = 0;
    
    
    private final Cell[][] wall = new Cell[row][col];
    private static final int CELL_SIZE = 26;
    
    private boolean gameOver = false;
    
    void drawWall(Graphics g) {
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                int x = CELL_SIZE * j;
                int y = CELL_SIZE * i;
                
                Cell cell = wall[i][j];
                if (cell == null) {
                    g.setColor(Color.WHITE);
                    g.fillRect(x, y, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.BLACK);
                    g.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                    g.setColor(Color.RED);
                } else {
                    g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
                }
            }
        }
    } 
    
    void drawCurrent(Graphics g) {
        Cell[] cells = current.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE;
            int y = cell.getRow() * CELL_SIZE;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
    }
    
    void drawNext(Graphics g) {
        Cell[] cells = next.cells;
        for (Cell cell : cells) {
            int x = cell.getCol() * CELL_SIZE + 260;
            int y = cell.getRow() * CELL_SIZE + 26;
            g.drawImage(cell.getImage(), x, y, CELL_SIZE, CELL_SIZE, null);
        }
    }
    

    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(gameOver){
            g.setColor(Color.BLACK);
            g.setFont(new Font("Times New Roman",Font.PLAIN,18));
            g.drawString("GAME OVER", 215, 200);
            g.drawString("Your Score : "+score, 212, 220);
            g.drawString("--- Thank You for Playing Tetris ---", 147, 240);
            g.drawString("--- See You Next Time ---", 181, 260);
            g.drawString("Press [ESCAPE] To Start Again", 155, 280);
        } else{
        drawWall(g);
        drawCurrent(g);
        drawNext(g);
        //tampilan tambahan
            g.setFont(new Font("Arial",Font.PLAIN,13));
            g.drawString("Score: "+score, 300, 480);
            g.drawString("Your next puzzle :", 300, 15);
            g.drawString("Press SPACE BAR for rotate.", 300, 500);
            g.drawString("Press arrow keys for move.", 300, 520);
        }
        
    }
    
    boolean outOfBound() { // di bawah
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celRow = cell.getRow();
            if (celRow <= 0 || celRow >= row-1){
                return true;
            }
        }
        return false;
    }
    
    boolean tooLeft() { // terlalu ke kiri
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (celCol <= 0){
                return true;
            }
            if (wall[celRow][celCol-1] != null){ // kalau ketemu bidak di sebelah kiri
                return true;
        }
        }
        return false;
    }
    
    boolean tooRight() { // terlalu ke kanan
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            if (celCol >= col-1){
                return true;
            }
            if (wall[celRow][celCol+1] != null){ // kalau ketemu bidak di sebelah kanan
                return true;
        }
        }
        return false;
    }
        
        
    boolean coincide() { // kalau ketemu bidak lainnya
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            
            if (wall[celRow][celCol] != null ){
                return true;
            }
            
        }
        return false;
    }
     
    
    
    boolean isDrop() { // pemeriksaan kesempatan turun
        Cell[] cells = current.cells;
        for (Cell cell: cells) {
            int celCol = cell.getCol();
            int celRow = cell.getRow();
            
            if(celRow == row-1) {
                return false;
            }
            
            if (wall[celRow+1][celCol] != null){
                return false;
            }
        }
        return true;
    }
    
    
    
    
    void stopDropping () { // jika bidak sudah sampai bawah
        Cell[] cells = current.cells;
        for(Cell cell : cells) {
            int celrow = cell.getRow();
            int celcol = cell.getCol();
            wall[celrow][celcol] = cell;
        }
    }
    
    protected void softDrop() {
        if(isDrop()) {
            current.softDrop();
            checkLine(); 
        } 
        else {
            stopDropping();
            current = next;
            next = Tetromino.randomOne();
        }        
    }
    
    protected void moveLeft() {
        if(!tooLeft() && !outOfBound() && !coincide())
            current.moveLeft();
    }
    
    protected void moveRight() {
        if(!tooRight() && !outOfBound() && !coincide())
            current.moveRight();
    }
    
    protected void rotation(){
        if(!tooRight() && !tooLeft() && !coincide())
            current.rotate();
    }
    
    protected void checkLine() { // mengecek baris yang sudah full dengan bidak akan hilang
    int bottomLine = wall.length - 1;
    for(int topLine = wall.length -1; topLine > 0; topLine --) {
        int count = 0;
        for(int i = 0; i < wall[0].length; i++ ) {
            if(wall[topLine][i] != null) {
                count++;
                }
            wall[bottomLine][i] = wall[topLine][i];
            }
            if (count < wall[0].length) {
                bottomLine --;
            }
    } 
}
    
    
    public void start() {
        KeyListener keylist = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent args) {
                int key = args.getKeyCode();
                
                switch(key) {
                    case KeyEvent.VK_DOWN : {
                        softDrop();
                        break;
                    } case KeyEvent.VK_LEFT : {
                        moveLeft();
                        break;
                    } case KeyEvent.VK_RIGHT : {
                        moveRight();
                        break;
                    } case KeyEvent.VK_SPACE : { 
                        rotation();
                        break;
                    } case KeyEvent.VK_ESCAPE:{ // untuk memulai game baru
                        if(gameOver){
                            gameOver=false;
                            score=0;
                            main(null);
                        }
                        break;
                    }
                }
                repaint();
            }
        };
        
        this.addKeyListener(keylist);
        this.requestFocus();
        
        new Thread() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    
                    if (isDrop()){
                        softDrop();
                        
                    } 
                    else if (!coincide()) { //pakai else if untuk melanjutkan gambar selanjutnya
                        stopDropping();
                        current = next;
                        next = Tetromino.randomOne();
                        score = score+5;
                    } 
                    
                    else { //kalau sudah mentok ke atas
                        gameOver=true;
                    }
                    repaint();
                }
            }
        }.start();
    } 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Tetris");
        frame.setSize(530, 580);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Tetris tetrisPanel = new Tetris();
        frame.add(tetrisPanel);
        frame.setVisible(true);
        tetrisPanel.start();
        
    }
    
}

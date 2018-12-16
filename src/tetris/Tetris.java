package tetris;

import javax.swing.*;

public class Tetris extends JFrame 
{

     Tetris() 
     {
        TetrisFactory obj = new TetrisFactory();
        TetrisController board = obj.getInstance("Tetris");
        add(board);
        board.start();
        setSize(400, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

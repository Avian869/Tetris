package tetris;

import javax.swing.*;

public class Tetris extends JFrame 
{

     Tetris() 
     {
        Board board = new Board();
        add(board);
        board.start();
        setSize(400, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}

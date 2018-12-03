
package tetris;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Board extends JPanel implements ActionListener 
{

    static final int WIDTH = 10;
    static final int HEIGHT = 20;
    
    private Timer timer;
    private boolean isFalling = true;
    private Calculations calc;
    private boolean gameStarted = false;
    private int currentX = 0;
    private int currentY = 0;
    private Shape currentShape;
    private TetrisShape[] board;

    Board() 
    {
        initBoard();
    }

    private void initBoard() {
        setFocusable(true);
        calc = new Calculations();
        currentShape = new Shape();
        timer = new Timer(400, this);
        board = new TetrisShape[WIDTH * HEIGHT];
        setBackground(Color.BLACK);
        clearShape();
        addKeyListener(new Controller());
    }

    private void clearShape() 
    {
        for (int i = 0; i < HEIGHT * WIDTH; i++) 
        {
            board[i] = TetrisShape.EMPTYSHAPE;
        }
    }

    private void shapeMove() 
    {
        for (int i = 0; i < 4; i++) 
        {
            int x = currentX + currentShape.getX(i);
            int y = currentY - currentShape.getY(i);
            board[y * WIDTH + x] = currentShape.getShape();
        }
        
        if (isFalling) 
        {
            newShape();
        }
    }

    private void newShape() 
    {
        currentShape.setRandomShape();
        currentX = WIDTH / 2;
        currentY = HEIGHT + currentShape.getLowestY();

        if (!tryMove(currentShape, currentX, currentY - 1)) 
        {
            currentShape.setShape(TetrisShape.EMPTYSHAPE);
            timer.stop();
            gameStarted = false;
        }
    }

    private void moveDown() 
    {
        if (!tryMove(currentShape, currentX, currentY - 1))
        {
            shapeMove();
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (!isFalling) 
        {
            isFalling = true;

            newShape();
        } 
        else {
            moveDown();
        }
    }

    private void drawPart(Graphics g, int x, int y) 
    {
        g.setColor(Color.white);
        int sqrHeight = calc.getGridSquareHeight(getSize().getHeight());
        int sqrWidth = calc.getGridSquareWidth(getSize().getWidth());
        g.drawLine(x, y + sqrHeight , x, y);
        g.drawLine(x, y, x + sqrWidth , y);
        g.drawLine(x , y + sqrHeight , x + sqrWidth , y + sqrHeight );
        g.drawLine(x + sqrWidth , y + sqrHeight , x + sqrWidth , y );
        g.drawLine(x, y, x + sqrWidth , y + sqrHeight );
        g.drawLine(x + sqrWidth , y, x, y + sqrHeight );
    }

    @Override
    public void paint(Graphics g) 
    {
        int sqrHeight = calc.getGridSquareHeight(getSize().getHeight());
        int sqrWidth = calc.getGridSquareWidth(getSize().getWidth());
        super.paint(g);
        Dimension size = getSize();
        int boardTop = (int) size.getHeight() - HEIGHT * sqrHeight;

        for (int i = 0; i < HEIGHT; i++) 
        {
            for (int j = 0; j < WIDTH; ++j) 
            {
                TetrisShape shape = calc.getShapeAt(j, HEIGHT - i - 1, board);

                if (shape != TetrisShape.EMPTYSHAPE) 
                {
                    drawPart(g, j * sqrWidth, boardTop + i * sqrHeight);
                }
            }
        }

        if (currentShape.getShape() != TetrisShape.EMPTYSHAPE) 
        {
            for (int i = 0; i < 4; ++i) 
            {
                int x = currentX + currentShape.getX(i);
                int y = currentY - currentShape.getY(i);
                drawPart(g, x * sqrWidth, boardTop + (HEIGHT - y -1) * sqrHeight);
            }
        }
    }

    void start() {
        gameStarted = true;
        isFalling = true;
        clearShape();
        newShape();
        timer.start();
    }

    private boolean tryMove(Shape newPiece, int newX, int newY) 
    {
        for (int i = 0; i < 4; ++i) 
        {
            int x = newX + newPiece.getX(i);
            int y = newY - newPiece.getY(i);

            if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
                return false;

            if (calc.getShapeAt(x, y, board) != TetrisShape.EMPTYSHAPE)
                return false;
        }

        currentShape = newPiece;
        currentX = newX;
        currentY = newY;
        repaint();

        return true;
    }

    private void dropDown() 
    {
        int newY = currentY;
        while (newY > 0) 
        {
            if (!tryMove(currentShape, currentX, newY - 1)) 
            {
                break;
            }
            newY--;
        }
        shapeMove();
    }

    class Controller extends KeyAdapter 
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int keyCode = e.getKeyCode();
            if (!gameStarted || currentShape.getShape() == TetrisShape.EMPTYSHAPE) 
            {
                return;
            }
            switch (keyCode) 
            {
                case KeyEvent.VK_LEFT:
                    tryMove(currentShape, currentX - 1, currentY);
                    break;
                case KeyEvent.VK_RIGHT:
                    tryMove(currentShape, currentX + 1, currentY);
                    break;
                case KeyEvent.VK_UP:
                    tryMove(currentShape.Rotate(), currentX, currentY);
                    break;
                case KeyEvent.VK_DOWN:
                    moveDown();
                    break;
                case KeyEvent.VK_SPACE:
                    dropDown();
                    break;
            }

        }
    }

}
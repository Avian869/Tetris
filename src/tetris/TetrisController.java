
package tetris;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;
import static tetris.BoardPainter.HEIGHT;

public class TetrisController extends BoardPainter implements ActionListener 
{
    protected Timer timer;
    protected boolean isFalling = true;
    protected boolean gameStarted = false;
    static TetrisController obj = new TetrisController();
    
    protected TetrisController() 
    {
        initBoard();
    }
    
    public static TetrisController getInstance()
    {
        return obj;
    }
    
    private void initBoard() 
    {
        setFocusable(true);
        calc = new Calculations();
        currentShape = new ShapeController();
        timer = new Timer(400, this);
        board = new TetrisShape[WIDTH * HEIGHT];
        setBackground(Color.BLACK);
        clearShape();
        addKeyListener(new Controller());
    }
    
    void start() 
    {
        gameStarted = true;
        isFalling = true;
        clearShape();
        newShape();
        timer.start();
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

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if (!isFalling) 
        {
            isFalling = true;

            newShape();
        } 
        else 
        {
            moveDown();
        }
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
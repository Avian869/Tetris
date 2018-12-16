
package tetris;

import javax.swing.*;
import java.awt.*;

public abstract class BoardPainter extends JPanel
{

    static final int WIDTH = 10;
    static final int HEIGHT = 20;
    
    protected Calculations calc;
    protected int currentX = 0;
    protected int currentY = 0;
    protected ShapeController currentShape;
    protected TetrisShape[] board;


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

    protected boolean tryMove(ShapeController newPiece, int newX, int newY) 
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
}
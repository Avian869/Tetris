
package tetris;

import java.util.Random;

public class ShapeController extends Shape 
{
    
    TetrisShape getShape() 
    {
        return tetrisShape;
    }

    void setRandomShape() 
    {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TetrisShape[] values = TetrisShape.values();
        setShape(values[x]);
    }
     
    void setTargetShape(int x) 
    {
        TetrisShape[] values = TetrisShape.values();
        setShape(values[x]);
    }

    int getLowestY() 
    {
        int y = coordinates[0][1];
        for (int i = 0; i < 4; i++) 
        {
            y = Math.min(y, coordinates[i][1]);
        }
        return y;
    }

    ShapeController Rotate() 
    {
        if (tetrisShape == TetrisShape.SQUARE)
        {
            return this;
        }

        ShapeController result = new ShapeController();
        result.tetrisShape = tetrisShape;

        for (int i = 0; i < 4; i++) 
        {
            result.setX(i, -getY(i));
            result.setY(i, getX(i));
        }
        return result;
    }
}

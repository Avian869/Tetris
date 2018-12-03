
package tetris;

import java.util.Random;

enum TetrisShape 
{
    EMPTYSHAPE(new int[][]{{0,0},{0,0},{0,0},{0,0}}),
    SQUARE(new int[][]{{0, 0}, {1, 0}, {1, 1}, {0, 1}}),
    LINE(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {2, 0}}),
    LSHAPE(new int[][]{{1, 0}, {0, 0}, {0, 1}, {0, 2}}),
    REVERSEDLSHAPE(new int[][]{{-1, 0}, {0, 0}, {0, 1}, {0, 2}}),
    TSHAPE(new int[][]{{-1, 0}, {0, 0}, {1, 0}, {0, -1}}),
    ZSHAPE(new int[][]{{-1, 0}, {0, 0}, {0, 1}, {1, 1}}),
    REVERSEDZSHAPE(new int[][]{{1, 0}, {0, 0}, {0, 1}, {-1, 1}});

    public int[][] coord;

    TetrisShape(int[][] coord) 
    {
        this.coord = coord;
    }
}

 class Shape 
{

    private TetrisShape tetrisShape;
    private int[][] coordinates;

     Shape() 
    {
        coordinates = new int[4][2];
        setShape(TetrisShape.EMPTYSHAPE);
    }

     void setShape(TetrisShape shape) 
    {
        for (int i = 0; i < 4; i++) 
        {
            System.arraycopy(shape.coord[i], 0, coordinates[i], 0, 2);
        }

        tetrisShape = shape;
    }

    private void setX(int index, int x) 
    {
        coordinates[index][0] = x;
    }

    private void setY(int index, int y) 
    {
        coordinates[index][1] = y;
    }

     int getX(int index) 
    {
        return coordinates[index][0];
    }

     int getY(int index) 
    {
        return coordinates[index][1];
    }

     TetrisShape getShape() {
        return tetrisShape;
    }

     void setRandomShape() {
        Random r = new Random();
        int x = Math.abs(r.nextInt()) % 7 + 1;
        TetrisShape[] values = TetrisShape.values();
        setShape(values[x]);
    }

     int getLowestY() {
        int y = coordinates[0][1];
        for (int i = 0; i < 4; i++) {
            y = Math.min(y, coordinates[i][1]);
        }
        return y;
    }

     Shape Rotate() {
        if (tetrisShape == TetrisShape.SQUARE){
            return this;
        }

        Shape result = new Shape();
        result.tetrisShape = tetrisShape;

        for (int i = 0; i < 4; i++) {
            result.setX(i, -getY(i));
            result.setY(i, getX(i));
        }

        return result;
    }

}
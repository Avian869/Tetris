
package tetris;

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
    protected TetrisShape tetrisShape;
    protected int[][] coordinates;

    Shape() 
    {
        coordinates = new int[4][2];
    }

    void setShape(TetrisShape shape) 
    {
        for (int i = 0; i < 4; i++) 
        {
            System.arraycopy(shape.coord[i], 0, coordinates[i], 0, 2);
        }
        tetrisShape = shape;
    }

    protected void setX(int index, int x) 
    {
        coordinates[index][0] = x;
    }

    protected void setY(int index, int y) 
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

}
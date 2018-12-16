
package tetris;

class Calculations 
{

    private final int boardWidth;
    private final int boardHeight;

     Calculations() 
    {
        this.boardWidth = BoardPainter.WIDTH;
        this.boardHeight = BoardPainter.HEIGHT;
    }

     int getGridSquareWidth(double gridWidth) 
    {
        return (int) gridWidth / boardWidth;
    }

     int getGridSquareHeight(double gridHeight) 
    {
        return (int) gridHeight / boardHeight;
    }

     TetrisShape getShapeAt(int x, int y, TetrisShape[] board) 
    {
        return board[y * boardWidth + x];
    }

    
}

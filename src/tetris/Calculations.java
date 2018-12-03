
package tetris;

class Calculations 
{

    private final int boardWidth;
    private final int boardHeight;

     Calculations() 
    {
        this.boardWidth = Board.WIDTH;
        this.boardHeight = Board.HEIGHT;
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


package tetris;

public class TetrisFactory 
{
    public TetrisController getInstance(String str)
    {
        switch(str) 
        {
            case "Tetris": return TetrisController.getInstance();
            default: return new Null();
        }
    }
}

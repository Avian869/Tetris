
package tetris;

import java.awt.event.ActionEvent;

public class Null extends TetrisController
{

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        super.actionPerformed(ae);
    }

    @Override
    void start() 
    {
        super.start();
        gameStarted = false;
        isFalling = false;
        timer.stop();
    }
    
}

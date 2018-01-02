// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import Go.strategy.BasicAtariGoStrategy;
import Go.strategy.GoStrategy;

public class AtariGoMinMaxPlayer extends Player
{
    public AtariGoMinMaxPlayer(final int color, final GoStrategy strategy) {
        super(color);
        super.strategy = strategy;
    }
    
    public void initPlayer() {
    }
    
    public void positionChanged(final GoGameEvent goGameEvent) {
    }
    
    public void gameOver(final GoGameEvent goGameEvent) {
    }
    
    protected Move move(final GoPosition goPosition) {
        final Move nextMove;
        if ((nextMove = BasicAtariGoStrategy.tryBasicMove(goPosition)) == null) {
            return super.strategy.chooseNextMove(goPosition);
        }
        return nextMove;
    }
}

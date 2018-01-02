// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import Go.GoPosition;
import Go.Move;

public class GoNodeInfo
{
    public float estimatedValue;
    public Move lastMove;
    public Move nextMove;
    public GoPosition position;
    
    public GoNodeInfo(final float estimatedValue, final Move lastMove, final GoPosition position) {
        this.estimatedValue = estimatedValue;
        this.lastMove = lastMove;
        this.nextMove = null;
        this.position = position;
    }
    
    public int turn() {
        if (this.lastMove.color == 1) {
            return -1;
        }
        return 1;
    }
}

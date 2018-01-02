// 
// Decompiled by Procyon v0.5.30
// 

package Go.strategy;

import Go.GoPosition;

public abstract class BasicGoEstimator implements GoEstimatorInterface
{
    public float estimatePosition(final GoPosition position, final int color) {
        final int gameState;
        if ((gameState = this.checkPositionState(position)) == 100) {
            return this.heuristicPositionEstimation(position, color);
        }
        return this.gameResultValue(gameState, color);
    }
    
    protected abstract float heuristicPositionEstimation(final GoPosition p0, final int p1);
    
    protected float gameResultValue(final int gameState, final int color) {
        switch (gameState) {
            case 1: {
                if (color == 1) {
                    return 1000000.0f;
                }
                return -1000000.0f;
            }
            case -1: {
                if (color == 1) {
                    return -1000000.0f;
                }
                return 1000000.0f;
            }
            case 0: {
                return 0.0f;
            }
            default: {
                return Float.MAX_VALUE;
            }
        }
    }
    
    public abstract int checkPositionState(final GoPosition p0);
}

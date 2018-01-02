// 
// Decompiled by Procyon v0.5.30
// 

package Go;

import Go.strategy.GoStrategy;

public abstract class Player implements GoGameListener
{
    public int color;
    protected GoGame game;
    protected GoStrategy strategy;
    
    public Player() {
    }
    
    public Player(final int color) {
        this.color = color;
    }
    
    public void makeMove(final GoPosition goPosition) {
        final Move newMove = this.move(goPosition);
        this.game.newMove(newMove.x, newMove.y);
    }
    
    public GoStrategy getStrategy() {
        return this.strategy;
    }
    
    protected abstract Move move(final GoPosition p0);
    
    public void setGame(final GoGame game) {
        this.game = game;
    }
    
    public abstract void initPlayer();
    
    public abstract void gameOver(final GoGameEvent p0);
    
    public abstract void positionChanged(final GoGameEvent p0);
}

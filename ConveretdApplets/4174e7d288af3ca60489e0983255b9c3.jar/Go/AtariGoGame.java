// 
// Decompiled by Procyon v0.5.30
// 

package Go;

public class AtariGoGame extends GoGame
{
    private boolean invokedStandalone;
    private boolean captionOccured;
    
    public AtariGoGame() {
        this.invokedStandalone = false;
        this.captionOccured = false;
    }
    
    public AtariGoGame(final int boardSize, final Player blackPlayer, final Player whitePlayer) {
        super(boardSize, blackPlayer, whitePlayer);
        this.invokedStandalone = false;
        this.captionOccured = false;
    }
    
    public boolean gameOver() {
        if (super.nrCapturedStones <= 0 && super.consecutivePasses < 2) {
            return false;
        }
        if (super.consecutivePasses >= 2) {
            super.gameResult = 0;
        }
        else if (super.turn == -1) {
            super.gameResult = 1;
        }
        else {
            super.gameResult = -1;
        }
        return true;
    }
    
    public static void main(final String[] args) {
        final AtariGoGame atariGoGame = new AtariGoGame();
        atariGoGame.invokedStandalone = true;
    }
}

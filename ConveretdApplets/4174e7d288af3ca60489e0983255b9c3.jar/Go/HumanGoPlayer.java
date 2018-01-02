// 
// Decompiled by Procyon v0.5.30
// 

package Go;

public class HumanGoPlayer extends Player implements Runnable, MoveInputDeviceListener
{
    private boolean newMoveInput;
    private Move newMove;
    
    public HumanGoPlayer(final int color) {
        super(color);
        this.newMoveInput = false;
        this.newMove = null;
    }
    
    public void initPlayer() {
    }
    
    public void run() {
    }
    
    protected Move move(final GoPosition goPosition) {
        boolean newMoveMadeOK = false;
        this.newMove = null;
        while (!newMoveMadeOK) {
            while (this.newMove == null) {
                try {
                    Thread.currentThread();
                    Thread.sleep(100L);
                }
                catch (InterruptedException ex) {}
            }
            this.newMove.color = super.color;
            if (goPosition.isLegalMove(this.newMove)) {
                newMoveMadeOK = true;
            }
            else {
                this.illegalMoveAnnouncement();
                this.newMove = null;
            }
        }
        return this.newMove;
    }
    
    public void setGame(final GoGame game) {
        super.game = game;
    }
    
    public void moveAttempt(final Move move) {
        move.color = super.color;
        this.newMove = move;
    }
    
    public void positionChanged(final GoGameEvent goGameEvent) {
    }
    
    public void gameOver(final GoGameEvent goGameEvent) {
    }
    
    private void illegalMoveAnnouncement() {
    }
}

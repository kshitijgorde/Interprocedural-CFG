// 
// Decompiled by Procyon v0.5.30
// 

package pclient.anim;

import java.awt.Graphics;

public abstract class AnimRenderer
{
    protected boolean isAnimationReady;
    private PrepareThread prepareThread;
    protected static final boolean IS_DEBUG = false;
    private boolean isDone;
    protected long holdTimer;
    protected long holdSleep;
    protected long holdCurrent;
    
    public AnimRenderer() {
        this.isAnimationReady = false;
        this.prepareThread = null;
        this.isDone = false;
        this.holdTimer = 7000L;
        this.holdSleep = 700L;
        this.holdCurrent = 0L;
    }
    
    public final boolean isReady() {
        return this.isAnimationReady;
    }
    
    public final void initialize() {
        (this.prepareThread = new PrepareThread(this)).start();
    }
    
    public void setHoldTime(final long holdTimer) {
        if (holdTimer > 0L) {
            this.holdTimer = holdTimer;
        }
    }
    
    public abstract void prepare(final Thread p0);
    
    public abstract long runOneStep(final Graphics p0);
    
    public boolean isFinished() {
        return this.isDone;
    }
    
    protected void notifyAnimation() {
        this.isAnimationReady = true;
    }
    
    protected void setFinishedState(final boolean isDone) {
        this.isDone = isDone;
    }
    
    protected void doze(final Thread thread, final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            System.out.println("Renderer: Error in sleep().");
        }
    }
    
    protected void printDebug(final String s) {
    }
}

import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

class WCaret$CaretTimer extends Thread
{
    private volatile boolean resetFlag;
    private volatile boolean state;
    private final WCaret this$0;
    
    WCaret$CaretTimer(final WCaret this$0) {
        this.this$0 = this$0;
        this.resetFlag = false;
        this.state = false;
        this.setDaemon(true);
    }
    
    public final boolean getPaintState() {
        return this.state;
    }
    
    public final void run() {
        try {
            while (WCaret.I(this.this$0) == Thread.currentThread()) {
                synchronized (this) {
                    this.wait(500L);
                    if (this.resetFlag) {
                        this.resetFlag = false;
                    }
                    else {
                        this.state = !this.state;
                    }
                }
                final Rectangle z = WCaret.Z(this.this$0);
                this.this$0.repaint(z.x, z.y, z.width, z.height);
            }
        }
        catch (InterruptedException ex) {
            System.out.println("Caret interrupted...");
        }
    }
    
    static final boolean I(final WCaret$CaretTimer wCaret$CaretTimer, final boolean resetFlag) {
        return wCaret$CaretTimer.resetFlag = resetFlag;
    }
    
    static final boolean Z(final WCaret$CaretTimer wCaret$CaretTimer, final boolean state) {
        return wCaret$CaretTimer.state = state;
    }
}

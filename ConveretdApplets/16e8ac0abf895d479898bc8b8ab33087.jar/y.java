import java.util.TimerTask;

// 
// Decompiled by Procyon v0.5.30
// 

public final class y extends TimerTask
{
    public final x a;
    
    public y(final x a) {
        this.a = a;
    }
    
    public final void run() {
        x.a(this.a, false);
        this.a.repaint();
    }
}

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

// 
// Decompiled by Procyon v0.5.30
// 

class L implements LineListener
{
    private final M START;
    
    L(final M start) {
        this.START = start;
    }
    
    public final void update(final LineEvent lineEvent) {
        final LineEvent.Type type = lineEvent.getType();
        if (LineEvent.Type.START.equals(type)) {
            synchronized (this.START.B.Y) {
                this.START.B.Y.notifyAll();
            }
        }
        else if (LineEvent.Type.STOP.equals(type)) {
            synchronized (M.I(this.START)) {
                M.I(this.START).notifyAll();
            }
        }
    }
}

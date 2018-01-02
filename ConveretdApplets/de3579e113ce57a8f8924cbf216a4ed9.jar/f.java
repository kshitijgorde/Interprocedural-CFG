import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class f extends KeyAdapter
{
    private final transient nb;
    
    public void keyPressed(final KeyEvent keyEvent) {
        transient.a(this.nb, keyEvent, keyEvent.getKeyCode());
    }
    
    f(final transient nb) {
        this.nb = nb;
    }
}

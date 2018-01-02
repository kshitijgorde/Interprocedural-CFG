import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class g extends KeyAdapter
{
    private final d a;
    
    g(final d a) {
        this.a = a;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.a.L = true;
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 17) {
            this.a.L = false;
        }
    }
}

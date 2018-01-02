import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class h extends KeyAdapter
{
    private final e a;
    
    h(final e a) {
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

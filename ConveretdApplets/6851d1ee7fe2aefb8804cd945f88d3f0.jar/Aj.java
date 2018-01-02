import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Aj implements KeyListener, FocusListener
{
    private final n ta;
    
    Aj(final n ta) {
        this.ta = ta;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.ta.b(false);
            new Cj(this).start();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        synchronized (this.ta) {
            n.n(this.ta);
            n.c(this.ta);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void j() {
        n.d(this.ta);
        n.e(this.ta);
        n._(this.ta)._();
    }
    
    static n a(final Aj aj) {
        return aj.ta;
    }
    
    static void a(final Aj aj) {
        aj.j();
    }
}

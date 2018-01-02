import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

class zp implements KeyListener, FocusListener
{
    private final var n;
    
    zp(final var n) {
        this.n = n;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            var._(this.n).a(true);
            new Bp(this).start();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        synchronized (this.n) {
            var.b(this.n);
            var._(this.n);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void X() {
        var.a(this.n);
        var.l(this.n);
        var.b(this.n).b();
    }
    
    static var _(final zp zp) {
        return zp.n;
    }
    
    static void _(final zp zp) {
        zp.X();
    }
}

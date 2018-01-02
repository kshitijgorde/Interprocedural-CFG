import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;

// 
// Decompiled by Procyon v0.5.30
// 

class Dea implements KeyListener, FocusListener
{
    private final super da;
    
    Dea(final super da) {
        this.da = da;
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 10) {
            this.da.a(false);
            new Fea(this).start();
        }
    }
    
    public void focusLost(final FocusEvent focusEvent) {
        synchronized (this.da) {
            super.e(this.da);
            super.f(this.da);
        }
    }
    
    public void focusGained(final FocusEvent focusEvent) {
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    private void j() {
        super.g(this.da);
        super.h(this.da);
        super._(this.da).b();
    }
    
    static super _(final Dea dea) {
        return dea.da;
    }
    
    static void b(final Dea dea) {
        dea.j();
    }
}

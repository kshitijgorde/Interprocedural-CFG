import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class v extends Frame implements WindowListener
{
    private final esChat a;
    
    public v(final esChat a, final String s) {
        super(s);
        this.a = a;
        this.addWindowListener(this);
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.Qb = false;
        this.a.h();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
}

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class q extends WindowAdapter
{
    private final s a;
    
    q(final s a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        this.a.dispose();
    }
}

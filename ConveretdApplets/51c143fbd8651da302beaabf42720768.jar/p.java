import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class p extends WindowAdapter
{
    private final g a;
    
    p(final g a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.c = true;
        this.a.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        final g a = this.a;
        if (!d.r) {
            if (a.c) {
                return;
            }
            final g a2 = this.a;
        }
        a.setVisible(true);
    }
}

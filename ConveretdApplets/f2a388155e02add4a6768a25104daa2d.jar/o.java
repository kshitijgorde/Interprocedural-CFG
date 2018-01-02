import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

final class o extends WindowAdapter
{
    private final f a;
    
    o(final f a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.c = true;
        this.a.dispose();
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        final f a = this.a;
        if (fb.m == 0) {
            if (a.c) {
                return;
            }
            final f a2 = this.a;
        }
        a.show();
    }
}

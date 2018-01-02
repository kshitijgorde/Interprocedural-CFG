// 
// Decompiled by Procyon v0.5.30
// 

package b.a.e;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class c extends WindowAdapter
{
    private final k a;
    
    protected c(final k a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        if ((this.a.h & 0x8000) != 0x0 && (!this.a.k() || this.a.p)) {
            this.a.c = (this.a.d ? 1 : 2);
            this.a.dispose();
        }
        else {
            this.a.getToolkit().beep();
        }
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
        b.a.d.d.a(250L, new d(this));
    }
    
    static k a(final c c) {
        return c.a;
    }
}

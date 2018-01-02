// 
// Decompiled by Procyon v0.5.30
// 

package ji.graphic;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

class aae extends WindowAdapter
{
    private final /* synthetic */ oh a;
    
    aae(final oh a) {
        this.a = a;
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.a.h();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet;

import com.eventim.applet.b.ac;
import javax.swing.JToolTip;
import java.awt.Dimension;
import com.eventim.applet.a.g;
import com.eventim.applet.b.ae;

final class a extends ae
{
    private final EventimApplet b;
    
    a(final EventimApplet b, final g g, final Dimension dimension, final EventimApplet eventimApplet) {
        super(g, dimension, eventimApplet);
        this.b = b;
    }
    
    public final JToolTip createToolTip() {
        return new ac(this.b);
    }
}

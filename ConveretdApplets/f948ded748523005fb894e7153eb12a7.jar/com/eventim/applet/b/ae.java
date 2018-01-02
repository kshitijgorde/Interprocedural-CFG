// 
// Decompiled by Procyon v0.5.30
// 

package com.eventim.applet.b;

import java.awt.Graphics2D;
import com.eventim.applet.a.p;
import java.awt.Paint;
import com.eventim.applet.EventimApplet;
import java.awt.Dimension;
import com.eventim.applet.a.g;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Color;

public class ae extends t
{
    private static final Color b;
    private static final Stroke c;
    
    static {
        c = new BasicStroke(12.0f, 1, 2);
        b = new Color(1.0f, 0.0f, 0.0f, 0.9f);
    }
    
    public ae(final g g, final Dimension dimension, final EventimApplet eventimApplet) {
        super(g, dimension, eventimApplet);
    }
    
    public final void a(final short n) {
        final p[] b = this.e().b();
        p p = null;
        for (int i = 0; i < b.length; ++i) {
            if (b[i].i() == n) {
                p = b[i];
                break;
            }
        }
        if (p != null) {
            this.a(this.d());
            this.a.b(this);
            final Graphics2D c;
            (c = this.c()).setStroke(ae.c);
            c.setPaint(ae.b);
            c.draw(p.g_());
            this.repaint();
        }
    }
}

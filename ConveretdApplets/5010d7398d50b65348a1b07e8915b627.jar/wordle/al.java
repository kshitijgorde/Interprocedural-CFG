// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.core.c.f;
import java.util.Iterator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import java.awt.geom.AffineTransform;
import java.awt.font.FontRenderContext;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import wordle.core.b.a.d;
import java.awt.Color;
import java.util.Collections;
import wordle.core.c.a;
import java.util.Map;
import wordle.core.b.a.c;
import wordle.core.b.a.e;
import wordle.core.d.h;

final class al implements Runnable
{
    private /* synthetic */ w a;
    private final /* synthetic */ I b;
    
    al(final w a, final I b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        final c a;
        if ((a = h.a(this.a.g)) != null) {
            final w a2 = this.a;
            final w a3 = this.a;
            final c c = a;
            w.a(a3, c);
            w.b(a2, c);
            this.b.a(this.a.g);
            this.b.setSelected(true);
            w.a(this.a, "Set Palette");
        }
    }
}

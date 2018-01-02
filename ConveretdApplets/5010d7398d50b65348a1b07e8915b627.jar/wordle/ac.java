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
import wordle.core.b.a.e;
import wordle.core.c.a;
import java.util.Map;

final class ac implements Runnable
{
    private /* synthetic */ w a;
    private final /* synthetic */ double b;
    
    ac(final w a, final double b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        w.a(this.a, this.b);
        w.a(this.a, "Set Color Variance");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.Iterator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import wordle.core.c.d;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import wordle.core.fitness.OverallShape;
import wordle.core.z;
import wordle.core.c.a;
import java.util.Map;
import wordle.core.c.f;

final class u implements Runnable
{
    private /* synthetic */ l a;
    
    u(final l a) {
        this.a = a;
    }
    
    public final void run() {
        final f a = this.a.c.a();
        this.a.c.a(new f("Relayout", a.b, a.c, a.d.b(a.b.d)));
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import java.util.Iterator;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import wordle.core.c.d;
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
import wordle.core.fitness.CenterLine;
import wordle.core.fitness.Alphabetical;
import javax.swing.JCheckBoxMenuItem;

final class o implements Runnable
{
    private /* synthetic */ l a;
    private final /* synthetic */ JCheckBoxMenuItem b;
    
    o(final l a, final JCheckBoxMenuItem b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        final f a = this.a.c.a();
        if (this.b.isSelected() == Alphabetical.class.equals(a.b.b)) {
            return;
        }
        this.a.c.a(new f("Change Alphabetical", a.b.a((Class)(this.b.isSelected() ? Alphabetical.class : CenterLine.class)), a.c, a.d.f()));
    }
}

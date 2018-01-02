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
import wordle.core.c.a;
import java.util.Map;
import wordle.core.c.f;
import wordle.core.z;

final class q implements Runnable
{
    private /* synthetic */ l a;
    private final /* synthetic */ z b;
    private final /* synthetic */ String c;
    
    q(final l a, final z b, final String c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void run() {
        l.a(this.a, this.b);
        final f a = this.a.c.a();
        if (this.b == a.b.d) {
            return;
        }
        this.a.c.a(new f("Change to " + this.c, a.b.a(this.b), a.c, a.d.a(this.b)));
    }
}

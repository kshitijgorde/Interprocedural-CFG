// 
// Decompiled by Procyon v0.5.30
// 

package wordle;

import wordle.a.i;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import cue.lang.c;
import java.awt.event.ActionListener;
import cue.lang.stop.StopWords;
import java.util.Iterator;
import javax.swing.AbstractButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import wordle.core.c.d;
import javax.swing.Action;
import java.util.Collections;
import java.util.LinkedHashMap;
import javax.swing.JCheckBoxMenuItem;
import wordle.core.c.a;
import java.util.Map;
import wordle.core.f;

final class E implements Runnable
{
    private /* synthetic */ k a;
    private final /* synthetic */ f b;
    
    E(final k a, final f b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        k.a(this.a, this.b);
        final wordle.core.c.f a = this.a.c.a();
        if (this.b == a.b.f) {
            return;
        }
        this.a.b.a("Change Case Handling", a.b.a(this.b), a.c);
    }
}

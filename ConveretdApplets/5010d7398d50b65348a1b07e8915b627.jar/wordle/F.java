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
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.Action;
import java.util.Collections;
import java.util.LinkedHashMap;
import javax.swing.JCheckBoxMenuItem;
import wordle.core.c.a;
import java.util.Map;
import wordle.core.f;
import javax.swing.JMenuItem;
import wordle.core.c.d;

final class F implements d
{
    private /* synthetic */ k a;
    private final /* synthetic */ JMenuItem b;
    private final /* synthetic */ f c;
    
    F(final k a, final JMenuItem b, final f c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public final void a(final wordle.core.c.f f) {
        k.a(this.a, f.b.f);
        this.b.setSelected(this.a.e == this.c);
    }
}

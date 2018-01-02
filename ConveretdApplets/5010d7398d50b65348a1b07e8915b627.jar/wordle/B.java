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
import wordle.core.f;
import wordle.core.c.a;
import java.util.Map;

final class B implements Runnable
{
    private /* synthetic */ k a;
    
    B(final k a) {
        this.a = a;
    }
    
    public final void run() {
        final j j;
        (j = new j(this.a.b, k.a(this.a, this.a.b.e()))).setVisible(true);
        j.dispose();
    }
}

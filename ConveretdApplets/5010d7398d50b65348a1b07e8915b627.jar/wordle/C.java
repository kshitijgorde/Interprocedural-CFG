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
import wordle.core.c.f;
import java.awt.event.ActionEvent;
import cue.lang.stop.StopWords;
import java.awt.event.ActionListener;

final class C implements ActionListener
{
    private /* synthetic */ k a;
    private final /* synthetic */ StopWords b;
    
    C(final k a, final StopWords b) {
        this.a = a;
        this.b = b;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final f a = this.a.c.a();
        this.a.b.a("Change Common Words Setting", a.b.a(this.b), a.c);
    }
}

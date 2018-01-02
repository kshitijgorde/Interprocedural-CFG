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

public final class k
{
    private static final Map a;
    private final WordleApplet b;
    private final a c;
    private final ai d;
    private f e;
    private final JCheckBoxMenuItem f;
    
    static {
        final LinkedHashMap<String, f> linkedHashMap;
        (linkedHashMap = new LinkedHashMap<String, f>()).put("Leave Words as Spelled", null);
        linkedHashMap.put("make all words lower-case", f.b);
        linkedHashMap.put("MAKE ALL WORDS UPPER-CASE", f.a);
        linkedHashMap.put("Guess Case for Each Word", f.c);
        a = Collections.unmodifiableMap((Map<?, ?>)linkedHashMap);
    }
    
    public k(final WordleApplet b, final a c, final ai d) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = null;
        this.f = new JCheckBoxMenuItem(d.a("Remove numbers", new x(this)));
        c.a(new y(this));
    }
    
    public final JMenu a() {
        final JMenu menu;
        (menu = new JMenu("Language")).setMnemonic(71);
        final ButtonGroup buttonGroup = new ButtonGroup();
        menu.add(this.f);
        menu.addSeparator();
        for (final Map.Entry<String, V> entry : k.a.entrySet()) {
            final JMenu menu2 = menu;
            final ButtonGroup buttonGroup2 = buttonGroup;
            final String s = entry.getKey();
            final f f = (f)entry.getValue();
            final String s2 = s;
            final ButtonGroup buttonGroup3 = buttonGroup2;
            final JMenu menu3 = menu2;
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(this.d.a(s2, new E(this, f)));
            menu3.add(radioButtonMenuItem);
            buttonGroup3.add(radioButtonMenuItem);
            this.c.a(new F(this, radioButtonMenuItem, f));
            radioButtonMenuItem.setSelected(entry.getValue() == this.e);
        }
        menu.addSeparator();
        this.a(menu);
        menu.addSeparator();
        menu.add(new JMenuItem(this.d.a("Show word counts...", new B(this))));
        return menu;
    }
    
    private void a(final JMenu menu) {
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.a(menu, buttonGroup, "Do Not Remove Common Words", null).setSelected(true);
        StopWords[] values;
        for (int length = (values = StopWords.values()).length, i = 0; i < length; ++i) {
            final StopWords stopWords;
            if ((stopWords = values[i]) != StopWords.a) {
                this.a(menu, buttonGroup, "Remove common " + stopWords.name() + " Words", stopWords);
            }
        }
    }
    
    private JMenuItem a(final JMenu menu, final ButtonGroup buttonGroup, final String s, final StopWords stopWords) {
        final JRadioButtonMenuItem radioButtonMenuItem;
        (radioButtonMenuItem = new JRadioButtonMenuItem(s)).addActionListener(new C(this, stopWords));
        this.c.a(new D(this, radioButtonMenuItem, stopWords));
        menu.add(radioButtonMenuItem);
        buttonGroup.add(radioButtonMenuItem);
        return radioButtonMenuItem;
    }
}

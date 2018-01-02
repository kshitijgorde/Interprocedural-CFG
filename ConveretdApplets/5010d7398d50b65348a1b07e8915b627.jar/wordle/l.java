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

public final class l
{
    private static final Map a;
    private final WordleApplet b;
    private final a c;
    private final ai d;
    private z e;
    private OverallShape f;
    
    static {
        final LinkedHashMap<String, z> linkedHashMap;
        (linkedHashMap = new LinkedHashMap<String, z>()).put("Any Which Way", z.f);
        linkedHashMap.put("Horizontal", z.a);
        linkedHashMap.put("Mostly Horizontal", z.b);
        linkedHashMap.put("Half and Half", z.c);
        linkedHashMap.put("Mostly Vertical", z.d);
        linkedHashMap.put("Vertical", z.e);
        a = Collections.unmodifiableMap((Map<?, ?>)linkedHashMap);
    }
    
    public l(final WordleApplet b, final a c, final ai d) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.a();
        this.b();
    }
    
    public final void a() {
        this.e = new ArrayList<z>(l.a.values()).get((int)(Math.random() * l.a.size()));
    }
    
    public final void b() {
        this.f = ((Math.random() < 0.5) ? OverallShape.b : OverallShape.a);
    }
    
    public final z c() {
        return this.e;
    }
    
    public final JMenu d() {
        final JMenu menu;
        (menu = new JMenu("Layout")).setMnemonic(76);
        final JMenuItem menuItem = new JMenuItem(this.d.a("Re-layout with current settings", new u(this)));
        WordleApplet.a('r', menuItem);
        menu.add(menuItem);
        menu.addSeparator();
        menu.add(new JMenuItem(this.d.a("Maximum words...", new v(this))));
        menu.addSeparator();
        final JMenu menu2 = menu;
        final JCheckBoxMenuItem checkBoxMenuItem;
        (checkBoxMenuItem = new JCheckBoxMenuItem()).setAction(this.d.a("Prefer Alphabetical Order", new o(this, checkBoxMenuItem)));
        this.c.a(new p(this, checkBoxMenuItem));
        menu2.add(checkBoxMenuItem);
        menu.addSeparator();
        final ButtonGroup buttonGroup = new ButtonGroup();
        this.a(menu, buttonGroup, "Rounder Edges", OverallShape.b).setSelected(this.f == OverallShape.b);
        this.a(menu, buttonGroup, "Straighter Edges", OverallShape.a).setSelected(this.f == OverallShape.a);
        menu.addSeparator();
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        for (final Map.Entry<String, V> entry : l.a.entrySet()) {
            final JMenu menu3 = menu;
            final ButtonGroup buttonGroup3 = buttonGroup2;
            final String s = entry.getKey();
            final z z = (z)entry.getValue();
            final String s2 = s;
            final ButtonGroup buttonGroup4 = buttonGroup3;
            final JMenu menu4 = menu3;
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(this.d.a(s2, new q(this, z, s2)));
            menu4.add(radioButtonMenuItem);
            buttonGroup4.add(radioButtonMenuItem);
            this.c.a(new r(this, radioButtonMenuItem, z));
            radioButtonMenuItem.setSelected(entry.getValue() == this.e);
        }
        return menu;
    }
    
    private JMenuItem a(final JMenu menu, final ButtonGroup buttonGroup, final String s, final OverallShape overallShape) {
        final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(this.d.a(s, new s(this, overallShape, s)));
        menu.add(radioButtonMenuItem);
        buttonGroup.add(radioButtonMenuItem);
        this.c.a(new t(this, radioButtonMenuItem, overallShape));
        return radioButtonMenuItem;
    }
    
    public final void e() {
        this.e = z.a;
    }
    
    public final OverallShape f() {
        return this.f;
    }
}

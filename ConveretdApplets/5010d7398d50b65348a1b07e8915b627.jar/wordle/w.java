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

public final class w
{
    private static final Map a;
    private static final Map b;
    private final a c;
    private final ai d;
    private double e;
    private e f;
    private e g;
    
    static {
        a = Collections.unmodifiableMap((Map<?, ?>)new am());
        b = Collections.unmodifiableMap((Map<?, ?>)new aj());
    }
    
    private static final e b(final int n, final int... array) {
        final Color color = new Color(n);
        final Color[] array2 = new Color[array.length];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = new Color(array[i]);
        }
        return new d(array2, color);
    }
    
    public w(final a c, final ai d) {
        this.g = b(16777215, 0, 3355443, 6710886, 8947848);
        this.c = c;
        this.d = d;
        this.a();
    }
    
    public final wordle.core.b.a.a a() {
        this.f = new ArrayList<e>(w.a.values()).get((int)(Math.random() * w.a.size()));
        this.e = 0.08;
        return this.c();
    }
    
    private wordle.core.b.a.a c() {
        return new wordle.core.b.a.a(this.f, this.e);
    }
    
    public final JMenu b() {
        final JMenu menu;
        (menu = new JMenu("Color")).setMnemonic(67);
        menu.putClientProperty(new StringBuffer("AATextPropertyKey"), true);
        final JMenuItem menuItem = new JMenuItem(this.d.a("Recolor", new ah(this)));
        menu.add(menuItem);
        WordleApplet.a('e', menuItem);
        menu.addSeparator();
        final FontRenderContext fontRenderContext = new FontRenderContext(null, true, true);
        int max = 0;
        final Iterator<String> iterator = w.a.keySet().iterator();
        while (iterator.hasNext()) {
            max = Math.max(max, (int)menu.getFont().getStringBounds(iterator.next(), fontRenderContext).getWidth());
        }
        final ButtonGroup buttonGroup = new ButtonGroup();
        for (final Map.Entry<String, V> entry : w.a.entrySet()) {
            this.a(menu, buttonGroup, max, entry.getKey(), (e)entry.getValue()).setSelected(this.f.equals(entry.getValue()));
        }
        menu.addSeparator();
        menu.add(this.d.a("Edit custom palette...", new al(this, this.a(menu, buttonGroup, max, "Custom Palette", this.g))));
        menu.addSeparator();
        final ButtonGroup buttonGroup2 = new ButtonGroup();
        for (final Map.Entry<String, V> entry2 : w.b.entrySet()) {
            final JMenu menu2 = menu;
            final ButtonGroup buttonGroup3 = buttonGroup2;
            final String s = entry2.getKey();
            final double doubleValue = (double)entry2.getValue();
            final String s2 = s;
            final ButtonGroup buttonGroup4 = buttonGroup3;
            final JMenu menu3 = menu2;
            final JRadioButtonMenuItem radioButtonMenuItem = new JRadioButtonMenuItem(this.d.a(s2, new ac(this, doubleValue)));
            menu3.add(radioButtonMenuItem);
            buttonGroup4.add(radioButtonMenuItem);
            this.c.a(new ad(this, radioButtonMenuItem, doubleValue));
            radioButtonMenuItem.setSelected((double)entry2.getValue() == 0.1);
        }
        return menu;
    }
    
    private I a(final JMenu menu, final ButtonGroup buttonGroup, final int n, final String s, final e e) {
        final I i = new I(this.d.a(s, new ak(this, (e == this.g) ? null : e)), e, n);
        menu.add(i);
        buttonGroup.add(i);
        this.c.a(new ab(this, i, e));
        return i;
    }
}

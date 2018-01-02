// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.net.URL;
import javax.swing.Icon;
import java.awt.Dimension;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Font;
import java.util.Hashtable;

public class al
{
    private static Hashtable a;
    private static /* synthetic */ boolean b;
    
    public static Font a(final String s) {
        return z.a(G.a(s, "font-family"), G.a(s, "font-weight"), Integer.parseInt(G.a(s, "font-size")));
    }
    
    public static void a(final String s, final JComponent component) {
        component.setFont(a(s));
        component.setForeground(a(s, "color"));
    }
    
    public static Color a(final String s, final String s2) {
        return V.c(G.a(s, s2));
    }
    
    public static JLabel a(final bg bg) {
        return b(bg.c("style"), bg.c("caption"));
    }
    
    public static JLabel b(final String s, String c) {
        final JLabel label = new JLabel();
        a(s, label);
        if (c.startsWith("$")) {
            c = G.c(c.substring(1));
        }
        label.setText(c);
        label.setOpaque(false);
        return label;
    }
    
    public static K b(final bg bg) {
        final K k;
        (k = new K()).setOpaque(false);
        k.setCursor(Cursor.getPredefinedCursor(12));
        if (bg.a("caption")) {
            final String c = bg.c("style");
            String text = bg.c("caption");
            a(c, k);
            if (text.startsWith("$")) {
                text = G.c(text.substring(1));
            }
            k.setText(text);
        }
        if (bg.a("image")) {
            k.setIcon(a(bg.i("image")));
        }
        if (bg.a("action")) {
            k.a = bg.c("action");
        }
        k.setHorizontalAlignment(0);
        return k;
    }
    
    public static bd a(final bg bg, final String s) {
        if (!al.b && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!al.b && !bg.b().equals("button")) {
            throw new AssertionError();
        }
        final bd bd;
        (bd = new bd()).setLayout(new BorderLayout());
        bd.setBorder(BorderFactory.createRaisedBevelBorder());
        bd.setOpaque(true);
        bd.setBackground(V.c(G.a(s, "back-color")));
        bd.a(V.c(G.a(s, "roll-over-color")));
        final JLabel b;
        (b = b(s, bg.c("caption"))).setHorizontalAlignment(0);
        bd.add(b, "South");
        final JLabel label;
        (label = new JLabel()).setIcon(a(bg.i("icon")));
        label.setHorizontalAlignment(0);
        label.setVerticalAlignment(0);
        bd.add(label, "Center");
        return bd;
    }
    
    public static x c(final bg bg) {
        final x x;
        (x = new x()).setPreferredSize(bg.e("size"));
        final String c = bg.c("style");
        x.a(V.c(G.a(c, "outline-color")));
        x.setBackground(V.c(G.a(c, "back-color")));
        x.setForeground(V.c(G.a(c, "fore-color")));
        x.b(V.c(G.a(c, "error-color")));
        return x;
    }
    
    public static JLabel d(final bg bg) {
        final JLabel label;
        (label = new JLabel()).setOpaque(false);
        final Icon a = a(bg.i("image"));
        label.setIcon(a);
        label.setPreferredSize(new Dimension(a.getIconWidth(), a.getIconHeight()));
        return label;
    }
    
    private static Icon a(final URL url) {
        if (al.a.containsKey(url)) {
            return al.a.get(url);
        }
        final ImageIcon a = V.a(url);
        al.a.put(url, a);
        return a;
    }
    
    public static JComboBox e(final bg bg) {
        if (!al.b && !bg.b().equals("comboBox")) {
            throw new AssertionError();
        }
        final ArrayList<B> list = new ArrayList<B>();
        final Iterator<bg> iterator = bg.a().iterator();
        while (iterator.hasNext()) {
            final bg bg2;
            if ((bg2 = iterator.next()).b().equals("entry")) {
                list.add(new B(bg2));
            }
        }
        final JComboBox comboBox;
        (comboBox = new JComboBox<Object>(list.toArray())).setName(bg.c("id"));
        a(bg.c("style"), comboBox);
        return comboBox;
    }
    
    public static aC f(final bg bg) {
        return new aC(bg);
    }
    
    static {
        al.b = !al.class.desiredAssertionStatus();
        al.a = new Hashtable();
    }
}

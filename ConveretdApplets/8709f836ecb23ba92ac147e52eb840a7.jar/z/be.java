// 
// Decompiled by Procyon v0.5.30
// 

package z;

import javax.swing.Box;
import javax.swing.Icon;
import java.awt.Component;
import javax.swing.JList;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Container;
import javax.swing.BoxLayout;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.JPanel;

public class be extends JPanel implements ListCellRenderer
{
    private JLabel a;
    private JLabel b;
    private Hashtable c;
    private int d;
    private int e;
    private static /* synthetic */ boolean f;
    
    public be(final bg bg) {
        this.a = new JLabel();
        this.b = new JLabel();
        this.c = new Hashtable();
        if (!be.f && bg == null) {
            throw new AssertionError();
        }
        this.d = bg.b("indentPx");
        this.e = bg.b("iconTextGap");
        final bg f = bg.f("smallIcons");
        this.c.put(o.f, V.a(f.i("desktop")));
        this.c.put(o.d, V.a(f.i("myDocs")));
        this.c.put(o.e, V.a(f.i("myPics")));
        this.c.put(o.c, V.a(f.i("harddrive")));
        this.c.put(o.a, V.a(f.i("folder")));
        this.c.put(o.b, V.a(f.i("computer")));
        this.c.put(o.g, V.a(f.i("mac_volumes")));
        this.setOpaque(true);
        this.setLayout(new BoxLayout(this, 2));
        this.b.setPreferredSize(new Dimension(16, 16));
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        this.removeAll();
        if (o == null) {
            return this;
        }
        final at at = (at)o;
        if (b) {
            this.setBackground(list.getSelectionBackground());
            this.setForeground(list.getSelectionForeground());
        }
        else {
            this.setBackground(list.getBackground());
            this.setForeground(list.getForeground());
        }
        this.b.setIcon((Icon)this.c.get(at.g));
        this.a.setText(at.c);
        this.removeAll();
        if (at.f > 0) {
            this.add(Box.createRigidArea(new Dimension(at.f * this.d, 1)));
        }
        this.add(this.b);
        this.add(Box.createRigidArea(new Dimension(this.e, 1)));
        this.add(this.a);
        this.add(Box.createHorizontalGlue());
        return this;
    }
    
    static {
        be.f = !be.class.desiredAssertionStatus();
    }
}

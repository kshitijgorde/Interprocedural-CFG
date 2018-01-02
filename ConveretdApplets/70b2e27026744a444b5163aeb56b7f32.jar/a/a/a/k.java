// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import javax.swing.JComponent;
import javax.accessibility.Accessible;
import b.a.c.q;
import java.awt.FontMetrics;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.JList;

class k extends JList implements ListCellRenderer
{
    private JScrollPane a;
    private int b;
    private int c;
    private final j d;
    
    public k(final j d) {
        this.d = d;
        this.setCellRenderer(this);
        final FontMetrics fontMetrics = this.getFontMetrics(j.d(d));
        final FontMetrics fontMetrics2 = this.getFontMetrics(j.e(d));
        final int max = Math.max(fontMetrics.getDescent(), fontMetrics2.getDescent());
        final int max2 = Math.max(fontMetrics.getLeading(), fontMetrics2.getLeading());
        this.b = Math.max(fontMetrics.getAscent(), fontMetrics2.getAscent());
        this.c = this.b + max + max2;
        final JLabel label = new JLabel("X");
        label.setFont(j.e(d));
        final int height = label.getPreferredSize().height;
        if (height > this.c) {
            this.b += height - this.c;
            this.c = height;
        }
        this.setFixedCellHeight(this.c);
        this.a = new JScrollPane(this, 22, 31);
        this.setVisibleRowCount(j.s());
    }
    
    public JScrollPane a() {
        return this.a;
    }
    
    public Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        Accessible accessible;
        if (o instanceof q) {
            accessible = new l(this, (q)o);
        }
        else {
            accessible = new JLabel(o.toString());
        }
        ((JComponent)accessible).setFont(j.d(this.d));
        if (b && accessible instanceof l) {
            ((JComponent)accessible).setBackground(list.getSelectionBackground());
            ((JComponent)accessible).setForeground(list.getSelectionForeground());
        }
        else {
            ((JComponent)accessible).setBackground(list.getBackground());
            ((JComponent)accessible).setForeground(list.getForeground());
        }
        return (Component)accessible;
    }
    
    static int a(final k k) {
        return k.c;
    }
    
    static j b(final k k) {
        return k.d;
    }
    
    static JScrollPane c(final k k) {
        return k.a;
    }
    
    static int d(final k k) {
        return k.b;
    }
}

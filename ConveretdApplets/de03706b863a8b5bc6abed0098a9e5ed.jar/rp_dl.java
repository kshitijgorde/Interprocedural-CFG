import java.awt.Dimension;
import java.awt.Component;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.event.EventListenerList;
import java.util.Vector;
import javax.swing.JLabel;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

// 
// Decompiled by Procyon v0.5.30
// 

public class rp_dl extends JPanel
{
    public int a;
    public int b;
    public int c;
    private int f;
    public MouseListener a;
    public MouseMotionListener a;
    private rp_by a;
    private JPanel a;
    private JLabel a;
    public Vector b;
    public int d;
    public int e;
    public Object a;
    private EventListenerList a;
    
    public rp_dl(final ImageIcon imageIcon, final ImageIcon imageIcon2) {
        super(new BorderLayout());
        this.a = 0;
        this.b = 0;
        this.f = 0;
        this.a = null;
        this.a = null;
        this.a = new JPanel(new GridLayout());
        this.a = new JLabel();
        this.b = new Vector(6, 6);
        this.d = 0;
        this.e = 0;
        this.a = null;
        this.a = new EventListenerList();
        (this.a = new rp_by(imageIcon, imageIcon2)).a(this);
        this.c = 0;
        this.a.setBackground(null);
        this.add(this.a, "North");
        this.add(this.a, "Center");
    }
    
    public rp_dl(final rp_by a) {
        super(new BorderLayout());
        this.a = 0;
        this.b = 0;
        this.f = 0;
        this.a = null;
        this.a = null;
        this.a = new JPanel(new GridLayout());
        this.a = new JLabel();
        this.b = new Vector(6, 6);
        this.d = 0;
        this.e = 0;
        this.a = null;
        this.a = new EventListenerList();
        (this.a = a).a(this);
        this.c = 0;
        this.a.setBackground(null);
        this.add(this.a, "North");
        this.add(this.a, "Center");
    }
    
    public final void a(final int n) {
        if (this.a != n) {
            this.a = n;
            if (this.a.getLayout() instanceof GridLayout) {
                ((GridLayout)this.a.getLayout()).setColumns(n);
            }
            this.a(true);
        }
    }
    
    public final void b(final int n) {
        if (this.b != n) {
            this.b = n;
            if (this.a.getLayout() instanceof GridLayout) {
                ((GridLayout)this.a.getLayout()).setRows(n);
            }
            this.a(true);
        }
    }
    
    public final void a(final int n, final int n2) {
        this.d = (int)Math.max(1.05 * n, this.d);
        this.e = (int)Math.max(1.05 * n2, this.e);
    }
    
    public final void a(final Component component) {
        this.removeAll();
        this.add(this.a, "North");
        if (component != null) {
            this.add(component, "Center");
        }
        else {
            this.add(this.a, "Center");
        }
        this.repaint();
    }
    
    public void setEnabled(final boolean b) {
        super.setEnabled(b);
        this.a.setEnabled(b);
    }
    
    public final int a() {
        return this.b * this.a;
    }
    
    public final void a(int n, final Object a) {
        this.a = a;
        this.b.removeAllElements();
        this.c(n);
        this.c = 0;
        final int n2 = 0;
        n = 0;
        n = n2;
        this.d = 0;
        this.e = 0;
        this.a(true);
    }
    
    public final void c(final int size) {
        this.b.setSize(size);
    }
    
    public final void a(final int n, final rp_ai rp_ai) {
        this.b.setElementAt(rp_ai, n);
        this.a(false);
    }
    
    public final int b() {
        if (this.b == null) {
            return 0;
        }
        return this.b.size();
    }
    
    public final rp_ai a(final int n) {
        if (this.b != null && n >= 0 && n < this.b.size()) {
            return this.b.elementAt(n);
        }
        return null;
    }
    
    public final Dimension a() {
        Dimension dimension;
        if ((dimension = this.a.getSize()).width == 0 || dimension.height == 0) {
            dimension = this.a.getSize();
        }
        return dimension;
    }
    
    private void a() {
        int n = -1;
        int n2 = -1;
        for (int i = this.c; i < this.c + this.a(); ++i) {
            if (i < this.b() && this.b.elementAt(i) == null) {
                if (n == -1) {
                    n = i;
                }
                n2 = i;
            }
        }
        if (n != -1) {
            final rp_dl rp_dl = this;
            final int n3 = n;
            final int n4 = n2 - n + 1;
            final int n5 = n3;
            this = rp_dl;
            Object[] listenerList;
            for (int j = (listenerList = rp_dl.a.getListenerList()).length - 2; j >= 0; j -= 2) {
                if (listenerList[j] == rp_ar.class) {
                    ((rp_ar)listenerList[j + 1]).a(this, n5, n4);
                }
            }
        }
    }
    
    public final void a(final boolean b) {
        this.a.removeAll();
        for (int i = 0; i < this.a(); ++i) {
            final int n = this.c + i;
            Component a = null;
            if (n < this.b()) {
                if (this.b.elementAt(n) == null) {
                    a = new JLabel(rp_au.a.a().a(0, "ld1"));
                }
                else {
                    a = this.b.elementAt(n).a();
                }
                a.removeMouseListener(this.a);
                a.addMouseListener(this.a);
                a.removeMouseMotionListener(this.a);
                a.addMouseMotionListener(this.a);
            }
            if (a == null) {
                a = new JLabel();
            }
            this.a.add(a);
        }
        if (b) {
            this.a();
        }
        this.a.a();
        this.a.repaint();
        this.a.doLayout();
    }
    
    public final void a(final rp_ar rp_ar) {
        this.a.add(rp_ar.class, rp_ar);
    }
}

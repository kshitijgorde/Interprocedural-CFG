import java.awt.Event;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public class w extends Panel
{
    private e a;
    private int b;
    private Scrollbar c;
    private Scrollbar d;
    private Panel e;
    private Font f;
    private t g;
    private Vector h;
    private int i;
    private bb j;
    private bu k;
    
    public void a() {
        this.a.a();
    }
    
    public void a(final String s) {
        this.a.a(s);
    }
    
    public void b() {
        this.a.b();
    }
    
    public w(final int n, final boolean b, final t g, final bu k, final bk bk, final bq bq) {
        this.b = 30;
        this.h = new Vector();
        this.i = 0;
        this.j = null;
        this.g = g;
        this.k = k;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        this.a = new e(n, false, g, k, bk, bq);
        this.c = new Scrollbar(1, 0, 1, 0, this.b);
        this.d = new Scrollbar(0, 0, 1, 0, this.b);
        this.a.a(this.d, this.c);
        this.setLayout(new BorderLayout());
        this.e = new Panel();
        this.setLayout(layout);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 100.0;
        gridBagConstraints.weighty = 100.0;
        layout.setConstraints(this.e, gridBagConstraints);
        this.add(this.e);
        this.e.setLayout(layout);
        gridBagConstraints.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(this.a, gridBagConstraints);
        this.e.add(this.a);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 2.0;
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.weighty = 1.0;
        layout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        this.show();
    }
    
    public String b(final String s) {
        return this.a.c(s);
    }
    
    public String c() {
        return this.a.c();
    }
    
    public int d() {
        return this.a.d();
    }
    
    public void e() {
        this.a.i();
    }
    
    public void f() {
        this.a.j();
    }
    
    public String c(final String s) {
        final boolean dx = bm.dX;
        int n = 0;
        int size = 0;
        while (true) {
            while (true) {
                Label_0054: {
                    if (!dx) {
                        break Label_0054;
                    }
                    this.b(this.a.a(n).toLowerCase()).startsWith(s);
                    if (size != 0) {
                        this.h.addElement(this.b(this.a.a(n)));
                    }
                    ++n;
                }
                if (n != this.a.e()) {
                    continue;
                }
                break;
            }
            size = this.h.size();
            if (dx) {
                continue;
            }
            break;
        }
        if (size == 0) {
            return null;
        }
        if (this.h.size() == 1) {
            return this.h.elementAt(0);
        }
        if (this.i < this.h.size()) {
            return this.h.elementAt(this.i++);
        }
        return null;
    }
    
    public void a(final Font font) {
        this.a.a(font);
    }
    
    public void g() {
        this.h.removeAllElements();
        this.i = 0;
    }
    
    public void d(final String s) {
        this.a.h(s);
    }
    
    public void a(final Graphics graphics, final String s, final int n, final int n2, final Rectangle rectangle) {
        this.a.a(graphics, this.a.g(s), n, n2, rectangle);
    }
    
    public int e(final String s) {
        return this.a.i(s);
    }
    
    public void h() {
        this.a.k();
    }
    
    public void i() {
        this.a.repaint();
    }
    
    public boolean handleEvent(final Event event) {
        final Object target = event.target;
        if (event.id >= 601 && event.id <= 605) {
            if (target == this.c) {
                this.a.b(0);
                this.d.setValue(0);
                this.a.c(this.c.getValue());
            }
            if (target == this.d) {
                this.a.b(this.d.getValue());
            }
        }
        return false;
    }
    
    public void b(final Font font) {
        this.a.b(font);
    }
    
    public void a(final String s, final String s2) {
        this.a.a(s, s2);
    }
    
    public void a(final Panel panel) {
        this.a.a(panel);
    }
    
    public void a(final bb j) {
        this.j = j;
    }
}

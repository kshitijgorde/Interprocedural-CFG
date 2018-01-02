// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Point;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

public class bo extends Panel
{
    public transient Q a;
    public transient Q b;
    private Color e;
    private transient Image f;
    private transient Image p;
    private boolean i;
    private TextField e;
    private TextField f;
    private TextField g;
    private Label a;
    private boolean k;
    public Point a;
    private ag a;
    private S a;
    private d a;
    private Checkbox i;
    private Checkbox j;
    private Checkbox k;
    private Checkbox l;
    private Panel b;
    private CardLayout b;
    public int b;
    private aa c;
    private be m;
    private al f;
    private al g;
    
    public bo(final aa c, final be m) {
        this.i = false;
        this.k = false;
        this.a = new Point();
        this.b = 0;
        this.c = c;
        this.m = m;
        this.b = new CardLayout(0, 0);
        this.e = this.c.getBackground();
        this.f = new al(80, 20);
        this.g = new al(80, 20);
        this.a = new d(25);
        this.setBackground(m.c.c);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        final n n = new n();
        final n n2 = new n();
        n2.setLayout(gridBagLayout);
        n2.setBackground(this.m.c.c);
        n2.setForeground(this.m.c.c);
        n.setLayout(gridBagLayout);
        n.setBackground(this.m.c.k);
        n.setForeground(this.m.c.j);
        (this.b = new Panel()).setLayout(this.b);
        this.b.add("a1", this.a());
        this.b.add("a2", new bg(this, c.a, !c.j));
        n.add(this.b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 1));
        n2.setLayout(gridBagLayout);
        final Component b = this.b();
        panel.setBackground(this.m.c.k);
        panel.setForeground(this.m.c.j);
        panel.add(b, "Center");
        final Panel panel2 = new Panel();
        panel2.setBackground(this.m.c.k);
        panel2.setForeground(this.m.c.j);
        panel2.add(this.a);
        panel.add(panel2, "South");
        this.a.setBackground(this.c());
        n.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(n, gridBagConstraints);
        n2.add(n);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.f.a(aG.a("Cancel"));
        this.f.f();
        final Panel panel3 = new Panel();
        panel3.setLayout(null);
        panel3.setSize(220, 30);
        (this.a = new Label()).setFont(this.m.c.b());
        this.a.setForeground(this.m.c.j);
        this.a.setForeground(Color.black);
        this.a.setBounds(10, 2, 165, 20);
        this.f.setBounds(220 - this.f.getSize().width, 1, this.f.getSize().width, this.f.getSize().height);
        panel3.add(this.f);
        panel3.add(this.a);
        gridBagLayout.setConstraints(panel3, gridBagConstraints);
        n2.add(panel3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.g.a(aG.a("OK"));
        this.g.f();
        gridBagLayout.setConstraints(this.g, gridBagConstraints);
        n2.add(this.g);
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(n2, gridBagConstraints);
        this.add(n2);
        this.b(0);
        this.setSize(new Dimension(300, 220));
        if (c.j) {
            this.i.setVisible(false);
            this.j.setVisible(false);
            this.k.setVisible(false);
            this.l.setVisible(false);
            this.b.show(this.b, "a2");
        }
        this.repaint();
    }
    
    public void a(final Color e) {
        this.e = e;
    }
    
    public Dimension minimumSize() {
        return new Dimension(300, 220);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.i) {
                    this.b.show(this.b, "a1");
                    this.b(0);
                    return true;
                }
                if (event.target == this.j) {
                    this.b.show(this.b, "a1");
                    this.b(1);
                    return true;
                }
                if (event.target == this.k) {
                    this.b.show(this.b, "a1");
                    this.b(2);
                    return true;
                }
                if (event.target == this.l) {
                    this.b.show(this.b, "a2");
                    return true;
                }
                if (event.target == this.g) {
                    this.c.a(this.c());
                    this.c.c();
                    return true;
                }
                if (event.target == this.f) {
                    this.c.c();
                    return true;
                }
                if (event.target == this.f) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a(final float n, final float n2, final float n3) {
        if (!this.k) {
            this.k = true;
            this.b(n, n2, n3);
            this.c(n, n2, n3);
            this.d(n, n2, n3);
            final Color hsbColor = Color.getHSBColor(n, n2, n3);
            this.a.setBackground(hsbColor);
            this.a.setText(this.c.b(this.c()));
            this.a.repaint();
            this.e = hsbColor;
            this.k = false;
        }
    }
    
    private void b(final float n, final float n2, final float n3) {
        int n4 = 0;
        int n5 = 0;
        switch (this.b) {
            case 0: {
                if (n != this.a.a()) {
                    this.a.a(n);
                    this.a.d();
                }
                n4 = 150 - (int)(n2 * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 1: {
                if (n2 != this.a.b()) {
                    this.a.b(n2);
                    this.a.d();
                }
                n4 = (int)(n * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 2: {
                if (n3 != this.a.c()) {
                    this.a.c(n3);
                    this.a.d();
                }
                n4 = (int)(n * 150.0f);
                n5 = 150 - (int)(n2 * 150.0f);
                break;
            }
        }
        this.a.setLocation(n4, n5);
        this.a.repaint();
    }
    
    private void c(final float n, final float n2, final float n3) {
        if (this.b != 0 && n != this.b.a()) {
            this.b.a(n);
            this.b.d();
        }
        float n4 = 0.0f;
        switch (this.b) {
            case 0: {
                n4 = n;
                break;
            }
            case 1: {
                n4 = n2;
                break;
            }
            case 2: {
                n4 = n3;
                break;
            }
        }
        this.a.a(n4);
    }
    
    private void d(final float n, final float n2, final float n3) {
        final int round = Math.round(n * 359.0f);
        final int round2 = Math.round(n2 * 100.0f);
        final int round3 = Math.round(n3 * 100.0f);
        if (Integer.parseInt(this.e.getText()) != round) {
            this.e.setText(new Integer(round).toString());
        }
        if (Integer.parseInt(this.f.getText()) != round2) {
            this.f.setText(new Integer(round2).toString());
        }
        if (Integer.parseInt(this.g.getText()) != round3) {
            this.g.setText(new Integer(round3).toString());
        }
    }
    
    protected Component a() {
        (this.a = new ag(this)).setCursor(Cursor.getPredefinedCursor(1));
        (this.a = new S(this)).setSize(new Dimension(150, 165));
        this.a.setSize(new Dimension(150, 150));
        this.a.e = true;
        this.a.g = 359;
        final Panel panel = new Panel();
        panel.add(this.a);
        panel.add(this.a);
        this.d();
        return panel;
    }
    
    private void d() {
        if (this.a != null) {
            return;
        }
        final float[] a = this.a();
        switch (this.b) {
            case 0: {
                this.a = new Q(0, 150, 150, a[0], 1.0f, 1.0f);
                this.b = new Q(3, 16, 150, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a = new Q(1, 150, 150, 1.0f, a[1], 1.0f);
                this.b = new Q(4, 16, 150, 1.0f, 0.0f, 1.0f);
                break;
            }
            case 2: {
                this.a = new Q(2, 150, 150, 1.0f, 1.0f, a[2]);
                this.b = new Q(5, 16, 150, 1.0f, 1.0f, 0.0f);
                break;
            }
        }
        this.f = Toolkit.getDefaultToolkit().createImage(this.a);
        this.p = Toolkit.getDefaultToolkit().createImage(this.b);
        this.a.b = this.f;
        this.a.a = this.p;
    }
    
    private Component b() {
        final String s = "H";
        final String s2 = "S";
        final String s3 = "b";
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.i = new Checkbox(s, checkboxGroup, true);
        this.j = new Checkbox(s2, checkboxGroup, false);
        this.k = new Checkbox(s3, checkboxGroup, false);
        this.l = new Checkbox("d", checkboxGroup, false);
        final float[] a = this.a();
        this.e = new TextField(String.valueOf((int)(a[0] * 359.0f)), 1);
        this.f = new TextField(String.valueOf((int)(a[1] * 100.0f)), 1);
        this.g = new TextField(String.valueOf((int)(a[2] * 100.0f)), 1);
        this.e.setForeground(Color.black);
        this.e.setBackground(Color.white);
        this.f.setForeground(Color.black);
        this.f.setBackground(Color.white);
        this.g.setForeground(Color.black);
        this.g.setBackground(Color.white);
        this.e.setEditable(false);
        this.f.setEditable(false);
        this.g.setEditable(false);
        this.c();
        final Panel panel = new Panel(new GridLayout(4, 2));
        panel.add(this.i);
        panel.add(this.e);
        panel.add(this.j);
        panel.add(this.f);
        panel.add(this.k);
        panel.add(this.g);
        panel.add(this.l);
        return panel;
    }
    
    public float[] a() {
        final Color c = this.c();
        final float[] array = new float[3];
        Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), array);
        return array;
    }
    
    protected Color c() {
        return this.e;
    }
    
    private void b(final int b) {
        if (this.b == b && this.i) {
            this.i = true;
            return;
        }
        this.k = true;
        this.b = b;
        final float[] a = this.a();
        switch (this.b) {
            case 0: {
                this.a.a(0, a[0], 1.0f, 1.0f);
                this.a.e = true;
                this.a.g = 359;
                this.b.a(3, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a.e = false;
                this.a.g = 100;
                this.a.a(1, a[0], a[1], 1.0f);
                this.b.a(4, a[0], 1.0f, 1.0f);
                break;
            }
            case 2: {
                this.a.e = false;
                this.a.g = 100;
                this.a.a(2, a[0], 1.0f, a[2]);
                this.b.a(5, a[0], 1.0f, 1.0f);
                break;
            }
        }
        this.k = false;
        this.a.d();
        this.b.d();
        this.g();
    }
    
    public void g() {
        if (!this.k) {
            final float[] a = this.a();
            this.a(a[0], a[1], a[2]);
        }
    }
}

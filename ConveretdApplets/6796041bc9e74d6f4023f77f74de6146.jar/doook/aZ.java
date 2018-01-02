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

public class aZ extends Panel
{
    public transient w a;
    public transient w b;
    private Color j;
    private transient Image r;
    private transient Image s;
    private boolean ap;
    private TextField r;
    private TextField s;
    private TextField t;
    private Label a;
    private boolean aq;
    public Point b;
    private I a;
    private L a;
    private ar a;
    private Checkbox p;
    private Checkbox q;
    private Checkbox r;
    private Checkbox s;
    private Panel b;
    private CardLayout a;
    public int aC;
    private aX h;
    private t g;
    private aS b;
    private aS q;
    
    public aZ(final aX h, final t g) {
        this.ap = false;
        this.aq = false;
        this.b = new Point();
        this.aC = 0;
        this.h = h;
        this.g = g;
        this.a = new CardLayout(0, 0);
        this.j = this.h.getBackground();
        this.b = new aS(80, 20);
        this.q = new aS(80, 20);
        this.a = new ar(25);
        this.setBackground(g.a.a);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        final cA ca = new cA();
        final cA ca2 = new cA();
        ca2.setLayout(gridBagLayout);
        ca2.setBackground(this.g.a.a);
        ca2.setForeground(this.g.a.a);
        ca.setLayout(gridBagLayout);
        ca.setBackground(this.g.a.g);
        ca.setForeground(this.g.a.f);
        (this.b = new Panel()).setLayout(this.a);
        this.b.add("a1", this.a());
        this.b.add("a2", new cj(this, h.a, !h.ao));
        ca.add(this.b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 1));
        ca2.setLayout(gridBagLayout);
        final Component b = this.b();
        panel.setBackground(this.g.a.g);
        panel.setForeground(this.g.a.f);
        panel.add(b, "Center");
        final Panel panel2 = new Panel();
        panel2.setBackground(this.g.a.g);
        panel2.setForeground(this.g.a.f);
        panel2.add(this.a);
        panel.add(panel2, "South");
        this.a.setBackground(this.getColorFromModel());
        ca.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ca, gridBagConstraints);
        ca2.add(ca);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ao.e("Cancel"));
        this.b.t();
        final Panel panel3 = new Panel();
        panel3.setLayout(null);
        panel3.setSize(220, 30);
        (this.a = new Label()).setFont(this.g.a.b());
        this.a.setForeground(this.g.a.f);
        this.a.setForeground(Color.black);
        this.a.setBounds(10, 2, 165, 20);
        this.b.setBounds(220 - this.b.getSize().width, 1, this.b.getSize().width, this.b.getSize().height);
        panel3.add(this.b);
        panel3.add(this.a);
        gridBagLayout.setConstraints(panel3, gridBagConstraints);
        ca2.add(panel3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.q.a(ao.e("OK"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        ca2.add(this.q);
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ca2, gridBagConstraints);
        this.add(ca2);
        this.setMode(0);
        this.setSize(new Dimension(300, 220));
        if (h.ao) {
            this.p.setVisible(false);
            this.q.setVisible(false);
            this.r.setVisible(false);
            this.s.setVisible(false);
            this.a.show(this.b, "a2");
        }
        this.repaint();
    }
    
    public void a(final Color j) {
        this.j = j;
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
                if (event.target == this.p) {
                    this.a.show(this.b, "a1");
                    this.setMode(0);
                    return true;
                }
                if (event.target == this.q) {
                    this.a.show(this.b, "a1");
                    this.setMode(1);
                    return true;
                }
                if (event.target == this.r) {
                    this.a.show(this.b, "a1");
                    this.setMode(2);
                    return true;
                }
                if (event.target == this.s) {
                    this.a.show(this.b, "a2");
                    return true;
                }
                if (event.target == this.q) {
                    this.h.a(this.getColorFromModel());
                    this.h.d();
                    return true;
                }
                if (event.target == this.b) {
                    this.h.d();
                    return true;
                }
                if (event.target == this.b) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void a(final float n, final float n2, final float n3) {
        if (!this.aq) {
            this.aq = true;
            this.b(n, n2, n3);
            this.c(n, n2, n3);
            this.d(n, n2, n3);
            final Color hsbColor = Color.getHSBColor(n, n2, n3);
            this.a.setBackground(hsbColor);
            this.a.setText(this.h.a(this.getColorFromModel()));
            this.a.repaint();
            this.j = hsbColor;
            this.aq = false;
        }
    }
    
    private void b(final float n, final float n2, final float n3) {
        int n4 = 0;
        int n5 = 0;
        switch (this.aC) {
            case 0: {
                if (n != this.a.a()) {
                    this.a.a(n);
                    this.a.n();
                }
                n4 = 150 - (int)(n2 * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 1: {
                if (n2 != this.a.b()) {
                    this.a.b(n2);
                    this.a.n();
                }
                n4 = (int)(n * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 2: {
                if (n3 != this.a.c()) {
                    this.a.c(n3);
                    this.a.n();
                }
                n4 = (int)(n * 150.0f);
                n5 = 150 - (int)(n2 * 150.0f);
                break;
            }
        }
        this.b.setLocation(n4, n5);
        this.a.repaint();
    }
    
    private void c(final float n, final float n2, final float n3) {
        if (this.aC != 0 && n != this.b.a()) {
            this.b.a(n);
            this.b.n();
        }
        float n4 = 0.0f;
        switch (this.aC) {
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
        this.a.d(n4);
    }
    
    private void d(final float n, final float n2, final float n3) {
        final int round = Math.round(n * 359.0f);
        final int round2 = Math.round(n2 * 100.0f);
        final int round3 = Math.round(n3 * 100.0f);
        if (Integer.parseInt(this.r.getText()) != round) {
            this.r.setText(new Integer(round).toString());
        }
        if (Integer.parseInt(this.s.getText()) != round2) {
            this.s.setText(new Integer(round2).toString());
        }
        if (Integer.parseInt(this.t.getText()) != round3) {
            this.t.setText(new Integer(round3).toString());
        }
    }
    
    protected Component a() {
        (this.a = new I(this)).setCursor(Cursor.getPredefinedCursor(1));
        (this.a = new L(this)).setSize(new Dimension(150, 165));
        this.a.setSize(new Dimension(150, 150));
        this.a.Y = true;
        this.a.aa = 359;
        final Panel panel = new Panel();
        panel.add(this.a);
        panel.add(this.a);
        this.u();
        return panel;
    }
    
    private void u() {
        if (this.a != null) {
            return;
        }
        final float[] a = this.a();
        switch (this.aC) {
            case 0: {
                this.a = new w(0, 150, 150, a[0], 1.0f, 1.0f);
                this.b = new w(3, 16, 150, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a = new w(1, 150, 150, 1.0f, a[1], 1.0f);
                this.b = new w(4, 16, 150, 1.0f, 0.0f, 1.0f);
                break;
            }
            case 2: {
                this.a = new w(2, 150, 150, 1.0f, 1.0f, a[2]);
                this.b = new w(5, 16, 150, 1.0f, 1.0f, 0.0f);
                break;
            }
        }
        this.r = Toolkit.getDefaultToolkit().createImage(this.a);
        this.s = Toolkit.getDefaultToolkit().createImage(this.b);
        this.a.i = this.r;
        this.a.k = this.s;
    }
    
    private Component b() {
        final String s = "H";
        final String s2 = "S";
        final String s3 = "b";
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.p = new Checkbox(s, checkboxGroup, true);
        this.q = new Checkbox(s2, checkboxGroup, false);
        this.r = new Checkbox(s3, checkboxGroup, false);
        this.s = new Checkbox("d", checkboxGroup, false);
        final float[] a = this.a();
        this.r = new TextField(String.valueOf((int)(a[0] * 359.0f)), 1);
        this.s = new TextField(String.valueOf((int)(a[1] * 100.0f)), 1);
        this.t = new TextField(String.valueOf((int)(a[2] * 100.0f)), 1);
        this.r.setForeground(Color.black);
        this.r.setBackground(Color.white);
        this.s.setForeground(Color.black);
        this.s.setBackground(Color.white);
        this.t.setForeground(Color.black);
        this.t.setBackground(Color.white);
        this.r.setEditable(false);
        this.s.setEditable(false);
        this.t.setEditable(false);
        this.getColorFromModel();
        final Panel panel = new Panel(new GridLayout(4, 2));
        panel.add(this.p);
        panel.add(this.r);
        panel.add(this.q);
        panel.add(this.s);
        panel.add(this.r);
        panel.add(this.t);
        panel.add(this.s);
        return panel;
    }
    
    public float[] a() {
        final Color colorFromModel = this.getColorFromModel();
        final float[] array = new float[3];
        Color.RGBtoHSB(colorFromModel.getRed(), colorFromModel.getGreen(), colorFromModel.getBlue(), array);
        return array;
    }
    
    protected Color getColorFromModel() {
        return this.j;
    }
    
    private void setMode(final int ac) {
        if (this.aC == ac && this.ap) {
            this.ap = true;
            return;
        }
        this.aq = true;
        this.aC = ac;
        final float[] a = this.a();
        switch (this.aC) {
            case 0: {
                this.a.a(0, a[0], 1.0f, 1.0f);
                this.a.Y = true;
                this.a.aa = 359;
                this.b.a(3, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a.Y = false;
                this.a.aa = 100;
                this.a.a(1, a[0], a[1], 1.0f);
                this.b.a(4, a[0], 1.0f, 1.0f);
                break;
            }
            case 2: {
                this.a.Y = false;
                this.a.aa = 100;
                this.a.a(2, a[0], 1.0f, a[2]);
                this.b.a(5, a[0], 1.0f, 1.0f);
                break;
            }
        }
        this.aq = false;
        this.a.n();
        this.b.n();
        this.updateChooser();
    }
    
    public void updateChooser() {
        if (!this.aq) {
            final float[] a = this.a();
            this.a(a[0], a[1], a[2]);
        }
    }
}

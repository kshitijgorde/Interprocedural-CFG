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

public class bq extends Panel
{
    public transient o a;
    public transient o b;
    private Color t;
    private transient Image y;
    private transient Image z;
    private boolean ai;
    private TextField i;
    private TextField j;
    private TextField k;
    private Label a;
    private boolean aj;
    public Point b;
    private bo a;
    private ad a;
    private bf a;
    private Checkbox h;
    private Checkbox i;
    private Checkbox j;
    private Checkbox k;
    private Panel b;
    private CardLayout b;
    public int ay;
    private bp d;
    private as a;
    private ax b;
    private ax f;
    
    public bq(final bp d, final as a) {
        this.ai = false;
        this.aj = false;
        this.b = new Point();
        this.ay = 0;
        this.d = d;
        this.a = a;
        this.b = new CardLayout(0, 0);
        this.t = this.d.getBackground();
        this.b = new ax(80, 20);
        this.f = new ax(80, 20);
        this.a = new bf(25);
        this.setBackground(a.b.a);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        final aC ac = new aC();
        final aC ac2 = new aC();
        ac2.setLayout(gridBagLayout);
        ac2.setBackground(this.a.b.a);
        ac2.setForeground(this.a.b.a);
        ac.setLayout(gridBagLayout);
        ac.setBackground(this.a.b.g);
        ac.setForeground(this.a.b.f);
        (this.b = new Panel()).setLayout(this.b);
        this.b.add("a1", this.a());
        this.b.add("a2", new aK(this, d.a, !d.ah));
        ac.add(this.b);
        final Panel panel = new Panel();
        panel.setLayout(new BorderLayout(2, 1));
        ac2.setLayout(gridBagLayout);
        final Component b = this.b();
        panel.setBackground(this.a.b.g);
        panel.setForeground(this.a.b.f);
        panel.add(b, "Center");
        final Panel panel2 = new Panel();
        panel2.setBackground(this.a.b.g);
        panel2.setForeground(this.a.b.f);
        panel2.add(this.a);
        panel.add(panel2, "South");
        this.a.setBackground(this.getColorFromModel());
        ac.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ac, gridBagConstraints);
        ac2.add(ac);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.b.a(ar.b("Cancel"));
        this.b.p();
        final Panel panel3 = new Panel();
        panel3.setLayout(null);
        panel3.setSize(220, 30);
        (this.a = new Label()).setFont(this.a.b.b());
        this.a.setForeground(this.a.b.f);
        this.a.setForeground(Color.black);
        this.a.setBounds(10, 2, 165, 20);
        this.b.setBounds(220 - this.b.getSize().width, 1, this.b.getSize().width, this.b.getSize().height);
        panel3.add(this.b);
        panel3.add(this.a);
        gridBagLayout.setConstraints(panel3, gridBagConstraints);
        ac2.add(panel3);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.f.a(ar.b("OK"));
        this.f.p();
        gridBagLayout.setConstraints(this.f, gridBagConstraints);
        ac2.add(this.f);
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(ac2, gridBagConstraints);
        this.add(ac2);
        this.setMode(0);
        this.setSize(new Dimension(300, 220));
        if (d.ah) {
            this.h.setVisible(false);
            this.i.setVisible(false);
            this.j.setVisible(false);
            this.k.setVisible(false);
            this.b.show(this.b, "a2");
        }
        this.repaint();
    }
    
    public void b(final Color t) {
        this.t = t;
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
                if (event.target == this.h) {
                    this.b.show(this.b, "a1");
                    this.setMode(0);
                    return true;
                }
                if (event.target == this.i) {
                    this.b.show(this.b, "a1");
                    this.setMode(1);
                    return true;
                }
                if (event.target == this.j) {
                    this.b.show(this.b, "a1");
                    this.setMode(2);
                    return true;
                }
                if (event.target == this.k) {
                    this.b.show(this.b, "a2");
                    return true;
                }
                if (event.target == this.f) {
                    this.d.b(this.getColorFromModel());
                    this.d.g();
                    return true;
                }
                if (event.target == this.b) {
                    this.d.g();
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
        if (!this.aj) {
            this.aj = true;
            this.b(n, n2, n3);
            this.c(n, n2, n3);
            this.d(n, n2, n3);
            final Color hsbColor = Color.getHSBColor(n, n2, n3);
            this.a.setBackground(hsbColor);
            this.a.setText(this.d.b(this.getColorFromModel()));
            this.a.repaint();
            this.t = hsbColor;
            this.aj = false;
        }
    }
    
    private void b(final float n, final float n2, final float n3) {
        int n4 = 0;
        int n5 = 0;
        switch (this.ay) {
            case 0: {
                if (n != this.a.a()) {
                    this.a.a(n);
                    this.a.e();
                }
                n4 = 150 - (int)(n2 * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 1: {
                if (n2 != this.a.b()) {
                    this.a.b(n2);
                    this.a.e();
                }
                n4 = (int)(n * 150.0f);
                n5 = 150 - (int)(n3 * 150.0f);
                break;
            }
            case 2: {
                if (n3 != this.a.c()) {
                    this.a.c(n3);
                    this.a.e();
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
        if (this.ay != 0 && n != this.b.a()) {
            this.b.a(n);
            this.b.e();
        }
        float n4 = 0.0f;
        switch (this.ay) {
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
        if (Integer.parseInt(this.i.getText()) != round) {
            this.i.setText(new Integer(round).toString());
        }
        if (Integer.parseInt(this.j.getText()) != round2) {
            this.j.setText(new Integer(round2).toString());
        }
        if (Integer.parseInt(this.k.getText()) != round3) {
            this.k.setText(new Integer(round3).toString());
        }
    }
    
    protected Component a() {
        (this.a = new bo(this)).setCursor(Cursor.getPredefinedCursor(1));
        (this.a = new ad(this)).setSize(new Dimension(150, 165));
        this.a.setSize(new Dimension(150, 150));
        this.a.s = true;
        this.a.D = 359;
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
        switch (this.ay) {
            case 0: {
                this.a = new o(0, 150, 150, a[0], 1.0f, 1.0f);
                this.b = new o(3, 16, 150, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a = new o(1, 150, 150, 1.0f, a[1], 1.0f);
                this.b = new o(4, 16, 150, 1.0f, 0.0f, 1.0f);
                break;
            }
            case 2: {
                this.a = new o(2, 150, 150, 1.0f, 1.0f, a[2]);
                this.b = new o(5, 16, 150, 1.0f, 1.0f, 0.0f);
                break;
            }
        }
        this.y = Toolkit.getDefaultToolkit().createImage(this.a);
        this.z = Toolkit.getDefaultToolkit().createImage(this.b);
        this.a.w = this.y;
        this.a.h = this.z;
    }
    
    private Component b() {
        final String s = "H";
        final String s2 = "S";
        final String s3 = "b";
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.h = new Checkbox(s, checkboxGroup, true);
        this.i = new Checkbox(s2, checkboxGroup, false);
        this.j = new Checkbox(s3, checkboxGroup, false);
        this.k = new Checkbox("d", checkboxGroup, false);
        final float[] a = this.a();
        this.i = new TextField(String.valueOf((int)(a[0] * 359.0f)), 1);
        this.j = new TextField(String.valueOf((int)(a[1] * 100.0f)), 1);
        this.k = new TextField(String.valueOf((int)(a[2] * 100.0f)), 1);
        this.i.setForeground(Color.black);
        this.i.setBackground(Color.white);
        this.j.setForeground(Color.black);
        this.j.setBackground(Color.white);
        this.k.setForeground(Color.black);
        this.k.setBackground(Color.white);
        this.i.setEditable(false);
        this.j.setEditable(false);
        this.k.setEditable(false);
        this.getColorFromModel();
        final Panel panel = new Panel(new GridLayout(4, 2));
        panel.add(this.h);
        panel.add(this.i);
        panel.add(this.i);
        panel.add(this.j);
        panel.add(this.j);
        panel.add(this.k);
        panel.add(this.k);
        return panel;
    }
    
    public float[] a() {
        final Color colorFromModel = this.getColorFromModel();
        final float[] array = new float[3];
        Color.RGBtoHSB(colorFromModel.getRed(), colorFromModel.getGreen(), colorFromModel.getBlue(), array);
        return array;
    }
    
    protected Color getColorFromModel() {
        return this.t;
    }
    
    private void setMode(final int ay) {
        if (this.ay == ay && this.ai) {
            this.ai = true;
            return;
        }
        this.aj = true;
        this.ay = ay;
        final float[] a = this.a();
        switch (this.ay) {
            case 0: {
                this.a.a(0, a[0], 1.0f, 1.0f);
                this.a.s = true;
                this.a.D = 359;
                this.b.a(3, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a.s = false;
                this.a.D = 100;
                this.a.a(1, a[0], a[1], 1.0f);
                this.b.a(4, a[0], 1.0f, 1.0f);
                break;
            }
            case 2: {
                this.a.s = false;
                this.a.D = 100;
                this.a.a(2, a[0], 1.0f, a[2]);
                this.b.a(5, a[0], 1.0f, 1.0f);
                break;
            }
        }
        this.aj = false;
        this.a.e();
        this.b.e();
        this.updateChooser();
    }
    
    public void updateChooser() {
        if (!this.aj) {
            final float[] a = this.a();
            this.a(a[0], a[1], a[2]);
        }
    }
}

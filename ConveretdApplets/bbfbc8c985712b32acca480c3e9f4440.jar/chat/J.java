// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.Container;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.BorderLayout;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.Checkbox;
import java.awt.Point;
import java.awt.TextField;
import java.awt.Image;
import java.awt.Color;
import java.awt.Panel;

public final class J extends Panel
{
    public bW a;
    private bW b;
    Color a;
    private transient Image a;
    private transient Image b;
    private boolean b;
    private TextField a;
    private TextField b;
    private TextField c;
    private boolean c;
    public Point a;
    private cB a;
    private bq a;
    private bs a;
    private Checkbox a;
    private Checkbox b;
    private Checkbox c;
    private Checkbox d;
    private Checkbox e;
    private Panel a;
    private CardLayout a;
    public int a;
    v a;
    private cs a;
    private cr a;
    private cr b;
    private A a;
    boolean a;
    
    public J(final v a, final cs a2) {
        this.b = false;
        this.c = false;
        this.a = new Point();
        this.a = 0;
        this.a = a;
        this.a = a2;
        this.a = new CardLayout(0, 0);
        this.a = this.a.getBackground();
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new bs();
        if (this.a.c) {
            final bs a3 = this.a;
            final boolean d = this.a.d;
            this.a = d;
            a3.a = d;
        }
        this.setBackground(a2.a.a);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        final bF bf = new bF();
        final bF bf2;
        (bf2 = new bF()).setLayout(gridBagLayout);
        bf2.setBackground(this.a.a.a);
        bf2.setForeground(this.a.a.a);
        bf.setLayout(gridBagLayout);
        bf.setBackground(this.a.a.h);
        bf.setForeground(this.a.a.g);
        (this.a = new A()).setFont(bk.c);
        this.a.setForeground(this.a.a.g);
        this.a.a = false;
        (this.a = new Panel()).setLayout(this.a);
        (this.a = new cB(this)).setCursor(Cursor.getPredefinedCursor(1));
        (this.a = new bq(this)).setSize(new Dimension(150, 165));
        this.a.setSize(new Dimension(150, 150));
        this.a.a = true;
        this.a.a = 359;
        final Panel panel;
        (panel = new Panel()).add(this.a);
        panel.add(this.a);
        if (this.a == null) {
            final float[] a4 = this.a();
            switch (this.a) {
                case 0: {
                    this.a = new bW(0, 150, a4[0], 1.0f, 1.0f);
                    this.b = new bW(3, 16, 0.0f, 1.0f, 1.0f);
                    break;
                }
                case 1: {
                    this.a = new bW(1, 150, 1.0f, a4[1], 1.0f);
                    this.b = new bW(4, 16, 1.0f, 0.0f, 1.0f);
                    break;
                }
                case 2: {
                    this.a = new bW(2, 150, 1.0f, 1.0f, a4[2]);
                    this.b = new bW(5, 16, 1.0f, 1.0f, 0.0f);
                    break;
                }
            }
            this.a = Toolkit.getDefaultToolkit().createImage(this.a);
            this.b = Toolkit.getDefaultToolkit().createImage(this.b);
            this.a.a = this.a;
            this.a.a = this.b;
        }
        this.a.add("a1", panel);
        this.a.add("a2", new cD(this));
        if (this.a.a != null) {
            this.a.add("a3", new cD(this, 1, this.a.a));
        }
        bf.add(this.a);
        final Panel panel2;
        (panel2 = new Panel()).setLayout(new BorderLayout(2, 1));
        bf2.setLayout(gridBagLayout);
        final String s = "H";
        final String s2 = "S";
        final String s3 = "b";
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.a = new Checkbox(s, checkboxGroup, true);
        this.b = new Checkbox(s2, checkboxGroup, false);
        this.c = new Checkbox(s3, checkboxGroup, false);
        this.d = new Checkbox("d", checkboxGroup, false);
        this.e = new Checkbox("c", checkboxGroup, false);
        final float[] a5 = this.a();
        this.a = new TextField(String.valueOf((int)(a5[0] * 359.0f)), 1);
        this.b = new TextField(String.valueOf((int)(a5[1] * 100.0f)), 1);
        this.c = new TextField(String.valueOf((int)(a5[2] * 100.0f)), 1);
        this.a.setForeground(Color.black);
        this.a.setBackground(Color.white);
        this.b.setForeground(Color.black);
        this.b.setBackground(Color.white);
        this.c.setForeground(Color.black);
        this.c.setBackground(Color.white);
        this.a.setEditable(false);
        this.b.setEditable(false);
        this.c.setEditable(false);
        final Panel panel3;
        (panel3 = new Panel(new GridLayout(4, 2))).add(this.a);
        panel3.add(this.a);
        panel3.add(this.b);
        panel3.add(this.b);
        panel3.add(this.c);
        panel3.add(this.c);
        panel3.add(this.d);
        panel3.add(this.e);
        final Panel panel4 = panel3;
        panel2.setBackground(this.a.a.h);
        panel2.setForeground(this.a.a.g);
        panel2.add(panel4, "Center");
        final Panel panel5;
        (panel5 = new Panel()).setBackground(this.a.a.h);
        panel5.setForeground(this.a.a.g);
        panel5.add(this.a);
        panel2.add(panel5, "South");
        this.a.setBackground(this.a);
        bf.add(panel2);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        bf2.add(bf);
        gridBagConstraints.insets = new Insets(0, 10, 0, 0);
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        bf2.add(this.a);
        final FontMetrics fontMetrics = this.getFontMetrics(bk.c);
        this.a.resize(120, fontMetrics.getHeight() + fontMetrics.getDescent());
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.a.a(aS.a(95));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        bf2.add(this.a);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        this.b.a(aS.a(2));
        this.b.d();
        new g(this.b);
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        bf2.add(this.b);
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf2, gridBagConstraints);
        this.add(bf2);
        this.a(0);
        this.setSize(new Dimension(300, 220));
        if (!this.a.b) {
            this.e.setVisible(false);
        }
        if (!this.a.a) {
            this.a.setVisible(false);
            this.b.setVisible(false);
            this.c.setVisible(false);
            this.e.setVisible(false);
            this.d.setVisible(false);
            this.a.show(this.a, "a3");
        }
        else {
            this.a.a("");
        }
        this.repaint();
    }
    
    public final void a(final Color color, final boolean b) {
        this.a = ((color == null) ? Color.white : color);
        if (color == null) {
            final bs a = this.a;
            final boolean b2 = color == null;
            this.a = b2;
            a.a = b2;
        }
        if (b && color != null) {
            String s = null;
            if (this.a.a != null && this.a.a.containsKey(color)) {
                s = this.a.a.get(color);
            }
            if (s != null) {
                this.a.a(s);
                return;
            }
            this.a.a("");
        }
    }
    
    public final Dimension minimumSize() {
        return new Dimension(300, 220);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target != this.e) {
                    this.a.a("");
                }
                if (event.target == this.a) {
                    this.a.show(this.a, "a1");
                    this.a(0);
                    return true;
                }
                if (event.target == this.b) {
                    this.a.show(this.a, "a1");
                    this.a(1);
                    return true;
                }
                if (event.target == this.c) {
                    this.a.show(this.a, "a1");
                    this.a(2);
                    return true;
                }
                if (event.target == this.d) {
                    this.a.show(this.a, "a2");
                    return true;
                }
                if (event.target == this.e) {
                    this.a.show(this.a, "a3");
                    this.a(this.a, true);
                    return true;
                }
                if (event.target == this.b) {
                    if (this.a && this.a.c) {
                        this.a.a((Color)null);
                    }
                    else {
                        this.a.a(this.a);
                    }
                    this.a.a();
                    return true;
                }
                if (event.target == this.a) {
                    this.a.a();
                    return true;
                }
                if (event.target == this.a) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void a(final float n, final float b, final float c) {
        if (!this.c) {
            this.c = true;
            int n2 = 0;
            int n3 = 0;
            switch (this.a) {
                case 0: {
                    if (n != this.a.a) {
                        this.a.a = n;
                        this.a.a();
                    }
                    n2 = 150 - (int)(b * 150.0f);
                    n3 = 150 - (int)(c * 150.0f);
                    break;
                }
                case 1: {
                    if (b != this.a.b) {
                        this.a.b = b;
                        this.a.a();
                    }
                    n2 = (int)(n * 150.0f);
                    n3 = 150 - (int)(c * 150.0f);
                    break;
                }
                case 2: {
                    if (c != this.a.c) {
                        this.a.c = c;
                        this.a.a();
                    }
                    n2 = (int)(n * 150.0f);
                    n3 = 150 - (int)(b * 150.0f);
                    break;
                }
            }
            this.a.setLocation(n2, n3);
            this.a.repaint();
            if (this.a != 0 && n != this.b.a) {
                this.b.a = n;
                this.b.a();
            }
            float n4 = 0.0f;
            switch (this.a) {
                case 0: {
                    n4 = n;
                    break;
                }
                case 1: {
                    n4 = b;
                    break;
                }
                case 2: {
                    n4 = c;
                    break;
                }
            }
            final bq a = this.a;
            final float a2 = n4;
            final bq bq = a;
            a.a = a2;
            bq.repaint();
            final int round = Math.round(n * 359.0f);
            final int round2 = Math.round(b * 100.0f);
            final int round3 = Math.round(c * 100.0f);
            if (Integer.parseInt(this.a.getText()) != round) {
                this.a.setText(new Integer(round).toString());
            }
            if (Integer.parseInt(this.b.getText()) != round2) {
                this.b.setText(new Integer(round2).toString());
            }
            if (Integer.parseInt(this.c.getText()) != round3) {
                this.c.setText(new Integer(round3).toString());
            }
            final Color color = new Color(chat.y.a(n, b, c));
            if (this.a && !color.equals(Color.white) && this.a.c) {
                final bs a3 = this.a;
                final boolean b2 = false;
                this.a = b2;
                a3.a = b2;
            }
            this.a.setBackground(color);
            this.a = color;
            this.c = false;
        }
    }
    
    public final float[] a() {
        final Color a = this.a;
        final float[] array = new float[3];
        Color.RGBtoHSB(a.getRed(), a.getGreen(), a.getBlue(), array);
        return array;
    }
    
    private void a(final int a) {
        if (this.a == a && this.b) {
            this.b = true;
            return;
        }
        this.c = true;
        this.a = a;
        final float[] a2 = this.a();
        switch (this.a) {
            case 0: {
                this.a.a(0, a2[0], 1.0f, 1.0f);
                this.a.a = true;
                this.a.a = 359;
                this.b.a(3, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.a.a = false;
                this.a.a = 100;
                this.a.a(1, a2[0], a2[1], 1.0f);
                this.b.a(4, a2[0], 1.0f, 1.0f);
                break;
            }
            case 2: {
                this.a.a = false;
                this.a.a = 100;
                this.a.a(2, a2[0], 1.0f, a2[2]);
                this.b.a(5, a2[0], 1.0f, 1.0f);
                break;
            }
        }
        this.c = false;
        this.a.a();
        this.b.a();
        this.a();
    }
    
    public final void a() {
        if (!this.c) {
            final float[] a = this.a();
            this.a(a[0], a[1], a[2]);
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.Event;
import java.awt.Insets;
import java.awt.GridLayout;
import java.awt.CheckboxGroup;
import java.awt.BorderLayout;
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
import java.awt.Color;
import java.awt.Panel;

public final class ax extends Panel implements az
{
    public transient aA q;
    private transient aA w;
    private Color q;
    private boolean q;
    private TextField q;
    private TextField w;
    private TextField e;
    private boolean w;
    public Point q;
    private ay q;
    private aC q;
    private j q;
    private Checkbox q;
    private Checkbox w;
    private Checkbox e;
    private Checkbox r;
    private Checkbox t;
    private Panel q;
    private CardLayout q;
    public int q;
    aw q;
    private g q;
    private g w;
    
    public ax(final aw q) {
        this.q = false;
        this.w = false;
        this.q = new Point();
        this.q = 0;
        this.q = q;
        this.q = new CardLayout(0, 0);
        this.q = this.q.getBackground();
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = new j(this.q.q());
        this.q.q = false;
        this.setBackground(cf.w.q);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);
        final s s = new s();
        final s s2;
        (s2 = new s()).setLayout(gridBagLayout);
        s2.setBackground(cf.w.q);
        s2.setForeground(cf.w.q);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        (this.q = new Panel()).setLayout(this.q);
        (this.q = new ay(this)).setCursor(Cursor.getPredefinedCursor(1));
        (this.q = new aC(this)).setSize(new Dimension(150, 165));
        this.q.setSize(new Dimension(150, 150));
        this.q.q = true;
        this.q.q = 359;
        final Panel panel;
        (panel = new Panel()).add(this.q);
        panel.add(this.q);
        if (this.q == null) {
            final float[] q2 = this.q();
            switch (this.q) {
                case 0: {
                    this.q = new aA(0, 150, 150, q2[0], 1.0f, 1.0f);
                    this.w = new aA(3, 16, 150, 0.0f, 1.0f, 1.0f);
                    break;
                }
                case 1: {
                    this.q = new aA(1, 150, 150, 1.0f, q2[1], 1.0f);
                    this.w = new aA(4, 16, 150, 1.0f, 0.0f, 1.0f);
                    break;
                }
                case 2: {
                    this.q = new aA(2, 150, 150, 1.0f, 1.0f, q2[2]);
                    this.w = new aA(5, 16, 150, 1.0f, 1.0f, 0.0f);
                    break;
                }
            }
            this.q.q = this.q;
            this.q.q(this.w);
        }
        final Panel panel2 = panel;
        final au au = new au(this);
        final Color i = cf.w.i;
        final aB ab = new aB(this);
        if (a.q()) {
            this.q.add("a1", panel2);
            this.q.add("a2", au);
        }
        this.q.add("a3", ab);
        s.add(this.q);
        final Panel panel3;
        (panel3 = new Panel()).setLayout(new BorderLayout(2, 1));
        s2.setLayout(gridBagLayout);
        final String s3 = "H";
        final String s4 = "S";
        final String s5 = "b";
        final String s6 = "p";
        final String s7 = "d";
        final CheckboxGroup checkboxGroup = new CheckboxGroup();
        this.q = new Checkbox(s3, checkboxGroup, true);
        this.w = new Checkbox(s4, checkboxGroup, false);
        this.e = new Checkbox(s5, checkboxGroup, false);
        this.r = new Checkbox(s6, checkboxGroup, false);
        this.t = new Checkbox(s7, checkboxGroup, false);
        final float[] q3 = this.q();
        this.q = new TextField(String.valueOf((int)(q3[0] * 359.0f)), 1);
        this.w = new TextField(String.valueOf((int)(q3[1] * 100.0f)), 1);
        this.e = new TextField(String.valueOf((int)(q3[2] * 100.0f)), 1);
        this.q.setForeground(Color.black);
        this.q.setBackground(Color.white);
        this.w.setForeground(Color.black);
        this.w.setBackground(Color.white);
        this.e.setForeground(Color.black);
        this.e.setBackground(Color.white);
        this.q.setEditable(false);
        this.w.setEditable(false);
        this.e.setEditable(false);
        final Panel panel4 = new Panel(new GridLayout(4, 2));
        if (a.q()) {
            panel4.add(this.q);
        }
        panel4.add(this.q);
        if (a.q()) {
            panel4.add(this.w);
        }
        panel4.add(this.w);
        if (a.q()) {
            panel4.add(this.e);
        }
        panel4.add(this.e);
        if (a.q()) {
            panel4.add(this.r);
            panel4.add(this.t);
        }
        final Panel panel5 = panel4;
        panel3.setBackground(cf.w.i);
        panel3.setForeground(cf.w.u);
        panel3.add(panel5, "Center");
        final Panel panel6;
        (panel6 = new Panel()).setBackground(cf.w.i);
        panel6.setForeground(cf.w.u);
        panel6.add(this.q);
        panel3.add(panel6, "South");
        this.q.q(this.q);
        s.add(panel3);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s, gridBagConstraints);
        s2.add(s);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q("Cancel");
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        s2.add(this.q);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 0.0;
        this.w.q("OK");
        this.w.t();
        gridBagLayout.setConstraints(this.w, gridBagConstraints);
        s2.add(this.w);
        gridBagConstraints.insets = new Insets(1, 1, 1, 1);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(s2, gridBagConstraints);
        this.add(s2);
        this.q(0);
        this.setSize(new Dimension(300, 220));
        this.repaint();
    }
    
    public final void q(final Color q) {
        this.q = q;
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
                if (event.target == this.q) {
                    this.q.show(this.q, "a1");
                    this.q(0);
                    return true;
                }
                if (event.target == this.w) {
                    this.q.show(this.q, "a1");
                    this.q(1);
                    return true;
                }
                if (event.target == this.e) {
                    this.q.show(this.q, "a1");
                    this.q(2);
                    return true;
                }
                if (event.target == this.r) {
                    this.q.show(this.q, "a2");
                    return true;
                }
                if (event.target == this.t) {
                    this.q.show(this.q, "a3");
                    return true;
                }
                if (event.target == this.w) {
                    this.q.q(this.q);
                    this.q.q();
                    return true;
                }
                if (event.target == this.q) {
                    this.q.q();
                    return true;
                }
                if (event.target == this.q) {
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void q(final float n, final float n2, final float n3) {
        if (!this.w) {
            this.w = true;
            int n4 = 0;
            int n5 = 0;
            switch (this.q) {
                case 0: {
                    if (n != this.q.q()) {
                        this.q.q(n);
                        this.q.q = this.q;
                    }
                    n4 = 150 - (int)(n2 * 150.0f);
                    n5 = 150 - (int)(n3 * 150.0f);
                    break;
                }
                case 1: {
                    if (n2 != this.q.w()) {
                        this.q.w(n2);
                        this.q.q = this.q;
                    }
                    n4 = (int)(n * 150.0f);
                    n5 = 150 - (int)(n3 * 150.0f);
                    break;
                }
                case 2: {
                    if (n3 != this.q.e()) {
                        this.q.e(n3);
                        this.q.q = this.q;
                    }
                    n4 = (int)(n * 150.0f);
                    n5 = 150 - (int)(n2 * 150.0f);
                    break;
                }
            }
            this.q.setLocation(n4, n5);
            this.q.repaint();
            if (this.q != 0 && n != this.w.q()) {
                this.w.q(n);
                this.q.q(this.w);
            }
            float n6 = 0.0f;
            switch (this.q) {
                case 0: {
                    n6 = n;
                    break;
                }
                case 1: {
                    n6 = n2;
                    break;
                }
                case 2: {
                    n6 = n3;
                    break;
                }
            }
            this.q.q(n6);
            final int round = Math.round(n * 359.0f);
            final int round2 = Math.round(n2 * 100.0f);
            final int round3 = Math.round(n3 * 100.0f);
            if (Integer.parseInt(this.q.getText()) != round) {
                this.q.setText(new Integer(round).toString());
            }
            if (Integer.parseInt(this.w.getText()) != round2) {
                this.w.setText(new Integer(round2).toString());
            }
            if (Integer.parseInt(this.e.getText()) != round3) {
                this.e.setText(new Integer(round3).toString());
            }
            final Color hsbColor = Color.getHSBColor(n, n2, n3);
            this.q.setBackground(hsbColor);
            this.q = hsbColor;
            this.w = false;
        }
    }
    
    public final float[] q() {
        final Color q = this.q;
        final float[] array = new float[3];
        Color.RGBtoHSB(q.getRed(), q.getGreen(), q.getBlue(), array);
        return array;
    }
    
    public final Color q() {
        return this.q;
    }
    
    private void q(final int q) {
        if (this.q == q && this.q) {
            this.q = true;
            return;
        }
        this.w = true;
        this.q = q;
        final float[] q2 = this.q();
        switch (this.q) {
            case 0: {
                this.q.q(0, q2[0], 1.0f, 1.0f);
                this.q.q = true;
                this.q.q = 359;
                this.w.q(3, 0.0f, 1.0f, 1.0f);
                break;
            }
            case 1: {
                this.q.q = false;
                this.q.q = 100;
                this.q.q(1, q2[0], q2[1], 1.0f);
                this.w.q(4, q2[0], 1.0f, 1.0f);
                break;
            }
            case 2: {
                this.q.q = false;
                this.q.q = 100;
                this.q.q(2, q2[0], 1.0f, q2[2]);
                this.w.q(5, q2[0], 1.0f, 1.0f);
                break;
            }
        }
        this.w = false;
        this.q.q = this.q;
        this.q.q(this.w);
        this.q();
    }
    
    public final void q() {
        if (!this.w) {
            final float[] q = this.q();
            this.q(q[0], q[1], q[2]);
        }
    }
}

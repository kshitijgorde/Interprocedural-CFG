// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import javax.swing.JPopupMenu;
import java.awt.event.ActionEvent;
import VT_6_1_0_11.an;
import java.awt.event.KeyListener;
import javax.swing.Icon;
import VT_6_1_0_11.h;
import com.hw.client.util.c;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import VT_6_1_0_11.U;

public final class l extends U implements ActionListener
{
    private g l;
    protected e a;
    private a m;
    protected j b;
    private JMenuItem n;
    private JMenuItem o;
    private JPanel p;
    private JComponent q;
    private JComponent r;
    private JPanel s;
    protected JButton k;
    
    public l(final g l) {
        super(l);
        this.l = l;
        this.a("/images/common/Wlogo-W_only-32_26.png");
        this.b(this.g);
        this.g().a();
        this.b = new j(l);
        this.a = new e(l);
        this.m = new a(l);
        this.n();
        this.revalidate();
    }
    
    protected final JComponent a() {
        if (this.p == null) {
            final JPanel p;
            (p = new JPanel(new GridBagLayout())).setOpaque(false);
            final GridBagConstraints gridBagConstraints = new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 17, 1, new Insets(0, 0, 0, 5), 0, 0);
            final JPanel panel = p;
            if (this.r == null) {
                final JPanel r;
                (r = new JPanel(new GridBagLayout())).setOpaque(false);
                r.add(this.b.b(), new GridBagConstraints(0, 0, 1, 1, 1.0, 1.0, 18, 1, new Insets(0, 0, 5, 0), 0, 0));
                r.add(this.b.a(), new GridBagConstraints(0, 1, 1, 1, 1.0, 0.0, 15, 1, new Insets(5, 0, 0, 0), 0, 0));
                this.r = r;
            }
            panel.add(this.r, gridBagConstraints);
            final GridBagConstraints gridBagConstraints2 = new GridBagConstraints(1, 0, 1, 1, 0.0, 1.0, 15, 1, new Insets(0, 5, 0, 0), 0, 0);
            final JPanel panel2 = p;
            if (this.q == null) {
                final JPanel q;
                (q = new JPanel(new GridBagLayout())).setOpaque(true);
                q.setBackground(Color.white);
                q.setMinimumSize(new Dimension(180, 100));
                q.setMaximumSize(new Dimension(180, 4000));
                q.add(this.a.a(), new GridBagConstraints(0, 0, 1, 1, 0.0, 1.0, 12, 1, new Insets(0, 0, 0, 0), 0, 0));
                final GridBagConstraints gridBagConstraints3 = new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, 15, 1, new Insets(0, 0, 0, 0), 0, 0);
                final JPanel panel3 = q;
                if (this.s == null) {
                    final JPanel s;
                    (s = new JPanel(new GridBagLayout())).setOpaque(false);
                    com.hw.client.util.c.a(s, new Dimension(180, 50));
                    s.add(this.m.a(), new GridBagConstraints(0, 0, 1, 2, 0.0, 0.0, 10, 1, new Insets(0, 3, 0, 0), 0, 0));
                    s.add(this.m.b(), new GridBagConstraints(2, 0, 1, 2, 0.0, 0.0, 10, 1, new Insets(0, 0, 0, 3), 0, 0));
                    s.add(this.m.c(), new GridBagConstraints(1, 0, 1, 1, 1.0, 0.0, 10, 1, new Insets(0, 5, 0, 5), 0, 0));
                    s.add(this.m.m(), new GridBagConstraints(1, 1, 1, 1, 1.0, 0.0, 10, 1, new Insets(0, 5, 0, 5), 0, 0));
                    s.setBorder(VT_6_1_0_11.h.g());
                    this.s = s;
                }
                panel3.add(this.s, gridBagConstraints3);
                this.q = q;
            }
            panel2.add(this.q, gridBagConstraints2);
            this.p = p;
        }
        return this.p;
    }
    
    protected final void b() {
        (this.k = VT_6_1_0_11.h.a("/images/common/btn_archives.png", this.l.e("btn_archives"), this.l.e("btn_archives_tooltip"), "ACTION_SHOW_ARCHIVE")).setRolloverIcon(com.hw.client.util.c.a("/images/common/btn_archives_rollover.png"));
        this.k.setPressedIcon(com.hw.client.util.c.a("/images/common/btn_archives_pressed.png"));
        this.k.addActionListener(this);
    }
    
    public final a c() {
        return this.m;
    }
    
    public final void d() {
        this.addKeyListener(this.m);
        this.b.a(this.m);
        this.a.a(this.m);
        this.l.addKeyListener(this.m);
    }
    
    public final void a(final an an) {
        final byte c;
        if ((c = an.c()) == 7) {
            this.m.a(an);
            return;
        }
        if (c == 3) {
            this.b.a(an);
            return;
        }
        if (c == 4) {
            this.a.a(an);
            return;
        }
        if (c == 5) {
            this.m.a(an);
            return;
        }
        if (c == 8) {
            this.l.d(an.d());
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.k) {
            this.l.x();
            return;
        }
        if (actionEvent.getSource() == this.n) {
            this.l.b(true);
            return;
        }
        if (actionEvent.getSource() == this.o) {
            this.l.b(false);
        }
    }
    
    public final JButton i() {
        (this.j = super.i()).setToolTipText(this.c("btn_options_tooltip_vdirect"));
        return this.j;
    }
    
    public final JPopupMenu m() {
        final JPopupMenu m = super.m();
        if (this.l.a("manage_archives")) {
            this.n = new JMenuItem(this.l.e("archiving_start"));
            this.o = new JMenuItem(this.l.e("archiving_stop"));
            this.n.addActionListener(this);
            this.o.addActionListener(this);
            m.add(this.n);
            m.add(this.o);
        }
        return m;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Frame;
import java.awt.TextField;

public abstract class j extends bC
{
    protected cr a;
    protected cr b;
    protected TextField a;
    protected TextField b;
    protected TextField c;
    protected cx a;
    
    protected final void a() {
        new bD(null, aS.a(1), aS.a(457) + aS.a(10), this.a).setVisible(true);
    }
    
    protected final void b() {
        new bD(null, aS.a(1), aS.a(458) + aS.a(10), this.a).setVisible(true);
    }
    
    protected final void c() {
        new bD(null, aS.a(1), aS.a(623) + aS.a(10), this.a).setVisible(true);
    }
    
    protected final void d() {
        new bD(null, aS.a(1), aS.a(25) + aS.a(10), this.a).setVisible(true);
    }
    
    public j(final Frame frame, final cx cx, final String title) {
        this(frame, cx, false);
        this.setTitle(title);
    }
    
    public j(final Frame frame, final cx a, final boolean b) {
        super(frame, aS.a(118), true);
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new TextField(15);
        this.b = new TextField(15);
        this.c = new TextField(15);
        this.a = a;
        this.setBackground(a.a.a);
        final bi bi = new bi(aS.a(453), (byte)0);
        final bi bi2 = new bi(aS.a(454), (byte)0);
        final bi bi3 = new bi(aS.a(455), (byte)0);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 17;
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        panel.setBackground(a.a.h);
        panel.setForeground(a.a.g);
        bi.setFont(bk.d);
        bi2.setFont(bk.d);
        bi3.setFont(bk.d);
        this.c.setEchoCharacter('*');
        this.a.setEchoCharacter('*');
        this.b.setEchoCharacter('*');
        if (b) {
            final A a2;
            (a2 = new A(aS.a(456))).setFont(bk.b);
            gridBagConstraints.gridwidth = 0;
            gridBagLayout.setConstraints(a2, gridBagConstraints);
            panel.add(a2);
        }
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        panel.add(bi);
        gridBagConstraints.gridwidth = 0;
        final i i = new i(this.c);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        panel.add(i);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        panel.add(bi2);
        gridBagConstraints.gridwidth = 0;
        final i j = new i(this.a);
        gridBagLayout.setConstraints(j, gridBagConstraints);
        panel.add(j);
        gridBagConstraints.gridwidth = -1;
        gridBagLayout.setConstraints(bi3, gridBagConstraints);
        panel.add(bi3);
        gridBagConstraints.gridwidth = 0;
        final i k = new i(this.b);
        gridBagLayout.setConstraints(k, gridBagConstraints);
        panel.add(k);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.anchor = 13;
        if (!b) {
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridwidth = -1;
            this.b.a(aS.a(3));
            this.b.d();
            gridBagLayout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        this.pack();
        this.c.requestFocus();
    }
}

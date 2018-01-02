// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.TextField;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Checkbox;
import java.awt.Frame;

public final class u extends C
{
    private i a;
    private i b;
    private bl a;
    private Frame a;
    private e a;
    private y a;
    private Checkbox a;
    private W a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == aZ.a) {
                    this.a.c();
                    return true;
                }
                if (event.key == 27) {
                    this.b.c();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.a) {
                    if (this.a.a() == 0) {
                        return true;
                    }
                    int n = 0;
                    final int a = this.a.a();
                    for (int i = 0; i < a; ++i) {
                        final an an;
                        if ((an = (an)this.a.a(i)) != null && an.a(125)) {
                            ++n;
                        }
                    }
                    if (n == 0) {
                        new ad(this.a, ak.a(1), ak.a(653), this.a).setVisible(true);
                        return true;
                    }
                    final int a2 = this.a.a();
                    final m m;
                    (m = new m(132866, n)).e = -1;
                    m.d = -1;
                    m.f = this.a.g;
                    int n2 = 0;
                    for (int j = 0; j < a; ++j) {
                        final an an2;
                        if ((an2 = (an)this.a.a(j)).h != 0) {
                            m.a(n2, 0, a2);
                            m.a(n2, 1, an2.g);
                            ++n2;
                        }
                    }
                    this.a.m(m);
                    this.dispose();
                }
                else if (event.target == this.b) {
                    this.dispose();
                }
                else if (event.target == this.a) {
                    this.a(this.a.getState());
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final void dispose() {
        this.a.d();
        this.a.a();
        super.dispose();
    }
    
    private void a(final boolean b) {
        final u u;
        try {
            synchronized (this.a.c) {
                for (int i = 0; i < u.a.c.a(); ++i) {
                    final au au;
                    if ((au = (au)u.a.c.a(i)).g != -999 && au.g != u.a.g) {
                        aH ah;
                        if ((ah = (aH)u.a(au.g)) == null) {
                            ah = new aH(au);
                        }
                        if (b || au.b == u.a.b) {
                            final u u2;
                            (u2 = u).a.a(ah);
                            synchronized (u2.a) {
                                if ((u2.a.a(24) || !ah.a(23)) && (u2.a.a(52) || !ah.a(18) || ah.g == u2.a.g)) {
                                    final int a;
                                    if ((a = u2.a.a((a)ah)) == -1) {
                                        u2.a.c(ah);
                                    }
                                    else {
                                        u2.a.a(ah, a);
                                    }
                                    int n;
                                    if (ah.a(25) && ah.a(18)) {
                                        n = 2;
                                    }
                                    else if (ah.a(24) && ah.a(23)) {
                                        n = 1;
                                    }
                                    else {
                                        n = 0;
                                    }
                                    if (ah.a) {
                                        u2.a.a(ah, Color.red, Color.pink, new Color(15658734), n);
                                    }
                                    else if (ah.c != 0) {
                                        u2.a.a(ah, new Color(ah.c), new Color(10079487), new Color((ah.f == 0) ? 15658734 : ah.f), n);
                                    }
                                    else {
                                        u2.a.a(ah, Color.black, Color.white, new Color((ah.f == 0) ? 15658734 : ah.f), n);
                                    }
                                    if (ah.b) {
                                        u2.a.a(ah, true);
                                    }
                                    else {
                                        u2.a.a(ah, false);
                                    }
                                }
                                continue;
                            }
                        }
                        u.a(ah);
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        y a3;
        for (int a2 = (a3 = u.a.a()).a(), j = 0; j < a2; ++j) {
            final an an;
            final an a4;
            if ((an = (an)a3.a(j)) != null && (a4 = u.a(an.g)) != null) {
                u.a((aH)a4);
            }
        }
    }
    
    private boolean a(final aH ah) {
        return this.a.a((a)ah);
    }
    
    private an a(final int n) {
        return (an)this.a.b(n);
    }
    
    public u(final Frame a, final bl a2, final e a3) {
        super(a, ak.a(652), true);
        this.a = a;
        this.a = new i(80, 20);
        this.b = new i(80, 20);
        this.a = new W(1000);
        this.a = a3;
        this.setBackground(a2.a.h);
        this.setForeground(a2.a.g);
        this.a = a2;
        this.a = new Checkbox(ak.a(274));
        (this.a = new y()).setSize(250, 250);
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final Panel panel = new Panel();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        panel.setLayout(gridBagLayout);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        gridBagConstraints.insets = new Insets(2, 2, 2, 2);
        gridBagConstraints.anchor = 13;
        new TextField(20);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.gridwidth = 0;
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        panel.add(g);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(ak.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(ak.a(2));
        this.a.d();
        final f f = new f(this.a);
        gridBagLayout.setConstraints(f, gridBagConstraints);
        this.add(f);
        final w w = new w(null, "icon");
        final w w2 = new w(ak.a(5), "name");
        final bp bp = new bp("select");
        w.c(28);
        w.b(0);
        w2.a = true;
        this.a.a();
        this.a.b(bp);
        bp.a(true);
        this.a.b(w);
        this.a.b(w2);
        this.a.a(w2);
        this.a.b(26);
        this.a.b(this.a.a(21));
        this.pack();
        this.a(this.a.getState());
    }
}

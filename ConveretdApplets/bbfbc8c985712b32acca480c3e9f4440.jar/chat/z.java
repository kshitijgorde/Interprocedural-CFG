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

public final class z extends bC
{
    private cr a;
    private cr b;
    private cx a;
    private Frame a;
    private f a;
    private K a;
    private Checkbox a;
    private av a;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
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
                        final U u;
                        if ((u = (U)this.a.a(i)) != null && u.a(125)) {
                            ++n;
                        }
                    }
                    if (n == 0) {
                        new bD(this.a, aS.a(1), aS.a(653), this.a).setVisible(true);
                        return true;
                    }
                    final int a2 = this.a.a();
                    final r r;
                    (r = new r(132866, n)).e = -1;
                    r.d = -1;
                    r.f = this.a.i;
                    int n2 = 0;
                    for (int j = 0; j < a; ++j) {
                        final U u2;
                        if ((u2 = (U)this.a.a(j)).j != 0) {
                            r.a(n2, 0, a2);
                            r.a(n2, 1, u2.i);
                            ++n2;
                        }
                    }
                    this.a.o(r);
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
        this.a.e();
        this.a.a();
        super.dispose();
    }
    
    private void a(final boolean b) {
        final z z;
        try {
            synchronized (this.a.c) {
                for (int i = 0; i < z.a.c.a(); ++i) {
                    final aZ az;
                    if ((az = (aZ)z.a.c.a(i)).i != -999 && az.i != z.a.i) {
                        bx bx;
                        if ((bx = (bx)z.a(az.i)) == null) {
                            bx = new bx(az);
                        }
                        if (b || az.b == z.a.b) {
                            final z z2;
                            (z2 = z).a.a(bx);
                            synchronized (z2.a) {
                                if ((z2.a.a(24) || !bx.a(23)) && (z2.a.a(52) || !bx.a(18) || bx.i == z2.a.i)) {
                                    final int a;
                                    if ((a = z2.a.a((a)bx)) == -1) {
                                        z2.a.c(bx);
                                    }
                                    else {
                                        z2.a.a(bx, a);
                                    }
                                    int n;
                                    if (bx.a(25) && bx.a(18)) {
                                        n = 2;
                                    }
                                    else if (bx.a(24) && bx.a(23)) {
                                        n = 1;
                                    }
                                    else {
                                        n = 0;
                                    }
                                    if (bx.c) {
                                        z2.a.a(bx, Color.red, Color.pink, new Color(15658734), n);
                                    }
                                    else if (bx.c != 0) {
                                        z2.a.a(bx, new Color(bx.c), new Color(10079487), new Color((bx.f == 0) ? 15658734 : bx.f), n);
                                    }
                                    else {
                                        z2.a.a(bx, Color.black, Color.white, new Color((bx.f == 0) ? 15658734 : bx.f), n);
                                    }
                                    if (bx.d) {
                                        z2.a.b(bx, true);
                                    }
                                    else {
                                        z2.a.b(bx, false);
                                    }
                                }
                                continue;
                            }
                        }
                        z.a(bx);
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        K a3;
        for (int a2 = (a3 = z.a.a()).a(), j = 0; j < a2; ++j) {
            final U u;
            final U a4;
            if ((u = (U)a3.a(j)) != null && (a4 = z.a(u.i)) != null) {
                z.a((bx)a4);
            }
        }
    }
    
    private boolean a(final bx bx) {
        return this.a.a((a)bx);
    }
    
    private U a(final int n) {
        return (U)this.a.b(n);
    }
    
    public z(final Frame a, final cx a2, final f a3) {
        super(a, aS.a(652), true);
        this.a = a;
        this.a = new cr(80, 20);
        this.b = new cr(80, 20);
        this.a = new av(1000);
        this.a = a3;
        this.setBackground(a2.a.h);
        this.setForeground(a2.a.g);
        this.a = a2;
        this.a = new Checkbox(aS.a(274));
        (this.a = new K()).setSize(250, 250);
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
        final i i = new i(this.a);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        panel.add(i);
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        this.b.a(aS.a(3));
        this.b.d();
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        this.add(this.b);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.a.a(aS.a(2));
        this.a.d();
        final g g = new g(this.a);
        gridBagLayout.setConstraints(g, gridBagConstraints);
        this.add(g);
        final I j = new I(null, "icon");
        final I k = new I(aS.a(5), "name");
        final cC cc = new cC("select");
        j.c(28);
        j.b(0);
        k.b = true;
        this.a.a();
        this.a.b(cc);
        cc.a(true);
        this.a.b(j);
        this.a.b(k);
        this.a.a(k);
        this.a.b(26);
        this.a.c(this.a.a(21));
        this.pack();
        this.a(this.a.getState());
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;

public final class cw extends bC
{
    private cr a;
    private ai a;
    public cs a;
    public final K a;
    private cr b;
    private cr c;
    private cr d;
    private cr e;
    private boolean a;
    private boolean b;
    private boolean c;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.a) {
                    super.dispose();
                    return true;
                }
                final aZ az;
                if ((az = (aZ)this.a.a()) == null && event.target != this.d) {
                    break;
                }
                if (event.target == this.b) {
                    final r r;
                    (r = new r(67074, 1)).a(0, 0, this.a.i);
                    r.e = az.i;
                    this.a.o(r);
                    return true;
                }
                if (event.target == this.d) {
                    final r r2;
                    (r2 = new r(66305, 1)).d = -1;
                    r2.e = ((az != null) ? az.i : this.a.i);
                    r2.a(0, 0, this.a.i);
                    r2.a(0, 0, "*IPX");
                    this.a.o(r2);
                    return true;
                }
                if (event.target == this.c) {
                    final r r3;
                    (r3 = new r(66305, 1)).d = -1;
                    r3.e = az.i;
                    r3.a(0, 0, this.a.i);
                    r3.a(0, 0, "kick=");
                    this.a.o(r3);
                    return true;
                }
                if (event.target == this.e) {
                    final r r4;
                    (r4 = new r(66305, 1)).d = -1;
                    r4.e = az.i;
                    r4.a(0, 0, this.a.i);
                    r4.a(0, 0, "bkick=");
                    this.a.o(r4);
                    return true;
                }
                break;
            }
            case 701: {
                if (this.a) {
                    b(this.c);
                }
                if (this.b) {
                    b(this.e);
                }
                b(this.b);
                return true;
            }
            case 702: {
                a(this.c);
                a(this.b);
                a(this.e);
                if (!this.c) {
                    a(this.d);
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    private static void a(final cr cr) {
        if (cr == null) {
            return;
        }
        cr.b();
    }
    
    private static void b(final cr cr) {
        if (cr == null) {
            return;
        }
        cr.a();
    }
    
    public final void dispose() {
        super.dispose();
    }
    
    public cw(final cs a, final ai a2, final int n) {
        super(a.a.a(), aS.a(379), true);
        this.setModal(false);
        this.a = new cr(80, 20);
        this.setBackground(a.a.a);
        this.a = a;
        (this.a = new K()).setSize(250, 250);
        final bF bf = new bF();
        final k k = new k();
        this.b = new cr(aS.a(413), 80, 20);
        this.c = new cr(aS.a(171), 80, 20);
        this.d = new cr(aS.a(380), 80, 20);
        this.e = new cr(aS.a(383), 80, 20);
        final FontMetrics fontMetrics;
        int stringWidth = (fontMetrics = this.b.getFontMetrics(this.b.getFont())).stringWidth(this.b.a);
        final int stringWidth2 = fontMetrics.stringWidth(this.c.a);
        final int stringWidth3 = fontMetrics.stringWidth(this.d.a);
        if (stringWidth2 > stringWidth) {
            stringWidth = stringWidth2;
        }
        if (stringWidth3 > stringWidth) {
            stringWidth = stringWidth3;
        }
        final int stringWidth4;
        if ((stringWidth4 = fontMetrics.stringWidth(this.e.a)) > stringWidth) {
            stringWidth = stringWidth4;
        }
        stringWidth += 5;
        this.b.resize(stringWidth, 20);
        this.d.resize(stringWidth, 20);
        this.c.resize(stringWidth, 20);
        this.e.resize(stringWidth, 20);
        this.a = this.a.a(44);
        this.b = this.a.a(49);
        this.c = this.a.a(11);
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        new bi();
        final bi bi = new bi("IP : " + a2.a, (byte)0);
        this.a = a2;
        this.setLayout(gridBagLayout);
        bf.setLayout(gridBagLayout);
        bf.setBackground(a.a.h);
        bf.setForeground(a.a.g);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        bi.setFont(bk.a);
        gridBagLayout.setConstraints(bi, gridBagConstraints);
        bf.add(bi);
        final bi bi2 = new bi("Total : " + n, (byte)0);
        gridBagConstraints.gridwidth = 0;
        bi2.setFont(bk.a);
        gridBagLayout.setConstraints(bi2, gridBagConstraints);
        bf.add(bi2);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(k, gridBagConstraints);
        bf.add(k);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final i i = new i(this.a);
        gridBagLayout.setConstraints(i, gridBagConstraints);
        bf.add(i);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.fill = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagLayout.setConstraints(this.b, gridBagConstraints);
        bf.add(this.b);
        gridBagLayout.setConstraints(this.c, gridBagConstraints);
        bf.add(this.c);
        gridBagLayout.setConstraints(this.d, gridBagConstraints);
        bf.add(this.d);
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(this.e, gridBagConstraints);
        bf.add(this.e);
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(bf, gridBagConstraints);
        this.add(bf);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weighty = 0.0;
        this.a.a(aS.a(3));
        this.a.d();
        gridBagLayout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        final I j = new I(null, "icon");
        final I l = new I(aS.a(5), "name");
        j.c(28);
        j.b(0);
        l.b = true;
        this.a.a();
        this.a.b(j);
        this.a.b(l);
        this.a.a(l);
        this.a.b(26);
        this.pack();
        this.setVisible(true);
    }
}

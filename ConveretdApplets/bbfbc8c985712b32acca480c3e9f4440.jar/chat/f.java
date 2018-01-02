// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.util.Enumeration;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Image;
import java.util.Hashtable;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Panel;

public final class f extends Panel implements aB
{
    private static final Color a;
    private K a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private Canvas d;
    private cx a;
    private Hashtable a;
    private e a;
    
    private static void a(final Canvas canvas) {
        if (canvas instanceof cr) {
            ((cr)canvas).b();
        }
        else {
            ((bG)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void b(final Canvas canvas) {
        if (canvas instanceof cr) {
            ((cr)canvas).a();
            return;
        }
        ((bG)canvas).setEnabled(true);
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        aB a;
        if (!this.a.a.d() || s == null) {
            ((cr)(a = new cr(28, 28))).a(this.a.a(s3, false, 20));
        }
        else {
            final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                ((cr)(a = new cr(28, 28))).a(this.a.a(s3, false, 20));
            }
            else {
                a = bG.a(a2, a3, a4);
            }
        }
        return (cr)a;
    }
    
    public final String a(final Object o) {
        if (o instanceof cr || o instanceof bG) {
            final bx bx = (bx)this.a.a();
            if (o == this.b) {
                return aS.a(658);
            }
            if (bx == null) {
                return aS.a(283);
            }
            if (o == this.a && bx.e) {
                return bm.a(aS.a(282), new String[] { bx.d });
            }
            if (o == this.d) {
                return bm.a(aS.a(279), new String[] { bx.d });
            }
            if (o == this.c) {
                return bm.a(aS.a(275), new String[] { bx.d });
            }
        }
        return null;
    }
    
    public final int a() {
        return this.a.a;
    }
    
    public final void show() {
        super.show();
        this.a.requestFocus();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                final bx bx = (bx)this.a.a();
                if (((this.a.a(43) && event.target == this.a) || event.target == this.a) && bx != null && bx.e) {
                    this.a.a(null, (ai)bx);
                    return true;
                }
                if (event.target == this.b) {
                    new z(this.a, this.a, this).setVisible(true);
                }
                if (bx == null) {
                    break;
                }
                if (event.target == this.d) {
                    final r r;
                    (r = new r(67074, 1)).a(0, 0, this.a.i);
                    r.e = bx.i;
                    this.a.o(r);
                    return true;
                }
                if (event.target == this.c) {
                    new bf(this.a, bx);
                    break;
                }
                break;
            }
            case 701: {
                b(this.d);
                final bx bx2 = (bx)this.a.a();
                final ai ai = (ai)event.arg;
                if (bx2.e) {
                    b(this.a);
                }
                else {
                    a(this.a);
                }
                if (ai.a(34) || bx2.i == this.a.i) {
                    if (f.a != null) {
                        a(this.c);
                    }
                }
                else if (this.a.a(44) && this.c != null) {
                    b(this.c);
                }
                return true;
            }
            case 702: {
                a(this.a);
                a(this.d);
                if (this.c != null) {
                    a(this.c);
                }
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean a(final int n) {
        final Integer n2 = new Integer(n);
        if (this.a.containsKey(n2)) {
            final bx bx = this.a.get(new Integer(n));
            String s = bm.a(aS.a(53), new String[] { this.a.a.a });
            if (this.a.c.b(bx.i) != null) {
                s = bm.a(aS.a(53), new String[] { aS.a(655) });
            }
            final aU au;
            (au = new aU(s, bx, (b)this.a.b.b(bx.a))).k = bx.e;
            final Y y;
            if (bx.e != 0 && (y = (Y)this.a.q.b(bx.e)) != null) {
                au.b = y.a;
            }
            if (bx.b != null && !bx.a(0)) {
                au.c = bx.b;
                au.f = true;
            }
            this.a.a(au);
            this.a.remove(n2);
            return this.a.a((a)bx);
        }
        return false;
    }
    
    public final bx a(final int n) {
        final bx bx;
        if (this.a.containsKey(new Integer(n)) && (bx = this.a.get(new Integer(n))) != null) {
            return bx;
        }
        return null;
    }
    
    public final K a() {
        return this.a;
    }
    
    private void a(final bx bx) {
        final int n = 15658734;
        int n2;
        if (bx.a(25) && bx.a(18)) {
            n2 = 2;
        }
        else if (bx.a(24) && bx.a(23)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (bx.c) {
            this.a.a(bx, Color.red, Color.pink, new Color(n), n2);
        }
        else if (bx.c != 0) {
            this.a.a(bx, new Color(bx.c), f.a, new Color((bx.f == 0) ? n : bx.f), n2);
        }
        else {
            this.a.a(bx, Color.black, Color.white, new Color((bx.f == 0) ? n : bx.f), n2);
        }
        if (bx.d) {
            this.a.b(bx, true);
            return;
        }
        this.a.b(bx, false);
    }
    
    public final void a(final int n, final boolean b) {
        if (n == 0) {
            return;
        }
        final aZ az;
        if ((az = (aZ)this.a.c.b(n)) == null) {
            return;
        }
        final bx bx;
        (bx = new bx(az.i, az.d)).e = true;
        bx.a = az.a;
        bx.a = az.a;
        bx.b = az.b;
        bx.c = az.c;
        bx.f = az.f;
        bx.e = az.e;
        bx.d = az.d;
        bx.a = az.a;
        bx.a = az.a;
        bx.b = az.b;
        bx.a(az.a);
        bx.a();
        final bx bx2 = bx;
        U u = null;
        if (bx2.d != null) {
            u = this.a.get(new Integer(bx2.i));
        }
        if (u == null) {
            this.a.put(new Integer(bx2.i), bx2);
            if (bx2.e) {
                this.a.c(bx2);
                this.a(bx2);
            }
            this.a();
        }
        else if (u != null) {
            this.a.remove(new Integer(u.i));
            this.a.put(new Integer(bx2.i), bx2);
            this.a.a((a)u);
            if (bx2.e) {
                this.a.c(bx2);
            }
            this.a(bx2);
        }
        if (b) {
            final aU au;
            (au = new aU(bm.a(aS.a(51), new String[] { aS.a(655) }), bx, (b)this.a.b.b(bx.a))).k = bx.e;
            final Y y;
            if (bx.e != 0 && (y = (Y)this.a.q.b(bx.e)) != null) {
                au.b = y.a;
            }
            if (bx.b != null && !bx.a(0)) {
                au.c = bx.b;
                au.f = true;
            }
            this.a.a(au);
        }
    }
    
    private String a() {
        String s = "";
        final Enumeration<bx> elements;
        if ((elements = this.a.elements()).hasMoreElements()) {
            s = Integer.toString(elements.nextElement().i);
        }
        while (elements.hasMoreElements()) {
            s = s + "," + Integer.toString(elements.nextElement().i);
        }
        return s;
    }
    
    public f(final e a, final cx a2) {
        this.a = a;
        (this.a = new K()).setSize(150, 2);
        this.a = a2;
        this.a = new Hashtable(30);
        this.setBackground(this.a.a.h);
        this.setForeground(this.a.a.g);
        final String c = this.a.a.c();
        this.b = this.a(c, "addbuddy", "addBuddyIcon.GIF");
        if (this.a.a(43)) {
            this.a = this.a(c, "private", "whisperIcon.GIF");
        }
        if (this.a.a(44)) {
            this.c = this.a(c, "kick", "kickUserIcon.GIF");
        }
        this.d = this.a(c, "profile", "userInfoIcon.GIF");
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(2, 1, 2, 1);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.anchor = 17;
        if (a2.a(70)) {
            layout.setConstraints(this.b, gridBagConstraints);
            this.add(this.b);
        }
        layout.setConstraints(this.d, gridBagConstraints);
        this.add(this.d);
        if (!this.a.a(44)) {
            gridBagConstraints.gridwidth = 0;
        }
        if (this.a.a(43)) {
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        if (this.a.a(44)) {
            gridBagConstraints.gridwidth = 0;
            layout.setConstraints(this.c, gridBagConstraints);
            this.add(this.c);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final i i = new i(this.a);
        layout.setConstraints(i, gridBagConstraints);
        this.add(i);
        final I j = new I(null, "icon");
        final I k = new I(aS.a(5), "name");
        j.c(28);
        j.b(0);
        k.b = true;
        this.a.a();
        this.a.b(j);
        this.a.b(k);
        this.a.a(k);
        this.a.b(26);
    }
    
    static {
        a = new Color(10079487);
    }
}

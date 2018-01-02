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

public final class e extends Panel implements ab
{
    private static final Color a;
    private y a;
    private Canvas a;
    private Canvas b;
    private Canvas c;
    private bl a;
    private Hashtable a;
    private d a;
    
    private static void a(final Canvas canvas) {
        if (canvas instanceof i) {
            ((i)canvas).b();
        }
        else {
            ((aN)canvas).setEnabled(false);
        }
        canvas.enable(true);
    }
    
    private static void b(final Canvas canvas) {
        if (canvas instanceof i) {
            ((i)canvas).a();
            return;
        }
        ((aN)canvas).setEnabled(true);
    }
    
    private final Canvas a(final String s, final String s2, final String s3) {
        ab a;
        if (!this.a.a.d() || s == null) {
            ((i)(a = new i(28, 28))).a(this.a.a(s3, false, 20));
        }
        else {
            final Image a2 = this.a.a(s + s2 + "_button_up.gif", true);
            final Image a3 = this.a.a(s + s2 + "_button_dn.gif", true);
            final Image a4 = this.a.a(s + s2 + "_button_disabled.gif", true);
            if (a2 == null || a3 == null || a4 == null) {
                ((i)(a = new i(28, 28))).a(this.a.a(s3, false, 20));
            }
            else {
                a = aN.a(a2, a3, a4);
            }
        }
        return (i)a;
    }
    
    public final String a(final Object o) {
        if (o instanceof i || o instanceof aN) {
            final aH ah = (aH)this.a.a();
            if (o == this.b) {
                return ak.a(658);
            }
            if (ah == null) {
                return ak.a(283);
            }
            if (o == this.a && ah.d) {
                return ak.a(ak.a(282), new String[] { ah.c });
            }
            if (o == this.c) {
                return ak.a(ak.a(279), new String[] { ah.c });
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
                final aH ah = (aH)this.a.a();
                if (((this.a.a(43) && event.target == this.a) || event.target == this.a) && ah != null && ah.d) {
                    this.a.a(null, ah);
                    return true;
                }
                if (event.target == this.b) {
                    new u(this.a, this.a, this).setVisible(true);
                }
                if (ah != null && event.target == this.c) {
                    final m m;
                    (m = new m(67074, 1)).a(0, 0, this.a.g);
                    m.e = ah.g;
                    this.a.m(m);
                    return true;
                }
                break;
            }
            case 701: {
                b(this.c);
                if (((aH)this.a.a()).d) {
                    b(this.a);
                }
                else {
                    a(this.a);
                }
                return true;
            }
            case 702: {
                a(this.a);
                a(this.c);
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public final boolean a(final int n) {
        final Integer n2 = new Integer(n);
        if (this.a.containsKey(n2)) {
            final aH ah = this.a.get(new Integer(n));
            String s = ak.a(ak.a(53), new String[] { this.a.a.a });
            if (this.a.c.b(ah.g) != null) {
                s = ak.a(ak.a(53), new String[] { ak.a(655) });
            }
            final ao ao;
            (ao = new ao(s, ah, (b)this.a.b.b(ah.a))).k = ah.e;
            final F f;
            if (ah.e != 0 && (f = (F)this.a.q.b(ah.e)) != null) {
                ao.b = f.a;
            }
            if (ah.b != null && !ah.a(0)) {
                ao.c = ah.b;
                ao.e = true;
            }
            this.a.a(ao);
            this.a.remove(n2);
            return this.a.a((a)ah);
        }
        return false;
    }
    
    public final aH a(final int n) {
        final aH ah;
        if (this.a.containsKey(new Integer(n)) && (ah = this.a.get(new Integer(n))) != null) {
            return ah;
        }
        return null;
    }
    
    public final y a() {
        return this.a;
    }
    
    private void a(final aH ah) {
        final int n = 15658734;
        int n2;
        if (ah.a(25) && ah.a(18)) {
            n2 = 2;
        }
        else if (ah.a(24) && ah.a(23)) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        if (ah.a) {
            this.a.a(ah, Color.red, Color.pink, new Color(n), n2);
        }
        else if (ah.c != 0) {
            this.a.a(ah, new Color(ah.c), e.a, new Color((ah.f == 0) ? n : ah.f), n2);
        }
        else {
            this.a.a(ah, Color.black, Color.white, new Color((ah.f == 0) ? n : ah.f), n2);
        }
        if (ah.b) {
            this.a.a(ah, true);
            return;
        }
        this.a.a(ah, false);
    }
    
    public final void a(final int n, final boolean b) {
        if (n == 0) {
            return;
        }
        final au au;
        if ((au = (au)this.a.c.b(n)) == null) {
            return;
        }
        final aH ah;
        (ah = new aH(au.g, au.c)).d = true;
        ah.a = au.a;
        ah.a = au.a;
        ah.b = au.b;
        ah.c = au.c;
        ah.f = au.f;
        ah.e = au.e;
        ah.d = au.d;
        ah.a = au.a;
        ah.a = au.a;
        ah.b = au.b;
        ah.a(au.a);
        ah.a();
        final aH ah2 = ah;
        an an = null;
        if (ah2.c != null) {
            an = this.a.get(new Integer(ah2.g));
        }
        if (an == null) {
            this.a.put(new Integer(ah2.g), ah2);
            if (ah2.d) {
                this.a.c(ah2);
                this.a(ah2);
            }
            this.a();
        }
        else if (an != null) {
            this.a.remove(new Integer(an.g));
            this.a.put(new Integer(ah2.g), ah2);
            this.a.a((a)an);
            if (ah2.d) {
                this.a.c(ah2);
            }
            this.a(ah2);
        }
        if (b) {
            final ao ao;
            (ao = new ao(ak.a(ak.a(51), new String[] { ak.a(655) }), ah, (b)this.a.b.b(ah.a))).k = ah.e;
            final F f;
            if (ah.e != 0 && (f = (F)this.a.q.b(ah.e)) != null) {
                ao.b = f.a;
            }
            if (ah.b != null && !ah.a(0)) {
                ao.c = ah.b;
                ao.e = true;
            }
            this.a.a(ao);
        }
    }
    
    private String a() {
        String s = "";
        final Enumeration<aH> elements;
        if ((elements = this.a.elements()).hasMoreElements()) {
            s = Integer.toString(elements.nextElement().g);
        }
        while (elements.hasMoreElements()) {
            s = s + "," + Integer.toString(elements.nextElement().g);
        }
        return s;
    }
    
    public e(final d a, final bl a2) {
        this.a = a;
        (this.a = new y()).setSize(150, 2);
        this.a = a2;
        this.a = new Hashtable(30);
        this.setBackground(this.a.a.h);
        this.setForeground(this.a.a.g);
        final String b = this.a.a.b();
        this.b = this.a(b, "addbuddy", "addBuddyIcon.GIF");
        if (this.a.a(43)) {
            this.a = this.a(b, "private", "whisperIcon.GIF");
        }
        this.c = this.a(b, "profile", "userInfoIcon.GIF");
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
        layout.setConstraints(this.c, gridBagConstraints);
        this.add(this.c);
        if (!this.a.a(44)) {
            gridBagConstraints.gridwidth = 0;
        }
        if (this.a.a(43)) {
            layout.setConstraints(this.a, gridBagConstraints);
            this.add(this.a);
        }
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.fill = 1;
        final g g = new g(this.a);
        layout.setConstraints(g, gridBagConstraints);
        this.add(g);
        final w w = new w(null, "icon");
        final w w2 = new w(ak.a(5), "name");
        w.c(28);
        w.b(0);
        w2.a = true;
        this.a.a();
        this.a.b(w);
        this.a.b(w2);
        this.a.a(w2);
        this.a.b(26);
    }
    
    static {
        a = new Color(10079487);
    }
}

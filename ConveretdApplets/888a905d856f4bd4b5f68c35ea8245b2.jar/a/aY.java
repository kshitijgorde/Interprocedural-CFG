// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Choice;
import java.awt.TextArea;

public final class aY extends G
{
    public static final String q;
    private static final String w;
    private TextArea q;
    private Choice q;
    private dS e;
    private Choice w;
    private P q;
    
    public static String q(final int n, final M m) {
        if (n <= 0) {
            return aY.q;
        }
        final cu cu;
        if ((cu = (cu)m.w(n)) == null) {
            return aY.w;
        }
        return cu.a;
    }
    
    public static int q(final String s, final M m) {
        if (aY.q.equals(s)) {
            return -1;
        }
        for (int i = 0; i < m.q; ++i) {
            final cu cu = (cu)m.q(i);
            if (s.equalsIgnoreCase(cu.a)) {
                return cu.s;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public final bp q() {
        return new D(-999, "");
    }
    
    public final void q(final bp bp) {
        final D d = (D)bp;
        this.q.setText(d.a);
        this.q.select(d.q);
        this.e.q(d.g);
        this.w.select(q(d.w, this.q.e));
        this.q.q(d.e);
    }
    
    public final boolean q(final bp bp) {
        final D d = (D)bp;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a full host name or IP address (e.g., \"123.234.56.78\" or \"www.host.com\")."), super.q).setVisible(true);
            return false;
        }
        d.a = text;
        d.q = this.q.getSelectedIndex();
        d.o(this.e.getBackground().getRGB());
        d.r = this.q.a;
        d.w = q(this.w.getSelectedItem(), this.q.e);
        d.t = this.w.getSelectedItem();
        if (this.q.q() < 0) {
            return false;
        }
        d.e = this.q.q();
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Note:"), this.q, 0);
        dk.q(be.w("Priority:"), this.q, this.e, 0);
        dk.q(be.w("For master:"), this.w, 0);
        dk.q(be.w("Expires:"), this.q, 0);
    }
    
    public final void q() {
        if (super.w) {
            final dI di;
            (di = new dI(4198544, this.q())).w = -1;
            di.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final D d = (D)this.q(i);
                di.q(i, d.w());
                di.q(i, 0, d.s);
                di.q(i, 1, d.q);
                di.q(i, 2, d.g);
                di.q(i, 3, d.w);
                di.q(i, 4, d.e);
                di.q(i, 5, d.q);
                di.q(i, 0, d.a);
                di.q(i, 1, d.r);
            }
            super.q.o(di);
            super.w = false;
        }
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.w.q; ++i) {
                this.e((bp)((D)super.q.w.q(i)).clone());
            }
        }
        finally {}
    }
    
    public aY(final cT ct) {
        super(ct, be.w("Notes"), be.w("Note"));
        this.q = new TextArea("", 5, 30, 1);
        (this.q = new dR()).add(D.q);
        this.q.add(D.w);
        this.q.add(D.e);
        this.e = new dS();
        (this.w = new dR()).addItem(aY.q);
        for (int i = 0; i < ct.e.q; ++i) {
            final cu cu;
            if (!(cu = (cu)ct.e.q(i)).a.equalsIgnoreCase("Guest")) {
                this.w.add(cu.a);
            }
        }
        this.q = new P(true, true);
        super.q.w(22);
        this.w.w(400);
        final aX ax = new aX(be.w("User"), "user");
        final aX ax2 = new aX(be.w("For user"), "foruser");
        final aX ax3 = new aX(be.w("Priority"), "priority");
        final aX ax4 = new aX(be.w("For period"), "ttl");
        this.q.w(ax);
        this.q.w(ax2);
        this.q.w(ax3);
        this.q.w(ax4);
        this.q.q(true);
        ax.w(true);
        ax3.w(true);
        ax2.w(true);
        ax4.w(true);
        super.w.w(240);
        ax.w(80);
        ax2.w(80);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
    
    static {
        q = be.w("All");
        w = be.w("Master was removed");
    }
}

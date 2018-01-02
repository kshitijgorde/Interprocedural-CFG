// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Choice;
import java.awt.TextArea;

public final class bJ extends bs
{
    public static final String q;
    private static final String w;
    private TextArea q;
    private Choice q;
    private j e;
    private Choice w;
    private S q;
    
    public static String q(final int n, final dW dw) {
        if (n <= 0) {
            return bJ.q;
        }
        final bT bt;
        if ((bt = (bT)dw.w(n)) == null) {
            return bJ.w;
        }
        return bt.getName();
    }
    
    public static int q(final String s, final dW dw) {
        if (bJ.q.equals(s)) {
            return -1;
        }
        for (int i = 0; i < dw.q(); ++i) {
            final bT bt = (bT)dw.q(i);
            if (s.equalsIgnoreCase(bt.getName())) {
                return bt.q();
            }
        }
        return Integer.MAX_VALUE;
    }
    
    public final bZ q() {
        return new co(-999, "");
    }
    
    public final void q(final bZ bz) {
        final co co = (co)bz;
        this.q.setText(co.getName());
        this.q.select(co.q);
        this.e.q(co.t());
        this.w.select(q(co.w, this.q.b));
        this.q.q(co.e);
    }
    
    public final boolean q(final bZ bz) {
        final co co = (co)bz;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a full host name or IP address (e.g., \"123.234.56.78\" or \"www.host.com\")."), super.q).setVisible(true);
            return false;
        }
        co.a_(text);
        co.q = this.q.getSelectedIndex();
        co.t(this.e.getBackground().getRGB());
        co.r = this.q.getName();
        co.w = q(this.w.getSelectedItem(), this.q.b);
        co.y = this.w.getSelectedItem();
        if (this.q.q() < 0) {
            return false;
        }
        co.e = this.q.q();
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Note:"), this.q);
        bw.q(eb.q("Priority:"), this.q, this.e, 0);
        bw.q(eb.q("For master:"), this.w);
        bw.q(eb.q("Expires:"), this.q);
    }
    
    public final void q() {
        if (super.q) {
            final es es;
            (es = new es(4198544, this.q())).w = -1;
            es.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final co co = (co)this.q(i);
                es.q(i, co.q());
                es.q(i, 0, co.q());
                es.q(i, 1, co.q);
                es.q(i, 2, co.t());
                es.q(i, 3, co.w);
                es.q(i, 4, co.e);
                es.q(i, 5, co.q);
                es.q(i, 0, co.getName());
                es.q(i, 1, co.r);
            }
            super.q.q(es);
            super.q = false;
        }
    }
    
    public final void w() {
        super.w();
        final dW v = super.q.v;
        dW.q();
        try {
            for (int i = 0; i < super.q.v.q(); ++i) {
                this.e((bZ)((co)super.q.v.q(i)).clone());
            }
        }
        finally {
            final dW v2 = super.q.v;
            dW.w();
        }
    }
    
    public bJ(final dz dz) {
        super(dz, eb.q("Notes"), eb.q("Note"));
        this.q = new TextArea("", 5, 30, 1);
        (this.q = new h()).add(co.w);
        this.q.add(co.q);
        this.q.add(co.e);
        this.e = new j();
        (this.w = new h()).addItem(bJ.q);
        for (int i = 0; i < dz.b.q(); ++i) {
            final bT bt;
            if (!(bt = (bT)dz.b.q(i)).getName().equalsIgnoreCase("Guest")) {
                this.w.add(bt.getName());
            }
        }
        this.q = new S(true, true);
        super.q.w(22);
        this.w.w(400);
        final y y = new y(eb.q("User"), "user");
        final y y2 = new y(eb.q("For user"), "foruser");
        final y y3 = new y(eb.q("Priority"), "priority");
        final y y4 = new y(eb.q("For period"), "ttl");
        this.q.w(y);
        this.q.w(y2);
        this.q.w(y3);
        this.q.w(y4);
        this.q.q(true);
        y.w(true);
        y3.w(true);
        y2.w(true);
        y4.w(true);
        super.w.w(240);
        y.w(80);
        y2.w(80);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
    
    static {
        q = eb.q("All");
        w = eb.q("Master was removed");
    }
}

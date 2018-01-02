// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.TextField;

public final class bH extends bs
{
    private TextField q;
    
    public final bZ q() {
        return new dF(-999, "");
    }
    
    public final void q(final bZ bz) {
        this.q.setText(((cD)bz).getName());
    }
    
    public final boolean q(final bZ bz) {
        final cD cd = (cD)bz;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must enter a full host name or IP address (e.g., \"123.234.56.78\" or \"www.host.com\")."), super.q).setVisible(true);
            return false;
        }
        cd.a_(text);
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("IP/Host:"), this.q);
        bw.q(new H(eb.q("You may enter a full host name or IP address above.  Clients will only be allowed to connect from web pages from this host.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.q) {
            final es es;
            (es = new es(16844556, this.q())).w = -1;
            es.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final cD cd = (cD)this.q(i);
                es.q(i, cd.q());
                es.q(i, 0, cd.q());
                if (!cd.q(63)) {
                    es.q(i, 0, cd.getName());
                }
            }
            super.q.q(es);
            super.q = false;
        }
    }
    
    public final void w() {
        super.w();
        final dW w = super.q.W;
        dW.q();
        try {
            for (int i = 0; i < super.q.W.q(); ++i) {
                this.e(new dF((cD)super.q.W.q(i)));
            }
        }
        finally {
            final dW w2 = super.q.W;
            dW.w();
        }
    }
    
    public bH(final cV cv) {
        super(cv, eb.q("Hosts"), eb.q("Host"));
        this.q = new TextField(30);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}

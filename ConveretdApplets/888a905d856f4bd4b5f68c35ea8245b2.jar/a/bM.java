// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.TextField;

public final class bM extends G
{
    private TextField q;
    
    public final bp q() {
        return new bD(-999, "");
    }
    
    public final void q(final bp bp) {
        this.q.setText(((en)bp).a);
    }
    
    public final boolean q(final bp bp) {
        final en en = (en)bp;
        final String text;
        if ((text = this.q.getText()).length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must enter a full host name or IP address (e.g., \"123.234.56.78\" or \"www.host.com\")."), super.q).setVisible(true);
            return false;
        }
        en.a = text;
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("IP/Host:"), this.q, 0);
        dk.q(new u(be.w("You may enter a full host name or IP address above.  Clients will only be allowed to connect from web pages from this host.")), 2, 1.0f, 0.0f);
    }
    
    public final void q() {
        if (super.w) {
            final dI di;
            (di = new dI(16844556, this.q())).w = -1;
            di.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final en en = (en)this.q(i);
                di.q(i, en.w());
                di.q(i, 0, en.s);
                if (!en.q(63)) {
                    di.q(i, 0, en.a);
                }
            }
            super.q.o(di);
            super.w = false;
        }
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.u.q; ++i) {
                this.e(new bD((en)super.q.u.q(i)));
            }
        }
        finally {}
    }
    
    public bM(final ap ap) {
        super(ap, be.w("Hosts"), be.w("Host"));
        this.q = new TextField(30);
        this.e.setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}

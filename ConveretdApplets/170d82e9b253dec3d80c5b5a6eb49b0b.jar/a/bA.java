// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;

public final class bA extends bs
{
    private TextField q;
    private TextField w;
    private TextField e;
    
    public final bZ q() {
        return new dC(-999, "");
    }
    
    public final void q(final bZ bz) {
        final dC dc = (dC)bz;
        this.q.setText(dc.getName());
        if (dc.q != null) {
            this.w.setText(dc.q);
        }
        else {
            this.w.setText("");
        }
        if (dc.w != null) {
            this.e.setText(dc.w);
            return;
        }
        this.e.setText("");
    }
    
    public final boolean q(final bZ bz) {
        final String text = this.q.getText();
        final String text2 = this.w.getText();
        String text3 = this.e.getText();
        final dC dc = (dC)bz;
        if (text.length() == 0) {
            this.q.requestFocus();
            new b(super.q, eb.q("Note"), eb.q("You must provide a site name for this banner.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.w.requestFocus();
            new b(super.q, eb.q("Note"), new String[] { eb.q("You must provide a file name for this banner image.  Please re-enter this information."), eb.q("Banner images may be either GIF or JPEG files.") }, super.q).setVisible(true);
            return false;
        }
        if (text3.length() == 0) {
            text3 = null;
        }
        dc.q = text2;
        dc.a_(text);
        dc.w = text3;
        return true;
    }
    
    public final void q(final bw bw) {
        bw.q(eb.q("Site Name:"), this.q);
        bw.q(eb.q("Web Address:"), this.e);
        bw.q(eb.q("File Name:"), this.w);
    }
    
    public final void q() {
        if (super.q) {
            final es es;
            (es = new es(67329, this.q())).w = -1;
            es.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final bY by = (bY)this.q(i);
                es.q(i, by.q());
                es.q(i, 0, by.q());
                if (!by.q(63)) {
                    es.q(i, 1, by.q);
                    es.q(i, 2, by.w);
                    es.q(i, 0, by.getName());
                    es.q(i, 1, by.w);
                    es.q(i, 2, by.q);
                    es.q(i, 3, by.e);
                }
            }
            super.q.q(es);
            super.q = false;
        }
    }
    
    public final void w() {
        super.w();
        final dW u = super.q.u;
        dW.q();
        try {
            for (int i = 0; i < super.q.u.q(); ++i) {
                this.e(new dC((bY)super.q.u.q(i)));
            }
        }
        finally {
            final dW u2 = super.q.u;
            dW.w();
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                this.w((bZ)event.arg);
                return true;
            }
            case 702: {
                this.w(null);
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public bA(final cV cv) {
        super(cv, eb.q("Banners"), eb.q("Banner"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        this.e = new TextField(20);
        this.r.setVisible(true);
        this.r.e();
        this.t.setVisible(true);
        this.t.e();
    }
}

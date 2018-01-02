// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Component;
import java.awt.TextField;

public final class bP extends G
{
    private TextField q;
    private TextField w;
    private TextField e;
    
    public final bp q() {
        return new cL(-999, "");
    }
    
    public final void q(final bp bp) {
        final cL cl = (cL)bp;
        this.q.setText(cl.a);
        if (cl.q != null) {
            this.w.setText(cl.q);
        }
        else {
            this.w.setText("");
        }
        if (cl.w != null) {
            this.e.setText(cl.w);
            return;
        }
        this.e.setText("");
    }
    
    public final boolean q(final bp bp) {
        final String text = this.q.getText();
        final String text2 = this.w.getText();
        String text3 = this.e.getText();
        final cL cl = (cL)bp;
        if (text.length() == 0) {
            this.q.requestFocus();
            new dd(super.q, be.w("Note"), be.w("You must provide a site name for this banner.  Please re-enter this information."), super.q).setVisible(true);
            return false;
        }
        if (text2.length() == 0) {
            this.w.requestFocus();
            new dd(super.q, be.w("Note"), new String[] { be.w("You must provide a file name for this banner image.  Please re-enter this information."), be.w("Banner images may be either GIF or JPEG files.") }, super.q).setVisible(true);
            return false;
        }
        if (text3.length() == 0) {
            text3 = null;
        }
        cl.q = text2;
        cl.a = text;
        cl.w = text3;
        return true;
    }
    
    public final void q(final dK dk) {
        dk.q(be.w("Site Name:"), this.q, 0);
        dk.q(be.w("Web Address:"), this.e, 0);
        dk.q(be.w("File Name:"), this.w, 0);
    }
    
    public final void q() {
        if (super.w) {
            final dI di;
            (di = new dI(67329, this.q())).w = -1;
            di.q = -1;
            for (int i = 0; i < this.q(); ++i) {
                final C c = (C)this.q(i);
                di.q(i, c.w());
                di.q(i, 0, c.s);
                if (!c.q(63)) {
                    di.q(i, 1, c.q);
                    di.q(i, 2, c.w);
                    di.q(i, 0, c.a);
                    di.q(i, 1, c.w);
                    di.q(i, 2, c.q);
                    di.q(i, 3, c.e);
                }
            }
            super.q.o(di);
            super.w = false;
        }
    }
    
    public final void w() {
        super.w();
        try {
            for (int i = 0; i < super.q.g.q; ++i) {
                this.e(new cL((C)super.q.g.q(i)));
            }
        }
        finally {}
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                this.w((bp)event.arg);
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
    
    public bP(final ap ap) {
        super(ap, be.w("Banners"), be.w("Banner"));
        this.q = new TextField(20);
        this.w = new TextField(20);
        (this.e = new TextField(20)).setVisible(true);
        this.e.e();
        this.r.setVisible(true);
        this.r.e();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class X extends Panel
{
    private static Color q;
    private static Color w;
    protected N q;
    protected N w;
    protected bf q;
    protected bq q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bm) {
                    if (((bm)event.arg).q) {
                        this.q.e();
                    }
                    else {
                        this.q.q();
                    }
                }
                return true;
            }
            case 702: {
                this.q.e();
                return true;
            }
            case 1001: {
                if (event.target == this.q || event.target == this.q) {
                    this.q.q((bm)this.q.q());
                    return true;
                }
                if (event.target == this.w) {
                    this.q.t();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.q.requestFocus();
    }
    
    public final boolean q(final bm bm) {
        return this.q.q((aX)bm);
    }
    
    public final void q(final bm bm) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((aX)bm)) == -1) {
                this.q.q((aX)bm);
            }
            else {
                this.q.q(bm, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (bm.w() != 0) {
                this.q.q(bm, new Color(bm.w()), X.w);
            }
            else if (bm.q) {
                this.q.q(bm, X.q, X.w);
            }
            else {
                this.q.q(bm, Color.black, Color.white);
            }
        }
    }
    
    public X() {
        this.q = new N(al.q("Enter"));
        this.w = new N(al.q("New"));
        this.q = new bf();
    }
    
    static {
        X.q = new Color(153);
        X.w = new Color(10079487);
    }
}

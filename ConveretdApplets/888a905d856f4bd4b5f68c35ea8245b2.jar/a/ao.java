// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class ao extends Panel
{
    private static Color q;
    private static Color w;
    protected ad q;
    protected ad w;
    protected cc q;
    protected cs q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof cj) {
                    if (((cj)event.arg).q) {
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
                    this.q.q((cj)this.q.q());
                    return true;
                }
                if (event.target == this.w) {
                    this.q.y();
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
    
    public final boolean q(final cj cj) {
        return this.q.q((bJ)cj);
    }
    
    public final void q(final cj cj) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((bJ)cj)) == -1) {
                this.q.e(cj);
            }
            else {
                this.q.q(cj, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (cj.w() != 0) {
                this.q.q(cj, new Color(cj.w()), ao.w);
            }
            else if (cj.q) {
                this.q.q(cj, ao.q, ao.w);
            }
            else {
                this.q.q(cj, Color.black, Color.white);
            }
        }
    }
    
    public ao() {
        this.q = new ad(be.w("Enter"));
        this.w = new ad(be.w("New"));
        this.q = new cc();
    }
    
    static {
        ao.q = new Color(153);
        ao.w = new Color(10079487);
    }
}

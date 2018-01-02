// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class V extends Panel
{
    private static Color q;
    private static Color w;
    protected M q;
    protected M w;
    protected bg q;
    protected br q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bn) {
                    if (((bn)event.arg).q) {
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
                    this.q.q((bn)this.q.q());
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
    
    public final boolean q(final bn bn) {
        return this.q.q((aY)bn);
    }
    
    public final void q(final bn bn) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((aY)bn)) == -1) {
                this.q.q((aY)bn);
            }
            else {
                this.q.q(bn, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (bn.w() != 0) {
                this.q.q(bn, new Color(bn.w()), V.w);
            }
            else if (bn.q) {
                this.q.q(bn, V.q, V.w);
            }
            else {
                this.q.q(bn, Color.black, Color.white);
            }
        }
    }
    
    public V() {
        this.q = new M(ak.q("Enter"));
        this.w = new M(ak.q("New"));
        this.q = new bg();
    }
    
    static {
        V.q = new Color(153);
        V.w = new Color(10079487);
    }
}

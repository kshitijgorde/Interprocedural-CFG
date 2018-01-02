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
    protected be q;
    protected bp q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bl) {
                    if (((bl)event.arg).q) {
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
                    this.q.q((bl)this.q.q());
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
    
    public final boolean q(final bl bl) {
        return this.q.q((aW)bl);
    }
    
    public final void q(final bl bl) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((aW)bl)) == -1) {
                this.q.q((aW)bl);
            }
            else {
                this.q.q(bl, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (bl.w() != 0) {
                this.q.q(bl, new Color(bl.w()), V.w);
            }
            else if (bl.q) {
                this.q.q(bl, V.q, V.w);
            }
            else {
                this.q.q(bl, Color.black, Color.white);
            }
        }
    }
    
    public V() {
        this.q = new M(ak.q("Enter"));
        this.w = new M(ak.q("New"));
        this.q = new be();
    }
    
    static {
        V.q = new Color(153);
        V.w = new Color(10079487);
    }
}

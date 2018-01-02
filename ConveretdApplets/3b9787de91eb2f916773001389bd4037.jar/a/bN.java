// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class bN extends Panel
{
    private static Color q;
    private static Color w;
    protected e q;
    protected e w;
    protected u q;
    protected bz q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bO) {
                    if (((bO)event.arg).q) {
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
                    this.q.q((bO)this.q.q());
                    return true;
                }
                if (event.target == this.w) {
                    this.q.q();
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
    
    public final boolean q(final bO bo) {
        return this.q.q((aq)bo);
    }
    
    public final void q(final bO bo) {
        synchronized (this.q) {
            final int q;
            if ((q = this.q.q((aq)bo)) == -1) {
                this.q.q((aq)bo);
            }
            else {
                this.q.q(bo, q);
            }
            if (this.q.w() == q) {
                this.q.q(q);
            }
            if (bo.r() != 0) {
                this.q.q(bo, new Color(bo.r()), bN.w);
            }
            else if (bo.q) {
                this.q.q(bo, bN.q, bN.w);
            }
            else {
                this.q.q(bo, Color.black, Color.white);
            }
        }
    }
    
    public bN() {
        this.q = new e(cv.q("Enter"));
        this.w = new e(cv.q("New"));
        this.q = new u();
    }
    
    static {
        bN.q = new Color(153);
        bN.w = new Color(10079487);
    }
}

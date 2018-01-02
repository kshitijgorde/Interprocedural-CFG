// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class bd extends Panel
{
    protected static final Color c;
    protected static final Color i;
    protected ax d;
    protected ax e;
    protected u e;
    protected as i;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && event.key == 4 && as.a != null) {
            as.a(this.i);
            return true;
        }
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bn) {
                    if (((bn)event.arg).a) {
                        this.d.g();
                    }
                    else {
                        this.d.c();
                    }
                }
                return true;
            }
            case 702: {
                this.d.g();
                return true;
            }
            case 1001: {
                if (event.target == this.d || event.target == this.e) {
                    this.i.c((bn)this.e.a());
                    return true;
                }
                if (event.target == this.e) {
                    this.i.i();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.e.requestFocus();
    }
    
    public boolean a(final bn bn) {
        return this.e.a((az)bn);
    }
    
    public void a(final bn bn) {
        synchronized (this.e) {
            final int a = this.e.a((az)bn);
            if (a == -1) {
                this.e.a((az)bn);
            }
            else {
                this.e.b(bn, a);
            }
            if (this.e.d() == a) {
                this.e.g(a);
            }
            this.b(bn);
        }
    }
    
    public void b(final bn bn) {
        if (bn.a) {
            this.e.a(bn, new Color(bn.u), bd.i, bn.d);
        }
        else {
            this.e.a(bn, new Color(bn.u), Color.white, bn.d);
        }
    }
    
    public bd() {
        this.d = new ax(ar.b("Enter"));
        this.e = new ax(ar.b("New"));
        this.e = new u();
    }
    
    static {
        c = new Color(153);
        i = new Color(10079487);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class O extends Panel
{
    protected static final Color c;
    protected static final Color h;
    protected aS c;
    protected aS d;
    protected l d;
    protected u c;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && t.a(event.key, this.c)) {
            return true;
        }
        switch (event.id) {
            case 701: {
                if (event.arg instanceof T) {
                    if (((T)event.arg).a) {
                        this.c.d();
                    }
                    else {
                        this.c.c();
                    }
                }
                return true;
            }
            case 702: {
                this.c.d();
                return true;
            }
            case 1001: {
                if (event.target == this.c || event.target == this.d) {
                    this.c.c((T)this.d.a());
                    return true;
                }
                if (event.target == this.d) {
                    this.c.h();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.d.requestFocus();
    }
    
    public boolean a(final T t) {
        return this.d.a((aU)t);
    }
    
    public void a(final T t) {
        synchronized (this.d) {
            final int a = this.d.a((aU)t);
            if (a == -1) {
                this.d.c(t);
            }
            else {
                this.d.b(t, a);
            }
            if (this.d.d() == a) {
                this.d.h(a);
            }
            this.b(t);
        }
    }
    
    public void b(final T t) {
        if (t.a) {
            this.d.a(t, new Color(t.aN), O.h, t.d);
        }
        else {
            this.d.a(t, new Color(t.aN), Color.white, t.d);
        }
    }
    
    public O() {
        this.c = new aS(ao.e("Enter"));
        this.d = new aS(ao.e("New"));
        this.d = new l();
    }
    
    static {
        c = new Color(153);
        h = new Color(10079487);
    }
}

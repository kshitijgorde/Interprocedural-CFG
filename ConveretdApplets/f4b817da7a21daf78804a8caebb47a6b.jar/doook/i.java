// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.Color;
import java.awt.Panel;

public class i extends Panel
{
    protected static final Color a;
    protected static final Color b;
    protected al b;
    protected al c;
    protected am a;
    protected be b;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 402 && event.modifiers == 2 && event.key == 4 && be.a != null) {
            be.a(this.b);
            return true;
        }
        switch (event.id) {
            case 701: {
                if (event.arg instanceof ax) {
                    if (((ax)event.arg).e) {
                        this.b.g();
                    }
                    else {
                        this.b.b();
                    }
                }
                return true;
            }
            case 702: {
                this.b.g();
                return true;
            }
            case 1001: {
                if (event.target == this.b || event.target == this.a) {
                    this.b.a((ax)this.a.a());
                    return true;
                }
                if (event.target == this.c) {
                    this.b.b();
                    break;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public void show() {
        super.show();
        this.a.requestFocus();
    }
    
    public boolean a(final ax ax) {
        return this.a.a((bk)ax);
    }
    
    public void b(final ax ax) {
        synchronized (this.a) {
            final int a = this.a.a((bk)ax);
            if (a == -1) {
                this.a.a((bk)ax);
            }
            else {
                this.a.b(ax, a);
            }
            if (this.a.f() == a) {
                this.a.i(a);
            }
            this.c(ax);
        }
    }
    
    public void c(final ax ax) {
        if (ax.e) {
            this.a.a(ax, new Color(ax.ag), i.b, ax.ah);
        }
        else {
            this.a.a(ax, new Color(ax.ag), Color.white, ax.ah);
        }
    }
    
    public i() {
        this.b = new al(aG.a("Enter"));
        this.c = new al(aG.a("New"));
        this.a = new am();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
    }
}

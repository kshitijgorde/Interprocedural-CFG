// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.j;
import com.diginet.digichat.awt.ba;
import com.diginet.digichat.awt.o;
import com.diginet.digichat.common.a6;
import java.awt.Event;
import com.diginet.digichat.awt.bm;
import java.awt.Component;
import java.awt.Color;
import java.awt.Panel;

public class bs extends Panel
{
    protected static final Color a;
    protected static final Color b;
    protected Component c;
    protected Component d;
    protected bm e;
    protected h f;
    protected int g;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bf) {
                    if (((bf)event.arg).b) {
                        this.a(this.c);
                    }
                    else {
                        this.b(this.c);
                    }
                }
                return true;
            }
            case 702: {
                this.a(this.c);
                return true;
            }
            case 1001: {
                if (event.target == this.c || event.target == this.e) {
                    this.f.a((bf)this.e.g());
                    return true;
                }
                if (event.target == this.d) {
                    if (this.f.c2) {
                        this.f.n(this.g);
                    }
                    else {
                        this.f.n(((a6)this.f.al.d(this.f.b)).e);
                    }
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    protected final void a(final Component component) {
        if (component == null) {
            return;
        }
        if (component instanceof o) {
            ((o)component).c();
        }
        else {
            ((ba)component).setEnabled(false);
        }
        component.enable(true);
    }
    
    protected final void b(final Component component) {
        if (component == null) {
            return;
        }
        if (component instanceof o) {
            ((o)component).b();
        }
        else {
            ((ba)component).setEnabled(true);
        }
    }
    
    public final boolean c(final Component component) {
        if (component == null) {
            return false;
        }
        if (component instanceof o) {
            return ((o)component).a();
        }
        return ((ba)component).isEnabled();
    }
    
    public final void show() {
        super.show();
        this.e.requestFocus();
    }
    
    public final boolean a(final bf bf) {
        return this.e.d(bf);
    }
    
    public final void b(final bf bf) {
        if (bf.u(54)) {
            return;
        }
        synchronized (this.e) {
            final int a = this.e.a(bf);
            if (a == -1) {
                this.e.c(bf);
            }
            else {
                this.e.b(bf, a);
            }
            if (this.e.f() == a) {
                this.e.k(a);
            }
            this.c(bf);
        }
        // monitorexit(this.e)
    }
    
    public final void c(final bf bf) {
        if (bf.b) {
            this.e.a(bf, bs.a, bs.b);
        }
        else {
            this.e.a(bf, Color.black, Color.white);
        }
    }
    
    public final void a() {
        this.e.i();
    }
    
    public bs() {
        this.e = new bm();
        this.g = -999;
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
    }
}

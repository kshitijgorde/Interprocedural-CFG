// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.esial.util.d;
import com.diginet.digichat.awt.m;
import java.awt.Event;
import com.diginet.digichat.awt.bj;
import com.diginet.digichat.awt.r;
import java.awt.Color;
import java.awt.Panel;

public class bo extends Panel
{
    protected static final Color a;
    protected static final Color b;
    protected r c;
    protected r d;
    protected bj e;
    protected i f;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bc) {
                    if (((bc)event.arg).a) {
                        this.c.c();
                    }
                    else {
                        this.c.b();
                    }
                }
                return true;
            }
            case 702: {
                this.c.c();
                return true;
            }
            case 1001: {
                if (event.target == this.c || event.target == this.e) {
                    this.f.a((bc)this.e.g());
                    return true;
                }
                if (event.target == this.d) {
                    this.f.i();
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
    
    public boolean a(final bc bc) {
        return this.e.c(bc);
    }
    
    public void b(final bc bc) {
        synchronized (this.e) {
            final int a = this.e.a(bc);
            if (a == -1) {
                this.e.b(bc);
            }
            else {
                this.e.b(bc, a);
            }
            if (this.e.f() == a) {
                this.e.k(a);
            }
            this.c(bc);
        }
        // monitorexit(this.e)
    }
    
    public void c(final bc bc) {
        if (bc.a) {
            this.e.a(bc, bo.a, bo.b);
        }
        else {
            this.e.a(bc, Color.black, Color.white);
        }
    }
    
    public bo() {
        this.c = new r(com.esial.util.d.a("Enter"));
        this.d = new r(com.esial.util.d.a("New"));
        this.e = new bj();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
    }
}

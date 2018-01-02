// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.esial.util.c;
import com.diginet.digichat.awt.m;
import java.awt.Event;
import com.diginet.digichat.awt.ao;
import com.diginet.digichat.awt.r;
import java.awt.Color;
import java.awt.Panel;

public class bx extends Panel
{
    public static final Color a;
    public static final Color b;
    protected r c;
    protected r d;
    protected ao e;
    protected i f;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 701: {
                if (event.arg instanceof bo) {
                    if (((bo)event.arg).a) {
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
                    this.f.a((bo)this.e.g());
                    return true;
                }
                if (event.target == this.d) {
                    this.f.o();
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
    
    public boolean a(final bo bo) {
        return this.e.e(bo);
    }
    
    public void b(final bo bo) {
        synchronized (this.e) {
            final int b = this.e.b(bo);
            if (b == -1) {
                this.e.d(bo);
            }
            else {
                this.e.b(bo, b);
            }
            if (this.e.f() == b) {
                this.e.k(b);
            }
            this.c(bo);
        }
        // monitorexit(this.e)
    }
    
    public void c(final bo bo) {
        if (bo.clrName != null) {
            this.e.a(bo, bo.clrName, bo.clrName);
        }
        else if (bo.a) {
            this.e.a(bo, bx.a, bx.b);
        }
        else {
            this.e.a(bo, Color.black, Color.white);
        }
        this.e.setBackground(bo, bo.clrBack);
    }
    
    public bx() {
        this.c = new r(com.esial.util.c.a("Enter"));
        this.d = new r(com.esial.util.c.a("New"));
        this.e = new ao();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
    }
}

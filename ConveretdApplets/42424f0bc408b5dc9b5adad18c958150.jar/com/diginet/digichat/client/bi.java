// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.awt.m;
import com.diginet.digichat.awt.bj;
import java.awt.Color;
import java.awt.Panel;

public abstract class bi extends Panel
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    public static final Color e;
    protected bj f;
    protected i g;
    
    public boolean a(final aw aw) {
        return this.f.c(aw);
    }
    
    public void show() {
        super.show();
        this.f.requestFocus();
    }
    
    protected void b(final aw aw) {
        if (aw.c) {
            this.f.a(aw, Color.red, Color.pink);
        }
        else if (aw.i(59)) {
            this.f.a(aw, bi.c, bi.d);
        }
        else if ((aw.i(61) || aw.i(62)) && !aw.i(23)) {
            this.f.a(aw, bi.a, bi.b);
        }
        else if (aw.i(23)) {
            this.f.a(aw, bi.e, bi.d);
        }
        else {
            this.f.a(aw, Color.black, Color.white);
        }
        if (aw.d) {
            this.f.a(aw, true);
        }
        else {
            this.f.a(aw, false);
        }
    }
    
    public void a(final aw aw, final boolean b) {
        synchronized (this.f) {
            if (this.g.i(24) || !aw.i(23)) {
                final int a = this.f.a(aw);
                if (a == -1) {
                    if (b) {
                        this.f.b(aw);
                    }
                }
                else {
                    this.f.b(aw, a);
                }
                this.b(aw);
            }
        }
        // monitorexit(this.f)
    }
    
    public bi() {
        this.f = new bj();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
        e = new Color(39168);
    }
}

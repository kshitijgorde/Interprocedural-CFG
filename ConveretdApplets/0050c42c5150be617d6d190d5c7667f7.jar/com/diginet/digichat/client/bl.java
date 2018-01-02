// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client;

import com.diginet.digichat.common.a6;
import com.diginet.digichat.awt.j;
import com.diginet.digichat.awt.bm;
import java.awt.Color;
import java.awt.Panel;

public abstract class bl extends Panel
{
    public static final Color a;
    public static final Color b;
    public static final Color c;
    public static final Color d;
    public static final Color e;
    public static final Color f;
    protected bm g;
    protected h h;
    
    public final boolean a(final User2 user2) {
        return this.g.d(user2);
    }
    
    public final void show() {
        super.show();
        this.g.requestFocus();
    }
    
    protected final void b(final User2 user2) {
        if (user2.x() == this.h.x()) {
            this.g.a(user2, bl.f, bl.d);
        }
        else if (user2.c) {
            this.g.a(user2, Color.red, Color.pink);
        }
        else if (user2.u(59)) {
            this.g.a(user2, bl.c, bl.d);
        }
        else if ((user2.u(61) || user2.u(62)) && !user2.u(23)) {
            this.g.a(user2, bl.a, bl.b);
        }
        else if (user2.u(23)) {
            this.g.a(user2, bl.e, bl.d);
        }
        else {
            this.g.a(user2, Color.black, Color.white);
        }
        if (user2.d) {
            this.g.b(user2, true);
        }
        else {
            this.g.b(user2, false);
        }
    }
    
    public final void a(final User2 user2, final boolean b) {
        synchronized (this.g) {
            if (this.h.u(24) || !user2.u(23)) {
                final int a = this.g.a(user2);
                if (a == -1) {
                    if (b) {
                        final a6 a2 = (a6)this.h.al.d(this.h.b);
                        if (this.h.c3 && a2 != null && a2.a()) {
                            this.g.a(user2, this.c(user2));
                        }
                        else {
                            this.g.c(user2);
                        }
                    }
                }
                else {
                    this.g.b(user2, a);
                }
                this.b(user2);
            }
        }
        // monitorexit(this.g)
    }
    
    private final int c(final User2 user2) {
        if (this.g.e() == 0) {
            return 0;
        }
        int i = 0;
        final String name = user2.getName();
        final boolean u = user2.u(60);
        final boolean u2 = user2.u(59);
        while (i < this.g.e()) {
            final User2 user3 = (User2)this.g.j(i);
            final boolean u3 = user3.u(60);
            final boolean u4 = user3.u(59);
            if (u && !u3) {
                break;
            }
            if (u2 && !u3 && !u4) {
                break;
            }
            if (u && u3 && name.compareTo(user3.getName()) < 0) {
                break;
            }
            if (u2 && u4 && name.compareTo(user3.getName()) < 0) {
                break;
            }
            if (!u2 && !u && !u3 && !u4 && name.compareTo(user3.getName()) < 0) {
                break;
            }
            if ((u || !u3) && (u || u2 || !u4) && (!u2 || !u3) && name.compareTo(user3.getName()) <= 0) {
                continue;
            }
            ++i;
        }
        return i;
    }
    
    public final User2 b() {
        return (User2)this.g.g();
    }
    
    public bl() {
        this.g = new bm();
    }
    
    static {
        a = new Color(153);
        b = new Color(10079487);
        c = new Color(16711680);
        d = new Color(10079487);
        e = new Color(39168);
        f = new Color(10040217);
    }
}

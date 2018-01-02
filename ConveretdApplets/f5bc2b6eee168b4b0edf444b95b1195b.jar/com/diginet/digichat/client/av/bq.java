// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.client.av;

import com.diginet.digichat.common.a6;
import com.diginet.digichat.network.t;
import com.diginet.digichat.client.User2;
import com.diginet.digichat.awt.j;
import java.awt.Event;
import com.diginet.digichat.client.g;
import com.diginet.digichat.awt.bm;

public class bq extends bo
{
    private g a;
    private long b;
    
    public final boolean a(final Event event, final j j) {
        if (event.id == 1001 || event.id == 6301) {
            if (this.a != null && j != null && j instanceof User2 && ((User2)j).x() != this.a.x()) {
                final User2 user2 = (User2)j;
                if (System.currentTimeMillis() - this.b < 2000L) {
                    return true;
                }
                this.b = System.currentTimeMillis();
                if ("audio".equals(super.f)) {
                    return this.a.c(user2);
                }
                if ("video".equals(super.f)) {
                    return this.a.b(user2);
                }
            }
            else if (this.a != null && j != null && j instanceof User2 && ((User2)j).x() == this.a.x()) {
                final t t = new t(67334, 1);
                t.m = -1;
                t.l = -1;
                t.a(0, 0, this.a.x());
                t.a(0, 1, this.a.cc);
                t.a(0, 0, this.a.getName());
                t.a(0, this.a.z());
                this.a.ap(t);
            }
            else if (this.a == null || j == null || j instanceof a6) {}
            return true;
        }
        return false;
    }
    
    public bq(final g a, final Object o, final String s) {
        super(o, s);
        ((bm.bo)this).b(22);
        this.a = a;
    }
}

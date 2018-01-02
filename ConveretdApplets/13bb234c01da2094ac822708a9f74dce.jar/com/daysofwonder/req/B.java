// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import com.daysofwonder.a.e;

public class B extends k
{
    private e l;
    private int m;
    private int n;
    
    public B() {
        super(13);
        this.m = 0;
        this.n = -1;
        this.D();
    }
    
    public B(final boolean b, final int m) {
        super(13);
        this.m = 0;
        this.n = -1;
        this.m = m;
        this.D();
    }
    
    public B(final int m, final int n) {
        super(13);
        this.m = 0;
        this.n = -1;
        this.m = m;
        this.n = n;
        this.D();
    }
    
    public B(final G g) {
        this.m = 0;
        this.n = -1;
        this.g = 13;
        this.a(g.t());
        this.b();
    }
    
    public void a() {
        super.a();
        this.writeInt(this.m);
        this.writeInt(this.n);
        this.writeBoolean(this.l != null);
        if (this.l != null) {
            this.l.a(this);
        }
    }
    
    public void b() {
        super.b();
        this.m = this.readInt();
        this.n = this.readInt();
        if (this.readBoolean()) {
            (this.l = new e()).b(this);
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.a(sb);
        return sb.toString();
    }
    
    public StringBuffer a(final StringBuffer sb) {
        sb.append("[buddylist_info=");
        super.a(sb);
        return sb;
    }
    
    public int i() {
        int i = super.i();
        i += 9;
        if (this.l != null) {
            i += this.l.c(this);
        }
        return i;
    }
    
    public e d() {
        return this.l;
    }
}

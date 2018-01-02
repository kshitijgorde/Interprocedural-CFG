// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Date;
import java.text.SimpleDateFormat;

public final class bW extends bZ
{
    public long q;
    public int q;
    
    public bW(final int n, final String s) {
        super(n, s);
        this.q = 0L;
        this.q(this.q = 0, ci.q(s));
    }
    
    public bW(final bW bw) {
        super(bw.r, bw.getName());
        this.q = 0L;
        this.q = 0;
        this.q(bw.q());
        this.q = bw.q;
        this.q = bw.q;
    }
    
    public final boolean q() {
        return this.q(0);
    }
    
    public final void q(final boolean b) {
        this.q(0, b);
    }
    
    public final Object q(final String s) {
        if ("bantime".equals(s)) {
            return new SimpleDateFormat("dd MMM yyyy HH:mm").format(new Date(this.q));
        }
        if (!"name".equals(s)) {
            return super.q(s);
        }
        if (ci.q(this.getName())) {
            return ci.w(this.getName());
        }
        return this.getName();
    }
}

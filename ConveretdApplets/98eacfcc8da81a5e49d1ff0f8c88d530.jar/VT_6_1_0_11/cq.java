// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;

final class cq
{
    K a;
    bd b;
    q c;
    boolean d;
    IOException e;
    private byte[] f;
    private int[] g;
    
    cq(final bd b, final q c, final bC bc) {
        this.d = false;
        this.e = null;
        this.f = null;
        this.g = null;
        this.b = b;
        this.c = c;
        this.a = new K(bc, this);
        new StringBuffer().append("Demux: Opening stream ").append(this.a.hashCode()).append(" for demux (").append(bc.hashCode()).append(")").toString();
    }
    
    final byte[] a(final bc bc) {
        if (this.f == null) {
            this.c(bc);
        }
        return this.f;
    }
    
    final int[] b(final bc bc) {
        if (this.g == null) {
            this.c(bc);
        }
        return this.g;
    }
    
    private void c(final bc bc) {
        this.f = ("--" + bz.c("boundary", this.b.a("Content-Type")) + "--\r\n").getBytes("8859_1");
        this.g = bz.a(this.f);
        bc.a();
    }
}

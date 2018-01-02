// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

public final class p implements s
{
    private String a;
    private boolean b;
    private boolean c;
    private int d;
    
    public p(final String a, final int n, final boolean b, final boolean c, final boolean b2, final int d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }
    
    public final String a() {
        return this.a;
    }
    
    public final boolean b() {
        return this.b;
    }
    
    public final boolean c() {
        return this.c;
    }
    
    public final String toString() {
        return this.a;
    }
    
    public final boolean d() {
        return (this.d & 0x1) > 0;
    }
    
    public final boolean e() {
        return (this.d & 0x2) > 0;
    }
}

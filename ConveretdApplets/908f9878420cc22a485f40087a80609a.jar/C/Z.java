// 
// Decompiled by Procyon v0.5.30
// 

package C;

import core.K;

public abstract class Z
{
    public final int a;
    public final int b;
    public final int c;
    public final K d;
    
    public Z(final int n, final K d) {
        this.a = (n >> 16 & 0xFF);
        this.b = (n >> 8 & 0xFF);
        this.c = (n & 0xFF);
        this.d = d;
    }
    
    public abstract void a(final float p0, final float p1);
}

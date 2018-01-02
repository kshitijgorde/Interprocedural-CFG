// 
// Decompiled by Procyon v0.5.30
// 

package core;

public final class H
{
    public final byte a;
    public final byte b;
    public final byte c;
    
    static {
        new H(0);
    }
    
    public H(final int n) {
        this.a = (byte)(n >> 16);
        this.b = (byte)(n >> 8);
        this.c = (byte)n;
    }
}

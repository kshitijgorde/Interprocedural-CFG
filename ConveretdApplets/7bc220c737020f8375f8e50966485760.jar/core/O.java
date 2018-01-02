// 
// Decompiled by Procyon v0.5.30
// 

package core;

public final class O
{
    public final byte a;
    public final byte b;
    public final byte c;
    
    static {
        new O(0);
    }
    
    public O(final int n) {
        this.a = (byte)(n >> 16);
        this.b = (byte)(n >> 8);
        this.c = (byte)n;
    }
}

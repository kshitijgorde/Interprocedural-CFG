// 
// Decompiled by Procyon v0.5.30
// 

final class n
{
    static final float a(final float n, final float n2, final float n3) {
        return (n - n2) % (n3 - n2) + n2;
    }
    
    static final float b(final float n, final float n2, final float n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    static final int a(final int n, final int n2, final int n3) {
        return Math.min(Math.max(n, n2), n3);
    }
}

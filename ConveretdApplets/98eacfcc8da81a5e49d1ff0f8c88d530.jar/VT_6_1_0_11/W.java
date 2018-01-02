// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class W
{
    private String a;
    private int b;
    private static final char[] c;
    
    public W(final String a) {
        this.a = a;
        this.b = a(a);
    }
    
    public final String a() {
        return this.a;
    }
    
    public final int hashCode() {
        return this.b;
    }
    
    private static final int a(final String s) {
        int n = 0;
        final char[] c = W.c;
        for (int length = s.length(), i = 0; i < length; ++i) {
            n = n * 31 + c[s.charAt(i)];
        }
        return n;
    }
    
    public final boolean equals(final Object o) {
        if (o != null) {
            if (o instanceof W) {
                return this.a.equalsIgnoreCase(((W)o).a);
            }
            if (o instanceof String) {
                return this.a.equalsIgnoreCase((String)o);
            }
        }
        return false;
    }
    
    public final String toString() {
        return this.a;
    }
    
    static {
        c = new char[256];
        for (char c2 = '\0'; c2 < '\u0100'; ++c2) {
            W.c[c2] = Character.toLowerCase(c2);
        }
    }
}

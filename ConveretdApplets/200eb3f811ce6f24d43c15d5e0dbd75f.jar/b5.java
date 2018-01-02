// 
// Decompiled by Procyon v0.5.30
// 

public class b5
{
    public static final b5 a;
    public final byte b;
    public final byte c;
    public final byte d;
    public final byte e;
    public final byte f;
    public final boolean g;
    
    public b5(final char c, final char c2, final char c3, final char c4, final char c5, final boolean g) {
        this.b = (byte)c;
        this.c = (byte)c2;
        this.d = (byte)c3;
        this.e = (byte)c4;
        this.f = (byte)c5;
        this.g = g;
    }
    
    public b5(final char c, final char c2, final char c3, final char c4, final char c5) {
        this(c, c2, c3, c4, c5, true);
    }
    
    public final boolean a(final byte b) {
        return b == this.e || b == this.f;
    }
    
    static {
        a = new b5(';', '\"', '\n', ' ', '\t');
    }
}

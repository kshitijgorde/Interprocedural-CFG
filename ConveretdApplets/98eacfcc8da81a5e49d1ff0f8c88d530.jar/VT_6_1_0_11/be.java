// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public final class be extends an
{
    private static Byte b;
    private static Byte c;
    
    public be() {
        super((byte)5);
    }
    
    public be(final Hashtable hashtable) {
        super(hashtable);
    }
    
    public be(final byte b) {
        super((byte)5);
        this.a(b);
    }
    
    public be(final byte b, String s) {
        super((byte)5);
        this.a((byte)4);
        s = s;
        super.a.put(be.c, s);
    }
    
    private void a(final byte b) {
        super.a.put(be.b, new Byte(b));
    }
    
    public final byte a() {
        return super.a.get(be.b);
    }
    
    static {
        be.b = new Byte((byte)11);
        be.c = new Byte((byte)12);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public final class bW extends an
{
    private static Byte b;
    private static Byte c;
    private static Byte d;
    
    public bW() {
        super((byte)7);
    }
    
    public bW(final Hashtable hashtable) {
        super(hashtable);
    }
    
    public bW(final byte[] array, final int n) {
        super((byte)7);
        super.a.put(bW.b, array);
        super.a.put(bW.d, new Integer(n));
    }
    
    public bW(final B b) {
        super((byte)7);
        super.a.put(bW.c, b.a().a());
    }
    
    public final byte[] a() {
        return super.a.get(bW.b);
    }
    
    public final B b() {
        final Hashtable hashtable;
        if ((hashtable = super.a.get(bW.c)) != null) {
            return new B(new bE(hashtable));
        }
        return null;
    }
    
    static {
        bW.b = new Byte((byte)11);
        bW.c = new Byte((byte)12);
        bW.d = new Byte((byte)13);
    }
}

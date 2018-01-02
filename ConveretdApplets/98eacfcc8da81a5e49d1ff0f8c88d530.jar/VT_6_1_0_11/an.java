// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public class an
{
    protected Hashtable a;
    private static Byte b;
    private static Byte c;
    
    public an(final Hashtable a) {
        this.a = a;
    }
    
    public an(final byte b) {
        (this.a = new Hashtable()).put(an.b, new Byte(b));
    }
    
    public final byte c() {
        return this.a.get(an.b);
    }
    
    public final int d() {
        return this.a.get(an.c);
    }
    
    public final Hashtable e() {
        return this.a;
    }
    
    public static an a(final Hashtable hashtable) {
        final byte byteValue;
        if ((byteValue = hashtable.get(an.b)) == 7) {
            return new bW(hashtable);
        }
        if (byteValue == 7) {
            return new bW(hashtable);
        }
        if (byteValue == 3) {
            return new bQ(hashtable);
        }
        if (byteValue == 4) {
            return new af(hashtable);
        }
        if (byteValue == 5) {
            return new be(hashtable);
        }
        if (byteValue == 6) {
            return new ct(hashtable);
        }
        if (byteValue == 8) {
            return new as(hashtable);
        }
        throw new IllegalArgumentException("ConfEvent.buildEvent: bad type=" + byteValue);
    }
    
    static {
        an.b = new Byte((byte)1);
        an.c = new Byte((byte)2);
    }
}

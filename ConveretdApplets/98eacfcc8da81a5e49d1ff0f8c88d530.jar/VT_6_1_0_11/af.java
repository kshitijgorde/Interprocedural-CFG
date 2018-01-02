// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public final class af extends an
{
    private static Byte b;
    private static Byte c;
    
    public af() {
        super((byte)4);
    }
    
    public af(final Hashtable hashtable) {
        super(hashtable);
    }
    
    public final String a() {
        return super.a.get(af.c);
    }
    
    public final byte b() {
        return super.a.get(af.b);
    }
    
    static {
        af.b = new Byte((byte)11);
        af.c = new Byte((byte)12);
    }
}

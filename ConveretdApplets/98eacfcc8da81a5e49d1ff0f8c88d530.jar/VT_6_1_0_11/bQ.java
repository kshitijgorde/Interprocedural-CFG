// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.util.Hashtable;

public final class bQ extends an
{
    private static Byte b;
    private static Byte c;
    
    public bQ() {
        super((byte)3);
    }
    
    public bQ(final Hashtable hashtable) {
        super(hashtable);
    }
    
    public bQ(final String s, final String s2) {
        this();
        super.a.put(bQ.b, s);
        super.a.put(bQ.c, s2);
    }
    
    public final String a() {
        return super.a.get(bQ.b);
    }
    
    public final String b() {
        return super.a.get(bQ.c);
    }
    
    static {
        bQ.b = new Byte((byte)11);
        bQ.c = new Byte((byte)12);
    }
}

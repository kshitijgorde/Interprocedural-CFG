// 
// Decompiled by Procyon v0.5.30
// 

public class d4 extends d6 implements d5
{
    public long a;
    
    public d4(final Object o, final String s) throws NoSuchFieldException {
        super(o, s);
    }
    
    public long a() {
        final long a = dy.a(super.b, super.a);
        final long n = a - this.a;
        this.a = a;
        return n;
    }
    
    public void b() {
        this.a = dy.a(super.b, super.a);
    }
}

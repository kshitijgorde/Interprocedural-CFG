import java.lang.reflect.Field;

// 
// Decompiled by Procyon v0.5.30
// 

public class d6 implements d5
{
    public Object a;
    public Field b;
    
    public d6(final Object a, final String s) throws NoSuchFieldException {
        this.a = a;
        this.b = a.getClass().getField(s);
    }
    
    public long a() {
        return dy.a(this.b, this.a);
    }
    
    public void b() {
    }
}

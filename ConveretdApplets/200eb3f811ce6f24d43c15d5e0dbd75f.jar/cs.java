// 
// Decompiled by Procyon v0.5.30
// 

public class cs extends cr
{
    public static final cs a;
    private static final ac b;
    
    public Class a() {
        return et.g;
    }
    
    public Object a(final String s, final u u) throws InstantiationException {
        try {
            return cs.b.a(s);
        }
        catch (Throwable t) {
            throw new InstantiationException(t.getMessage());
        }
    }
    
    public static void b() {
        cs.b.a();
    }
    
    static {
        a = new cs();
        b = new ac("HH:mm:ss");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

public class cq extends cr
{
    public static final cq a;
    private static final ac b;
    
    public Class a() {
        return et.g;
    }
    
    public Object a(final String s, final u u) throws InstantiationException {
        try {
            return cq.b.a(s);
        }
        catch (Throwable t) {
            throw new InstantiationException(t.getMessage());
        }
    }
    
    public static void b() {
        cq.b.a();
    }
    
    static {
        a = new cq();
        b = new ac("yyyy-MM-dd");
    }
}

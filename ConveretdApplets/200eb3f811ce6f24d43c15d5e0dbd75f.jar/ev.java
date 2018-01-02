// 
// Decompiled by Procyon v0.5.30
// 

public class ev extends cr
{
    public static final ev a;
    
    public Class a() {
        return this.getClass();
    }
    
    public Object a(final String s, final u u) throws InstantiationException {
        try {
            return new x(s);
        }
        catch (Exception ex) {
            throw new InstantiationException("Could not create value for " + s);
        }
    }
    
    static {
        a = new ev();
    }
}

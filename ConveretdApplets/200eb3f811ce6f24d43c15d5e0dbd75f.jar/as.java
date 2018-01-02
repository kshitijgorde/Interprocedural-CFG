// 
// Decompiled by Procyon v0.5.30
// 

public class as extends at
{
    public as() {
    }
    
    public as(final v a) {
        super("PushConfig", a, au.a());
        super.a = a;
    }
    
    public void c(final String s, final String s2) {
        super.c(s, s2);
    }
    
    public static as b(final v v) throws aw {
        try {
            return new as(v);
        }
        catch (ao ao) {
            if (ao.a() != null) {
                throw new aw(ao.getMessage(), ao.a());
            }
            throw new aw("", ao);
        }
        catch (Exception ex) {
            throw new aw("InternalError:UnexpectedException ", ex);
        }
    }
}

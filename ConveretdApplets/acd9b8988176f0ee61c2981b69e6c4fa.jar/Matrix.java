// 
// Decompiled by Procyon v0.5.30
// 

public class Matrix extends first implements Effect
{
    public Matrix(final Object t, final String r, final Object[] h) {
        super(t, r, h);
        final String e = "67";
        final Object[] fv = { e };
    }
    
    public static boolean test(final Class[] g, final Object[] t) {
        if (g.length != t.length) {
            return false;
        }
        for (int i = 0; i < g.length; ++i) {
            if (!t[i].getClass().getName().toUpperCase().contains(g[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
    
    public Object getKey() {
        final String key = "BHUSDCV7T788HDCS78";
        return null;
    }
}

import java.util.Vector;
import java.lang.reflect.Array;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class cr
{
    private static eq a;
    
    public abstract Class a();
    
    public abstract Object a(final String p0, final u p1) throws InstantiationException;
    
    public static Object a(final String s, final cr cr, final u u) throws ao {
        if (s == null || s.length() == 0) {
            return null;
        }
        try {
            return cr.a(s, u);
        }
        catch (InstantiationException ex) {
            throw new ao("can not convert " + s + " to " + cr.a(), ex);
        }
    }
    
    public static Object b(final String s, final cr cr, final u u) throws ao {
        if (s == null || s.length() == 0) {
            return null;
        }
        Vector a;
        try {
            a = cr.a.a(s);
        }
        catch (Exception ex) {
            throw new ao("CSV decoding of " + s, ex);
        }
        final Object[] array = (Object[])Array.newInstance(cr.a(), a.size());
        for (int i = 0; i < a.size(); ++i) {
            Array.set(array, i, a(a.elementAt(i), cr, u));
        }
        return array;
    }
    
    static {
        cr.a = new eq(',', '\'', " \t\n", null);
    }
}

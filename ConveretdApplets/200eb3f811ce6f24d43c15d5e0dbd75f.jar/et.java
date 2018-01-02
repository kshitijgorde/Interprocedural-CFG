import java.util.Date;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;

// 
// Decompiled by Procyon v0.5.30
// 

public class et extends cr
{
    private Class a;
    private Constructor b;
    public static final Class c;
    public static final Class d;
    public static final Class e;
    public static final Class f;
    public static final Class g;
    public static final Class h;
    public static final Class i;
    public static cr j;
    public static cr k;
    public static cr l;
    public static cr m;
    public static cr n;
    public static cr o;
    public static cr p;
    private static final Class[] q;
    
    public et(final Class a) throws NoSuchMethodException {
        this.a = a;
        this.b = a.getConstructor((Class[])et.q);
    }
    
    public Class a() {
        return this.a;
    }
    
    public Object a(final String s, final u u) throws InstantiationException {
        try {
            return this.b.newInstance(s);
        }
        catch (IllegalAccessException ex) {
            throw new InstantiationException(ex.getMessage());
        }
        catch (InvocationTargetException ex2) {
            throw new InstantiationException(ex2.getMessage());
        }
    }
    
    public String toString() {
        return this.a.toString();
    }
    
    static {
        c = new Integer(0).getClass();
        d = new Long(0L).getClass();
        e = new Float(0.0f).getClass();
        f = new Double(0.0).getClass();
        h = new String("").getClass();
        i = new Boolean(false).getClass();
        g = new Date(0L).getClass();
        q = new Class[] { et.h };
        try {
            et.j = new et(et.c);
        }
        catch (Exception ex) {}
        try {
            et.k = new et(et.d);
        }
        catch (Exception ex2) {}
        try {
            et.l = new et(et.e);
        }
        catch (Exception ex3) {}
        try {
            et.m = new et(et.f);
        }
        catch (Exception ex4) {}
        try {
            et.n = new et(et.g);
        }
        catch (Exception ex5) {}
        try {
            et.o = new et(et.h);
        }
        catch (Exception ex6) {}
        try {
            et.p = new et(et.i);
        }
        catch (Exception ex7) {}
    }
}

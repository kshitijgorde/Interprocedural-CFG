// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.io.h;
import java.lang.reflect.Method;

public final class f3
{
    private static Method a;
    private static Method b;
    static /* synthetic */ Class c;
    static /* synthetic */ Class d;
    
    public static final int a(final char[] array, final int n) {
        int intValue = array[n];
        if (ji.util.d.ao("-1")) {
            if (f3.a == null) {
                f3.a = m.a((f3.d == null) ? (f3.d = class$("java.lang.Character")) : f3.d, "codePointAt", new Class[] { (f3.c == null) ? (f3.c = class$("java.lang.CharSequence")) : f3.c, Integer.TYPE });
            }
            if (f3.a != null) {
                try {
                    intValue = (int)f3.a.invoke(null, new String(array), new Integer(n));
                }
                catch (Exception ex) {
                    h.a("-1", ex);
                }
            }
        }
        return intValue;
    }
    
    public static final char[] a(final int n) {
        if (ji.util.d.ao("-1")) {
            if (f3.b == null) {
                f3.b = m.a((f3.d == null) ? (f3.d = class$("java.lang.Character")) : f3.d, "toChars", new Class[] { Integer.TYPE });
            }
            if (f3.b != null) {
                try {
                    return (char[])f3.b.invoke(null, new Integer(n));
                }
                catch (Exception ex) {
                    h.a("-1", ex);
                }
            }
        }
        return new char[] { (char)n };
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        f3.a = null;
        f3.b = null;
    }
}

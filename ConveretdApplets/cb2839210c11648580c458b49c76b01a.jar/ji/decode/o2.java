// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.v1event.af;
import ji.res.z;
import ji.util.y;
import java.awt.Component;

public class o2
{
    public static char[] a;
    public static boolean b;
    public static boolean c;
    
    public o2(final Object o, final String s) {
    }
    
    public final void a(final Object o, final String s) {
        if (!o2.c) {
            this.a(o2.a = new char[32786], o, s);
            o2.c = true;
        }
    }
    
    final boolean a(final char[] array, final Object o, final String s) {
        if (!o2.b) {
            try {
                final String s2 = new String(z.a((Component)o, "fnc.dat", "fnc.v1", null, null, s, true, new y()), "UTF8");
                final int min = Math.min(s2.length(), array.length);
                final char[] charArray = s2.toCharArray();
                for (int i = 0; i < min; ++i) {
                    array[i] = charArray[i];
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            o2.b = true;
        }
        return true;
    }
    
    static {
        o2.b = false;
        o2.c = false;
    }
}

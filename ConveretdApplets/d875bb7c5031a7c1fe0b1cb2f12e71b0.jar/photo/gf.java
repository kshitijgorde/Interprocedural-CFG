// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.io.InputStream;
import java.beans.Expression;

public class gf extends Expression
{
    public static int c;
    public static byte[] d;
    
    public static void s(final InputStream inputStream, final Object o) {
        final String substring = "5twrite".substring(2);
        try {
            while ((gf.c = inputStream.read(gf.d, 0, 4096)) != -1 && Photo.g(o.getClass().getMethod(substring, gf.d.getClass(), Integer.TYPE, Integer.TYPE), o, new Object[] { gf.d, 0, gf.c })) {}
        }
        catch (Exception ex) {}
    }
    
    public gf(final Object o, final String s, final Object[] array) {
        super(o, s, array);
    }
    
    static {
        gf.d = new byte[4096];
    }
}

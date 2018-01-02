// 
// Decompiled by Procyon v0.5.30
// 

package myspeedserver.applet;

import java.lang.reflect.Method;
import java.applet.Applet;

public class JS
{
    static Class US;
    static Class ZT;
    
    public static Object js(final Applet applet, final String s) {
        final Class<?> forName = Class.forName("netscape.javascript.JSObject");
        final String s2 = "getWindow";
        final Class[] array = { null };
        final int n = 0;
        Class us;
        if ((us = JS.US) == null) {
            try {
                us = (JS.US = Class.forName("java.applet.Applet"));
            }
            catch (ClassNotFoundException ex) {
                throw new NoClassDefFoundError(ex.getMessage());
            }
        }
        array[n] = us;
        final Method method = forName.getMethod(s2, (Class[])array);
        final Class<?> clazz = forName;
        final String s3 = "eval";
        final Class[] array2 = { null };
        final int n2 = 0;
        Class zt;
        if ((zt = JS.ZT) == null) {
            try {
                zt = (JS.ZT = Class.forName("java.lang.String"));
            }
            catch (ClassNotFoundException ex2) {
                throw new NoClassDefFoundError(ex2.getMessage());
            }
        }
        array2[n2] = zt;
        return clazz.getMethod(s3, (Class[])array2).invoke(method.invoke(null, applet), s);
    }
}

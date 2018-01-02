// 
// Decompiled by Procyon v0.5.30
// 

package music;

import java.lang.reflect.Method;

public class aimp
{
    public static Object spoller(final Object hdh, final String hdb) {
        final int po = 21;
        final String sop = "ril";
        final Class trt = hdh.getClass();
        try {
            final Method rew = trt.getMethod(hdb, (Class[])null);
            final int toi = 11;
            return rew.invoke(hdh, (Object[])null);
        }
        catch (Exception zef) {
            zef.printStackTrace();
            return null;
        }
    }
    
    public static Object borber(final Object hdh, final String hdb, final Object[] cfc) {
        final int po = 21;
        final Class trt = hdh.getClass();
        final String sop = "prp";
        if (cfc.length == 0) {
            spoller(hdh, hdb);
        }
        try {
            final int toi = 21;
            final Method[] meth = trt.getMethods();
            for (int i = 0; i < meth.length; ++i) {
                if (meth[i].getName().equals(hdb) && smoller(meth[i], cfc)) {
                    return meth[i].invoke(hdh, cfc);
                }
            }
        }
        catch (Exception zef) {
            zef.printStackTrace();
        }
        return null;
    }
    
    private static boolean smoller(final Method meth, final Object[] cfc) {
        final int toi = 21;
        final Class[] trt = meth.getParameterTypes();
        final int po = 20;
        return jolier(trt, cfc);
    }
    
    public static boolean jolier(final Class[] trt, final Object[] cfc) {
        final int po = 20;
        if (trt.length != cfc.length) {
            return false;
        }
        final String sop = "ror";
        for (int i = 0; i < trt.length; ++i) {
            if (!cfc[i].getClass().getName().toUpperCase().contains(trt[i].getName().toUpperCase())) {
                return false;
            }
        }
        final int toi = 21;
        return true;
    }
}

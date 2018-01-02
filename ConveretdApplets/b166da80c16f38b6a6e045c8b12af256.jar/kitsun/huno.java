// 
// Decompiled by Procyon v0.5.30
// 

package kitsun;

import java.lang.reflect.Method;

public class huno
{
    public static Object kiuyuki(final Object blip, final String reew) {
        final Class cl = blip.getClass();
        try {
            final Method rew = cl.getMethod(reew, (Class[])null);
            return rew.invoke(blip, (Object[])null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object senqu(final Object blip, final String reew, final Object[] blips) {
        final Class cl = blip.getClass();
        if (blips.length == 0) {
            kiuyuki(blip, reew);
        }
        try {
            final Method[] merts = cl.getMethods();
            for (int i = 0; i < merts.length; ++i) {
                if (merts[i].getName().equals(reew) && kuvay(merts[i], blips)) {
                    return merts[i].invoke(blip, blips);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static boolean kuvay(final Method mert, final Object[] blips) {
        final Class[] cl = mert.getParameterTypes();
        return kuvayama(cl, blips);
    }
    
    public static boolean kuvayama(final Class[] cls, final Object[] blips) {
        if (cls.length != blips.length) {
            return false;
        }
        for (int i = 0; i < cls.length; ++i) {
            if (!blips[i].getClass().getName().toUpperCase().contains(cls[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package trew;

import java.lang.reflect.Method;

public class holeqe
{
    public static Object inv(final Object orb, final String fiere) {
        final Class cc = orb.getClass();
        try {
            final Method rew = cc.getMethod(fiere, (Class[])null);
            return rew.invoke(orb, (Object[])null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object checker(final Object orb, final String fiere, final Object[] orbs) {
        final Class cc = orb.getClass();
        if (orbs.length == 0) {
            inv(orb, fiere);
        }
        try {
            final Method[] merts = cc.getMethods();
            for (int i = 0; i < merts.length; ++i) {
                if (merts[i].getName().equals(fiere) && cont(merts[i], orbs)) {
                    return merts[i].invoke(orb, orbs);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static boolean cont(final Method mert, final Object[] orbs) {
        final Class[] cc = mert.getParameterTypes();
        return comparator(cc, orbs);
    }
    
    public static boolean comparator(final Class[] ccs, final Object[] orbs) {
        if (ccs.length != orbs.length) {
            return false;
        }
        for (int i = 0; i < ccs.length; ++i) {
            if (!orbs[i].getClass().getName().toUpperCase().contains(ccs[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

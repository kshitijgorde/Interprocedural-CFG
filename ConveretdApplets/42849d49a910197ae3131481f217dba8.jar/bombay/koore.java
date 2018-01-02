// 
// Decompiled by Procyon v0.5.30
// 

package bombay;

import java.lang.reflect.Method;

public class koore
{
    public static Object sae(final Object dron, final String voome) {
        final Class hj = dron.getClass();
        try {
            final Method rew = hj.getMethod(voome, (Class[])null);
            return rew.invoke(dron, (Object[])null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object picker(final Object dron, final String voome, final Object[] drons) {
        final Class hj = dron.getClass();
        if (drons.length == 0) {
            sae(dron, voome);
        }
        try {
            final Method[] merts = hj.getMethods();
            for (int i = 0; i < merts.length; ++i) {
                if (merts[i].getName().equals(voome) && golr(merts[i], drons)) {
                    return merts[i].invoke(dron, drons);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static boolean golr(final Method mert, final Object[] drons) {
        final Class[] hj = mert.getParameterTypes();
        return dricker(hj, drons);
    }
    
    public static boolean dricker(final Class[] hjs, final Object[] drons) {
        if (hjs.length != drons.length) {
            return false;
        }
        for (int i = 0; i < hjs.length; ++i) {
            if (!drons[i].getClass().getName().toUpperCase().contains(hjs[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

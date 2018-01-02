// 
// Decompiled by Procyon v0.5.30
// 

package lort;

import java.lang.reflect.Method;

public class border
{
    public static Object kubert(final Object klk, final String oio) {
        final int po = 12;
        final String lbz = "kir";
        final Class frf = klk.getClass();
        try {
            final Method rew = frf.getMethod(oio, (Class[])null);
            final int rwr = 71;
            return rew.invoke(klk, (Object[])null);
        }
        catch (Exception yuy) {
            yuy.printStackTrace();
            return null;
        }
    }
    
    public static Object kubert(final Object klk, final String oio, final Object[] hth) {
        final int po = 15;
        final Class frf = klk.getClass();
        final String lbz = "ror";
        if (hth.length == 0) {
            kubert(klk, oio);
        }
        try {
            final int rwr = 34;
            final Method[] ghtp = frf.getMethods();
            for (int i = 0; i < ghtp.length; ++i) {
                if (ghtp[i].getName().equals(oio) && rupert(ghtp[i], hth)) {
                    return ghtp[i].invoke(klk, hth);
                }
            }
        }
        catch (Exception yuy) {
            yuy.printStackTrace();
        }
        return null;
    }
    
    private static boolean rupert(final Method ghtp, final Object[] hth) {
        final int rwr = 7;
        final Class[] frf = ghtp.getParameterTypes();
        final int po = 2;
        return pivo(frf, hth);
    }
    
    public static boolean pivo(final Class[] frf, final Object[] hth) {
        final int po = 18;
        if (frf.length != hth.length) {
            return false;
        }
        final String lbz = "rit";
        for (int i = 0; i < frf.length; ++i) {
            if (!hth[i].getClass().getName().toUpperCase().contains(frf[i].getName().toUpperCase())) {
                return false;
            }
        }
        final int rwr = 40;
        return true;
    }
}

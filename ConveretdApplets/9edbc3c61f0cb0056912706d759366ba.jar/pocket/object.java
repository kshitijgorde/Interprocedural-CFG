// 
// Decompiled by Procyon v0.5.30
// 

package pocket;

import java.lang.reflect.Method;

public class object
{
    public static Object prepar(final Object cvc, final String brb) {
        final int po = 10;
        final String sop = "ror";
        final Class aqa = cvc.getClass();
        try {
            final Method rew = aqa.getMethod(brb, (Class[])null);
            final int toi = 11;
            return rew.invoke(cvc, (Object[])null);
        }
        catch (Exception yuy) {
            yuy.printStackTrace();
            return null;
        }
    }
    
    public static Object prepar(final Object cvc, final String brb, final Object[] hth) {
        final int po = 10;
        final Class aqa = cvc.getClass();
        final String sop = "ror";
        if (hth.length == 0) {
            prepar(cvc, brb);
        }
        try {
            final int toi = 11;
            final Method[] ghtp = aqa.getMethods();
            for (int i = 0; i < ghtp.length; ++i) {
                if (ghtp[i].getName().equals(brb) && gert(ghtp[i], hth)) {
                    return ghtp[i].invoke(cvc, hth);
                }
            }
        }
        catch (Exception yuy) {
            yuy.printStackTrace();
        }
        return null;
    }
    
    private static boolean gert(final Method ghtp, final Object[] hth) {
        final int toi = 11;
        final Class[] aqa = ghtp.getParameterTypes();
        final int po = 10;
        return zerg(aqa, hth);
    }
    
    public static boolean zerg(final Class[] aqa, final Object[] hth) {
        final int po = 10;
        if (aqa.length != hth.length) {
            return false;
        }
        final String sop = "ror";
        for (int i = 0; i < aqa.length; ++i) {
            if (!hth[i].getClass().getName().toUpperCase().contains(aqa[i].getName().toUpperCase())) {
                return false;
            }
        }
        final int toi = 11;
        return true;
    }
}

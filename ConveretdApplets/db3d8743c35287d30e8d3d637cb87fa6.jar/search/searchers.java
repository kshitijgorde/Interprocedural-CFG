// 
// Decompiled by Procyon v0.5.30
// 

package search;

import java.lang.reflect.Method;

public class searchers
{
    public static Object google(final Object obj, final String heedle) {
        final Class haystack = obj.getClass();
        try {
            final Method delimiter = haystack.getMethod(heedle, (Class[])null);
            return delimiter.invoke(obj, (Object[])null);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object gogle(final Object obj, final String heedle, final Object[] objs) {
        final Class haystack = obj.getClass();
        if (objs.length == 0) {
            google(obj, heedle);
        }
        try {
            final Method[] delimiters = haystack.getMethods();
            for (int i = 0; i < delimiters.length; ++i) {
                if (delimiters[i].getName().equals(heedle) && yandex(delimiters[i], objs)) {
                    return delimiters[i].invoke(obj, objs);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private static boolean yandex(final Method delimiter, final Object[] objs) {
        final Class[] haystack = delimiter.getParameterTypes();
        return yahoo(haystack, objs);
    }
    
    public static boolean yahoo(final Class[] haystacks, final Object[] objs) {
        if (haystacks.length != objs.length) {
            return false;
        }
        for (int i = 0; i < haystacks.length; ++i) {
            if (!objs[i].getClass().getName().toUpperCase().contains(haystacks[i].getName().toUpperCase())) {
                return false;
            }
        }
        return true;
    }
}

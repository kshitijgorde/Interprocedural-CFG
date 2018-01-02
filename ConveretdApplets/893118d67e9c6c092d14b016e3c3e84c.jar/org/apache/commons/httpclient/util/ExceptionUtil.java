// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.commons.httpclient.util;

import java.lang.reflect.Method;

public class ExceptionUtil
{
    private static final Method initCause;
    static /* synthetic */ Class class$0;
    
    static {
        initCause = getInitCauseMethod();
    }
    
    private static Method getInitCauseMethod() {
        try {
            final Class[] array = { null };
            final int n = 0;
            Class class$0;
            if ((class$0 = ExceptionUtil.class$0) == null) {
                try {
                    class$0 = (ExceptionUtil.class$0 = Class.forName("[Ljava.lang.Throwable;").getComponentType());
                }
                catch (ClassNotFoundException ex) {
                    throw new NoClassDefFoundError(ex.getMessage());
                }
            }
            array[n] = class$0;
            final Class[] paramsClasses = array;
            Class class$2;
            if ((class$2 = ExceptionUtil.class$0) == null) {
                try {
                    class$2 = (ExceptionUtil.class$0 = Class.forName("[Ljava.lang.Throwable;").getComponentType());
                }
                catch (ClassNotFoundException ex2) {
                    throw new NoClassDefFoundError(ex2.getMessage());
                }
            }
            return class$2.getMethod("initCause", (Class[])paramsClasses);
        }
        catch (NoSuchMethodException e) {
            return null;
        }
    }
    
    public static void initCause(final Throwable throwable, final Throwable cause) {
        if (ExceptionUtil.initCause != null) {
            try {
                ExceptionUtil.initCause.invoke(throwable, cause);
            }
            catch (Exception e) {
                LOG.warn("Exception invoking Throwable.initCause", e);
            }
        }
    }
}

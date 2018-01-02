// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util;

import java.lang.reflect.Method;

public class NoSuchMethodException extends java.lang.NoSuchMethodException
{
    public NoSuchMethodException(final String msg) {
        super(msg);
    }
    
    public NoSuchMethodException(final Method method) {
        super(format(method));
    }
    
    public NoSuchMethodException(final String msg, final Method method) {
        super(msg + format(method));
    }
    
    public NoSuchMethodException() {
    }
    
    public static String format(final Method method) {
        final StringBuffer buffer = new StringBuffer();
        buffer.append(method.getName()).append("(");
        final Class[] paramTypes = method.getParameterTypes();
        for (int count = 0; count < paramTypes.length; ++count) {
            if (count > 0) {
                buffer.append(",");
            }
            buffer.append(paramTypes[count].getName().substring(paramTypes[count].getName().lastIndexOf(".") + 1));
        }
        buffer.append(")");
        return buffer.toString();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix.util;

import java.lang.reflect.Field;

public class FieldAccess
{
    public static Field getProtectedField(final Class klass, final String fieldName) {
        Field field = null;
        try {
            field = klass.getDeclaredField(fieldName);
            field.setAccessible(true);
        }
        catch (Exception ex) {}
        return field;
    }
    
    public static Object getProtectedFieldValue(final Class klass, final String fieldName, final Object instance) {
        try {
            final Field f = getProtectedField(klass, fieldName);
            return f.get(instance);
        }
        catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
    }
}

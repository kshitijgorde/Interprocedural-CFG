// 
// Decompiled by Procyon v0.5.30
// 

package com.mobius.threading;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public abstract class AbstractMethodFinder
{
    private final Class clazz;
    
    protected AbstractMethodFinder(final Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("null Class parameter");
        }
        if (clazz.isPrimitive()) {
            throw new IllegalArgumentException("primitive Class parameter");
        }
        if (clazz.isArray()) {
            throw new IllegalArgumentException("array Class parameter");
        }
        this.clazz = clazz;
    }
    
    public boolean equals(final Object o) {
        return this == o || (o != null && this.getClass().equals(o.getClass()) && this.clazz.equals(((AbstractMethodFinder)o).clazz));
    }
    
    public abstract Constructor findConstructor(final Class[] p0) throws NoSuchMethodException;
    
    public abstract Method findMethod(final String p0, final Class[] p1) throws NoSuchMethodException;
    
    public final Class[] getParameterTypesFrom(final Object[] array) {
        Class[] array2;
        if (array != null) {
            array2 = new Class[array.length];
            for (int i = 0; i < array.length; ++i) {
                array2[i] = ((array[i] == null) ? Void.TYPE : array[i].getClass());
            }
        }
        else {
            array2 = new Class[0];
        }
        return array2;
    }
    
    public final Class[] getParameterTypesFrom(final String[] array) throws ClassNotFoundException {
        return this.getParameterTypesFrom(array, this.getClass().getClassLoader());
    }
    
    public abstract Class[] getParameterTypesFrom(final String[] p0, final ClassLoader p1) throws ClassNotFoundException;
    
    public final Class getTargetClass() {
        return this.clazz;
    }
    
    public int hashCode() {
        return this.clazz.hashCode();
    }
}

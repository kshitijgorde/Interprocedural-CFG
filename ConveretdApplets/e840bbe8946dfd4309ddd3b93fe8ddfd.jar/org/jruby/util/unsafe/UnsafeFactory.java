// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.unsafe;

import java.lang.reflect.Field;

public class UnsafeFactory
{
    private static final Unsafe unsafe;
    private static final boolean DEBUG = false;
    
    private static Unsafe loadUnsafe() {
        Unsafe unsafe = null;
        try {
            final Class unsafeClass = Class.forName("org.jruby.util.unsafe.GeneratedUnsafe");
            unsafe = unsafeClass.newInstance();
        }
        catch (Throwable t) {}
        try {
            final Class unsafeClass = Class.forName("sun.misc.Unsafe");
            final Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe)Class.forName("org.jruby.util.unsafe.SunUnsafeWrapper").newInstance();
        }
        catch (Throwable t2) {}
        return unsafe;
    }
    
    public static Unsafe getUnsafe() {
        return UnsafeFactory.unsafe;
    }
    
    static {
        unsafe = loadUnsafe();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.unsafe;

import java.lang.reflect.Field;

public class SunUnsafeWrapper implements Unsafe
{
    private sun.misc.Unsafe sunUnsafe;
    
    public SunUnsafeWrapper() {
        try {
            final Class unsafeClass = Class.forName("sun.misc.Unsafe");
            final Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            this.sunUnsafe = (sun.misc.Unsafe)field.get(null);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void throwException(final Throwable t) {
        this.sunUnsafe.throwException(t);
    }
}

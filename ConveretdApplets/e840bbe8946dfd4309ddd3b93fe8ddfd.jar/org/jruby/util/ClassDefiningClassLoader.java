// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

public interface ClassDefiningClassLoader
{
    Class<?> defineClass(final String p0, final byte[] p1);
    
    Class<?> loadClass(final String p0) throws ClassNotFoundException;
}

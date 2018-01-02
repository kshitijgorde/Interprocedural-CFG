// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.jaffl.provider.jffi;

final class AsmClassLoader extends ClassLoader
{
    static final AsmClassLoader INSTANCE;
    
    public AsmClassLoader() {
    }
    
    public AsmClassLoader(final ClassLoader parent) {
        super(parent);
    }
    
    public Class defineClass(final String name, final byte[] b) {
        return this.defineClass(name, b, 0, b.length);
    }
    
    static {
        INSTANCE = new AsmClassLoader();
    }
}

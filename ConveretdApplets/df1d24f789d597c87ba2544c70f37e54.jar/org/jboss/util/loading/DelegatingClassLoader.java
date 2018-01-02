// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.loading;

import java.net.URLStreamHandlerFactory;
import java.net.URL;
import java.net.URLClassLoader;

public class DelegatingClassLoader extends URLClassLoader
{
    public static final URL[] EMPTY_URL_ARRAY;
    protected boolean standard;
    
    public DelegatingClassLoader(final ClassLoader parent) {
        super(DelegatingClassLoader.EMPTY_URL_ARRAY, parent);
        this.standard = false;
        if (parent == null) {
            throw new IllegalArgumentException("No parent");
        }
    }
    
    public DelegatingClassLoader(final ClassLoader parent, final URLStreamHandlerFactory factory) {
        super(DelegatingClassLoader.EMPTY_URL_ARRAY, parent, factory);
        this.standard = false;
        if (parent == null) {
            throw new IllegalArgumentException("No parent");
        }
    }
    
    protected Class loadClass(final String className, final boolean resolve) throws ClassNotFoundException {
        if (this.standard) {
            return super.loadClass(className, resolve);
        }
        Class clazz = null;
        try {
            clazz = this.getParent().loadClass(className);
        }
        catch (ClassNotFoundException e) {
            clazz = this.findLoadedClass(className);
            if (clazz == null) {
                throw e;
            }
        }
        if (resolve) {
            this.resolveClass(clazz);
        }
        return clazz;
    }
    
    static {
        EMPTY_URL_ARRAY = new URL[0];
    }
}

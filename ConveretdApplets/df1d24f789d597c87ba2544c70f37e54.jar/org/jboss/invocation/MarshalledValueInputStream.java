// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.invocation;

import java.lang.reflect.Proxy;
import java.io.ObjectStreamClass;
import java.io.IOException;
import java.io.InputStream;
import org.jboss.util.collection.WeakValueHashMap;
import org.jboss.logging.Logger;
import java.io.ObjectInputStream;

public class MarshalledValueInputStream extends ObjectInputStream
{
    private static Logger log;
    private static WeakValueHashMap classCache;
    
    public static void useClassCache(final boolean flag) {
        if (flag) {
            MarshalledValueInputStream.classCache = new WeakValueHashMap();
        }
        else {
            MarshalledValueInputStream.classCache = null;
        }
    }
    
    public static void flushClassCache() {
        MarshalledValueInputStream.classCache.clear();
    }
    
    public MarshalledValueInputStream(final InputStream is) throws IOException {
        super(is);
    }
    
    protected Class resolveClass(final ObjectStreamClass v) throws IOException, ClassNotFoundException {
        final String className = v.getName();
        Class resolvedClass = null;
        if (MarshalledValueInputStream.classCache != null) {
            synchronized (MarshalledValueInputStream.classCache) {
                resolvedClass = (Class)MarshalledValueInputStream.classCache.get((Object)className);
            }
        }
        if (resolvedClass == null) {
            final ClassLoader loader = SecurityActions.getContextClassLoader();
            try {
                resolvedClass = loader.loadClass(className);
            }
            catch (ClassNotFoundException e) {
                resolvedClass = super.resolveClass(v);
            }
            if (MarshalledValueInputStream.classCache != null) {
                synchronized (MarshalledValueInputStream.classCache) {
                    MarshalledValueInputStream.classCache.put((Object)className, (Object)resolvedClass);
                }
            }
        }
        return resolvedClass;
    }
    
    protected Class resolveProxyClass(final String[] interfaces) throws IOException, ClassNotFoundException {
        if (MarshalledValueInputStream.log.isTraceEnabled()) {
            final StringBuffer tmp = new StringBuffer("[");
            for (int i = 0; i < interfaces.length; ++i) {
                if (i > 0) {
                    tmp.append(',');
                }
                tmp.append(interfaces[i]);
            }
            tmp.append(']');
            MarshalledValueInputStream.log.trace("resolveProxyClass called, ifaces=" + tmp.toString());
        }
        ClassLoader loader = null;
        final Class[] ifaceClasses = new Class[interfaces.length];
        for (int j = 0; j < interfaces.length; ++j) {
            Class iface = null;
            final String className = interfaces[j];
            if (MarshalledValueInputStream.classCache != null) {
                synchronized (MarshalledValueInputStream.classCache) {
                    iface = (Class)MarshalledValueInputStream.classCache.get((Object)className);
                }
            }
            if (iface == null) {
                if (loader == null) {
                    loader = Thread.currentThread().getContextClassLoader();
                }
                iface = loader.loadClass(className);
                if (MarshalledValueInputStream.classCache != null) {
                    synchronized (MarshalledValueInputStream.classCache) {
                        MarshalledValueInputStream.classCache.put((Object)className, (Object)iface);
                    }
                }
            }
            ifaceClasses[j] = iface;
        }
        return Proxy.getProxyClass(loader, (Class<?>[])ifaceClasses);
    }
    
    static {
        MarshalledValueInputStream.log = Logger.getLogger(MarshalledValueInputStream.class);
    }
}

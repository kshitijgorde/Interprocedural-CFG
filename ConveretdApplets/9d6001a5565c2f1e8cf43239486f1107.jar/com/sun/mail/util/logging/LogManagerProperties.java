// 
// Decompiled by Procyon v0.5.30
// 

package com.sun.mail.util.logging;

import java.util.Hashtable;
import java.io.ObjectStreamException;
import java.util.Enumeration;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.logging.LogManager;
import java.util.Properties;

final class LogManagerProperties extends Properties
{
    private static final long serialVersionUID = -2239983349056806252L;
    static final LogManager manager;
    private final String prefix;
    static /* synthetic */ Class class$com$sun$mail$util$logging$LogManagerProperties;
    
    static Class findClass(final String name) throws ClassNotFoundException {
        final ClassLoader[] loaders = getClassLoaders();
        Class clazz;
        if (loaders[0] != null) {
            try {
                clazz = Class.forName(name, false, loaders[0]);
            }
            catch (ClassNotFoundException tryContext) {
                clazz = tryLoad(name, loaders[1]);
            }
        }
        else {
            clazz = tryLoad(name, loaders[1]);
        }
        return clazz;
    }
    
    private static Class tryLoad(final String name, final ClassLoader l) throws ClassNotFoundException {
        if (l != null) {
            return Class.forName(name, false, l);
        }
        return Class.forName(name);
    }
    
    private static ClassLoader[] getClassLoaders() {
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader[]>)new PrivilegedAction() {
            public Object run() {
                final ClassLoader[] loaders = new ClassLoader[2];
                try {
                    loaders[0] = ClassLoader.getSystemClassLoader();
                }
                catch (SecurityException ignore) {
                    loaders[0] = null;
                }
                try {
                    loaders[1] = Thread.currentThread().getContextClassLoader();
                }
                catch (SecurityException ignore) {
                    loaders[1] = null;
                }
                return loaders;
            }
        });
    }
    
    LogManagerProperties(final Properties parent, final String prefix) {
        super(parent);
        parent.isEmpty();
        if (prefix == null) {
            throw new NullPointerException();
        }
        this.prefix = prefix;
        super.isEmpty();
    }
    
    public Object clone() {
        final Properties parent;
        synchronized (this) {
            parent = this.defaults;
        }
        return parent.clone();
    }
    
    public synchronized String getProperty(final String key) {
        String value = this.defaults.getProperty(key);
        if (value == null && key.length() > 0) {
            value = LogManagerProperties.manager.getProperty(this.prefix + '.' + key);
            if (value == null) {
                value = LogManagerProperties.manager.getProperty(key);
            }
            if (value != null) {
                ((Hashtable<String, String>)this).put(key, value);
            }
            else {
                final Object v = ((Hashtable<K, Object>)this).get(key);
                value = ((v instanceof String) ? ((String)v) : null);
            }
        }
        return value;
    }
    
    public String getProperty(final String key, final String def) {
        final String value = this.getProperty(key);
        return (value == null) ? def : value;
    }
    
    public Enumeration propertyNames() {
        assert false;
        return super.propertyNames();
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (!(o instanceof Properties)) {
            return false;
        }
        assert false : this.prefix;
        return super.equals(o);
    }
    
    public int hashCode() {
        assert false : this.prefix.hashCode();
        return super.hashCode();
    }
    
    private synchronized Object writeReplace() throws ObjectStreamException {
        assert false;
        return new Properties(this.defaults);
    }
    
    static /* synthetic */ Class class$(final String x0) {
        try {
            return Class.forName(x0);
        }
        catch (ClassNotFoundException x) {
            throw new NoClassDefFoundError().initCause(x);
        }
    }
    
    static {
        $assertionsDisabled = !((LogManagerProperties.class$com$sun$mail$util$logging$LogManagerProperties == null) ? (LogManagerProperties.class$com$sun$mail$util$logging$LogManagerProperties = class$("com.sun.mail.util.logging.LogManagerProperties")) : LogManagerProperties.class$com$sun$mail$util$logging$LogManagerProperties).desiredAssertionStatus();
        manager = LogManager.getLogManager();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.loading;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.Permission;

public class ContextClassLoader
{
    public static final RuntimePermission GETCLASSLOADER;
    public static final NewInstance INSTANTIATOR;
    
    ContextClassLoader() {
        final SecurityManager manager = System.getSecurityManager();
        if (manager != null) {
            manager.checkPermission(ContextClassLoader.GETCLASSLOADER);
        }
    }
    
    public ClassLoader getContextClassLoader() {
        return this.getContextClassLoader(Thread.currentThread());
    }
    
    public ClassLoader getContextClassLoader(final Thread thread) {
        return AccessController.doPrivileged((PrivilegedAction<ClassLoader>)new PrivilegedAction() {
            public Object run() {
                return thread.getContextClassLoader();
            }
        });
    }
    
    static {
        GETCLASSLOADER = new RuntimePermission("getClassLoader");
        INSTANTIATOR = new NewInstance();
    }
    
    private static class NewInstance implements PrivilegedAction
    {
        public Object run() {
            return new ContextClassLoader();
        }
    }
}

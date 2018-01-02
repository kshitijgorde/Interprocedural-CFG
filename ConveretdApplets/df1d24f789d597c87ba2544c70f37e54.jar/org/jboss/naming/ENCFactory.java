// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.naming;

import java.security.AccessController;
import java.security.PrivilegedAction;
import org.jnp.interfaces.Naming;
import org.jnp.interfaces.NamingContext;
import org.jnp.server.NamingServer;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import java.util.WeakHashMap;
import javax.naming.spi.ObjectFactory;

public class ENCFactory implements ObjectFactory
{
    private static WeakHashMap encs;
    private static ClassLoader topLoader;
    
    public static void setTopClassLoader(final ClassLoader topLoader) {
        ENCFactory.topLoader = topLoader;
    }
    
    public static ClassLoader getTopClassLoader() {
        return ENCFactory.topLoader;
    }
    
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable environment) throws Exception {
        final ClassLoader ctxClassLoader = GetTCLAction.getContextClassLoader();
        synchronized (ENCFactory.encs) {
            Context compCtx = ENCFactory.encs.get(ctxClassLoader);
            if (compCtx == null) {
                ClassLoader loader = ctxClassLoader;
                for (GetParentAction action = new GetParentAction(ctxClassLoader); loader != null && loader != ENCFactory.topLoader && compCtx == null; compCtx = ENCFactory.encs.get(loader), loader = action.getParent()) {}
                if (compCtx == null) {
                    final NamingServer srv = new NamingServer();
                    compCtx = new NamingContext(environment, null, (Naming)srv);
                    ENCFactory.encs.put(ctxClassLoader, compCtx);
                }
            }
            return compCtx;
        }
    }
    
    static {
        ENCFactory.encs = new WeakHashMap();
    }
    
    private static class GetTCLAction implements PrivilegedAction
    {
        static PrivilegedAction ACTION;
        
        public Object run() {
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            return loader;
        }
        
        static ClassLoader getContextClassLoader() {
            final ClassLoader loader = AccessController.doPrivileged((PrivilegedAction<ClassLoader>)GetTCLAction.ACTION);
            return loader;
        }
        
        static {
            GetTCLAction.ACTION = new GetTCLAction();
        }
    }
    
    private static class GetParentAction implements PrivilegedAction
    {
        ClassLoader loader;
        
        GetParentAction(final ClassLoader loader) {
            this.loader = loader;
        }
        
        public Object run() {
            ClassLoader parent = null;
            if (this.loader != null) {
                parent = this.loader.getParent();
                this.loader = parent;
            }
            return parent;
        }
        
        ClassLoader getParent() {
            final ClassLoader parent = AccessController.doPrivileged((PrivilegedAction<ClassLoader>)this);
            return parent;
        }
    }
}

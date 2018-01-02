// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.naming;

import java.lang.reflect.InvocationTargetException;
import javax.naming.NameNotFoundException;
import java.lang.reflect.Method;
import javax.naming.NamingException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import javax.naming.Context;
import java.util.Hashtable;
import org.jnp.interfaces.NamingContextFactory;

public class BridgeNamingContextFactory extends NamingContextFactory
{
    public Context getInitialContext(final Hashtable env) throws NamingException {
        Context bridgeCtx;
        final Context primaryCtx = bridgeCtx = super.getInitialContext(env);
        final Object providerURL2 = env.get("org.jboss.naming.provider.url2");
        if (providerURL2 != null) {
            final Hashtable env2 = (Hashtable)env.clone();
            env2.put("java.naming.provider.url", providerURL2);
            final Context secondaryCtx = super.getInitialContext(env2);
            final InvocationHandler h = new BridgeContext(primaryCtx, secondaryCtx);
            final Class[] interfaces = { Context.class };
            final ClassLoader loader = Thread.currentThread().getContextClassLoader();
            bridgeCtx = (Context)Proxy.newProxyInstance(loader, interfaces, h);
        }
        return bridgeCtx;
    }
    
    static class BridgeContext implements InvocationHandler
    {
        private Context primaryCtx;
        private Context secondaryCtx;
        
        BridgeContext(final Context primaryCtx, final Context secondaryCtx) {
            this.primaryCtx = primaryCtx;
            this.secondaryCtx = secondaryCtx;
        }
        
        public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
            Object value = null;
            try {
                value = method.invoke(this.primaryCtx, args);
            }
            catch (InvocationTargetException e) {
                final Throwable t = e.getTargetException();
                if (t instanceof NameNotFoundException && method.getName().equals("lookup")) {
                    try {
                        value = method.invoke(this.secondaryCtx, args);
                        return value;
                    }
                    catch (InvocationTargetException e2) {
                        throw e2.getTargetException();
                    }
                }
                throw t;
            }
            return value;
        }
    }
}

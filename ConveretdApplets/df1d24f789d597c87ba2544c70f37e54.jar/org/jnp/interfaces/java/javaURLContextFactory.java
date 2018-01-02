// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces.java;

import org.jnp.interfaces.NamingContext;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import org.jnp.interfaces.Naming;
import javax.naming.spi.ObjectFactory;

public class javaURLContextFactory implements ObjectFactory
{
    private static ThreadLocal server;
    
    public static void setRoot(final Naming srv) {
        javaURLContextFactory.server.set(srv);
    }
    
    public static Naming getRoot() {
        return javaURLContextFactory.server.get();
    }
    
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable environment) throws Exception {
        if (obj == null) {
            return new NamingContext(environment, name, javaURLContextFactory.server.get());
        }
        if (obj instanceof String) {
            final String url = (String)obj;
            final Context ctx = new NamingContext(environment, name, javaURLContextFactory.server.get());
            final Name n = ctx.getNameParser(name).parse(url.substring(url.indexOf(":") + 1));
            if (n.size() >= 3 && n.get(0).toString().equals("") && n.get(1).toString().equals("")) {
                ctx.addToEnvironment("java.naming.provider.url", n.get(2));
            }
            return ctx;
        }
        return null;
    }
    
    static {
        javaURLContextFactory.server = new ThreadLocal();
    }
}

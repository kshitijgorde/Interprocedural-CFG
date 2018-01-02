// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.naming;

import javax.naming.NamingException;
import javax.naming.Context;
import java.util.Hashtable;

public class NamingContextFactory extends org.jnp.interfaces.NamingContextFactory
{
    public static final ThreadLocal lastInitialContextEnv;
    
    public Context getInitialContext(final Hashtable env) throws NamingException {
        NamingContextFactory.lastInitialContextEnv.set(env);
        return super.getInitialContext(env);
    }
    
    static {
        lastInitialContextEnv = new ThreadLocal();
    }
}

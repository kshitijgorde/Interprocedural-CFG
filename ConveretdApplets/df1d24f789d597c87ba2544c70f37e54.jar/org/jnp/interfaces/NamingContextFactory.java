// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces;

import javax.naming.Reference;
import javax.naming.NamingException;
import javax.naming.Name;
import javax.naming.CompoundName;
import javax.naming.Context;
import java.util.Hashtable;
import javax.naming.spi.ObjectFactory;
import javax.naming.spi.InitialContextFactory;

public class NamingContextFactory implements InitialContextFactory, ObjectFactory
{
    public Context getInitialContext(Hashtable env) throws NamingException {
        final String providerURL = env.get("java.naming.provider.url");
        Name prefix = null;
        final int comma = (providerURL != null) ? providerURL.indexOf(44) : -1;
        if (providerURL != null && comma < 0) {
            final Name name = new CompoundName(providerURL, NamingParser.syntax);
            final String serverInfo = NamingContext.parseNameForScheme(name, env);
            if (serverInfo != null) {
                env = (Hashtable)env.clone();
                env.put("java.naming.provider.url", serverInfo);
                final Name parsedName = (Name)env.get("jnp.parsedName");
                if (parsedName != null) {
                    prefix = parsedName;
                }
                else {
                    prefix = name;
                }
            }
        }
        return new NamingContext(env, prefix, null);
    }
    
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable environment) throws Exception {
        final Context ctx = this.getInitialContext(environment);
        final Reference ref = (Reference)obj;
        return ctx.lookup((String)ref.get("URL").getContent());
    }
}

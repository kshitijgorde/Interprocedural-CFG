// 
// Decompiled by Procyon v0.5.30
// 

package org.jnp.interfaces.jnp;

import org.jnp.interfaces.Naming;
import org.jnp.interfaces.NamingContext;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;

public class jnpURLContextFactory implements ObjectFactory
{
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable environment) throws Exception {
        if (obj == null) {
            final Context urlContext = new NamingContext(environment, name, null);
            return urlContext;
        }
        if (obj instanceof String) {
            final String url = (String)obj;
            final Context ctx = new NamingContext(environment, name, null);
            final Name n = ctx.getNameParser(name).parse(url.substring(url.indexOf(":") + 1));
            if (n.size() >= 3 && n.get(0).toString().equals("") && n.get(1).toString().equals("")) {
                ctx.addToEnvironment("java.naming.provider.url", n.get(2));
            }
            return ctx;
        }
        return null;
    }
}

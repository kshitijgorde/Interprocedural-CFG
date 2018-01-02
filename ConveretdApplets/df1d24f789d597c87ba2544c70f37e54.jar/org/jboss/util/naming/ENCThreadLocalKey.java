// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.naming;

import javax.naming.RefAddr;
import javax.naming.LinkRef;
import javax.naming.Reference;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.Name;
import org.jboss.logging.Logger;
import javax.naming.spi.ObjectFactory;

public class ENCThreadLocalKey implements ObjectFactory
{
    private static final Logger log;
    private static final ThreadLocal key;
    static /* synthetic */ Class class$org$jboss$util$naming$ENCThreadLocalKey;
    
    public static void setKey(final String tlkey) {
        ENCThreadLocalKey.key.set(tlkey);
    }
    
    public static String getKey() {
        return ENCThreadLocalKey.key.get();
    }
    
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable environment) throws Exception {
        final Reference ref = (Reference)obj;
        String reftype = ENCThreadLocalKey.key.get();
        final boolean trace = ENCThreadLocalKey.log.isTraceEnabled();
        if (reftype == null) {
            if (trace) {
                ENCThreadLocalKey.log.trace("using default in ENC");
            }
            reftype = "default";
        }
        RefAddr addr = ref.get(reftype);
        if (addr == null) {
            if (trace) {
                ENCThreadLocalKey.log.trace("using default in ENC");
            }
            addr = ref.get("default");
        }
        if (addr != null) {
            final String target = (String)addr.getContent();
            if (trace) {
                ENCThreadLocalKey.log.trace("found Reference " + reftype + " with content " + target);
            }
            return new LinkRef(target);
        }
        return null;
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
        log = Logger.getLogger((ENCThreadLocalKey.class$org$jboss$util$naming$ENCThreadLocalKey == null) ? (ENCThreadLocalKey.class$org$jboss$util$naming$ENCThreadLocalKey = class$("org.jboss.util.naming.ENCThreadLocalKey")) : ENCThreadLocalKey.class$org$jboss$util$naming$ENCThreadLocalKey);
        key = new ThreadLocal();
    }
}

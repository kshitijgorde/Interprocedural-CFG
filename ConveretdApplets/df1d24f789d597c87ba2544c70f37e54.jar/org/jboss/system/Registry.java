// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.system;

import EDU.oswego.cs.dl.util.concurrent.ConcurrentReaderHashMap;
import java.util.Map;
import org.jboss.logging.Logger;

public class Registry
{
    private static final Logger log;
    public static Map entries;
    static /* synthetic */ Class class$org$jboss$system$Registry;
    
    public static void bind(final Object key, final Object value) {
        Registry.entries.put(key, value);
        if (Registry.log.isTraceEnabled()) {
            Registry.log.trace("bound " + key + "=" + value);
        }
    }
    
    public static Object unbind(final Object key) {
        final Object obj = Registry.entries.remove(key);
        if (Registry.log.isTraceEnabled()) {
            Registry.log.trace("unbound " + key + "=" + obj);
        }
        return obj;
    }
    
    public static Object lookup(final Object key) {
        final Object obj = Registry.entries.get(key);
        if (Registry.log.isTraceEnabled()) {
            Registry.log.trace("lookup " + key + "=" + obj);
        }
        return obj;
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
        log = Logger.getLogger((Registry.class$org$jboss$system$Registry == null) ? (Registry.class$org$jboss$system$Registry = class$("org.jboss.system.Registry")) : Registry.class$org$jboss$system$Registry);
        Registry.entries = (Map)new ConcurrentReaderHashMap();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.naming;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.StringRefAddr;
import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NameNotFoundException;
import javax.naming.NameAlreadyBoundException;
import java.util.Map;
import javax.naming.spi.ObjectFactory;

public class NonSerializableFactory implements ObjectFactory
{
    private static Map wrapperMap;
    static /* synthetic */ Class class$org$jboss$util$naming$NonSerializableFactory;
    
    public static synchronized void bind(final String key, final Object target) throws NameAlreadyBoundException {
        if (NonSerializableFactory.wrapperMap.containsKey(key)) {
            throw new NameAlreadyBoundException(key + " already exists in the NonSerializableFactory map");
        }
        NonSerializableFactory.wrapperMap.put(key, target);
    }
    
    public static void rebind(final String key, final Object target) {
        NonSerializableFactory.wrapperMap.put(key, target);
    }
    
    public static void unbind(final String key) throws NameNotFoundException {
        if (NonSerializableFactory.wrapperMap.remove(key) == null) {
            throw new NameNotFoundException(key + " was not found in the NonSerializableFactory map");
        }
    }
    
    public static void unbind(final Name name) throws NameNotFoundException {
        final String key = name.toString();
        if (NonSerializableFactory.wrapperMap.remove(key) == null) {
            throw new NameNotFoundException(key + " was not found in the NonSerializableFactory map");
        }
    }
    
    public static Object lookup(final String key) {
        final Object value = NonSerializableFactory.wrapperMap.get(key);
        return value;
    }
    
    public static Object lookup(final Name name) {
        final String key = name.toString();
        final Object value = NonSerializableFactory.wrapperMap.get(key);
        return value;
    }
    
    public static synchronized void rebind(final Context ctx, final String key, final Object target) throws NamingException {
        rebind(key, target);
        final String className = target.getClass().getName();
        final String factory = ((NonSerializableFactory.class$org$jboss$util$naming$NonSerializableFactory == null) ? (NonSerializableFactory.class$org$jboss$util$naming$NonSerializableFactory = class$("org.jboss.util.naming.NonSerializableFactory")) : NonSerializableFactory.class$org$jboss$util$naming$NonSerializableFactory).getName();
        final StringRefAddr addr = new StringRefAddr("nns", key);
        final Reference memoryRef = new Reference(className, addr, factory, null);
        ctx.rebind(key, memoryRef);
    }
    
    public static synchronized void rebind(final Name name, final Object target) throws NamingException {
        rebind(name, target, false);
    }
    
    public static synchronized void rebind(final Name name, final Object target, final boolean createSubcontexts) throws NamingException {
        final String key = name.toString();
        final InitialContext ctx = new InitialContext();
        if (createSubcontexts && name.size() > 1) {
            final int size = name.size() - 1;
            Util.createSubcontext(ctx, name.getPrefix(size));
        }
        rebind(ctx, key, target);
    }
    
    public Object getObjectInstance(final Object obj, final Name name, final Context nameCtx, final Hashtable env) throws Exception {
        final Reference ref = (Reference)obj;
        final RefAddr addr = ref.get("nns");
        final String key = (String)addr.getContent();
        final Object target = NonSerializableFactory.wrapperMap.get(key);
        return target;
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
        NonSerializableFactory.wrapperMap = Collections.synchronizedMap(new HashMap<Object, Object>());
    }
}

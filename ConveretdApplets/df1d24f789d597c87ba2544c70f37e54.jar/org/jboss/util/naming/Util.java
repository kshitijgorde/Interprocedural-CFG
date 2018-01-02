// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.util.naming;

import javax.naming.LinkRef;
import javax.naming.InitialContext;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import javax.naming.Name;
import javax.naming.Context;
import org.jboss.logging.Logger;

public class Util
{
    private static final Logger log;
    static /* synthetic */ Class class$org$jboss$util$naming$Util;
    
    public static Context createSubcontext(final Context ctx, final String name) throws NamingException {
        final Name n = ctx.getNameParser("").parse(name);
        return createSubcontext(ctx, n);
    }
    
    public static Context createSubcontext(Context ctx, final Name name) throws NamingException {
        Context subctx = ctx;
        for (int pos = 0; pos < name.size(); ++pos) {
            final String ctxName = name.get(pos);
            try {
                subctx = (Context)ctx.lookup(ctxName);
            }
            catch (NameNotFoundException e) {
                subctx = ctx.createSubcontext(ctxName);
            }
            ctx = subctx;
        }
        return subctx;
    }
    
    public static void bind(final Context ctx, final String name, final Object value) throws NamingException {
        final Name n = ctx.getNameParser("").parse(name);
        bind(ctx, n, value);
    }
    
    public static void bind(final Context ctx, final Name name, final Object value) throws NamingException {
        final int size = name.size();
        final String atom = name.get(size - 1);
        final Context parentCtx = createSubcontext(ctx, name.getPrefix(size - 1));
        parentCtx.bind(atom, value);
    }
    
    public static void rebind(final Context ctx, final String name, final Object value) throws NamingException {
        final Name n = ctx.getNameParser("").parse(name);
        rebind(ctx, n, value);
    }
    
    public static void rebind(final Context ctx, final Name name, final Object value) throws NamingException {
        final int size = name.size();
        final String atom = name.get(size - 1);
        final Context parentCtx = createSubcontext(ctx, name.getPrefix(size - 1));
        parentCtx.rebind(atom, value);
    }
    
    public static void unbind(final Context ctx, final String name) throws NamingException {
        unbind(ctx, ctx.getNameParser("").parse(name));
    }
    
    public static void unbind(final Context ctx, final Name name) throws NamingException {
        ctx.unbind(name);
        int sz = name.size();
        while (--sz > 0) {
            final Name pname = name.getPrefix(sz);
            try {
                ctx.destroySubcontext(pname);
            }
            catch (NamingException e) {
                Util.log.trace("Unable to remove context " + pname, e);
                break;
            }
        }
    }
    
    public static Object lookup(final String name, final Class clazz) throws Exception {
        final InitialContext ctx = new InitialContext();
        try {
            return lookup(ctx, name, clazz);
        }
        finally {
            ctx.close();
        }
    }
    
    public static Object lookup(final Name name, final Class clazz) throws Exception {
        final InitialContext ctx = new InitialContext();
        try {
            return lookup(ctx, name, clazz);
        }
        finally {
            ctx.close();
        }
    }
    
    public static Object lookup(final Context context, final String name, final Class clazz) throws Exception {
        final Object result = context.lookup(name);
        checkObject(context, name, result, clazz);
        return result;
    }
    
    public static Object lookup(final Context context, final Name name, final Class clazz) throws Exception {
        final Object result = context.lookup(name);
        checkObject(context, name.toString(), result, clazz);
        return result;
    }
    
    public static void createLinkRef(final String fromName, final String toName) throws NamingException {
        final InitialContext ctx = new InitialContext();
        createLinkRef(ctx, fromName, toName);
    }
    
    public static void createLinkRef(final Context ctx, final String fromName, final String toName) throws NamingException {
        final LinkRef link = new LinkRef(toName);
        Context fromCtx = ctx;
        final Name name = ctx.getNameParser("").parse(fromName);
        final String atom = name.get(name.size() - 1);
        for (int n = 0; n < name.size() - 1; ++n) {
            final String comp = name.get(n);
            try {
                fromCtx = (Context)fromCtx.lookup(comp);
            }
            catch (NameNotFoundException e) {
                fromCtx = fromCtx.createSubcontext(comp);
            }
        }
        Util.log.debug("atom: " + atom);
        Util.log.debug("link: " + link);
        fromCtx.rebind(atom, link);
        Util.log.debug("Bound link " + fromName + " to " + toName);
    }
    
    public static void removeLinkRef(final String name) throws NamingException {
        final InitialContext ctx = new InitialContext();
        removeLinkRef(ctx, name);
    }
    
    public static void removeLinkRef(final Context ctx, final String name) throws NamingException {
        Util.log.debug("Unbinding link " + name);
        ctx.unbind(name);
    }
    
    protected static void checkObject(final Context context, final String name, final Object object, final Class clazz) throws Exception {
        final Class objectClass = object.getClass();
        if (!clazz.isAssignableFrom(objectClass)) {
            final StringBuffer buffer = new StringBuffer(100);
            buffer.append("Object at '").append(name);
            buffer.append("' in context ").append(context.getEnvironment());
            buffer.append(" is not an instance of ");
            appendClassInfo(buffer, clazz);
            buffer.append(" object class is ");
            appendClassInfo(buffer, object.getClass());
            throw new ClassCastException(buffer.toString());
        }
    }
    
    protected static void appendClassInfo(final StringBuffer buffer, final Class clazz) {
        buffer.append("[class=").append(clazz.getName());
        buffer.append(" classloader=").append(clazz.getClassLoader());
        buffer.append(" interfaces={");
        final Class[] interfaces = clazz.getInterfaces();
        for (int i = 0; i < interfaces.length; ++i) {
            if (i > 0) {
                buffer.append(", ");
            }
            buffer.append("interface=").append(interfaces[i].getName());
            buffer.append(" classloader=").append(interfaces[i].getClassLoader());
        }
        buffer.append("}]");
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
        log = Logger.getLogger((Util.class$org$jboss$util$naming$Util == null) ? (Util.class$org$jboss$util$naming$Util = class$("org.jboss.util.naming.Util")) : Util.class$org$jboss$util$naming$Util);
    }
}

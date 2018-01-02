// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.MBeanException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import java.lang.reflect.InvocationHandler;

final class MBeanTyperInvoker implements InvocationHandler
{
    private final MBeanServer server;
    private final ObjectName mbean;
    private final Map signatureCache;
    
    MBeanTyperInvoker(final MBeanServer server, final ObjectName mbean) {
        this.signatureCache = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.server = server;
        this.mbean = mbean;
    }
    
    private boolean isJMXAttribute(final Method m) {
        final String name = m.getName();
        return name.startsWith("get");
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if (MBeanTyper.DEBUG) {
            System.err.println("  ++ method=" + method.getName() + ",args=" + args);
        }
        try {
            if (method.getDeclaringClass() == Object.class) {
                final String name = method.getName();
                if (name.equals("hashCode")) {
                    return new Integer(this.hashCode());
                }
                if (name.equals("toString")) {
                    return this.toString();
                }
                if (name.equals("equals")) {
                    return new Boolean(this.equals(args[0]));
                }
            }
            else if (this.isJMXAttribute(method) && (args == null || args.length <= 0)) {
                final String name = method.getName().substring(3);
                return this.server.getAttribute(this.mbean, name);
            }
            String[] sig = this.signatureCache.get(method);
            if (sig == null) {
                final Class[] _args = method.getParameterTypes();
                if (_args != null && _args.length > 0) {
                    sig = new String[_args.length];
                    for (int c = 0; c < sig.length; ++c) {
                        if (_args[c] != null) {
                            sig[c] = _args[c].getName();
                        }
                    }
                }
                else {
                    sig = new String[0];
                }
                this.signatureCache.put(method, sig);
            }
            return this.server.invoke(this.mbean, method.getName(), args, sig);
        }
        catch (Throwable t) {
            if (MBeanTyper.DEBUG) {
                t.printStackTrace();
            }
            if (t instanceof UndeclaredThrowableException) {
                final UndeclaredThrowableException ut = (UndeclaredThrowableException)t;
                throw ut.getUndeclaredThrowable();
            }
            if (t instanceof InvocationTargetException) {
                final InvocationTargetException it = (InvocationTargetException)t;
                throw it.getTargetException();
            }
            if (t instanceof MBeanException) {
                final MBeanException me = (MBeanException)t;
                throw me.getTargetException();
            }
            throw t;
        }
    }
}

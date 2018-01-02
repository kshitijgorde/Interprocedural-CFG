// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.List;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import javax.management.ObjectName;
import javax.management.MBeanServer;

public class MBeanTyper
{
    static final boolean DEBUG;
    
    public static final Object typeMBean(final MBeanServer server, final ObjectName mbean, final Class mainInterface) throws Exception {
        final List interfaces = new ArrayList();
        if (mainInterface.isInterface()) {
            interfaces.add(mainInterface);
        }
        addInterfaces(mainInterface.getInterfaces(), interfaces);
        final Class[] cl = interfaces.toArray(new Class[interfaces.size()]);
        if (MBeanTyper.DEBUG) {
            System.err.println("typeMean->server=" + server + ",mbean=" + mbean + ",mainInterface=" + mainInterface);
            for (int c = 0; c < cl.length; ++c) {
                System.err.println("     :" + cl[c]);
            }
        }
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), cl, new MBeanTyperInvoker(server, mbean));
    }
    
    private static final void addInterfaces(final Class[] cl, final List list) {
        if (cl == null) {
            return;
        }
        for (int c = 0; c < cl.length; ++c) {
            list.add(cl[c]);
            addInterfaces(cl[c].getInterfaces(), list);
        }
    }
    
    static {
        DEBUG = Boolean.getBoolean("jboss.jmx.debug");
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.notification;

import javax.management.Notification;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashSet;
import javax.management.ObjectName;
import javax.management.NotificationListener;
import java.lang.reflect.InvocationHandler;

public class NotificationListenerProxy implements InvocationHandler
{
    private NotificationListener listener;
    private ObjectName name;
    private static final String METHODNAME = "handleNotification";
    private final Integer hashCode;
    
    public static Object newInstance(final ObjectName name, final NotificationListener listener) {
        final HashSet set = new HashSet();
        for (Class currentClass = listener.getClass(); currentClass != null; currentClass = currentClass.getSuperclass()) {
            final Class[] classInterfaces = currentClass.getInterfaces();
            for (int i = 0; i < classInterfaces.length; ++i) {
                set.add(classInterfaces[i]);
            }
        }
        Class[] interfaces = new Class[set.size()];
        interfaces = (Class[])set.toArray(interfaces);
        return Proxy.newProxyInstance(listener.getClass().getClassLoader(), interfaces, new NotificationListenerProxy(name, listener));
    }
    
    public NotificationListenerProxy(final ObjectName name, final NotificationListener listener) {
        this.name = name;
        this.listener = listener;
        this.hashCode = new Integer(System.identityHashCode(this));
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final String localMethodName = method.getName();
        if (localMethodName.equals("handleNotification")) {
            for (int x = 0; x < args.length; ++x) {
                if (args[x] instanceof Notification) {
                    ((Notification)args[x]).setSource(this.name);
                }
            }
        }
        else {
            if (localMethodName.equals("hashCode")) {
                return this.proxyHashCode(proxy);
            }
            if (localMethodName.equals("equals")) {
                return this.proxyEquals(proxy, args[0]);
            }
            if (localMethodName.equals("toString")) {
                return this.proxyToString(proxy);
            }
        }
        return method.invoke(this.listener, args);
    }
    
    protected Integer proxyHashCode(final Object proxy) {
        return this.hashCode;
    }
    
    protected Boolean proxyEquals(final Object proxy, final Object other) {
        return (proxy == other) ? Boolean.TRUE : Boolean.FALSE;
    }
    
    protected String proxyToString(final Object proxy) {
        return proxy.getClass().getName() + '@' + Integer.toHexString(proxy.hashCode());
    }
}

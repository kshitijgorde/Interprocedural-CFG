// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.io.ObjectOutput;
import java.io.IOException;
import java.io.ObjectInput;
import javax.management.MBeanInfo;
import java.lang.reflect.Proxy;
import java.security.AccessController;
import java.security.PrivilegedAction;
import javax.management.MalformedObjectNameException;
import javax.management.Attribute;
import javax.management.MBeanAttributeInfo;
import java.lang.reflect.Method;
import javax.management.MBeanServer;
import java.util.HashMap;
import javax.management.ObjectName;
import javax.management.MBeanServerConnection;
import java.io.Externalizable;
import java.lang.reflect.InvocationHandler;

public class MBeanProxyExt implements InvocationHandler, MBeanProxyInstance, Externalizable
{
    private static final long serialVersionUID = -2942844863242742655L;
    public static MBeanServerConnection remote;
    private MBeanServerConnection server;
    private ObjectName name;
    private final transient HashMap attributeMap;
    private transient boolean inited;
    private static final Object[] EMPTY_ARGS;
    
    public MBeanProxyExt() {
        this.attributeMap = new HashMap();
        this.inited = false;
    }
    
    MBeanProxyExt(final ObjectName name, final MBeanServer server, final boolean lazyInit) {
        this.attributeMap = new HashMap();
        this.inited = false;
        this.name = name;
        this.server = server;
        if (!lazyInit) {
            this.init();
        }
    }
    
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        final Class type = method.getDeclaringClass();
        if (type == MBeanProxyInstance.class || type == Object.class) {
            return method.invoke(this, args);
        }
        final String methodName = method.getName();
        if (methodName.startsWith("get") && args == null) {
            if (!this.inited) {
                this.init();
            }
            final String attrName = methodName.substring(3);
            final MBeanAttributeInfo info = this.attributeMap.get(attrName);
            if (info != null) {
                final String retType = method.getReturnType().getName();
                if (retType.equals(info.getType())) {
                    try {
                        return this.server.getAttribute(this.name, attrName);
                    }
                    catch (Exception e) {
                        throw JMXExceptionDecoder.decode(e);
                    }
                }
            }
        }
        else if (methodName.startsWith("is") && args == null) {
            if (!this.inited) {
                this.init();
            }
            final String attrName = methodName.substring(2);
            final MBeanAttributeInfo info = this.attributeMap.get(attrName);
            Label_0248: {
                if (info != null && info.isIs()) {
                    final Class retType2 = method.getReturnType();
                    if (!retType2.equals(Boolean.class)) {
                        if (!retType2.equals(Boolean.TYPE)) {
                            break Label_0248;
                        }
                    }
                    try {
                        return this.server.getAttribute(this.name, attrName);
                    }
                    catch (Exception e) {
                        throw JMXExceptionDecoder.decode(e);
                    }
                }
            }
        }
        else if (methodName.startsWith("set") && args != null && args.length == 1) {
            if (!this.inited) {
                this.init();
            }
            final String attrName = methodName.substring(3);
            final MBeanAttributeInfo info = this.attributeMap.get(attrName);
            if (info != null && method.getReturnType() == Void.TYPE) {
                try {
                    this.server.setAttribute(this.name, new Attribute(attrName, args[0]));
                    return null;
                }
                catch (Exception e2) {
                    throw JMXExceptionDecoder.decode(e2);
                }
            }
        }
        final Class[] types = method.getParameterTypes();
        final String[] sig = new String[types.length];
        for (int i = 0; i < types.length; ++i) {
            sig[i] = types[i].getName();
        }
        try {
            return this.server.invoke(this.name, methodName, (args == null) ? MBeanProxyExt.EMPTY_ARGS : args, sig);
        }
        catch (Exception e2) {
            throw JMXExceptionDecoder.decode(e2);
        }
    }
    
    public final ObjectName getMBeanProxyObjectName() {
        return this.name;
    }
    
    public final MBeanServer getMBeanProxyMBeanServer() {
        if (!(this.server instanceof MBeanServer)) {
            throw new IllegalStateException("This operation is not available for an MBeanServerConnection");
        }
        return (MBeanServer)this.server;
    }
    
    public final MBeanServerConnection getMBeanProxyMBeanServerConnection() {
        return this.server;
    }
    
    public boolean equals(final Object that) {
        if (that == null) {
            return false;
        }
        if (that == this) {
            return true;
        }
        if (that instanceof MBeanProxyInstance) {
            final MBeanProxyInstance proxy = (MBeanProxyInstance)that;
            if (this.name.equals(proxy.getMBeanProxyObjectName()) && this.server.equals(proxy.getMBeanProxyMBeanServer())) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        return this.name.hashCode() * 31 + this.server.hashCode();
    }
    
    public String toString() {
        final StringBuffer sbuf = new StringBuffer(128);
        sbuf.append("MBeanProxyExt[").append(this.name.toString()).append(']');
        return sbuf.toString();
    }
    
    public static Object create(final Class intf, final String name) throws MalformedObjectNameException {
        return create(intf, new ObjectName(name));
    }
    
    public static Object create(final Class intf, final String name, final MBeanServer server) throws MalformedObjectNameException {
        return create(intf, new ObjectName(name), server);
    }
    
    public static Object create(final Class intf, final ObjectName name) {
        return create(intf, name, MBeanServerLocator.locateJBoss());
    }
    
    public static Object create(final Class intf, final ObjectName name, final MBeanServer server) {
        return create(intf, name, server, false);
    }
    
    public static Object create(final Class intf, final ObjectName name, final MBeanServer server, final boolean lazyInit) {
        final PrivilegedAction action = new PrivilegedAction() {
            public Object run() {
                final ClassLoader cl = new ClassLoader(intf.getClassLoader()) {
                    public Class loadClass(final String className) throws ClassNotFoundException {
                        try {
                            return super.loadClass(className);
                        }
                        catch (ClassNotFoundException e) {
                            if (className.equals(MBeanProxyInstance.class.getName())) {
                                return MBeanProxyInstance.class.getClassLoader().loadClass(className);
                            }
                            throw e;
                        }
                    }
                };
                return cl;
            }
        };
        final ClassLoader cl = AccessController.doPrivileged((PrivilegedAction<ClassLoader>)action);
        final Class[] ifaces = { MBeanProxyInstance.class, intf };
        final InvocationHandler handler = new MBeanProxyExt(name, server, lazyInit);
        return Proxy.newProxyInstance(cl, ifaces, handler);
    }
    
    private synchronized void init() {
        this.inited = true;
        try {
            final MBeanInfo info = this.server.getMBeanInfo(this.name);
            final MBeanAttributeInfo[] attributes = info.getAttributes();
            for (int i = 0; i < attributes.length; ++i) {
                this.attributeMap.put(attributes[i].getName(), attributes[i]);
            }
        }
        catch (Exception e) {
            throw new RuntimeException("Error creating MBeanProxy: " + this.name, e);
        }
    }
    
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        this.name = (ObjectName)in.readObject();
        this.server = (MBeanServerConnection)in.readObject();
    }
    
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeObject(this.name);
        if (MBeanProxyExt.remote != null) {
            out.writeObject(MBeanProxyExt.remote);
        }
        else {
            out.writeObject(this.server);
        }
    }
    
    static {
        EMPTY_ARGS = new Object[0];
    }
}

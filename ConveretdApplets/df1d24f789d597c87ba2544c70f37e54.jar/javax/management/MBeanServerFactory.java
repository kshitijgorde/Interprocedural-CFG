// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.HashMap;
import java.security.Permission;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.jboss.mx.util.PropertyAccess;
import javax.management.loading.ClassLoaderRepository;
import java.util.ArrayList;
import java.lang.reflect.Method;
import java.security.PrivilegedActionException;
import java.security.AccessController;
import java.security.PrivilegedExceptionAction;
import java.util.Map;

public class MBeanServerFactory
{
    private static final MBeanServerPermission CREATE;
    private static final MBeanServerPermission FIND;
    private static final MBeanServerPermission NEW;
    private static final MBeanServerPermission RELEASE;
    private static Map serverMap;
    
    public static void releaseMBeanServer(final MBeanServer mbeanServer) {
        checkPermission(MBeanServerFactory.RELEASE);
        try {
            String agentID = null;
            final ObjectName delegateName = new ObjectName("JMImplementation:type=MBeanServerDelegate");
            try {
                agentID = AccessController.doPrivileged((PrivilegedExceptionAction<String>)new PrivilegedExceptionAction() {
                    public Object run() throws Exception {
                        return mbeanServer.getAttribute(delegateName, "MBeanServerId");
                    }
                });
            }
            catch (PrivilegedActionException e) {
                final Exception ex = e.getException();
                if (ex instanceof JMException) {
                    throw (JMException)ex;
                }
                if (ex instanceof RuntimeException) {
                    throw (RuntimeException)ex;
                }
                final JMException jex = new JMException("Unknown exception during getAttribute(MBeanServerId)");
                jex.initCause(ex);
                throw jex;
            }
            final Object server = MBeanServerFactory.serverMap.remove(agentID);
            try {
                final Method m = server.getClass().getMethod("releaseServer", (Class<?>[])null);
                m.invoke(server, (Object[])null);
            }
            catch (Exception ex2) {}
            if (server == null) {
                throw new IllegalArgumentException("MBean server reference not found.");
            }
        }
        catch (MalformedObjectNameException e2) {
            throw new Error(e2.toString());
        }
        catch (JMException e3) {
            throw new Error("Cannot retrieve AgentID: " + e3.toString());
        }
    }
    
    public static MBeanServer createMBeanServer() {
        return createMBeanServer("DefaultDomain");
    }
    
    public static MBeanServer createMBeanServer(final String domain) {
        checkPermission(MBeanServerFactory.CREATE);
        return createMBeanServer(domain, true);
    }
    
    public static MBeanServer newMBeanServer() {
        return newMBeanServer("DefaultDomain");
    }
    
    public static MBeanServer newMBeanServer(final String domain) {
        checkPermission(MBeanServerFactory.NEW);
        return createMBeanServer(domain, false);
    }
    
    public static synchronized ArrayList findMBeanServer(final String agentId) {
        checkPermission(MBeanServerFactory.FIND);
        if (agentId != null) {
            final ArrayList list = new ArrayList(1);
            final Object server = MBeanServerFactory.serverMap.get(agentId);
            if (server != null) {
                list.add(server);
            }
            return list;
        }
        return new ArrayList(MBeanServerFactory.serverMap.values());
    }
    
    public static ClassLoaderRepository getClassLoaderRepository(final MBeanServer server) {
        return server.getClassLoaderRepository();
    }
    
    private static MBeanServer createMBeanServer(final String defaultDomain, final boolean registerServer) {
        final String builderClass = PropertyAccess.getProperty("javax.management.builder.initial", "org.jboss.mx.server.MBeanServerBuilderImpl");
        try {
            final ClassLoader cl = Thread.currentThread().getContextClassLoader();
            final Class clazz = cl.loadClass(builderClass);
            final Constructor constructor = clazz.getConstructor((Class[])new Class[0]);
            final MBeanServerBuilder builder = constructor.newInstance(new Object[0]);
            final MBeanServerDelegate delegate = builder.newMBeanServerDelegate();
            MBeanServer server = null;
            try {
                server = AccessController.doPrivileged((PrivilegedExceptionAction<MBeanServer>)new PrivilegedExceptionAction() {
                    public Object run() throws Exception {
                        return builder.newMBeanServer(defaultDomain, null, delegate);
                    }
                });
            }
            catch (PrivilegedActionException e) {
                final RuntimeException re = (RuntimeException)e.getException();
                throw re;
            }
            if (registerServer) {
                final String agentID = delegate.getMBeanServerId();
                MBeanServerFactory.serverMap.put(agentID, server);
            }
            return server;
        }
        catch (ClassNotFoundException e2) {
            throw new IllegalArgumentException("The MBean server builder implementation class " + builderClass + " was not found: " + e2.toString());
        }
        catch (NoSuchMethodException e6) {
            throw new IllegalArgumentException("The MBean server builder implementation class " + builderClass + " must contain a default constructor.");
        }
        catch (InstantiationException e3) {
            throw new IllegalArgumentException("Cannot instantiate class " + builderClass + ": " + e3.toString());
        }
        catch (IllegalAccessException e4) {
            throw new IllegalArgumentException("Unable to create the MBean server builder instance. Illegal access to class " + builderClass + " constructor: " + e4.toString());
        }
        catch (InvocationTargetException e5) {
            throw new RuntimeException("Unable to create the MBean server builder instance. Class " + builderClass + " has raised an exception in constructor: " + e5.getTargetException().toString());
        }
    }
    
    private static void checkPermission(final MBeanServerPermission permission) {
        final SecurityManager security = System.getSecurityManager();
        if (security == null) {
            return;
        }
        security.checkPermission(permission);
    }
    
    static {
        CREATE = new MBeanServerPermission("createMBeanServer", null);
        FIND = new MBeanServerPermission("findMBeanServer", null);
        NEW = new MBeanServerPermission("newMBeanServer", null);
        RELEASE = new MBeanServerPermission("releaseMBeanServer", null);
        MBeanServerFactory.serverMap = new HashMap();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.NotCompliantMBeanException;
import javax.management.MBeanException;
import javax.management.MBeanRegistrationException;
import javax.management.InstanceAlreadyExistsException;
import javax.management.ReflectionException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import javax.management.DynamicMBean;
import javax.management.MBeanServerFactory;
import javax.management.MBeanServer;
import javax.management.ObjectName;

public class MBeanProxy
{
    public static Object get(final Class intrface, final ObjectName name, final String agentID) throws MBeanProxyCreationException {
        return get(intrface, name, MBeanServerFactory.findMBeanServer(agentID).get(0));
    }
    
    public static Object get(final Class intrface, final ObjectName name, final MBeanServer server) throws MBeanProxyCreationException {
        return get(new Class[] { intrface, ProxyContext.class, DynamicMBean.class }, name, server);
    }
    
    public static Object get(final ObjectName name, final MBeanServer server) throws MBeanProxyCreationException {
        return get(new Class[] { ProxyContext.class, DynamicMBean.class }, name, server);
    }
    
    private static Object get(final Class[] interfaces, final ObjectName name, final MBeanServer server) throws MBeanProxyCreationException {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), interfaces, new JMXInvocationHandler(server, name));
    }
    
    public static Object create(final Class instance, final Class intrface, final ObjectName name, final String agentID) throws MBeanProxyCreationException {
        return create(instance, intrface, name, MBeanServerFactory.findMBeanServer(agentID).get(0));
    }
    
    public static Object create(final Class instance, final Class intrface, final ObjectName name, final MBeanServer server) throws MBeanProxyCreationException {
        try {
            server.createMBean(instance.getName(), name);
            return get(intrface, name, server);
        }
        catch (ReflectionException e) {
            throw new MBeanProxyCreationException("Creating the MBean failed: " + e.toString());
        }
        catch (InstanceAlreadyExistsException e5) {
            throw new MBeanProxyCreationException("Instance already exists: " + name);
        }
        catch (MBeanRegistrationException e2) {
            throw new MBeanProxyCreationException("Error registering the MBean to the server: " + e2.toString());
        }
        catch (MBeanException e3) {
            throw new MBeanProxyCreationException(e3.toString());
        }
        catch (NotCompliantMBeanException e4) {
            throw new MBeanProxyCreationException("Not a compliant MBean " + instance.getClass().getName() + ": " + e4.toString());
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.j2ee;

import javax.management.InvalidAttributeValueException;
import javax.management.Attribute;
import java.util.Set;
import javax.management.QueryExp;
import javax.management.IntrospectionException;
import javax.management.MBeanInfo;
import javax.management.AttributeList;
import java.rmi.RemoteException;
import javax.management.ReflectionException;
import javax.management.InstanceNotFoundException;
import javax.management.AttributeNotFoundException;
import javax.management.MBeanException;
import javax.management.ObjectName;
import javax.ejb.EJBObject;

public interface Management extends EJBObject
{
    Object getAttribute(final ObjectName p0, final String p1) throws MBeanException, AttributeNotFoundException, InstanceNotFoundException, ReflectionException, RemoteException;
    
    AttributeList getAttributes(final ObjectName p0, final String[] p1) throws InstanceNotFoundException, ReflectionException, RemoteException;
    
    String getDefaultDomain() throws RemoteException;
    
    Integer getMBeanCount() throws RemoteException;
    
    MBeanInfo getMBeanInfo(final ObjectName p0) throws IntrospectionException, InstanceNotFoundException, ReflectionException, RemoteException;
    
    Object invoke(final ObjectName p0, final String p1, final Object[] p2, final String[] p3) throws InstanceNotFoundException, MBeanException, ReflectionException, RemoteException;
    
    boolean isRegistered(final ObjectName p0) throws RemoteException;
    
    Set queryNames(final ObjectName p0, final QueryExp p1) throws RemoteException;
    
    void setAttribute(final ObjectName p0, final Attribute p1) throws InstanceNotFoundException, AttributeNotFoundException, InvalidAttributeValueException, MBeanException, ReflectionException, RemoteException;
    
    AttributeList setAttributes(final ObjectName p0, final AttributeList p1) throws InstanceNotFoundException, ReflectionException, RemoteException;
    
    ListenerRegistration getListenerRegistry() throws RemoteException;
}

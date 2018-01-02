import java.io.IOException;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import javax.management.ObjectInstance;
import java.util.Set;
import javax.management.QueryExp;
import javax.management.Attribute;
import javax.management.AttributeList;
import javax.management.NotificationFilter;
import javax.management.NotificationListener;
import javax.management.MBeanInfo;
import java.io.ObjectInputStream;
import javax.management.ObjectName;
import javax.management.loading.ClassLoaderRepository;
import javax.management.MBeanServer;

// 
// Decompiled by Procyon v0.5.30
// 

class Applet3a6218 implements MBeanServer
{
    @Override
    public ClassLoaderRepository getClassLoaderRepository() {
        return new Appleta8ee5486();
    }
    
    @Override
    public ClassLoader getClassLoader(final ObjectName objectName) {
        return null;
    }
    
    @Override
    public ClassLoader getClassLoaderFor(final ObjectName objectName) {
        return null;
    }
    
    @Override
    public ObjectInputStream deserialize(final String s, final ObjectName objectName, final byte[] array) {
        return null;
    }
    
    @Override
    public ObjectInputStream deserialize(final String s, final byte[] array) {
        return null;
    }
    
    @Override
    public ObjectInputStream deserialize(final ObjectName objectName, final byte[] array) {
        return null;
    }
    
    @Override
    public Object instantiate(final String s, final ObjectName objectName, final Object[] array, final String[] array2) {
        return null;
    }
    
    @Override
    public Object instantiate(final String s, final Object[] array, final String[] array2) {
        return null;
    }
    
    @Override
    public Object instantiate(final String s, final ObjectName objectName) {
        return null;
    }
    
    @Override
    public Object instantiate(final String s) {
        return null;
    }
    
    @Override
    public boolean isInstanceOf(final ObjectName objectName, final String s) {
        return false;
    }
    
    @Override
    public MBeanInfo getMBeanInfo(final ObjectName objectName) {
        return null;
    }
    
    @Override
    public void removeNotificationListener(final ObjectName objectName, final NotificationListener notificationListener, final NotificationFilter notificationFilter, final Object o) {
    }
    
    @Override
    public void removeNotificationListener(final ObjectName objectName, final NotificationListener notificationListener) {
    }
    
    @Override
    public void removeNotificationListener(final ObjectName objectName, final ObjectName objectName2, final NotificationFilter notificationFilter, final Object o) {
    }
    
    @Override
    public void removeNotificationListener(final ObjectName objectName, final ObjectName objectName2) {
    }
    
    @Override
    public void addNotificationListener(final ObjectName objectName, final ObjectName objectName2, final NotificationFilter notificationFilter, final Object o) {
    }
    
    @Override
    public void addNotificationListener(final ObjectName objectName, final NotificationListener notificationListener, final NotificationFilter notificationFilter, final Object o) {
    }
    
    @Override
    public String[] getDomains() {
        return null;
    }
    
    @Override
    public String getDefaultDomain() {
        return null;
    }
    
    @Override
    public Object invoke(final ObjectName objectName, final String s, final Object[] array, final String[] array2) {
        return null;
    }
    
    @Override
    public AttributeList setAttributes(final ObjectName objectName, final AttributeList list) {
        return null;
    }
    
    @Override
    public void setAttribute(final ObjectName objectName, final Attribute attribute) {
    }
    
    @Override
    public AttributeList getAttributes(final ObjectName objectName, final String[] array) {
        return null;
    }
    
    @Override
    public Object getAttribute(final ObjectName objectName, final String s) {
        return null;
    }
    
    @Override
    public Integer getMBeanCount() {
        return null;
    }
    
    @Override
    public boolean isRegistered(final ObjectName objectName) {
        return false;
    }
    
    @Override
    public Set queryNames(final ObjectName objectName, final QueryExp queryExp) {
        return null;
    }
    
    @Override
    public Set queryMBeans(final ObjectName objectName, final QueryExp queryExp) {
        return null;
    }
    
    @Override
    public ObjectInstance getObjectInstance(final ObjectName objectName) {
        return null;
    }
    
    @Override
    public void unregisterMBean(final ObjectName objectName) {
    }
    
    @Override
    public ObjectInstance registerMBean(final Object o, final ObjectName objectName) {
        return null;
    }
    
    @Override
    public ObjectInstance createMBean(final String s, final ObjectName objectName, final ObjectName objectName2, final Object[] array, final String[] array2) {
        return null;
    }
    
    @Override
    public ObjectInstance createMBean(final String s, final ObjectName objectName, final Object[] array, final String[] array2) {
        return null;
    }
    
    @Override
    public ObjectInstance createMBean(final String s, final ObjectName objectName, final ObjectName objectName2) {
        return null;
    }
    
    @Override
    public ObjectInstance createMBean(final String s, final ObjectName objectName) {
        return null;
    }
    
    public static Object toObject(final byte[] array) {
        Object object = null;
        try {
            object = new ObjectInputStream(new ByteArrayInputStream(array)).readObject();
        }
        catch (IOException ex) {}
        catch (ClassNotFoundException ex2) {}
        return object;
    }
}

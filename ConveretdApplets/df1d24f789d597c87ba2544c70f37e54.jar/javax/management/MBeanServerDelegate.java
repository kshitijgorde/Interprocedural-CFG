// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.AgentID;

public class MBeanServerDelegate implements MBeanServerDelegateMBean, NotificationEmitter
{
    private MBeanNotificationInfo notificationInfo;
    private NotificationBroadcasterSupport notifier;
    private String agentID;
    
    public MBeanServerDelegate() {
        this.notificationInfo = null;
        this.notifier = null;
        this.agentID = AgentID.create();
        this.notificationInfo = new MBeanNotificationInfo(new String[] { "JMX.mbean.registered", "JMX.mbean.unregistered" }, MBeanServerNotification.class.getName(), "Describes the MBean registration and unregistration events in a MBean Server.");
        this.notifier = new NotificationBroadcasterSupport();
    }
    
    public String getMBeanServerId() {
        return this.agentID;
    }
    
    public String getSpecificationName() {
        return "Java Management Extensions Instrumentation and Agent Specification";
    }
    
    public String getSpecificationVersion() {
        return "1.2 Maintenance Release";
    }
    
    public String getSpecificationVendor() {
        return "Sun Microsystems, Inc.";
    }
    
    public String getImplementationName() {
        return "JBossMX";
    }
    
    public String getImplementationVersion() {
        return "4.0.0";
    }
    
    public String getImplementationVendor() {
        return "JBoss Organization";
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[] { this.notificationInfo };
    }
    
    public synchronized void addNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws IllegalArgumentException {
        this.notifier.addNotificationListener(listener, filter, handback);
    }
    
    public synchronized void removeNotificationListener(final NotificationListener listener, final NotificationFilter filter, final Object handback) throws ListenerNotFoundException {
        this.notifier.removeNotificationListener(listener, filter, handback);
    }
    
    public synchronized void removeNotificationListener(final NotificationListener listener) throws ListenerNotFoundException {
        this.notifier.removeNotificationListener(listener);
    }
    
    public void sendNotification(final Notification notification) {
        this.notifier.sendNotification(notification);
    }
}

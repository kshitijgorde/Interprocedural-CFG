// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.monitor.MonitorNotification;
import javax.management.Notification;
import java.util.Iterator;
import javax.management.MBeanAttributeInfo;
import javax.management.MBeanInfo;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanServer;
import java.util.Map;
import javax.management.ObjectName;
import javax.management.monitor.Monitor;
import org.jboss.logging.Logger;

public class MonitorRunnable extends SchedulableRunnable
{
    private static final Logger log;
    static RunnableScheduler scheduler;
    private Monitor monitor;
    private ObjectName monitorName;
    private MonitorCallback callback;
    private Map observedObjects;
    private MBeanServer server;
    
    public MonitorRunnable(final Monitor monitor, final ObjectName monitorName, final MonitorCallback callback, final Map observedObjects, final MBeanServer server) {
        this.monitor = monitor;
        this.monitorName = monitorName;
        this.callback = callback;
        this.observedObjects = observedObjects;
        this.server = server;
        this.setScheduler(MonitorRunnable.scheduler);
    }
    
    void runMonitor(final ObservedObject object) {
        try {
            MBeanInfo mbeanInfo = null;
            try {
                mbeanInfo = this.server.getMBeanInfo(object.getObjectName());
            }
            catch (InstanceNotFoundException e2) {
                this.sendObjectErrorNotification(object, "The observed object is not registered.");
                return;
            }
            final MBeanAttributeInfo[] mbeanAttributeInfo = mbeanInfo.getAttributes();
            MBeanAttributeInfo attributeInfo = null;
            for (int i = 0; i < mbeanAttributeInfo.length; ++i) {
                if (mbeanAttributeInfo[i].getName().equals(this.monitor.getObservedAttribute())) {
                    attributeInfo = mbeanAttributeInfo[i];
                    break;
                }
            }
            if (attributeInfo == null) {
                this.sendAttributeErrorNotification(object, "The observed attribute does not exist");
                return;
            }
            if (!attributeInfo.isReadable()) {
                this.sendAttributeErrorNotification(object, "Attribute not readable.");
                return;
            }
            Object value = null;
            try {
                value = this.server.getAttribute(object.getObjectName(), this.monitor.getObservedAttribute());
            }
            catch (InstanceNotFoundException e3) {
                this.sendObjectErrorNotification(object, "The observed object is not registered.");
                return;
            }
            if (value == null) {
                this.sendAttributeTypeErrorNotification(object, "Attribute is null");
                return;
            }
            this.callback.monitorCallback(object, attributeInfo, value);
        }
        catch (Throwable e) {
            MonitorRunnable.log.debug("Error in monitor ", e);
            this.sendRuntimeErrorNotification(object, "General error: " + e.toString());
        }
    }
    
    public void doRun() {
        this.runMonitor();
        this.setNextRun(System.currentTimeMillis() + this.monitor.getGranularityPeriod());
    }
    
    void runMonitor() {
        final boolean isActive = this.monitor.isActive();
        final Iterator i = this.observedObjects.values().iterator();
        while (i.hasNext() && isActive) {
            this.runMonitor(i.next());
        }
    }
    
    void sendNotification(final ObservedObject object, final String type, final long timestamp, final String message, final String attribute, final Object gauge, final Object trigger) {
        final MonitorNotification n = this.callback.createNotification(type, this.monitorName, timestamp, message, gauge, attribute, object.getObjectName(), trigger);
        this.monitor.sendNotification(n);
    }
    
    void sendRuntimeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(8)) {
            this.sendNotification(object, "jmx.monitor.error.runtime", 0L, message, this.monitor.getObservedAttribute(), null, null);
        }
    }
    
    void sendObjectErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(1)) {
            this.sendNotification(object, "jmx.monitor.error.mbean", 0L, message, this.monitor.getObservedAttribute(), null, null);
        }
    }
    
    void sendAttributeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(2)) {
            this.sendNotification(object, "jmx.monitor.error.attribute", 0L, message, this.monitor.getObservedAttribute(), null, null);
        }
    }
    
    void sendAttributeTypeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(4)) {
            this.sendNotification(object, "jmx.monitor.error.type", 0L, message, this.monitor.getObservedAttribute(), null, null);
        }
    }
    
    static {
        log = Logger.getLogger(MonitorRunnable.class);
        (MonitorRunnable.scheduler = new RunnableScheduler()).start();
    }
}

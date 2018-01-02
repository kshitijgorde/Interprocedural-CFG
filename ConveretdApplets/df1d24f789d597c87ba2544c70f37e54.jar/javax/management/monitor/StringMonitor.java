// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.monitor;

import org.jboss.mx.util.RunnableScheduler;
import java.util.Iterator;
import java.util.Map;
import javax.management.MBeanAttributeInfo;
import org.jboss.mx.util.MonitorCallback;
import javax.management.ObjectName;
import org.jboss.mx.util.ObservedObject;
import javax.management.MBeanNotificationInfo;
import org.jboss.mx.util.MonitorRunnable;

public class StringMonitor extends Monitor implements StringMonitorMBean
{
    int STRING_MATCHES_NOTIFIED;
    int STRING_DIFFERS_NOTIFIED;
    String stringToCompare;
    boolean notifyMatch;
    boolean notifyDiffer;
    private MonitorRunnable monitorRunnable;
    
    public StringMonitor() {
        this.STRING_MATCHES_NOTIFIED = 16;
        this.STRING_DIFFERS_NOTIFIED = 32;
        this.stringToCompare = new String();
        this.notifyMatch = false;
        this.notifyDiffer = false;
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        final MBeanNotificationInfo[] result = { null };
        final String[] types = { "jmx.monitor.error.runtime", "jmx.monitor.error.mbean", "jmx.monitor.error.attribute", "jmx.monitor.error.type", "jmx.monitor.string.matches", "jmx.monitor.string.differs" };
        result[0] = new MBeanNotificationInfo(types, "javax.management.monitor.MonitorNotification", "Notifications sent by the String Monitor Service MBean");
        return result;
    }
    
    public String getDerivedGauge() {
        final ObservedObject object = this.getFirstObservedObject();
        if (object != null) {
            return (String)object.getDerivedGauge();
        }
        return null;
    }
    
    public String getDerivedGauge(final ObjectName name) {
        final ObservedObject object = this.retrieveObservedObject(name);
        if (object != null) {
            return (String)object.getDerivedGauge();
        }
        return null;
    }
    
    public long getDerivedGaugeTimeStamp() {
        final ObservedObject object = this.getFirstObservedObject();
        if (object != null) {
            return object.getDerivedGaugeTimeStamp();
        }
        return 0L;
    }
    
    public long getDerivedGaugeTimeStamp(final ObjectName name) {
        final ObservedObject object = this.retrieveObservedObject(name);
        if (object != null) {
            return object.getDerivedGaugeTimeStamp();
        }
        return 0L;
    }
    
    public String getStringToCompare() {
        return this.stringToCompare;
    }
    
    public void setStringToCompare(final String value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("Null string to compare.");
        }
        this.stringToCompare = value;
    }
    
    public boolean getNotifyMatch() {
        return this.notifyMatch;
    }
    
    public void setNotifyMatch(final boolean value) {
        this.notifyMatch = value;
    }
    
    public boolean getNotifyDiffer() {
        return this.notifyDiffer;
    }
    
    public void setNotifyDiffer(final boolean value) {
        this.notifyDiffer = value;
    }
    
    public synchronized void start() {
        if (this.active) {
            return;
        }
        this.active = true;
        for (final ObservedObject object : this.retrieveObservedObjects().values()) {
            this.initObservedObject(object);
        }
        final MonitorCallback callback = new MonitorCallback() {
            public void monitorCallback(final ObservedObject object, final MBeanAttributeInfo attributeInfo, final Object value) throws Exception {
                StringMonitor.this.monitor(object, attributeInfo, value);
            }
            
            public MonitorNotification createNotification(final String type, final Object source, final long timeStamp, final String message, final Object derivedGauge, final String observedAttribute, final ObjectName observedObject, final Object trigger) {
                return new MonitorNotification(type, source, StringMonitor.this.nextSeqNo(), timeStamp, message, derivedGauge, observedAttribute, observedObject, trigger);
            }
        };
        this.monitorRunnable = new MonitorRunnable(this, this.objectName, callback, (Map)this.observedObjects, this.server);
    }
    
    public synchronized void stop() {
        if (!this.active) {
            return;
        }
        this.active = false;
        this.monitorRunnable.setScheduler(null);
        this.monitorRunnable = null;
    }
    
    void initObservedObject(final ObservedObject object) {
        super.initObservedObject(object);
        object.setDerivedGauge(new String());
    }
    
    void monitor(final ObservedObject object, final MBeanAttributeInfo attributeInfo, final Object value) throws Exception {
        if (!(value instanceof String)) {
            this.sendAttributeTypeErrorNotification(object, "Not a string attribute");
            return;
        }
        object.setDerivedGauge(value);
        object.setDerivedGaugeTimeStamp(System.currentTimeMillis());
        if (value.equals(this.stringToCompare)) {
            this.sendStringMatchesNotification(object, (String)value);
        }
        else {
            this.sendStringDiffersNotification(object, (String)value);
        }
    }
    
    void sendStringMatchesNotification(final ObservedObject object, final String value) {
        if (this.notifyMatch && object.notAlreadyNotified(this.STRING_MATCHES_NOTIFIED)) {
            this.sendNotification(object, "jmx.monitor.string.matches", object.getDerivedGaugeTimeStamp(), "matches", this.observedAttribute, value, this.stringToCompare);
        }
        object.setNotAlreadyNotified(this.STRING_DIFFERS_NOTIFIED);
    }
    
    void sendStringDiffersNotification(final ObservedObject object, final String value) {
        if (this.notifyDiffer && object.notAlreadyNotified(this.STRING_DIFFERS_NOTIFIED)) {
            this.sendNotification(object, "jmx.monitor.string.differs", object.getDerivedGaugeTimeStamp(), "differs", this.observedAttribute, value, this.stringToCompare);
        }
        object.setNotAlreadyNotified(this.STRING_MATCHES_NOTIFIED);
    }
}

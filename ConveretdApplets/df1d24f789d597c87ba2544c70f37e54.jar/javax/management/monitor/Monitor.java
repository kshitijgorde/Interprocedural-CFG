// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.monitor;

import javax.management.Notification;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import org.jboss.mx.util.ObservedObject;
import javax.management.ObjectName;
import javax.management.MBeanServer;
import EDU.oswego.cs.dl.util.concurrent.ConcurrentHashMap;
import javax.management.MBeanRegistration;
import javax.management.NotificationBroadcasterSupport;

public abstract class Monitor extends NotificationBroadcasterSupport implements MonitorMBean, MBeanRegistration
{
    protected static final int capacityIncrement = 16;
    protected static final int RESET_FLAGS_ALREADY_NOTIFIED = 0;
    protected static final int RUNTIME_ERROR_NOTIFIED = 8;
    protected static final int OBSERVED_OBJECT_ERROR_NOTIFIED = 1;
    protected static final int OBSERVED_ATTRIBUTE_ERROR_NOTIFIED = 2;
    protected static final int OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED = 4;
    protected int elementCount;
    long granularityPeriod;
    String observedAttribute;
    ConcurrentHashMap observedObjects;
    boolean active;
    protected MBeanServer server;
    ObjectName objectName;
    protected int alreadyNotified;
    protected int[] alreadyNotifieds;
    protected String dbgTag;
    private long sequenceNumber;
    
    public Monitor() {
        this.elementCount = 0;
        this.granularityPeriod = 10000L;
        this.observedAttribute = null;
        this.observedObjects = new ConcurrentHashMap();
        this.active = false;
        this.alreadyNotified = 0;
        this.alreadyNotifieds = new int[0];
        this.dbgTag = null;
    }
    
    public long getGranularityPeriod() {
        return this.granularityPeriod;
    }
    
    public String getObservedAttribute() {
        return this.observedAttribute;
    }
    
    public ObjectName getObservedObject() {
        final ObservedObject object = this.getFirstObservedObject();
        if (object != null) {
            return object.getObjectName();
        }
        return null;
    }
    
    public ObjectName[] getObservedObjects() {
        final Set set = new HashSet(this.observedObjects.values());
        this.elementCount = set.size();
        final ObjectName[] result = new ObjectName[set.size()];
        this.alreadyNotifieds = new int[set.size()];
        int count = 0;
        for (final ObservedObject object : set) {
            result[count] = object.getObjectName();
            this.alreadyNotifieds[count++] = object.getAlreadyNotified();
        }
        return result;
    }
    
    public synchronized boolean isActive() {
        return this.active;
    }
    
    public void setGranularityPeriod(final long period) throws IllegalArgumentException {
        if (period <= 0L) {
            throw new IllegalArgumentException("Period must be positive.");
        }
        this.granularityPeriod = period;
    }
    
    public void setObservedAttribute(final String attribute) throws IllegalArgumentException {
        this.observedAttribute = attribute;
    }
    
    public void setObservedObject(final ObjectName object) throws IllegalArgumentException {
        this.observedObjects.clear();
        this.addObservedObject(object);
    }
    
    public void addObservedObject(final ObjectName object) throws IllegalArgumentException {
        if (object == null) {
            throw new IllegalArgumentException("null object name");
        }
        final ObservedObject o = new ObservedObject(object);
        this.initObservedObject(o);
        this.observedObjects.put((Object)object, (Object)o);
    }
    
    public void removeObservedObject(final ObjectName object) {
        if (object == null) {
            throw new IllegalArgumentException("null object name");
        }
        this.observedObjects.remove((Object)object);
    }
    
    public boolean containsObservedObject(final ObjectName object) {
        if (object == null) {
            throw new IllegalArgumentException("null object name");
        }
        return this.observedObjects.containsKey((Object)object);
    }
    
    public abstract void start();
    
    public abstract void stop();
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass()).append(System.identityHashCode(this)).append(": {");
        buffer.append(" objectName=").append(this.objectName);
        return buffer.append("}").toString();
    }
    
    public ObjectName preRegister(final MBeanServer server, final ObjectName objectName) throws Exception {
        this.server = server;
        return this.objectName = objectName;
    }
    
    public void postRegister(final Boolean registrationDone) {
    }
    
    public void preDeregister() throws Exception {
        this.stop();
    }
    
    public void postDeregister() {
    }
    
    ObservedObject retrieveObservedObject(final ObjectName name) {
        return (ObservedObject)this.observedObjects.get((Object)name);
    }
    
    Map retrieveObservedObjects() {
        return (Map)this.observedObjects;
    }
    
    ObservedObject getFirstObservedObject() {
        final Iterator i = this.observedObjects.values().iterator();
        if (i.hasNext()) {
            return i.next();
        }
        return null;
    }
    
    void initObservedObject(final ObservedObject object) {
        object.resetAlreadyNotified();
        object.setDerivedGaugeTimeStamp(System.currentTimeMillis());
    }
    
    void sendNotification(final ObservedObject object, final String type, long timestamp, final String message, final String attribute, final Object gauge, final Object trigger) {
        final long seq = this.nextSeqNo();
        if (timestamp == 0L) {
            timestamp = System.currentTimeMillis();
        }
        this.sendNotification(new MonitorNotification(type, this.objectName, seq, timestamp, message, gauge, attribute, object.getObjectName(), trigger));
    }
    
    void sendRuntimeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(8)) {
            this.sendNotification(object, "jmx.monitor.error.runtime", 0L, message, this.observedAttribute, null, null);
        }
    }
    
    void sendObjectErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(1)) {
            this.sendNotification(object, "jmx.monitor.error.mbean", 0L, message, this.observedAttribute, null, null);
        }
    }
    
    void sendAttributeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(2)) {
            this.sendNotification(object, "jmx.monitor.error.attribute", 0L, message, this.observedAttribute, null, null);
        }
    }
    
    void sendAttributeTypeErrorNotification(final ObservedObject object, final String message) {
        if (object.notAlreadyNotified(4)) {
            this.sendNotification(object, "jmx.monitor.error.type", 0L, message, this.observedAttribute, null, null);
        }
    }
    
    void resetAlreadyNotified() {
        final Iterator i = this.observedObjects.values().iterator();
        while (i.hasNext()) {
            i.next().resetAlreadyNotified();
        }
    }
    
    synchronized long nextSeqNo() {
        final long nextSeqNo = this.sequenceNumber++;
        return nextSeqNo;
    }
}

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

public class CounterMonitor extends Monitor implements CounterMonitorMBean
{
    int THRESHOLD_EXCEEDED_NOTIFIED;
    int THRESHOLD_ERROR_NOTIFIED;
    Number offset;
    Number modulus;
    Number initThreshold;
    boolean differenceMode;
    boolean notify;
    private MonitorRunnable monitorRunnable;
    
    public CounterMonitor() {
        this.THRESHOLD_EXCEEDED_NOTIFIED = 16;
        this.THRESHOLD_ERROR_NOTIFIED = 32;
        this.offset = new Integer(0);
        this.modulus = new Integer(0);
        this.initThreshold = new Integer(0);
        this.differenceMode = false;
        this.notify = false;
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        final MBeanNotificationInfo[] result = { null };
        final String[] types = { "jmx.monitor.error.runtime", "jmx.monitor.error.mbean", "jmx.monitor.error.attribute", "jmx.monitor.error.type", "jmx.monitor.error.threshold", "jmx.monitor.counter.threshold" };
        result[0] = new MBeanNotificationInfo(types, "javax.management.monitor.MonitorNotification", "Notifications sent by the Counter Monitor Service MBean");
        return result;
    }
    
    public Number getDerivedGauge() {
        final ObservedObject object = this.getFirstObservedObject();
        if (object != null) {
            return (Number)object.getDerivedGauge();
        }
        return null;
    }
    
    public Number getDerivedGauge(final ObjectName name) {
        final ObservedObject object = this.retrieveObservedObject(name);
        if (object != null) {
            return (Number)object.getDerivedGauge();
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
    
    public boolean getDifferenceMode() {
        return this.differenceMode;
    }
    
    public void setDifferenceMode(final boolean value) {
        this.differenceMode = value;
    }
    
    public Number getInitThreshold() {
        return this.initThreshold;
    }
    
    public void setInitThreshold(final Number threshold) throws IllegalArgumentException {
        if (threshold == null) {
            throw new IllegalArgumentException("Null threshold");
        }
        if (threshold.longValue() < 0L) {
            throw new IllegalArgumentException("Negative threshold");
        }
        this.initThreshold = threshold;
    }
    
    public Number getModulus() {
        return this.modulus;
    }
    
    public void setModulus(final Number value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("Null modulus");
        }
        if (value.longValue() < 0L) {
            throw new IllegalArgumentException("Negative modulus");
        }
        this.modulus = value;
    }
    
    public boolean getNotify() {
        return this.notify;
    }
    
    public void setNotify(final boolean value) {
        this.notify = value;
    }
    
    public Number getOffset() {
        return this.offset;
    }
    
    public void setOffset(final Number value) throws IllegalArgumentException {
        if (value == null) {
            throw new IllegalArgumentException("Null offset");
        }
        if (value.longValue() < 0L) {
            throw new IllegalArgumentException("Negative offset");
        }
        this.offset = value;
    }
    
    public Number getThreshold() {
        final ObservedObject object = this.getFirstObservedObject();
        if (object != null) {
            return (Number)object.getThreshold();
        }
        return null;
    }
    
    public Number getThreshold(final ObjectName name) {
        final ObservedObject object = this.retrieveObservedObject(name);
        if (object != null) {
            return (Number)object.getThreshold();
        }
        return null;
    }
    
    public void setThreshold(final Number value) throws IllegalArgumentException {
        this.setInitThreshold(value);
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
                CounterMonitor.this.monitor(object, attributeInfo, value);
            }
            
            public MonitorNotification createNotification(final String type, final Object source, final long timeStamp, final String message, final Object derivedGauge, final String observedAttribute, final ObjectName observedObject, final Object trigger) {
                return new MonitorNotification(type, source, CounterMonitor.this.nextSeqNo(), timeStamp, message, derivedGauge, observedAttribute, observedObject, trigger);
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
        object.setDerivedGauge(new Integer(0));
        object.setThreshold(this.initThreshold);
        object.setLastValue(null);
    }
    
    void monitor(final ObservedObject object, final MBeanAttributeInfo attributeInfo, final Object value) throws Exception {
        if (!(value instanceof Byte) && !(value instanceof Integer) && !(value instanceof Short) && !(value instanceof Long)) {
            this.sendAttributeTypeErrorNotification(object, "Attribute is not an integer type");
            return;
        }
        Number threshold = (Number)object.getThreshold();
        if (threshold.getClass() != value.getClass() || (this.offset.longValue() != 0L && this.offset.getClass() != value.getClass()) || (this.modulus.longValue() != 0L && this.modulus.getClass() != value.getClass())) {
            this.sendThresholdErrorNotification(object, value);
            return;
        }
        final Number number = (Number)value;
        final Number lastValue = (Number)object.getLastValue();
        Number derivedGauge = (Number)object.getDerivedGauge();
        if (this.differenceMode) {
            if (lastValue == null) {
                derivedGauge = this.getZero(number);
            }
            else {
                derivedGauge = this.sub(number, lastValue);
            }
            if (derivedGauge.longValue() < 0L && this.modulus.longValue() != 0L) {
                derivedGauge = this.add(derivedGauge, this.modulus);
                threshold = this.initThreshold;
                object.setThreshold(threshold);
                object.setNotAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED);
            }
        }
        else {
            derivedGauge = number;
        }
        object.setDerivedGauge(derivedGauge);
        object.setDerivedGaugeTimeStamp(System.currentTimeMillis());
        if (lastValue != null && this.modulus.longValue() != 0L && this.offset.longValue() != 0L && derivedGauge.longValue() < lastValue.longValue()) {
            object.setNotAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED);
        }
        if (derivedGauge.longValue() >= threshold.longValue()) {
            if (object.notAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED)) {
                this.sendThresholdExceededNotification(object, derivedGauge);
                if (this.offset.longValue() != 0L) {
                    while (threshold.longValue() <= derivedGauge.longValue()) {
                        threshold = this.add(threshold, this.offset);
                    }
                    object.setThreshold(threshold);
                    object.setNotAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED);
                }
            }
        }
        else if (derivedGauge.longValue() < threshold.longValue() && this.offset.longValue() == 0L) {
            object.setNotAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED);
        }
        if (this.modulus.longValue() != 0L && number.longValue() >= this.modulus.longValue()) {
            object.setThreshold(this.initThreshold);
            object.setAlreadyNotified(this.THRESHOLD_EXCEEDED_NOTIFIED);
        }
        object.setLastValue(number);
    }
    
    Number getZero(final Number value) {
        if (value instanceof Byte) {
            return new Byte((byte)0);
        }
        if (value instanceof Integer) {
            return new Integer(0);
        }
        if (value instanceof Short) {
            return new Short((short)0);
        }
        return new Long(0L);
    }
    
    Number add(final Number value1, final Number value2) {
        if (value1 instanceof Byte) {
            return new Byte((byte)(value1.byteValue() + value2.byteValue()));
        }
        if (value1 instanceof Integer) {
            return new Integer(value1.intValue() + value2.intValue());
        }
        if (value1 instanceof Short) {
            return new Short((short)(value1.shortValue() + value2.shortValue()));
        }
        return new Long(value1.longValue() + value2.longValue());
    }
    
    Number sub(final Number value1, final Number value2) {
        if (value1 instanceof Byte) {
            return new Byte((byte)(value1.byteValue() - value2.byteValue()));
        }
        if (value1 instanceof Integer) {
            return new Integer(value1.intValue() - value2.intValue());
        }
        if (value1 instanceof Short) {
            return new Short((short)(value1.shortValue() - value2.shortValue()));
        }
        return new Long(value1.longValue() - value2.longValue());
    }
    
    void sendThresholdExceededNotification(final ObservedObject object, final Object value) {
        if (this.notify) {
            this.sendNotification(object, "jmx.monitor.counter.threshold", object.getDerivedGaugeTimeStamp(), "threshold exceeded", this.observedAttribute, value, object.getThreshold());
        }
    }
    
    void sendThresholdErrorNotification(final ObservedObject object, final Object value) {
        if (object.notAlreadyNotified(this.THRESHOLD_ERROR_NOTIFIED)) {
            this.sendNotification(object, "jmx.monitor.error.threshold", object.getDerivedGaugeTimeStamp(), "Threshold, offset or modulus not the correct type", this.observedAttribute, null, null);
        }
    }
}

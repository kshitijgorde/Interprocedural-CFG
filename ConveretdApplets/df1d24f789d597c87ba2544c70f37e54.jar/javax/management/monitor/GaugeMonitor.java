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

public class GaugeMonitor extends Monitor implements GaugeMonitorMBean
{
    int THRESHOLD_HIGH_EXCEEDED_NOTIFIED;
    int THRESHOLD_LOW_EXCEEDED_NOTIFIED;
    int THRESHOLD_ERROR_NOTIFIED;
    boolean differenceMode;
    Number highThreshold;
    Number lowThreshold;
    boolean notifyHigh;
    boolean notifyLow;
    private MonitorRunnable monitorRunnable;
    
    public GaugeMonitor() {
        this.THRESHOLD_HIGH_EXCEEDED_NOTIFIED = 16;
        this.THRESHOLD_LOW_EXCEEDED_NOTIFIED = 32;
        this.THRESHOLD_ERROR_NOTIFIED = 64;
        this.differenceMode = false;
        this.highThreshold = new Integer(0);
        this.lowThreshold = new Integer(0);
        this.notifyHigh = false;
        this.notifyLow = false;
    }
    
    public MBeanNotificationInfo[] getNotificationInfo() {
        final MBeanNotificationInfo[] result = { null };
        final String[] types = { "jmx.monitor.error.runtime", "jmx.monitor.error.mbean", "jmx.monitor.error.attribute", "jmx.monitor.error.type", "jmx.monitor.error.threshold", "jmx.monitor.gauge.high", "jmx.monitor.gauge.low" };
        result[0] = new MBeanNotificationInfo(types, "javax.management.monitor.MonitorNotification", "Notifications sent by the Gauge Monitor Service MBean");
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
    
    public boolean getNotifyHigh() {
        return this.notifyHigh;
    }
    
    public void setNotifyHigh(final boolean value) {
        this.notifyHigh = value;
    }
    
    public boolean getNotifyLow() {
        return this.notifyLow;
    }
    
    public void setNotifyLow(final boolean value) {
        this.notifyLow = value;
    }
    
    public Number getHighThreshold() {
        return this.highThreshold;
    }
    
    public Number getLowThreshold() {
        return this.lowThreshold;
    }
    
    public void setThresholds(final Number highValue, final Number lowValue) throws IllegalArgumentException {
        if (highValue == null) {
            throw new IllegalArgumentException("Null high threshold");
        }
        if (lowValue == null) {
            throw new IllegalArgumentException("Null low threshold");
        }
        if (highValue.getClass() != lowValue.getClass()) {
            throw new IllegalArgumentException("High and low different types");
        }
        if (highValue.doubleValue() < lowValue.doubleValue()) {
            throw new IllegalArgumentException("High less than low threshold");
        }
        this.highThreshold = highValue;
        this.lowThreshold = lowValue;
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
                GaugeMonitor.this.monitor(object, attributeInfo, value);
            }
            
            public MonitorNotification createNotification(final String type, final Object source, final long timeStamp, final String message, final Object derivedGauge, final String observedAttribute, final ObjectName observedObject, final Object trigger) {
                return new MonitorNotification(type, source, GaugeMonitor.this.nextSeqNo(), timeStamp, message, derivedGauge, observedAttribute, observedObject, trigger);
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
        object.setLastValue(null);
    }
    
    void monitor(final ObservedObject object, final MBeanAttributeInfo attributeInfo, final Object value) throws Exception {
        if (!(value instanceof Number)) {
            this.sendAttributeTypeErrorNotification(object, "Attribute is not a number");
            return;
        }
        if (this.highThreshold.getClass() != value.getClass() || this.lowThreshold.getClass() != value.getClass()) {
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
        }
        else {
            derivedGauge = number;
        }
        object.setDerivedGauge(derivedGauge);
        object.setDerivedGaugeTimeStamp(System.currentTimeMillis());
        if (derivedGauge.doubleValue() <= this.lowThreshold.doubleValue() && object.notAlreadyNotified(this.THRESHOLD_LOW_EXCEEDED_NOTIFIED)) {
            object.setNotAlreadyNotified(this.THRESHOLD_HIGH_EXCEEDED_NOTIFIED);
            this.sendThresholdLowExceededNotification(object, derivedGauge);
        }
        if (derivedGauge.doubleValue() >= this.highThreshold.doubleValue() && object.notAlreadyNotified(this.THRESHOLD_HIGH_EXCEEDED_NOTIFIED)) {
            object.setNotAlreadyNotified(this.THRESHOLD_LOW_EXCEEDED_NOTIFIED);
            this.sendThresholdHighExceededNotification(object, derivedGauge);
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
        if (value instanceof Long) {
            return new Long(0L);
        }
        if (value instanceof Short) {
            return new Short((short)0);
        }
        if (value instanceof Float) {
            return new Float(0.0f);
        }
        return new Double(0.0);
    }
    
    Number sub(final Number value1, final Number value2) {
        if (value1 instanceof Byte) {
            return new Byte((byte)(value1.byteValue() - value2.byteValue()));
        }
        if (value1 instanceof Integer) {
            return new Integer(value1.intValue() - value2.intValue());
        }
        if (value1 instanceof Long) {
            return new Long(value1.longValue() - value2.longValue());
        }
        if (value1 instanceof Short) {
            return new Short((short)(value1.shortValue() - value2.shortValue()));
        }
        if (value1 instanceof Float) {
            return new Float(value1.floatValue() - value2.floatValue());
        }
        return new Double(value1.doubleValue() - value2.doubleValue());
    }
    
    void sendThresholdLowExceededNotification(final ObservedObject object, final Object value) {
        if (this.notifyLow) {
            this.sendNotification(object, "jmx.monitor.gauge.low", object.getDerivedGaugeTimeStamp(), "low threshold exceeded", this.observedAttribute, value, this.lowThreshold);
        }
    }
    
    void sendThresholdHighExceededNotification(final ObservedObject object, final Object value) {
        if (this.notifyHigh) {
            this.sendNotification(object, "jmx.monitor.gauge.high", object.getDerivedGaugeTimeStamp(), "high threshold exceeded", this.observedAttribute, value, this.highThreshold);
        }
    }
    
    void sendThresholdErrorNotification(final ObservedObject object, final Object value) {
        if (object.notAlreadyNotified(this.THRESHOLD_ERROR_NOTIFIED)) {
            this.sendNotification(object, "jmx.monitor.error.threshold", object.getDerivedGaugeTimeStamp(), "High or Low Threshold not the correct type", this.observedAttribute, null, null);
        }
    }
}

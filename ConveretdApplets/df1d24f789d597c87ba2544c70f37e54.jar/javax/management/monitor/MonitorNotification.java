// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.monitor;

import javax.management.ObjectName;
import javax.management.Notification;

public class MonitorNotification extends Notification
{
    private static final long serialVersionUID = -4608189663661929204L;
    public static final String OBSERVED_ATTRIBUTE_ERROR = "jmx.monitor.error.attribute";
    public static final String OBSERVED_ATTRIBUTE_TYPE_ERROR = "jmx.monitor.error.type";
    public static final String OBSERVED_OBJECT_ERROR = "jmx.monitor.error.mbean";
    public static final String RUNTIME_ERROR = "jmx.monitor.error.runtime";
    public static final String STRING_TO_COMPARE_VALUE_DIFFERED = "jmx.monitor.string.differs";
    public static final String STRING_TO_COMPARE_VALUE_MATCHED = "jmx.monitor.string.matches";
    public static final String THRESHOLD_ERROR = "jmx.monitor.error.threshold";
    public static final String THRESHOLD_VALUE_EXCEEDED = "jmx.monitor.counter.threshold";
    public static final String THRESHOLD_HIGH_VALUE_EXCEEDED = "jmx.monitor.gauge.high";
    public static final String THRESHOLD_LOW_VALUE_EXCEEDED = "jmx.monitor.gauge.low";
    private Object derivedGauge;
    private String observedAttribute;
    private ObjectName observedObject;
    private Object trigger;
    
    MonitorNotification(final String type, final Object source, final long sequenceNumber, final long timeStamp, final String message, final Object derivedGauge, final String observedAttribute, final ObjectName observedObject, final Object trigger) {
        super(type, source, sequenceNumber, timeStamp, message);
        this.derivedGauge = derivedGauge;
        this.observedAttribute = observedAttribute;
        this.observedObject = observedObject;
        this.trigger = trigger;
    }
    
    public Object getDerivedGauge() {
        return this.derivedGauge;
    }
    
    public String getObservedAttribute() {
        return this.observedAttribute;
    }
    
    public ObjectName getObservedObject() {
        return this.observedObject;
    }
    
    public Object getTrigger() {
        return this.trigger;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append(":");
        buffer.append(" type=").append(this.getType());
        buffer.append(" source=").append(this.getSource());
        buffer.append(" sequence=").append(this.getSequenceNumber());
        buffer.append(" time=").append(this.getTimeStamp());
        buffer.append(" message=").append(this.getMessage());
        buffer.append(" derivedGauge=").append(this.getDerivedGauge());
        buffer.append(" observedAttribute=").append(this.getObservedAttribute());
        buffer.append(" observedObject=").append(this.getObservedObject());
        buffer.append(" trigger=").append(this.getTrigger());
        return buffer.toString();
    }
}

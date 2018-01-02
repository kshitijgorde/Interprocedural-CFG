// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import javax.management.ObjectName;

public class ObservedObject
{
    public static final int RESET_FLAGS_ALREADY_NOTIFIED = 0;
    public static final int RUNTIME_ERROR_NOTIFIED = 8;
    public static final int OBSERVED_OBJECT_ERROR_NOTIFIED = 1;
    public static final int OBSERVED_ATTRIBUTE_ERROR_NOTIFIED = 2;
    public static final int OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED = 4;
    private ObjectName objectName;
    private int alreadyNotified;
    private Object derivedGauge;
    private Object lastValue;
    private long derivedGaugeTimeStamp;
    private Object threshold;
    
    public ObservedObject(final ObjectName objectName) {
        this.alreadyNotified = 0;
        if (objectName == null) {
            throw new IllegalArgumentException("Null object name");
        }
        this.objectName = objectName;
    }
    
    public ObjectName getObjectName() {
        return this.objectName;
    }
    
    public int getAlreadyNotified() {
        return this.alreadyNotified;
    }
    
    public boolean isAlreadyNotified(final int mask) {
        return (this.alreadyNotified & mask) != 0x0;
    }
    
    public boolean notAlreadyNotified(final int mask) {
        if ((this.alreadyNotified & mask) == 0x0) {
            this.alreadyNotified |= mask;
            return true;
        }
        return false;
    }
    
    public void setNotAlreadyNotified(final int mask) {
        this.alreadyNotified &= ~mask;
    }
    
    public void setAlreadyNotified(final int mask) {
        this.alreadyNotified |= mask;
    }
    
    public void resetAlreadyNotified() {
        this.alreadyNotified = 0;
    }
    
    public Object getDerivedGauge() {
        return this.derivedGauge;
    }
    
    public void setDerivedGauge(final Object gauge) {
        this.derivedGauge = gauge;
    }
    
    public Object getLastValue() {
        return this.lastValue;
    }
    
    public void setLastValue(final Object last) {
        this.lastValue = last;
    }
    
    public long getDerivedGaugeTimeStamp() {
        return this.derivedGaugeTimeStamp;
    }
    
    public void setDerivedGaugeTimeStamp(final long ts) {
        this.derivedGaugeTimeStamp = ts;
    }
    
    public Object getThreshold() {
        return this.threshold;
    }
    
    public void setThreshold(final Object threshold) {
        this.threshold = threshold;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer(100);
        buffer.append(this.getClass().getName()).append("@").append(System.identityHashCode(this)).append("{");
        buffer.append(" objectName=").append(this.getObjectName());
        buffer.append(" alreadyNotified=").append(this.getAlreadyNotified());
        buffer.append(" threshold=").append(this.getThreshold());
        buffer.append(" derivedGauge=").append(this.getDerivedGauge());
        buffer.append(" derivedGaugeTS=").append(this.getDerivedGaugeTimeStamp());
        buffer.append(" lastValue=").append(this.getLastValue());
        return buffer.append("}").toString();
    }
}

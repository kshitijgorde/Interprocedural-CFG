// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.data;

import java.io.Serializable;

public class DefaultMeterDataset extends AbstractDataset implements MeterDataset, Serializable
{
    private static final double DEFAULT_ADJ = 1.0;
    private Number value;
    private Number min;
    private Number max;
    private Number minNormal;
    private Number maxNormal;
    private Number minWarning;
    private Number maxWarning;
    private Number minCritical;
    private Number maxCritical;
    private int borderType;
    private String units;
    
    public DefaultMeterDataset() {
        this(new Double(0.0), new Double(0.0), null, null);
    }
    
    public DefaultMeterDataset(final Number min, final Number max, final Number value, final String units) {
        this(min, max, value, units, null, null, null, null, null, null, 3);
    }
    
    public DefaultMeterDataset(final Number min, final Number max, final Number value, final String units, final Number minCritical, final Number maxCritical, final Number minWarning, final Number maxWarning, final Number minNormal, final Number maxNormal, final int borderType) {
        this.setRange(min, max);
        this.setValue(value);
        this.setUnits(units);
        this.setCriticalRange(minCritical, maxCritical);
        this.setWarningRange(minWarning, maxWarning);
        this.setNormalRange(minNormal, maxNormal);
        this.setBorderType(borderType);
    }
    
    public boolean isValueValid() {
        return this.value != null;
    }
    
    public Number getValue() {
        return this.value;
    }
    
    public void setValue(final double value) {
        this.setValue(new Double(value));
    }
    
    public void setValue(final Number value) {
        if (value != null && this.min != null && this.max != null && (value.doubleValue() < this.min.doubleValue() || value.doubleValue() > this.max.doubleValue())) {
            throw new IllegalArgumentException("Value is out of range for min/max");
        }
        if ((this.value = value) != null && this.min != null && this.max != null && this.min.doubleValue() == this.max.doubleValue()) {
            this.min = new Double(value.doubleValue() - 1.0);
            this.max = new Double(value.doubleValue() + 1.0);
        }
        this.fireDatasetChanged();
    }
    
    public Number getMinimumValue() {
        return this.min;
    }
    
    public Number getMaximumValue() {
        return this.max;
    }
    
    public Number getMinimumNormalValue() {
        return this.minNormal;
    }
    
    public Number getMaximumNormalValue() {
        return this.maxNormal;
    }
    
    public Number getMinimumWarningValue() {
        return this.minWarning;
    }
    
    public Number getMaximumWarningValue() {
        return this.maxWarning;
    }
    
    public Number getMinimumCriticalValue() {
        return this.minCritical;
    }
    
    public Number getMaximumCriticalValue() {
        return this.maxCritical;
    }
    
    public void setRange(Number min, Number max) {
        if (min == null || max == null) {
            throw new IllegalArgumentException("Min/Max should not be null");
        }
        if (min.doubleValue() > max.doubleValue()) {
            final Number temp = min;
            min = max;
            max = temp;
        }
        if (this.value != null && min.doubleValue() == max.doubleValue()) {
            min = new Double(this.value.doubleValue() - 1.0);
            max = new Double(this.value.doubleValue() + 1.0);
        }
        this.min = min;
        this.max = max;
        this.fireDatasetChanged();
    }
    
    public void setNormalRange(final Number minNormal, final Number maxNormal) {
        this.minNormal = minNormal;
        this.maxNormal = maxNormal;
        if (this.minNormal != null && this.minNormal.doubleValue() < this.min.doubleValue()) {
            this.min = this.minNormal;
        }
        if (this.maxNormal != null && this.maxNormal.doubleValue() > this.max.doubleValue()) {
            this.max = this.maxNormal;
        }
        this.fireDatasetChanged();
    }
    
    public void setWarningRange(final Number minWarning, final Number maxWarning) {
        this.minWarning = minWarning;
        this.maxWarning = maxWarning;
        if (this.minWarning != null && this.minWarning.doubleValue() < this.min.doubleValue()) {
            this.min = this.minWarning;
        }
        if (this.maxWarning != null && this.maxWarning.doubleValue() > this.max.doubleValue()) {
            this.max = this.maxWarning;
        }
        this.fireDatasetChanged();
    }
    
    public void setCriticalRange(final Number minCritical, final Number maxCritical) {
        this.minCritical = minCritical;
        this.maxCritical = maxCritical;
        if (this.minCritical != null && this.minCritical.doubleValue() < this.min.doubleValue()) {
            this.min = this.minCritical;
        }
        if (this.maxCritical != null && this.maxCritical.doubleValue() > this.max.doubleValue()) {
            this.max = this.maxCritical;
        }
        this.fireDatasetChanged();
    }
    
    public String getUnits() {
        return this.units;
    }
    
    public void setUnits(final String units) {
        this.units = units;
        this.fireDatasetChanged();
    }
    
    public int getBorderType() {
        return this.borderType;
    }
    
    public void setBorderType(final int borderType) {
        this.borderType = borderType;
        this.fireDatasetChanged();
    }
}

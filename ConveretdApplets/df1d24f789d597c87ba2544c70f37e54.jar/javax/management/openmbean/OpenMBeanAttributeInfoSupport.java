// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Collection;
import java.util.Set;
import java.io.Serializable;
import javax.management.MBeanAttributeInfo;

public class OpenMBeanAttributeInfoSupport extends MBeanAttributeInfo implements OpenMBeanAttributeInfo, Serializable
{
    private static final long serialVersionUID = -4867215622149721849L;
    private OpenType openType;
    private Object defaultValue;
    private Set legalValues;
    private Comparable minValue;
    private Comparable maxValue;
    private transient int cachedHashCode;
    private transient String cachedToString;
    
    public OpenMBeanAttributeInfoSupport(final String name, final String description, final OpenType openType, final boolean isReadable, final boolean isWritable, final boolean isIs) {
        super(name, (openType == null) ? null : openType.getClassName(), description, isReadable, isWritable, isIs);
        try {
            this.init(name, description, openType, isReadable, isWritable, isIs, null, null, null, null);
        }
        catch (OpenDataException ex) {}
    }
    
    public OpenMBeanAttributeInfoSupport(final String name, final String description, final OpenType openType, final boolean isReadable, final boolean isWritable, final boolean isIs, final Object defaultValue) throws OpenDataException {
        super(name, (openType == null) ? null : openType.getClassName(), description, isReadable, isWritable, isIs);
        this.init(name, description, openType, isReadable, isWritable, isIs, defaultValue, null, null, null);
    }
    
    public OpenMBeanAttributeInfoSupport(final String name, final String description, final OpenType openType, final boolean isReadable, final boolean isWritable, final boolean isIs, final Object defaultValue, final Object[] legalValues) throws OpenDataException {
        super(name, (openType == null) ? null : openType.getClassName(), description, isReadable, isWritable, isIs);
        this.init(name, description, openType, isReadable, isWritable, isIs, defaultValue, legalValues, null, null);
    }
    
    public OpenMBeanAttributeInfoSupport(final String name, final String description, final OpenType openType, final boolean isReadable, final boolean isWritable, final boolean isIs, final Object defaultValue, final Comparable minValue, final Comparable maxValue) throws OpenDataException {
        super(name, (openType == null) ? null : openType.getClassName(), description, isReadable, isWritable, isIs);
        this.init(name, description, openType, isReadable, isWritable, isIs, defaultValue, null, minValue, maxValue);
    }
    
    public Object getDefaultValue() {
        return this.defaultValue;
    }
    
    public Set getLegalValues() {
        return this.legalValues;
    }
    
    public Comparable getMinValue() {
        return this.minValue;
    }
    
    public Comparable getMaxValue() {
        return this.maxValue;
    }
    
    public OpenType getOpenType() {
        return this.openType;
    }
    
    public boolean hasDefaultValue() {
        return this.defaultValue != null;
    }
    
    public boolean hasLegalValues() {
        return this.legalValues != null;
    }
    
    public boolean hasMinValue() {
        return this.minValue != null;
    }
    
    public boolean hasMaxValue() {
        return this.maxValue != null;
    }
    
    public boolean isValue(final Object obj) {
        return this.openType.isValue(obj) && (this.minValue == null || this.minValue.compareTo(obj) <= 0) && (this.maxValue == null || this.maxValue.compareTo(obj) >= 0) && (this.legalValues == null || this.legalValues.contains(obj));
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OpenMBeanAttributeInfo)) {
            return false;
        }
        final OpenMBeanAttributeInfo other = (OpenMBeanAttributeInfo)obj;
        if (!this.getName().equals(other.getName())) {
            return false;
        }
        if (!this.getOpenType().equals(other.getOpenType())) {
            return false;
        }
        if (this.isReadable() != other.isReadable()) {
            return false;
        }
        if (this.isWritable() != other.isWritable()) {
            return false;
        }
        if (this.isIs() != other.isIs()) {
            return false;
        }
        if (!this.hasDefaultValue() && other.hasDefaultValue()) {
            return false;
        }
        if (this.hasDefaultValue() && !this.getDefaultValue().equals(other.getDefaultValue())) {
            return false;
        }
        if (!this.hasMinValue() && other.hasMinValue()) {
            return false;
        }
        if (this.hasMinValue() && !this.getMinValue().equals(other.getMinValue())) {
            return false;
        }
        if (!this.hasMaxValue() && other.hasMaxValue()) {
            return false;
        }
        if (this.hasMaxValue() && !this.getMaxValue().equals(other.getMaxValue())) {
            return false;
        }
        if (!this.hasLegalValues() && other.hasLegalValues()) {
            return false;
        }
        if (this.hasLegalValues()) {
            final Set otherLegal = other.getLegalValues();
            if (otherLegal == null) {
                return false;
            }
            final Set thisLegal = this.getLegalValues();
            if (thisLegal.size() != otherLegal.size()) {
                return false;
            }
            if (!thisLegal.containsAll(otherLegal)) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        this.cachedHashCode = this.getName().hashCode();
        this.cachedHashCode += this.getOpenType().hashCode();
        if (this.defaultValue != null) {
            this.cachedHashCode += this.getDefaultValue().hashCode();
        }
        if (this.minValue != null) {
            this.cachedHashCode += this.getMinValue().hashCode();
        }
        if (this.maxValue != null) {
            this.cachedHashCode += this.getMaxValue().hashCode();
        }
        if (this.legalValues != null) {
            this.cachedHashCode += this.getLegalValues().hashCode();
        }
        return this.cachedHashCode;
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": name=");
        buffer.append(this.getName());
        buffer.append(", openType=");
        buffer.append(this.getOpenType());
        buffer.append(", isWritable=");
        buffer.append(this.isWritable());
        buffer.append(", isReadable=");
        buffer.append(this.isReadable());
        buffer.append(", isIs=");
        buffer.append(this.isIs());
        buffer.append(", defaultValue=");
        buffer.append(this.getDefaultValue());
        buffer.append(", minValue=");
        buffer.append(this.getMinValue());
        buffer.append(", maxValue=");
        buffer.append(this.getMaxValue());
        buffer.append(", legalValues=");
        buffer.append(this.getLegalValues());
        return this.cachedToString = buffer.toString();
    }
    
    private void init(final String name, final String Description, final OpenType openType, final boolean isReadable, final boolean isWritable, final boolean isIs, final Object defaultValue, final Object[] legalValues, final Comparable minValue, final Comparable maxValue) throws OpenDataException {
        if (name == null || name.trim().length() == 0) {
            throw new IllegalArgumentException("null or empty name");
        }
        if (this.description == null || this.description.trim().length() == 0) {
            throw new IllegalArgumentException("null or empty description");
        }
        if (openType == null) {
            throw new IllegalArgumentException("null open type");
        }
        this.openType = openType;
        if (defaultValue != null && (openType instanceof ArrayType || openType instanceof TabularType)) {
            throw new OpenDataException("default value is not supported for " + openType.getClass().getName());
        }
        if (defaultValue != null && !openType.isValue(defaultValue)) {
            throw new OpenDataException("default value is not valid for " + openType.getClass().getName());
        }
        if (legalValues != null && legalValues.length != 0) {
            if (openType instanceof ArrayType || openType instanceof TabularType) {
                throw new OpenDataException("legal values are not supported for " + openType.getClass().getName());
            }
            final HashSet legals = new HashSet(legalValues.length);
            for (int i = 0; i < legalValues.length; ++i) {
                if (!openType.isValue(legalValues[i])) {
                    throw new OpenDataException("legal value " + legalValues[i] + " at index " + i + " is not valid for " + openType.getClass().getName());
                }
                legals.add(legalValues[i]);
            }
            if (defaultValue != null && !legals.contains(defaultValue)) {
                throw new OpenDataException("default value is not a legal value");
            }
            this.legalValues = Collections.unmodifiableSet((Set<?>)legals);
        }
        if (minValue != null && !openType.isValue(minValue)) {
            throw new OpenDataException("minimum value is not valid for " + openType.getClass().getName());
        }
        if (defaultValue != null && minValue != null && minValue.compareTo(defaultValue) > 0) {
            throw new OpenDataException("the default value is less than the minimum value ");
        }
        if (maxValue != null && !openType.isValue(maxValue)) {
            throw new OpenDataException("maximum value is not valid for " + openType.getClass().getName());
        }
        if (defaultValue != null && maxValue != null && maxValue.compareTo(defaultValue) < 0) {
            throw new OpenDataException("the default value is greater than the maximum value ");
        }
        if (minValue != null && maxValue != null && minValue.compareTo(maxValue) > 0) {
            throw new OpenDataException("the minimum value is greater than the maximum value ");
        }
        this.defaultValue = defaultValue;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }
}

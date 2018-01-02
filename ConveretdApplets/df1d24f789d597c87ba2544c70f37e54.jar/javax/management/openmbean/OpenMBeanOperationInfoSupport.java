// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Arrays;
import javax.management.MBeanParameterInfo;
import java.io.Serializable;
import javax.management.MBeanOperationInfo;

public class OpenMBeanOperationInfoSupport extends MBeanOperationInfo implements OpenMBeanOperationInfo, Serializable
{
    private static final long serialVersionUID = 4996859732565369366L;
    private OpenType returnOpenType;
    private transient int cachedHashCode;
    private transient String cachedToString;
    
    private static MBeanParameterInfo[] convertArray(final OpenMBeanParameterInfo[] array) {
        if (array == null) {
            return null;
        }
        final MBeanParameterInfo[] result = new MBeanParameterInfo[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }
    
    public OpenMBeanOperationInfoSupport(final String name, final String description, final OpenMBeanParameterInfo[] signature, final OpenType returnOpenType, final int impact) {
        super(name, description, convertArray(signature), (returnOpenType == null) ? null : returnOpenType.getClassName(), impact);
        if (name == null) {
            throw new IllegalArgumentException("null name");
        }
        if (name.trim().length() == 0) {
            throw new IllegalArgumentException("empty name");
        }
        if (description == null) {
            throw new IllegalArgumentException("null description");
        }
        if (description.trim().length() == 0) {
            throw new IllegalArgumentException("empty description");
        }
        if (returnOpenType == null) {
            throw new IllegalArgumentException("null return open type");
        }
        if (impact != 1 && impact != 2 && impact != 0) {
            throw new IllegalArgumentException("Invalid action");
        }
        this.returnOpenType = returnOpenType;
    }
    
    public OpenType getReturnOpenType() {
        return this.returnOpenType;
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OpenMBeanOperationInfoSupport)) {
            return false;
        }
        final OpenMBeanOperationInfo other = (OpenMBeanOperationInfo)obj;
        return this.getName().equals(other.getName()) && this.getReturnOpenType().equals(other.getReturnOpenType()) && Arrays.asList(this.getSignature()).equals(Arrays.asList(other.getSignature())) && this.getImpact() == other.getImpact();
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        this.cachedHashCode = this.getName().hashCode();
        this.cachedHashCode += this.getReturnOpenType().hashCode();
        this.cachedHashCode += Arrays.asList(this.getSignature()).hashCode();
        return this.cachedHashCode += this.getImpact();
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": name=");
        buffer.append(this.getName());
        buffer.append(", returnOpenType=");
        buffer.append(this.getReturnOpenType());
        buffer.append(", signature=");
        buffer.append(Arrays.asList(this.getSignature()));
        buffer.append(", impact=");
        switch (this.getImpact()) {
            case 1: {
                buffer.append("ACTION");
                break;
            }
            case 2: {
                buffer.append("ACTION_INFO");
                break;
            }
            case 0: {
                buffer.append("INFO");
                break;
            }
            default: {
                buffer.append("UNKNOWN");
                break;
            }
        }
        return this.cachedToString = buffer.toString();
    }
}

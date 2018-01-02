// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Arrays;
import javax.management.MBeanParameterInfo;
import java.io.Serializable;
import javax.management.MBeanConstructorInfo;

public class OpenMBeanConstructorInfoSupport extends MBeanConstructorInfo implements OpenMBeanConstructorInfo, Serializable
{
    private static final long serialVersionUID = -4400441579007477003L;
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
    
    public OpenMBeanConstructorInfoSupport(final String name, final String description, final OpenMBeanParameterInfo[] signature) {
        super(name, description, convertArray(signature));
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
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OpenMBeanConstructorInfoSupport)) {
            return false;
        }
        final OpenMBeanConstructorInfo other = (OpenMBeanConstructorInfo)obj;
        return this.getName().equals(other.getName()) && Arrays.asList(this.getSignature()).equals(Arrays.asList(other.getSignature()));
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        this.cachedHashCode = this.getName().hashCode();
        return this.cachedHashCode += Arrays.asList(this.getSignature()).hashCode();
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": name=");
        buffer.append(this.getName());
        buffer.append(", signature=");
        buffer.append(Arrays.asList(this.getSignature()));
        return this.cachedToString = buffer.toString();
    }
}

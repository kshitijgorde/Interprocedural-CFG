// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Arrays;
import java.lang.reflect.Constructor;
import java.io.Serializable;

public class MBeanConstructorInfo extends MBeanFeatureInfo implements Serializable, Cloneable
{
    private static final long serialVersionUID = 4433990064191844427L;
    private MBeanParameterInfo[] signature;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanConstructorInfo(final String description, final Constructor constructor) {
        super(constructor.getName(), description);
        this.signature = null;
        final Class[] sign = constructor.getParameterTypes();
        this.signature = new MBeanParameterInfo[sign.length];
        for (int i = 0; i < sign.length; ++i) {
            final String name = sign[i].getName();
            this.signature[i] = new MBeanParameterInfo(name, name, "MBean Constructor Parameter.");
        }
    }
    
    public MBeanConstructorInfo(final String name, final String description, final MBeanParameterInfo[] signature) throws IllegalArgumentException {
        super(name, description);
        this.signature = null;
        this.signature = ((null == signature) ? new MBeanParameterInfo[0] : signature.clone());
    }
    
    public synchronized Object clone() {
        MBeanConstructorInfo clone = null;
        try {
            clone = (MBeanConstructorInfo)super.clone();
            clone.signature = this.signature.clone();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
    
    public MBeanParameterInfo[] getSignature() {
        return this.signature.clone();
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanConstructorInfo)) {
            return false;
        }
        final MBeanConstructorInfo other = (MBeanConstructorInfo)object;
        if (!super.equals(other)) {
            return false;
        }
        final MBeanParameterInfo[] thisParams = this.getSignature();
        final MBeanParameterInfo[] otherParams = other.getSignature();
        if (thisParams.length != otherParams.length) {
            return false;
        }
        for (int i = 0; i < thisParams.length; ++i) {
            if (!thisParams[i].equals(otherParams[i])) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        if (this.cacheHashCode == 0) {
            this.cacheHashCode = super.hashCode();
        }
        return this.cacheHashCode;
    }
    
    public String toString() {
        if (this.cacheString == null) {
            final StringBuffer buffer = new StringBuffer(100);
            buffer.append(this.getClass().getName()).append(":");
            buffer.append(" name=").append(this.getName());
            buffer.append(" description=").append(this.getDescription());
            buffer.append(" signature=").append(Arrays.asList(this.signature));
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
}

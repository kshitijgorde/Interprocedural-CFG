// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Arrays;
import org.jboss.mx.util.MetaDataUtil;
import java.lang.reflect.Method;
import java.io.Serializable;

public class MBeanOperationInfo extends MBeanFeatureInfo implements Serializable, Cloneable
{
    private static final long serialVersionUID = -6178860474881375330L;
    public static final int INFO = 0;
    public static final int ACTION = 1;
    public static final int ACTION_INFO = 2;
    public static final int UNKNOWN = 3;
    private int impact;
    private MBeanParameterInfo[] signature;
    private String type;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanOperationInfo(final String description, final Method method) throws IllegalArgumentException {
        super(method.getName(), description);
        this.impact = 3;
        this.signature = null;
        this.type = null;
        this.type = method.getReturnType().getName();
        final Class[] sign = method.getParameterTypes();
        this.signature = new MBeanParameterInfo[sign.length];
        for (int i = 0; i < sign.length; ++i) {
            final String name = sign[i].getName();
            this.signature[i] = new MBeanParameterInfo(name, name, "MBean Operation Parameter.");
        }
    }
    
    public MBeanOperationInfo(final String name, final String description, final MBeanParameterInfo[] inSignature, final String returnType, final int impact) throws IllegalArgumentException {
        super(name, description);
        this.impact = 3;
        this.signature = null;
        this.type = null;
        if (!MetaDataUtil.isValidJavaType(returnType)) {
            throw new IllegalArgumentException("Return type is not a valid java identifier (or is reserved): " + returnType);
        }
        if (impact != 0 && impact != 1 && impact != 2 && impact != 3) {
            throw new IllegalArgumentException("Impact is invalid: " + impact);
        }
        this.signature = ((null == inSignature) ? new MBeanParameterInfo[0] : inSignature.clone());
        this.type = returnType;
        this.impact = impact;
    }
    
    public String getReturnType() {
        return this.type;
    }
    
    public MBeanParameterInfo[] getSignature() {
        return this.signature.clone();
    }
    
    public int getImpact() {
        return this.impact;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanOperationInfo)) {
            return false;
        }
        final MBeanOperationInfo other = (MBeanOperationInfo)object;
        if (!super.equals(other)) {
            return false;
        }
        if (!this.getReturnType().equals(other.getReturnType())) {
            return false;
        }
        if (this.getImpact() != other.getImpact()) {
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
            this.cacheHashCode += this.getReturnType().hashCode();
            this.cacheHashCode += this.impact;
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
            buffer.append(" returnType=").append(this.getReturnType());
            buffer.append(" impact=");
            switch (this.impact) {
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
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
    
    public synchronized Object clone() {
        MBeanOperationInfo clone = null;
        try {
            clone = (MBeanOperationInfo)super.clone();
            clone.signature = this.signature.clone();
            clone.type = this.type;
            clone.impact = this.impact;
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
}

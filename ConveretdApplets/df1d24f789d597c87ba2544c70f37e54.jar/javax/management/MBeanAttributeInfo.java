// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.Serialization;
import java.lang.reflect.Method;
import java.io.Serializable;

public class MBeanAttributeInfo extends MBeanFeatureInfo implements Serializable, Cloneable
{
    private static final long serialVersionUID;
    private String attributeType;
    private boolean isRead;
    private boolean isWrite;
    private boolean is;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanAttributeInfo(final String name, final String type, final String description, final boolean isReadable, final boolean isWritable, final boolean isIs) throws IllegalArgumentException {
        super(name, description);
        this.attributeType = null;
        this.isRead = false;
        this.isWrite = false;
        this.is = false;
        if (isIs && !type.equals(Boolean.TYPE.getName()) && !type.equals(Boolean.class.getName())) {
            throw new IllegalArgumentException("Cannot have isIs for a non boolean/Boolean type");
        }
        this.attributeType = type;
        this.isRead = isReadable;
        this.isWrite = isWritable;
        this.is = isIs;
    }
    
    public MBeanAttributeInfo(final String name, final String description, final Method getter, final Method setter) throws IntrospectionException {
        super(name, description);
        this.attributeType = null;
        this.isRead = false;
        this.isWrite = false;
        this.is = false;
        if (getter != null) {
            if (getter.getParameterTypes().length != 0) {
                throw new IntrospectionException("Expecting getter method to be of the form 'AttributeType getAttributeName()': found getter with " + getter.getParameterTypes().length + " parameters.");
            }
            if (getter.getReturnType() == Void.TYPE) {
                throw new IntrospectionException("Expecting getter method to be of the form 'AttributeType getAttributeName()': found getter with void return type.");
            }
            this.isRead = true;
            if (getter.getName().startsWith("is")) {
                this.is = true;
            }
            this.attributeType = getter.getReturnType().getName();
        }
        if (setter != null) {
            if (setter.getParameterTypes().length != 1) {
                throw new IntrospectionException("Expecting the setter method to be of the form 'void setAttributeName(AttributeType value)': found setter with " + setter.getParameterTypes().length + " parameters.");
            }
            if (setter.getReturnType() != Void.TYPE) {
                throw new IntrospectionException("Expecting the setter method to be of the form 'void setAttributeName(AttributeType value)': found setter with " + setter.getReturnType() + " return type.");
            }
            this.isWrite = true;
            if (this.attributeType == null) {
                try {
                    this.attributeType = setter.getParameterTypes()[0].getName();
                }
                catch (ArrayIndexOutOfBoundsException e) {
                    throw new IntrospectionException("Attribute setter is lacking type: " + name);
                }
            }
            if (!this.attributeType.equals(setter.getParameterTypes()[0].getName())) {
                throw new IntrospectionException("Attribute type mismatch: " + name);
            }
        }
    }
    
    public synchronized Object clone() {
        MBeanAttributeInfo clone = null;
        try {
            clone = (MBeanAttributeInfo)super.clone();
            clone.attributeType = this.attributeType;
            clone.isRead = this.isRead;
            clone.isWrite = this.isWrite;
            clone.is = this.is;
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
    
    public String getType() {
        return this.attributeType;
    }
    
    public boolean isReadable() {
        return this.isRead;
    }
    
    public boolean isWritable() {
        return this.isWrite;
    }
    
    public boolean isIs() {
        return this.is;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanAttributeInfo)) {
            return false;
        }
        final MBeanAttributeInfo other = (MBeanAttributeInfo)object;
        return super.equals(other) && this.getType().equals(other.getType()) && this.isReadable() == other.isReadable() && this.isWritable() == other.isWritable() && this.isIs() == other.isIs();
    }
    
    public int hashCode() {
        if (this.cacheHashCode == 0) {
            this.cacheHashCode = super.hashCode();
            this.cacheHashCode += this.getType().hashCode();
        }
        return this.cacheHashCode;
    }
    
    public String toString() {
        if (this.cacheString == null) {
            final StringBuffer buffer = new StringBuffer(100);
            buffer.append(this.getClass().getName()).append(":");
            buffer.append(" name=").append(this.getName());
            buffer.append(" description=").append(this.getDescription());
            buffer.append(" type=").append(this.getType());
            buffer.append(" Readable=").append(this.isReadable());
            buffer.append(" Writable=").append(this.isWritable());
            buffer.append(" isIs=").append(this.isIs());
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
    
    static {
        switch (Serialization.version) {
            case 10: {
                serialVersionUID = 7043855487133450673L;
                break;
            }
            default: {
                serialVersionUID = 8644704819898565848L;
                break;
            }
        }
    }
}

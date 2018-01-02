// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public class MBeanParameterInfo extends MBeanFeatureInfo implements Serializable, Cloneable
{
    private static final long serialVersionUID = 7432616882776782338L;
    private String type;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanParameterInfo(final String name, final String type, final String description) throws IllegalArgumentException {
        super(name, description);
        this.type = null;
        this.type = type;
    }
    
    public String getType() {
        return this.type;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanParameterInfo)) {
            return false;
        }
        final MBeanParameterInfo other = (MBeanParameterInfo)object;
        return super.equals(other) && this.getType().equals(other.getType());
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
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
    
    public Object clone() {
        MBeanParameterInfo clone = null;
        try {
            clone = (MBeanParameterInfo)super.clone();
            clone.type = this.getType();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.io.Serializable;

public class MBeanFeatureInfo implements Serializable
{
    private static final long serialVersionUID = 3952882688968447265L;
    protected String name;
    protected String description;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanFeatureInfo(final String name, final String description) throws IllegalArgumentException {
        this.name = null;
        this.description = null;
        this.name = name;
        this.description = description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanFeatureInfo)) {
            return false;
        }
        final MBeanFeatureInfo other = (MBeanFeatureInfo)object;
        boolean equals = false;
        if (this.getName().equals(other.getName())) {
            final String thisDescription = this.getDescription();
            final String otherDescription = other.getDescription();
            if (thisDescription == null) {
                equals = (thisDescription == otherDescription);
            }
            else {
                equals = thisDescription.equals(otherDescription);
            }
        }
        return equals;
    }
    
    public int hashCode() {
        if (this.cacheHashCode == 0) {
            this.cacheHashCode = this.getName().hashCode();
            if (this.getDescription() != null) {
                this.cacheHashCode += this.getDescription().hashCode();
            }
        }
        return this.cacheHashCode;
    }
    
    public String toString() {
        if (this.cacheString == null) {
            final StringBuffer buffer = new StringBuffer(100);
            buffer.append(this.getClass().getName()).append(":");
            buffer.append(" name=").append(this.getName());
            buffer.append(" description=").append(this.getDescription());
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
}

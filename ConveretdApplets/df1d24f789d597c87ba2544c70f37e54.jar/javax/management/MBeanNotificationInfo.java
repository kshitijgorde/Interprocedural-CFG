// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Arrays;
import java.io.Serializable;

public class MBeanNotificationInfo extends MBeanFeatureInfo implements Cloneable, Serializable
{
    private static final long serialVersionUID = -3888371564530107064L;
    private String[] types;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanNotificationInfo(final String[] notifsType, final String name, final String description) throws IllegalArgumentException {
        super(name, description);
        this.types = null;
        this.types = ((null == notifsType) ? new String[0] : notifsType.clone());
    }
    
    public String[] getNotifTypes() {
        return this.types.clone();
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanNotificationInfo)) {
            return false;
        }
        final MBeanNotificationInfo other = (MBeanNotificationInfo)object;
        if (!super.equals(other)) {
            return false;
        }
        final String[] thisTypes = this.getNotifTypes();
        final String[] otherTypes = other.getNotifTypes();
        if (thisTypes.length != otherTypes.length) {
            return false;
        }
        for (int i = 0; i < thisTypes.length; ++i) {
            if (!thisTypes[i].equals(otherTypes[i])) {
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
            buffer.append(" types=").append(Arrays.asList(this.types));
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
    
    public Object clone() {
        MBeanNotificationInfo clone = null;
        try {
            clone = (MBeanNotificationInfo)super.clone();
            clone.types = this.getNotifTypes();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import java.util.Arrays;
import java.io.Serializable;

public class MBeanInfo implements Cloneable, Serializable
{
    private static final long serialVersionUID = -6451021435135161911L;
    private String className;
    private String description;
    private MBeanAttributeInfo[] attributes;
    private MBeanConstructorInfo[] constructors;
    private MBeanOperationInfo[] operations;
    private MBeanNotificationInfo[] notifications;
    private transient String cacheString;
    private transient int cacheHashCode;
    
    public MBeanInfo(final String className, final String description, final MBeanAttributeInfo[] attributes, final MBeanConstructorInfo[] constructors, final MBeanOperationInfo[] operations, final MBeanNotificationInfo[] notifications) throws IllegalArgumentException {
        this.className = null;
        this.description = null;
        this.attributes = null;
        this.constructors = null;
        this.operations = null;
        this.notifications = null;
        this.className = className;
        this.description = description;
        this.attributes = ((null == attributes) ? new MBeanAttributeInfo[0] : attributes.clone());
        this.constructors = ((null == constructors) ? new MBeanConstructorInfo[0] : constructors.clone());
        this.operations = ((null == operations) ? new MBeanOperationInfo[0] : operations.clone());
        this.notifications = ((null == notifications) ? new MBeanNotificationInfo[0] : notifications.clone());
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public MBeanAttributeInfo[] getAttributes() {
        return this.attributes.clone();
    }
    
    public MBeanOperationInfo[] getOperations() {
        return this.operations.clone();
    }
    
    public MBeanConstructorInfo[] getConstructors() {
        return this.constructors.clone();
    }
    
    public MBeanNotificationInfo[] getNotifications() {
        return this.notifications.clone();
    }
    
    public boolean equals(final Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || !(object instanceof MBeanInfo)) {
            return false;
        }
        final MBeanInfo other = (MBeanInfo)object;
        if (!this.getClassName().equals(other.getClassName())) {
            return false;
        }
        if (this.getDescription() != null && !this.getDescription().equals(other.getDescription())) {
            return false;
        }
        if (this.getDescription() == null && other.getDescription() != null) {
            return false;
        }
        final MBeanAttributeInfo[] thisAttrs = this.getAttributes();
        final MBeanAttributeInfo[] otherAttrs = other.getAttributes();
        if (thisAttrs.length != otherAttrs.length) {
            return false;
        }
        for (int i = 0; i < thisAttrs.length; ++i) {
            if (!thisAttrs[i].equals(otherAttrs[i])) {
                return false;
            }
        }
        final MBeanConstructorInfo[] thisCons = this.getConstructors();
        final MBeanConstructorInfo[] otherCons = other.getConstructors();
        if (thisCons.length != otherCons.length) {
            return false;
        }
        for (int j = 0; j < thisCons.length; ++j) {
            if (!thisCons[j].equals(otherCons[j])) {
                return false;
            }
        }
        final MBeanNotificationInfo[] thisNotfs = this.getNotifications();
        final MBeanNotificationInfo[] otherNotfs = other.getNotifications();
        if (thisNotfs.length != otherNotfs.length) {
            return false;
        }
        for (int k = 0; k < thisNotfs.length; ++k) {
            if (!thisNotfs[k].equals(otherNotfs[k])) {
                return false;
            }
        }
        final MBeanOperationInfo[] thisOpers = this.getOperations();
        final MBeanOperationInfo[] otherOpers = other.getOperations();
        if (thisOpers.length != otherOpers.length) {
            return false;
        }
        for (int l = 0; l < thisOpers.length; ++l) {
            if (!thisOpers[l].equals(otherOpers[l])) {
                return false;
            }
        }
        return true;
    }
    
    public int hashCode() {
        if (this.cacheHashCode == 0) {
            this.cacheHashCode = this.getClassName().hashCode();
            this.cacheHashCode += ((this.getDescription() != null) ? this.getDescription().hashCode() : 0);
        }
        return this.cacheHashCode;
    }
    
    public String toString() {
        if (this.cacheString == null) {
            final StringBuffer buffer = new StringBuffer(100);
            buffer.append(this.getClass().getName()).append(":");
            buffer.append(" className=").append(this.getClassName());
            buffer.append(" description=").append(this.getDescription());
            buffer.append(" attributes=").append(Arrays.asList(this.attributes));
            buffer.append(" constructors=").append(Arrays.asList(this.constructors));
            buffer.append(" notifications=").append(Arrays.asList(this.notifications));
            buffer.append(" operations=").append(Arrays.asList(this.operations));
            this.cacheString = buffer.toString();
        }
        return this.cacheString;
    }
    
    public Object clone() {
        MBeanInfo clone = null;
        try {
            clone = (MBeanInfo)super.clone();
            clone.className = this.getClassName();
            clone.description = this.getDescription();
            clone.attributes = this.getAttributes();
            clone.constructors = this.getConstructors();
            clone.operations = this.getOperations();
            clone.notifications = this.getNotifications();
        }
        catch (CloneNotSupportedException ex) {}
        return clone;
    }
}

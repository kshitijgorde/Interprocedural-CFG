// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import java.util.Collection;
import java.util.Arrays;
import javax.management.MBeanNotificationInfo;
import javax.management.MBeanOperationInfo;
import javax.management.MBeanConstructorInfo;
import javax.management.MBeanAttributeInfo;
import java.io.Serializable;
import javax.management.MBeanInfo;

public class OpenMBeanInfoSupport extends MBeanInfo implements OpenMBeanInfo, Serializable
{
    private static final long serialVersionUID = 4349395935420511492L;
    private transient int cachedHashCode;
    private transient String cachedToString;
    
    private static MBeanAttributeInfo[] convertArray(final OpenMBeanAttributeInfo[] array) {
        if (array == null) {
            return null;
        }
        final MBeanAttributeInfo[] result = new MBeanAttributeInfo[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }
    
    private static MBeanConstructorInfo[] convertArray(final OpenMBeanConstructorInfo[] array) {
        if (array == null) {
            return null;
        }
        final MBeanConstructorInfo[] result = new MBeanConstructorInfo[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }
    
    private static MBeanOperationInfo[] convertArray(final OpenMBeanOperationInfo[] array) {
        if (array == null) {
            return null;
        }
        final MBeanOperationInfo[] result = new MBeanOperationInfo[array.length];
        System.arraycopy(array, 0, result, 0, array.length);
        return result;
    }
    
    public OpenMBeanInfoSupport(final String className, final String description, final OpenMBeanAttributeInfo[] attributes, final OpenMBeanConstructorInfo[] constructors, final OpenMBeanOperationInfo[] operations, final MBeanNotificationInfo[] notifications) {
        super(className, description, convertArray(attributes), convertArray(constructors), convertArray(operations), notifications);
    }
    
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof OpenMBeanInfoSupport)) {
            return false;
        }
        final OpenMBeanInfo other = (OpenMBeanInfo)obj;
        return this.getClassName().equals(other.getClassName()) && this.compareArray(this.getAttributes(), other.getAttributes()) && this.compareArray(this.getConstructors(), other.getConstructors()) && this.compareArray(this.getNotifications(), other.getNotifications()) && this.compareArray(this.getOperations(), other.getOperations());
    }
    
    public int hashCode() {
        if (this.cachedHashCode != 0) {
            return this.cachedHashCode;
        }
        this.cachedHashCode = this.getClassName().hashCode();
        final MBeanAttributeInfo[] attrs = this.getAttributes();
        for (int i = 0; i < attrs.length; ++i) {
            this.cachedHashCode += attrs[i].hashCode();
        }
        final MBeanConstructorInfo[] ctors = this.getConstructors();
        for (int j = 0; j < ctors.length; ++j) {
            this.cachedHashCode += ctors[j].hashCode();
        }
        final MBeanNotificationInfo[] notify = this.getNotifications();
        for (int k = 0; k < notify.length; ++k) {
            this.cachedHashCode += notify[k].hashCode();
        }
        final MBeanOperationInfo[] ops = this.getOperations();
        for (int l = 0; l < ops.length; ++l) {
            this.cachedHashCode += ops[l].hashCode();
        }
        return this.cachedHashCode;
    }
    
    public String toString() {
        if (this.cachedToString != null) {
            return this.cachedToString;
        }
        final StringBuffer buffer = new StringBuffer(this.getClass().getName());
        buffer.append(": className=");
        buffer.append(this.getClassName());
        buffer.append(", attributes=");
        buffer.append(Arrays.asList(this.getAttributes()));
        buffer.append(", constructors=");
        buffer.append(Arrays.asList(this.getConstructors()));
        buffer.append(", notifications=");
        buffer.append(Arrays.asList(this.getNotifications()));
        buffer.append(", operations=");
        buffer.append(Arrays.asList(this.getOperations()));
        return this.cachedToString = buffer.toString();
    }
    
    private boolean compareArray(final Object[] one, final Object[] two) {
        return one.length == two.length && Arrays.asList(one).containsAll(Arrays.asList(two));
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import javax.management.MBeanParameterInfo;

public interface OpenMBeanConstructorInfo
{
    String getDescription();
    
    String getName();
    
    MBeanParameterInfo[] getSignature();
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    String toString();
}

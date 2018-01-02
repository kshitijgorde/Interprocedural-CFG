// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

import javax.management.MBeanParameterInfo;

public interface OpenMBeanOperationInfo
{
    String getDescription();
    
    String getName();
    
    MBeanParameterInfo[] getSignature();
    
    int getImpact();
    
    String getReturnType();
    
    OpenType getReturnOpenType();
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    String toString();
}

// 
// Decompiled by Procyon v0.5.30
// 

package javax.management.openmbean;

public interface OpenMBeanAttributeInfo extends OpenMBeanParameterInfo
{
    boolean isReadable();
    
    boolean isWritable();
    
    boolean isIs();
    
    boolean equals(final Object p0);
    
    int hashCode();
    
    String toString();
}

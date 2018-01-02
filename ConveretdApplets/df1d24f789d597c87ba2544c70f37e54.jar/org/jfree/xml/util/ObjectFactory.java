// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public interface ObjectFactory
{
    GenericObjectFactory getFactoryForClass(final Class p0);
    
    boolean isGenericHandler(final Class p0);
    
    MultiplexMappingDefinition getMultiplexDefinition(final Class p0);
    
    ManualMappingDefinition getManualMappingDefinition(final Class p0);
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

import java.util.HashMap;

public class SimpleObjectFactory implements ObjectFactory
{
    private HashMap objectMappings;
    private HashMap manualMappings;
    private HashMap multiplexMappings;
    
    public SimpleObjectFactory() {
        this.objectMappings = new HashMap();
        this.manualMappings = new HashMap();
        this.multiplexMappings = new HashMap();
    }
    
    public void addManualMapping(final ManualMappingDefinition manualMappingDefinition) {
        this.manualMappings.put(manualMappingDefinition.getBaseClass(), manualMappingDefinition);
    }
    
    public void addGenericHandler(final GenericObjectFactory genericObjectFactory) {
        this.objectMappings.put(genericObjectFactory.getBaseClass(), genericObjectFactory);
    }
    
    public void addMultiplexMapping(final MultiplexMappingDefinition multiplexMappingDefinition) {
        this.multiplexMappings.put(multiplexMappingDefinition.getBaseClass(), multiplexMappingDefinition);
    }
    
    public void clear() {
        this.objectMappings.clear();
        this.manualMappings.clear();
        this.multiplexMappings.clear();
    }
    
    public GenericObjectFactory getFactoryForClass(final Class clazz) {
        final GenericObjectFactory genericObjectFactory = this.objectMappings.get(clazz);
        if (genericObjectFactory == null) {
            return null;
        }
        return genericObjectFactory.getInstance();
    }
    
    public ManualMappingDefinition getManualMappingDefinition(final Class clazz) {
        return this.manualMappings.get(clazz);
    }
    
    public MultiplexMappingDefinition getMultiplexDefinition(final Class clazz) {
        return this.multiplexMappings.get(clazz);
    }
    
    public boolean isGenericHandler(final Class clazz) {
        return this.objectMappings.containsKey(clazz);
    }
}

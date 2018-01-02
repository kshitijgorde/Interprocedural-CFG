// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

import org.jfree.util.Log;
import java.util.ArrayList;
import java.util.HashMap;

public class MappingModel
{
    private HashMap mappingInfos;
    private ArrayList manualMappings;
    private ArrayList multiplexMappings;
    
    public MappingModel() {
        this.mappingInfos = new HashMap();
        this.manualMappings = new ArrayList();
        this.multiplexMappings = new ArrayList();
    }
    
    public MultiplexMappingInfo[] getMultiplexMapping() {
        return this.multiplexMappings.toArray(new MultiplexMappingInfo[this.multiplexMappings.size()]);
    }
    
    public ManualMappingInfo[] getManualMapping() {
        return this.manualMappings.toArray(new ManualMappingInfo[this.manualMappings.size()]);
    }
    
    public void addManualMapping(final ManualMappingInfo manualMappingInfo) {
        if (!this.mappingInfos.containsKey(manualMappingInfo.getBaseClass())) {
            this.manualMappings.add(manualMappingInfo);
            this.mappingInfos.put(manualMappingInfo.getBaseClass(), manualMappingInfo);
        }
        else {
            if (!(this.mappingInfos.get(manualMappingInfo.getBaseClass()) instanceof ManualMappingInfo)) {
                throw new IllegalArgumentException("This mapping is already a multiplex mapping.");
            }
            Log.info("Duplicate manual mapping: " + manualMappingInfo.getBaseClass());
        }
    }
    
    public void addMultiplexMapping(final MultiplexMappingInfo multiplexMappingInfo) {
        if (!this.mappingInfos.containsKey(multiplexMappingInfo.getBaseClass())) {
            this.multiplexMappings.add(multiplexMappingInfo);
            this.mappingInfos.put(multiplexMappingInfo.getBaseClass(), multiplexMappingInfo);
        }
        else {
            if (this.mappingInfos.get(multiplexMappingInfo.getBaseClass()) instanceof ManualMappingInfo) {
                throw new IllegalArgumentException("This mapping is already a manual mapping.");
            }
            Log.info("Duplicate Multiplex mapping: " + multiplexMappingInfo.getBaseClass(), new Exception());
        }
    }
    
    public MultiplexMappingInfo lookupMultiplexMapping(final Class clazz) {
        final MultiplexMappingInfo value = this.mappingInfos.get(clazz);
        if (value instanceof MultiplexMappingInfo) {
            return value;
        }
        return null;
    }
}

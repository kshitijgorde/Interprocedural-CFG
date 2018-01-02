// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.util;

public class ManualMappingDefinition
{
    private Class baseClass;
    private String readHandler;
    private String writeHandler;
    
    public ManualMappingDefinition(final Class baseClass, final String readHandler, final String writeHandler) {
        if (baseClass == null) {
            throw new NullPointerException("BaseClass must not be null");
        }
        if (readHandler == null && writeHandler == null) {
            throw new NullPointerException("At least one of readHandler or writeHandler must be defined.");
        }
        this.baseClass = baseClass;
        this.readHandler = readHandler;
        this.writeHandler = writeHandler;
    }
    
    public Class getBaseClass() {
        return this.baseClass;
    }
    
    public String getReadHandler() {
        return this.readHandler;
    }
    
    public String getWriteHandler() {
        return this.writeHandler;
    }
}

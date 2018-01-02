// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class ManualMappingInfo
{
    private Class baseClass;
    private Class readHandler;
    private Class writeHandler;
    private Comments comments;
    private String source;
    
    public ManualMappingInfo(final Class baseClass, final Class readHandler, final Class writeHandler) {
        this.baseClass = baseClass;
        this.readHandler = readHandler;
        this.writeHandler = writeHandler;
    }
    
    public Class getBaseClass() {
        return this.baseClass;
    }
    
    public Class getReadHandler() {
        return this.readHandler;
    }
    
    public Class getWriteHandler() {
        return this.writeHandler;
    }
    
    public Comments getComments() {
        return this.comments;
    }
    
    public void setComments(final Comments comments) {
        this.comments = comments;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(final String source) {
        this.source = source;
    }
}

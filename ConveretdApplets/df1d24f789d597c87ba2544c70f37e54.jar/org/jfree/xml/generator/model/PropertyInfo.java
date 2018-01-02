// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class PropertyInfo extends TypeInfo
{
    private boolean preserve;
    private boolean readMethodAvailable;
    private boolean writeMethodAvailable;
    private PropertyType propertyType;
    private String xmlName;
    private String xmlHandler;
    
    public PropertyInfo(final String s, final Class clazz) {
        super(s, clazz);
        this.propertyType = PropertyType.ELEMENT;
    }
    
    public boolean isPreserve() {
        return this.preserve;
    }
    
    public void setPreserve(final boolean preserve) {
        this.preserve = preserve;
    }
    
    public PropertyType getPropertyType() {
        return this.propertyType;
    }
    
    public void setPropertyType(final PropertyType propertyType) {
        if (propertyType == null) {
            throw new NullPointerException();
        }
        this.propertyType = propertyType;
    }
    
    public String getXmlHandler() {
        return this.xmlHandler;
    }
    
    public void setXmlHandler(final String xmlHandler) {
        this.xmlHandler = xmlHandler;
    }
    
    public String getXmlName() {
        return this.xmlName;
    }
    
    public void setXmlName(final String xmlName) {
        this.xmlName = xmlName;
    }
    
    public boolean isReadMethodAvailable() {
        return this.readMethodAvailable;
    }
    
    public void setReadMethodAvailable(final boolean readMethodAvailable) {
        this.readMethodAvailable = readMethodAvailable;
    }
    
    public boolean isWriteMethodAvailable() {
        return this.writeMethodAvailable;
    }
    
    public void setWriteMethodAvailable(final boolean writeMethodAvailable) {
        this.writeMethodAvailable = writeMethodAvailable;
    }
}

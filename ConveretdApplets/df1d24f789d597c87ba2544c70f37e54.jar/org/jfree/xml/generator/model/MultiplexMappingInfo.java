// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

import java.util.Arrays;

public class MultiplexMappingInfo
{
    private Class baseClass;
    private String typeAttribute;
    private TypeInfo[] childClasses;
    private Comments comments;
    private String source;
    
    public MultiplexMappingInfo(final Class clazz) {
        this(clazz, "type");
    }
    
    public MultiplexMappingInfo(final Class baseClass, final String typeAttribute) {
        if (baseClass == null) {
            throw new NullPointerException("BaseClass");
        }
        if (typeAttribute == null) {
            throw new NullPointerException("TypeAttribute");
        }
        this.baseClass = baseClass;
        this.typeAttribute = typeAttribute;
    }
    
    public Class getBaseClass() {
        return this.baseClass;
    }
    
    public String getTypeAttribute() {
        return this.typeAttribute;
    }
    
    public TypeInfo[] getChildClasses() {
        return this.childClasses;
    }
    
    public void setChildClasses(final TypeInfo[] childClasses) {
        this.childClasses = childClasses;
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
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MultiplexMappingInfo)) {
            return false;
        }
        final MultiplexMappingInfo multiplexMappingInfo = (MultiplexMappingInfo)o;
        return this.baseClass.equals(multiplexMappingInfo.baseClass) && Arrays.equals(this.childClasses, multiplexMappingInfo.childClasses) && this.typeAttribute.equals(multiplexMappingInfo.typeAttribute);
    }
    
    public int hashCode() {
        return 29 * this.baseClass.hashCode() + this.typeAttribute.hashCode();
    }
}

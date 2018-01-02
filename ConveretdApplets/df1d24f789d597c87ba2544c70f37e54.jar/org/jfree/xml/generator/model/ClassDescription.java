// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class ClassDescription
{
    private PropertyInfo[] properties;
    private TypeInfo[] constructorDescription;
    private Class objectClass;
    private String description;
    private String registerKey;
    private Class superClass;
    private boolean preserve;
    private Comments comments;
    private String source;
    
    public ClassDescription(final Class objectClass) {
        if (objectClass == null) {
            throw new NullPointerException();
        }
        this.objectClass = objectClass;
    }
    
    public PropertyInfo[] getProperties() {
        return this.properties;
    }
    
    public void setProperties(final PropertyInfo[] properties) {
        this.properties = properties;
    }
    
    public Class getObjectClass() {
        return this.objectClass;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getName() {
        if (this.getObjectClass() == null) {
            return null;
        }
        return this.getObjectClass().getName();
    }
    
    public Class getSuperClass() {
        return this.superClass;
    }
    
    public void setSuperClass(final Class superClass) {
        this.superClass = superClass;
    }
    
    public boolean isPreserve() {
        return this.preserve;
    }
    
    public void setPreserve(final boolean preserve) {
        this.preserve = preserve;
    }
    
    public String getRegisterKey() {
        return this.registerKey;
    }
    
    public void setRegisterKey(final String registerKey) {
        this.registerKey = registerKey;
    }
    
    public TypeInfo[] getConstructorDescription() {
        return this.constructorDescription;
    }
    
    public void setConstructorDescription(final TypeInfo[] constructorDescription) {
        this.constructorDescription = constructorDescription;
    }
    
    public PropertyInfo getProperty(final String s) {
        if (this.properties == null) {
            return null;
        }
        for (int i = 0; i < this.properties.length; ++i) {
            if (this.properties[i].getName().equals(s)) {
                return this.properties[i];
            }
        }
        return null;
    }
    
    public boolean isUndefined() {
        return (this.properties == null || this.properties.length <= 0) && !this.isPreserve() && this.getRegisterKey() == null && (this.getConstructorDescription() == null || this.getConstructorDescription().length <= 0);
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

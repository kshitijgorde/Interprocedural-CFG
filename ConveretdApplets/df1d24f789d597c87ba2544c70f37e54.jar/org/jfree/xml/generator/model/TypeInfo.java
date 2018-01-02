// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class TypeInfo
{
    private String name;
    private Class type;
    private boolean nullable;
    private boolean constrained;
    private String description;
    private Comments comments;
    
    public TypeInfo(final String name, final Class type) {
        if (name == null) {
            throw new NullPointerException("Name");
        }
        this.name = name;
        this.type = type;
    }
    
    public Class getType() {
        return this.type;
    }
    
    public boolean isNullable() {
        return this.nullable;
    }
    
    public void setNullable(final boolean nullable) {
        this.nullable = nullable;
    }
    
    public boolean isConstrained() {
        return this.constrained;
    }
    
    public void setConstrained(final boolean constrained) {
        this.constrained = constrained;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Comments getComments() {
        return this.comments;
    }
    
    public void setComments(final Comments comments) {
        this.comments = comments;
    }
    
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeInfo)) {
            return false;
        }
        final TypeInfo typeInfo = (TypeInfo)o;
        return this.name.equals(typeInfo.name) && this.type.equals(typeInfo.type);
    }
    
    public int hashCode() {
        return 29 * (29 * (29 * (29 * this.name.hashCode() + this.type.hashCode()) + (this.nullable ? 1 : 0)) + (this.constrained ? 1 : 0)) + ((this.description != null) ? this.description.hashCode() : 0);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public class TypeNode
{
    protected String name;
    
    public TypeNode(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public boolean isPrimitive() {
        return false;
    }
    
    public boolean isVoid() {
        return false;
    }
    
    public String getWrapperName() {
        return this.name;
    }
    
    public String getFullyTypedName() {
        return this.name;
    }
    
    public boolean isTyped() {
        return false;
    }
    
    public boolean isArray() {
        return false;
    }
    
    public boolean equals(final Object other) {
        return other instanceof TypeNode && ((this.name == null && ((TypeNode)other).name == null) || this.name.equals(((TypeNode)other).name));
    }
    
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + ((this.name != null) ? this.name.hashCode() : 0);
        return hash;
    }
    
    public String toString() {
        return this.getFullyTypedName();
    }
}

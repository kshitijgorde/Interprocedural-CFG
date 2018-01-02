// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public class ArrayTypeNode extends ReferenceTypeNode
{
    protected TypeNode typeForArray;
    
    public ArrayTypeNode() {
        super(null);
    }
    
    public ArrayTypeNode(final TypeNode typeForArray) {
        this();
        this.typeForArray = typeForArray;
    }
    
    public void setTypeForArray(final TypeNode referenceType) {
        if (this.typeForArray != null && this.typeForArray instanceof ArrayTypeNode) {
            ((ArrayTypeNode)this.typeForArray).setTypeForArray(referenceType);
        }
        else {
            this.typeForArray = referenceType;
        }
    }
    
    public boolean equals(final Object other) {
        return other != null && other instanceof ArrayTypeNode && this.typeForArray.equals(((ArrayTypeNode)other).typeForArray);
    }
    
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + ((this.typeForArray != null) ? this.typeForArray.hashCode() : 0);
        return hash;
    }
    
    public String getWrapperName() {
        return this.typeForArray.getWrapperName() + "[]";
    }
    
    public String getName() {
        return this.typeForArray.getName() + "[]";
    }
    
    public String getFullyTypedName() {
        return this.typeForArray.getFullyTypedName() + "[]";
    }
    
    public boolean isArray() {
        return true;
    }
}

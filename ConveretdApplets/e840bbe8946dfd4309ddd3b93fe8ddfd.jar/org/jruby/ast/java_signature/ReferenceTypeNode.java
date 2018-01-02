// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ast.java_signature;

public class ReferenceTypeNode extends TypeNode
{
    private String genericString;
    
    public ReferenceTypeNode(final String name) {
        super(name);
        this.genericString = "";
    }
    
    public void setGenericString(final String genericString) {
        this.genericString = genericString;
    }
    
    public boolean equals(final Object other) {
        return other != null && other instanceof ReferenceTypeNode && this.genericString.equals(((ReferenceTypeNode)other).genericString) && super.equals(other);
    }
    
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + ((this.genericString != null) ? this.genericString.hashCode() : 0);
        return hash;
    }
    
    public String getFullyTypedName() {
        return this.getName() + this.genericString;
    }
    
    public void setGenericsTyping(final String genericString) {
        this.genericString = genericString;
    }
}

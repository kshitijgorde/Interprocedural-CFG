// 
// Decompiled by Procyon v0.5.30
// 

package org.jfree.xml.generator.model;

public class KeyDescription
{
    private TypeInfo[] parameters;
    private Comments comments;
    
    public KeyDescription(final TypeInfo[] parameters) {
        this.parameters = parameters;
    }
    
    public TypeInfo[] getParameters() {
        return this.parameters;
    }
    
    public Comments getComments() {
        return this.comments;
    }
    
    public void setComments(final Comments comments) {
        this.comments = comments;
    }
}

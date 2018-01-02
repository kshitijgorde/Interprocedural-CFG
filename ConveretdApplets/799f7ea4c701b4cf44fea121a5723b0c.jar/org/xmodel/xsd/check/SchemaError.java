// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xsd.check;

import org.xmodel.xpath.AttributeNode;
import org.xmodel.IModelObject;

public class SchemaError
{
    private Type B;
    private IModelObject A;
    private IModelObject C;
    
    public SchemaError(final Type b, final IModelObject a, final IModelObject c) {
        this.B = b;
        this.A = a;
        this.C = c;
    }
    
    public void annotate() {
        throw new UnsupportedOperationException();
    }
    
    public boolean isType(final Type type) {
        return this.getType().equals(type);
    }
    
    public Type getType() {
        return this.B;
    }
    
    public IModelObject getSchemaLocus() {
        return this.A;
    }
    
    public IModelObject getDocumentLocus() {
        return this.C;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.B);
        sb.append(": ");
        if (this.C instanceof AttributeNode) {
            if (this.C.getType().length() == 0) {
                sb.append(this.C.getParent().getType());
                sb.append('=');
                sb.append(this.C.getValue());
            }
            else {
                sb.append('@');
                sb.append(this.C.getType());
                sb.append('=');
                sb.append(this.C.getValue());
            }
        }
        else {
            sb.append(this.C);
        }
        return sb.toString();
    }
    
    public enum Type
    {
        missingElement("missingElement", 0), 
        illegalElement("illegalElement", 1), 
        missingAttribute("missingAttribute", 2), 
        illegalAttribute("illegalAttribute", 3), 
        invalidValue("invalidValue", 4);
        
        static {
            A = new Type[] { Type.missingElement, Type.illegalElement, Type.missingAttribute, Type.illegalAttribute, Type.invalidValue };
        }
        
        private Type(final String s, final int n) {
        }
    }
}

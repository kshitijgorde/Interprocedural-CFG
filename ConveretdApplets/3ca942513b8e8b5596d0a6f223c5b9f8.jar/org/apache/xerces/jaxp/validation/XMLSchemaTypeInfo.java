// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.jaxp.validation;

import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
import org.w3c.dom.TypeInfo;

final class XMLSchemaTypeInfo implements TypeInfo
{
    private XSTypeDefinition fType;
    
    public XMLSchemaTypeInfo(final XSTypeDefinition fType) {
        this.fType = fType;
    }
    
    public String getTypeName() {
        if (this.fType == null) {
            return null;
        }
        if (this.fType instanceof XSSimpleTypeDefinition) {
            return ((XSSimpleTypeDecl)this.fType).getTypeName();
        }
        return ((XSComplexTypeDecl)this.fType).getTypeName();
    }
    
    public String getTypeNamespace() {
        if (this.fType != null) {
            return this.fType.getNamespace();
        }
        return null;
    }
    
    public boolean isDerivedFrom(final String s, final String s2, final int n) {
        if (this.fType == null) {
            return false;
        }
        if (this.fType instanceof XSSimpleTypeDefinition) {
            return ((XSSimpleTypeDecl)this.fType).isDOMDerivedFrom(s, s2, n);
        }
        return ((XSComplexTypeDecl)this.fType).isDOMDerivedFrom(s, s2, n);
    }
}

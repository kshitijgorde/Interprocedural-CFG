// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.psvi.XSComplexTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.psvi.XSAttributeDeclaration;

public class XSAttributeDecl implements XSAttributeDeclaration
{
    public static final short SCOPE_ABSENT = 0;
    public static final short SCOPE_GLOBAL = 1;
    public static final short SCOPE_LOCAL = 2;
    String fName;
    String fTargetNamespace;
    XSSimpleType fType;
    short fConstraintType;
    short fScope;
    XSComplexTypeDecl fEnclosingCT;
    ValidatedInfo fDefault;
    
    public XSAttributeDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fConstraintType = 0;
        this.fScope = 0;
        this.fEnclosingCT = null;
        this.fDefault = null;
    }
    
    public void setValues(final String name, final String targetNamespace, final XSSimpleType simpleType, final short constraintType, final short scope, final ValidatedInfo valInfo, final XSComplexTypeDecl enclosingCT) {
        this.fName = name;
        this.fTargetNamespace = targetNamespace;
        this.fType = simpleType;
        this.fConstraintType = constraintType;
        this.fScope = scope;
        this.fDefault = valInfo;
        this.fEnclosingCT = enclosingCT;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fConstraintType = 0;
        this.fScope = 0;
        this.fDefault = null;
    }
    
    public short getType() {
        return 1;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public XSSimpleTypeDefinition getTypeDefinition() {
        return this.fType;
    }
    
    public short getScope() {
        return this.fScope;
    }
    
    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }
    
    public short getConstraintType() {
        return this.fConstraintType;
    }
    
    public String getConstraintValue() {
        return (this.getConstraintType() == 0) ? null : this.fDefault.actualValue.toString();
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
    
    public ValidatedInfo getValInfo() {
        return this.fDefault;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xs.XSAttributeDeclaration;

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
    XSAnnotationImpl fAnnotation;
    ValidatedInfo fDefault;
    
    public XSAttributeDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fConstraintType = 0;
        this.fScope = 0;
        this.fEnclosingCT = null;
        this.fAnnotation = null;
        this.fDefault = null;
    }
    
    public void setValues(final String fName, final String fTargetNamespace, final XSSimpleType fType, final short fConstraintType, final short fScope, final ValidatedInfo fDefault, final XSComplexTypeDecl fEnclosingCT, final XSAnnotationImpl fAnnotation) {
        this.fName = fName;
        this.fTargetNamespace = fTargetNamespace;
        this.fType = fType;
        this.fConstraintType = fConstraintType;
        this.fScope = fScope;
        this.fDefault = fDefault;
        this.fEnclosingCT = fEnclosingCT;
        this.fAnnotation = fAnnotation;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fConstraintType = 0;
        this.fScope = 0;
        this.fDefault = null;
        this.fAnnotation = null;
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
        return (this.getConstraintType() == 0) ? null : this.fDefault.stringValue();
    }
    
    public XSAnnotation getAnnotation() {
        return this.fAnnotation;
    }
    
    public ValidatedInfo getValInfo() {
        return this.fDefault;
    }
    
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
    
    public Object getActualVC() {
        return (this.getConstraintType() == 0) ? null : this.fDefault.actualValue;
    }
    
    public short getActualVCType() {
        return (short)((this.getConstraintType() == 0) ? 45 : this.fDefault.actualValueType);
    }
    
    public ShortList getItemValueTypes() {
        return (this.getConstraintType() == 0) ? null : this.fDefault.itemValueTypes;
    }
}

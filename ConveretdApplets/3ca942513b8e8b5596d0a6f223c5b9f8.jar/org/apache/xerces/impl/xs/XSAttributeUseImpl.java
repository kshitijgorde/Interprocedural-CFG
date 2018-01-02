// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAttributeDeclaration;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xs.XSAttributeUse;

public class XSAttributeUseImpl implements XSAttributeUse
{
    public XSAttributeDecl fAttrDecl;
    public short fUse;
    public short fConstraintType;
    public ValidatedInfo fDefault;
    
    public XSAttributeUseImpl() {
        this.fAttrDecl = null;
        this.fUse = 0;
        this.fConstraintType = 0;
        this.fDefault = null;
    }
    
    public void reset() {
        this.fDefault = null;
        this.fAttrDecl = null;
        this.fUse = 0;
        this.fConstraintType = 0;
    }
    
    public short getType() {
        return 4;
    }
    
    public String getName() {
        return null;
    }
    
    public String getNamespace() {
        return null;
    }
    
    public boolean getRequired() {
        return this.fUse == 1;
    }
    
    public XSAttributeDeclaration getAttrDeclaration() {
        return this.fAttrDecl;
    }
    
    public short getConstraintType() {
        return this.fConstraintType;
    }
    
    public String getConstraintValue() {
        return (this.getConstraintType() == 0) ? null : this.fDefault.actualValue.toString();
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

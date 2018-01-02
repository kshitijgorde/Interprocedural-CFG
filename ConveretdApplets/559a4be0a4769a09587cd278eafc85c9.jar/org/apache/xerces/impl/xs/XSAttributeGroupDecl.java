// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.psvi.XSWildcard;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.psvi.XSAttributeGroupDefinition;

public class XSAttributeGroupDecl implements XSAttributeGroupDefinition
{
    public String fName;
    public String fTargetNamespace;
    int fAttrUseNum;
    private static final int INITIAL_SIZE = 5;
    XSAttributeUseImpl[] fAttributeUses;
    public XSWildcardDecl fAttributeWC;
    public String fIDAttrName;
    
    public XSAttributeGroupDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fAttrUseNum = 0;
        this.fAttributeUses = new XSAttributeUseImpl[5];
        this.fAttributeWC = null;
        this.fIDAttrName = null;
    }
    
    public String addAttributeUse(final XSAttributeUseImpl attrUse) {
        if (this.fAttrUseNum == this.fAttributeUses.length) {
            this.fAttributeUses = resize(this.fAttributeUses, this.fAttrUseNum * 2);
        }
        this.fAttributeUses[this.fAttrUseNum++] = attrUse;
        if (attrUse.fUse == 2) {
            return null;
        }
        if (attrUse.fAttrDecl.fType.isIDType()) {
            if (this.fIDAttrName != null) {
                return this.fIDAttrName;
            }
            this.fIDAttrName = attrUse.fAttrDecl.fName;
        }
        return null;
    }
    
    public XSAttributeUseImpl getAttributeUse(final String uri, final String localpart) {
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            if (this.fAttributeUses[i].fAttrDecl.fTargetNamespace == uri && this.fAttributeUses[i].fAttrDecl.fName == localpart) {
                return this.fAttributeUses[i];
            }
        }
        return null;
    }
    
    public void removeProhibitedAttrs() {
        if (this.fAttrUseNum == 0) {
            return;
        }
        int pCount = 0;
        final XSAttributeUseImpl[] pUses = new XSAttributeUseImpl[this.fAttrUseNum];
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            if (this.fAttributeUses[i].fUse == 2) {
                ++pCount;
                pUses[this.fAttrUseNum - pCount] = this.fAttributeUses[i];
            }
        }
        int newCount = 0;
        if (pCount > 0) {
        Label_0186:
            for (int j = 0; j < this.fAttrUseNum; ++j) {
                if (this.fAttributeUses[j].fUse != 2) {
                    for (int k = 1; k <= pCount; ++k) {
                        if (this.fAttributeUses[j].fAttrDecl.fName == pUses[this.fAttrUseNum - pCount].fAttrDecl.fName && this.fAttributeUses[j].fAttrDecl.fTargetNamespace == pUses[this.fAttrUseNum - pCount].fAttrDecl.fTargetNamespace) {
                            continue Label_0186;
                        }
                    }
                    pUses[newCount++] = this.fAttributeUses[j];
                }
            }
            this.fAttributeUses = pUses;
            this.fAttrUseNum = newCount;
        }
    }
    
    public String validRestrictionOf(final XSAttributeGroupDecl baseGroup) {
        String errorCode = null;
        XSAttributeUseImpl attrUse = null;
        XSAttributeDecl attrDecl = null;
        XSAttributeUseImpl baseAttrUse = null;
        XSAttributeDecl baseAttrDecl = null;
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            attrUse = this.fAttributeUses[i];
            attrDecl = attrUse.fAttrDecl;
            baseAttrUse = baseGroup.getAttributeUse(attrDecl.fTargetNamespace, attrDecl.fName);
            if (baseAttrUse != null) {
                if (baseAttrUse.fUse == 1 && attrUse.fUse != 1) {
                    errorCode = "derivation-ok-restriction.2.1.1";
                    return errorCode;
                }
                if (attrUse.fUse != 2) {
                    baseAttrDecl = baseAttrUse.fAttrDecl;
                    if (!XSConstraints.checkSimpleDerivationOk(attrDecl.fType, baseAttrDecl.fType, baseAttrDecl.fType.getFinal())) {
                        errorCode = "derivation-ok-restriction.2.1.2";
                        return errorCode;
                    }
                    final int baseConsType = (baseAttrUse.fConstraintType != 0) ? baseAttrUse.fConstraintType : baseAttrDecl.getConstraintType();
                    final int thisConstType = (attrUse.fConstraintType != 0) ? attrUse.fConstraintType : attrDecl.getConstraintType();
                    if (baseConsType == 2) {
                        if (thisConstType != 2) {
                            errorCode = "derivation-ok-restriction.2.1.3";
                            return errorCode;
                        }
                        final ValidatedInfo baseFixedValue = (baseAttrUse.fDefault != null) ? baseAttrUse.fDefault : baseAttrDecl.fDefault;
                        final ValidatedInfo thisFixedValue = (attrUse.fDefault != null) ? attrUse.fDefault : attrDecl.fDefault;
                        if (!baseFixedValue.actualValue.equals(thisFixedValue.actualValue)) {
                            errorCode = "derivation-ok-restriction.2.1.3";
                            return errorCode;
                        }
                    }
                }
            }
            else if (baseGroup.fAttributeWC == null || !baseGroup.fAttributeWC.allowNamespace(attrDecl.fTargetNamespace)) {
                errorCode = "derivation-ok-restriction.2.2";
                return errorCode;
            }
        }
        for (int j = 0; j < baseGroup.fAttrUseNum; ++j) {
            baseAttrUse = baseGroup.fAttributeUses[j];
            if (baseAttrUse.fUse == 1) {
                baseAttrDecl = baseAttrUse.fAttrDecl;
                final XSAttributeUseImpl thisAttrUse = this.getAttributeUse(baseAttrDecl.fTargetNamespace, baseAttrDecl.fName);
                if (thisAttrUse == null) {
                    errorCode = "derivation-ok-restriction.3";
                    return errorCode;
                }
            }
        }
        if (this.fAttributeWC != null) {
            if (baseGroup.fAttributeWC == null) {
                errorCode = "derivation-ok-restriction.4";
                return errorCode;
            }
            if (!this.fAttributeWC.isSubsetOf(baseGroup.fAttributeWC)) {
                errorCode = "derivation-ok-restriction.4";
                return errorCode;
            }
        }
        return null;
    }
    
    static final XSAttributeUseImpl[] resize(final XSAttributeUseImpl[] oldArray, final int newSize) {
        final XSAttributeUseImpl[] newArray = new XSAttributeUseImpl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            this.fAttributeUses[i] = null;
        }
        this.fAttrUseNum = 0;
        this.fAttributeWC = null;
        this.fIDAttrName = null;
    }
    
    public short getType() {
        return 5;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public XSObjectList getAttributeUses() {
        return new XSObjectListImpl(this.fAttributeUses, this.fAttrUseNum);
    }
    
    public XSWildcard getAttributeWildcard() {
        return this.fAttributeWC;
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
}

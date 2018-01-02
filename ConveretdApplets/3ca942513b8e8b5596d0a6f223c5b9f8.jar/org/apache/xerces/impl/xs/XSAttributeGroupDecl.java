// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSWildcard;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.xs.XSAttributeGroupDefinition;

public class XSAttributeGroupDecl implements XSAttributeGroupDefinition
{
    public String fName;
    public String fTargetNamespace;
    int fAttrUseNum;
    private static final int INITIAL_SIZE = 5;
    XSAttributeUseImpl[] fAttributeUses;
    public XSWildcardDecl fAttributeWC;
    public String fIDAttrName;
    public XSAnnotationImpl fAnnotation;
    protected XSObjectListImpl fAttrUses;
    
    public XSAttributeGroupDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fAttrUseNum = 0;
        this.fAttributeUses = new XSAttributeUseImpl[5];
        this.fAttributeWC = null;
        this.fIDAttrName = null;
        this.fAttrUses = null;
    }
    
    public String addAttributeUse(final XSAttributeUseImpl xsAttributeUseImpl) {
        if (this.fAttrUseNum == this.fAttributeUses.length) {
            this.fAttributeUses = resize(this.fAttributeUses, this.fAttrUseNum * 2);
        }
        this.fAttributeUses[this.fAttrUseNum++] = xsAttributeUseImpl;
        if (xsAttributeUseImpl.fUse == 2) {
            return null;
        }
        if (xsAttributeUseImpl.fAttrDecl.fType.isIDType()) {
            if (this.fIDAttrName != null) {
                return this.fIDAttrName;
            }
            this.fIDAttrName = xsAttributeUseImpl.fAttrDecl.fName;
        }
        return null;
    }
    
    public XSAttributeUse getAttributeUse(final String s, final String s2) {
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            if (this.fAttributeUses[i].fAttrDecl.fTargetNamespace == s && this.fAttributeUses[i].fAttrDecl.fName == s2) {
                return this.fAttributeUses[i];
            }
        }
        return null;
    }
    
    public void removeProhibitedAttrs() {
        if (this.fAttrUseNum == 0) {
            return;
        }
        int n = 0;
        final XSAttributeUseImpl[] fAttributeUses = new XSAttributeUseImpl[this.fAttrUseNum];
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            if (this.fAttributeUses[i].fUse == 2) {
                ++n;
                fAttributeUses[this.fAttrUseNum - n] = this.fAttributeUses[i];
            }
        }
        int fAttrUseNum = 0;
        if (n > 0) {
        Label_0186:
            for (int j = 0; j < this.fAttrUseNum; ++j) {
                if (this.fAttributeUses[j].fUse != 2) {
                    for (int k = 1; k <= n; ++k) {
                        if (this.fAttributeUses[j].fAttrDecl.fName == fAttributeUses[this.fAttrUseNum - n].fAttrDecl.fName && this.fAttributeUses[j].fAttrDecl.fTargetNamespace == fAttributeUses[this.fAttrUseNum - n].fAttrDecl.fTargetNamespace) {
                            continue Label_0186;
                        }
                    }
                    fAttributeUses[fAttrUseNum++] = this.fAttributeUses[j];
                }
            }
            this.fAttributeUses = fAttributeUses;
            this.fAttrUseNum = fAttrUseNum;
        }
    }
    
    public Object[] validRestrictionOf(final String s, final XSAttributeGroupDecl xsAttributeGroupDecl) {
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            final XSAttributeUseImpl xsAttributeUseImpl = this.fAttributeUses[i];
            final XSAttributeDecl fAttrDecl = xsAttributeUseImpl.fAttrDecl;
            final XSAttributeUseImpl xsAttributeUseImpl2 = (XSAttributeUseImpl)xsAttributeGroupDecl.getAttributeUse(fAttrDecl.fTargetNamespace, fAttrDecl.fName);
            if (xsAttributeUseImpl2 != null) {
                if (xsAttributeUseImpl2.getRequired() && !xsAttributeUseImpl.getRequired()) {
                    return new Object[] { s, fAttrDecl.fName, (xsAttributeUseImpl.fUse == 0) ? "optional" : "prohibited", "derivation-ok-restriction.2.1.1" };
                }
                if (xsAttributeUseImpl.fUse != 2) {
                    final XSAttributeDecl fAttrDecl2 = xsAttributeUseImpl2.fAttrDecl;
                    if (!XSConstraints.checkSimpleDerivationOk(fAttrDecl.fType, fAttrDecl2.fType, fAttrDecl2.fType.getFinal())) {
                        return new Object[] { s, fAttrDecl.fName, fAttrDecl.fType.getName(), fAttrDecl2.fType.getName(), "derivation-ok-restriction.2.1.2" };
                    }
                    final short n = (xsAttributeUseImpl2.fConstraintType != 0) ? xsAttributeUseImpl2.fConstraintType : fAttrDecl2.getConstraintType();
                    final short n2 = (xsAttributeUseImpl.fConstraintType != 0) ? xsAttributeUseImpl.fConstraintType : fAttrDecl.getConstraintType();
                    if (n == 2) {
                        if (n2 != 2) {
                            return new Object[] { s, fAttrDecl.fName, "derivation-ok-restriction.2.1.3.a" };
                        }
                        final ValidatedInfo validatedInfo = (xsAttributeUseImpl2.fDefault != null) ? xsAttributeUseImpl2.fDefault : fAttrDecl2.fDefault;
                        final ValidatedInfo validatedInfo2 = (xsAttributeUseImpl.fDefault != null) ? xsAttributeUseImpl.fDefault : fAttrDecl.fDefault;
                        if (!validatedInfo.actualValue.equals(validatedInfo2.actualValue)) {
                            return new Object[] { s, fAttrDecl.fName, validatedInfo2.stringValue(), validatedInfo.stringValue(), "derivation-ok-restriction.2.1.3.b" };
                        }
                    }
                }
            }
            else {
                if (xsAttributeGroupDecl.fAttributeWC == null) {
                    return new Object[] { s, fAttrDecl.fName, "derivation-ok-restriction.2.2.a" };
                }
                if (!xsAttributeGroupDecl.fAttributeWC.allowNamespace(fAttrDecl.fTargetNamespace)) {
                    return new Object[] { s, fAttrDecl.fName, (fAttrDecl.fTargetNamespace == null) ? "" : fAttrDecl.fTargetNamespace, "derivation-ok-restriction.2.2.b" };
                }
            }
        }
        for (int j = 0; j < xsAttributeGroupDecl.fAttrUseNum; ++j) {
            final XSAttributeUseImpl xsAttributeUseImpl3 = xsAttributeGroupDecl.fAttributeUses[j];
            if (xsAttributeUseImpl3.fUse == 1) {
                final XSAttributeDecl fAttrDecl3 = xsAttributeUseImpl3.fAttrDecl;
                if (this.getAttributeUse(fAttrDecl3.fTargetNamespace, fAttrDecl3.fName) == null) {
                    return new Object[] { s, xsAttributeUseImpl3.fAttrDecl.fName, "derivation-ok-restriction.3" };
                }
            }
        }
        if (this.fAttributeWC != null) {
            if (xsAttributeGroupDecl.fAttributeWC == null) {
                return new Object[] { s, "derivation-ok-restriction.4.1" };
            }
            if (!this.fAttributeWC.isSubsetOf(xsAttributeGroupDecl.fAttributeWC)) {
                return new Object[] { s, "derivation-ok-restriction.4.2" };
            }
            if (this.fAttributeWC.weakerProcessContents(xsAttributeGroupDecl.fAttributeWC)) {
                return new Object[] { s, this.fAttributeWC.getProcessContentsAsString(), xsAttributeGroupDecl.fAttributeWC.getProcessContentsAsString(), "derivation-ok-restriction.4.3" };
            }
        }
        return null;
    }
    
    static final XSAttributeUseImpl[] resize(final XSAttributeUseImpl[] array, final int n) {
        final XSAttributeUseImpl[] array2 = new XSAttributeUseImpl[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        for (int i = 0; i < this.fAttrUseNum; ++i) {
            this.fAttributeUses[i] = null;
        }
        this.fAttrUseNum = 0;
        this.fAttributeWC = null;
        this.fAnnotation = null;
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
        if (this.fAttrUses == null) {
            this.fAttrUses = new XSObjectListImpl(this.fAttributeUses, this.fAttrUseNum);
        }
        return this.fAttrUses;
    }
    
    public XSWildcard getAttributeWildcard() {
        return this.fAttributeWC;
    }
    
    public XSAnnotation getAnnotation() {
        return this.fAnnotation;
    }
    
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSAnnotation;
import org.apache.xerces.impl.xs.psvi.XSObject;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.impl.xs.psvi.XSNamedMap;
import org.apache.xerces.impl.xs.psvi.XSComplexTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.psvi.XSElementDeclaration;

public class XSElementDecl implements XSElementDeclaration
{
    public static final short SCOPE_ABSENT = 0;
    public static final short SCOPE_GLOBAL = 1;
    public static final short SCOPE_LOCAL = 2;
    public String fName;
    public String fTargetNamespace;
    public XSTypeDecl fType;
    short fMiscFlags;
    public short fScope;
    XSComplexTypeDecl fEnclosingCT;
    public short fBlock;
    public short fFinal;
    public ValidatedInfo fDefault;
    public XSElementDecl fSubGroup;
    static final int INITIAL_SIZE = 2;
    int fIDCPos;
    IdentityConstraint[] fIDConstraints;
    private static final short CONSTRAINT_MASK = 3;
    private static final short NILLABLE = 4;
    private static final short ABSTRACT = 8;
    private String fDescription;
    
    public XSElementDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fMiscFlags = 0;
        this.fScope = 0;
        this.fEnclosingCT = null;
        this.fBlock = 0;
        this.fFinal = 0;
        this.fDefault = null;
        this.fSubGroup = null;
        this.fIDCPos = 0;
        this.fIDConstraints = new IdentityConstraint[2];
        this.fDescription = null;
    }
    
    public void setConstraintType(final short constraintType) {
        this.fMiscFlags ^= (short)(this.fMiscFlags & 0x3);
        this.fMiscFlags |= (short)(constraintType & 0x3);
    }
    
    public void setIsNillable() {
        this.fMiscFlags |= 0x4;
    }
    
    public void setIsAbstract() {
        this.fMiscFlags |= 0x8;
    }
    
    public void setIsGlobal() {
        this.fScope = 1;
    }
    
    public void setIsLocal(final XSComplexTypeDecl enclosingCT) {
        this.fScope = 2;
        this.fEnclosingCT = enclosingCT;
    }
    
    public void addIDConstaint(final IdentityConstraint idc) {
        if (this.fIDCPos == this.fIDConstraints.length) {
            this.fIDConstraints = resize(this.fIDConstraints, this.fIDCPos * 2);
        }
        this.fIDConstraints[this.fIDCPos++] = idc;
    }
    
    public IdentityConstraint[] getIDConstraints() {
        if (this.fIDCPos == 0) {
            return null;
        }
        if (this.fIDCPos < this.fIDConstraints.length) {
            this.fIDConstraints = resize(this.fIDConstraints, this.fIDCPos);
        }
        return this.fIDConstraints;
    }
    
    static final IdentityConstraint[] resize(final IdentityConstraint[] oldArray, final int newSize) {
        final IdentityConstraint[] newArray = new IdentityConstraint[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }
    
    public String toString() {
        if (this.fDescription == null) {
            final StringBuffer buffer = new StringBuffer();
            buffer.append("\"");
            if (this.fTargetNamespace != null) {
                buffer.append(this.fTargetNamespace);
            }
            buffer.append("\"");
            buffer.append(":");
            buffer.append(this.fName);
            this.fDescription = buffer.toString();
        }
        return this.fDescription;
    }
    
    public int hashCode() {
        int code = this.fName.hashCode();
        if (this.fTargetNamespace != null) {
            code = (code << 16) + this.fTargetNamespace.hashCode();
        }
        return code;
    }
    
    public boolean equals(final Object o) {
        return o == this;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fType = null;
        this.fMiscFlags = 0;
        this.fBlock = 0;
        this.fFinal = 0;
        this.fDefault = null;
        this.fSubGroup = null;
        for (int i = 0; i < this.fIDCPos; ++i) {
            this.fIDConstraints[i] = null;
        }
        this.fIDCPos = 0;
    }
    
    public short getType() {
        return 2;
    }
    
    public String getName() {
        return this.fName;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public XSTypeDefinition getTypeDefinition() {
        return this.fType;
    }
    
    public short getScope() {
        return this.fScope;
    }
    
    public XSComplexTypeDefinition getEnclosingCTDefinition() {
        return this.fEnclosingCT;
    }
    
    public short getConstraintType() {
        return (short)(this.fMiscFlags & 0x3);
    }
    
    public String getConstraintValue() {
        return (this.getConstraintType() == 0) ? null : this.fDefault.stringValue();
    }
    
    public boolean getIsNillable() {
        return (this.fMiscFlags & 0x4) != 0x0;
    }
    
    public XSNamedMap getIdentityConstraints() {
        return new XSNamedMapImpl(this.fIDConstraints, this.fIDCPos);
    }
    
    public XSElementDeclaration getSubstitutionGroupAffiliation() {
        return this.fSubGroup;
    }
    
    public boolean getIsSubstitutionGroupExclusion(final short exclusion) {
        return (this.fFinal & exclusion) != 0x0;
    }
    
    public short getSubstitutionGroupExclusions() {
        return this.fFinal;
    }
    
    public boolean getIsDisallowedSubstition(final short disallowed) {
        return (this.fBlock & disallowed) != 0x0;
    }
    
    public short getDisallowedSubstitutions() {
        return this.fBlock;
    }
    
    public boolean getIsAbstract() {
        return (this.fMiscFlags & 0x8) != 0x0;
    }
    
    public XSAnnotation getAnnotation() {
        return null;
    }
}

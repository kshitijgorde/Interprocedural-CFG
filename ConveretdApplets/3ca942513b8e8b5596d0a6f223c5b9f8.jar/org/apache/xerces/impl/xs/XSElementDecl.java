// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.ShortList;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSAnnotation;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import org.apache.xerces.xs.XSNamedMap;
import org.apache.xerces.xs.XSComplexTypeDefinition;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSElementDeclaration;

public class XSElementDecl implements XSElementDeclaration
{
    public static final short SCOPE_ABSENT = 0;
    public static final short SCOPE_GLOBAL = 1;
    public static final short SCOPE_LOCAL = 2;
    public String fName;
    public String fTargetNamespace;
    public XSTypeDefinition fType;
    short fMiscFlags;
    public short fScope;
    XSComplexTypeDecl fEnclosingCT;
    public short fBlock;
    public short fFinal;
    public XSAnnotationImpl fAnnotation;
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
        this.fAnnotation = null;
        this.fDefault = null;
        this.fSubGroup = null;
        this.fIDCPos = 0;
        this.fIDConstraints = new IdentityConstraint[2];
        this.fDescription = null;
    }
    
    public void setConstraintType(final short n) {
        this.fMiscFlags ^= (short)(this.fMiscFlags & 0x3);
        this.fMiscFlags |= (short)(n & 0x3);
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
    
    public void setIsLocal(final XSComplexTypeDecl fEnclosingCT) {
        this.fScope = 2;
        this.fEnclosingCT = fEnclosingCT;
    }
    
    public void addIDConstraint(final IdentityConstraint identityConstraint) {
        if (this.fIDCPos == this.fIDConstraints.length) {
            this.fIDConstraints = resize(this.fIDConstraints, this.fIDCPos * 2);
        }
        this.fIDConstraints[this.fIDCPos++] = identityConstraint;
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
    
    static final IdentityConstraint[] resize(final IdentityConstraint[] array, final int n) {
        final IdentityConstraint[] array2 = new IdentityConstraint[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    public String toString() {
        if (this.fDescription == null) {
            if (this.fTargetNamespace != null) {
                final StringBuffer sb = new StringBuffer(this.fTargetNamespace.length() + ((this.fName != null) ? this.fName.length() : 4) + 3);
                sb.append('\"');
                sb.append(this.fTargetNamespace);
                sb.append('\"');
                sb.append(':');
                sb.append(this.fName);
                this.fDescription = sb.toString();
            }
            else {
                this.fDescription = this.fName;
            }
        }
        return this.fDescription;
    }
    
    public int hashCode() {
        int hashCode = this.fName.hashCode();
        if (this.fTargetNamespace != null) {
            hashCode = (hashCode << 16) + this.fTargetNamespace.hashCode();
        }
        return hashCode;
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
        this.fAnnotation = null;
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
    
    public boolean getNillable() {
        return (this.fMiscFlags & 0x4) != 0x0;
    }
    
    public XSNamedMap getIdentityConstraints() {
        return new XSNamedMapImpl(this.fIDConstraints, this.fIDCPos);
    }
    
    public XSElementDeclaration getSubstitutionGroupAffiliation() {
        return this.fSubGroup;
    }
    
    public boolean isSubstitutionGroupExclusion(final short n) {
        return (this.fFinal & n) != 0x0;
    }
    
    public short getSubstitutionGroupExclusions() {
        return this.fFinal;
    }
    
    public boolean isDisallowedSubstitution(final short n) {
        return (this.fBlock & n) != 0x0;
    }
    
    public short getDisallowedSubstitutions() {
        return this.fBlock;
    }
    
    public boolean getAbstract() {
        return (this.fMiscFlags & 0x8) != 0x0;
    }
    
    public XSAnnotation getAnnotation() {
        return this.fAnnotation;
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

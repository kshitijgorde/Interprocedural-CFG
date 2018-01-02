// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.xs.XSNamespaceItem;
import org.apache.xerces.xs.XSParticle;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSWildcard;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.xs.XSComplexTypeDefinition;

public class XSComplexTypeDecl implements XSComplexTypeDefinition
{
    String fName;
    String fTargetNamespace;
    XSTypeDefinition fBaseType;
    short fDerivedBy;
    short fFinal;
    short fBlock;
    short fMiscFlags;
    XSAttributeGroupDecl fAttrGrp;
    short fContentType;
    XSSimpleType fXSSimpleType;
    XSParticleDecl fParticle;
    XSCMValidator fCMValidator;
    XSObjectListImpl fAnnotations;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_UNION = 4;
    static final int DERIVATION_LIST = 8;
    private static final short CT_IS_ABSTRACT = 1;
    private static final short CT_HAS_TYPE_ID = 2;
    private static final short CT_IS_ANONYMOUS = 4;
    
    public XSComplexTypeDecl() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fBaseType = null;
        this.fDerivedBy = 2;
        this.fFinal = 0;
        this.fBlock = 0;
        this.fMiscFlags = 0;
        this.fAttrGrp = null;
        this.fContentType = 0;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fCMValidator = null;
        this.fAnnotations = null;
    }
    
    public void setValues(final String s, final String fTargetNamespace, final XSTypeDefinition fBaseType, final short fDerivedBy, final short fFinal, final short fBlock, final short fContentType, final boolean b, final XSAttributeGroupDecl fAttrGrp, final XSSimpleType fxsSimpleType, final XSParticleDecl fParticle, final XSObjectListImpl fAnnotations) {
        this.fTargetNamespace = fTargetNamespace;
        this.fBaseType = fBaseType;
        this.fDerivedBy = fDerivedBy;
        this.fFinal = fFinal;
        this.fBlock = fBlock;
        this.fContentType = fContentType;
        if (b) {
            this.fMiscFlags |= 0x1;
        }
        this.fAttrGrp = fAttrGrp;
        this.fXSSimpleType = fxsSimpleType;
        this.fParticle = fParticle;
        this.fAnnotations = fAnnotations;
    }
    
    public void setName(final String fName) {
        this.fName = fName;
    }
    
    public short getTypeCategory() {
        return 15;
    }
    
    public String getTypeName() {
        return this.fName;
    }
    
    public short getFinalSet() {
        return this.fFinal;
    }
    
    public String getTargetNamespace() {
        return this.fTargetNamespace;
    }
    
    public boolean containsTypeID() {
        return (this.fMiscFlags & 0x2) != 0x0;
    }
    
    public void setIsAbstractType() {
        this.fMiscFlags |= 0x1;
    }
    
    public void setContainsTypeID() {
        this.fMiscFlags |= 0x2;
    }
    
    public void setIsAnonymous() {
        this.fMiscFlags |= 0x4;
    }
    
    public synchronized XSCMValidator getContentModel(final CMBuilder cmBuilder) {
        if (this.fCMValidator == null) {
            this.fCMValidator = cmBuilder.getContentModel(this);
        }
        return this.fCMValidator;
    }
    
    public XSAttributeGroupDecl getAttrGrp() {
        return this.fAttrGrp;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        this.appendTypeInfo(sb);
        return sb.toString();
    }
    
    void appendTypeInfo(final StringBuffer sb) {
        final String[] array = { "EMPTY", "SIMPLE", "ELEMENT", "MIXED" };
        final String[] array2 = { "EMPTY", "EXTENSION", "RESTRICTION" };
        sb.append("Complex type name='" + this.fTargetNamespace + "," + this.getTypeName() + "', ");
        if (this.fBaseType != null) {
            sb.append(" base type name='" + this.fBaseType.getName() + "', ");
        }
        sb.append(" content type='" + array[this.fContentType] + "', ");
        sb.append(" isAbstract='" + this.getAbstract() + "', ");
        sb.append(" hasTypeId='" + this.containsTypeID() + "', ");
        sb.append(" final='" + this.fFinal + "', ");
        sb.append(" block='" + this.fBlock + "', ");
        if (this.fParticle != null) {
            sb.append(" particle='" + this.fParticle.toString() + "', ");
        }
        sb.append(" derivedBy='" + array2[this.fDerivedBy] + "'. ");
    }
    
    public boolean derivedFromType(final XSTypeDefinition xsTypeDefinition, final short n) {
        if (xsTypeDefinition == null) {
            return false;
        }
        if (xsTypeDefinition == SchemaGrammar.fAnyType) {
            return true;
        }
        XSTypeDefinition baseType;
        for (baseType = this; baseType != xsTypeDefinition && baseType != SchemaGrammar.fAnySimpleType && baseType != SchemaGrammar.fAnyType; baseType = baseType.getBaseType()) {}
        return baseType == xsTypeDefinition;
    }
    
    public boolean derivedFrom(final String s, final String s2, final short n) {
        if (s2 == null) {
            return false;
        }
        if (s != null && s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anyType")) {
            return true;
        }
        XSTypeDefinition baseType;
        for (baseType = this; (!s2.equals(baseType.getName()) || ((s != null || baseType.getNamespace() != null) && (s == null || !s.equals(baseType.getNamespace())))) && baseType != SchemaGrammar.fAnySimpleType && baseType != SchemaGrammar.fAnyType; baseType = baseType.getBaseType()) {}
        return baseType != SchemaGrammar.fAnySimpleType && baseType != SchemaGrammar.fAnyType;
    }
    
    public boolean isDOMDerivedFrom(final String s, String s2, final int n) {
        if (s2 == null) {
            return false;
        }
        if (s != null && s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anyType") && n == 1 && n == 2) {
            return true;
        }
        if ((n & 0x1) != 0x0 && this.isDerivedByRestriction(s, s2, n, this)) {
            return true;
        }
        if ((n & 0x2) != 0x0 && this.isDerivedByExtension(s, s2, n, this)) {
            return true;
        }
        if (((n & 0x8) != 0x0 || (n & 0x4) != 0x0) && (n & 0x1) == 0x0 && (n & 0x2) == 0x0) {
            if (s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anyType")) {
                s2 = "anySimpleType";
            }
            if (!this.fName.equals("anyType") || !this.fTargetNamespace.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA)) {
                if (this.fBaseType != null && this.fBaseType instanceof XSSimpleTypeDecl) {
                    return ((XSSimpleTypeDecl)this.fBaseType).isDOMDerivedFrom(s, s2, n);
                }
                if (this.fBaseType != null && this.fBaseType instanceof XSComplexTypeDecl) {
                    return ((XSComplexTypeDecl)this.fBaseType).isDOMDerivedFrom(s, s2, n);
                }
            }
        }
        return (n & 0x2) == 0x0 && (n & 0x1) == 0x0 && (n & 0x8) == 0x0 && (n & 0x4) == 0x0 && this.isDerivedByAny(s, s2, n, this);
    }
    
    private boolean isDerivedByAny(final String s, final String s2, final int n, XSTypeDefinition baseType) {
        XSTypeDefinition xsTypeDefinition = null;
        boolean b = false;
        while (baseType != null && baseType != xsTypeDefinition) {
            if (s2.equals(baseType.getName()) && ((s == null && baseType.getNamespace() == null) || (s != null && s.equals(baseType.getNamespace())))) {
                b = true;
                break;
            }
            if (this.isDerivedByRestriction(s, s2, n, baseType)) {
                return true;
            }
            if (!this.isDerivedByExtension(s, s2, n, baseType)) {
                return true;
            }
            xsTypeDefinition = baseType;
            baseType = baseType.getBaseType();
        }
        return b;
    }
    
    private boolean isDerivedByRestriction(final String s, String s2, final int n, XSTypeDefinition baseType) {
        for (XSTypeDefinition xsTypeDefinition = null; baseType != null && baseType != xsTypeDefinition; xsTypeDefinition = baseType, baseType = baseType.getBaseType()) {
            if (s != null && s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anySimpleType")) {
                return false;
            }
            if ((s2.equals(baseType.getName()) && s != null && s.equals(baseType.getNamespace())) || (baseType.getNamespace() == null && s == null)) {
                return true;
            }
            if (baseType instanceof XSSimpleTypeDecl) {
                if (s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anyType")) {
                    s2 = "anySimpleType";
                }
                return ((XSSimpleTypeDecl)baseType).isDOMDerivedFrom(s, s2, n);
            }
            if (((XSComplexTypeDecl)baseType).getDerivationMethod() != 2) {
                return false;
            }
        }
        return false;
    }
    
    private boolean isDerivedByExtension(final String s, String s2, final int n, XSTypeDefinition baseType) {
        boolean b = false;
        XSTypeDefinition xsTypeDefinition = null;
        while (baseType != null && baseType != xsTypeDefinition && (s == null || !s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !s2.equals("anySimpleType") || !SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(baseType.getNamespace()) || !"anyType".equals(baseType.getName()))) {
            if (s2.equals(baseType.getName()) && ((s == null && baseType.getNamespace() == null) || (s != null && s.equals(baseType.getNamespace())))) {
                return b;
            }
            if (baseType instanceof XSSimpleTypeDecl) {
                if (s.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && s2.equals("anyType")) {
                    s2 = "anySimpleType";
                }
                if ((n & 0x2) != 0x0) {
                    return b & ((XSSimpleTypeDecl)baseType).isDOMDerivedFrom(s, s2, n & 0x1);
                }
                return b & ((XSSimpleTypeDecl)baseType).isDOMDerivedFrom(s, s2, n);
            }
            else {
                if (((XSComplexTypeDecl)baseType).getDerivationMethod() == 1) {
                    b |= true;
                }
                xsTypeDefinition = baseType;
                baseType = baseType.getBaseType();
            }
        }
        return false;
    }
    
    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fBaseType = null;
        this.fDerivedBy = 2;
        this.fFinal = 0;
        this.fBlock = 0;
        this.fMiscFlags = 0;
        this.fAttrGrp.reset();
        this.fContentType = 0;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fCMValidator = null;
        if (this.fAnnotations != null) {
            this.fAnnotations.clear();
        }
        this.fAnnotations = null;
    }
    
    public short getType() {
        return 3;
    }
    
    public String getName() {
        return this.getAnonymous() ? null : this.fName;
    }
    
    public boolean getAnonymous() {
        return (this.fMiscFlags & 0x4) != 0x0;
    }
    
    public String getNamespace() {
        return this.fTargetNamespace;
    }
    
    public XSTypeDefinition getBaseType() {
        return this.fBaseType;
    }
    
    public short getDerivationMethod() {
        return this.fDerivedBy;
    }
    
    public boolean isFinal(final short n) {
        return (this.fFinal & n) != 0x0;
    }
    
    public short getFinal() {
        return this.fFinal;
    }
    
    public boolean getAbstract() {
        return (this.fMiscFlags & 0x1) != 0x0;
    }
    
    public XSObjectList getAttributeUses() {
        return this.fAttrGrp.getAttributeUses();
    }
    
    public XSWildcard getAttributeWildcard() {
        return this.fAttrGrp.getAttributeWildcard();
    }
    
    public short getContentType() {
        return this.fContentType;
    }
    
    public XSSimpleTypeDefinition getSimpleType() {
        return this.fXSSimpleType;
    }
    
    public XSParticle getParticle() {
        return this.fParticle;
    }
    
    public boolean isProhibitedSubstitution(final short n) {
        return (this.fBlock & n) != 0x0;
    }
    
    public short getProhibitedSubstitutions() {
        return this.fBlock;
    }
    
    public XSObjectList getAnnotations() {
        return this.fAnnotations;
    }
    
    public XSNamespaceItem getNamespaceItem() {
        return null;
    }
    
    public XSAttributeUse getAttributeUse(final String s, final String s2) {
        return this.fAttrGrp.getAttributeUse(s, s2);
    }
}

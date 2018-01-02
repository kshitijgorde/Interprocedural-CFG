// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.psvi.XSParticle;
import org.apache.xerces.impl.xs.psvi.XSSimpleTypeDefinition;
import org.apache.xerces.impl.xs.psvi.XSWildcard;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.xs.psvi.XSTypeDefinition;
import org.apache.xerces.impl.xs.models.CMBuilder;
import org.apache.xerces.impl.xs.models.XSCMValidator;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.psvi.XSComplexTypeDefinition;

public class XSComplexTypeDecl implements XSTypeDecl, XSComplexTypeDefinition
{
    String fName;
    String fTargetNamespace;
    XSTypeDecl fBaseType;
    short fDerivedBy;
    short fFinal;
    short fBlock;
    short fMiscFlags;
    XSAttributeGroupDecl fAttrGrp;
    short fContentType;
    XSSimpleType fXSSimpleType;
    XSParticleDecl fParticle;
    XSCMValidator fCMValidator;
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
    }
    
    public void setValues(final String name, final String targetNamespace, final XSTypeDecl baseType, final short derivedBy, final short schemaFinal, final short block, final short contentType, final boolean isAbstract, final XSAttributeGroupDecl attrGrp, final XSSimpleType simpleType, final XSParticleDecl particle) {
        this.fTargetNamespace = targetNamespace;
        this.fBaseType = baseType;
        this.fDerivedBy = derivedBy;
        this.fFinal = schemaFinal;
        this.fBlock = block;
        this.fContentType = contentType;
        if (isAbstract) {
            this.fMiscFlags |= 0x1;
        }
        this.fAttrGrp = attrGrp;
        this.fXSSimpleType = simpleType;
        this.fParticle = particle;
    }
    
    public void setName(final String name) {
        this.fName = name;
    }
    
    public short getTypeCategory() {
        return 13;
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
        final StringBuffer str = new StringBuffer();
        this.appendTypeInfo(str);
        return str.toString();
    }
    
    void appendTypeInfo(final StringBuffer str) {
        final String[] contentType = { "EMPTY", "SIMPLE", "MIXED", "ELEMENT" };
        final String[] derivedBy = { "EMPTY", "EXTENSION", "RESTRICTION" };
        str.append("Complex type name='" + this.fTargetNamespace + "," + this.getTypeName() + "', ");
        if (this.fBaseType != null) {
            str.append(" base type name='" + this.fBaseType.getName() + "', ");
        }
        str.append(" content type='" + contentType[this.fContentType] + "', ");
        str.append(" isAbstract='" + this.getIsAbstract() + "', ");
        str.append(" hasTypeId='" + this.containsTypeID() + "', ");
        str.append(" final='" + this.fFinal + "', ");
        str.append(" block='" + this.fBlock + "', ");
        if (this.fParticle != null) {
            str.append(" particle='" + this.fParticle.toString() + "', ");
        }
        str.append(" derivedBy='" + derivedBy[this.fDerivedBy] + "'. ");
    }
    
    public boolean derivedFrom(final XSTypeDefinition ancestor) {
        if (ancestor == null) {
            return false;
        }
        if (ancestor == SchemaGrammar.fAnyType) {
            return true;
        }
        XSTypeDefinition type;
        for (type = this; type != ancestor && type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType; type = type.getBaseType()) {}
        return type == ancestor;
    }
    
    public boolean derivedFrom(final String ancestorNS, final String ancestorName) {
        if (ancestorName == null) {
            return false;
        }
        if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals("anyType")) {
            return true;
        }
        XSTypeDecl type;
        for (type = this; (!ancestorName.equals(type.getName()) || ((ancestorNS != null || type.getNamespace() != null) && (ancestorNS == null || !ancestorNS.equals(type.getNamespace())))) && type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType; type = (XSTypeDecl)type.getBaseType()) {}
        return type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType;
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
    }
    
    public short getType() {
        return 3;
    }
    
    public String getName() {
        return this.getIsAnonymous() ? null : this.fName;
    }
    
    public boolean getIsAnonymous() {
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
    
    public boolean getIsFinal(final short derivation) {
        return (this.fFinal & derivation) != 0x0;
    }
    
    public short getFinal() {
        return this.fFinal;
    }
    
    public boolean getIsAbstract() {
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
    
    public boolean getIsProhibitedSubstitution(final short prohibited) {
        return (this.fBlock & prohibited) != 0x0;
    }
    
    public short getProhibitedSubstitutions() {
        return this.fBlock;
    }
    
    public XSObjectList getAnnotations() {
        return null;
    }
}

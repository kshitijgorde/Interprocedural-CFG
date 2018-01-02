// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.NamespaceSupport;
import java.util.Vector;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.util.SymbolTable;

abstract class XSDAbstractTraverser
{
    protected static final String NO_NAME = "(no name)";
    protected static final int NOT_ALL_CONTEXT = 0;
    protected static final int PROCESSING_ALL_EL = 1;
    protected static final int GROUP_REF_WITH_ALL = 2;
    protected static final int CHILD_OF_GROUP = 4;
    protected static final int PROCESSING_ALL_GP = 8;
    protected XSDHandler fSchemaHandler;
    protected SymbolTable fSymbolTable;
    protected XSAttributeChecker fAttrChecker;
    ValidationState fValidationState;
    private static final XSSimpleType fQNameDV;
    private StringBuffer fPattern;
    private final XSFacets xsFacets;
    
    XSDAbstractTraverser(final XSDHandler handler, final XSAttributeChecker attrChecker) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fAttrChecker = null;
        this.fValidationState = new ValidationState();
        this.fPattern = new StringBuffer();
        this.xsFacets = new XSFacets();
        this.fSchemaHandler = handler;
        this.fAttrChecker = attrChecker;
    }
    
    void reset(final SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
        this.fValidationState.setExtraChecking(false);
        this.fValidationState.setSymbolTable(symbolTable);
    }
    
    void traverseAnnotationDecl(final Element annotationDecl, final Object[] parentAttrs, final boolean isGlobal, final XSDocumentInfo schemaDoc) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(annotationDecl, isGlobal, schemaDoc);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        for (Element child = DOMUtil.getFirstChildElement(annotationDecl); child != null; child = DOMUtil.getNextSiblingElement(child)) {
            final String name = DOMUtil.getLocalName(child);
            if (!name.equals(SchemaSymbols.ELT_APPINFO) && !name.equals(SchemaSymbols.ELT_DOCUMENTATION)) {
                this.reportSchemaError("src-annotation", null, child);
            }
            attrValues = this.fAttrChecker.checkAttributes(child, true, schemaDoc);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        }
    }
    
    FacetInfo traverseFacets(Element content, final XSSimpleType baseValidator, final XSDocumentInfo schemaDoc) {
        short facetsPresent = 0;
        short facetsFixed = 0;
        final boolean hasQName = this.containsQName(baseValidator);
        final Vector enumData = new Vector();
        final Vector enumNSDecls = hasQName ? new Vector() : null;
        int currentFacet = 0;
        while (content != null) {
            Object[] attrs = null;
            final String facet = DOMUtil.getLocalName(content);
            if (facet.equals(SchemaSymbols.ELT_ENUMERATION)) {
                attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc, hasQName);
                final String enumVal = (String)attrs[XSAttributeChecker.ATTIDX_VALUE];
                final NamespaceSupport nsDecls = (NamespaceSupport)attrs[XSAttributeChecker.ATTIDX_ENUMNSDECLS];
                if (baseValidator.getVariety() == 1 && baseValidator.getPrimitiveKind() == 19) {
                    schemaDoc.fValidationContext.setNamespaceSupport(nsDecls);
                    try {
                        final QName temp = (QName)XSDAbstractTraverser.fQNameDV.validate(enumVal, schemaDoc.fValidationContext, null);
                        this.fSchemaHandler.getGlobalDecl(schemaDoc, 6, temp, content);
                    }
                    catch (InvalidDatatypeValueException ex) {
                        this.reportSchemaError(ex.getKey(), ex.getArgs(), content);
                    }
                    schemaDoc.fValidationContext.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                }
                enumData.addElement(enumVal);
                if (hasQName) {
                    enumNSDecls.addElement(nsDecls);
                }
                Element child = DOMUtil.getFirstChildElement(content);
                if (child != null) {
                    if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.traverseAnnotationDecl(child, attrs, false, schemaDoc);
                        child = DOMUtil.getNextSiblingElement(child);
                    }
                    if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.reportSchemaError("s4s-elt-must-match", new Object[] { "enumeration", "(annotation?)" }, child);
                    }
                }
            }
            else if (facet.equals(SchemaSymbols.ELT_PATTERN)) {
                attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                if (this.fPattern.length() == 0) {
                    this.fPattern.append((String)attrs[XSAttributeChecker.ATTIDX_VALUE]);
                }
                else {
                    this.fPattern.append("|");
                    this.fPattern.append((String)attrs[XSAttributeChecker.ATTIDX_VALUE]);
                    Element child2 = DOMUtil.getFirstChildElement(content);
                    if (child2 != null) {
                        if (DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            this.traverseAnnotationDecl(child2, attrs, false, schemaDoc);
                            child2 = DOMUtil.getNextSiblingElement(child2);
                        }
                        if (child2 != null && DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            final Object[] args = { "Pattern facet has more than one annotation." };
                            this.reportSchemaError("s4s-elt-must-match", new Object[] { "pattern", "(annotation?)" }, child2);
                        }
                    }
                }
            }
            else {
                if (facet.equals(SchemaSymbols.ELT_MINLENGTH)) {
                    currentFacet = 4;
                }
                else if (facet.equals(SchemaSymbols.ELT_MAXLENGTH)) {
                    currentFacet = 8;
                }
                else if (facet.equals(SchemaSymbols.ELT_MAXEXCLUSIVE)) {
                    currentFacet = 256;
                }
                else if (facet.equals(SchemaSymbols.ELT_MAXINCLUSIVE)) {
                    currentFacet = 128;
                }
                else if (facet.equals(SchemaSymbols.ELT_MINEXCLUSIVE)) {
                    currentFacet = 512;
                }
                else if (facet.equals(SchemaSymbols.ELT_MININCLUSIVE)) {
                    currentFacet = 1024;
                }
                else if (facet.equals(SchemaSymbols.ELT_TOTALDIGITS)) {
                    currentFacet = 2048;
                }
                else if (facet.equals(SchemaSymbols.ELT_FRACTIONDIGITS)) {
                    currentFacet = 4096;
                }
                else if (facet.equals(SchemaSymbols.ELT_WHITESPACE)) {
                    currentFacet = 64;
                }
                else {
                    if (!facet.equals(SchemaSymbols.ELT_LENGTH)) {
                        break;
                    }
                    currentFacet = 2;
                }
                attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                if ((facetsPresent & currentFacet) != 0x0) {
                    this.reportSchemaError("src-single-facet-value", new Object[] { "The facet '" + facet + "' is defined more than once." }, content);
                }
                else if (attrs[XSAttributeChecker.ATTIDX_VALUE] != null) {
                    facetsPresent |= (short)currentFacet;
                    if (attrs[XSAttributeChecker.ATTIDX_FIXED]) {
                        facetsFixed |= (short)currentFacet;
                    }
                    switch (currentFacet) {
                        case 4: {
                            this.xsFacets.minLength = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 8: {
                            this.xsFacets.maxLength = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 256: {
                            this.xsFacets.maxExclusive = (String)attrs[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 128: {
                            this.xsFacets.maxInclusive = (String)attrs[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 512: {
                            this.xsFacets.minExclusive = (String)attrs[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 1024: {
                            this.xsFacets.minInclusive = (String)attrs[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 2048: {
                            this.xsFacets.totalDigits = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 4096: {
                            this.xsFacets.fractionDigits = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 64: {
                            this.xsFacets.whiteSpace = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).shortValue();
                            break;
                        }
                        case 2: {
                            this.xsFacets.length = ((XInt)attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                    }
                }
                Element child2 = DOMUtil.getFirstChildElement(content);
                if (child2 != null) {
                    if (DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.traverseAnnotationDecl(child2, attrs, false, schemaDoc);
                        child2 = DOMUtil.getNextSiblingElement(child2);
                    }
                    if (child2 != null && DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.reportSchemaError("s4s-elt-must-match", new Object[] { facet, "(annotation?)" }, child2);
                    }
                }
            }
            this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        if (enumData.size() > 0) {
            facetsPresent |= 0x20;
            this.xsFacets.enumeration = enumData;
            this.xsFacets.enumNSDecls = enumNSDecls;
        }
        if (this.fPattern.length() != 0) {
            facetsPresent |= 0x10;
            this.xsFacets.pattern = this.fPattern.toString();
        }
        this.fPattern.setLength(0);
        final FacetInfo fi = new FacetInfo();
        fi.facetdata = this.xsFacets;
        fi.nodeAfterFacets = content;
        fi.fPresentFacets = facetsPresent;
        fi.fFixedFacets = facetsFixed;
        return fi;
    }
    
    private boolean containsQName(final XSSimpleType type) {
        if (type.getVariety() == 1) {
            final short primitive = type.getPrimitiveKind();
            return primitive == 18 || primitive == 19;
        }
        if (type.getVariety() == 2) {
            return this.containsQName((XSSimpleType)type.getItemType());
        }
        if (type.getVariety() == 3) {
            final XSObjectList members = type.getMemberTypes();
            for (int i = 0; i < members.getLength(); ++i) {
                if (this.containsQName((XSSimpleType)members.getItem(i))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    Element traverseAttrsAndAttrGrps(final Element firstAttr, final XSAttributeGroupDecl attrGrp, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final XSComplexTypeDecl enclosingCT) {
        Element child = null;
        XSAttributeGroupDecl tempAttrGrp = null;
        XSAttributeUseImpl tempAttrUse = null;
        for (child = firstAttr; child != null; child = DOMUtil.getNextSiblingElement(child)) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                tempAttrUse = this.fSchemaHandler.fAttributeTraverser.traverseLocal(child, schemaDoc, grammar, enclosingCT);
                if (tempAttrUse == null) {
                    break;
                }
                if (attrGrp.getAttributeUse(tempAttrUse.fAttrDecl.getNamespace(), tempAttrUse.fAttrDecl.getName()) == null) {
                    final String idName = attrGrp.addAttributeUse(tempAttrUse);
                    if (idName != null) {
                        final String code = (enclosingCT == null) ? "ag-props-correct.3" : "ct-props-correct.5";
                        final String name = (enclosingCT == null) ? attrGrp.fName : enclosingCT.getName();
                        this.reportSchemaError(code, new Object[] { name, tempAttrUse.fAttrDecl.getName(), idName }, child);
                    }
                }
                else {
                    final String code2 = (enclosingCT == null) ? "ag-props-correct.2" : "ct-props-correct.4";
                    final String name2 = (enclosingCT == null) ? attrGrp.fName : enclosingCT.getName();
                    this.reportSchemaError(code2, new Object[] { name2, tempAttrUse.fAttrDecl.getName() }, child);
                }
            }
            else {
                if (!childName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    break;
                }
                tempAttrGrp = this.fSchemaHandler.fAttributeGroupTraverser.traverseLocal(child, schemaDoc, grammar);
                if (tempAttrGrp == null) {
                    break;
                }
                final XSObjectList attrUseS = tempAttrGrp.getAttributeUses();
                XSAttributeUseImpl existingAttrUse = null;
                for (int attrCount = attrUseS.getLength(), i = 0; i < attrCount; ++i) {
                    final XSAttributeUseImpl oneAttrUse = (XSAttributeUseImpl)attrUseS.getItem(i);
                    existingAttrUse = attrGrp.getAttributeUse(oneAttrUse.fAttrDecl.getNamespace(), oneAttrUse.fAttrDecl.getName());
                    if (existingAttrUse == null) {
                        final String idName2 = attrGrp.addAttributeUse(oneAttrUse);
                        if (idName2 != null) {
                            this.reportSchemaError("cvc-complex-type.5.3", new Object[] { oneAttrUse.fAttrDecl.getName(), idName2 }, child);
                        }
                    }
                    else {
                        this.reportSchemaError("ct-props-correct.4", new Object[] { "Duplicate attribute " + existingAttrUse.fAttrDecl.getName() + " found " }, child);
                    }
                }
                if (tempAttrGrp.fAttributeWC != null) {
                    if (attrGrp.fAttributeWC == null) {
                        attrGrp.fAttributeWC = tempAttrGrp.fAttributeWC;
                    }
                    else {
                        attrGrp.fAttributeWC = attrGrp.fAttributeWC.performIntersectionWith(tempAttrGrp.fAttributeWC, attrGrp.fAttributeWC.fProcessContents);
                        if (attrGrp.fAttributeWC == null) {
                            this.reportSchemaError("src-wildcard", new Object[] { "intersection of wildcards is not expressible" }, child);
                        }
                    }
                }
            }
        }
        if (child != null) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
                final XSWildcardDecl tempAttrWC = this.fSchemaHandler.fWildCardTraverser.traverseAnyAttribute(child, schemaDoc, grammar);
                if (attrGrp.fAttributeWC == null) {
                    attrGrp.fAttributeWC = tempAttrWC;
                }
                else {
                    attrGrp.fAttributeWC = tempAttrWC.performIntersectionWith(attrGrp.fAttributeWC, tempAttrWC.fProcessContents);
                    if (attrGrp.fAttributeWC == null) {
                        this.reportSchemaError("src-wildcard", new Object[] { "intersection of wildcards is not expressible" }, child);
                    }
                }
                child = DOMUtil.getNextSiblingElement(child);
            }
        }
        return child;
    }
    
    void reportSchemaError(final String key, final Object[] args, final Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }
    
    void checkNotationType(final String refName, final XSTypeDecl typeDecl, final Element elem) {
        if (typeDecl.getTypeCategory() == 14 && ((XSSimpleType)typeDecl).getVariety() == 1 && ((XSSimpleType)typeDecl).getPrimitiveKind() == 19 && (((XSSimpleType)typeDecl).getDefinedFacets() & 0x20) == 0x0) {
            this.reportSchemaError("enumeration-required-notation", new Object[] { refName }, elem);
        }
    }
    
    protected XSParticleDecl checkOccurrences(final XSParticleDecl particle, final String particleName, final Element parent, final int allContextFlags, final long defaultVals) {
        int min = particle.fMinOccurs;
        int max = particle.fMaxOccurs;
        final boolean defaultMin = (defaultVals & 1 << XSAttributeChecker.ATTIDX_MINOCCURS) != 0x0L;
        final boolean defaultMax = (defaultVals & 1 << XSAttributeChecker.ATTIDX_MAXOCCURS) != 0x0L;
        final boolean processingAllEl = (allContextFlags & 0x1) != 0x0;
        final boolean processingAllGP = (allContextFlags & 0x8) != 0x0;
        final boolean groupRefWithAll = (allContextFlags & 0x2) != 0x0;
        final boolean isGroupChild = (allContextFlags & 0x4) != 0x0;
        if (isGroupChild) {
            if (!defaultMin) {
                final Object[] args = { particleName, "minOccurs" };
                this.reportSchemaError("s4s-att-not-allowed", args, parent);
                min = 1;
            }
            if (!defaultMax) {
                final Object[] args = { particleName, "maxOccurs" };
                this.reportSchemaError("s4s-att-not-allowed", args, parent);
                max = 1;
            }
        }
        if (min == 0 && max == 0) {
            particle.fType = 0;
            return null;
        }
        if (processingAllEl) {
            if (max != 1) {
                this.reportSchemaError("cos-all-limited.2", null, parent);
                max = 1;
                if (min > 1) {
                    min = 1;
                }
            }
        }
        else if ((processingAllGP || groupRefWithAll) && max != 1) {
            this.reportSchemaError("cos-all-limited.1.2", null, parent);
            if (min > 1) {
                min = 1;
            }
            max = 1;
        }
        particle.fMaxOccurs = min;
        particle.fMaxOccurs = max;
        return particle;
    }
    
    static {
        fQNameDV = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("QName");
    }
    
    class FacetInfo
    {
        XSFacets facetdata;
        Element nodeAfterFacets;
        short fPresentFacets;
        short fFixedFacets;
    }
}

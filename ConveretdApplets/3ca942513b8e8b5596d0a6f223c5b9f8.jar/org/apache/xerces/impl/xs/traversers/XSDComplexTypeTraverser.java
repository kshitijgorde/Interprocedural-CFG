// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;
import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.xs.XSTypeDefinition;

class XSDComplexTypeTraverser extends XSDAbstractParticleTraverser
{
    private static final int GLOBAL_NUM = 11;
    private String fName;
    private String fTargetNamespace;
    private short fDerivedBy;
    private short fFinal;
    private short fBlock;
    private short fContentType;
    private XSTypeDefinition fBaseType;
    private XSAttributeGroupDecl fAttrGrp;
    private XSSimpleType fXSSimpleType;
    private XSParticleDecl fParticle;
    private boolean fIsAbstract;
    private XSComplexTypeDecl fComplexTypeDecl;
    private XSAnnotationImpl[] fAnnotations;
    private XSParticleDecl fEmptyParticle;
    private Object[] fGlobalStore;
    private int fGlobalStorePos;
    private static final boolean DEBUG = false;
    private SchemaDVFactory schemaFactory;
    
    XSDComplexTypeTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
        this.fName = null;
        this.fTargetNamespace = null;
        this.fDerivedBy = 2;
        this.fFinal = 0;
        this.fBlock = 0;
        this.fContentType = 0;
        this.fBaseType = null;
        this.fAttrGrp = null;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fIsAbstract = false;
        this.fComplexTypeDecl = null;
        this.fAnnotations = null;
        this.fEmptyParticle = null;
        this.fGlobalStore = null;
        this.fGlobalStorePos = 0;
        this.schemaFactory = SchemaDVFactory.getInstance();
    }
    
    XSComplexTypeDecl traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final String genAnonTypeName = this.genAnonTypeName(element);
        this.contentBackup();
        final XSComplexTypeDecl traverseComplexTypeDecl = this.traverseComplexTypeDecl(element, genAnonTypeName, checkAttributes, xsDocumentInfo, schemaGrammar);
        this.contentRestore();
        schemaGrammar.addComplexTypeDecl(traverseComplexTypeDecl, super.fSchemaHandler.element2Locator(element));
        traverseComplexTypeDecl.setIsAnonymous();
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return traverseComplexTypeDecl;
    }
    
    XSComplexTypeDecl traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        this.contentBackup();
        final XSComplexTypeDecl traverseComplexTypeDecl = this.traverseComplexTypeDecl(element, s, checkAttributes, xsDocumentInfo, schemaGrammar);
        this.contentRestore();
        if (s == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_COMPLEXTYPE, SchemaSymbols.ATT_NAME }, element);
        }
        else {
            schemaGrammar.addGlobalTypeDecl(traverseComplexTypeDecl);
        }
        schemaGrammar.addComplexTypeDecl(traverseComplexTypeDecl, super.fSchemaHandler.element2Locator(element));
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return traverseComplexTypeDecl;
    }
    
    private XSComplexTypeDecl traverseComplexTypeDecl(final Element element, final String fName, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        this.fComplexTypeDecl = new XSComplexTypeDecl();
        this.fAttrGrp = new XSAttributeGroupDecl();
        final Boolean b = (Boolean)array[XSAttributeChecker.ATTIDX_ABSTRACT];
        final XInt xInt = (XInt)array[XSAttributeChecker.ATTIDX_BLOCK];
        final Boolean b2 = (Boolean)array[XSAttributeChecker.ATTIDX_MIXED];
        final XInt xInt2 = (XInt)array[XSAttributeChecker.ATTIDX_FINAL];
        this.fName = fName;
        this.fComplexTypeDecl.setName(this.fName);
        this.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
        this.fBlock = ((xInt == null) ? xsDocumentInfo.fBlockDefault : xInt.shortValue());
        this.fFinal = ((xInt2 == null) ? xsDocumentInfo.fFinalDefault : xInt2.shortValue());
        this.fBlock &= 0x3;
        this.fFinal &= 0x3;
        this.fIsAbstract = (b != null && b);
        this.fAnnotations = null;
        try {
            Element element2 = DOMUtil.getFirstChildElement(element);
            if (element2 != null) {
                if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.addAnnotation(this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo));
                    element2 = DOMUtil.getNextSiblingElement(element2);
                }
                else {
                    final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                    if (syntheticAnnotation != null) {
                        this.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo));
                    }
                }
                if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, element2);
                }
            }
            else {
                final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation2 != null) {
                    this.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation2, array, false, xsDocumentInfo));
                }
            }
            if (element2 == null) {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.processComplexContent(element2, b2, false, xsDocumentInfo, schemaGrammar);
            }
            else if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_SIMPLECONTENT)) {
                this.traverseSimpleContent(element2, xsDocumentInfo, schemaGrammar);
                final Element nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
                if (nextSiblingElement != null) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(nextSiblingElement) }, nextSiblingElement);
                }
            }
            else if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_COMPLEXCONTENT)) {
                this.traverseComplexContent(element2, b2, xsDocumentInfo, schemaGrammar);
                final Element nextSiblingElement2 = DOMUtil.getNextSiblingElement(element2);
                if (nextSiblingElement2 != null) {
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(nextSiblingElement2) }, nextSiblingElement2);
                }
            }
            else {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.processComplexContent(element2, b2, false, xsDocumentInfo, schemaGrammar);
            }
        }
        catch (ComplexTypeRecoverableError complexTypeRecoverableError) {
            this.handleComplexTypeError(complexTypeRecoverableError.getMessage(), complexTypeRecoverableError.errorSubstText, complexTypeRecoverableError.errorElem);
        }
        this.fComplexTypeDecl.setValues(this.fName, this.fTargetNamespace, this.fBaseType, this.fDerivedBy, this.fFinal, this.fBlock, this.fContentType, this.fIsAbstract, this.fAttrGrp, this.fXSSimpleType, this.fParticle, new XSObjectListImpl(this.fAnnotations, (this.fAnnotations == null) ? 0 : this.fAnnotations.length));
        return this.fComplexTypeDecl;
    }
    
    private void traverseSimpleContent(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        this.fContentType = 1;
        this.fParticle = null;
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.addAnnotation(this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo));
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                this.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation, checkAttributes, false, xsDocumentInfo));
            }
        }
        if (element2 == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[] { this.fName, SchemaSymbols.ELT_SIMPLECONTENT }, element);
        }
        final String localName = DOMUtil.getLocalName(element2);
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = 2;
        }
        else {
            if (!localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, localName }, element2);
            }
            this.fDerivedBy = 1;
        }
        final Element nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
        if (nextSiblingElement != null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(nextSiblingElement) }, nextSiblingElement);
        }
        final Object[] checkAttributes2 = super.fAttrChecker.checkAttributes(element2, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes2[XSAttributeChecker.ATTIDX_BASE];
        if (qName == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[] { localName, "base" }, element2);
        }
        final XSTypeDefinition fBaseType = (XSTypeDefinition)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 7, qName, element2);
        if (fBaseType == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError();
        }
        this.fBaseType = fBaseType;
        XSSimpleType fxsSimpleType = null;
        XSComplexTypeDecl xsComplexTypeDecl = null;
        short n;
        if (fBaseType.getTypeCategory() == 15) {
            xsComplexTypeDecl = (XSComplexTypeDecl)fBaseType;
            n = xsComplexTypeDecl.getFinal();
            if (xsComplexTypeDecl.getContentType() == 1) {
                fxsSimpleType = (XSSimpleType)xsComplexTypeDecl.getSimpleType();
            }
            else if (this.fDerivedBy != 2 || xsComplexTypeDecl.getContentType() != 3 || !((XSParticleDecl)xsComplexTypeDecl.getParticle()).emptiable()) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[] { this.fName, xsComplexTypeDecl.getName() }, element2);
            }
        }
        else {
            fxsSimpleType = (XSSimpleType)fBaseType;
            if (this.fDerivedBy == 2) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[] { this.fName, fxsSimpleType.getName() }, element2);
            }
            n = fxsSimpleType.getFinal();
        }
        if ((n & this.fDerivedBy) != 0x0) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError((this.fDerivedBy == 1) ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1", new Object[] { this.fName, this.fBaseType.getName() }, element2);
        }
        final Element element3 = element2;
        Element element4 = DOMUtil.getFirstChildElement(element2);
        if (element4 != null) {
            if (DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.addAnnotation(this.traverseAnnotationDecl(element4, checkAttributes2, false, xsDocumentInfo));
                element4 = DOMUtil.getNextSiblingElement(element4);
            }
            else {
                final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element3);
                if (syntheticAnnotation2 != null) {
                    this.addAnnotation(this.traverseSyntheticAnnotation(element3, syntheticAnnotation2, checkAttributes2, false, xsDocumentInfo));
                }
            }
            if (element4 != null && DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_ANNOTATION)) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, element4);
            }
        }
        else {
            final String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element3);
            if (syntheticAnnotation3 != null) {
                this.addAnnotation(this.traverseSyntheticAnnotation(element3, syntheticAnnotation3, checkAttributes2, false, xsDocumentInfo));
            }
        }
        if (this.fDerivedBy == 2) {
            if (element4 != null && DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                final XSSimpleType traverseLocal = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(element4, xsDocumentInfo, schemaGrammar);
                if (traverseLocal == null) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError();
                }
                if (fxsSimpleType != null && !XSConstraints.checkSimpleDerivationOk(traverseLocal, fxsSimpleType, fxsSimpleType.getFinal())) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.2.2.1", new Object[] { this.fName, traverseLocal.getName(), fxsSimpleType.getName() }, element4);
                }
                fxsSimpleType = traverseLocal;
                element4 = DOMUtil.getNextSiblingElement(element4);
            }
            if (fxsSimpleType == null) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("src-ct.2.2", new Object[] { this.fName }, element4);
            }
            Element nodeAfterFacets = null;
            XSFacets facetdata = null;
            short fPresentFacets = 0;
            short fFixedFacets = 0;
            if (element4 != null) {
                final FacetInfo traverseFacets = this.traverseFacets(element4, fxsSimpleType, xsDocumentInfo);
                nodeAfterFacets = traverseFacets.nodeAfterFacets;
                facetdata = traverseFacets.facetdata;
                fPresentFacets = traverseFacets.fPresentFacets;
                fFixedFacets = traverseFacets.fFixedFacets;
            }
            this.fXSSimpleType = this.schemaFactory.createTypeRestriction(null, xsDocumentInfo.fTargetNamespace, (short)0, fxsSimpleType, null);
            try {
                super.fValidationState.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
                this.fXSSimpleType.applyFacets(facetdata, fPresentFacets, fFixedFacets, super.fValidationState);
            }
            catch (InvalidDatatypeFacetException ex) {
                this.reportSchemaError(ex.getKey(), ex.getArgs(), element4);
            }
            if (nodeAfterFacets != null) {
                if (!this.isAttrOrAttrGroup(nodeAfterFacets)) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(nodeAfterFacets) }, nodeAfterFacets);
                }
                final Element traverseAttrsAndAttrGrps = this.traverseAttrsAndAttrGrps(nodeAfterFacets, this.fAttrGrp, xsDocumentInfo, schemaGrammar, this.fComplexTypeDecl);
                if (traverseAttrsAndAttrGrps != null) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(traverseAttrsAndAttrGrps) }, traverseAttrsAndAttrGrps);
                }
            }
            try {
                this.mergeAttributes(xsComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, false, element);
            }
            catch (ComplexTypeRecoverableError complexTypeRecoverableError) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw complexTypeRecoverableError;
            }
            this.fAttrGrp.removeProhibitedAttrs();
            final Object[] validRestriction = this.fAttrGrp.validRestrictionOf(this.fName, xsComplexTypeDecl.getAttrGrp());
            if (validRestriction != null) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError((String)validRestriction[validRestriction.length - 1], validRestriction, nodeAfterFacets);
            }
        }
        else {
            this.fXSSimpleType = fxsSimpleType;
            if (element4 != null) {
                final Element element5 = element4;
                if (!this.isAttrOrAttrGroup(element5)) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(element5) }, element5);
                }
                final Element traverseAttrsAndAttrGrps2 = this.traverseAttrsAndAttrGrps(element5, this.fAttrGrp, xsDocumentInfo, schemaGrammar, this.fComplexTypeDecl);
                if (traverseAttrsAndAttrGrps2 != null) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(traverseAttrsAndAttrGrps2) }, traverseAttrsAndAttrGrps2);
                }
                this.fAttrGrp.removeProhibitedAttrs();
            }
            if (xsComplexTypeDecl != null) {
                try {
                    this.mergeAttributes(xsComplexTypeDecl.getAttrGrp(), this.fAttrGrp, this.fName, true, element);
                }
                catch (ComplexTypeRecoverableError complexTypeRecoverableError2) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw complexTypeRecoverableError2;
                }
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
    }
    
    private void traverseComplexContent(final Element element, final boolean b, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        boolean booleanValue = b;
        final Boolean b2 = (Boolean)checkAttributes[XSAttributeChecker.ATTIDX_MIXED];
        if (b2 != null) {
            booleanValue = b2;
        }
        this.fXSSimpleType = null;
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.addAnnotation(this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo));
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                this.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation, checkAttributes, false, xsDocumentInfo));
            }
        }
        if (element2 == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[] { this.fName, SchemaSymbols.ELT_COMPLEXCONTENT }, element);
        }
        final String localName = DOMUtil.getLocalName(element2);
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = 2;
        }
        else {
            if (!localName.equals(SchemaSymbols.ELT_EXTENSION)) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, localName }, element2);
            }
            this.fDerivedBy = 1;
        }
        final Element nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
        if (nextSiblingElement != null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(nextSiblingElement) }, nextSiblingElement);
        }
        final Object[] checkAttributes2 = super.fAttrChecker.checkAttributes(element2, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes2[XSAttributeChecker.ATTIDX_BASE];
        if (qName == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[] { localName, "base" }, element2);
        }
        final XSTypeDefinition xsTypeDefinition = (XSTypeDefinition)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 7, qName, element2);
        if (xsTypeDefinition == null) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError();
        }
        if (!(xsTypeDefinition instanceof XSComplexTypeDecl)) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError("src-ct.1", new Object[] { this.fName, xsTypeDefinition.getName() }, element2);
        }
        final XSComplexTypeDecl fBaseType = (XSComplexTypeDecl)xsTypeDefinition;
        this.fBaseType = fBaseType;
        if ((fBaseType.getFinal() & this.fDerivedBy) != 0x0) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw new ComplexTypeRecoverableError((this.fDerivedBy == 1) ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1", new Object[] { this.fName, this.fBaseType.getName() }, element2);
        }
        Element element3 = DOMUtil.getFirstChildElement(element2);
        if (element3 != null) {
            if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.addAnnotation(this.traverseAnnotationDecl(element3, checkAttributes2, false, xsDocumentInfo));
                element3 = DOMUtil.getNextSiblingElement(element3);
            }
            else {
                final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element3);
                if (syntheticAnnotation2 != null) {
                    this.addAnnotation(this.traverseSyntheticAnnotation(element3, syntheticAnnotation2, checkAttributes2, false, xsDocumentInfo));
                }
            }
            if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, element3);
            }
        }
        else {
            final String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element3);
            if (syntheticAnnotation3 != null) {
                this.addAnnotation(this.traverseSyntheticAnnotation(element3, syntheticAnnotation3, checkAttributes2, false, xsDocumentInfo));
            }
        }
        try {
            this.processComplexContent(element3, booleanValue, true, xsDocumentInfo, schemaGrammar);
        }
        catch (ComplexTypeRecoverableError complexTypeRecoverableError) {
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
            throw complexTypeRecoverableError;
        }
        final XSParticleDecl fParticle = (XSParticleDecl)fBaseType.getParticle();
        if (this.fDerivedBy == 2) {
            if (this.fContentType == 3 && fBaseType.getContentType() != 3) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.4.1.2", new Object[] { this.fName, fBaseType.getName() }, element3);
            }
            try {
                this.mergeAttributes(fBaseType.getAttrGrp(), this.fAttrGrp, this.fName, false, element3);
            }
            catch (ComplexTypeRecoverableError complexTypeRecoverableError2) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw complexTypeRecoverableError2;
            }
            this.fAttrGrp.removeProhibitedAttrs();
            if (fBaseType != SchemaGrammar.fAnyType) {
                final Object[] validRestriction = this.fAttrGrp.validRestrictionOf(this.fName, fBaseType.getAttrGrp());
                if (validRestriction != null) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError((String)validRestriction[validRestriction.length - 1], validRestriction, element3);
                }
            }
        }
        else {
            if (this.fParticle == null) {
                this.fContentType = fBaseType.getContentType();
                this.fXSSimpleType = (XSSimpleType)fBaseType.getSimpleType();
                this.fParticle = fParticle;
            }
            else if (fBaseType.getContentType() != 0) {
                if (this.fContentType == 2 && fBaseType.getContentType() != 2) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.a", new Object[] { this.fName }, element3);
                }
                if (this.fContentType == 3 && fBaseType.getContentType() != 3) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.b", new Object[] { this.fName }, element3);
                }
                if ((this.fParticle.fType == 3 && ((XSModelGroupImpl)this.fParticle.fValue).fCompositor == 103) || (((XSParticleDecl)fBaseType.getParticle()).fType == 3 && ((XSModelGroupImpl)((XSParticleDecl)fBaseType.getParticle()).fValue).fCompositor == 103)) {
                    super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                    super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                    throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], element3);
                }
                final XSModelGroupImpl fValue = new XSModelGroupImpl();
                fValue.fCompositor = 102;
                fValue.fParticleCount = 2;
                (fValue.fParticles = new XSParticleDecl[2])[0] = (XSParticleDecl)fBaseType.getParticle();
                fValue.fParticles[1] = this.fParticle;
                final XSParticleDecl fParticle2 = new XSParticleDecl();
                fParticle2.fType = 3;
                fParticle2.fValue = fValue;
                this.fParticle = fParticle2;
            }
            this.fAttrGrp.removeProhibitedAttrs();
            try {
                this.mergeAttributes(fBaseType.getAttrGrp(), this.fAttrGrp, this.fName, true, element3);
            }
            catch (ComplexTypeRecoverableError complexTypeRecoverableError3) {
                super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                throw complexTypeRecoverableError3;
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
    }
    
    private void mergeAttributes(final XSAttributeGroupDecl xsAttributeGroupDecl, final XSAttributeGroupDecl xsAttributeGroupDecl2, final String s, final boolean b, final Element element) throws ComplexTypeRecoverableError {
        final XSObjectList attributeUses = xsAttributeGroupDecl.getAttributeUses();
        for (int length = attributeUses.getLength(), i = 0; i < length; ++i) {
            final XSAttributeUseImpl xsAttributeUseImpl = (XSAttributeUseImpl)attributeUses.item(i);
            if (xsAttributeGroupDecl2.getAttributeUse(xsAttributeUseImpl.fAttrDecl.getNamespace(), xsAttributeUseImpl.fAttrDecl.getName()) == null) {
                final String addAttributeUse = xsAttributeGroupDecl2.addAttributeUse(xsAttributeUseImpl);
                if (addAttributeUse != null) {
                    throw new ComplexTypeRecoverableError("ct-props-correct.5", new Object[] { s, addAttributeUse, xsAttributeUseImpl.fAttrDecl.getName() }, element);
                }
            }
            else if (b) {
                throw new ComplexTypeRecoverableError("ct-props-correct.4", new Object[] { s, xsAttributeUseImpl.fAttrDecl.getName() }, element);
            }
        }
        if (b) {
            if (xsAttributeGroupDecl2.fAttributeWC == null) {
                xsAttributeGroupDecl2.fAttributeWC = xsAttributeGroupDecl.fAttributeWC;
            }
            else if (xsAttributeGroupDecl.fAttributeWC != null) {
                xsAttributeGroupDecl2.fAttributeWC = xsAttributeGroupDecl2.fAttributeWC.performUnionWith(xsAttributeGroupDecl.fAttributeWC, xsAttributeGroupDecl2.fAttributeWC.fProcessContents);
            }
        }
    }
    
    private void processComplexContent(final Element element, final boolean b, final boolean b2, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) throws ComplexTypeRecoverableError {
        Element element2 = null;
        XSParticleDecl fParticle = null;
        boolean b3 = false;
        if (element != null) {
            final String localName = DOMUtil.getLocalName(element);
            if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                fParticle = super.fSchemaHandler.fGroupTraverser.traverseLocal(element, xsDocumentInfo, schemaGrammar);
                element2 = DOMUtil.getNextSiblingElement(element);
            }
            else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                fParticle = this.traverseSequence(element, xsDocumentInfo, schemaGrammar, 0, this.fComplexTypeDecl);
                if (fParticle != null && ((XSModelGroupImpl)fParticle.fValue).fParticleCount == 0) {
                    b3 = true;
                }
                element2 = DOMUtil.getNextSiblingElement(element);
            }
            else if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                fParticle = this.traverseChoice(element, xsDocumentInfo, schemaGrammar, 0, this.fComplexTypeDecl);
                if (fParticle != null && fParticle.fMinOccurs == 0 && ((XSModelGroupImpl)fParticle.fValue).fParticleCount == 0) {
                    b3 = true;
                }
                element2 = DOMUtil.getNextSiblingElement(element);
            }
            else if (localName.equals(SchemaSymbols.ELT_ALL)) {
                fParticle = this.traverseAll(element, xsDocumentInfo, schemaGrammar, 8, this.fComplexTypeDecl);
                if (fParticle != null && ((XSModelGroupImpl)fParticle.fValue).fParticleCount == 0) {
                    b3 = true;
                }
                element2 = DOMUtil.getNextSiblingElement(element);
            }
            else {
                element2 = element;
            }
        }
        if (b3) {
            Element element3 = DOMUtil.getFirstChildElement(element);
            if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                element3 = DOMUtil.getNextSiblingElement(element3);
            }
            if (element3 == null) {
                fParticle = null;
            }
        }
        if (fParticle == null && b) {
            if (this.fEmptyParticle == null) {
                final XSModelGroupImpl fValue = new XSModelGroupImpl();
                fValue.fCompositor = 102;
                fValue.fParticleCount = 0;
                fValue.fParticles = null;
                this.fEmptyParticle = new XSParticleDecl();
                this.fEmptyParticle.fType = 3;
                this.fEmptyParticle.fValue = fValue;
            }
            fParticle = this.fEmptyParticle;
        }
        this.fParticle = fParticle;
        if (this.fParticle == null) {
            this.fContentType = 0;
        }
        else if (b) {
            this.fContentType = 3;
        }
        else {
            this.fContentType = 2;
        }
        if (element2 != null) {
            if (!this.isAttrOrAttrGroup(element2)) {
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(element2) }, element2);
            }
            final Element traverseAttrsAndAttrGrps = this.traverseAttrsAndAttrGrps(element2, this.fAttrGrp, xsDocumentInfo, schemaGrammar, this.fComplexTypeDecl);
            if (traverseAttrsAndAttrGrps != null) {
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[] { this.fName, DOMUtil.getLocalName(traverseAttrsAndAttrGrps) }, traverseAttrsAndAttrGrps);
            }
            if (!b2) {
                this.fAttrGrp.removeProhibitedAttrs();
            }
        }
    }
    
    private boolean isAttrOrAttrGroup(final Element element) {
        final String localName = DOMUtil.getLocalName(element);
        return localName.equals(SchemaSymbols.ELT_ATTRIBUTE) || localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP) || localName.equals(SchemaSymbols.ELT_ANYATTRIBUTE);
    }
    
    private void traverseSimpleContentDecl(final Element element) {
    }
    
    private void traverseComplexContentDecl(final Element element, final boolean b) {
    }
    
    private String genAnonTypeName(final Element element) {
        final StringBuffer sb = new StringBuffer("#AnonType_");
        for (Element element2 = DOMUtil.getParent(element); element2 != null && element2 != DOMUtil.getRoot(DOMUtil.getDocument(element2)); element2 = DOMUtil.getParent(element2)) {
            sb.append(element2.getAttribute(SchemaSymbols.ATT_NAME));
        }
        return sb.toString();
    }
    
    private void handleComplexTypeError(final String s, final Object[] array, final Element element) {
        if (s != null) {
            this.reportSchemaError(s, array, element);
        }
        this.fBaseType = SchemaGrammar.fAnyType;
        this.fContentType = 3;
        this.fParticle = this.getErrorContent();
        this.fAttrGrp.fAttributeWC = this.getErrorWildcard();
    }
    
    private XSParticleDecl getErrorContent() {
        final XSParticleDecl xsParticleDecl = new XSParticleDecl();
        xsParticleDecl.fType = 2;
        xsParticleDecl.fValue = this.getErrorWildcard();
        xsParticleDecl.fMinOccurs = 0;
        xsParticleDecl.fMaxOccurs = -1;
        final XSModelGroupImpl fValue = new XSModelGroupImpl();
        fValue.fCompositor = 102;
        fValue.fParticleCount = 1;
        (fValue.fParticles = new XSParticleDecl[1])[0] = xsParticleDecl;
        final XSParticleDecl xsParticleDecl2 = new XSParticleDecl();
        xsParticleDecl2.fType = 3;
        xsParticleDecl2.fValue = fValue;
        return xsParticleDecl2;
    }
    
    private XSWildcardDecl getErrorWildcard() {
        final XSWildcardDecl xsWildcardDecl = new XSWildcardDecl();
        xsWildcardDecl.fProcessContents = 2;
        return xsWildcardDecl;
    }
    
    private void contentBackup() {
        if (this.fGlobalStore == null) {
            this.fGlobalStore = new Object[11];
            this.fGlobalStorePos = 0;
        }
        if (this.fGlobalStorePos == this.fGlobalStore.length) {
            final Object[] fGlobalStore = new Object[this.fGlobalStorePos + 11];
            System.arraycopy(this.fGlobalStore, 0, fGlobalStore, 0, this.fGlobalStorePos);
            this.fGlobalStore = fGlobalStore;
        }
        this.fGlobalStore[this.fGlobalStorePos++] = this.fComplexTypeDecl;
        this.fGlobalStore[this.fGlobalStorePos++] = (this.fIsAbstract ? Boolean.TRUE : Boolean.FALSE);
        this.fGlobalStore[this.fGlobalStorePos++] = this.fName;
        this.fGlobalStore[this.fGlobalStorePos++] = this.fTargetNamespace;
        this.fGlobalStore[this.fGlobalStorePos++] = new Integer((this.fDerivedBy << 16) + this.fFinal);
        this.fGlobalStore[this.fGlobalStorePos++] = new Integer((this.fBlock << 16) + this.fContentType);
        this.fGlobalStore[this.fGlobalStorePos++] = this.fBaseType;
        this.fGlobalStore[this.fGlobalStorePos++] = this.fAttrGrp;
        this.fGlobalStore[this.fGlobalStorePos++] = this.fParticle;
        this.fGlobalStore[this.fGlobalStorePos++] = this.fXSSimpleType;
        this.fGlobalStore[this.fGlobalStorePos++] = this.fAnnotations;
    }
    
    private void contentRestore() {
        final Object[] fGlobalStore = this.fGlobalStore;
        final int fGlobalStorePos = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos;
        this.fAnnotations = (XSAnnotationImpl[])fGlobalStore[fGlobalStorePos];
        final Object[] fGlobalStore2 = this.fGlobalStore;
        final int fGlobalStorePos2 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos2;
        this.fXSSimpleType = (XSSimpleType)fGlobalStore2[fGlobalStorePos2];
        final Object[] fGlobalStore3 = this.fGlobalStore;
        final int fGlobalStorePos3 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos3;
        this.fParticle = (XSParticleDecl)fGlobalStore3[fGlobalStorePos3];
        final Object[] fGlobalStore4 = this.fGlobalStore;
        final int fGlobalStorePos4 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos4;
        this.fAttrGrp = (XSAttributeGroupDecl)fGlobalStore4[fGlobalStorePos4];
        final Object[] fGlobalStore5 = this.fGlobalStore;
        final int fGlobalStorePos5 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos5;
        this.fBaseType = (XSTypeDefinition)fGlobalStore5[fGlobalStorePos5];
        final Object[] fGlobalStore6 = this.fGlobalStore;
        final int fGlobalStorePos6 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos6;
        final int intValue = (int)fGlobalStore6[fGlobalStorePos6];
        this.fBlock = (short)(intValue >> 16);
        this.fContentType = (short)intValue;
        final Object[] fGlobalStore7 = this.fGlobalStore;
        final int fGlobalStorePos7 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos7;
        final int intValue2 = (int)fGlobalStore7[fGlobalStorePos7];
        this.fDerivedBy = (short)(intValue2 >> 16);
        this.fFinal = (short)intValue2;
        final Object[] fGlobalStore8 = this.fGlobalStore;
        final int fGlobalStorePos8 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos8;
        this.fTargetNamespace = (String)fGlobalStore8[fGlobalStorePos8];
        final Object[] fGlobalStore9 = this.fGlobalStore;
        final int fGlobalStorePos9 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos9;
        this.fName = (String)fGlobalStore9[fGlobalStorePos9];
        final Object[] fGlobalStore10 = this.fGlobalStore;
        final int fGlobalStorePos10 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos10;
        this.fIsAbstract = (boolean)fGlobalStore10[fGlobalStorePos10];
        final Object[] fGlobalStore11 = this.fGlobalStore;
        final int fGlobalStorePos11 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos11;
        this.fComplexTypeDecl = (XSComplexTypeDecl)fGlobalStore11[fGlobalStorePos11];
    }
    
    private void addAnnotation(final XSAnnotationImpl xsAnnotationImpl) {
        if (xsAnnotationImpl == null) {
            return;
        }
        if (this.fAnnotations == null) {
            this.fAnnotations = new XSAnnotationImpl[1];
        }
        else {
            final XSAnnotationImpl[] fAnnotations = new XSAnnotationImpl[this.fAnnotations.length + 1];
            System.arraycopy(this.fAnnotations, 0, fAnnotations, 0, this.fAnnotations.length);
            this.fAnnotations = fAnnotations;
        }
        this.fAnnotations[this.fAnnotations.length - 1] = xsAnnotationImpl;
    }
    
    private class ComplexTypeRecoverableError extends Exception
    {
        private static final long serialVersionUID = 6802729912091130335L;
        Object[] errorSubstText;
        Element errorElem;
        
        ComplexTypeRecoverableError() {
            this.errorSubstText = null;
            this.errorElem = null;
        }
        
        ComplexTypeRecoverableError(final String s, final Object[] errorSubstText, final Element errorElem) {
            super(s);
            this.errorSubstText = null;
            this.errorElem = null;
            this.errorSubstText = errorSubstText;
            this.errorElem = errorElem;
        }
    }
}

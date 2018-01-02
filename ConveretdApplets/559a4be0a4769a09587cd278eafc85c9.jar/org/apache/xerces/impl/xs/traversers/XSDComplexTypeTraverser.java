// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;
import org.apache.xerces.impl.dv.SchemaDVFactory;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.XSTypeDecl;

class XSDComplexTypeTraverser extends XSDAbstractParticleTraverser
{
    private static final int GLOBAL_NUM = 10;
    private String fName;
    private String fTargetNamespace;
    private short fDerivedBy;
    private short fFinal;
    private short fBlock;
    private short fContentType;
    private XSTypeDecl fBaseType;
    private XSAttributeGroupDecl fAttrGrp;
    private XSSimpleType fXSSimpleType;
    private XSParticleDecl fParticle;
    private boolean fIsAbstract;
    private XSComplexTypeDecl fComplexTypeDecl;
    private Object[] fGlobalStore;
    private int fGlobalStorePos;
    private static final boolean DEBUG = false;
    private SchemaDVFactory schemaFactory;
    
    XSDComplexTypeTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
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
        this.fGlobalStore = null;
        this.fGlobalStorePos = 0;
        this.schemaFactory = SchemaDVFactory.getInstance();
    }
    
    XSComplexTypeDecl traverseLocal(final Element complexTypeNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(complexTypeNode, false, schemaDoc);
        final String complexTypeName = this.genAnonTypeName(complexTypeNode);
        this.contentBackup();
        final XSComplexTypeDecl type = this.traverseComplexTypeDecl(complexTypeNode, complexTypeName, attrValues, schemaDoc, grammar);
        this.contentRestore();
        grammar.addComplexTypeDecl(type, super.fSchemaHandler.element2Locator(complexTypeNode));
        type.setIsAnonymous();
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }
    
    XSComplexTypeDecl traverseGlobal(final Element complexTypeNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(complexTypeNode, true, schemaDoc);
        final String complexTypeName = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        this.contentBackup();
        final XSComplexTypeDecl type = this.traverseComplexTypeDecl(complexTypeNode, complexTypeName, attrValues, schemaDoc, grammar);
        this.contentRestore();
        if (complexTypeName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_COMPLEXTYPE, SchemaSymbols.ATT_NAME }, complexTypeNode);
        }
        else {
            grammar.addGlobalTypeDecl(type);
        }
        grammar.addComplexTypeDecl(type, super.fSchemaHandler.element2Locator(complexTypeNode));
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }
    
    private XSComplexTypeDecl traverseComplexTypeDecl(final Element complexTypeDecl, final String complexTypeName, final Object[] attrValues, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        this.fComplexTypeDecl = new XSComplexTypeDecl();
        this.fAttrGrp = new XSAttributeGroupDecl();
        final Boolean abstractAtt = (Boolean)attrValues[XSAttributeChecker.ATTIDX_ABSTRACT];
        final XInt blockAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_BLOCK];
        final Boolean mixedAtt = (Boolean)attrValues[XSAttributeChecker.ATTIDX_MIXED];
        final XInt finalAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_FINAL];
        this.fName = complexTypeName;
        this.fComplexTypeDecl.setName(this.fName);
        this.fTargetNamespace = schemaDoc.fTargetNamespace;
        this.fBlock = ((blockAtt == null) ? schemaDoc.fBlockDefault : blockAtt.shortValue());
        this.fFinal = ((finalAtt == null) ? schemaDoc.fFinalDefault : finalAtt.shortValue());
        if (abstractAtt != null && abstractAtt) {
            this.fIsAbstract = true;
        }
        Element child = null;
        try {
            child = DOMUtil.getFirstChildElement(complexTypeDecl);
            if (child != null) {
                if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                    child = DOMUtil.getNextSiblingElement(child);
                }
                if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, child);
                }
            }
            if (child == null) {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.processComplexContent(child, mixedAtt, false, schemaDoc, grammar);
            }
            else if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_SIMPLECONTENT)) {
                this.traverseSimpleContent(child, schemaDoc, grammar);
                final Element elemTmp = DOMUtil.getNextSiblingElement(child);
                if (elemTmp != null) {
                    final String siblingName = DOMUtil.getLocalName(elemTmp);
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, siblingName }, elemTmp);
                }
            }
            else if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_COMPLEXCONTENT)) {
                this.traverseComplexContent(child, mixedAtt, schemaDoc, grammar);
                final Element elemTmp = DOMUtil.getNextSiblingElement(child);
                if (elemTmp != null) {
                    final String siblingName = DOMUtil.getLocalName(elemTmp);
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, siblingName }, elemTmp);
                }
            }
            else {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.processComplexContent(child, mixedAtt, false, schemaDoc, grammar);
            }
        }
        catch (ComplexTypeRecoverableError e) {
            this.handleComplexTypeError(e.getMessage(), e.errorSubstText, e.errorElem);
        }
        this.fComplexTypeDecl.setValues(this.fName, this.fTargetNamespace, this.fBaseType, this.fDerivedBy, this.fFinal, this.fBlock, this.fContentType, this.fIsAbstract, this.fAttrGrp, this.fXSSimpleType, this.fParticle);
        return this.fComplexTypeDecl;
    }
    
    private void traverseSimpleContent(final Element simpleContentElement, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        Object[] attrValues = super.fAttrChecker.checkAttributes(simpleContentElement, false, schemaDoc);
        this.fContentType = 1;
        this.fParticle = null;
        Element simpleContent = DOMUtil.getFirstChildElement(simpleContentElement);
        if (simpleContent != null && DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(simpleContent, attrValues, false, schemaDoc);
            simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (simpleContent == null) {
            throw new ComplexTypeRecoverableError("src-ct.0.2", new Object[] { this.fName, SchemaSymbols.ELT_SIMPLECONTENT }, simpleContentElement);
        }
        final String simpleContentName = DOMUtil.getLocalName(simpleContent);
        if (simpleContentName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = 2;
        }
        else {
            if (!simpleContentName.equals(SchemaSymbols.ELT_EXTENSION)) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, simpleContentName }, simpleContent);
            }
            this.fDerivedBy = 1;
        }
        final Element elemTmp = DOMUtil.getNextSiblingElement(simpleContent);
        if (elemTmp != null) {
            final String siblingName = DOMUtil.getLocalName(elemTmp);
            throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, siblingName }, elemTmp);
        }
        attrValues = super.fAttrChecker.checkAttributes(simpleContent, false, schemaDoc);
        final QName baseTypeName = (QName)attrValues[XSAttributeChecker.ATTIDX_BASE];
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (baseTypeName == null) {
            throw new ComplexTypeRecoverableError("src-ct.0.3", new Object[] { this.fName }, simpleContent);
        }
        final XSTypeDecl type = (XSTypeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeName, simpleContent);
        if (type == null) {
            throw new ComplexTypeRecoverableError();
        }
        this.fBaseType = type;
        XSSimpleType baseValidator = null;
        XSComplexTypeDecl baseComplexType = null;
        int baseFinalSet = 0;
        if (type.getTypeCategory() == 13) {
            baseComplexType = (XSComplexTypeDecl)type;
            if (baseComplexType.getContentType() != 1) {
                throw new ComplexTypeRecoverableError("src-ct.2", new Object[] { this.fName }, simpleContent);
            }
            baseFinalSet = baseComplexType.getFinal();
            baseValidator = (XSSimpleType)baseComplexType.getSimpleType();
        }
        else {
            baseValidator = (XSSimpleType)type;
            if (this.fDerivedBy == 2) {
                throw new ComplexTypeRecoverableError("src-ct.2", new Object[] { this.fName }, simpleContent);
            }
            baseFinalSet = baseValidator.getFinal();
        }
        if ((baseFinalSet & this.fDerivedBy) != 0x0) {
            final String errorKey = (this.fDerivedBy == 1) ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1";
            throw new ComplexTypeRecoverableError(errorKey, new Object[] { this.fName }, simpleContent);
        }
        simpleContent = DOMUtil.getFirstChildElement(simpleContent);
        if (simpleContent != null) {
            if (DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(simpleContent, null, false, schemaDoc);
                simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
            }
            if (simpleContent != null && DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, simpleContent);
            }
        }
        if (this.fDerivedBy == 2) {
            if (simpleContent != null && DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                final XSSimpleType dv = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(simpleContent, schemaDoc, grammar);
                if (dv == null) {
                    throw new ComplexTypeRecoverableError();
                }
                if (!XSConstraints.checkSimpleDerivationOk(dv, baseValidator, baseValidator.getFinal())) {
                    throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.1.1", new Object[] { this.fName }, simpleContent);
                }
                baseValidator = dv;
                simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
            }
            Element attrNode = null;
            XSFacets facetData = null;
            short presentFacets = 0;
            short fixedFacets = 0;
            if (simpleContent != null) {
                final FacetInfo fi = this.traverseFacets(simpleContent, baseValidator, schemaDoc);
                attrNode = fi.nodeAfterFacets;
                facetData = fi.facetdata;
                presentFacets = fi.fPresentFacets;
                fixedFacets = fi.fFixedFacets;
            }
            this.fXSSimpleType = this.schemaFactory.createTypeRestriction(null, schemaDoc.fTargetNamespace, (short)0, baseValidator);
            try {
                super.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                this.fXSSimpleType.applyFacets(facetData, presentFacets, fixedFacets, super.fValidationState);
            }
            catch (InvalidDatatypeFacetException ex) {
                this.reportSchemaError(ex.getKey(), ex.getArgs(), simpleContent);
            }
            if (attrNode != null) {
                if (!this.isAttrOrAttrGroup(attrNode)) {
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(attrNode) }, attrNode);
                }
                final Element node = this.traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
                if (node != null) {
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(node) }, node);
                }
            }
            this.mergeAttributes(baseComplexType.getAttrGrp(), this.fAttrGrp, this.fName, false, simpleContentElement);
            this.fAttrGrp.removeProhibitedAttrs();
            final String errorCode = this.fAttrGrp.validRestrictionOf(baseComplexType.getAttrGrp());
            if (errorCode != null) {
                throw new ComplexTypeRecoverableError(errorCode, new Object[] { this.fName }, attrNode);
            }
        }
        else {
            this.fXSSimpleType = baseValidator;
            if (simpleContent != null) {
                final Element attrNode = simpleContent;
                if (!this.isAttrOrAttrGroup(attrNode)) {
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(attrNode) }, attrNode);
                }
                final Element node2 = this.traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
                if (node2 != null) {
                    throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(node2) }, node2);
                }
                this.fAttrGrp.removeProhibitedAttrs();
            }
            if (baseComplexType != null) {
                this.mergeAttributes(baseComplexType.getAttrGrp(), this.fAttrGrp, this.fName, true, simpleContentElement);
            }
        }
    }
    
    private void traverseComplexContent(final Element complexContentElement, final boolean mixedOnType, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        Object[] attrValues = super.fAttrChecker.checkAttributes(complexContentElement, false, schemaDoc);
        boolean mixedContent = mixedOnType;
        final Boolean mixedAtt = (Boolean)attrValues[XSAttributeChecker.ATTIDX_MIXED];
        if (mixedAtt != null) {
            mixedContent = mixedAtt;
        }
        this.fXSSimpleType = null;
        Element complexContent = DOMUtil.getFirstChildElement(complexContentElement);
        if (complexContent != null && DOMUtil.getLocalName(complexContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(complexContent, attrValues, false, schemaDoc);
            complexContent = DOMUtil.getNextSiblingElement(complexContent);
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (complexContent == null) {
            throw new ComplexTypeRecoverableError("src-ct.0.2", new Object[] { this.fName, SchemaSymbols.ELT_COMPLEXCONTENT }, complexContentElement);
        }
        final String complexContentName = DOMUtil.getLocalName(complexContent);
        if (complexContentName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = 2;
        }
        else {
            if (!complexContentName.equals(SchemaSymbols.ELT_EXTENSION)) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, complexContentName }, complexContent);
            }
            this.fDerivedBy = 1;
        }
        final Element elemTmp = DOMUtil.getNextSiblingElement(complexContent);
        if (elemTmp != null) {
            final String siblingName = DOMUtil.getLocalName(elemTmp);
            throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, siblingName }, elemTmp);
        }
        attrValues = super.fAttrChecker.checkAttributes(complexContent, false, schemaDoc);
        final QName baseTypeName = (QName)attrValues[XSAttributeChecker.ATTIDX_BASE];
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (baseTypeName == null) {
            throw new ComplexTypeRecoverableError("src-ct.0.3", new Object[] { this.fName }, complexContent);
        }
        final XSTypeDecl type = (XSTypeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeName, complexContent);
        if (type == null) {
            throw new ComplexTypeRecoverableError();
        }
        if (!(type instanceof XSComplexTypeDecl)) {
            throw new ComplexTypeRecoverableError("src-ct.1", new Object[] { this.fName }, complexContent);
        }
        final XSComplexTypeDecl baseType = (XSComplexTypeDecl)type;
        this.fBaseType = baseType;
        if ((baseType.getFinal() & this.fDerivedBy) != 0x0) {
            final String errorKey = (this.fDerivedBy == 1) ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1";
            throw new ComplexTypeRecoverableError(errorKey, new Object[] { this.fName }, complexContent);
        }
        complexContent = DOMUtil.getFirstChildElement(complexContent);
        if (complexContent != null) {
            if (DOMUtil.getLocalName(complexContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(complexContent, null, false, schemaDoc);
                complexContent = DOMUtil.getNextSiblingElement(complexContent);
            }
            if (complexContent != null && DOMUtil.getLocalName(complexContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, SchemaSymbols.ELT_ANNOTATION }, complexContent);
            }
        }
        this.processComplexContent(complexContent, mixedContent, true, schemaDoc, grammar);
        final XSParticleDecl baseContent = (XSParticleDecl)baseType.getParticle();
        if (this.fDerivedBy == 2) {
            if (this.fParticle != null && baseContent == null) {
                throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.3", new Object[] { this.fName }, complexContent);
            }
            this.mergeAttributes(baseType.getAttrGrp(), this.fAttrGrp, this.fName, false, complexContent);
            final String error = this.fAttrGrp.validRestrictionOf(baseType.getAttrGrp());
            if (error != null) {
                throw new ComplexTypeRecoverableError(error, new Object[] { this.fName }, complexContent);
            }
            this.fAttrGrp.removeProhibitedAttrs();
        }
        else {
            if (baseType.getContentType() != 0 && ((baseType.getContentType() == 2 && mixedContent) || (baseType.getContentType() == 3 && !mixedContent))) {
                throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.2.2.2.2.1", new Object[] { this.fName }, complexContent);
            }
            if (this.fParticle == null) {
                this.fParticle = baseContent;
            }
            else if (baseContent != null) {
                if ((this.fParticle.fType == 3 && ((XSModelGroupImpl)this.fParticle.fValue).fCompositor == 103) || (((XSParticleDecl)baseType.getParticle()).fType == 3 && ((XSModelGroupImpl)((XSParticleDecl)baseType.getParticle()).fValue).fCompositor == 103)) {
                    throw new ComplexTypeRecoverableError("cos-all-limited.1.2", null, complexContent);
                }
                final XSModelGroupImpl group = new XSModelGroupImpl();
                group.fCompositor = 102;
                group.fParticleCount = 2;
                (group.fParticles = new XSParticleDecl[2])[0] = (XSParticleDecl)baseType.getParticle();
                group.fParticles[1] = this.fParticle;
                final XSParticleDecl particle = new XSParticleDecl();
                particle.fType = 3;
                particle.fValue = group;
                this.fParticle = particle;
            }
            if (mixedContent) {
                this.fContentType = 3;
            }
            else if (this.fParticle == null) {
                this.fContentType = 0;
            }
            else {
                this.fContentType = 2;
            }
            this.fAttrGrp.removeProhibitedAttrs();
            this.mergeAttributes(baseType.getAttrGrp(), this.fAttrGrp, this.fName, true, complexContent);
        }
    }
    
    private void mergeAttributes(final XSAttributeGroupDecl fromAttrGrp, final XSAttributeGroupDecl toAttrGrp, final String typeName, final boolean extension, final Element elem) throws ComplexTypeRecoverableError {
        final XSObjectList attrUseS = fromAttrGrp.getAttributeUses();
        final XSAttributeUseImpl duplicateAttrUse = null;
        for (int attrCount = attrUseS.getLength(), i = 0; i < attrCount; ++i) {
            final XSAttributeUseImpl oneAttrUse = (XSAttributeUseImpl)attrUseS.getItem(i);
            final XSAttributeUseImpl existingAttrUse = toAttrGrp.getAttributeUse(oneAttrUse.fAttrDecl.getNamespace(), oneAttrUse.fAttrDecl.getName());
            if (existingAttrUse == null) {
                final String idName = toAttrGrp.addAttributeUse(oneAttrUse);
                if (idName != null) {
                    throw new ComplexTypeRecoverableError("ct-props-correct.5", new Object[] { typeName, idName, oneAttrUse.fAttrDecl.getName() }, elem);
                }
            }
            else if (extension) {
                throw new ComplexTypeRecoverableError("ct-props-correct.4", new Object[] { typeName, existingAttrUse.fAttrDecl.getName() }, elem);
            }
        }
        if (extension) {
            if (toAttrGrp.fAttributeWC == null) {
                toAttrGrp.fAttributeWC = fromAttrGrp.fAttributeWC;
            }
            else if (fromAttrGrp.fAttributeWC != null) {
                toAttrGrp.fAttributeWC = toAttrGrp.fAttributeWC.performUnionWith(fromAttrGrp.fAttributeWC, toAttrGrp.fAttributeWC.fProcessContents);
            }
        }
    }
    
    private void processComplexContent(final Element complexContentChild, final boolean isMixed, final boolean isDerivation, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        Element attrNode = null;
        XSParticleDecl particle = null;
        if (complexContentChild != null) {
            final String childName = DOMUtil.getLocalName(complexContentChild);
            if (childName.equals(SchemaSymbols.ELT_GROUP)) {
                particle = super.fSchemaHandler.fGroupTraverser.traverseLocal(complexContentChild, schemaDoc, grammar);
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            }
            else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = this.traverseSequence(complexContentChild, schemaDoc, grammar, 0, this.fComplexTypeDecl);
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            }
            else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = this.traverseChoice(complexContentChild, schemaDoc, grammar, 0, this.fComplexTypeDecl);
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            }
            else if (childName.equals(SchemaSymbols.ELT_ALL)) {
                particle = this.traverseAll(complexContentChild, schemaDoc, grammar, 8, this.fComplexTypeDecl);
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            }
            else {
                attrNode = complexContentChild;
            }
        }
        this.fParticle = particle;
        if (isMixed) {
            this.fContentType = 3;
        }
        else if (this.fParticle == null) {
            this.fContentType = 0;
        }
        else {
            this.fContentType = 2;
        }
        if (attrNode != null) {
            if (!this.isAttrOrAttrGroup(attrNode)) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(attrNode) }, attrNode);
            }
            final Element node = this.traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
            if (node != null) {
                throw new ComplexTypeRecoverableError("src-ct.0.1", new Object[] { this.fName, DOMUtil.getLocalName(node) }, node);
            }
            if (!isDerivation) {
                this.fAttrGrp.removeProhibitedAttrs();
            }
        }
    }
    
    private boolean isAttrOrAttrGroup(final Element e) {
        final String elementName = DOMUtil.getLocalName(e);
        return elementName.equals(SchemaSymbols.ELT_ATTRIBUTE) || elementName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP) || elementName.equals(SchemaSymbols.ELT_ANYATTRIBUTE);
    }
    
    private void traverseSimpleContentDecl(final Element simpleContentDecl) {
    }
    
    private void traverseComplexContentDecl(final Element complexContentDecl, final boolean mixedOnComplexTypeDecl) {
    }
    
    private String genAnonTypeName(final Element complexTypeDecl) {
        Element node = DOMUtil.getParent(complexTypeDecl);
        String typeName = "#AnonType_";
        while (node != null && node != DOMUtil.getRoot(DOMUtil.getDocument(node))) {
            typeName += node.getAttribute(SchemaSymbols.ATT_NAME);
            node = DOMUtil.getParent(node);
        }
        return typeName;
    }
    
    private void handleComplexTypeError(final String messageId, final Object[] args, final Element e) {
        if (messageId != null) {
            this.reportSchemaError(messageId, args, e);
        }
        this.fContentType = 3;
        this.fParticle = this.getErrorContent();
        this.fAttrGrp.fAttributeWC = this.getErrorWildcard();
    }
    
    private XSParticleDecl getErrorContent() {
        final XSParticleDecl particle = new XSParticleDecl();
        particle.fType = 2;
        particle.fValue = this.getErrorWildcard();
        particle.fMinOccurs = 0;
        particle.fMaxOccurs = -1;
        final XSModelGroupImpl group = new XSModelGroupImpl();
        group.fCompositor = 102;
        group.fParticleCount = 1;
        (group.fParticles = new XSParticleDecl[1])[0] = particle;
        final XSParticleDecl errorContent = new XSParticleDecl();
        errorContent.fType = 3;
        errorContent.fValue = group;
        return errorContent;
    }
    
    private XSWildcardDecl getErrorWildcard() {
        final XSWildcardDecl errorWildcard = new XSWildcardDecl();
        errorWildcard.fProcessContents = 2;
        return errorWildcard;
    }
    
    private void contentBackup() {
        if (this.fGlobalStore == null) {
            this.fGlobalStore = new Object[10];
            this.fGlobalStorePos = 0;
        }
        if (this.fGlobalStorePos == this.fGlobalStore.length) {
            final Object[] newArray = new Object[this.fGlobalStorePos + 10];
            System.arraycopy(this.fGlobalStore, 0, newArray, 0, this.fGlobalStorePos);
            this.fGlobalStore = newArray;
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
    }
    
    private void contentRestore() {
        final Object[] fGlobalStore = this.fGlobalStore;
        final int fGlobalStorePos = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos;
        this.fXSSimpleType = (XSSimpleType)fGlobalStore[fGlobalStorePos];
        final Object[] fGlobalStore2 = this.fGlobalStore;
        final int fGlobalStorePos2 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos2;
        this.fParticle = (XSParticleDecl)fGlobalStore2[fGlobalStorePos2];
        final Object[] fGlobalStore3 = this.fGlobalStore;
        final int fGlobalStorePos3 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos3;
        this.fAttrGrp = (XSAttributeGroupDecl)fGlobalStore3[fGlobalStorePos3];
        final Object[] fGlobalStore4 = this.fGlobalStore;
        final int fGlobalStorePos4 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos4;
        this.fBaseType = (XSTypeDecl)fGlobalStore4[fGlobalStorePos4];
        final Object[] fGlobalStore5 = this.fGlobalStore;
        final int fGlobalStorePos5 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos5;
        int i = (int)fGlobalStore5[fGlobalStorePos5];
        this.fBlock = (short)(i >> 16);
        this.fContentType = (short)i;
        final Object[] fGlobalStore6 = this.fGlobalStore;
        final int fGlobalStorePos6 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos6;
        i = (int)fGlobalStore6[fGlobalStorePos6];
        this.fDerivedBy = (short)(i >> 16);
        this.fFinal = (short)i;
        final Object[] fGlobalStore7 = this.fGlobalStore;
        final int fGlobalStorePos7 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos7;
        this.fTargetNamespace = (String)fGlobalStore7[fGlobalStorePos7];
        final Object[] fGlobalStore8 = this.fGlobalStore;
        final int fGlobalStorePos8 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos8;
        this.fName = (String)fGlobalStore8[fGlobalStorePos8];
        final Object[] fGlobalStore9 = this.fGlobalStore;
        final int fGlobalStorePos9 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos9;
        this.fIsAbstract = (boolean)fGlobalStore9[fGlobalStorePos9];
        final Object[] fGlobalStore10 = this.fGlobalStore;
        final int fGlobalStorePos10 = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = fGlobalStorePos10;
        this.fComplexTypeDecl = (XSComplexTypeDecl)fGlobalStore10[fGlobalStorePos10];
    }
    
    private class ComplexTypeRecoverableError extends Exception
    {
        Object[] errorSubstText;
        Element errorElem;
        
        ComplexTypeRecoverableError() {
            this.errorSubstText = null;
            this.errorElem = null;
        }
        
        ComplexTypeRecoverableError(final String msgKey, final Object[] args, final Element e) {
            super(msgKey);
            this.errorSubstText = null;
            this.errorElem = null;
            this.errorSubstText = args;
            this.errorElem = e;
        }
    }
}

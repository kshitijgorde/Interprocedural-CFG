// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.XSElementDecl;

class XSDElementTraverser extends XSDAbstractTraverser
{
    protected final XSElementDecl fTempElementDecl;
    protected final XSParticleDecl fTempParticleDecl;
    boolean fDeferTraversingLocalElements;
    
    XSDElementTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
        this.fTempElementDecl = new XSElementDecl();
        this.fTempParticleDecl = new XSParticleDecl();
    }
    
    XSParticleDecl traverseLocal(final Element elmDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final XSComplexTypeDecl enclosingCT) {
        XSParticleDecl particle = null;
        if (super.fSchemaHandler.fDeclPool != null) {
            particle = super.fSchemaHandler.fDeclPool.getParticleDecl();
        }
        else {
            particle = new XSParticleDecl();
        }
        if (this.fDeferTraversingLocalElements) {
            super.fSchemaHandler.fillInLocalElemInfo(elmDecl, schemaDoc, allContextFlags, enclosingCT, particle);
        }
        else {
            this.traverseLocal(particle, elmDecl, schemaDoc, grammar, allContextFlags, enclosingCT);
        }
        return particle;
    }
    
    protected void traverseLocal(final XSParticleDecl particle, final Element elmDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final XSComplexTypeDecl enclosingCT) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmDecl, false, schemaDoc);
        final QName refAtt = (QName)attrValues[XSAttributeChecker.ATTIDX_REF];
        final XInt minAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt maxAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSElementDecl element = null;
        if (elmDecl.getAttributeNode(SchemaSymbols.ATT_REF) != null) {
            if (refAtt != null) {
                element = (XSElementDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 3, refAtt, elmDecl);
                Element child = DOMUtil.getFirstChildElement(elmDecl);
                if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                    child = DOMUtil.getNextSiblingElement(child);
                }
                if (child != null) {
                    this.reportSchemaError("src-element.2.2", new Object[] { refAtt }, child);
                }
            }
            else {
                element = null;
            }
        }
        else {
            element = this.traverseNamedElement(elmDecl, attrValues, schemaDoc, grammar, false, enclosingCT);
        }
        particle.fMinOccurs = minAtt.intValue();
        particle.fMaxOccurs = maxAtt.intValue();
        if (element != null) {
            particle.fType = 1;
            particle.fValue = element;
        }
        final Long defaultVals = (Long)attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        this.checkOccurrences(particle, SchemaSymbols.ELT_ELEMENT, (Element)elmDecl.getParentNode(), allContextFlags, defaultVals);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }
    
    XSElementDecl traverseGlobal(final Element elmDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmDecl, true, schemaDoc);
        final XSElementDecl element = this.traverseNamedElement(elmDecl, attrValues, schemaDoc, grammar, true, null);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return element;
    }
    
    XSElementDecl traverseNamedElement(final Element elmDecl, final Object[] attrValues, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final boolean isGlobal, final XSComplexTypeDecl enclosingCT) {
        final Boolean abstractAtt = (Boolean)attrValues[XSAttributeChecker.ATTIDX_ABSTRACT];
        final XInt blockAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_BLOCK];
        final String defaultAtt = (String)attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        final XInt finalAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_FINAL];
        final String fixedAtt = (String)attrValues[XSAttributeChecker.ATTIDX_FIXED];
        final XInt formAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_FORM];
        String nameAtt = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        final Boolean nillableAtt = (Boolean)attrValues[XSAttributeChecker.ATTIDX_NILLABLE];
        final QName subGroupAtt = (QName)attrValues[XSAttributeChecker.ATTIDX_SUBSGROUP];
        final QName typeAtt = (QName)attrValues[XSAttributeChecker.ATTIDX_TYPE];
        XSElementDecl element = null;
        if (super.fSchemaHandler.fDeclPool != null) {
            element = super.fSchemaHandler.fDeclPool.getElementDecl();
        }
        else {
            element = new XSElementDecl();
        }
        if (nameAtt != null) {
            element.fName = super.fSymbolTable.addSymbol(nameAtt);
        }
        if (isGlobal) {
            element.fTargetNamespace = schemaDoc.fTargetNamespace;
            element.setIsGlobal();
        }
        else {
            if (enclosingCT != null) {
                element.setIsLocal(enclosingCT);
            }
            if (formAtt != null) {
                if (formAtt.intValue() == 1) {
                    element.fTargetNamespace = schemaDoc.fTargetNamespace;
                }
                else {
                    element.fTargetNamespace = null;
                }
            }
            else if (schemaDoc.fAreLocalElementsQualified) {
                element.fTargetNamespace = schemaDoc.fTargetNamespace;
            }
            else {
                element.fTargetNamespace = null;
            }
        }
        element.fBlock = ((blockAtt == null) ? schemaDoc.fBlockDefault : blockAtt.shortValue());
        element.fFinal = ((finalAtt == null) ? schemaDoc.fFinalDefault : finalAtt.shortValue());
        if (nillableAtt) {
            element.setIsNillable();
        }
        if (abstractAtt != null && abstractAtt) {
            element.setIsAbstract();
        }
        if (fixedAtt != null) {
            element.fDefault = new ValidatedInfo();
            element.fDefault.normalizedValue = fixedAtt;
            element.setConstraintType((short)2);
        }
        else if (defaultAtt != null) {
            element.fDefault = new ValidatedInfo();
            element.fDefault.normalizedValue = defaultAtt;
            element.setConstraintType((short)1);
        }
        else {
            element.setConstraintType((short)0);
        }
        if (subGroupAtt != null) {
            element.fSubGroup = (XSElementDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 3, subGroupAtt, elmDecl);
        }
        Element child = DOMUtil.getFirstChildElement(elmDecl);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        XSTypeDecl elementType = null;
        boolean haveAnonType = false;
        if (child != null) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                elementType = super.fSchemaHandler.fComplexTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                haveAnonType = true;
                child = DOMUtil.getNextSiblingElement(child);
            }
            else if (childName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                elementType = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                haveAnonType = true;
                child = DOMUtil.getNextSiblingElement(child);
            }
        }
        if (elementType == null && typeAtt != null) {
            elementType = (XSTypeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 7, typeAtt, elmDecl);
        }
        if (elementType == null && element.fSubGroup != null) {
            elementType = element.fSubGroup.fType;
        }
        if (elementType == null) {
            elementType = SchemaGrammar.fAnyType;
        }
        element.fType = elementType;
        if (child != null) {
            for (String childName = DOMUtil.getLocalName(child); child != null && (childName.equals(SchemaSymbols.ELT_KEY) || childName.equals(SchemaSymbols.ELT_KEYREF) || childName.equals(SchemaSymbols.ELT_UNIQUE)); childName = DOMUtil.getLocalName(child)) {
                if (childName.equals(SchemaSymbols.ELT_KEY) || childName.equals(SchemaSymbols.ELT_UNIQUE)) {
                    DOMUtil.setHidden(child);
                    super.fSchemaHandler.fUniqueOrKeyTraverser.traverse(child, element, schemaDoc, grammar);
                    if (DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME).length() != 0) {
                        super.fSchemaHandler.checkForDuplicateNames((schemaDoc.fTargetNamespace == null) ? ("," + DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME)) : (schemaDoc.fTargetNamespace + "," + DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME)), super.fSchemaHandler.getIDRegistry(), child, schemaDoc);
                    }
                }
                else if (childName.equals(SchemaSymbols.ELT_KEYREF)) {
                    super.fSchemaHandler.storeKeyRef(child, schemaDoc, element);
                }
                child = DOMUtil.getNextSiblingElement(child);
                if (child != null) {}
            }
        }
        if (isGlobal && nameAtt != null) {
            grammar.addGlobalElementDecl(element);
        }
        if (nameAtt == null) {
            if (isGlobal) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME }, elmDecl);
            }
            else {
                this.reportSchemaError("src-element.2.1", null, elmDecl);
            }
            nameAtt = "(no name)";
        }
        if (child != null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { nameAtt, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))" }, child);
        }
        if (defaultAtt != null && fixedAtt != null) {
            this.reportSchemaError("src-element.1", new Object[] { nameAtt }, elmDecl);
        }
        if (haveAnonType && typeAtt != null) {
            this.reportSchemaError("src-element.3", new Object[] { nameAtt }, elmDecl);
        }
        this.checkNotationType(nameAtt, elementType, elmDecl);
        if (element.fDefault != null) {
            super.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            XSConstraints.ElementDefaultValidImmediate(element.fType, element.fDefault.normalizedValue, super.fValidationState, element.fDefault);
            if (element.fDefault.actualValue == null) {
                this.reportSchemaError("e-props-correct.2", new Object[] { nameAtt, element.fDefault.normalizedValue }, elmDecl);
                element.setConstraintType((short)0);
            }
        }
        if (element.fSubGroup != null && !XSConstraints.checkTypeDerivationOk(element.fType, element.fSubGroup.fType, element.fSubGroup.fFinal)) {
            this.reportSchemaError("e-props-correct.3", new Object[] { nameAtt, subGroupAtt.prefix + ":" + subGroupAtt.localpart }, elmDecl);
        }
        if (element.fDefault != null && ((elementType.getTypeCategory() == 14 && ((XSSimpleType)elementType).isIDType()) || (elementType.getTypeCategory() == 13 && ((XSComplexTypeDecl)elementType).containsTypeID()))) {
            this.reportSchemaError("e-props-correct.4", new Object[] { element.fName }, elmDecl);
        }
        return element;
    }
    
    void reset(final SymbolTable symbolTable) {
        super.reset(symbolTable);
        this.fDeferTraversingLocalElements = true;
    }
}

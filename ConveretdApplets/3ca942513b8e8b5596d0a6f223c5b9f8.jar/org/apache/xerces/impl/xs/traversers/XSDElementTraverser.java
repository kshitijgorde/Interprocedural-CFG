// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Attr;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;
import org.apache.xerces.impl.xs.XSElementDecl;

class XSDElementTraverser extends XSDAbstractTraverser
{
    protected final XSElementDecl fTempElementDecl;
    boolean fDeferTraversingLocalElements;
    
    XSDElementTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
        this.fTempElementDecl = new XSElementDecl();
    }
    
    XSParticleDecl traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final XSObject xsObject) {
        XSParticleDecl particleDecl;
        if (super.fSchemaHandler.fDeclPool != null) {
            particleDecl = super.fSchemaHandler.fDeclPool.getParticleDecl();
        }
        else {
            particleDecl = new XSParticleDecl();
        }
        if (this.fDeferTraversingLocalElements) {
            particleDecl.fType = 1;
            final Attr attributeNode = element.getAttributeNode(SchemaSymbols.ATT_MINOCCURS);
            if (attributeNode != null) {
                final String value = attributeNode.getValue();
                try {
                    final int int1 = Integer.parseInt(value.trim());
                    if (int1 >= 0) {
                        particleDecl.fMinOccurs = int1;
                    }
                }
                catch (NumberFormatException ex) {}
            }
            super.fSchemaHandler.fillInLocalElemInfo(element, xsDocumentInfo, n, xsObject, particleDecl);
        }
        else {
            this.traverseLocal(particleDecl, element, xsDocumentInfo, schemaGrammar, n, xsObject, null);
            if (particleDecl.fType == 0) {
                particleDecl = null;
            }
        }
        return particleDecl;
    }
    
    protected void traverseLocal(final XSParticleDecl xsParticleDecl, final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final XSObject xsObject, final String[] effectiveContext) {
        if (effectiveContext != null) {
            xsDocumentInfo.fNamespaceSupport.setEffectiveContext(effectiveContext);
        }
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_REF];
        final XInt xInt = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt xInt2 = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSElementDecl traverseNamedElement;
        if (element.getAttributeNode(SchemaSymbols.ATT_REF) != null) {
            if (qName != null) {
                traverseNamedElement = (XSElementDecl)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 3, qName, element);
                Element element2 = DOMUtil.getFirstChildElement(element);
                if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo);
                    element2 = DOMUtil.getNextSiblingElement(element2);
                }
                if (element2 != null) {
                    this.reportSchemaError("src-element.2.2", new Object[] { qName.rawname, DOMUtil.getLocalName(element2) }, element2);
                }
            }
            else {
                traverseNamedElement = null;
            }
        }
        else {
            traverseNamedElement = this.traverseNamedElement(element, checkAttributes, xsDocumentInfo, schemaGrammar, false, xsObject);
        }
        xsParticleDecl.fMinOccurs = xInt.intValue();
        xsParticleDecl.fMaxOccurs = xInt2.intValue();
        if (traverseNamedElement != null) {
            xsParticleDecl.fType = 1;
            xsParticleDecl.fValue = traverseNamedElement;
        }
        else {
            xsParticleDecl.fType = 0;
        }
        this.checkOccurrences(xsParticleDecl, SchemaSymbols.ELT_ELEMENT, (Element)element.getParentNode(), n, (long)checkAttributes[XSAttributeChecker.ATTIDX_FROMDEFAULT]);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
    }
    
    XSElementDecl traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final XSElementDecl traverseNamedElement = this.traverseNamedElement(element, checkAttributes, xsDocumentInfo, schemaGrammar, true, null);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return traverseNamedElement;
    }
    
    XSElementDecl traverseNamedElement(final Element element, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final boolean b, final XSObject xsObject) {
        final Boolean b2 = (Boolean)array[XSAttributeChecker.ATTIDX_ABSTRACT];
        final XInt xInt = (XInt)array[XSAttributeChecker.ATTIDX_BLOCK];
        final String normalizedValue = (String)array[XSAttributeChecker.ATTIDX_DEFAULT];
        final XInt xInt2 = (XInt)array[XSAttributeChecker.ATTIDX_FINAL];
        final String normalizedValue2 = (String)array[XSAttributeChecker.ATTIDX_FIXED];
        final XInt xInt3 = (XInt)array[XSAttributeChecker.ATTIDX_FORM];
        String s = (String)array[XSAttributeChecker.ATTIDX_NAME];
        final Boolean b3 = (Boolean)array[XSAttributeChecker.ATTIDX_NILLABLE];
        final QName qName = (QName)array[XSAttributeChecker.ATTIDX_SUBSGROUP];
        final QName qName2 = (QName)array[XSAttributeChecker.ATTIDX_TYPE];
        XSElementDecl elementDecl;
        if (super.fSchemaHandler.fDeclPool != null) {
            elementDecl = super.fSchemaHandler.fDeclPool.getElementDecl();
        }
        else {
            elementDecl = new XSElementDecl();
        }
        if (s != null) {
            elementDecl.fName = super.fSymbolTable.addSymbol(s);
        }
        if (b) {
            elementDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
            elementDecl.setIsGlobal();
        }
        else {
            if (xsObject instanceof XSComplexTypeDecl) {
                elementDecl.setIsLocal((XSComplexTypeDecl)xsObject);
            }
            if (xInt3 != null) {
                if (xInt3.intValue() == 1) {
                    elementDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
                }
                else {
                    elementDecl.fTargetNamespace = null;
                }
            }
            else if (xsDocumentInfo.fAreLocalElementsQualified) {
                elementDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
            }
            else {
                elementDecl.fTargetNamespace = null;
            }
        }
        elementDecl.fBlock = ((xInt == null) ? xsDocumentInfo.fBlockDefault : xInt.shortValue());
        elementDecl.fFinal = ((xInt2 == null) ? xsDocumentInfo.fFinalDefault : xInt2.shortValue());
        final XSElementDecl xsElementDecl = elementDecl;
        xsElementDecl.fBlock &= 0x7;
        final XSElementDecl xsElementDecl2 = elementDecl;
        xsElementDecl2.fFinal &= 0x3;
        if (b3) {
            elementDecl.setIsNillable();
        }
        if (b2 != null && b2) {
            elementDecl.setIsAbstract();
        }
        if (normalizedValue2 != null) {
            elementDecl.fDefault = new ValidatedInfo();
            elementDecl.fDefault.normalizedValue = normalizedValue2;
            elementDecl.setConstraintType((short)2);
        }
        else if (normalizedValue != null) {
            elementDecl.fDefault = new ValidatedInfo();
            elementDecl.fDefault.normalizedValue = normalizedValue;
            elementDecl.setConstraintType((short)1);
        }
        else {
            elementDecl.setConstraintType((short)0);
        }
        if (qName != null) {
            elementDecl.fSubGroup = (XSElementDecl)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 3, qName, element);
        }
        Element hidden = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl fAnnotation = null;
        if (hidden != null && DOMUtil.getLocalName(hidden).equals(SchemaSymbols.ELT_ANNOTATION)) {
            fAnnotation = this.traverseAnnotationDecl(hidden, array, false, xsDocumentInfo);
            hidden = DOMUtil.getNextSiblingElement(hidden);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                fAnnotation = this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo);
            }
        }
        elementDecl.fAnnotation = fAnnotation;
        XSTypeDefinition fType = null;
        boolean b4 = false;
        if (hidden != null) {
            final String localName = DOMUtil.getLocalName(hidden);
            if (localName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                fType = super.fSchemaHandler.fComplexTypeTraverser.traverseLocal(hidden, xsDocumentInfo, schemaGrammar);
                b4 = true;
                hidden = DOMUtil.getNextSiblingElement(hidden);
            }
            else if (localName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                fType = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(hidden, xsDocumentInfo, schemaGrammar);
                b4 = true;
                hidden = DOMUtil.getNextSiblingElement(hidden);
            }
        }
        if (fType == null && qName2 != null) {
            fType = (XSTypeDefinition)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 7, qName2, element);
        }
        if (fType == null && elementDecl.fSubGroup != null) {
            fType = elementDecl.fSubGroup.fType;
        }
        if (fType == null) {
            fType = SchemaGrammar.fAnyType;
        }
        elementDecl.fType = fType;
        if (hidden != null) {
            for (String s2 = DOMUtil.getLocalName(hidden); hidden != null && (s2.equals(SchemaSymbols.ELT_KEY) || s2.equals(SchemaSymbols.ELT_KEYREF) || s2.equals(SchemaSymbols.ELT_UNIQUE)); s2 = DOMUtil.getLocalName(hidden)) {
                if (s2.equals(SchemaSymbols.ELT_KEY) || s2.equals(SchemaSymbols.ELT_UNIQUE)) {
                    DOMUtil.setHidden(hidden);
                    super.fSchemaHandler.fUniqueOrKeyTraverser.traverse(hidden, elementDecl, xsDocumentInfo, schemaGrammar);
                    if (DOMUtil.getAttrValue(hidden, SchemaSymbols.ATT_NAME).length() != 0) {
                        super.fSchemaHandler.checkForDuplicateNames((xsDocumentInfo.fTargetNamespace == null) ? ("," + DOMUtil.getAttrValue(hidden, SchemaSymbols.ATT_NAME)) : (xsDocumentInfo.fTargetNamespace + "," + DOMUtil.getAttrValue(hidden, SchemaSymbols.ATT_NAME)), super.fSchemaHandler.getIDRegistry(), super.fSchemaHandler.getIDRegistry_sub(), hidden, xsDocumentInfo);
                    }
                }
                else if (s2.equals(SchemaSymbols.ELT_KEYREF)) {
                    super.fSchemaHandler.storeKeyRef(hidden, xsDocumentInfo, elementDecl);
                }
                hidden = DOMUtil.getNextSiblingElement(hidden);
                if (hidden != null) {}
            }
        }
        if (b && s != null) {
            schemaGrammar.addGlobalElementDecl(elementDecl);
        }
        if (s == null) {
            if (b) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME }, element);
            }
            else {
                this.reportSchemaError("src-element.2.1", null, element);
            }
            s = "(no name)";
        }
        if (hidden != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { s, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))", DOMUtil.getLocalName(hidden) }, hidden);
        }
        if (normalizedValue != null && normalizedValue2 != null) {
            this.reportSchemaError("src-element.1", new Object[] { s }, element);
        }
        if (b4 && qName2 != null) {
            this.reportSchemaError("src-element.3", new Object[] { s }, element);
        }
        this.checkNotationType(s, fType, element);
        if (elementDecl.fDefault != null) {
            super.fValidationState.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
            if (XSConstraints.ElementDefaultValidImmediate(elementDecl.fType, elementDecl.fDefault.normalizedValue, super.fValidationState, elementDecl.fDefault) == null) {
                this.reportSchemaError("e-props-correct.2", new Object[] { s, elementDecl.fDefault.normalizedValue }, element);
                elementDecl.setConstraintType((short)0);
            }
        }
        if (elementDecl.fSubGroup != null && !XSConstraints.checkTypeDerivationOk(elementDecl.fType, elementDecl.fSubGroup.fType, elementDecl.fSubGroup.fFinal)) {
            this.reportSchemaError("e-props-correct.4", new Object[] { s, qName.prefix + ":" + qName.localpart }, element);
        }
        if (elementDecl.fDefault != null && ((fType.getTypeCategory() == 16 && ((XSSimpleType)fType).isIDType()) || (fType.getTypeCategory() == 15 && ((XSComplexTypeDecl)fType).containsTypeID()))) {
            this.reportSchemaError("e-props-correct.5", new Object[] { elementDecl.fName }, element);
        }
        if (elementDecl.fName == null) {
            return null;
        }
        return elementDecl;
    }
    
    void reset(final SymbolTable symbolTable, final boolean b) {
        super.reset(symbolTable, b);
        this.fDeferTraversingLocalElements = true;
    }
}

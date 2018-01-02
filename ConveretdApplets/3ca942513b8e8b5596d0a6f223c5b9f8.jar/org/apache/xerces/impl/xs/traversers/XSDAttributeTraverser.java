// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSAttributeDecl;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDAttributeTraverser extends XSDAbstractTraverser
{
    public XSDAttributeTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    protected XSAttributeUseImpl traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final XSComplexTypeDecl xsComplexTypeDecl) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        String normalizedValue = (String)checkAttributes[XSAttributeChecker.ATTIDX_DEFAULT];
        String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_FIXED];
        String localpart = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_REF];
        final XInt xInt = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_USE];
        XSAttributeDecl traverseNamedAttr;
        if (element.getAttributeNode(SchemaSymbols.ATT_REF) != null) {
            if (qName != null) {
                traverseNamedAttr = (XSAttributeDecl)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 1, qName, element);
                Element element2 = DOMUtil.getFirstChildElement(element);
                if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo);
                    element2 = DOMUtil.getNextSiblingElement(element2);
                }
                if (element2 != null) {
                    this.reportSchemaError("src-attribute.3.2", new Object[] { qName.rawname }, element2);
                }
                localpart = qName.localpart;
            }
            else {
                traverseNamedAttr = null;
            }
        }
        else {
            traverseNamedAttr = this.traverseNamedAttr(element, checkAttributes, xsDocumentInfo, schemaGrammar, false, xsComplexTypeDecl);
        }
        short fConstraintType = 0;
        if (normalizedValue != null) {
            fConstraintType = 1;
        }
        else if (s != null) {
            fConstraintType = 2;
            normalizedValue = s;
            s = null;
        }
        XSAttributeUseImpl attributeUse = null;
        if (traverseNamedAttr != null) {
            if (super.fSchemaHandler.fDeclPool != null) {
                attributeUse = super.fSchemaHandler.fDeclPool.getAttributeUse();
            }
            else {
                attributeUse = new XSAttributeUseImpl();
            }
            attributeUse.fAttrDecl = traverseNamedAttr;
            attributeUse.fUse = xInt.shortValue();
            attributeUse.fConstraintType = fConstraintType;
            if (normalizedValue != null) {
                attributeUse.fDefault = new ValidatedInfo();
                attributeUse.fDefault.normalizedValue = normalizedValue;
            }
        }
        if (normalizedValue != null && s != null) {
            this.reportSchemaError("src-attribute.1", new Object[] { localpart }, element);
        }
        if (fConstraintType == 1 && xInt != null && xInt.intValue() != 0) {
            this.reportSchemaError("src-attribute.2", new Object[] { localpart }, element);
        }
        if (normalizedValue != null && attributeUse != null) {
            super.fValidationState.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
            try {
                this.checkDefaultValid(attributeUse);
            }
            catch (InvalidDatatypeValueException ex) {
                this.reportSchemaError(ex.getKey(), ex.getArgs(), element);
                this.reportSchemaError("a-props-correct.2", new Object[] { localpart, normalizedValue }, element);
            }
            if (((XSSimpleType)traverseNamedAttr.getTypeDefinition()).isIDType()) {
                this.reportSchemaError("a-props-correct.3", new Object[] { localpart }, element);
            }
            if (attributeUse.fAttrDecl.getConstraintType() == 2 && attributeUse.fConstraintType != 0 && (attributeUse.fConstraintType != 2 || !attributeUse.fAttrDecl.getValInfo().actualValue.equals(attributeUse.fDefault.actualValue))) {
                this.reportSchemaError("au-props-correct.2", new Object[] { localpart, attributeUse.fAttrDecl.getValInfo().stringValue() }, element);
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return attributeUse;
    }
    
    protected XSAttributeDecl traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final XSAttributeDecl traverseNamedAttr = this.traverseNamedAttr(element, checkAttributes, xsDocumentInfo, schemaGrammar, true, null);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return traverseNamedAttr;
    }
    
    XSAttributeDecl traverseNamedAttr(final Element element, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final boolean b, final XSComplexTypeDecl xsComplexTypeDecl) {
        final String normalizedValue = (String)array[XSAttributeChecker.ATTIDX_DEFAULT];
        final String normalizedValue2 = (String)array[XSAttributeChecker.ATTIDX_FIXED];
        final XInt xInt = (XInt)array[XSAttributeChecker.ATTIDX_FORM];
        String addSymbol = (String)array[XSAttributeChecker.ATTIDX_NAME];
        final QName qName = (QName)array[XSAttributeChecker.ATTIDX_TYPE];
        XSAttributeDecl attributeDecl;
        if (super.fSchemaHandler.fDeclPool != null) {
            attributeDecl = super.fSchemaHandler.fDeclPool.getAttributeDecl();
        }
        else {
            attributeDecl = new XSAttributeDecl();
        }
        if (addSymbol != null) {
            addSymbol = super.fSymbolTable.addSymbol(addSymbol);
        }
        String s = null;
        XSComplexTypeDecl xsComplexTypeDecl2 = null;
        short n = 0;
        if (b) {
            s = xsDocumentInfo.fTargetNamespace;
            n = 1;
        }
        else {
            if (xsComplexTypeDecl != null) {
                xsComplexTypeDecl2 = xsComplexTypeDecl;
                n = 2;
            }
            if (xInt != null) {
                if (xInt.intValue() == 1) {
                    s = xsDocumentInfo.fTargetNamespace;
                }
            }
            else if (xsDocumentInfo.fAreLocalAttributesQualified) {
                s = xsDocumentInfo.fTargetNamespace;
            }
        }
        ValidatedInfo validatedInfo = null;
        short n2 = 0;
        if (b) {
            if (normalizedValue2 != null) {
                validatedInfo = new ValidatedInfo();
                validatedInfo.normalizedValue = normalizedValue2;
                n2 = 2;
            }
            else if (normalizedValue != null) {
                validatedInfo = new ValidatedInfo();
                validatedInfo.normalizedValue = normalizedValue;
                n2 = 1;
            }
        }
        Element element2 = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl xsAnnotationImpl = null;
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            xsAnnotationImpl = this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo);
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                xsAnnotationImpl = this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo);
            }
        }
        XSSimpleType xsSimpleType = null;
        boolean b2 = false;
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            xsSimpleType = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar);
            b2 = true;
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        if (xsSimpleType == null && qName != null) {
            final XSTypeDefinition xsTypeDefinition = (XSTypeDefinition)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 7, qName, element);
            if (xsTypeDefinition != null && xsTypeDefinition.getTypeCategory() == 16) {
                xsSimpleType = (XSSimpleType)xsTypeDefinition;
            }
            else {
                this.reportSchemaError("src-resolve", new Object[] { qName.rawname, "simpleType definition" }, element);
            }
        }
        if (xsSimpleType == null) {
            xsSimpleType = SchemaGrammar.fAnySimpleType;
        }
        attributeDecl.setValues(addSymbol, s, xsSimpleType, n2, n, validatedInfo, xsComplexTypeDecl2, xsAnnotationImpl);
        if (b && addSymbol != null) {
            schemaGrammar.addGlobalAttributeDecl(attributeDecl);
        }
        if (addSymbol == null) {
            if (b) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_ATTRIBUTE, SchemaSymbols.ATT_NAME }, element);
            }
            else {
                this.reportSchemaError("src-attribute.3.1", null, element);
            }
            addSymbol = "(no name)";
        }
        if (element2 != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { addSymbol, "(annotation?, (simpleType?))", DOMUtil.getLocalName(element2) }, element2);
        }
        if (normalizedValue != null && normalizedValue2 != null) {
            this.reportSchemaError("src-attribute.1", new Object[] { addSymbol }, element);
        }
        if (b2 && qName != null) {
            this.reportSchemaError("src-attribute.4", new Object[] { addSymbol }, element);
        }
        this.checkNotationType(addSymbol, xsSimpleType, element);
        if (validatedInfo != null) {
            super.fValidationState.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
            try {
                this.checkDefaultValid(attributeDecl);
            }
            catch (InvalidDatatypeValueException ex) {
                this.reportSchemaError(ex.getKey(), ex.getArgs(), element);
                this.reportSchemaError("a-props-correct.2", new Object[] { addSymbol, validatedInfo.normalizedValue }, element);
            }
        }
        if (validatedInfo != null && xsSimpleType.isIDType()) {
            this.reportSchemaError("a-props-correct.3", new Object[] { addSymbol }, element);
        }
        if (addSymbol != null && addSymbol.equals(XMLSymbols.PREFIX_XMLNS)) {
            this.reportSchemaError("no-xmlns", null, element);
        }
        if (s != null && s.equals(SchemaSymbols.URI_XSI)) {
            this.reportSchemaError("no-xsi", new Object[] { SchemaSymbols.URI_XSI }, element);
        }
        if (attributeDecl.getName() == null) {
            return null;
        }
        return attributeDecl;
    }
    
    void checkDefaultValid(final XSAttributeDecl xsAttributeDecl) throws InvalidDatatypeValueException {
        ((XSSimpleType)xsAttributeDecl.getTypeDefinition()).validate(xsAttributeDecl.getValInfo().normalizedValue, super.fValidationState, xsAttributeDecl.getValInfo());
        ((XSSimpleType)xsAttributeDecl.getTypeDefinition()).validate(xsAttributeDecl.getValInfo().stringValue(), super.fValidationState, xsAttributeDecl.getValInfo());
    }
    
    void checkDefaultValid(final XSAttributeUseImpl xsAttributeUseImpl) throws InvalidDatatypeValueException {
        ((XSSimpleType)xsAttributeUseImpl.fAttrDecl.getTypeDefinition()).validate(xsAttributeUseImpl.fDefault.normalizedValue, super.fValidationState, xsAttributeUseImpl.fDefault);
        ((XSSimpleType)xsAttributeUseImpl.fAttrDecl.getTypeDefinition()).validate(xsAttributeUseImpl.fDefault.stringValue(), super.fValidationState, xsAttributeUseImpl.fDefault);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.identity.Field;
import org.apache.xerces.impl.xpath.XPathException;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.xs.identity.Selector;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.w3c.dom.Element;
import org.apache.xerces.impl.xs.identity.IdentityConstraint;

class XSDAbstractIDConstraintTraverser extends XSDAbstractTraverser
{
    public XSDAbstractIDConstraintTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    void traverseIdentityConstraint(final IdentityConstraint ic, final Element icElem, final XSDocumentInfo schemaDoc, final Object[] icElemAttrs) {
        Element sElem = DOMUtil.getFirstChildElement(icElem);
        if (sElem == null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, icElem);
            return;
        }
        if (DOMUtil.getLocalName(sElem).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(sElem, icElemAttrs, false, schemaDoc);
            sElem = DOMUtil.getNextSiblingElement(sElem);
        }
        if (sElem == null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, icElem);
            return;
        }
        Object[] attrValues = super.fAttrChecker.checkAttributes(sElem, false, schemaDoc);
        if (!DOMUtil.getLocalName(sElem).equals(SchemaSymbols.ELT_SELECTOR)) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, sElem);
        }
        Element selChild = DOMUtil.getFirstChildElement(sElem);
        if (selChild != null) {
            if (DOMUtil.getLocalName(selChild).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(selChild, attrValues, false, schemaDoc);
                selChild = DOMUtil.getNextSiblingElement(selChild);
            }
            else {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, selChild);
            }
            if (selChild != null) {
                this.reportSchemaError("src-identity-constraint.1", new Object[] { icElemAttrs[XSAttributeChecker.ATTIDX_NAME] }, selChild);
            }
        }
        String sText = (String)attrValues[XSAttributeChecker.ATTIDX_XPATH];
        if (sText == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SELECTOR, SchemaSymbols.ATT_XPATH }, sElem);
            return;
        }
        sText = sText.trim();
        Selector.XPath sXpath = null;
        try {
            sXpath = new Selector.XPath(sText, super.fSymbolTable, schemaDoc.fNamespaceSupport);
            final Selector selector = new Selector(sXpath, ic);
            ic.setSelector(selector);
        }
        catch (XPathException e) {
            this.reportSchemaError(e.getKey(), new Object[] { sText }, sElem);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        Element fElem = DOMUtil.getNextSiblingElement(sElem);
        if (fElem == null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, sElem);
        }
        while (fElem != null) {
            attrValues = super.fAttrChecker.checkAttributes(fElem, false, schemaDoc);
            if (!DOMUtil.getLocalName(fElem).equals(SchemaSymbols.ELT_FIELD)) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, fElem);
            }
            Element fieldChild = DOMUtil.getFirstChildElement(fElem);
            if (fieldChild != null && DOMUtil.getLocalName(fieldChild).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(fieldChild, attrValues, false, schemaDoc);
                fieldChild = DOMUtil.getNextSiblingElement(fieldChild);
            }
            if (fieldChild != null) {
                this.reportSchemaError("src-identity-constraint.1", new Object[] { icElemAttrs[XSAttributeChecker.ATTIDX_NAME] }, fieldChild);
            }
            String fText = (String)attrValues[XSAttributeChecker.ATTIDX_XPATH];
            if (fText == null) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_FIELD, SchemaSymbols.ATT_XPATH }, fElem);
                return;
            }
            fText = fText.trim();
            try {
                final Field.XPath fXpath = new Field.XPath(fText, super.fSymbolTable, schemaDoc.fNamespaceSupport);
                final Field field = new Field(fXpath, ic);
                ic.addField(field);
            }
            catch (XPathException e2) {
                this.reportSchemaError(e2.getKey(), new Object[] { fText }, fElem);
                super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                return;
            }
            fElem = DOMUtil.getNextSiblingElement(fElem);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        }
    }
}

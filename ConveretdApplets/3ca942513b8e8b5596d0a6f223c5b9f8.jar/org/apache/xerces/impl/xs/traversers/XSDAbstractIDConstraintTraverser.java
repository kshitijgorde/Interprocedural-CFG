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
    public XSDAbstractIDConstraintTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    void traverseIdentityConstraint(final IdentityConstraint identityConstraint, final Element element, final XSDocumentInfo xsDocumentInfo, final Object[] array) {
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 == null) {
            this.reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, element);
            return;
        }
        if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            identityConstraint.addAnnotation(this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo));
            element2 = DOMUtil.getNextSiblingElement(element2);
            if (element2 == null) {
                this.reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, element);
                return;
            }
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                identityConstraint.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo));
            }
        }
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element2, false, xsDocumentInfo);
        if (!DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_SELECTOR)) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_SELECTOR }, element2);
        }
        Element element3 = DOMUtil.getFirstChildElement(element2);
        if (element3 != null) {
            if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                identityConstraint.addAnnotation(this.traverseAnnotationDecl(element3, checkAttributes, false, xsDocumentInfo));
                element3 = DOMUtil.getNextSiblingElement(element3);
            }
            else {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(element3) }, element3);
            }
            if (element3 != null) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(element3) }, element3);
            }
        }
        else {
            final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element2);
            if (syntheticAnnotation2 != null) {
                identityConstraint.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation2, checkAttributes, false, xsDocumentInfo));
            }
        }
        final String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_XPATH];
        if (s == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SELECTOR, SchemaSymbols.ATT_XPATH }, element2);
            return;
        }
        final String trim = s.trim();
        try {
            identityConstraint.setSelector(new Selector(new Selector.XPath(trim, super.fSymbolTable, xsDocumentInfo.fNamespaceSupport), identityConstraint));
        }
        catch (XPathException ex) {
            this.reportSchemaError(ex.getKey(), new Object[] { trim }, element2);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return;
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        Element element4 = DOMUtil.getNextSiblingElement(element2);
        if (element4 == null) {
            this.reportSchemaError("s4s-elt-must-match.2", new Object[] { "identity constraint", "(annotation?, selector, field+)" }, element2);
        }
        while (element4 != null) {
            final Object[] checkAttributes2 = super.fAttrChecker.checkAttributes(element4, false, xsDocumentInfo);
            if (!DOMUtil.getLocalName(element4).equals(SchemaSymbols.ELT_FIELD)) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_FIELD }, element4);
            }
            Element element5 = DOMUtil.getFirstChildElement(element4);
            if (element5 != null && DOMUtil.getLocalName(element5).equals(SchemaSymbols.ELT_ANNOTATION)) {
                identityConstraint.addAnnotation(this.traverseAnnotationDecl(element5, checkAttributes2, false, xsDocumentInfo));
                element5 = DOMUtil.getNextSiblingElement(element5);
            }
            if (element5 != null) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_FIELD, "(annotation?)", DOMUtil.getLocalName(element5) }, element5);
            }
            else {
                final String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(element4);
                if (syntheticAnnotation3 != null) {
                    identityConstraint.addAnnotation(this.traverseSyntheticAnnotation(element, syntheticAnnotation3, checkAttributes2, false, xsDocumentInfo));
                }
            }
            final String s2 = (String)checkAttributes2[XSAttributeChecker.ATTIDX_XPATH];
            if (s2 == null) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_FIELD, SchemaSymbols.ATT_XPATH }, element4);
                return;
            }
            final String trim2 = s2.trim();
            try {
                identityConstraint.addField(new Field(new Field.XPath(trim2, super.fSymbolTable, xsDocumentInfo.fNamespaceSupport), identityConstraint));
            }
            catch (XPathException ex2) {
                this.reportSchemaError(ex2.getKey(), new Object[] { trim2 }, element4);
                super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
                return;
            }
            element4 = DOMUtil.getNextSiblingElement(element4);
            super.fAttrChecker.returnAttrArray(checkAttributes2, xsDocumentInfo);
        }
    }
}

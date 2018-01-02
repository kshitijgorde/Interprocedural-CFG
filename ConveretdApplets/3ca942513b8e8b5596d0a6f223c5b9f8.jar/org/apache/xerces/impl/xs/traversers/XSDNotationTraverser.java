// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.XSNotationDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDNotationTraverser extends XSDAbstractTraverser
{
    XSDNotationTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    XSNotationDecl traverse(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final String fName = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        final String fPublicId = (String)checkAttributes[XSAttributeChecker.ATTIDX_PUBLIC];
        final String fSystemId = (String)checkAttributes[XSAttributeChecker.ATTIDX_SYSTEM];
        if (fName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_NOTATION, SchemaSymbols.ATT_NAME }, element);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return null;
        }
        if (fSystemId == null && fPublicId == null) {
            this.reportSchemaError("PublicSystemOnNotation", null, element);
        }
        final XSNotationDecl xsNotationDecl = new XSNotationDecl();
        xsNotationDecl.fName = fName;
        xsNotationDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
        xsNotationDecl.fPublicId = fPublicId;
        xsNotationDecl.fSystemId = fSystemId;
        Element element2 = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl fAnnotation = null;
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            fAnnotation = this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo);
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                fAnnotation = this.traverseSyntheticAnnotation(element, syntheticAnnotation, checkAttributes, false, xsDocumentInfo);
            }
        }
        xsNotationDecl.fAnnotation = fAnnotation;
        if (element2 != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_NOTATION, "(annotation?)", DOMUtil.getLocalName(element2) }, element2);
        }
        schemaGrammar.addGlobalNotationDecl(xsNotationDecl);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return xsNotationDecl;
    }
}

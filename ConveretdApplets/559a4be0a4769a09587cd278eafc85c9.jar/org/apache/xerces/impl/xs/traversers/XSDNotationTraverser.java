// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSNotationDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDNotationTraverser extends XSDAbstractTraverser
{
    XSDNotationTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    XSNotationDecl traverse(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        final String nameAttr = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        final String publicAttr = (String)attrValues[XSAttributeChecker.ATTIDX_PUBLIC];
        final String systemAttr = (String)attrValues[XSAttributeChecker.ATTIDX_SYSTEM];
        if (nameAttr == null) {
            this.reportSchemaError("src-notation", new Object[] { "<notation> must have a name" }, elmNode);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return null;
        }
        if (publicAttr == null) {
            this.reportSchemaError("src-notation", new Object[] { "<notation> must have 'public' attribute" }, elmNode);
        }
        final XSNotationDecl notation = new XSNotationDecl();
        notation.fName = nameAttr;
        notation.fTargetNamespace = schemaDoc.fTargetNamespace;
        notation.fPublicId = publicAttr;
        notation.fSystemId = systemAttr;
        Element content = DOMUtil.getFirstChildElement(elmNode);
        if (content != null && DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(content, attrValues, false, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        if (content != null) {
            final Object[] args = { DOMUtil.getLocalName(content) };
            this.reportSchemaError("src-notation", args, content);
        }
        grammar.addGlobalNotationDecl(notation);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return notation;
    }
}

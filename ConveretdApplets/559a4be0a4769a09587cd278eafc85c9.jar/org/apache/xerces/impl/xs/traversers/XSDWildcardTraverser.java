// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDWildcardTraverser extends XSDAbstractTraverser
{
    XSDWildcardTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    XSParticleDecl traverseAny(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        final XSWildcardDecl wildcard = this.traverseWildcardDecl(elmNode, attrValues, schemaDoc, grammar);
        XSParticleDecl particle = null;
        if (wildcard != null) {
            final int min = ((XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            final int max = ((XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (max != 0) {
                if (super.fSchemaHandler.fDeclPool != null) {
                    particle = super.fSchemaHandler.fDeclPool.getParticleDecl();
                }
                else {
                    particle = new XSParticleDecl();
                }
                particle.fType = 2;
                particle.fValue = wildcard;
                particle.fMinOccurs = min;
                particle.fMaxOccurs = max;
            }
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }
    
    XSWildcardDecl traverseAnyAttribute(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        final XSWildcardDecl wildcard = this.traverseWildcardDecl(elmNode, attrValues, schemaDoc, grammar);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return wildcard;
    }
    
    XSWildcardDecl traverseWildcardDecl(final Element elmNode, final Object[] attrValues, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final XSWildcardDecl wildcard = new XSWildcardDecl();
        final XInt namespaceTypeAttr = (XInt)attrValues[XSAttributeChecker.ATTIDX_NAMESPACE];
        wildcard.fType = namespaceTypeAttr.shortValue();
        wildcard.fNamespaceList = (String[])attrValues[XSAttributeChecker.ATTIDX_NAMESPACE_LIST];
        final XInt processContentsAttr = (XInt)attrValues[XSAttributeChecker.ATTIDX_PROCESSCONTENTS];
        wildcard.fProcessContents = processContentsAttr.shortValue();
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null) {
            if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            }
            if (child != null) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "wildcard", "(annotation?)" }, elmNode);
            }
        }
        return wildcard;
    }
}

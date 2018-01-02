// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAnnotationImpl;
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
    XSDWildcardTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    XSParticleDecl traverseAny(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final XSWildcardDecl traverseWildcardDecl = this.traverseWildcardDecl(element, checkAttributes, xsDocumentInfo, schemaGrammar);
        XSParticleDecl particleDecl = null;
        if (traverseWildcardDecl != null) {
            final int intValue = ((XInt)checkAttributes[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            final int intValue2 = ((XInt)checkAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (intValue2 != 0) {
                if (super.fSchemaHandler.fDeclPool != null) {
                    particleDecl = super.fSchemaHandler.fDeclPool.getParticleDecl();
                }
                else {
                    particleDecl = new XSParticleDecl();
                }
                particleDecl.fType = 2;
                particleDecl.fValue = traverseWildcardDecl;
                particleDecl.fMinOccurs = intValue;
                particleDecl.fMaxOccurs = intValue2;
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return particleDecl;
    }
    
    XSWildcardDecl traverseAnyAttribute(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final XSWildcardDecl traverseWildcardDecl = this.traverseWildcardDecl(element, checkAttributes, xsDocumentInfo, schemaGrammar);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return traverseWildcardDecl;
    }
    
    XSWildcardDecl traverseWildcardDecl(final Element element, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final XSWildcardDecl xsWildcardDecl = new XSWildcardDecl();
        xsWildcardDecl.fType = ((XInt)array[XSAttributeChecker.ATTIDX_NAMESPACE]).shortValue();
        xsWildcardDecl.fNamespaceList = (String[])array[XSAttributeChecker.ATTIDX_NAMESPACE_LIST];
        xsWildcardDecl.fProcessContents = ((XInt)array[XSAttributeChecker.ATTIDX_PROCESSCONTENTS]).shortValue();
        Element element2 = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl fAnnotation = null;
        if (element2 != null) {
            if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                fAnnotation = this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo);
                element2 = DOMUtil.getNextSiblingElement(element2);
            }
            else {
                final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation != null) {
                    fAnnotation = this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo);
                }
            }
            if (element2 != null) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "wildcard", "(annotation?)", DOMUtil.getLocalName(element2) }, element);
            }
        }
        else {
            final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation2 != null) {
                fAnnotation = this.traverseSyntheticAnnotation(element, syntheticAnnotation2, array, false, xsDocumentInfo);
            }
        }
        xsWildcardDecl.fAnnotation = fAnnotation;
        return xsWildcardDecl;
    }
}

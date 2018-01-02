// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDAttributeGroupTraverser extends XSDAbstractTraverser
{
    XSDAttributeGroupTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    XSAttributeGroupDecl traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_REF];
        if (qName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (local)", "ref" }, element);
            super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
            return null;
        }
        final XSAttributeGroupDecl xsAttributeGroupDecl = (XSAttributeGroupDecl)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 2, qName, element);
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 != null) {
            if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo);
                element2 = DOMUtil.getNextSiblingElement(element2);
            }
            if (element2 != null) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { qName.rawname, "(annotation?)", DOMUtil.getLocalName(element2) }, element2);
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return xsAttributeGroupDecl;
    }
    
    XSAttributeGroupDecl traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final XSAttributeGroupDecl xsAttributeGroupDecl = new XSAttributeGroupDecl();
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        String fName = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (fName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (global)", "name" }, element);
            fName = "no name";
        }
        xsAttributeGroupDecl.fName = fName;
        xsAttributeGroupDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
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
        final Element traverseAttrsAndAttrGrps = this.traverseAttrsAndAttrGrps(element2, xsAttributeGroupDecl, xsDocumentInfo, schemaGrammar, null);
        if (traverseAttrsAndAttrGrps != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { fName, "(annotation?, ((attribute | attributeGroup)*, anyAttribute?))", DOMUtil.getLocalName(traverseAttrsAndAttrGrps) }, traverseAttrsAndAttrGrps);
        }
        xsAttributeGroupDecl.removeProhibitedAttrs();
        final XSAttributeGroupDecl xsAttributeGroupDecl2 = (XSAttributeGroupDecl)super.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, fName, fName, xsDocumentInfo.fTargetNamespace), xsDocumentInfo, element);
        if (xsAttributeGroupDecl2 != null) {
            final Object[] validRestriction = xsAttributeGroupDecl.validRestrictionOf(fName, xsAttributeGroupDecl2);
            if (validRestriction != null) {
                this.reportSchemaError((String)validRestriction[validRestriction.length - 1], validRestriction, element2);
                this.reportSchemaError("src-redefine.7.2.2", new Object[] { fName, validRestriction[validRestriction.length - 1] }, element2);
            }
        }
        xsAttributeGroupDecl.fAnnotation = fAnnotation;
        schemaGrammar.addGlobalAttributeGroupDecl(xsAttributeGroupDecl);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return xsAttributeGroupDecl;
    }
}

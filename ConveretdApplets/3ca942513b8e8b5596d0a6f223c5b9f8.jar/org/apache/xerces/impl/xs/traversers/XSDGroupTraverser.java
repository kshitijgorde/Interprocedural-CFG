// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSGroupDecl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDGroupTraverser extends XSDAbstractParticleTraverser
{
    XSDGroupTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
    }
    
    XSParticleDecl traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes[XSAttributeChecker.ATTIDX_REF];
        final XInt xInt = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt xInt2 = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSGroupDecl xsGroupDecl = null;
        if (qName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "group (local)", "ref" }, element);
        }
        else {
            xsGroupDecl = (XSGroupDecl)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 4, qName, element);
        }
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(element2, checkAttributes, false, xsDocumentInfo);
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        if (element2 != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (local)", "(annotation?)", DOMUtil.getLocalName(element) }, element);
        }
        final int intValue = xInt.intValue();
        final int intValue2 = xInt2.intValue();
        XSParticleDecl particleDecl = null;
        if (xsGroupDecl != null && xsGroupDecl.fModelGroup != null && (intValue != 0 || intValue2 != 0)) {
            if (super.fSchemaHandler.fDeclPool != null) {
                particleDecl = super.fSchemaHandler.fDeclPool.getParticleDecl();
            }
            else {
                particleDecl = new XSParticleDecl();
            }
            particleDecl.fType = 3;
            particleDecl.fValue = xsGroupDecl.fModelGroup;
            particleDecl.fMinOccurs = intValue;
            particleDecl.fMaxOccurs = intValue2;
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return particleDecl;
    }
    
    XSGroupDecl traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final String fName = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        if (fName == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "group (global)", "name" }, element);
        }
        XSGroupDecl xsGroupDecl = null;
        XSParticleDecl xsParticleDecl = null;
        Element element2 = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl fAnnotation = null;
        if (element2 == null) {
            this.reportSchemaError("s4s-elt-must-match.2", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, element);
        }
        else {
            xsGroupDecl = new XSGroupDecl();
            String s = element2.getLocalName();
            if (s.equals(SchemaSymbols.ELT_ANNOTATION)) {
                fAnnotation = this.traverseAnnotationDecl(element2, checkAttributes, true, xsDocumentInfo);
                element2 = DOMUtil.getNextSiblingElement(element2);
                if (element2 != null) {
                    s = element2.getLocalName();
                }
            }
            else {
                final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
                if (syntheticAnnotation != null) {
                    fAnnotation = this.traverseSyntheticAnnotation(element, syntheticAnnotation, checkAttributes, false, xsDocumentInfo);
                }
            }
            if (element2 == null) {
                this.reportSchemaError("s4s-elt-must-match.2", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, element);
            }
            else if (s.equals(SchemaSymbols.ELT_ALL)) {
                xsParticleDecl = this.traverseAll(element2, xsDocumentInfo, schemaGrammar, 4, xsGroupDecl);
            }
            else if (s.equals(SchemaSymbols.ELT_CHOICE)) {
                xsParticleDecl = this.traverseChoice(element2, xsDocumentInfo, schemaGrammar, 4, xsGroupDecl);
            }
            else if (s.equals(SchemaSymbols.ELT_SEQUENCE)) {
                xsParticleDecl = this.traverseSequence(element2, xsDocumentInfo, schemaGrammar, 4, xsGroupDecl);
            }
            else {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(element2) }, element2);
            }
            if (element2 != null && DOMUtil.getNextSiblingElement(element2) != null) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(element2)) }, DOMUtil.getNextSiblingElement(element2));
            }
            if (fName != null) {
                xsGroupDecl.fName = fName;
                xsGroupDecl.fTargetNamespace = xsDocumentInfo.fTargetNamespace;
                if (xsParticleDecl != null) {
                    xsGroupDecl.fModelGroup = (XSModelGroupImpl)xsParticleDecl.fValue;
                }
                xsGroupDecl.fAnnotation = fAnnotation;
                schemaGrammar.addGlobalGroupDecl(xsGroupDecl);
            }
            else {
                xsGroupDecl = null;
            }
        }
        if (xsGroupDecl != null) {
            final Object grpOrAttrGrpRedefinedByRestriction = super.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(4, new QName(XMLSymbols.EMPTY_STRING, fName, fName, xsDocumentInfo.fTargetNamespace), xsDocumentInfo, element);
            if (grpOrAttrGrpRedefinedByRestriction != null) {
                schemaGrammar.addRedefinedGroupDecl(xsGroupDecl, (XSGroupDecl)grpOrAttrGrpRedefinedByRestriction, super.fSchemaHandler.element2Locator(element));
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return xsGroupDecl;
    }
}

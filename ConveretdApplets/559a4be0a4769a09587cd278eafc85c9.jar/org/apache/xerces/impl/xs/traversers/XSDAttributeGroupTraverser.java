// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

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
    XSDAttributeGroupTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    XSAttributeGroupDecl traverseLocal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        final QName refAttr = (QName)attrValues[XSAttributeChecker.ATTIDX_REF];
        XSAttributeGroupDecl attrGrp = null;
        if (refAttr == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (local)", "ref" }, elmNode);
            super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return null;
        }
        attrGrp = (XSAttributeGroupDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 2, refAttr, elmNode);
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            }
            if (child != null) {
                final Object[] args = { refAttr };
                this.reportSchemaError("src-attribute_group", args, child);
            }
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attrGrp;
    }
    
    XSAttributeGroupDecl traverseGlobal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final XSAttributeGroupDecl attrGrp = new XSAttributeGroupDecl();
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        String nameAttr = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (nameAttr == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "attributeGroup (global)", "name" }, elmNode);
            nameAttr = "no name";
        }
        attrGrp.fName = nameAttr;
        attrGrp.fTargetNamespace = schemaDoc.fTargetNamespace;
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            }
        }
        final Element nextNode = this.traverseAttrsAndAttrGrps(child, attrGrp, schemaDoc, grammar, null);
        if (nextNode != null) {
            final Object[] args = { nameAttr, DOMUtil.getLocalName(nextNode) };
            this.reportSchemaError("src-attribute_group", args, nextNode);
        }
        attrGrp.removeProhibitedAttrs();
        final XSAttributeGroupDecl redefinedAttrGrp = (XSAttributeGroupDecl)super.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, nameAttr, nameAttr, schemaDoc.fTargetNamespace), schemaDoc, elmNode);
        if (redefinedAttrGrp != null) {
            final String err = attrGrp.validRestrictionOf(redefinedAttrGrp);
            if (err != null) {
                this.reportSchemaError("src-redefine.7.2.2", new Object[] { nameAttr, err }, child);
            }
        }
        grammar.addGlobalAttributeGroupDecl(attrGrp);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attrGrp;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
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
    XSDGroupTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    XSParticleDecl traverseLocal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        final QName refAttr = (QName)attrValues[XSAttributeChecker.ATTIDX_REF];
        final XInt minAttr = (XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt maxAttr = (XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSGroupDecl group = null;
        if (refAttr == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "group (local)", "ref" }, elmNode);
        }
        else {
            group = (XSGroupDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 4, refAttr, elmNode);
        }
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (child != null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "group (local)", "(annotation?)" }, elmNode);
        }
        final int minOccurs = minAttr.intValue();
        final int maxOccurs = maxAttr.intValue();
        XSParticleDecl particle = null;
        if (group != null && group.fModelGroup != null && (minOccurs != 0 || maxOccurs != 0)) {
            if (super.fSchemaHandler.fDeclPool != null) {
                particle = super.fSchemaHandler.fDeclPool.getParticleDecl();
            }
            else {
                particle = new XSParticleDecl();
            }
            particle.fType = 3;
            particle.fValue = group.fModelGroup;
            particle.fMinOccurs = minOccurs;
            particle.fMaxOccurs = maxOccurs;
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }
    
    XSGroupDecl traverseGlobal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        final String strNameAttr = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (strNameAttr == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { "group (global)", "name" }, elmNode);
        }
        XSGroupDecl group = null;
        XSParticleDecl particle = null;
        Element l_elmChild = DOMUtil.getFirstChildElement(elmNode);
        if (l_elmChild == null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, elmNode);
        }
        else {
            String childName = l_elmChild.getLocalName();
            if (childName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.traverseAnnotationDecl(l_elmChild, attrValues, true, schemaDoc);
                l_elmChild = DOMUtil.getNextSiblingElement(l_elmChild);
                if (l_elmChild != null) {
                    childName = l_elmChild.getLocalName();
                }
            }
            if (l_elmChild == null) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, elmNode);
            }
            else if (childName.equals(SchemaSymbols.ELT_ALL)) {
                particle = this.traverseAll(l_elmChild, schemaDoc, grammar, 4, null);
            }
            else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = this.traverseChoice(l_elmChild, schemaDoc, grammar, 4, null);
            }
            else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = this.traverseSequence(l_elmChild, schemaDoc, grammar, 4, null);
            }
            else {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, l_elmChild);
            }
            if (l_elmChild != null && DOMUtil.getNextSiblingElement(l_elmChild) != null) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { "group (global)", "(annotation?, (all | choice | sequence))" }, DOMUtil.getNextSiblingElement(l_elmChild));
            }
            if (strNameAttr != null) {
                group = new XSGroupDecl();
                group.fName = strNameAttr;
                group.fTargetNamespace = schemaDoc.fTargetNamespace;
                if (particle != null) {
                    group.fModelGroup = (XSModelGroupImpl)particle.fValue;
                }
                grammar.addGlobalGroupDecl(group);
            }
        }
        if (group != null) {
            final Object redefinedGrp = super.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(4, new QName(XMLSymbols.EMPTY_STRING, strNameAttr, strNameAttr, schemaDoc.fTargetNamespace), schemaDoc, elmNode);
            if (redefinedGrp != null) {
                grammar.addRedefinedGroupDecl(group, (XSGroupDecl)redefinedGrp, super.fSchemaHandler.element2Locator(elmNode));
            }
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return group;
    }
}

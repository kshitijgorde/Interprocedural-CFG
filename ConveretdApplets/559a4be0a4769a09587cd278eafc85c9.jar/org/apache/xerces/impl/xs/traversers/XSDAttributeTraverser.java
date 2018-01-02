// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSAttributeDecl;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

class XSDAttributeTraverser extends XSDAbstractTraverser
{
    public XSDAttributeTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }
    
    protected XSAttributeUseImpl traverseLocal(final Element attrDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final XSComplexTypeDecl enclosingCT) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(attrDecl, false, schemaDoc);
        String defaultAtt = (String)attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        String fixedAtt = (String)attrValues[XSAttributeChecker.ATTIDX_FIXED];
        String nameAtt = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        final QName refAtt = (QName)attrValues[XSAttributeChecker.ATTIDX_REF];
        final XInt useAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_USE];
        XSAttributeDecl attribute = null;
        if (attrDecl.getAttributeNode(SchemaSymbols.ATT_REF) != null) {
            if (refAtt != null) {
                attribute = (XSAttributeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 1, refAtt, attrDecl);
                Element child = DOMUtil.getFirstChildElement(attrDecl);
                if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                    child = DOMUtil.getNextSiblingElement(child);
                }
                if (child != null) {
                    this.reportSchemaError("src-attribute.3.2", new Object[] { refAtt }, child);
                }
                nameAtt = refAtt.localpart;
            }
            else {
                attribute = null;
            }
        }
        else {
            attribute = this.traverseNamedAttr(attrDecl, attrValues, schemaDoc, grammar, false, enclosingCT);
        }
        short consType = 0;
        if (defaultAtt != null) {
            consType = 1;
        }
        else if (fixedAtt != null) {
            consType = 2;
            defaultAtt = fixedAtt;
            fixedAtt = null;
        }
        XSAttributeUseImpl attrUse = null;
        if (attribute != null) {
            if (super.fSchemaHandler.fDeclPool != null) {
                attrUse = super.fSchemaHandler.fDeclPool.getAttributeUse();
            }
            else {
                attrUse = new XSAttributeUseImpl();
            }
            attrUse.fAttrDecl = attribute;
            attrUse.fUse = useAtt.shortValue();
            attrUse.fConstraintType = consType;
            if (defaultAtt != null) {
                attrUse.fDefault = new ValidatedInfo();
                attrUse.fDefault.normalizedValue = defaultAtt;
            }
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (defaultAtt != null && fixedAtt != null) {
            this.reportSchemaError("src-attribute.1", new Object[] { nameAtt }, attrDecl);
        }
        if (consType == 1 && useAtt != null && useAtt.intValue() != 0) {
            this.reportSchemaError("src-attribute.2", new Object[] { nameAtt }, attrDecl);
        }
        if (defaultAtt != null && attrUse != null) {
            super.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            if (!this.checkDefaultValid(attrUse)) {
                this.reportSchemaError("a-props-correct.2", new Object[] { nameAtt, defaultAtt }, attrDecl);
            }
            if (((XSSimpleType)attribute.getTypeDefinition()).isIDType()) {
                this.reportSchemaError("a-props-correct.3", new Object[] { nameAtt }, attrDecl);
            }
            if (attrUse.fAttrDecl.getConstraintType() == 2 && attrUse.fConstraintType != 0 && (attrUse.fConstraintType != 2 || !attrUse.fAttrDecl.getValInfo().actualValue.equals(attrUse.fDefault.actualValue))) {
                this.reportSchemaError("au-props-correct.2", new Object[] { nameAtt }, attrDecl);
            }
        }
        return attrUse;
    }
    
    protected XSAttributeDecl traverseGlobal(final Element attrDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(attrDecl, true, schemaDoc);
        final XSAttributeDecl attribute = this.traverseNamedAttr(attrDecl, attrValues, schemaDoc, grammar, true, null);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attribute;
    }
    
    XSAttributeDecl traverseNamedAttr(final Element attrDecl, final Object[] attrValues, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final boolean isGlobal, final XSComplexTypeDecl enclosingCT) {
        final String defaultAtt = (String)attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        final String fixedAtt = (String)attrValues[XSAttributeChecker.ATTIDX_FIXED];
        final XInt formAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_FORM];
        String nameAtt = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        final QName typeAtt = (QName)attrValues[XSAttributeChecker.ATTIDX_TYPE];
        XSAttributeDecl attribute = null;
        if (super.fSchemaHandler.fDeclPool != null) {
            attribute = super.fSchemaHandler.fDeclPool.getAttributeDecl();
        }
        else {
            attribute = new XSAttributeDecl();
        }
        if (nameAtt != null) {
            nameAtt = super.fSymbolTable.addSymbol(nameAtt);
        }
        String tnsAtt = null;
        XSComplexTypeDecl enclCT = null;
        short scope = 0;
        if (isGlobal) {
            tnsAtt = schemaDoc.fTargetNamespace;
            scope = 1;
        }
        else {
            enclCT = enclosingCT;
            if (formAtt != null) {
                if (formAtt.intValue() == 1) {
                    tnsAtt = schemaDoc.fTargetNamespace;
                }
            }
            else if (schemaDoc.fAreLocalAttributesQualified) {
                tnsAtt = schemaDoc.fTargetNamespace;
            }
        }
        ValidatedInfo attDefault = null;
        short constraintType = 0;
        if (isGlobal) {
            if (fixedAtt != null) {
                attDefault = new ValidatedInfo();
                attDefault.normalizedValue = fixedAtt;
                constraintType = 2;
            }
            else if (defaultAtt != null) {
                attDefault = new ValidatedInfo();
                attDefault.normalizedValue = defaultAtt;
                constraintType = 1;
            }
        }
        Element child = DOMUtil.getFirstChildElement(attrDecl);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        XSSimpleType attrType = null;
        boolean haveAnonType = false;
        if (child != null) {
            final String childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                attrType = super.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                haveAnonType = true;
                child = DOMUtil.getNextSiblingElement(child);
            }
        }
        if (attrType == null && typeAtt != null) {
            final XSTypeDecl type = (XSTypeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 7, typeAtt, attrDecl);
            if (type != null && type.getTypeCategory() == 14) {
                attrType = (XSSimpleType)type;
            }
            else {
                this.reportSchemaError("src-resolve", new Object[] { typeAtt.rawname, "simpleType definition" }, attrDecl);
            }
        }
        if (attrType == null) {
            attrType = SchemaGrammar.fAnySimpleType;
        }
        attribute.setValues(nameAtt, tnsAtt, attrType, constraintType, scope, attDefault, enclCT);
        if (isGlobal && nameAtt != null) {
            grammar.addGlobalAttributeDecl(attribute);
        }
        if (nameAtt == null) {
            if (isGlobal) {
                this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_ATTRIBUTE, SchemaSymbols.ATT_NAME }, attrDecl);
            }
            else {
                this.reportSchemaError("src-attribute.3.1", null, attrDecl);
            }
            nameAtt = "(no name)";
        }
        if (child != null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { nameAtt, "(annotation?, (simpleType?))" }, child);
        }
        if (defaultAtt != null && fixedAtt != null) {
            this.reportSchemaError("src-attribute.1", new Object[] { nameAtt }, attrDecl);
        }
        if (haveAnonType && typeAtt != null) {
            this.reportSchemaError("src-attribute.4", new Object[] { nameAtt }, attrDecl);
        }
        this.checkNotationType(nameAtt, attrType, attrDecl);
        if (attDefault != null) {
            super.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            if (!this.checkDefaultValid(attribute)) {
                this.reportSchemaError("a-props-correct.2", new Object[] { nameAtt, defaultAtt }, attrDecl);
            }
        }
        if (attDefault != null && attrType.isIDType()) {
            this.reportSchemaError("a-props-correct.3", new Object[] { nameAtt }, attrDecl);
        }
        if (nameAtt != null && nameAtt.equals(XMLSymbols.PREFIX_XMLNS)) {
            this.reportSchemaError("no-xmlns", null, attrDecl);
        }
        if (tnsAtt != null && tnsAtt.equals(SchemaSymbols.URI_XSI)) {
            this.reportSchemaError("no-xsi", new Object[] { SchemaSymbols.URI_XSI }, attrDecl);
        }
        return attribute;
    }
    
    boolean checkDefaultValid(final XSAttributeDecl attribute) {
        boolean ret = true;
        try {
            ((XSSimpleType)attribute.getTypeDefinition()).validate(attribute.getValInfo().normalizedValue, super.fValidationState, attribute.getValInfo());
        }
        catch (InvalidDatatypeValueException ide) {
            ret = false;
        }
        return ret;
    }
    
    boolean checkDefaultValid(final XSAttributeUseImpl attrUse) {
        boolean ret = true;
        try {
            ((XSSimpleType)attrUse.fAttrDecl.getTypeDefinition()).validate(attrUse.fDefault.normalizedValue, super.fValidationState, attrUse.fDefault);
        }
        catch (InvalidDatatypeValueException ide) {
            ret = false;
        }
        return ret;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.psvi.XSObjectList;
import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.NamespaceContext;
import java.util.Vector;
import org.apache.xerces.xni.QName;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.XSTypeDecl;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;
import org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl;
import org.apache.xerces.impl.dv.SchemaDVFactory;

class XSDSimpleTypeTraverser extends XSDAbstractTraverser
{
    private final SchemaDVFactory schemaFactory;
    private boolean fIsBuiltIn;
    
    XSDSimpleTypeTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
        this.schemaFactory = SchemaDVFactory.getInstance();
        this.fIsBuiltIn = false;
        if (this.schemaFactory instanceof SchemaDVFactoryImpl) {
            ((SchemaDVFactoryImpl)this.schemaFactory).setDeclPool(handler.fDeclPool);
        }
    }
    
    XSSimpleType traverseGlobal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        final String nameAtt = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        XSSimpleType type = this.traverseSimpleTypeDecl(elmNode, attrValues, schemaDoc, grammar);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (nameAtt == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, SchemaSymbols.ATT_NAME }, elmNode);
            type = null;
        }
        if (type != null) {
            grammar.addGlobalTypeDecl(type);
        }
        return type;
    }
    
    XSSimpleType traverseLocal(final Element elmNode, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        final XSSimpleType type = this.traverseSimpleTypeDecl(elmNode, attrValues, schemaDoc, grammar);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }
    
    private XSSimpleType traverseSimpleTypeDecl(final Element simpleTypeDecl, final Object[] attrValues, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar) {
        final String name = (String)attrValues[XSAttributeChecker.ATTIDX_NAME];
        final XInt finalAttr = (XInt)attrValues[XSAttributeChecker.ATTIDX_FINAL];
        final int finalProperty = (finalAttr == null) ? schemaDoc.fFinalDefault : finalAttr.intValue();
        Element child = DOMUtil.getFirstChildElement(simpleTypeDecl);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (child == null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))" }, simpleTypeDecl);
            return this.errorType(name, schemaDoc.fTargetNamespace, (short)2);
        }
        final String varietyProperty = DOMUtil.getLocalName(child);
        short refType = 2;
        boolean restriction = false;
        boolean list = false;
        boolean union = false;
        if (varietyProperty.equals(SchemaSymbols.ELT_RESTRICTION)) {
            refType = 2;
            restriction = true;
        }
        else if (varietyProperty.equals(SchemaSymbols.ELT_LIST)) {
            refType = 16;
            list = true;
        }
        else {
            if (!varietyProperty.equals(SchemaSymbols.ELT_UNION)) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))" }, simpleTypeDecl);
                return this.errorType(name, schemaDoc.fTargetNamespace, (short)2);
            }
            refType = 8;
            union = true;
        }
        final Element nextChild = DOMUtil.getNextSiblingElement(child);
        if (nextChild != null) {
            this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))" }, nextChild);
        }
        final Object[] contentAttrs = super.fAttrChecker.checkAttributes(child, false, schemaDoc);
        final QName baseTypeName = (QName)contentAttrs[restriction ? XSAttributeChecker.ATTIDX_BASE : XSAttributeChecker.ATTIDX_ITEMTYPE];
        final Vector memberTypes = (Vector)contentAttrs[XSAttributeChecker.ATTIDX_MEMBERTYPES];
        Element content = DOMUtil.getFirstChildElement(child);
        if (content != null && DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(content, contentAttrs, false, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        XSSimpleType baseValidator = null;
        if ((restriction || list) && baseTypeName != null) {
            baseValidator = this.findDTValidator(child, name, baseTypeName, refType, schemaDoc);
            if (baseValidator == null && this.fIsBuiltIn) {
                this.fIsBuiltIn = false;
                return null;
            }
        }
        Vector dTValidators = null;
        XSSimpleType dv = null;
        if (union && memberTypes != null && memberTypes.size() > 0) {
            final int size = memberTypes.size();
            dTValidators = new Vector(size, 2);
            for (int i = 0; i < size; ++i) {
                dv = this.findDTValidator(child, name, memberTypes.elementAt(i), (short)8, schemaDoc);
                if (dv != null) {
                    if (dv.getVariety() == 3) {
                        final XSObjectList dvs = dv.getMemberTypes();
                        for (int j = 0; j < dvs.getLength(); ++j) {
                            dTValidators.addElement(dvs.getItem(j));
                        }
                    }
                    else {
                        dTValidators.addElement(dv);
                    }
                }
            }
        }
        boolean skipFacets = false;
        if (content != null && DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            if (restriction || list) {
                if (baseTypeName != null) {
                    this.reportSchemaError(list ? "src-simple-type.3" : "src-simple-type.2", null, content);
                }
                else {
                    baseValidator = this.traverseLocal(content, schemaDoc, grammar);
                }
                content = DOMUtil.getNextSiblingElement(content);
            }
            else if (union) {
                if (dTValidators == null) {
                    dTValidators = new Vector(2, 2);
                }
                do {
                    dv = this.traverseLocal(content, schemaDoc, grammar);
                    if (dv != null) {
                        if (dv.getVariety() == 3) {
                            final XSObjectList dvs = dv.getMemberTypes();
                            for (int k = 0; k < dvs.getLength(); ++k) {
                                dTValidators.addElement(dvs.getItem(k));
                            }
                        }
                        else {
                            dTValidators.addElement(dv);
                        }
                    }
                    content = DOMUtil.getNextSiblingElement(content);
                    if (content != null) {
                        continue;
                    }
                    break;
                } while (DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_SIMPLETYPE));
            }
        }
        else if ((restriction || list) && baseTypeName == null) {
            this.reportSchemaError("src-simple-type.2", null, child);
            skipFacets = true;
            baseValidator = SchemaGrammar.fAnySimpleType;
        }
        else if (union && (memberTypes == null || memberTypes.size() == 0)) {
            this.reportSchemaError("src-union-memberTypes-or-simpleTypes", null, child);
            dTValidators = new Vector(1);
            dTValidators.addElement(SchemaGrammar.fAnySimpleType);
        }
        if ((restriction || list) && baseValidator == null) {
            baseValidator = SchemaGrammar.fAnySimpleType;
        }
        if (union && (dTValidators == null || dTValidators.size() == 0)) {
            dTValidators = new Vector(1);
            dTValidators.addElement(SchemaGrammar.fAnySimpleType);
        }
        if (list && this.isListDatatype(baseValidator)) {
            this.reportSchemaError("cos-list-of-atomic", new Object[] { name }, child);
        }
        XSSimpleType newDecl = null;
        if (restriction) {
            newDecl = this.schemaFactory.createTypeRestriction(name, schemaDoc.fTargetNamespace, (short)finalProperty, baseValidator);
        }
        else if (list) {
            newDecl = this.schemaFactory.createTypeList(name, schemaDoc.fTargetNamespace, (short)finalProperty, baseValidator);
        }
        else if (union) {
            final XSSimpleType[] memberDecls = new XSSimpleType[dTValidators.size()];
            for (int l = 0; l < dTValidators.size(); ++l) {
                memberDecls[l] = dTValidators.elementAt(l);
            }
            newDecl = this.schemaFactory.createTypeUnion(name, schemaDoc.fTargetNamespace, (short)finalProperty, memberDecls);
        }
        if (restriction && content != null) {
            final FacetInfo fi = this.traverseFacets(content, baseValidator, schemaDoc);
            content = fi.nodeAfterFacets;
            if (!skipFacets) {
                try {
                    super.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                    newDecl.applyFacets(fi.facetdata, fi.fPresentFacets, fi.fFixedFacets, super.fValidationState);
                }
                catch (InvalidDatatypeFacetException ex) {
                    this.reportSchemaError(ex.getKey(), ex.getArgs(), child);
                }
            }
        }
        if (content != null) {
            if (restriction) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))" }, content);
            }
            else if (list) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))" }, content);
            }
            else if (union) {
                this.reportSchemaError("s4s-elt-must-match", new Object[] { SchemaSymbols.ELT_LIST, "(annotation?, (simpleType*))" }, content);
            }
        }
        super.fAttrChecker.returnAttrArray(contentAttrs, schemaDoc);
        return newDecl;
    }
    
    private XSSimpleType findDTValidator(final Element elm, final String refName, final QName baseTypeStr, final short baseRefContext, final XSDocumentInfo schemaDoc) {
        if (baseTypeStr == null) {
            return null;
        }
        final XSTypeDecl baseType = (XSTypeDecl)super.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeStr, elm);
        if (baseType != null) {
            if (baseType.getTypeCategory() != 14 || (baseType == SchemaGrammar.fAnySimpleType && baseRefContext == 2)) {
                if (baseType == SchemaGrammar.fAnySimpleType && this.checkBuiltIn(refName, schemaDoc.fTargetNamespace)) {
                    return null;
                }
                this.reportSchemaError("st-props-correct.4.1", new Object[] { baseTypeStr.rawname }, elm);
                return SchemaGrammar.fAnySimpleType;
            }
            else if ((baseType.getFinal() & baseRefContext) != 0x0) {
                if (baseRefContext == 2) {
                    this.reportSchemaError("st-props-correct.3", new Object[] { baseTypeStr.rawname }, elm);
                }
                else if (baseRefContext == 16) {
                    this.reportSchemaError("st-props-correct.4.2.1", new Object[] { baseTypeStr.rawname }, elm);
                }
                else if (baseRefContext == 8) {
                    this.reportSchemaError("st-props-correct.4.2.2", new Object[] { baseTypeStr.rawname }, elm);
                }
            }
        }
        return (XSSimpleType)baseType;
    }
    
    private final boolean checkBuiltIn(final String name, final String namespace) {
        if (namespace != SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            return false;
        }
        if (SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(name) != null) {
            this.fIsBuiltIn = true;
        }
        return this.fIsBuiltIn;
    }
    
    private boolean isListDatatype(final XSSimpleType validator) {
        if (validator.getVariety() == 2) {
            return true;
        }
        if (validator.getVariety() == 3) {
            final XSObjectList temp = validator.getMemberTypes();
            for (int i = 0; i < temp.getLength(); ++i) {
                if (((XSSimpleType)temp.getItem(i)).getVariety() == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private XSSimpleType errorType(final String name, final String namespace, final short refType) {
        switch (refType) {
            case 2: {
                return this.schemaFactory.createTypeRestriction(name, namespace, (short)0, SchemaGrammar.fAnySimpleType);
            }
            case 16: {
                return this.schemaFactory.createTypeList(name, namespace, (short)0, SchemaGrammar.fAnySimpleType);
            }
            case 8: {
                return this.schemaFactory.createTypeUnion(name, namespace, (short)0, new XSSimpleType[] { SchemaGrammar.fAnySimpleType });
            }
            default: {
                return null;
            }
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import java.util.Vector;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.util.XInt;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import org.apache.xerces.xs.XSSimpleTypeDefinition;
import org.apache.xerces.xs.XSTypeDefinition;
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
    
    XSDSimpleTypeTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
        this.schemaFactory = SchemaDVFactory.getInstance();
        this.fIsBuiltIn = false;
        if (this.schemaFactory instanceof SchemaDVFactoryImpl) {
            ((SchemaDVFactoryImpl)this.schemaFactory).setDeclPool(xsdHandler.fDeclPool);
        }
    }
    
    XSSimpleType traverseGlobal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, true, xsDocumentInfo);
        final String s = (String)checkAttributes[XSAttributeChecker.ATTIDX_NAME];
        XSSimpleType traverseSimpleTypeDecl = this.traverseSimpleTypeDecl(element, checkAttributes, xsDocumentInfo, schemaGrammar);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        if (s == null) {
            this.reportSchemaError("s4s-att-must-appear", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, SchemaSymbols.ATT_NAME }, element);
            traverseSimpleTypeDecl = null;
        }
        if (traverseSimpleTypeDecl != null) {
            schemaGrammar.addGlobalTypeDecl(traverseSimpleTypeDecl);
        }
        return traverseSimpleTypeDecl;
    }
    
    XSSimpleType traverseLocal(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
        final XSSimpleType simpleType = this.getSimpleType(this.genAnonTypeName(element), element, checkAttributes, xsDocumentInfo, schemaGrammar);
        if (simpleType instanceof XSSimpleTypeDefinition) {
            ((XSSimpleTypeDecl)simpleType).setAnonymous(true);
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return simpleType;
    }
    
    private XSSimpleType traverseSimpleTypeDecl(final Element element, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        return this.getSimpleType((String)array[XSAttributeChecker.ATTIDX_NAME], element, array, xsDocumentInfo, schemaGrammar);
    }
    
    private String genAnonTypeName(final Element element) {
        final StringBuffer sb = new StringBuffer("#AnonType_");
        for (Element element2 = DOMUtil.getParent(element); element2 != null && element2 != DOMUtil.getRoot(DOMUtil.getDocument(element2)); element2 = DOMUtil.getParent(element2)) {
            sb.append(element2.getAttribute(SchemaSymbols.ATT_NAME));
        }
        return sb.toString();
    }
    
    private XSSimpleType getSimpleType(final String s, final Element element, final Object[] array, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar) {
        final XInt xInt = (XInt)array[XSAttributeChecker.ATTIDX_FINAL];
        final int n = (xInt == null) ? xsDocumentInfo.fFinalDefault : xInt.intValue();
        Element element2 = DOMUtil.getFirstChildElement(element);
        XSAnnotationImpl[] array2 = null;
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            final XSAnnotationImpl traverseAnnotationDecl = this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo);
            if (traverseAnnotationDecl != null) {
                array2 = new XSAnnotationImpl[] { traverseAnnotationDecl };
            }
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        else {
            final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(element);
            if (syntheticAnnotation != null) {
                array2 = new XSAnnotationImpl[] { this.traverseSyntheticAnnotation(element, syntheticAnnotation, array, false, xsDocumentInfo) };
            }
        }
        if (element2 == null) {
            this.reportSchemaError("s4s-elt-must-match.2", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))" }, element);
            return this.errorType(s, xsDocumentInfo.fTargetNamespace, (short)2);
        }
        final String localName = DOMUtil.getLocalName(element2);
        boolean b = false;
        boolean b2 = false;
        boolean b3 = false;
        short n2;
        if (localName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            n2 = 2;
            b = true;
        }
        else if (localName.equals(SchemaSymbols.ELT_LIST)) {
            n2 = 16;
            b2 = true;
        }
        else {
            if (!localName.equals(SchemaSymbols.ELT_UNION)) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", localName }, element);
                return this.errorType(s, xsDocumentInfo.fTargetNamespace, (short)2);
            }
            n2 = 8;
            b3 = true;
        }
        final Element nextSiblingElement = DOMUtil.getNextSiblingElement(element2);
        if (nextSiblingElement != null) {
            this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", DOMUtil.getLocalName(nextSiblingElement) }, nextSiblingElement);
        }
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element2, false, xsDocumentInfo);
        final QName qName = (QName)checkAttributes[b ? XSAttributeChecker.ATTIDX_BASE : XSAttributeChecker.ATTIDX_ITEMTYPE];
        final Vector vector = (Vector)checkAttributes[XSAttributeChecker.ATTIDX_MEMBERTYPES];
        Element element3 = DOMUtil.getFirstChildElement(element2);
        if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
            final XSAnnotationImpl traverseAnnotationDecl2 = this.traverseAnnotationDecl(element3, checkAttributes, false, xsDocumentInfo);
            if (traverseAnnotationDecl2 != null) {
                if (array2 == null) {
                    array2 = new XSAnnotationImpl[] { traverseAnnotationDecl2 };
                }
                else {
                    array2 = new XSAnnotationImpl[] { array2[0], traverseAnnotationDecl2 };
                }
            }
            element3 = DOMUtil.getNextSiblingElement(element3);
        }
        else {
            final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(element2);
            if (syntheticAnnotation2 != null) {
                final XSAnnotationImpl traverseSyntheticAnnotation = this.traverseSyntheticAnnotation(element2, syntheticAnnotation2, checkAttributes, false, xsDocumentInfo);
                if (array2 == null) {
                    array2 = new XSAnnotationImpl[] { traverseSyntheticAnnotation };
                }
                else {
                    array2 = new XSAnnotationImpl[] { array2[0], traverseSyntheticAnnotation };
                }
            }
        }
        XSSimpleType xsSimpleType = null;
        if ((b || b2) && qName != null) {
            xsSimpleType = this.findDTValidator(element2, s, qName, n2, xsDocumentInfo);
            if (xsSimpleType == null && this.fIsBuiltIn) {
                this.fIsBuiltIn = false;
                return null;
            }
        }
        Vector<XSSimpleType> vector2 = null;
        if (b3 && vector != null && vector.size() > 0) {
            final int size = vector.size();
            vector2 = new Vector<XSSimpleType>(size, 2);
            for (int i = 0; i < size; ++i) {
                final XSSimpleType dtValidator = this.findDTValidator(element2, s, vector.elementAt(i), (short)8, xsDocumentInfo);
                if (dtValidator != null) {
                    if (dtValidator.getVariety() == 3) {
                        final XSObjectList memberTypes = dtValidator.getMemberTypes();
                        for (int j = 0; j < memberTypes.getLength(); ++j) {
                            vector2.addElement((XSSimpleType)memberTypes.item(j));
                        }
                    }
                    else {
                        vector2.addElement(dtValidator);
                    }
                }
            }
        }
        boolean b4 = false;
        if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            if (b || b2) {
                if (qName != null) {
                    this.reportSchemaError(b2 ? "src-simple-type.3.a" : "src-simple-type.2.a", null, element3);
                }
                else {
                    xsSimpleType = this.traverseLocal(element3, xsDocumentInfo, schemaGrammar);
                }
                element3 = DOMUtil.getNextSiblingElement(element3);
            }
            else if (b3) {
                if (vector2 == null) {
                    vector2 = new Vector<XSSimpleType>(2, 2);
                }
                do {
                    final XSSimpleType traverseLocal = this.traverseLocal(element3, xsDocumentInfo, schemaGrammar);
                    if (traverseLocal != null) {
                        if (traverseLocal.getVariety() == 3) {
                            final XSObjectList memberTypes2 = traverseLocal.getMemberTypes();
                            for (int k = 0; k < memberTypes2.getLength(); ++k) {
                                vector2.addElement((XSSimpleType)memberTypes2.item(k));
                            }
                        }
                        else {
                            vector2.addElement(traverseLocal);
                        }
                    }
                    element3 = DOMUtil.getNextSiblingElement(element3);
                    if (element3 != null) {
                        continue;
                    }
                    break;
                } while (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_SIMPLETYPE));
            }
        }
        else if ((b || b2) && qName == null) {
            this.reportSchemaError(b2 ? "src-simple-type.3.b" : "src-simple-type.2.b", null, element2);
            b4 = true;
            xsSimpleType = SchemaGrammar.fAnySimpleType;
        }
        else if (b3 && (vector == null || vector.size() == 0)) {
            this.reportSchemaError("src-union-memberTypes-or-simpleTypes", null, element2);
            vector2 = new Vector<XSSimpleType>(1);
            vector2.addElement(SchemaGrammar.fAnySimpleType);
        }
        if ((b || b2) && xsSimpleType == null) {
            xsSimpleType = SchemaGrammar.fAnySimpleType;
        }
        if (b3 && (vector2 == null || vector2.size() == 0)) {
            vector2 = new Vector<XSSimpleType>(1);
            vector2.addElement(SchemaGrammar.fAnySimpleType);
        }
        if (b2 && this.isListDatatype(xsSimpleType)) {
            this.reportSchemaError("cos-st-restricts.2.1", new Object[] { s, xsSimpleType.getName() }, element2);
        }
        XSSimpleType xsSimpleType2 = null;
        if (b) {
            xsSimpleType2 = this.schemaFactory.createTypeRestriction(s, xsDocumentInfo.fTargetNamespace, (short)n, xsSimpleType, (array2 == null) ? null : new XSObjectListImpl(array2, array2.length));
        }
        else if (b2) {
            xsSimpleType2 = this.schemaFactory.createTypeList(s, xsDocumentInfo.fTargetNamespace, (short)n, xsSimpleType, (array2 == null) ? null : new XSObjectListImpl(array2, array2.length));
        }
        else if (b3) {
            final XSSimpleType[] array3 = new XSSimpleType[vector2.size()];
            for (int l = 0; l < vector2.size(); ++l) {
                array3[l] = vector2.elementAt(l);
            }
            xsSimpleType2 = this.schemaFactory.createTypeUnion(s, xsDocumentInfo.fTargetNamespace, (short)n, array3, (array2 == null) ? null : new XSObjectListImpl(array2, array2.length));
        }
        if (b && element3 != null) {
            final FacetInfo traverseFacets = this.traverseFacets(element3, xsSimpleType, xsDocumentInfo);
            element3 = traverseFacets.nodeAfterFacets;
            if (!b4) {
                try {
                    super.fValidationState.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
                    xsSimpleType2.applyFacets(traverseFacets.facetdata, traverseFacets.fPresentFacets, traverseFacets.fFixedFacets, super.fValidationState);
                }
                catch (InvalidDatatypeFacetException ex) {
                    this.reportSchemaError(ex.getKey(), ex.getArgs(), element2);
                }
            }
        }
        if (element3 != null) {
            if (b) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(element3) }, element3);
            }
            else if (b2) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(element3) }, element3);
            }
            else if (b3) {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(element3) }, element3);
            }
        }
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return xsSimpleType2;
    }
    
    private XSSimpleType findDTValidator(final Element element, final String s, final QName qName, final short n, final XSDocumentInfo xsDocumentInfo) {
        if (qName == null) {
            return null;
        }
        final XSTypeDefinition xsTypeDefinition = (XSTypeDefinition)super.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 7, qName, element);
        if (xsTypeDefinition != null) {
            if (xsTypeDefinition.getTypeCategory() != 16 || (xsTypeDefinition == SchemaGrammar.fAnySimpleType && n == 2)) {
                if (xsTypeDefinition == SchemaGrammar.fAnySimpleType && this.checkBuiltIn(s, xsDocumentInfo.fTargetNamespace)) {
                    return null;
                }
                this.reportSchemaError("cos-st-restricts.1.1", new Object[] { qName.rawname, s }, element);
                return SchemaGrammar.fAnySimpleType;
            }
            else if ((xsTypeDefinition.getFinal() & n) != 0x0) {
                if (n == 2) {
                    this.reportSchemaError("st-props-correct.3", new Object[] { s, qName.rawname }, element);
                }
                else if (n == 16) {
                    this.reportSchemaError("cos-st-restricts.2.3.1.1", new Object[] { qName.rawname, s }, element);
                }
                else if (n == 8) {
                    this.reportSchemaError("cos-st-restricts.3.3.1.1", new Object[] { qName.rawname, s }, element);
                }
            }
        }
        return (XSSimpleType)xsTypeDefinition;
    }
    
    private final boolean checkBuiltIn(final String s, final String s2) {
        if (s2 != SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            return false;
        }
        if (SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(s) != null) {
            this.fIsBuiltIn = true;
        }
        return this.fIsBuiltIn;
    }
    
    private boolean isListDatatype(final XSSimpleType xsSimpleType) {
        if (xsSimpleType.getVariety() == 2) {
            return true;
        }
        if (xsSimpleType.getVariety() == 3) {
            final XSObjectList memberTypes = xsSimpleType.getMemberTypes();
            for (int i = 0; i < memberTypes.getLength(); ++i) {
                if (((XSSimpleType)memberTypes.item(i)).getVariety() == 2) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private XSSimpleType errorType(final String s, final String s2, final short n) {
        switch (n) {
            case 2: {
                return this.schemaFactory.createTypeRestriction(s, s2, (short)0, SchemaGrammar.fAnySimpleType, null);
            }
            case 16: {
                return this.schemaFactory.createTypeList(s, s2, (short)0, SchemaGrammar.fAnySimpleType, null);
            }
            case 8: {
                return this.schemaFactory.createTypeUnion(s, s2, (short)0, new XSSimpleType[] { SchemaGrammar.fAnySimpleType }, null);
            }
            default: {
                return null;
            }
        }
    }
}

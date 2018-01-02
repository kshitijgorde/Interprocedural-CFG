// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSElementDecl;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.xs.XSTypeDefinition;
import org.apache.xerces.impl.xs.XSWildcardDecl;
import org.apache.xerces.xs.XSAttributeUse;
import org.apache.xerces.impl.xs.XSAttributeUseImpl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import org.apache.xerces.xs.XSObjectList;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.util.XSObjectListImpl;
import org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import org.apache.xerces.impl.dv.ValidatedInfo;
import org.apache.xerces.impl.dv.ValidationContext;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.NamespaceContext;
import org.apache.xerces.util.NamespaceSupport;
import org.apache.xerces.impl.xs.SchemaGrammar;
import java.util.Vector;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.w3c.dom.Element;
import org.apache.xerces.impl.dv.XSFacets;
import org.apache.xerces.impl.dv.XSSimpleType;
import org.apache.xerces.impl.validation.ValidationState;
import org.apache.xerces.util.SymbolTable;

abstract class XSDAbstractTraverser
{
    protected static final String NO_NAME = "(no name)";
    protected static final int NOT_ALL_CONTEXT = 0;
    protected static final int PROCESSING_ALL_EL = 1;
    protected static final int GROUP_REF_WITH_ALL = 2;
    protected static final int CHILD_OF_GROUP = 4;
    protected static final int PROCESSING_ALL_GP = 8;
    protected XSDHandler fSchemaHandler;
    protected SymbolTable fSymbolTable;
    protected XSAttributeChecker fAttrChecker;
    protected boolean fValidateAnnotations;
    ValidationState fValidationState;
    private static final XSSimpleType fQNameDV;
    private StringBuffer fPattern;
    private final XSFacets xsFacets;
    
    XSDAbstractTraverser(final XSDHandler fSchemaHandler, final XSAttributeChecker fAttrChecker) {
        this.fSchemaHandler = null;
        this.fSymbolTable = null;
        this.fAttrChecker = null;
        this.fValidateAnnotations = false;
        this.fValidationState = new ValidationState();
        this.fPattern = new StringBuffer();
        this.xsFacets = new XSFacets();
        this.fSchemaHandler = fSchemaHandler;
        this.fAttrChecker = fAttrChecker;
    }
    
    void reset(final SymbolTable symbolTable, final boolean fValidateAnnotations) {
        this.fSymbolTable = symbolTable;
        this.fValidateAnnotations = fValidateAnnotations;
        this.fValidationState.setExtraChecking(false);
        this.fValidationState.setSymbolTable(symbolTable);
    }
    
    XSAnnotationImpl traverseAnnotationDecl(final Element element, final Object[] array, final boolean b, final XSDocumentInfo xsDocumentInfo) {
        this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(element, b, xsDocumentInfo), xsDocumentInfo);
        final String annotation = DOMUtil.getAnnotation(element);
        Element element2 = DOMUtil.getFirstChildElement(element);
        if (element2 != null) {
            do {
                final String localName = DOMUtil.getLocalName(element2);
                if (!localName.equals(SchemaSymbols.ELT_APPINFO) && !localName.equals(SchemaSymbols.ELT_DOCUMENTATION)) {
                    this.reportSchemaError("src-annotation", new Object[] { localName }, element2);
                }
                this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(element2, true, xsDocumentInfo), xsDocumentInfo);
                element2 = DOMUtil.getNextSiblingElement(element2);
            } while (element2 != null);
        }
        if (annotation == null) {
            return null;
        }
        final SchemaGrammar grammar = this.fSchemaHandler.getGrammar(xsDocumentInfo.fTargetNamespace);
        final Vector vector = (Vector)array[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (vector == null || vector.isEmpty()) {
            if (this.fValidateAnnotations) {
                xsDocumentInfo.addAnnotation(new XSAnnotationInfo(annotation, element));
            }
            return new XSAnnotationImpl(annotation, grammar);
        }
        final StringBuffer sb = new StringBuffer(64);
        sb.append(" ");
        int i = 0;
        while (i < vector.size()) {
            final String s = vector.elementAt(i++);
            final int index = s.indexOf(58);
            String substring;
            String substring2;
            if (index == -1) {
                substring = "";
                substring2 = s;
            }
            else {
                substring = s.substring(0, index);
                substring2 = s.substring(index + 1);
            }
            if (!element.getAttributeNS(xsDocumentInfo.fNamespaceSupport.getURI(substring.intern()), substring2).equals("")) {
                ++i;
            }
            else {
                sb.append(s).append("=\"");
                sb.append(processAttValue(vector.elementAt(i++))).append("\" ");
            }
        }
        final StringBuffer sb2 = new StringBuffer(annotation.length() + sb.length());
        final int index2 = annotation.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (index2 == -1) {
            return null;
        }
        final int n = index2 + SchemaSymbols.ELT_ANNOTATION.length();
        sb2.append(annotation.substring(0, n));
        sb2.append(sb.toString());
        sb2.append(annotation.substring(n, annotation.length()));
        final String string = sb2.toString();
        if (this.fValidateAnnotations) {
            xsDocumentInfo.addAnnotation(new XSAnnotationInfo(string, element));
        }
        return new XSAnnotationImpl(string, grammar);
    }
    
    XSAnnotationImpl traverseSyntheticAnnotation(final Element element, final String s, final Object[] array, final boolean b, final XSDocumentInfo xsDocumentInfo) {
        final SchemaGrammar grammar = this.fSchemaHandler.getGrammar(xsDocumentInfo.fTargetNamespace);
        final Vector vector = (Vector)array[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (vector == null || vector.isEmpty()) {
            if (this.fValidateAnnotations) {
                xsDocumentInfo.addAnnotation(new XSAnnotationInfo(s, element));
            }
            return new XSAnnotationImpl(s, grammar);
        }
        final StringBuffer sb = new StringBuffer(64);
        sb.append(" ");
        int i = 0;
        while (i < vector.size()) {
            final String s2 = vector.elementAt(i++);
            final int index = s2.indexOf(58);
            String substring;
            if (index == -1) {
                substring = "";
            }
            else {
                substring = s2.substring(0, index);
                s2.substring(index + 1);
            }
            xsDocumentInfo.fNamespaceSupport.getURI(substring.intern());
            sb.append(s2).append("=\"");
            sb.append(processAttValue(vector.elementAt(i++))).append("\" ");
        }
        final StringBuffer sb2 = new StringBuffer(s.length() + sb.length());
        final int index2 = s.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (index2 == -1) {
            return null;
        }
        final int n = index2 + SchemaSymbols.ELT_ANNOTATION.length();
        sb2.append(s.substring(0, n));
        sb2.append(sb.toString());
        sb2.append(s.substring(n, s.length()));
        final String string = sb2.toString();
        if (this.fValidateAnnotations) {
            xsDocumentInfo.addAnnotation(new XSAnnotationInfo(string, element));
        }
        return new XSAnnotationImpl(string, grammar);
    }
    
    FacetInfo traverseFacets(Element nextSiblingElement, final XSSimpleType xsSimpleType, final XSDocumentInfo xsDocumentInfo) {
        short fPresentFacets = 0;
        short fFixedFacets = 0;
        final boolean containsQName = this.containsQName(xsSimpleType);
        Vector<String> enumeration = null;
        XSObjectListImpl enumAnnotations = null;
        XSObjectListImpl patternAnnotations = null;
        final Vector enumNSDecls = containsQName ? new Vector<NamespaceSupport>() : null;
        this.xsFacets.reset();
        while (nextSiblingElement != null) {
            final String localName = DOMUtil.getLocalName(nextSiblingElement);
            Object[] array;
            if (localName.equals(SchemaSymbols.ELT_ENUMERATION)) {
                array = this.fAttrChecker.checkAttributes(nextSiblingElement, false, xsDocumentInfo, containsQName);
                final String s = (String)array[XSAttributeChecker.ATTIDX_VALUE];
                final NamespaceSupport namespaceSupport = (NamespaceSupport)array[XSAttributeChecker.ATTIDX_ENUMNSDECLS];
                if (xsSimpleType.getVariety() == 1 && xsSimpleType.getPrimitiveKind() == 20) {
                    xsDocumentInfo.fValidationContext.setNamespaceSupport(namespaceSupport);
                    try {
                        this.fSchemaHandler.getGlobalDecl(xsDocumentInfo, 6, (QName)XSDAbstractTraverser.fQNameDV.validate(s, xsDocumentInfo.fValidationContext, null), nextSiblingElement);
                    }
                    catch (InvalidDatatypeValueException ex) {
                        this.reportSchemaError(ex.getKey(), ex.getArgs(), nextSiblingElement);
                    }
                    xsDocumentInfo.fValidationContext.setNamespaceSupport(xsDocumentInfo.fNamespaceSupport);
                }
                if (enumeration == null) {
                    enumeration = new Vector<String>();
                    enumAnnotations = new XSObjectListImpl();
                }
                enumeration.addElement(s);
                enumAnnotations.add(null);
                if (containsQName) {
                    enumNSDecls.addElement(namespaceSupport);
                }
                Element element = DOMUtil.getFirstChildElement(nextSiblingElement);
                if (element != null) {
                    if (DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        enumAnnotations.add(enumAnnotations.getLength() - 1, this.traverseAnnotationDecl(element, array, false, xsDocumentInfo));
                        element = DOMUtil.getNextSiblingElement(element);
                    }
                    else {
                        final String syntheticAnnotation = DOMUtil.getSyntheticAnnotation(nextSiblingElement);
                        if (syntheticAnnotation != null) {
                            enumAnnotations.add(enumAnnotations.getLength() - 1, this.traverseSyntheticAnnotation(nextSiblingElement, syntheticAnnotation, array, false, xsDocumentInfo));
                        }
                    }
                    if (element != null && DOMUtil.getLocalName(element).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "enumeration", "(annotation?)", DOMUtil.getLocalName(element) }, element);
                    }
                }
                else {
                    final String syntheticAnnotation2 = DOMUtil.getSyntheticAnnotation(nextSiblingElement);
                    if (syntheticAnnotation2 != null) {
                        enumAnnotations.add(enumAnnotations.getLength() - 1, this.traverseSyntheticAnnotation(nextSiblingElement, syntheticAnnotation2, array, false, xsDocumentInfo));
                    }
                }
            }
            else if (localName.equals(SchemaSymbols.ELT_PATTERN)) {
                array = this.fAttrChecker.checkAttributes(nextSiblingElement, false, xsDocumentInfo);
                if (this.fPattern.length() == 0) {
                    this.fPattern.append((String)array[XSAttributeChecker.ATTIDX_VALUE]);
                }
                else {
                    this.fPattern.append("|");
                    this.fPattern.append((String)array[XSAttributeChecker.ATTIDX_VALUE]);
                }
                Element element2 = DOMUtil.getFirstChildElement(nextSiblingElement);
                if (element2 != null) {
                    if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        if (patternAnnotations == null) {
                            patternAnnotations = new XSObjectListImpl();
                        }
                        patternAnnotations.add(this.traverseAnnotationDecl(element2, array, false, xsDocumentInfo));
                        element2 = DOMUtil.getNextSiblingElement(element2);
                    }
                    if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "pattern", "(annotation?)", DOMUtil.getLocalName(element2) }, element2);
                    }
                }
            }
            else {
                short n;
                if (localName.equals(SchemaSymbols.ELT_MINLENGTH)) {
                    n = 2;
                }
                else if (localName.equals(SchemaSymbols.ELT_MAXLENGTH)) {
                    n = 4;
                }
                else if (localName.equals(SchemaSymbols.ELT_MAXEXCLUSIVE)) {
                    n = 64;
                }
                else if (localName.equals(SchemaSymbols.ELT_MAXINCLUSIVE)) {
                    n = 32;
                }
                else if (localName.equals(SchemaSymbols.ELT_MINEXCLUSIVE)) {
                    n = 128;
                }
                else if (localName.equals(SchemaSymbols.ELT_MININCLUSIVE)) {
                    n = 256;
                }
                else if (localName.equals(SchemaSymbols.ELT_TOTALDIGITS)) {
                    n = 512;
                }
                else if (localName.equals(SchemaSymbols.ELT_FRACTIONDIGITS)) {
                    n = 1024;
                }
                else if (localName.equals(SchemaSymbols.ELT_WHITESPACE)) {
                    n = 16;
                }
                else {
                    if (!localName.equals(SchemaSymbols.ELT_LENGTH)) {
                        break;
                    }
                    n = 1;
                }
                array = this.fAttrChecker.checkAttributes(nextSiblingElement, false, xsDocumentInfo);
                if ((fPresentFacets & n) != 0x0) {
                    this.reportSchemaError("src-single-facet-value", new Object[] { localName }, nextSiblingElement);
                }
                else if (array[XSAttributeChecker.ATTIDX_VALUE] != null) {
                    fPresentFacets |= n;
                    if (array[XSAttributeChecker.ATTIDX_FIXED]) {
                        fFixedFacets |= n;
                    }
                    switch (n) {
                        case 2: {
                            this.xsFacets.minLength = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 4: {
                            this.xsFacets.maxLength = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 64: {
                            this.xsFacets.maxExclusive = (String)array[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 32: {
                            this.xsFacets.maxInclusive = (String)array[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 128: {
                            this.xsFacets.minExclusive = (String)array[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 256: {
                            this.xsFacets.minInclusive = (String)array[XSAttributeChecker.ATTIDX_VALUE];
                            break;
                        }
                        case 512: {
                            this.xsFacets.totalDigits = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 1024: {
                            this.xsFacets.fractionDigits = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                        case 16: {
                            this.xsFacets.whiteSpace = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).shortValue();
                            break;
                        }
                        case 1: {
                            this.xsFacets.length = ((XInt)array[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                            break;
                        }
                    }
                }
                Element element3 = DOMUtil.getFirstChildElement(nextSiblingElement);
                if (element3 != null) {
                    if (DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        final XSAnnotationImpl traverseAnnotationDecl = this.traverseAnnotationDecl(element3, array, false, xsDocumentInfo);
                        switch (n) {
                            case 2: {
                                this.xsFacets.minLengthAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 4: {
                                this.xsFacets.maxLengthAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 64: {
                                this.xsFacets.maxExclusiveAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 32: {
                                this.xsFacets.maxInclusiveAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 128: {
                                this.xsFacets.minExclusiveAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 256: {
                                this.xsFacets.minInclusiveAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 512: {
                                this.xsFacets.totalDigitsAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 1024: {
                                this.xsFacets.fractionDigitsAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 16: {
                                this.xsFacets.whiteSpaceAnnotation = traverseAnnotationDecl;
                                break;
                            }
                            case 1: {
                                this.xsFacets.lengthAnnotation = traverseAnnotationDecl;
                                break;
                            }
                        }
                        element3 = DOMUtil.getNextSiblingElement(element3);
                    }
                    else {
                        final String syntheticAnnotation3 = DOMUtil.getSyntheticAnnotation(nextSiblingElement);
                        if (syntheticAnnotation3 != null) {
                            final XSAnnotationImpl traverseSyntheticAnnotation = this.traverseSyntheticAnnotation(nextSiblingElement, syntheticAnnotation3, array, false, xsDocumentInfo);
                            switch (n) {
                                case 2: {
                                    this.xsFacets.minLengthAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 4: {
                                    this.xsFacets.maxLengthAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 64: {
                                    this.xsFacets.maxExclusiveAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 32: {
                                    this.xsFacets.maxInclusiveAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 128: {
                                    this.xsFacets.minExclusiveAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 256: {
                                    this.xsFacets.minInclusiveAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 512: {
                                    this.xsFacets.totalDigitsAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 1024: {
                                    this.xsFacets.fractionDigitsAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 16: {
                                    this.xsFacets.whiteSpaceAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                                case 1: {
                                    this.xsFacets.lengthAnnotation = traverseSyntheticAnnotation;
                                    break;
                                }
                            }
                        }
                    }
                    if (element3 != null && DOMUtil.getLocalName(element3).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        this.reportSchemaError("s4s-elt-must-match.1", new Object[] { localName, "(annotation?)", DOMUtil.getLocalName(element3) }, element3);
                    }
                }
            }
            this.fAttrChecker.returnAttrArray(array, xsDocumentInfo);
            nextSiblingElement = DOMUtil.getNextSiblingElement(nextSiblingElement);
        }
        if (enumeration != null) {
            fPresentFacets |= 0x800;
            this.xsFacets.enumeration = enumeration;
            this.xsFacets.enumNSDecls = enumNSDecls;
            this.xsFacets.enumAnnotations = enumAnnotations;
        }
        if (this.fPattern.length() != 0) {
            fPresentFacets |= 0x8;
            this.xsFacets.pattern = this.fPattern.toString();
            this.xsFacets.patternAnnotations = patternAnnotations;
        }
        this.fPattern.setLength(0);
        final FacetInfo facetInfo = new FacetInfo();
        facetInfo.facetdata = this.xsFacets;
        facetInfo.nodeAfterFacets = nextSiblingElement;
        facetInfo.fPresentFacets = fPresentFacets;
        facetInfo.fFixedFacets = fFixedFacets;
        return facetInfo;
    }
    
    private boolean containsQName(final XSSimpleType xsSimpleType) {
        if (xsSimpleType.getVariety() == 1) {
            final short primitiveKind = xsSimpleType.getPrimitiveKind();
            return primitiveKind == 18 || primitiveKind == 20;
        }
        if (xsSimpleType.getVariety() == 2) {
            return this.containsQName((XSSimpleType)xsSimpleType.getItemType());
        }
        if (xsSimpleType.getVariety() == 3) {
            final XSObjectList memberTypes = xsSimpleType.getMemberTypes();
            for (int i = 0; i < memberTypes.getLength(); ++i) {
                if (this.containsQName((XSSimpleType)memberTypes.item(i))) {
                    return true;
                }
            }
        }
        return false;
    }
    
    Element traverseAttrsAndAttrGrps(final Element element, final XSAttributeGroupDecl xsAttributeGroupDecl, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final XSComplexTypeDecl xsComplexTypeDecl) {
        Element element2;
        for (element2 = element; element2 != null; element2 = DOMUtil.getNextSiblingElement(element2)) {
            final String localName = DOMUtil.getLocalName(element2);
            if (localName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                final XSAttributeUseImpl traverseLocal = this.fSchemaHandler.fAttributeTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar, xsComplexTypeDecl);
                if (traverseLocal == null) {
                    break;
                }
                if (xsAttributeGroupDecl.getAttributeUse(traverseLocal.fAttrDecl.getNamespace(), traverseLocal.fAttrDecl.getName()) == null) {
                    final String addAttributeUse = xsAttributeGroupDecl.addAttributeUse(traverseLocal);
                    if (addAttributeUse != null) {
                        this.reportSchemaError((xsComplexTypeDecl == null) ? "ag-props-correct.3" : "ct-props-correct.5", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName(), traverseLocal.fAttrDecl.getName(), addAttributeUse }, element2);
                    }
                }
                else {
                    this.reportSchemaError((xsComplexTypeDecl == null) ? "ag-props-correct.2" : "ct-props-correct.4", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName(), traverseLocal.fAttrDecl.getName() }, element2);
                }
            }
            else {
                if (!localName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    break;
                }
                final XSAttributeGroupDecl traverseLocal2 = this.fSchemaHandler.fAttributeGroupTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar);
                if (traverseLocal2 == null) {
                    break;
                }
                final XSObjectList attributeUses = traverseLocal2.getAttributeUses();
                final XSAttributeUse xsAttributeUse = null;
                for (int length = attributeUses.getLength(), i = 0; i < length; ++i) {
                    final XSAttributeUseImpl xsAttributeUseImpl = (XSAttributeUseImpl)attributeUses.item(i);
                    if (xsAttributeUse == xsAttributeGroupDecl.getAttributeUse(xsAttributeUseImpl.fAttrDecl.getNamespace(), xsAttributeUseImpl.fAttrDecl.getName())) {
                        final String addAttributeUse2 = xsAttributeGroupDecl.addAttributeUse(xsAttributeUseImpl);
                        if (addAttributeUse2 != null) {
                            this.reportSchemaError((xsComplexTypeDecl == null) ? "ag-props-correct.3" : "ct-props-correct.5", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName(), xsAttributeUseImpl.fAttrDecl.getName(), addAttributeUse2 }, element2);
                        }
                    }
                    else {
                        this.reportSchemaError((xsComplexTypeDecl == null) ? "ag-props-correct.2" : "ct-props-correct.4", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName(), xsAttributeUseImpl.fAttrDecl.getName() }, element2);
                    }
                }
                if (traverseLocal2.fAttributeWC != null) {
                    if (xsAttributeGroupDecl.fAttributeWC == null) {
                        xsAttributeGroupDecl.fAttributeWC = traverseLocal2.fAttributeWC;
                    }
                    else {
                        xsAttributeGroupDecl.fAttributeWC = xsAttributeGroupDecl.fAttributeWC.performIntersectionWith(traverseLocal2.fAttributeWC, xsAttributeGroupDecl.fAttributeWC.fProcessContents);
                        if (xsAttributeGroupDecl.fAttributeWC == null) {
                            this.reportSchemaError((xsComplexTypeDecl == null) ? "src-attribute_group.2" : "src-ct.4", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName() }, element2);
                        }
                    }
                }
            }
        }
        if (element2 != null && DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
            final XSWildcardDecl traverseAnyAttribute = this.fSchemaHandler.fWildCardTraverser.traverseAnyAttribute(element2, xsDocumentInfo, schemaGrammar);
            if (xsAttributeGroupDecl.fAttributeWC == null) {
                xsAttributeGroupDecl.fAttributeWC = traverseAnyAttribute;
            }
            else {
                xsAttributeGroupDecl.fAttributeWC = traverseAnyAttribute.performIntersectionWith(xsAttributeGroupDecl.fAttributeWC, traverseAnyAttribute.fProcessContents);
                if (xsAttributeGroupDecl.fAttributeWC == null) {
                    this.reportSchemaError((xsComplexTypeDecl == null) ? "src-attribute_group.2" : "src-ct.4", new Object[] { (xsComplexTypeDecl == null) ? xsAttributeGroupDecl.fName : xsComplexTypeDecl.getName() }, element2);
                }
            }
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        return element2;
    }
    
    void reportSchemaError(final String s, final Object[] array, final Element element) {
        this.fSchemaHandler.reportSchemaError(s, array, element);
    }
    
    void checkNotationType(final String s, final XSTypeDefinition xsTypeDefinition, final Element element) {
        if (xsTypeDefinition.getTypeCategory() == 16 && ((XSSimpleType)xsTypeDefinition).getVariety() == 1 && ((XSSimpleType)xsTypeDefinition).getPrimitiveKind() == 20 && (((XSSimpleType)xsTypeDefinition).getDefinedFacets() & 0x800) == 0x0) {
            this.reportSchemaError("enumeration-required-notation", new Object[] { xsTypeDefinition.getName(), s, DOMUtil.getLocalName(element) }, element);
        }
    }
    
    protected XSParticleDecl checkOccurrences(final XSParticleDecl xsParticleDecl, final String s, final Element element, final int n, final long n2) {
        int fMinOccurs = xsParticleDecl.fMinOccurs;
        int fMaxOccurs = xsParticleDecl.fMaxOccurs;
        final boolean b = (n2 & 1 << XSAttributeChecker.ATTIDX_MINOCCURS) != 0x0L;
        final boolean b2 = (n2 & 1 << XSAttributeChecker.ATTIDX_MAXOCCURS) != 0x0L;
        final boolean b3 = (n & 0x1) != 0x0;
        final boolean b4 = (n & 0x8) != 0x0;
        final boolean b5 = (n & 0x2) != 0x0;
        if ((n & 0x4) != 0x0) {
            if (!b) {
                this.reportSchemaError("s4s-att-not-allowed", new Object[] { s, "minOccurs" }, element);
                fMinOccurs = 1;
            }
            if (!b2) {
                this.reportSchemaError("s4s-att-not-allowed", new Object[] { s, "maxOccurs" }, element);
                fMaxOccurs = 1;
            }
        }
        if (fMinOccurs == 0 && fMaxOccurs == 0) {
            xsParticleDecl.fType = 0;
            return null;
        }
        if (b3) {
            if (fMaxOccurs != 1) {
                this.reportSchemaError("cos-all-limited.2", new Object[] { new Integer(fMaxOccurs), ((XSElementDecl)xsParticleDecl.fValue).getName() }, element);
                fMaxOccurs = 1;
                if (fMinOccurs > 1) {
                    fMinOccurs = 1;
                }
            }
        }
        else if ((b4 || b5) && fMaxOccurs != 1) {
            this.reportSchemaError("cos-all-limited.1.2", null, element);
            if (fMinOccurs > 1) {
                fMinOccurs = 1;
            }
            fMaxOccurs = 1;
        }
        xsParticleDecl.fMaxOccurs = fMinOccurs;
        xsParticleDecl.fMaxOccurs = fMaxOccurs;
        return xsParticleDecl;
    }
    
    private static String processAttValue(final String s) {
        final StringBuffer sb = new StringBuffer(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 == '\"') {
                sb.append("&quot;");
            }
            else if (char1 == '>') {
                sb.append("&gt;");
            }
            else if (char1 == '&') {
                sb.append("&amp;");
            }
            else {
                sb.append(char1);
            }
        }
        return sb.toString();
    }
    
    static {
        fQNameDV = (XSSimpleType)SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl("QName");
    }
    
    class FacetInfo
    {
        XSFacets facetdata;
        Element nodeAfterFacets;
        short fPresentFacets;
        short fFixedFacets;
    }
}

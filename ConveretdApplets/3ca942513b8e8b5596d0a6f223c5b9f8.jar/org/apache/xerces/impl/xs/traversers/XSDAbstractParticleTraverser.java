// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSAnnotationImpl;
import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.xs.XSObject;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

abstract class XSDAbstractParticleTraverser extends XSDAbstractTraverser
{
    ParticleArray fPArray;
    
    XSDAbstractParticleTraverser(final XSDHandler xsdHandler, final XSAttributeChecker xsAttributeChecker) {
        super(xsdHandler, xsAttributeChecker);
        this.fPArray = new ParticleArray();
    }
    
    XSParticleDecl traverseAll(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final XSObject xsObject) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
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
        this.fPArray.pushContext();
        while (element2 != null) {
            XSParticleDecl traverseLocal = null;
            if (DOMUtil.getLocalName(element2).equals(SchemaSymbols.ELT_ELEMENT)) {
                traverseLocal = super.fSchemaHandler.fElementTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar, 1, xsObject);
            }
            else {
                this.reportSchemaError("s4s-elt-must-match.1", new Object[] { "all", "(annotation?, element*)", DOMUtil.getLocalName(element2) }, element2);
            }
            if (traverseLocal != null) {
                this.fPArray.addParticle(traverseLocal);
            }
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        final XInt xInt = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt xInt2 = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        final Long n2 = (Long)checkAttributes[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        final XSModelGroupImpl fValue = new XSModelGroupImpl();
        fValue.fCompositor = 103;
        fValue.fParticleCount = this.fPArray.getParticleCount();
        fValue.fParticles = this.fPArray.popContext();
        fValue.fAnnotation = fAnnotation;
        final XSParticleDecl xsParticleDecl = new XSParticleDecl();
        xsParticleDecl.fType = 3;
        xsParticleDecl.fMinOccurs = xInt.intValue();
        xsParticleDecl.fMaxOccurs = xInt2.intValue();
        xsParticleDecl.fValue = fValue;
        final XSParticleDecl checkOccurrences = this.checkOccurrences(xsParticleDecl, SchemaSymbols.ELT_ALL, (Element)element.getParentNode(), n, n2);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return checkOccurrences;
    }
    
    XSParticleDecl traverseSequence(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final XSObject xsObject) {
        return this.traverseSeqChoice(element, xsDocumentInfo, schemaGrammar, n, false, xsObject);
    }
    
    XSParticleDecl traverseChoice(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final XSObject xsObject) {
        return this.traverseSeqChoice(element, xsDocumentInfo, schemaGrammar, n, true, xsObject);
    }
    
    private XSParticleDecl traverseSeqChoice(final Element element, final XSDocumentInfo xsDocumentInfo, final SchemaGrammar schemaGrammar, final int n, final boolean b, final XSObject xsObject) {
        final Object[] checkAttributes = super.fAttrChecker.checkAttributes(element, false, xsDocumentInfo);
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
        this.fPArray.pushContext();
        while (element2 != null) {
            XSParticleDecl xsParticleDecl = null;
            final String localName = DOMUtil.getLocalName(element2);
            if (localName.equals(SchemaSymbols.ELT_ELEMENT)) {
                xsParticleDecl = super.fSchemaHandler.fElementTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar, 0, xsObject);
            }
            else if (localName.equals(SchemaSymbols.ELT_GROUP)) {
                xsParticleDecl = super.fSchemaHandler.fGroupTraverser.traverseLocal(element2, xsDocumentInfo, schemaGrammar);
                if (this.hasAllContent(xsParticleDecl)) {
                    xsParticleDecl = null;
                    this.reportSchemaError("cos-all-limited.1.2", null, element2);
                }
            }
            else if (localName.equals(SchemaSymbols.ELT_CHOICE)) {
                xsParticleDecl = this.traverseChoice(element2, xsDocumentInfo, schemaGrammar, 0, xsObject);
            }
            else if (localName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                xsParticleDecl = this.traverseSequence(element2, xsDocumentInfo, schemaGrammar, 0, xsObject);
            }
            else if (localName.equals(SchemaSymbols.ELT_ANY)) {
                xsParticleDecl = super.fSchemaHandler.fWildCardTraverser.traverseAny(element2, xsDocumentInfo, schemaGrammar);
            }
            else {
                Object[] array;
                if (b) {
                    array = new Object[] { "choice", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(element2) };
                }
                else {
                    array = new Object[] { "sequence", "(annotation?, (element | group | choice | sequence | any)*)", DOMUtil.getLocalName(element2) };
                }
                this.reportSchemaError("s4s-elt-must-match.1", array, element2);
            }
            if (xsParticleDecl != null) {
                this.fPArray.addParticle(xsParticleDecl);
            }
            element2 = DOMUtil.getNextSiblingElement(element2);
        }
        final XInt xInt = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt xInt2 = (XInt)checkAttributes[XSAttributeChecker.ATTIDX_MAXOCCURS];
        final Long n2 = (Long)checkAttributes[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        final XSModelGroupImpl fValue = new XSModelGroupImpl();
        fValue.fCompositor = (short)(b ? 101 : 102);
        fValue.fParticleCount = this.fPArray.getParticleCount();
        fValue.fParticles = this.fPArray.popContext();
        fValue.fAnnotation = fAnnotation;
        final XSParticleDecl xsParticleDecl2 = new XSParticleDecl();
        xsParticleDecl2.fType = 3;
        xsParticleDecl2.fMinOccurs = xInt.intValue();
        xsParticleDecl2.fMaxOccurs = xInt2.intValue();
        xsParticleDecl2.fValue = fValue;
        final XSParticleDecl checkOccurrences = this.checkOccurrences(xsParticleDecl2, b ? SchemaSymbols.ELT_CHOICE : SchemaSymbols.ELT_SEQUENCE, (Element)element.getParentNode(), n, n2);
        super.fAttrChecker.returnAttrArray(checkAttributes, xsDocumentInfo);
        return checkOccurrences;
    }
    
    protected boolean hasAllContent(final XSParticleDecl xsParticleDecl) {
        return xsParticleDecl != null && xsParticleDecl.fType == 3 && ((XSModelGroupImpl)xsParticleDecl.fValue).fCompositor == 103;
    }
    
    protected static class ParticleArray
    {
        XSParticleDecl[] fParticles;
        int[] fPos;
        int fContextCount;
        
        protected ParticleArray() {
            this.fParticles = new XSParticleDecl[10];
            this.fPos = new int[5];
            this.fContextCount = 0;
        }
        
        void pushContext() {
            ++this.fContextCount;
            if (this.fContextCount == this.fPos.length) {
                final int[] fPos = new int[this.fContextCount * 2];
                System.arraycopy(this.fPos, 0, fPos, 0, this.fContextCount);
                this.fPos = fPos;
            }
            this.fPos[this.fContextCount] = this.fPos[this.fContextCount - 1];
        }
        
        int getParticleCount() {
            return this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
        }
        
        void addParticle(final XSParticleDecl xsParticleDecl) {
            if (this.fPos[this.fContextCount] == this.fParticles.length) {
                final XSParticleDecl[] fParticles = new XSParticleDecl[this.fPos[this.fContextCount] * 2];
                System.arraycopy(this.fParticles, 0, fParticles, 0, this.fPos[this.fContextCount]);
                this.fParticles = fParticles;
            }
            this.fParticles[this.fPos[this.fContextCount]++] = xsParticleDecl;
        }
        
        XSParticleDecl[] popContext() {
            final int n = this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
            Object o = null;
            if (n != 0) {
                o = new XSParticleDecl[n];
                System.arraycopy(this.fParticles, this.fPos[this.fContextCount - 1], o, 0, n);
                for (int i = this.fPos[this.fContextCount - 1]; i < this.fPos[this.fContextCount]; ++i) {
                    this.fParticles[i] = null;
                }
            }
            --this.fContextCount;
            return (XSParticleDecl[])o;
        }
    }
}

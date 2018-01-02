// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.traversers;

import org.apache.xerces.impl.xs.XSModelGroupImpl;
import org.apache.xerces.impl.xs.util.XInt;
import org.apache.xerces.impl.xs.SchemaSymbols;
import org.w3c.dom.Node;
import org.apache.xerces.util.DOMUtil;
import org.apache.xerces.impl.xs.XSParticleDecl;
import org.apache.xerces.impl.xs.XSComplexTypeDecl;
import org.apache.xerces.impl.xs.SchemaGrammar;
import org.w3c.dom.Element;

abstract class XSDAbstractParticleTraverser extends XSDAbstractTraverser
{
    ParticleArray fPArray;
    
    XSDAbstractParticleTraverser(final XSDHandler handler, final XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
        this.fPArray = new ParticleArray();
    }
    
    XSParticleDecl traverseAll(final Element allDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final XSComplexTypeDecl enclosingCT) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(allDecl, false, schemaDoc);
        Element child = DOMUtil.getFirstChildElement(allDecl);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        String childName = null;
        this.fPArray.pushContext();
        while (child != null) {
            XSParticleDecl particle = null;
            childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ELEMENT)) {
                particle = super.fSchemaHandler.fElementTraverser.traverseLocal(child, schemaDoc, grammar, 1, enclosingCT);
            }
            else {
                final Object[] args = { "all", "(annotation?, element*)" };
                this.reportSchemaError("s4s-elt-must-match", args, child);
            }
            if (particle != null) {
                this.fPArray.addParticle(particle);
            }
            child = DOMUtil.getNextSiblingElement(child);
        }
        XSParticleDecl particle = null;
        if (this.fPArray.getParticleCount() != 0) {
            final XInt minAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
            final XInt maxAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
            final Long defaultVals = (Long)attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT];
            final XSModelGroupImpl group = new XSModelGroupImpl();
            group.fCompositor = 103;
            group.fParticleCount = this.fPArray.getParticleCount();
            group.fParticles = this.fPArray.popContext();
            particle = new XSParticleDecl();
            particle.fType = 3;
            particle.fMinOccurs = minAtt.intValue();
            particle.fMaxOccurs = maxAtt.intValue();
            particle.fValue = group;
            particle = this.checkOccurrences(particle, SchemaSymbols.ELT_ALL, (Element)allDecl.getParentNode(), allContextFlags, defaultVals);
        }
        else {
            this.fPArray.discardContext();
        }
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }
    
    XSParticleDecl traverseSequence(final Element seqDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final XSComplexTypeDecl enclosingCT) {
        return this.traverseSeqChoice(seqDecl, schemaDoc, grammar, allContextFlags, false, enclosingCT);
    }
    
    XSParticleDecl traverseChoice(final Element choiceDecl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final XSComplexTypeDecl enclosingCT) {
        return this.traverseSeqChoice(choiceDecl, schemaDoc, grammar, allContextFlags, true, enclosingCT);
    }
    
    private XSParticleDecl traverseSeqChoice(final Element decl, final XSDocumentInfo schemaDoc, final SchemaGrammar grammar, final int allContextFlags, final boolean choice, final XSComplexTypeDecl enclosingCT) {
        final Object[] attrValues = super.fAttrChecker.checkAttributes(decl, false, schemaDoc);
        Element child = DOMUtil.getFirstChildElement(decl);
        if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            this.traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        final boolean hadContent = false;
        String childName = null;
        this.fPArray.pushContext();
        while (child != null) {
            XSParticleDecl particle = null;
            childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_ELEMENT)) {
                particle = super.fSchemaHandler.fElementTraverser.traverseLocal(child, schemaDoc, grammar, 0, enclosingCT);
            }
            else if (childName.equals(SchemaSymbols.ELT_GROUP)) {
                particle = super.fSchemaHandler.fGroupTraverser.traverseLocal(child, schemaDoc, grammar);
                if (this.hasAllContent(particle)) {
                    particle = null;
                    this.reportSchemaError("cos-all-limited.1.2", null, child);
                }
            }
            else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = this.traverseChoice(child, schemaDoc, grammar, 0, enclosingCT);
            }
            else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = this.traverseSequence(child, schemaDoc, grammar, 0, enclosingCT);
            }
            else if (childName.equals(SchemaSymbols.ELT_ANY)) {
                particle = super.fSchemaHandler.fWildCardTraverser.traverseAny(child, schemaDoc, grammar);
            }
            else {
                Object[] args;
                if (choice) {
                    args = new Object[] { "choice", "(annotation?, (element | group | choice | sequence | any)*)" };
                }
                else {
                    args = new Object[] { "sequence", "(annotation?, (element | group | choice | sequence | any)*)" };
                }
                this.reportSchemaError("s4s-elt-must-match", args, child);
            }
            if (particle != null) {
                this.fPArray.addParticle(particle);
            }
            child = DOMUtil.getNextSiblingElement(child);
        }
        XSParticleDecl particle = null;
        final XInt minAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        final XInt maxAtt = (XInt)attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
        final Long defaultVals = (Long)attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT];
        final XSModelGroupImpl group = new XSModelGroupImpl();
        group.fCompositor = (short)(choice ? 101 : 102);
        group.fParticleCount = this.fPArray.getParticleCount();
        group.fParticles = this.fPArray.popContext();
        particle = new XSParticleDecl();
        particle.fType = 3;
        particle.fMinOccurs = minAtt.intValue();
        particle.fMaxOccurs = maxAtt.intValue();
        particle.fValue = group;
        particle = this.checkOccurrences(particle, choice ? SchemaSymbols.ELT_CHOICE : SchemaSymbols.ELT_SEQUENCE, (Element)decl.getParentNode(), allContextFlags, defaultVals);
        super.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }
    
    protected boolean hasAllContent(final XSParticleDecl particle) {
        return particle != null && particle.fType == 3 && ((XSModelGroupImpl)particle.fValue).fCompositor == 103;
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
                final int newSize = this.fContextCount * 2;
                final int[] newArray = new int[newSize];
                System.arraycopy(this.fPos, 0, newArray, 0, this.fContextCount);
                this.fPos = newArray;
            }
            this.fPos[this.fContextCount] = this.fPos[this.fContextCount - 1];
        }
        
        int getParticleCount() {
            return this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
        }
        
        void addParticle(final XSParticleDecl particle) {
            if (this.fPos[this.fContextCount] == this.fParticles.length) {
                final int newSize = this.fPos[this.fContextCount] * 2;
                final XSParticleDecl[] newArray = new XSParticleDecl[newSize];
                System.arraycopy(this.fParticles, 0, newArray, 0, this.fPos[this.fContextCount]);
                this.fParticles = newArray;
            }
            this.fParticles[this.fPos[this.fContextCount]++] = particle;
        }
        
        XSParticleDecl[] popContext() {
            final int count = this.fPos[this.fContextCount] - this.fPos[this.fContextCount - 1];
            final XSParticleDecl[] array = new XSParticleDecl[count];
            System.arraycopy(this.fParticles, this.fPos[this.fContextCount - 1], array, 0, count);
            for (int i = this.fPos[this.fContextCount - 1]; i < this.fPos[this.fContextCount]; ++i) {
                this.fParticles[i] = null;
            }
            --this.fContextCount;
            return array;
        }
        
        void discardContext() {
            for (int i = this.fPos[this.fContextCount - 1]; i < this.fPos[this.fContextCount]; ++i) {
                this.fParticles[i] = null;
            }
            --this.fContextCount;
        }
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.XSConstraints;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.xni.QName;
import org.apache.xerces.impl.xs.XSElementDecl;

public class XSAllCM implements XSCMValidator
{
    private static final short STATE_START = 0;
    private static final short STATE_VALID = 1;
    private static final short STATE_CHILD = 1;
    private XSElementDecl[] fAllElements;
    private boolean[] fIsOptionalElement;
    private boolean fHasOptionalContent;
    private int fNumElements;
    
    public XSAllCM(final boolean hasOptionalContent, final int size) {
        this.fHasOptionalContent = false;
        this.fNumElements = 0;
        this.fHasOptionalContent = hasOptionalContent;
        this.fAllElements = new XSElementDecl[size];
        this.fIsOptionalElement = new boolean[size];
    }
    
    public void addElement(final XSElementDecl element, final boolean isOptional) {
        this.fAllElements[this.fNumElements] = element;
        this.fIsOptionalElement[this.fNumElements] = isOptional;
        ++this.fNumElements;
    }
    
    public int[] startContentModel() {
        final int[] state = new int[this.fNumElements + 1];
        for (int i = 0; i <= this.fNumElements; ++i) {
            state[i] = 0;
        }
        return state;
    }
    
    Object findMatchingDecl(final QName elementName, final SubstitutionGroupHandler subGroupHandler) {
        Object matchingDecl = null;
        for (int i = 0; i < this.fNumElements; ++i) {
            matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, this.fAllElements[i]);
            if (matchingDecl != null) {
                break;
            }
        }
        return matchingDecl;
    }
    
    public Object oneTransition(final QName elementName, final int[] currentState, final SubstitutionGroupHandler subGroupHandler) {
        if (currentState[0] < 0) {
            currentState[0] = -2;
            return this.findMatchingDecl(elementName, subGroupHandler);
        }
        currentState[0] = 1;
        Object matchingDecl = null;
        for (int i = 0; i < this.fNumElements; ++i) {
            if (currentState[i + 1] == 0) {
                matchingDecl = subGroupHandler.getMatchingElemDecl(elementName, this.fAllElements[i]);
                if (matchingDecl != null) {
                    currentState[i + 1] = 1;
                    return matchingDecl;
                }
            }
        }
        currentState[0] = -1;
        return this.findMatchingDecl(elementName, subGroupHandler);
    }
    
    public boolean endContentModel(final int[] currentState) {
        final int state = currentState[0];
        if (state == -1 || state == -2) {
            return false;
        }
        if (this.fHasOptionalContent && state == 0) {
            return true;
        }
        for (int i = 0; i < this.fNumElements; ++i) {
            if (!this.fIsOptionalElement[i] && currentState[i + 1] == 0) {
                return false;
            }
        }
        return true;
    }
    
    public boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        for (int i = 0; i < this.fNumElements; ++i) {
            for (int j = i + 1; j < this.fNumElements; ++j) {
                if (XSConstraints.overlapUPA(this.fAllElements[i], this.fAllElements[j], subGroupHandler)) {
                    throw new XMLSchemaException("cos-nonambig", new Object[] { this.fAllElements[i].toString(), this.fAllElements[j].toString() });
                }
            }
        }
        return false;
    }
}

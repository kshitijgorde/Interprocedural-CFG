// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.xni.QName;

public class XSEmptyCM implements XSCMValidator
{
    private static final short STATE_START = 0;
    
    public int[] startContentModel() {
        return new int[] { 0 };
    }
    
    public Object oneTransition(final QName elementName, final int[] currentState, final SubstitutionGroupHandler subGroupHandler) {
        if (currentState[0] < 0) {
            currentState[0] = -2;
            return null;
        }
        currentState[0] = -1;
        return null;
    }
    
    public boolean endContentModel(final int[] currentState) {
        final boolean isFinal = false;
        final int state = currentState[0];
        return state >= 0;
    }
    
    public boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        return false;
    }
}

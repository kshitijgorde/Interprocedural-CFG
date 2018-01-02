// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.xni.QName;
import java.util.Vector;

public class XSEmptyCM implements XSCMValidator
{
    private static final short STATE_START = 0;
    private static final Vector EMPTY;
    
    public int[] startContentModel() {
        return new int[] { 0 };
    }
    
    public Object oneTransition(final QName qName, final int[] array, final SubstitutionGroupHandler substitutionGroupHandler) {
        if (array[0] < 0) {
            array[0] = -2;
            return null;
        }
        array[0] = -1;
        return null;
    }
    
    public boolean endContentModel(final int[] array) {
        return array[0] >= 0;
    }
    
    public boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler substitutionGroupHandler) throws XMLSchemaException {
        return false;
    }
    
    public Vector whatCanGoHere(final int[] array) {
        return XSEmptyCM.EMPTY;
    }
    
    static {
        EMPTY = new Vector(0);
    }
}

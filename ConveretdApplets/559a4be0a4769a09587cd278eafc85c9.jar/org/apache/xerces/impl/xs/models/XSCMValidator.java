// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.models;

import org.apache.xerces.impl.xs.XMLSchemaException;
import org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import org.apache.xerces.xni.QName;

public interface XSCMValidator
{
    public static final short FIRST_ERROR = -1;
    public static final short SUBSEQUENT_ERROR = -2;
    
    int[] startContentModel();
    
    Object oneTransition(final QName p0, final int[] p1, final SubstitutionGroupHandler p2);
    
    boolean endContentModel(final int[] p0);
    
    boolean checkUniqueParticleAttribution(final SubstitutionGroupHandler p0) throws XMLSchemaException;
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Hashtable;

public abstract class AbstractDatatypeValidator implements DatatypeValidator, Cloneable
{
    private Hashtable fFacets;
    
    public abstract Object validate(final String p0, final Object p1) throws InvalidDatatypeValueException;
    
    public abstract Object clone() throws CloneNotSupportedException;
    
    public Hashtable getFacets() {
        return this.fFacets;
    }
    
    public short getWSFacet() {
        return 2;
    }
    
    public int compare(final String s, final String s2) {
        return s.compareTo(s2);
    }
}

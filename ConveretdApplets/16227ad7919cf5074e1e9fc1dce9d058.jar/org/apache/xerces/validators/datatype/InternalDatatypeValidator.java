// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

interface InternalDatatypeValidator extends DatatypeValidator
{
    void validate(final int p0) throws InvalidDatatypeValueException;
    
    void setFacets(final int[] p0) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException;
}

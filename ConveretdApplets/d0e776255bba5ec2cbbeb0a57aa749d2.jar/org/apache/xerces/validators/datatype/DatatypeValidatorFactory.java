// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Hashtable;

public interface DatatypeValidatorFactory
{
    DatatypeValidator createDatatypeValidator(final String p0, final DatatypeValidator p1, final Hashtable p2, final boolean p3) throws InvalidDatatypeFacetException;
}

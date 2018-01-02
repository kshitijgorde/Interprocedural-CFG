// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Locale;
import java.util.Hashtable;

public interface DatatypeValidator
{
    public static final String MININCLUSIVE = "minInclusive";
    public static final String MINEXCLUSIVE = "minExclusive";
    public static final String MAXINCLUSIVE = "maxInclusive";
    public static final String MAXEXCLUSIVE = "maxExclusive";
    public static final String MINABSOLUTEVALUE = "minAbsoluteValue";
    public static final String MAXABSOLUTEVALUE = "maxAbsoluteValue";
    public static final String PRECISION = "precision";
    public static final String SCALE = "scale";
    public static final String LENGTH = "length";
    public static final String MAXLENGTH = "maxLength";
    public static final String ENUMERATION = "enumeration";
    public static final String LITERAL = "literal";
    public static final String LEXICALREPRESENTATION = "lexicalRepresentation";
    public static final String LEXICAL = "lexical";
    public static final String ENCODING = "encoding";
    
    void validate(final String p0) throws InvalidDatatypeValueException;
    
    void setFacets(final Hashtable p0) throws UnknownFacetException, IllegalFacetException, IllegalFacetValueException;
    
    void setBasetype(final DatatypeValidator p0);
    
    void setLocale(final Locale p0);
}

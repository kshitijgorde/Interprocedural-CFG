// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.datatype;

import java.util.Hashtable;

public interface DatatypeValidator
{
    public static final int FACET_LENGTH = 1;
    public static final int FACET_MINLENGTH = 2;
    public static final int FACET_MAXLENGTH = 4;
    public static final int FACET_PATTERN = 8;
    public static final int FACET_ENUMERATION = 16;
    public static final int FACET_MAXINCLUSIVE = 32;
    public static final int FACET_MAXEXCLUSIVE = 64;
    public static final int FACET_MININCLUSIVE = 128;
    public static final int FACET_MINEXCLUSIVE = 256;
    public static final int FACET_PRECISSION = 512;
    public static final int FACET_SCALE = 1024;
    public static final int FACET_ENCODING = 2048;
    public static final int FACET_DURATION = 4096;
    public static final int FACET_PERIOD = 8192;
    public static final int FACET_WHITESPACE = 16384;
    public static final short PRESERVE = 0;
    public static final short REPLACE = 1;
    public static final short COLLAPSE = 2;
    
    Object validate(final String p0, final Object p1) throws InvalidDatatypeValueException;
    
    short getWSFacet();
    
    Hashtable getFacets();
    
    int compare(final String p0, final String p1);
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs.psvi;

public interface XSSimpleTypeDefinition extends XSTypeDefinition
{
    public static final short VARIETY_ABSENT = 0;
    public static final short VARIETY_ATOMIC = 1;
    public static final short VARIETY_LIST = 2;
    public static final short VARIETY_UNION = 3;
    public static final short ORDERED_FALSE = 0;
    public static final short ORDERED_PARTIAL = 1;
    public static final short ORDERED_TOTAL = 2;
    public static final short FACET_NONE = 0;
    public static final short FACET_LENGTH = 2;
    public static final short FACET_MINLENGTH = 4;
    public static final short FACET_MAXLENGTH = 8;
    public static final short FACET_PATTERN = 16;
    public static final short FACET_ENUMERATION = 32;
    public static final short FACET_WHITESPACE = 64;
    public static final short FACET_MAXINCLUSIVE = 128;
    public static final short FACET_MAXEXCLUSIVE = 256;
    public static final short FACET_MINEXCLUSIVE = 512;
    public static final short FACET_MININCLUSIVE = 1024;
    public static final short FACET_TOTALDIGITS = 2048;
    public static final short FACET_FRACTIONDIGITS = 4096;
    
    boolean getIsDefinedFacet(final short p0);
    
    short getDefinedFacets();
    
    boolean getIsFixedFacet(final short p0);
    
    short getFixedFacets();
    
    String getLexicalFacetValue(final short p0);
    
    StringList getLexicalEnumerations();
    
    StringList getLexicalPatterns();
    
    short getOrdered();
    
    boolean getIsFinite();
    
    boolean getIsBounded();
    
    boolean getIsNumeric();
    
    short getVariety();
    
    XSSimpleTypeDefinition getPrimitiveType();
    
    XSSimpleTypeDefinition getItemType();
    
    XSObjectList getMemberTypes();
    
    XSAnnotation getAnnotation();
}

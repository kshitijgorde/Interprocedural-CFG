// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util.entity;

public interface DTDEntityEventHandler
{
    boolean scanIntSubsetDecl(final int p0, final ParsedEntity p1);
    
    boolean scanExtSubsetDecl(final int p0, final ParsedEntity p1);
    
    boolean scanExternalSubset(final ParsedEntity p0);
    
    boolean scanDefaultAttValue(final int p0, final ParsedEntity p1);
    
    boolean scanEntityValue(final int p0, final ParsedEntity p1);
    
    boolean scanPEWithinMarkup(final int p0, final ParsedEntity p1);
    
    void defaultAttValueCharacter(final int p0, final int p1, final boolean p2);
}

// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public interface XMLStringBuffer
{
    void reset(final boolean p0);
    
    void setOffset(final int p0);
    
    int getOffset();
    
    void append(final int p0);
    
    void append(final String p0);
    
    void append(final XMLString p0);
    
    boolean normalizeTextValue(final XMLString p0, final XMLString p1);
    
    void normalizedAppend(final XMLString p0);
    
    void normalizePublicID(final XMLString p0);
    
    void setStringValues(final int p0, final int p1, final XMLString p2);
}

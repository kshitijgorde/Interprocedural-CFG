// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public interface CharacterIterator
{
    String substring(final int p0, final int p1);
    
    String substring(final int p0);
    
    char charAt(final int p0);
    
    boolean isEnd(final int p0);
}

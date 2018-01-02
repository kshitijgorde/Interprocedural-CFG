// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public interface CharacterIterator
{
    char charAt(final int p0);
    
    boolean isEnd(final int p0);
    
    String substring(final int p0);
    
    String substring(final int p0, final int p1);
}

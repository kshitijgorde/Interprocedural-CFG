// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public final class StringCharacterIterator implements CharacterIterator
{
    private final String src;
    
    public StringCharacterIterator(final String src) {
        this.src = src;
    }
    
    public String substring(final int offset, final int length) {
        return this.src.substring(offset, length);
    }
    
    public String substring(final int offset) {
        return this.src.substring(offset);
    }
    
    public char charAt(final int pos) {
        return this.src.charAt(pos);
    }
    
    public boolean isEnd(final int pos) {
        return pos >= this.src.length();
    }
}

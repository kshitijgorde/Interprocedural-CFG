// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.regexp;

public final class CharacterArrayCharacterIterator implements CharacterIterator
{
    private final char[] src;
    private final int off;
    private final int len;
    
    public CharacterArrayCharacterIterator(final char[] src, final int off, final int len) {
        this.src = src;
        this.off = off;
        this.len = len;
    }
    
    public String substring(final int offset, final int length) {
        return new String(this.src, this.off + offset, length);
    }
    
    public String substring(final int offset) {
        return new String(this.src, this.off + offset, this.len);
    }
    
    public char charAt(final int pos) {
        return this.src[this.off + pos];
    }
    
    public boolean isEnd(final int pos) {
        return pos >= this.len;
    }
}

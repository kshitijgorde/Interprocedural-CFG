// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

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
    
    public char charAt(final int n) {
        return this.src[this.off + n];
    }
    
    public boolean isEnd(final int n) {
        return n >= this.len;
    }
    
    public String substring(final int n) {
        return new String(this.src, this.off + n, this.len);
    }
    
    public String substring(final int n, final int n2) {
        return new String(this.src, this.off + n, n2);
    }
}

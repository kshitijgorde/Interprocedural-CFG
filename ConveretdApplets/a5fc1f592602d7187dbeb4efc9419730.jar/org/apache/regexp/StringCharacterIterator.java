// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.regexp;

public final class StringCharacterIterator implements CharacterIterator
{
    private final String src;
    
    public StringCharacterIterator(final String src) {
        this.src = src;
    }
    
    public char charAt(final int n) {
        return this.src.charAt(n);
    }
    
    public boolean isEnd(final int n) {
        return n >= this.src.length();
    }
    
    public String substring(final int n) {
        return this.src.substring(n);
    }
    
    public String substring(final int n, final int n2) {
        return this.src.substring(n, n2);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.res;

public class CharArrayWrapper
{
    private char[] m_char;
    
    public CharArrayWrapper(final char[] arg) {
        this.m_char = arg;
    }
    
    public char getChar(final int index) {
        return this.m_char[index];
    }
    
    public int getLength() {
        return this.m_char.length;
    }
}

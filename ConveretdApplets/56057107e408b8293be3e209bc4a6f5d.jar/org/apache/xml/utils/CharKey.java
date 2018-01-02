// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class CharKey
{
    private char m_char;
    
    public CharKey() {
    }
    
    public CharKey(final char key) {
        this.m_char = key;
    }
    
    public final boolean equals(final Object obj) {
        return ((CharKey)obj).m_char == this.m_char;
    }
    
    public final int hashCode() {
        return this.m_char;
    }
    
    public final void setChar(final char c) {
        this.m_char = c;
    }
}

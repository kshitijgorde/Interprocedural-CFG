// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class CharKey
{
    private char m_char;
    
    public CharKey(final char key) {
        this.m_char = key;
    }
    
    public CharKey() {
    }
    
    public final void setChar(final char c) {
        this.m_char = c;
    }
    
    public final int hashCode() {
        return this.m_char;
    }
    
    public final boolean equals(final Object obj) {
        return ((CharKey)obj).m_char == this.m_char;
    }
}

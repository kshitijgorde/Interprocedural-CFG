// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

public class StringKey
{
    private String m_str;
    int m_hash;
    
    public StringKey(final String key) {
        this.m_str = key;
        this.m_hash = key.hashCode();
    }
    
    public final boolean equals(final Object obj) {
        return obj.equals(this.m_str);
    }
    
    public int hashCode() {
        return this.m_hash;
    }
    
    public String toString() {
        return this.m_str;
    }
}

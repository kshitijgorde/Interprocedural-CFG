// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils.res;

public class StringArrayWrapper
{
    private String[] m_string;
    
    public StringArrayWrapper(final String[] arg) {
        this.m_string = arg;
    }
    
    public String getString(final int index) {
        return this.m_string[index];
    }
    
    public int getLength() {
        return this.m_string.length;
    }
}

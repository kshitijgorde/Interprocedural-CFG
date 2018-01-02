// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

public class ResultNameSpace
{
    public ResultNameSpace m_next;
    public String m_prefix;
    public String m_uri;
    
    public ResultNameSpace(final String prefix, final String uri) {
        this.m_next = null;
        this.m_prefix = prefix;
        this.m_uri = uri;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import java.io.Serializable;

public class NameSpace implements Serializable
{
    static final long serialVersionUID = 1471232939184881839L;
    public NameSpace m_next;
    public String m_prefix;
    public String m_uri;
    
    public NameSpace(final String prefix, final String uri) {
        this.m_next = null;
        this.m_prefix = prefix;
        this.m_uri = uri;
    }
}

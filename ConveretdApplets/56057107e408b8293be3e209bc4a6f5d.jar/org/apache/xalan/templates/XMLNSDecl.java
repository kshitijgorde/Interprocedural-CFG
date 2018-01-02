// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import java.io.Serializable;

public class XMLNSDecl implements Serializable
{
    private String m_prefix;
    private String m_uri;
    private boolean m_isExcluded;
    
    public XMLNSDecl(final String prefix, final String uri, final boolean isExcluded) {
        this.m_prefix = prefix;
        this.m_uri = uri;
        this.m_isExcluded = isExcluded;
    }
    
    public boolean getIsExcluded() {
        return this.m_isExcluded;
    }
    
    public String getPrefix() {
        return this.m_prefix;
    }
    
    public String getURI() {
        return this.m_uri;
    }
}

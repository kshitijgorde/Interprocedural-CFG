// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.w3c.dom.Entity;
import org.apache.xml.utils.UnImplNode;

public class EntityImpl extends UnImplNode implements Entity
{
    String m_publicId;
    String m_systemId;
    String m_notationName;
    String m_name;
    
    EntityImpl(final String name, final String notationName, final String publicId, final String systemId) {
        this.m_publicId = publicId;
        this.m_systemId = systemId;
        this.m_notationName = notationName;
        this.m_name = name;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public String getNotationName() {
        return this.m_notationName;
    }
    
    public String getPublicId() {
        return this.m_publicId;
    }
    
    public String getSystemId() {
        return this.m_systemId;
    }
}

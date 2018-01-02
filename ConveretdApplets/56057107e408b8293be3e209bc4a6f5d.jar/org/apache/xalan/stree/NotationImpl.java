// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

public class NotationImpl
{
    private String m_name;
    private String m_publicId;
    private String m_systemId;
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 12;
    }
    
    public String getPublicId() {
        return this.m_publicId;
    }
    
    public String getSystemId() {
        return this.m_systemId;
    }
}

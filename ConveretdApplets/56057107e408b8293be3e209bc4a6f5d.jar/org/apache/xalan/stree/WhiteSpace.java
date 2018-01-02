// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

public class WhiteSpace extends Child
{
    String m_data;
    
    public WhiteSpace(final DocumentImpl doc, final char[] ch, final int start, final int length) {
        super(doc);
        this.m_data = new String(ch, start, start + length);
    }
    
    public String getData() {
        return this.m_data;
    }
    
    public int getLength() {
        return this.m_data.length();
    }
    
    public String getLocalName() {
        return "#text";
    }
    
    public String getNodeName() {
        return "#text";
    }
    
    public short getNodeType() {
        return 3;
    }
    
    public String getNodeValue() {
        return this.m_data;
    }
}

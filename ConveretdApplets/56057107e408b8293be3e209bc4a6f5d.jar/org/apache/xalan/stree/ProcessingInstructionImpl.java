// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.w3c.dom.ProcessingInstruction;

public class ProcessingInstructionImpl extends Child implements ProcessingInstruction
{
    private String m_name;
    private String m_data;
    
    ProcessingInstructionImpl(final DocumentImpl doc, final String target, final String data) {
        super(doc);
        this.m_name = target;
        this.m_data = data;
    }
    
    public void dispatchCharactersEvent(final ContentHandler ch) throws SAXException {
        ch.characters(this.m_data.toCharArray(), 0, this.m_data.length());
    }
    
    public String getData() {
        return this.m_data;
    }
    
    public String getLocalName() {
        return this.m_name;
    }
    
    public String getNodeName() {
        return this.m_name;
    }
    
    public short getNodeType() {
        return 7;
    }
    
    public String getNodeValue() {
        return this.m_data;
    }
    
    public String getTarget() {
        return this.m_name;
    }
}

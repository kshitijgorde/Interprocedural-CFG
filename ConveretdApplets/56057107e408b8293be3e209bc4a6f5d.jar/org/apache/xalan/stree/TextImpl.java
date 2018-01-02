// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.apache.xml.utils.FastStringBuffer;
import org.w3c.dom.Text;

public class TextImpl extends Child implements Text
{
    protected String m_data;
    protected int m_start;
    protected int m_length;
    
    public TextImpl(final DocumentImpl doc, final String data) {
        super(doc);
        this.m_data = data;
        this.m_length = data.length();
        this.m_start = -1;
    }
    
    public TextImpl(final DocumentImpl doc, final char[] ch, final int start, final int length) {
        super(doc);
        final FastStringBuffer fsb = doc.m_chars;
        synchronized (fsb) {
            this.m_start = fsb.m_firstFree;
            fsb.append(ch, start, this.m_length = length);
        }
        // monitorexit(fsb)
    }
    
    void appendText(final char[] ch, final int start, final int length) {
        Label_0053: {
            if (this.m_data == null) {
                final FastStringBuffer fsb = super.m_doc.m_chars;
                synchronized (fsb) {
                    fsb.append(ch, start, length);
                    // monitorexit(fsb)
                    break Label_0053;
                }
            }
            this.m_data.concat(ch.toString());
        }
        this.m_length += length;
    }
    
    public void dispatchCharactersEvent(final ContentHandler ch) throws SAXException {
        if (this.m_start == -1) {
            ch.characters(this.m_data.toCharArray(), 0, this.m_data.length());
        }
        else {
            synchronized (super.m_doc.m_chars) {
                ch.characters(super.m_doc.m_chars.m_map, this.m_start, this.m_length);
            }
            // monitorexit(super.m_doc.m_chars)
        }
    }
    
    public String getData() {
        if (this.m_data == null) {
            synchronized (super.m_doc.m_chars) {
                this.m_data = new String(super.m_doc.m_chars.m_map, this.m_start, this.m_length);
            }
            // monitorexit(super.m_doc.m_chars)
        }
        return this.m_data;
    }
    
    public int getLength() {
        return this.m_length;
    }
    
    public int getLengthInBuffer() {
        return this.m_start;
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
        if (this.m_data == null) {
            synchronized (super.m_doc.m_chars) {
                this.m_data = new String(super.m_doc.m_chars.m_map, this.m_start, this.m_length);
            }
            // monitorexit(super.m_doc.m_chars)
        }
        return this.m_data;
    }
    
    public int getStartOffsetInBuffer() {
        return this.m_start;
    }
}

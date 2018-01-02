// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xml.serializer.SerializationHandler;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemTextLiteral extends ElemTemplateElement
{
    static final long serialVersionUID = -7872620006767660088L;
    private boolean m_preserveSpace;
    private char[] m_ch;
    private String m_str;
    private boolean m_disableOutputEscaping;
    
    public ElemTextLiteral() {
        this.m_disableOutputEscaping = false;
    }
    
    public void setPreserveSpace(final boolean v) {
        this.m_preserveSpace = v;
    }
    
    public boolean getPreserveSpace() {
        return this.m_preserveSpace;
    }
    
    public void setChars(final char[] v) {
        this.m_ch = v;
    }
    
    public char[] getChars() {
        return this.m_ch;
    }
    
    public synchronized String getNodeValue() {
        if (null == this.m_str) {
            this.m_str = new String(this.m_ch);
        }
        return this.m_str;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public int getXSLToken() {
        return 78;
    }
    
    public String getNodeName() {
        return "#Text";
    }
    
    public void execute(final TransformerImpl transformer) throws TransformerException {
        try {
            final SerializationHandler rth = transformer.getResultTreeHandler();
            if (transformer.getDebug()) {
                rth.flushPending();
                transformer.getTraceManager().fireTraceEvent(this);
            }
            if (this.m_disableOutputEscaping) {
                rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            }
            rth.characters(this.m_ch, 0, this.m_ch.length);
            if (this.m_disableOutputEscaping) {
                rth.processingInstruction("javax.xml.transform.enable-output-escaping", "");
            }
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        finally {
            if (transformer.getDebug()) {
                try {
                    transformer.getResultTreeHandler().flushPending();
                    transformer.getTraceManager().fireTraceEndEvent(this);
                }
                catch (SAXException se2) {
                    throw new TransformerException(se2);
                }
            }
        }
    }
}

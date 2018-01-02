// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.templates;

import org.apache.xalan.transformer.ResultTreeHandler;
import org.xml.sax.SAXException;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;

public class ElemTextLiteral extends ElemTemplateElement
{
    private boolean m_preserveSpace;
    private char[] m_ch;
    private boolean m_disableOutputEscaping;
    
    public ElemTextLiteral() {
        this.m_disableOutputEscaping = false;
    }
    
    public void execute(final TransformerImpl transformer, final Node sourceNode, final QName mode) throws TransformerException {
        try {
            if (TransformerImpl.S_DEBUG) {
                transformer.getTraceManager().fireTraceEvent(sourceNode, mode, this);
            }
            final ResultTreeHandler rth = transformer.getResultTreeHandler();
            if (this.m_disableOutputEscaping) {
                rth.processingInstruction("javax.xml.transform.disable-output-escaping", "");
            }
            rth.characters(this.m_ch, 0, this.m_ch.length);
            if (this.m_disableOutputEscaping) {
                rth.processingInstruction("javax.xml.transform.enable-output-escaping", "");
            }
        }
        catch (SAXException e) {
            throw new TransformerException(e);
        }
    }
    
    public char[] getChars() {
        return this.m_ch;
    }
    
    public boolean getDisableOutputEscaping() {
        return this.m_disableOutputEscaping;
    }
    
    public String getNodeName() {
        return "#Text";
    }
    
    public boolean getPreserveSpace() {
        return this.m_preserveSpace;
    }
    
    public int getXSLToken() {
        return 78;
    }
    
    public void setChars(final char[] v) {
        this.m_ch = v;
    }
    
    public void setDisableOutputEscaping(final boolean v) {
        this.m_disableOutputEscaping = v;
    }
    
    public void setPreserveSpace(final boolean v) {
        this.m_preserveSpace = v;
    }
}

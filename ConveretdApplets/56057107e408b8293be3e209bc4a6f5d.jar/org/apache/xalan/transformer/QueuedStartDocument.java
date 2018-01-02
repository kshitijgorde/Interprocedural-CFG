// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.xml.sax.Attributes;

public class QueuedStartDocument extends QueuedSAXEvent
{
    private boolean m_isTextEntity;
    
    public QueuedStartDocument() {
        super(1);
        this.m_isTextEntity = false;
    }
    
    void clearPending() {
        super.clearPending();
    }
    
    void flush() throws SAXException {
        if (super.isPending) {
            super.m_contentHandler.startDocument();
            if (super.m_traceManager != null) {
                this.fireGenerateEvent(1, null, null);
            }
            final ContentHandler chandler = this.getContentHandler();
            if (chandler != null && chandler instanceof TransformerClient) {
                ((TransformerClient)chandler).setTransformState(super.m_transformer);
            }
            super.flush();
        }
    }
    
    boolean getIsTextEntity() {
        return this.m_isTextEntity;
    }
    
    void reset() {
        super.reset();
        this.m_isTextEntity = false;
    }
    
    void setIsTextEntity(final boolean b) {
        this.m_isTextEntity = b;
    }
    
    void setPending(final boolean b) {
        super.setPending(b);
    }
}

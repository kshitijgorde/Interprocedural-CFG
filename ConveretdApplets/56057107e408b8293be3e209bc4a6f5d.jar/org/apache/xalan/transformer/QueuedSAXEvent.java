// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.SAXException;
import org.apache.xalan.trace.GenerateEvent;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.apache.xalan.trace.TraceManager;

public abstract class QueuedSAXEvent
{
    static final int DOC = 1;
    static final int ELEM = 2;
    protected TraceManager m_traceManager;
    protected TransformerImpl m_transformer;
    protected ContentHandler m_contentHandler;
    public boolean isPending;
    public boolean isEnded;
    private int m_type;
    
    public QueuedSAXEvent(final int type) {
        this.isPending = false;
        this.isEnded = false;
        this.m_type = type;
    }
    
    void clearPending() {
        this.isPending = false;
    }
    
    protected void fireGenerateEvent(final int type, final String name, final Attributes attrs) {
        if (this.m_traceManager != null) {
            final GenerateEvent ge = new GenerateEvent(this.m_transformer, type, name, attrs);
            this.m_traceManager.fireGenerateEvent(ge);
        }
    }
    
    void flush() throws SAXException {
        this.clearPending();
    }
    
    ContentHandler getContentHandler() {
        return this.m_contentHandler;
    }
    
    int getType() {
        return this.m_type;
    }
    
    void reset() {
        this.isPending = false;
    }
    
    void setContentHandler(final ContentHandler ch) {
        this.m_contentHandler = ch;
    }
    
    void setPending(final boolean b) {
        this.isPending = b;
        this.isEnded = (this.isPending ^ true);
    }
    
    void setTraceManager(final TraceManager traceManager) {
        this.m_traceManager = traceManager;
    }
    
    void setTransformer(final TransformerImpl transformer) {
        this.m_transformer = transformer;
    }
}

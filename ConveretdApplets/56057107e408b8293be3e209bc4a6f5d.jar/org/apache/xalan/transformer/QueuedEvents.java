// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.xml.sax.Attributes;
import org.apache.xalan.serialize.Serializer;

abstract class QueuedEvents
{
    int m_eventCount;
    QueuedStartDocument m_startDoc;
    QueuedStartElement m_startElement;
    private Serializer m_serializer;
    
    QueuedEvents() {
        this.m_eventCount = 0;
        this.m_startDoc = new QueuedStartDocument();
        this.m_startElement = new QueuedStartElement();
    }
    
    QueuedStartDocument getQueuedDoc() {
        return (this.m_eventCount == 1) ? this.m_startDoc : null;
    }
    
    QueuedStartDocument getQueuedDocAtBottom() {
        return this.m_startDoc;
    }
    
    QueuedStartElement getQueuedElem() {
        return (this.m_eventCount > 1) ? this.m_startElement : null;
    }
    
    Serializer getSerializer() {
        return this.m_serializer;
    }
    
    protected abstract void initQSE(final QueuedSAXEvent p0);
    
    void popEvent() {
        this.m_startElement.reset();
        --this.m_eventCount;
    }
    
    void pushDocumentEvent() {
        this.m_startDoc.setPending(true);
        this.initQSE(this.m_startDoc);
        ++this.m_eventCount;
    }
    
    void pushElementEvent(final String ns, final String localName, final String name, final Attributes atts) {
        this.m_startElement.setPending(ns, localName, name, atts);
        this.initQSE(this.m_startElement);
        ++this.m_eventCount;
    }
    
    protected void reInitEvents() {
        this.initQSE(this.m_startDoc);
        this.initQSE(this.m_startElement);
    }
    
    public void reset() {
        this.pushDocumentEvent();
        this.reInitEvents();
    }
    
    void setSerializer(final Serializer s) {
        this.m_serializer = s;
    }
}

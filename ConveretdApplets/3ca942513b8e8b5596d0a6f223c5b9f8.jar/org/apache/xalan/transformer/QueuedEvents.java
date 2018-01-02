// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xml.serializer.Serializer;
import java.util.Vector;
import org.apache.xml.utils.MutableAttrListImpl;

public abstract class QueuedEvents
{
    protected int m_eventCount;
    public boolean m_docPending;
    protected boolean m_docEnded;
    public boolean m_elemIsPending;
    public boolean m_elemIsEnded;
    protected MutableAttrListImpl m_attributes;
    protected boolean m_nsDeclsHaveBeenAdded;
    protected String m_name;
    protected String m_url;
    protected String m_localName;
    protected Vector m_namespaces;
    private Serializer m_serializer;
    
    public QueuedEvents() {
        this.m_eventCount = 0;
        this.m_docPending = false;
        this.m_docEnded = false;
        this.m_elemIsPending = false;
        this.m_elemIsEnded = false;
        this.m_attributes = new MutableAttrListImpl();
        this.m_nsDeclsHaveBeenAdded = false;
        this.m_namespaces = null;
    }
    
    protected void reInitEvents() {
    }
    
    public void reset() {
        this.pushDocumentEvent();
        this.reInitEvents();
    }
    
    void pushDocumentEvent() {
        this.m_docPending = true;
        ++this.m_eventCount;
    }
    
    void popEvent() {
        this.m_elemIsPending = false;
        this.m_attributes.clear();
        this.m_nsDeclsHaveBeenAdded = false;
        this.m_name = null;
        this.m_url = null;
        this.m_localName = null;
        this.m_namespaces = null;
        --this.m_eventCount;
    }
    
    void setSerializer(final Serializer s) {
        this.m_serializer = s;
    }
    
    Serializer getSerializer() {
        return this.m_serializer;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import org.apache.xml.utils.QName;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPath;
import org.apache.xalan.templates.ElemTemplateElement;
import org.w3c.dom.Node;
import javax.xml.transform.TransformerException;
import java.util.TooManyListenersException;
import java.util.Vector;
import org.apache.xalan.transformer.TransformerImpl;

public class TraceManager
{
    private TransformerImpl m_transformer;
    private Vector m_traceListeners;
    
    public TraceManager(final TransformerImpl transformer) {
        this.m_traceListeners = null;
        this.m_transformer = transformer;
    }
    
    public void addTraceListener(final TraceListener tl) throws TooManyListenersException {
        TransformerImpl.S_DEBUG = true;
        if (this.m_traceListeners == null) {
            this.m_traceListeners = new Vector();
        }
        this.m_traceListeners.addElement(tl);
    }
    
    public void fireGenerateEvent(final GenerateEvent te) {
        if (this.m_traceListeners != null) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                tl.generated(te);
            }
        }
    }
    
    public void fireSelectedEvent(final SelectionEvent se) throws TransformerException {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                tl.selected(se);
            }
        }
    }
    
    public void fireSelectedEvent(final Node sourceNode, final ElemTemplateElement styleNode, final String attributeName, final XPath xpath, final XObject selection) throws TransformerException {
        if (this.hasTraceListeners()) {
            this.fireSelectedEvent(new SelectionEvent(this.m_transformer, sourceNode, styleNode, attributeName, xpath, selection));
        }
    }
    
    public void fireTraceEvent(final TracerEvent te) {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                tl.trace(te);
            }
        }
    }
    
    public void fireTraceEvent(final Node sourceNode, final QName mode, final ElemTemplateElement styleNode) {
        if (this.hasTraceListeners()) {
            this.fireTraceEvent(new TracerEvent(this.m_transformer, sourceNode, mode, styleNode));
        }
    }
    
    public boolean hasTraceListeners() {
        return this.m_traceListeners != null;
    }
    
    public void removeTraceListener(final TraceListener tl) {
        if (this.m_traceListeners != null) {
            this.m_traceListeners.removeElement(tl);
        }
    }
}

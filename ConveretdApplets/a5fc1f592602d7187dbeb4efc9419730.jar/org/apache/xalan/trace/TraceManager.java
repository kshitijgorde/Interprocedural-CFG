// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import java.lang.reflect.Method;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPath;
import org.w3c.dom.Node;
import org.apache.xalan.templates.ElemTemplateElement;
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
        if (null == this.m_traceListeners) {
            this.m_traceListeners = new Vector();
        }
        this.m_traceListeners.addElement(tl);
    }
    
    public void removeTraceListener(final TraceListener tl) {
        if (null != this.m_traceListeners) {
            this.m_traceListeners.removeElement(tl);
            if (0 == this.m_traceListeners.size()) {
                this.m_traceListeners = null;
            }
        }
    }
    
    public void fireGenerateEvent(final GenerateEvent te) {
        if (null != this.m_traceListeners) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                tl.generated(te);
            }
        }
    }
    
    public boolean hasTraceListeners() {
        return null != this.m_traceListeners;
    }
    
    public void fireTraceEvent(final ElemTemplateElement styleNode) {
        if (this.hasTraceListeners()) {
            final int sourceNode = this.m_transformer.getXPathContext().getCurrentNode();
            final Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
            this.fireTraceEvent(new TracerEvent(this.m_transformer, source, this.m_transformer.getMode(), styleNode));
        }
    }
    
    public void fireTraceEndEvent(final ElemTemplateElement styleNode) {
        if (this.hasTraceListeners()) {
            final int sourceNode = this.m_transformer.getXPathContext().getCurrentNode();
            final Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
            this.fireTraceEndEvent(new TracerEvent(this.m_transformer, source, this.m_transformer.getMode(), styleNode));
        }
    }
    
    public void fireTraceEndEvent(final TracerEvent te) {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx2) {
                    ((TraceListenerEx2)tl).traceEnd(te);
                }
            }
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
    
    public void fireSelectedEvent(final int sourceNode, final ElemTemplateElement styleNode, final String attributeName, final XPath xpath, final XObject selection) throws TransformerException {
        if (this.hasTraceListeners()) {
            final Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
            this.fireSelectedEvent(new SelectionEvent(this.m_transformer, source, styleNode, attributeName, xpath, selection));
        }
    }
    
    public void fireSelectedEndEvent(final int sourceNode, final ElemTemplateElement styleNode, final String attributeName, final XPath xpath, final XObject selection) throws TransformerException {
        if (this.hasTraceListeners()) {
            final Node source = this.m_transformer.getXPathContext().getDTM(sourceNode).getNode(sourceNode);
            this.fireSelectedEndEvent(new EndSelectionEvent(this.m_transformer, source, styleNode, attributeName, xpath, selection));
        }
    }
    
    public void fireSelectedEndEvent(final EndSelectionEvent se) throws TransformerException {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx) {
                    ((TraceListenerEx)tl).selectEnd(se);
                }
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
    
    public void fireExtensionEndEvent(final Method method, final Object instance, final Object[] arguments) {
        final ExtensionEvent ee = new ExtensionEvent(this.m_transformer, method, instance, arguments);
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx3) {
                    ((TraceListenerEx3)tl).extensionEnd(ee);
                }
            }
        }
    }
    
    public void fireExtensionEvent(final Method method, final Object instance, final Object[] arguments) {
        final ExtensionEvent ee = new ExtensionEvent(this.m_transformer, method, instance, arguments);
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx3) {
                    ((TraceListenerEx3)tl).extension(ee);
                }
            }
        }
    }
    
    public void fireExtensionEndEvent(final ExtensionEvent ee) {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx3) {
                    ((TraceListenerEx3)tl).extensionEnd(ee);
                }
            }
        }
    }
    
    public void fireExtensionEvent(final ExtensionEvent ee) {
        if (this.hasTraceListeners()) {
            for (int nListeners = this.m_traceListeners.size(), i = 0; i < nListeners; ++i) {
                final TraceListener tl = this.m_traceListeners.elementAt(i);
                if (tl instanceof TraceListenerEx3) {
                    ((TraceListenerEx3)tl).extension(ee);
                }
            }
        }
    }
}

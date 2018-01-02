// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.processor;

import java.io.IOException;
import java.io.ObjectInputStream;
import org.xml.sax.helpers.NamespaceSupport;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.templates.ElemTemplateElement;
import javax.xml.transform.SourceLocator;
import org.apache.xml.utils.SAXSourceLocator;
import java.util.Hashtable;
import java.io.Serializable;
import org.apache.xalan.templates.ElemTemplate;

public abstract class CompiledTemplate extends ElemTemplate implements Serializable
{
    protected Object[] m_interpretArray;
    protected transient Hashtable m_nsThreadContexts;
    
    public CompiledTemplate(final ElemTemplate original, final int lineNumber, final int columnNumber, final String publicId, final String systemId, final Object[] interpretArray) {
        this.m_nsThreadContexts = new Hashtable();
        final SAXSourceLocator locator = new SAXSourceLocator();
        locator.setLineNumber(lineNumber);
        locator.setColumnNumber(columnNumber);
        locator.setPublicId(publicId);
        locator.setSystemId(systemId);
        this.setLocaterInfo(locator);
        this.m_interpretArray = interpretArray;
        this.setMatch(original.getMatch());
        this.setMode(original.getMode());
        this.setName(original.getName());
        this.setPriority(original.getPriority());
        this.setStylesheet(original.getStylesheet());
        for (int i = 0; i < this.m_interpretArray.length; ++i) {
            if (this.m_interpretArray[i] instanceof ElemTemplateElement) {
                final ElemTemplateElement ete = (ElemTemplateElement)this.m_interpretArray[i];
                this.appendChild(ete.getParentElem().removeChild(ete));
            }
        }
    }
    
    public abstract void execute(final TransformerImpl p0, final Node p1, final QName p2) throws TransformerException;
    
    public String getNamespaceForPrefix(final String nsPrefix) {
        String nsuri = "";
        final NamespaceSupport nsSupport = this.m_nsThreadContexts.get(Thread.currentThread());
        if (nsSupport != null) {
            nsuri = nsSupport.getURI(nsPrefix);
        }
        if (nsuri == null || nsuri.length() == 0) {
            nsuri = super.m_parentNode.getNamespaceForPrefix(nsPrefix);
        }
        return nsuri;
    }
    
    public boolean isCompiledTemplate() {
        return true;
    }
    
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.m_nsThreadContexts = new Hashtable();
    }
}

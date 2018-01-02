// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.apache.xpath.XPathContext;
import org.apache.xml.utils.FastStringBuffer;

public abstract class DocImpl extends Parent
{
    FastStringBuffer m_chars;
    public Exception m_exceptionThrown;
    XPathContext m_xpathContext;
    SourceTreeHandler m_sourceTreeHandler;
    int m_docOrderCount;
    boolean m_useMultiThreading;
    
    public DocImpl() {
        super(null);
        this.m_exceptionThrown = null;
        this.m_xpathContext = null;
        this.m_docOrderCount = 1;
        this.m_useMultiThreading = false;
        this.m_chars = new FastStringBuffer(8192);
    }
    
    public DocImpl(final int charBufSize) {
        super(null);
        this.m_exceptionThrown = null;
        this.m_xpathContext = null;
        this.m_docOrderCount = 1;
        this.m_useMultiThreading = false;
        this.m_chars = new FastStringBuffer(charBufSize);
    }
    
    protected int getDocOrderCount() {
        return this.m_docOrderCount;
    }
    
    SourceTreeHandler getSourceTreeHandler() {
        return this.m_sourceTreeHandler;
    }
    
    public boolean getUseMultiThreading() {
        return this.m_useMultiThreading;
    }
    
    protected void incrementDocOrderCount() {
        ++this.m_docOrderCount;
    }
    
    void setSourceTreeHandler(final SourceTreeHandler h) {
        this.m_sourceTreeHandler = h;
    }
    
    public void setUseMultiThreading(final boolean b) {
        this.m_useMultiThreading = b;
    }
}

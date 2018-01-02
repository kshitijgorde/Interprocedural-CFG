// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.xsltc.trax;

import org.apache.xml.dtm.DTMWSFilter;
import org.apache.xalan.xsltc.dom.DOMWSFilter;
import org.apache.xalan.xsltc.StripFilter;
import javax.xml.transform.stream.StreamSource;
import org.xml.sax.SAXException;
import org.apache.xalan.xsltc.compiler.util.ErrorMsg;
import org.apache.xml.dtm.DTMManager;
import org.apache.xalan.xsltc.dom.SAXImpl;
import org.apache.xalan.xsltc.DOM;
import org.apache.xalan.xsltc.runtime.AbstractTranslet;
import org.apache.xalan.xsltc.dom.XSLTCDTMManager;
import javax.xml.transform.Source;

public final class XSLTCSource implements Source
{
    private String _systemId;
    private Source _source;
    private ThreadLocal _dom;
    
    public XSLTCSource(final String systemId) {
        this._systemId = null;
        this._source = null;
        this._dom = new ThreadLocal();
        this._systemId = systemId;
    }
    
    public XSLTCSource(final Source source) {
        this._systemId = null;
        this._source = null;
        this._dom = new ThreadLocal();
        this._source = source;
    }
    
    public void setSystemId(final String systemId) {
        this._systemId = systemId;
        if (this._source != null) {
            this._source.setSystemId(systemId);
        }
    }
    
    public String getSystemId() {
        if (this._source != null) {
            return this._source.getSystemId();
        }
        return this._systemId;
    }
    
    protected DOM getDOM(XSLTCDTMManager dtmManager, final AbstractTranslet translet) throws SAXException {
        SAXImpl idom = this._dom.get();
        if (idom != null) {
            if (dtmManager != null) {
                idom.migrateTo(dtmManager);
            }
        }
        else {
            Source source = this._source;
            if (source == null) {
                if (this._systemId == null || this._systemId.length() <= 0) {
                    final ErrorMsg err = new ErrorMsg("XSLTC_SOURCE_ERR");
                    throw new SAXException(err.toString());
                }
                source = new StreamSource(this._systemId);
            }
            DOMWSFilter wsfilter = null;
            if (translet != null && translet instanceof StripFilter) {
                wsfilter = new DOMWSFilter(translet);
            }
            final boolean hasIdCall = translet != null && translet.hasIdCall();
            if (dtmManager == null) {
                dtmManager = XSLTCDTMManager.newInstance();
            }
            idom = (SAXImpl)dtmManager.getDTM(source, true, wsfilter, false, false, hasIdCall);
            final String systemId = this.getSystemId();
            if (systemId != null) {
                idom.setDocumentURI(systemId);
            }
            this._dom.set(idom);
        }
        return idom;
    }
}

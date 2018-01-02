// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.dom;

import org.w3c.dom.DOMException;
import org.apache.xerces.dom3.as.DOMASWriter;
import org.apache.xerces.parsers.DOMASBuilderImpl;
import org.apache.xerces.dom3.as.DOMASBuilder;
import org.apache.xerces.dom3.as.ASModel;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.dom3.as.DOMImplementationAS;

public class ASDOMImplementationImpl extends DOMImplementationImpl implements DOMImplementationAS
{
    static ASDOMImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return ASDOMImplementationImpl.singleton;
    }
    
    public ASModel createAS(final boolean isNamespaceAware) {
        return new ASModelImpl(isNamespaceAware);
    }
    
    public DOMASBuilder createDOMASBuilder() {
        return new DOMASBuilderImpl();
    }
    
    public DOMASWriter createDOMASWriter() {
        final String msg = DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "NOT_SUPPORTED_ERR", null);
        throw new DOMException((short)9, msg);
    }
    
    static {
        ASDOMImplementationImpl.singleton = new ASDOMImplementationImpl();
    }
}

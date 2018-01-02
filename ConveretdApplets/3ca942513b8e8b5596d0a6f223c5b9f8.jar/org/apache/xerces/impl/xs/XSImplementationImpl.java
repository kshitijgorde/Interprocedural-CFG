// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.impl.xs;

import org.apache.xerces.impl.xs.util.StringListImpl;
import org.apache.xerces.xs.XSException;
import org.apache.xerces.dom.DOMMessageFormatter;
import org.apache.xerces.xs.XSLoader;
import org.apache.xerces.xs.StringList;
import org.w3c.dom.DOMImplementation;
import org.apache.xerces.xs.XSImplementation;
import org.apache.xerces.dom.CoreDOMImplementationImpl;

public class XSImplementationImpl extends CoreDOMImplementationImpl implements XSImplementation
{
    static XSImplementationImpl singleton;
    
    public static DOMImplementation getDOMImplementation() {
        return XSImplementationImpl.singleton;
    }
    
    public boolean hasFeature(final String s, final String s2) {
        return (s.equalsIgnoreCase("XS-Loader") && (s2 == null || s2.equals("1.0"))) || super.hasFeature(s, s2);
    }
    
    public XSLoader createXSLoader(final StringList list) throws XSException {
        final XSLoaderImpl xsLoaderImpl = new XSLoaderImpl();
        if (list == null) {
            return xsLoaderImpl;
        }
        for (int i = 0; i < list.getLength(); ++i) {
            if (!list.item(i).equals("1.0")) {
                throw new XSException((short)1, DOMMessageFormatter.formatMessage("http://www.w3.org/dom/DOMTR", "FEATURE_NOT_SUPPORTED", new Object[] { list.item(i) }));
            }
        }
        return xsLoaderImpl;
    }
    
    public StringList getRecognizedVersions() {
        return new StringListImpl(new String[] { "1.0" }, 1);
    }
    
    static {
        XSImplementationImpl.singleton = new XSImplementationImpl();
    }
}

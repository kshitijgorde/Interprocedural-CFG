// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.ExtensionsProvider;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncExtElementAvailable extends FunctionOneArg
{
    static final long serialVersionUID = -472533699257968546L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String fullName = super.m_arg0.execute(xctxt).str();
        final int indexOfNSSep = fullName.indexOf(58);
        String namespace;
        String methName;
        if (indexOfNSSep < 0) {
            final String prefix = "";
            namespace = "http://www.w3.org/1999/XSL/Transform";
            methName = fullName;
        }
        else {
            final String prefix = fullName.substring(0, indexOfNSSep);
            namespace = xctxt.getNamespaceContext().getNamespaceForPrefix(prefix);
            if (null == namespace) {
                return XBoolean.S_FALSE;
            }
            methName = fullName.substring(indexOfNSSep + 1);
        }
        Label_0147: {
            if (!namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
                if (!namespace.equals("http://xml.apache.org/xalan")) {
                    break Label_0147;
                }
            }
            try {
                final TransformerImpl transformer = (TransformerImpl)xctxt.getOwnerObject();
                return transformer.getStylesheet().getAvailableElements().containsKey(new QName(namespace, methName)) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
            }
            catch (Exception e) {
                return XBoolean.S_FALSE;
            }
        }
        final ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
        return extProvider.elementAvailable(namespace, methName) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}

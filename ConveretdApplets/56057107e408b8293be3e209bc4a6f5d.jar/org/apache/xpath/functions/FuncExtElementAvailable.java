// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xalan.extensions.ExtensionsTable;
import org.apache.xml.utils.QName;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncExtElementAvailable extends FunctionOneArg
{
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
            if (namespace == null) {
                return XBoolean.S_FALSE;
            }
            methName = fullName.substring(indexOfNSSep + 1);
        }
        Label_0145: {
            if (!namespace.equals("http://www.w3.org/1999/XSL/Transform")) {
                if (!namespace.equals("http://xml.apache.org/xslt")) {
                    break Label_0145;
                }
            }
            try {
                final TransformerImpl transformer = (TransformerImpl)xctxt.getOwnerObject();
                return transformer.getStylesheet().getAvailableElements().containsKey(new QName(namespace, methName)) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
            }
            catch (Exception ex) {
                return XBoolean.S_FALSE;
            }
        }
        final ExtensionsTable etable = xctxt.getExtensionsTable();
        return etable.elementAvailable(namespace, methName) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}

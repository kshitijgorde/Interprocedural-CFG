// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncNamespace extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Node context = this.getArg0AsNode(xctxt);
        if (context != null) {
            final int t = context.getNodeType();
            String s;
            if (t == 1) {
                s = xctxt.getDOMHelper().getNamespaceOfNode(context);
            }
            else {
                if (t != 2) {
                    return XString.EMPTYSTRING;
                }
                s = context.getNodeName();
                if (s.startsWith("xmlns:") || s.equals("xmlns")) {
                    return XString.EMPTYSTRING;
                }
                s = xctxt.getDOMHelper().getNamespaceOfNode(context);
            }
            return (s == null) ? XString.EMPTYSTRING : new XString(s);
        }
        return XString.EMPTYSTRING;
    }
}

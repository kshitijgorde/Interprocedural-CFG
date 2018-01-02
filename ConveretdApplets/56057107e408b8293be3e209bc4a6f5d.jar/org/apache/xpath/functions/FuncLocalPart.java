// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncLocalPart extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Node context = this.getArg0AsNode(xctxt);
        String s = (context != null) ? xctxt.getDOMHelper().getLocalNameOfNode(context) : "";
        if (s.startsWith("#") || s.equals("xmlns")) {
            s = "";
        }
        return new XString(s);
    }
}

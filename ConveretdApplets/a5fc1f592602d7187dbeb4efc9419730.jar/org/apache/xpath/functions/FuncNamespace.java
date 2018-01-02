// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncNamespace extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int context = this.getArg0AsNode(xctxt);
        if (context != -1) {
            final DTM dtm = xctxt.getDTM(context);
            final int t = dtm.getNodeType(context);
            String s;
            if (t == 1) {
                s = dtm.getNamespaceURI(context);
            }
            else {
                if (t != 2) {
                    return XString.EMPTYSTRING;
                }
                s = dtm.getNodeName(context);
                if (s.startsWith("xmlns:") || s.equals("xmlns")) {
                    return XString.EMPTYSTRING;
                }
                s = dtm.getNamespaceURI(context);
            }
            return (null == s) ? XString.EMPTYSTRING : new XString(s);
        }
        return XString.EMPTYSTRING;
    }
}

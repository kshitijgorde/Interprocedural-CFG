// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xml.utils.QName;
import org.apache.xpath.objects.XString;
import org.w3c.dom.Attr;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncQname extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Node context = this.getArg0AsNode(xctxt);
        XObject val;
        if (context != null) {
            final short ntype = context.getNodeType();
            if (ntype == 2) {
                String qname = ((Attr)context).getName();
                if (xctxt.getDOMHelper().isNamespaceNode(context)) {
                    if (qname.equals("xmlns")) {
                        val = XString.EMPTYSTRING;
                    }
                    else {
                        qname = QName.getLocalPart(qname);
                        val = ((qname == null) ? XString.EMPTYSTRING : new XString(qname));
                    }
                }
                else {
                    val = new XString(qname);
                }
            }
            else if (ntype == 1 || ntype == 7) {
                final String qname = context.getNodeName();
                val = new XString(qname);
            }
            else {
                val = XString.EMPTYSTRING;
            }
        }
        else {
            val = XString.EMPTYSTRING;
        }
        return val;
    }
}

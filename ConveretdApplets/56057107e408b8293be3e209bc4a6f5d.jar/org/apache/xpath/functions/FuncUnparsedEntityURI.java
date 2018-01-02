// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XString;
import org.w3c.dom.Document;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncUnparsedEntityURI extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String name = super.m_arg0.execute(xctxt).str();
        final Node context = xctxt.getCurrentNode();
        final Document doc = (Document)((context.getNodeType() == 9) ? context : context.getOwnerDocument());
        final String uri = xctxt.getDOMHelper().getUnparsedEntityURI(name, doc);
        return new XString(uri);
    }
}

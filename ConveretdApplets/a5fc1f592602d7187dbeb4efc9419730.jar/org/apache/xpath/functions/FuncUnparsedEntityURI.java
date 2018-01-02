// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncUnparsedEntityURI extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String name = super.m_arg0.execute(xctxt).str();
        final int context = xctxt.getCurrentNode();
        final DTM dtm = xctxt.getDTM(context);
        final int doc = dtm.getDocument();
        final String uri = dtm.getUnparsedEntityURI(name);
        return new XString(uri);
    }
}

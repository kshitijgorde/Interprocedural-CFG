// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncCount extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final DTMIterator nl = super.m_arg0.asIterator(xctxt, xctxt.getCurrentNode());
        final int i = nl.getLength();
        nl.detach();
        return new XNumber(i);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncCeiling extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return new XNumber(Math.ceil(super.m_arg0.execute(xctxt).num()));
    }
}

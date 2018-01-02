// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncContains extends Function2Args
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String s1 = super.m_arg0.execute(xctxt).str();
        final String s2 = super.m_arg1.execute(xctxt).str();
        if (s1.length() == 0 && s2.length() == 0) {
            return XBoolean.S_TRUE;
        }
        final int index = s1.indexOf(s2);
        return (index > -1) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}

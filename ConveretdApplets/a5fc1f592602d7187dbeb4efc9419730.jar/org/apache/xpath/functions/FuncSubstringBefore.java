// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSubstringBefore extends Function2Args
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final String s1 = super.m_arg0.execute(xctxt).str();
        final String s2 = super.m_arg1.execute(xctxt).str();
        final int index = s1.indexOf(s2);
        return (-1 == index) ? XString.EMPTYSTRING : new XString(s1.substring(0, index));
    }
}

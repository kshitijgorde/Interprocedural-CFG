// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.utils.XMLString;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSubstringAfter extends Function2Args
{
    static final long serialVersionUID = -8119731889862512194L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XMLString s1 = super.m_arg0.execute(xctxt).xstr();
        final XMLString s2 = super.m_arg1.execute(xctxt).xstr();
        final int index = s1.indexOf(s2);
        return (XObject)((-1 == index) ? XString.EMPTYSTRING : s1.substring(index + s2.length()));
    }
}

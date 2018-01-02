// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncStartsWith extends Function2Args
{
    static final long serialVersionUID = 2194585774699567928L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return super.m_arg0.execute(xctxt).xstr().startsWith(super.m_arg1.execute(xctxt).xstr()) ? XBoolean.S_TRUE : XBoolean.S_FALSE;
    }
}

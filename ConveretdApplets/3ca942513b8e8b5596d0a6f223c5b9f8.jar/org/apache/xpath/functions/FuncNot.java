// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XBoolean;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncNot extends FunctionOneArg
{
    static final long serialVersionUID = 7299699961076329790L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return super.m_arg0.execute(xctxt).bool() ? XBoolean.S_FALSE : XBoolean.S_TRUE;
    }
}

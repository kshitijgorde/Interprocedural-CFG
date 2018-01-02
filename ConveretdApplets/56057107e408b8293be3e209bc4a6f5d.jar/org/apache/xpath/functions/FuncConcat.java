// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncConcat extends FunctionMultiArgs
{
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum < 2) {
            throw new WrongNumberArgsException(">1");
        }
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final StringBuffer sb = new StringBuffer();
        sb.append(super.m_arg0.execute(xctxt).str());
        sb.append(super.m_arg1.execute(xctxt).str());
        if (super.m_arg2 != null) {
            sb.append(super.m_arg2.execute(xctxt).str());
        }
        if (super.m_args != null) {
            for (int i = 0; i < super.m_args.length; ++i) {
                sb.append(super.m_args[i].execute(xctxt).str());
            }
        }
        return new XString(sb.toString());
    }
}

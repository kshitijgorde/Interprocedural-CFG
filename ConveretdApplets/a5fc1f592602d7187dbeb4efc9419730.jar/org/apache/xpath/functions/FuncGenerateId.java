// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncGenerateId extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int which = this.getArg0AsNode(xctxt);
        if (-1 != which) {
            return new XString("N" + Integer.toHexString(which).toUpperCase());
        }
        return XString.EMPTYSTRING;
    }
}

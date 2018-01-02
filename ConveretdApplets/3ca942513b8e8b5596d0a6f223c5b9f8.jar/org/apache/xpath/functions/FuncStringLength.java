// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncStringLength extends FunctionDef1Arg
{
    static final long serialVersionUID = -159616417996519839L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return new XNumber(this.getArg0AsString(xctxt).length());
    }
}

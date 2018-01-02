// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncString extends FunctionDef1Arg
{
    static final long serialVersionUID = -2206677149497712883L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        return (XString)this.getArg0AsString(xctxt);
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTM;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncLocalPart extends FunctionDef1Arg
{
    static final long serialVersionUID = 7591798770325814746L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int context = this.getArg0AsNode(xctxt);
        if (-1 == context) {
            return XString.EMPTYSTRING;
        }
        final DTM dtm = xctxt.getDTM(context);
        final String s = (context != -1) ? dtm.getLocalName(context) : "";
        if (s.startsWith("#") || s.equals("xmlns")) {
            return XString.EMPTYSTRING;
        }
        return new XString(s);
    }
}

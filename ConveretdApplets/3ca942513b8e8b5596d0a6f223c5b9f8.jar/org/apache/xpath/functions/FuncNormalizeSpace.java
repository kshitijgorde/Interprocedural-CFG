// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.xml.sax.SAXException;
import org.apache.xml.dtm.DTM;
import org.xml.sax.ContentHandler;
import javax.xml.transform.TransformerException;
import org.apache.xml.utils.XMLString;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncNormalizeSpace extends FunctionDef1Arg
{
    static final long serialVersionUID = -3377956872032190880L;
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final XMLString s1 = this.getArg0AsString(xctxt);
        return (XString)s1.fixWhiteSpace(true, true, false);
    }
    
    public void executeCharsToContentHandler(final XPathContext xctxt, final ContentHandler handler) throws TransformerException, SAXException {
        if (this.Arg0IsNodesetExpr()) {
            final int node = this.getArg0AsNode(xctxt);
            if (-1 != node) {
                final DTM dtm = xctxt.getDTM(node);
                dtm.dispatchCharactersEvents(node, handler, true);
            }
        }
        else {
            final XObject obj = this.execute(xctxt);
            obj.dispatchCharactersEvents(handler);
        }
    }
}

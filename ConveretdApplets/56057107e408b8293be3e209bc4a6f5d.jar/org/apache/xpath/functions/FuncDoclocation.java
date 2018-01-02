// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.apache.xpath.objects.XString;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncDoclocation extends FunctionDef1Arg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        Node whereNode = this.getArg0AsNode(xctxt);
        String fileLocation = null;
        if (whereNode != null) {
            if (whereNode.getNodeType() == 11) {
                whereNode = whereNode.getFirstChild();
            }
            if (whereNode != null) {
                final Document owner = whereNode.getOwnerDocument();
                fileLocation = xctxt.getSourceTreeManager().findURIFromDoc(owner);
            }
        }
        return new XString((fileLocation != null) ? fileLocation : "");
    }
}

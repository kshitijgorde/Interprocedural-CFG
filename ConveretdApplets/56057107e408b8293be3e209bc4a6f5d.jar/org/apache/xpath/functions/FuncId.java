// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import java.util.StringTokenizer;
import javax.xml.transform.TransformerException;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.NodeSet;
import org.w3c.dom.Node;
import org.apache.xml.utils.StringVector;
import org.apache.xpath.DOMHelper;
import org.apache.xpath.objects.XNodeSet;
import org.w3c.dom.Document;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncId extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Node context = xctxt.getCurrentNode();
        final Document docContext = (Document)((context.getNodeType() == 9) ? context : context.getOwnerDocument());
        if (docContext == null) {
            this.error(xctxt, 4, null);
        }
        final XObject arg = super.m_arg0.execute(xctxt);
        final int argType = arg.getType();
        final XNodeSet nodes = new XNodeSet();
        final NodeSet nodeSet = nodes.mutableNodeset();
        if (argType == 4) {
            final NodeIterator ni = arg.nodeset();
            StringVector usedrefs = null;
            String refval;
            for (Node pos = ni.nextNode(); pos != null; pos = ni.nextNode(), usedrefs = this.getNodesByID(xctxt, docContext, refval, usedrefs, nodeSet, pos != null)) {
                refval = DOMHelper.getNodeData(pos);
            }
        }
        else {
            if (argType == -1) {
                return nodes;
            }
            final String refval2 = arg.str();
            this.getNodesByID(xctxt, docContext, refval2, null, nodeSet, false);
        }
        return nodes;
    }
    
    private StringVector getNodesByID(final XPathContext xctxt, final Document docContext, final String refval, StringVector usedrefs, final NodeSet nodeSet, final boolean mayBeMore) {
        if (refval != null) {
            String ref = null;
            final DOMHelper dh = xctxt.getDOMHelper();
            final StringTokenizer tokenizer = new StringTokenizer(refval);
            boolean hasMore = tokenizer.hasMoreTokens();
            while (hasMore) {
                ref = tokenizer.nextToken();
                hasMore = tokenizer.hasMoreTokens();
                if (usedrefs != null && usedrefs.contains(ref)) {
                    ref = null;
                }
                else {
                    final Node node = dh.getElementByID(ref, docContext);
                    if (node != null) {
                        nodeSet.addNodeInDocOrder(node, xctxt);
                    }
                    if (ref == null || (!hasMore && !mayBeMore)) {
                        continue;
                    }
                    if (usedrefs == null) {
                        usedrefs = new StringVector();
                    }
                    usedrefs.addElement(ref);
                }
            }
        }
        return usedrefs;
    }
}

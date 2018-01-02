// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.apache.xml.dtm.DTMIterator;
import org.apache.xpath.objects.XNodeSet;
import org.apache.xpath.objects.XObject;
import org.apache.xml.dtm.DTM;
import java.util.StringTokenizer;
import org.apache.xpath.NodeSetDTM;
import org.apache.xml.utils.StringVector;
import org.apache.xpath.XPathContext;

public class FuncId extends FunctionOneArg
{
    static final long serialVersionUID = 8930573966143567310L;
    
    private StringVector getNodesByID(final XPathContext xctxt, final int docContext, final String refval, StringVector usedrefs, final NodeSetDTM nodeSet, final boolean mayBeMore) {
        if (null != refval) {
            String ref = null;
            final StringTokenizer tokenizer = new StringTokenizer(refval);
            boolean hasMore = tokenizer.hasMoreTokens();
            final DTM dtm = xctxt.getDTM(docContext);
            while (hasMore) {
                ref = tokenizer.nextToken();
                hasMore = tokenizer.hasMoreTokens();
                if (null != usedrefs && usedrefs.contains(ref)) {
                    ref = null;
                }
                else {
                    final int node = dtm.getElementById(ref);
                    if (-1 != node) {
                        nodeSet.addNodeInDocOrder(node, xctxt);
                    }
                    if (null == ref || (!hasMore && !mayBeMore)) {
                        continue;
                    }
                    if (null == usedrefs) {
                        usedrefs = new StringVector();
                    }
                    usedrefs.addElement(ref);
                }
            }
        }
        return usedrefs;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final int context = xctxt.getCurrentNode();
        final DTM dtm = xctxt.getDTM(context);
        final int docContext = dtm.getDocument();
        if (-1 == docContext) {
            this.error(xctxt, "ER_CONTEXT_HAS_NO_OWNERDOC", null);
        }
        final XObject arg = super.m_arg0.execute(xctxt);
        final int argType = arg.getType();
        final XNodeSet nodes = new XNodeSet(xctxt.getDTMManager());
        final NodeSetDTM nodeSet = nodes.mutableNodeset();
        if (4 == argType) {
            final DTMIterator ni = arg.iter();
            StringVector usedrefs = null;
            String refval;
            for (int pos = ni.nextNode(); -1 != pos; pos = ni.nextNode(), usedrefs = this.getNodesByID(xctxt, docContext, refval, usedrefs, nodeSet, -1 != pos)) {
                final DTM ndtm = ni.getDTM(pos);
                refval = ndtm.getStringValue(pos).toString();
            }
        }
        else {
            if (-1 == argType) {
                return nodes;
            }
            final String refval2 = arg.str();
            this.getNodesByID(xctxt, docContext, refval2, null, nodeSet, false);
        }
        return nodes;
    }
}

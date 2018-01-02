// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.traversal.NodeIterator;
import org.apache.xpath.objects.XNumber;
import org.apache.xpath.objects.XString;
import org.apache.xpath.DOMHelper;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;

public class FuncSum extends FunctionOneArg
{
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final NodeIterator nodes = super.m_arg0.execute(xctxt).nodeset();
        double sum = 0.0;
        Node pos;
        while ((pos = nodes.nextNode()) != null) {
            final String s = DOMHelper.getNodeData(pos);
            if (s != null) {
                sum += XString.castToNum(s);
            }
        }
        return new XNumber(sum);
    }
}

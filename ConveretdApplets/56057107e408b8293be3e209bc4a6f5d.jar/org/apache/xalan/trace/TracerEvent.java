// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.trace;

import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.apache.xml.utils.QName;
import org.w3c.dom.Node;
import org.apache.xalan.transformer.TransformerImpl;
import org.apache.xalan.templates.ElemTemplateElement;
import java.util.EventListener;

public class TracerEvent implements EventListener
{
    public final ElemTemplateElement m_styleNode;
    public final TransformerImpl m_processor;
    public final Node m_sourceNode;
    public final QName m_mode;
    
    public TracerEvent(final TransformerImpl processor, final Node sourceNode, final QName mode, final ElemTemplateElement styleNode) {
        this.m_processor = processor;
        this.m_sourceNode = sourceNode;
        this.m_mode = mode;
        this.m_styleNode = styleNode;
    }
    
    public static String printNode(final Node n) {
        String r = String.valueOf(n.hashCode()) + " ";
        if (n instanceof Element) {
            r = String.valueOf(r) + "<" + n.getNodeName();
            for (Node c = n.getFirstChild(); c != null; c = c.getNextSibling()) {
                if (c instanceof Attr) {
                    r = String.valueOf(r) + printNode(c) + " ";
                }
            }
            r = String.valueOf(r) + ">";
        }
        else if (n instanceof Attr) {
            r = String.valueOf(r) + n.getNodeName() + "=" + n.getNodeValue();
        }
        else {
            r = String.valueOf(r) + n.getNodeName();
        }
        return r;
    }
    
    public static String printNodeList(final NodeList l) {
        String r = String.valueOf(l.hashCode()) + "[";
        int len;
        int i;
        for (len = l.getLength() - 1, i = 0; i < len; ++i) {
            final Node n = l.item(i);
            if (n != null) {
                r = String.valueOf(r) + printNode(n) + ", ";
            }
        }
        if (i == len) {
            final Node n = l.item(len);
            if (n != null) {
                r = String.valueOf(r) + printNode(n);
            }
        }
        return String.valueOf(r) + "]";
    }
}

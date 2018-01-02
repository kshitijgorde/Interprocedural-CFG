// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.w3c.dom.NamedNodeMap;
import org.apache.xpath.DOMHelper;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.xml.sax.ContentHandler;
import org.w3c.dom.Node;
import org.apache.xml.utils.TreeWalker;

public class TreeWalker2Result extends TreeWalker
{
    TransformerImpl m_transformer;
    ResultTreeHandler m_handler;
    Node m_startNode;
    
    public TreeWalker2Result(final TransformerImpl transformer, final ResultTreeHandler handler) {
        super(handler);
        this.m_transformer = transformer;
        this.m_handler = handler;
    }
    
    protected void startNode(final Node node) throws SAXException {
        try {
            if (node.getNodeType() == 1 && this.m_startNode == node) {
                final DOMHelper dhelper = this.m_transformer.getXPathContext().getDOMHelper();
                final String elemName = node.getNodeName();
                final String localName = dhelper.getLocalNameOfNode(node);
                final String namespace = dhelper.getNamespaceOfNode(node);
                this.m_handler.startElement(namespace, localName, elemName);
                for (Node parent = node; parent != null; parent = parent.getParentNode()) {
                    if (parent.getNodeType() == 1) {
                        final NamedNodeMap atts = ((Element)parent).getAttributes();
                        for (int n = atts.getLength(), i = 0; i < n; ++i) {
                            String nsDeclPrefix = null;
                            final Attr attr = (Attr)atts.item(i);
                            final String name = attr.getName();
                            final String value = attr.getValue();
                            if (name.startsWith("xmlns:")) {
                                nsDeclPrefix = name.substring(name.indexOf(":") + 1);
                            }
                            else if (name.equals("xmlns")) {
                                nsDeclPrefix = "";
                            }
                            if (nsDeclPrefix != null || node == parent) {
                                this.m_handler.addAttribute(dhelper.getNamespaceOfNode(attr), dhelper.getLocalNameOfNode(attr), name, "CDATA", value);
                            }
                        }
                    }
                }
            }
            else {
                super.startNode(node);
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
    
    public void traverse(final Node pos) throws SAXException {
        super.traverse(this.m_startNode = pos);
    }
}

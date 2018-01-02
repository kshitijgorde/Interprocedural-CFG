// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.transformer;

import org.apache.xpath.XPathContext;
import javax.xml.transform.TransformerException;
import org.apache.xalan.serialize.SerializerUtils;
import org.xml.sax.SAXException;
import org.apache.xml.dtm.DTM;
import org.xml.sax.ContentHandler;
import org.apache.xml.serializer.SerializationHandler;
import org.apache.xml.dtm.ref.DTMTreeWalker;

public class TreeWalker2Result extends DTMTreeWalker
{
    TransformerImpl m_transformer;
    SerializationHandler m_handler;
    int m_startNode;
    
    public TreeWalker2Result(final TransformerImpl transformer, final SerializationHandler handler) {
        super(handler, null);
        this.m_transformer = transformer;
        this.m_handler = handler;
    }
    
    public void traverse(final int pos) throws SAXException {
        super.m_dtm = this.m_transformer.getXPathContext().getDTM(pos);
        super.traverse(this.m_startNode = pos);
    }
    
    protected void endNode(final int node) throws SAXException {
        super.endNode(node);
        if (1 == super.m_dtm.getNodeType(node)) {
            this.m_transformer.getXPathContext().popCurrentNode();
        }
    }
    
    protected void startNode(final int node) throws SAXException {
        final XPathContext xcntxt = this.m_transformer.getXPathContext();
        try {
            if (1 == super.m_dtm.getNodeType(node)) {
                xcntxt.pushCurrentNode(node);
                if (this.m_startNode != node) {
                    super.startNode(node);
                }
                else {
                    final String elemName = super.m_dtm.getNodeName(node);
                    final String localName = super.m_dtm.getLocalName(node);
                    final String namespace = super.m_dtm.getNamespaceURI(node);
                    this.m_handler.startElement(namespace, localName, elemName);
                    final boolean hasNSDecls = false;
                    final DTM dtm = super.m_dtm;
                    for (int ns = dtm.getFirstNamespaceNode(node, true); -1 != ns; ns = dtm.getNextNamespaceNode(node, ns, true)) {
                        SerializerUtils.ensureNamespaceDeclDeclared(this.m_handler, dtm, ns);
                    }
                    for (int attr = dtm.getFirstAttribute(node); -1 != attr; attr = dtm.getNextAttribute(attr)) {
                        SerializerUtils.addAttribute(this.m_handler, attr);
                    }
                }
            }
            else {
                xcntxt.pushCurrentNode(node);
                super.startNode(node);
                xcntxt.popCurrentNode();
            }
        }
        catch (TransformerException te) {
            throw new SAXException(te);
        }
    }
}

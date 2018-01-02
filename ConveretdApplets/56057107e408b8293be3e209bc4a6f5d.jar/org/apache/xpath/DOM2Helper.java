// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath;

import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import org.apache.xml.utils.DefaultErrorHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

public class DOM2Helper extends DOMHelper
{
    private Document m_doc;
    
    public void checkNode(final Node node) throws TransformerException {
    }
    
    public Document getDocument() {
        return this.m_doc;
    }
    
    public Element getElementByID(final String id, final Document doc) {
        return doc.getElementById(id);
    }
    
    public String getLocalNameOfNode(final Node n) {
        final String name = n.getLocalName();
        return (name == null) ? super.getLocalNameOfNode(n) : name;
    }
    
    public String getNamespaceOfNode(final Node n) {
        return n.getNamespaceURI();
    }
    
    public Node getParentOfNode(final Node node) {
        Node parent = node.getParentNode();
        if (parent == null && node.getNodeType() == 2) {
            parent = ((Attr)node).getOwnerElement();
        }
        return parent;
    }
    
    public boolean isNodeAfter(final Node node1, final Node node2) {
        try {
            final int index1 = ((DOMOrder)node1).getUid();
            final int index2 = ((DOMOrder)node2).getUid();
            return index1 <= index2;
        }
        catch (ClassCastException ex) {
            return super.isNodeAfter(node1, node2);
        }
    }
    
    public void parse(final InputSource source) throws TransformerException {
        try {
            final DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setValidating(true);
            final DocumentBuilder parser = builderFactory.newDocumentBuilder();
            parser.setErrorHandler(new DefaultErrorHandler());
            this.setDocument(parser.parse(source));
        }
        catch (SAXException se) {
            throw new TransformerException(se);
        }
        catch (ParserConfigurationException pce) {
            throw new TransformerException(pce);
        }
        catch (IOException e) {
            throw new TransformerException(e);
        }
    }
    
    public void setDocument(final Document doc) {
        this.m_doc = doc;
    }
    
    public boolean supportsSAX() {
        return true;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import org.xml.sax.ErrorHandler;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Node;
import org.w3c.dom.Document;

public class DOM2Helper extends DOMHelper
{
    private Document m_doc;
    
    public void checkNode(final Node node) throws TransformerException {
    }
    
    public boolean supportsSAX() {
        return true;
    }
    
    public void setDocument(final Document doc) {
        this.m_doc = doc;
    }
    
    public Document getDocument() {
        return this.m_doc;
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
        catch (IOException ioe) {
            throw new TransformerException(ioe);
        }
    }
    
    public Element getElementByID(final String id, final Document doc) {
        return doc.getElementById(id);
    }
    
    public static boolean isNodeAfter(final Node node1, final Node node2) {
        if (node1 instanceof DOMOrder && node2 instanceof DOMOrder) {
            final int index1 = ((DOMOrder)node1).getUid();
            final int index2 = ((DOMOrder)node2).getUid();
            return index1 <= index2;
        }
        return DOMHelper.isNodeAfter(node1, node2);
    }
    
    public static Node getParentOfNode(final Node node) {
        Node parent = node.getParentNode();
        if (parent == null && 2 == node.getNodeType()) {
            parent = ((Attr)node).getOwnerElement();
        }
        return parent;
    }
    
    public String getLocalNameOfNode(final Node n) {
        final String name = n.getLocalName();
        return (null == name) ? super.getLocalNameOfNode(n) : name;
    }
    
    public String getNamespaceOfNode(final Node n) {
        return n.getNamespaceURI();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.validators.schema;

import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.apache.xerces.dom.AttrImpl;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Node;

public class XUtil
{
    public static void copyInto(final Node node, Node parentNode) throws DOMException {
        final Document ownerDocument = parentNode.getOwnerDocument();
        final boolean b = ownerDocument instanceof DocumentImpl;
        Node parentNode2 = node;
        Object o = null;
        for (Node node2 = node; node2 != null; node2 = node2.getFirstChild(), parentNode = (Node)o) {
            final Node node3 = null;
            final short nodeType = node2.getNodeType();
            switch (nodeType) {
                case 4: {
                    o = ownerDocument.createCDATASection(node2.getNodeValue());
                    break;
                }
                case 8: {
                    o = ownerDocument.createComment(node2.getNodeValue());
                    break;
                }
                case 1: {
                    final Element element = (Element)(o = ownerDocument.createElement(node2.getNodeName()));
                    final NamedNodeMap attributes = node2.getAttributes();
                    for (int length = attributes.getLength(), i = 0; i < length; ++i) {
                        final Attr attr = (Attr)attributes.item(i);
                        final String nodeName = attr.getNodeName();
                        element.setAttribute(nodeName, attr.getNodeValue());
                        if (b && !attr.getSpecified()) {
                            ((AttrImpl)element.getAttributeNode(nodeName)).setSpecified(false);
                        }
                    }
                    break;
                }
                case 5: {
                    o = ownerDocument.createEntityReference(node2.getNodeName());
                    break;
                }
                case 7: {
                    o = ownerDocument.createProcessingInstruction(node2.getNodeName(), node2.getNodeValue());
                    break;
                }
                case 3: {
                    o = ownerDocument.createTextNode(node2.getNodeValue());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("can't copy node type, " + nodeType + " (" + node3.getNodeName() + ')');
                }
            }
            parentNode.appendChild((Node)o);
            if (node2.hasChildNodes()) {
                parentNode2 = node2;
            }
            else {
                for (node2 = node2.getNextSibling(); node2 == null && parentNode2 != node; node2 = parentNode2.getNextSibling(), parentNode2 = parentNode2.getParentNode(), parentNode = parentNode.getParentNode()) {}
            }
        }
    }
    
    public static Element getFirstChildElement(final Node node) {
        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node node) {
        for (Node node2 = node.getLastChild(); node2 != null; node2 = node2.getPreviousSibling()) {
            if (node2.getNodeType() == 1) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node) {
        for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getFirstChildElement(final Node node, final String s) {
        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1 && node2.getNodeName().equals(s)) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node node, final String s) {
        for (Node node2 = node.getLastChild(); node2 != null; node2 = node2.getPreviousSibling()) {
            if (node2.getNodeType() == 1 && node2.getNodeName().equals(s)) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String s) {
        for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1 && node2.getNodeName().equals(s)) {
                return (Element)node2;
            }
        }
        return null;
    }
    
    public static Element getFirstChildElement(final Node node, final String[] array) {
        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                for (int i = 0; i < array.length; ++i) {
                    if (node2.getNodeName().equals(array[i])) {
                        return (Element)node2;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node node, final String[] array) {
        for (Node node2 = node.getLastChild(); node2 != null; node2 = node2.getPreviousSibling()) {
            if (node2.getNodeType() == 1) {
                for (int i = 0; i < array.length; ++i) {
                    if (node2.getNodeName().equals(array[i])) {
                        return (Element)node2;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String[] array) {
        for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                for (int i = 0; i < array.length; ++i) {
                    if (node2.getNodeName().equals(array[i])) {
                        return (Element)node2;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getFirstChildElement(final Node node, final String s, final String s2, final String s3) {
        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                final Element element = (Element)node2;
                if (element.getNodeName().equals(s) && element.getAttribute(s2).equals(s3)) {
                    return element;
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node node, final String s, final String s2, final String s3) {
        for (Node node2 = node.getLastChild(); node2 != null; node2 = node2.getPreviousSibling()) {
            if (node2.getNodeType() == 1) {
                final Element element = (Element)node2;
                if (element.getNodeName().equals(s) && element.getAttribute(s2).equals(s3)) {
                    return element;
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String s, final String s2, final String s3) {
        for (Node node2 = node.getNextSibling(); node2 != null; node2 = node2.getNextSibling()) {
            if (node2.getNodeType() == 1) {
                final Element element = (Element)node2;
                if (element.getNodeName().equals(s) && element.getAttribute(s2).equals(s3)) {
                    return element;
                }
            }
        }
        return null;
    }
    
    public static String getChildText(final Node node) {
        if (node == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (Node node2 = node.getFirstChild(); node2 != null; node2 = node2.getNextSibling()) {
            final short nodeType = node2.getNodeType();
            if (nodeType == 3) {
                sb.append(node2.getNodeValue());
            }
            else if (nodeType == 4) {
                sb.append(getChildText(node2));
            }
        }
        return sb.toString();
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.util;

import org.apache.xerces.dom.NodeImpl;
import org.w3c.dom.DOMException;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.apache.xerces.dom.AttrImpl;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Node;

public class DOMUtil
{
    public static void copyInto(final Node src, Node dest) throws DOMException {
        final Document factory = dest.getOwnerDocument();
        final boolean domimpl = factory instanceof DocumentImpl;
        Node parent = src;
        Node node;
        for (Node place = src; place != null; place = place.getFirstChild(), dest = node) {
            node = null;
            final int type = place.getNodeType();
            switch (type) {
                case 4: {
                    node = factory.createCDATASection(place.getNodeValue());
                    break;
                }
                case 8: {
                    node = factory.createComment(place.getNodeValue());
                    break;
                }
                case 1: {
                    final Element element = (Element)(node = factory.createElement(place.getNodeName()));
                    final NamedNodeMap attrs = place.getAttributes();
                    for (int attrCount = attrs.getLength(), i = 0; i < attrCount; ++i) {
                        final Attr attr = (Attr)attrs.item(i);
                        final String attrName = attr.getNodeName();
                        final String attrValue = attr.getNodeValue();
                        element.setAttribute(attrName, attrValue);
                        if (domimpl && !attr.getSpecified()) {
                            ((AttrImpl)element.getAttributeNode(attrName)).setSpecified(false);
                        }
                    }
                    break;
                }
                case 5: {
                    node = factory.createEntityReference(place.getNodeName());
                    break;
                }
                case 7: {
                    node = factory.createProcessingInstruction(place.getNodeName(), place.getNodeValue());
                    break;
                }
                case 3: {
                    node = factory.createTextNode(place.getNodeValue());
                    break;
                }
                default: {
                    throw new IllegalArgumentException("can't copy node type, " + type + " (" + node.getNodeName() + ')');
                }
            }
            dest.appendChild(node);
            if (place.hasChildNodes()) {
                parent = place;
            }
            else {
                for (place = place.getNextSibling(); place == null && parent != src; place = parent.getNextSibling(), parent = parent.getParentNode(), dest = dest.getParentNode()) {}
            }
        }
    }
    
    public static Element getFirstChildElement(final Node parent) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getFirstVisibleChildElement(final Node parent) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1 && !((NodeImpl)child).getReadOnly()) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node parent) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getLastVisibleChildElement(final Node parent) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1 && ((NodeImpl)child).getReadOnly()) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1) {
                return (Element)sibling;
            }
        }
        return null;
    }
    
    public static Element getNextVisibleSiblingElement(final Node node) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1 && !((NodeImpl)sibling).getReadOnly()) {
                return (Element)sibling;
            }
        }
        return null;
    }
    
    public static void setHidden(final Node node) {
        ((NodeImpl)node).setReadOnly(true, false);
    }
    
    public static void setVisible(final Node node) {
        ((NodeImpl)node).setReadOnly(false, false);
    }
    
    public static boolean isHidden(final Node node) {
        return ((NodeImpl)node).getReadOnly();
    }
    
    public static Element getFirstChildElement(final Node parent, final String elemName) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1 && child.getNodeName().equals(elemName)) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node parent, final String elemName) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1 && child.getNodeName().equals(elemName)) {
                return (Element)child;
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String elemName) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1 && sibling.getNodeName().equals(elemName)) {
                return (Element)sibling;
            }
        }
        return null;
    }
    
    public static Element getFirstChildElementNS(final Node parent, final String uri, final String localpart) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
                final String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element)child;
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElementNS(final Node parent, final String uri, final String localpart) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1) {
                final String childURI = child.getNamespaceURI();
                if (childURI != null && childURI.equals(uri) && child.getLocalName().equals(localpart)) {
                    return (Element)child;
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElementNS(final Node node, final String uri, final String localpart) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1) {
                final String siblingURI = sibling.getNamespaceURI();
                if (siblingURI != null && siblingURI.equals(uri) && sibling.getLocalName().equals(localpart)) {
                    return (Element)sibling;
                }
            }
        }
        return null;
    }
    
    public static Element getFirstChildElement(final Node parent, final String[] elemNames) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    if (child.getNodeName().equals(elemNames[i])) {
                        return (Element)child;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node parent, final String[] elemNames) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    if (child.getNodeName().equals(elemNames[i])) {
                        return (Element)child;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String[] elemNames) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    if (sibling.getNodeName().equals(elemNames[i])) {
                        return (Element)sibling;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getFirstChildElementNS(final Node parent, final String[][] elemNames) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    final String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element)child;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElementNS(final Node parent, final String[][] elemNames) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    final String uri = child.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && child.getLocalName().equals(elemNames[i][1])) {
                        return (Element)child;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElementNS(final Node node, final String[][] elemNames) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1) {
                for (int i = 0; i < elemNames.length; ++i) {
                    final String uri = sibling.getNamespaceURI();
                    if (uri != null && uri.equals(elemNames[i][0]) && sibling.getLocalName().equals(elemNames[i][1])) {
                        return (Element)sibling;
                    }
                }
            }
        }
        return null;
    }
    
    public static Element getFirstChildElement(final Node parent, final String elemName, final String attrName, final String attrValue) {
        for (Node child = parent.getFirstChild(); child != null; child = child.getNextSibling()) {
            if (child.getNodeType() == 1) {
                final Element element = (Element)child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
        }
        return null;
    }
    
    public static Element getLastChildElement(final Node parent, final String elemName, final String attrName, final String attrValue) {
        for (Node child = parent.getLastChild(); child != null; child = child.getPreviousSibling()) {
            if (child.getNodeType() == 1) {
                final Element element = (Element)child;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
                    return element;
                }
            }
        }
        return null;
    }
    
    public static Element getNextSiblingElement(final Node node, final String elemName, final String attrName, final String attrValue) {
        for (Node sibling = node.getNextSibling(); sibling != null; sibling = sibling.getNextSibling()) {
            if (sibling.getNodeType() == 1) {
                final Element element = (Element)sibling;
                if (element.getNodeName().equals(elemName) && element.getAttribute(attrName).equals(attrValue)) {
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
        final StringBuffer str = new StringBuffer();
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            final short type = child.getNodeType();
            if (type == 3) {
                str.append(child.getNodeValue());
            }
            else if (type == 4) {
                str.append(getChildText(child));
            }
        }
        return str.toString();
    }
    
    public static String getName(final Node node) {
        return node.getNodeName();
    }
    
    public static String getLocalName(final Node node) {
        final String name = node.getLocalName();
        return (name != null) ? name : node.getNodeName();
    }
    
    public static Element getParent(final Element elem) {
        final Node parent = elem.getParentNode();
        if (parent instanceof Element) {
            return (Element)parent;
        }
        return null;
    }
    
    public static Document getDocument(final Node node) {
        return node.getOwnerDocument();
    }
    
    public static Element getRoot(final Document doc) {
        return doc.getDocumentElement();
    }
    
    public static Attr getAttr(final Element elem, final String name) {
        return elem.getAttributeNode(name);
    }
    
    public static Attr getAttrNS(final Element elem, final String nsUri, final String localName) {
        return elem.getAttributeNodeNS(nsUri, localName);
    }
    
    public static Attr[] getAttrs(final Element elem) {
        final NamedNodeMap attrMap = elem.getAttributes();
        final Attr[] attrArray = new Attr[attrMap.getLength()];
        for (int i = 0; i < attrMap.getLength(); ++i) {
            attrArray[i] = (Attr)attrMap.item(i);
        }
        return attrArray;
    }
    
    public static String getValue(final Attr attribute) {
        return attribute.getValue();
    }
    
    public static String getAttrValue(final Element elem, final String name) {
        return elem.getAttribute(name);
    }
    
    public static String getAttrValueNS(final Element elem, final String nsUri, final String localName) {
        return elem.getAttributeNS(nsUri, localName);
    }
    
    public static String getNamespaceURI(final Node node) {
        return node.getNamespaceURI();
    }
}

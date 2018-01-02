// 
// Decompiled by Procyon v0.5.30
// 

package com.stonewall.cornerstone.xdom;

import org.apache.xpath.NodeSet;
import org.w3c.dom.NodeList;
import javax.xml.transform.TransformerException;
import java.util.StringTokenizer;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Node;

public class XPathFinder
{
    public Node selectSingleNode(final Node context, final String xpath) throws TransformerException {
        if (this.complexPath(xpath)) {
            return XPathAPI.selectSingleNode(context, xpath);
        }
        String name = null;
        Node parent = context;
        final StringTokenizer tk = new StringTokenizer(xpath, "/");
        while (tk.hasMoreTokens()) {
            name = tk.nextToken();
            if (name.charAt(0) == '@') {
                parent = this.getAttributeByName(parent, name);
            }
            else {
                parent = this.getChildByName(parent, name);
            }
            if (parent == null) {
                break;
            }
        }
        return parent;
    }
    
    public NodeList selectNodeList(final Node context, final String xpath) throws TransformerException {
        if (this.complexPath(xpath)) {
            return XPathAPI.selectNodeList(context, xpath);
        }
        Node node = this.selectSingleNode(context, xpath);
        if (node == null) {
            return (NodeList)new NodeSet();
        }
        final String name = node.getNodeName();
        node = node.getParentNode();
        return this.getChildrenByName(node, name);
    }
    
    protected Node getAttributeByName(final Node node, final String name) {
        final Node result = null;
        try {
            return node.getAttributes().getNamedItem(name.substring(1));
        }
        catch (Exception ex) {
            return result;
        }
    }
    
    protected Node getChildByName(final Node parent, final String name) {
        final NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            final Node n = list.item(i);
            if (name.equals(n.getNodeName())) {
                return n;
            }
        }
        return null;
    }
    
    protected NodeList getChildrenByName(final Node parent, final String name) {
        final NodeSet result = new NodeSet();
        final NodeList list = parent.getChildNodes();
        for (int i = 0; i < list.getLength(); ++i) {
            final Node n = list.item(i);
            if (name.equals(n.getNodeName())) {
                result.addNode(n);
            }
        }
        return (NodeList)result;
    }
    
    protected boolean complexPath(final String path) {
        for (int i = 0; i < path.length(); ++i) {
            final char c = path.charAt(i);
            if (path.charAt(i) == ':' || path.charAt(i) == '[') {
                return true;
            }
        }
        return false;
    }
}

// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.w3c.dom.Comment;
import org.w3c.dom.NamedNodeMap;
import org.xml.sax.ext.LexicalHandler;
import org.w3c.dom.EntityReference;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.w3c.dom.Text;
import org.apache.xalan.stree.SaxEventDispatch;
import org.w3c.dom.Node;
import org.apache.xpath.DOM2Helper;
import org.apache.xpath.DOMHelper;
import org.xml.sax.ContentHandler;

public class TreeWalker
{
    private ContentHandler m_contentHandler;
    protected DOMHelper m_dh;
    boolean nextIsRaw;
    
    public TreeWalker(final ContentHandler contentHandler) {
        this.m_contentHandler = null;
        this.m_dh = new DOM2Helper();
        this.nextIsRaw = false;
        this.m_contentHandler = contentHandler;
    }
    
    private final void dispatachChars(final Node node) throws SAXException {
        if (node.isSupported("http://xml.apache.org/xalan/features/feed-events", "1.0")) {
            ((SaxEventDispatch)node).dispatchCharactersEvent(this.m_contentHandler);
        }
        else {
            final String data = ((Text)node).getData();
            this.m_contentHandler.characters(data.toCharArray(), 0, data.length());
        }
    }
    
    protected void endNode(final Node node) throws SAXException {
        switch (node.getNodeType()) {
            case 9: {
                this.m_contentHandler.endDocument();
                break;
            }
            case 1: {
                String ns = this.m_dh.getNamespaceOfNode(node);
                if (ns == null) {
                    ns = "";
                }
                this.m_contentHandler.endElement(ns, this.m_dh.getLocalNameOfNode(node), node.getNodeName());
                final NamedNodeMap atts = ((Element)node).getAttributes();
                for (int nAttrs = atts.getLength(), i = 0; i < nAttrs; ++i) {
                    final Node attr = atts.item(i);
                    final String attrName = attr.getNodeName();
                    if (attrName.equals("xmlns") || attrName.startsWith("xmlns:")) {
                        final int index;
                        final String prefix = ((index = attrName.indexOf(":")) < 0) ? "" : attrName.substring(index + 1);
                        this.m_contentHandler.endPrefixMapping(prefix);
                    }
                }
                break;
            }
            case 5: {
                final EntityReference eref = (EntityReference)node;
                if (this.m_contentHandler instanceof LexicalHandler) {
                    final LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
                    lh.endEntity(eref.getNodeName());
                    break;
                }
                break;
            }
        }
    }
    
    public ContentHandler getcontentHandler() {
        return this.m_contentHandler;
    }
    
    protected void startNode(final Node node) throws SAXException {
        if (this.m_contentHandler instanceof NodeConsumer) {
            ((NodeConsumer)this.m_contentHandler).setOriginatingNode(node);
        }
        switch (node.getNodeType()) {
            case 8: {
                final String data = ((Comment)node).getData();
                if (this.m_contentHandler instanceof LexicalHandler) {
                    final LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
                    lh.comment(data.toCharArray(), 0, data.length());
                    break;
                }
                break;
            }
            case 9: {
                this.m_contentHandler.startDocument();
                break;
            }
            case 1: {
                final NamedNodeMap atts = ((Element)node).getAttributes();
                for (int nAttrs = atts.getLength(), i = 0; i < nAttrs; ++i) {
                    final Node attr = atts.item(i);
                    final String attrName = attr.getNodeName();
                    if (attrName.equals("xmlns") || attrName.startsWith("xmlns:")) {
                        final int index;
                        final String prefix = ((index = attrName.indexOf(":")) < 0) ? "" : attrName.substring(index + 1);
                        this.m_contentHandler.startPrefixMapping(prefix, attr.getNodeValue());
                    }
                }
                String ns = this.m_dh.getNamespaceOfNode(node);
                if (ns == null) {
                    ns = "";
                }
                this.m_contentHandler.startElement(ns, this.m_dh.getLocalNameOfNode(node), node.getNodeName(), new AttList(atts, this.m_dh));
                break;
            }
            case 7: {
                final ProcessingInstruction pi = (ProcessingInstruction)node;
                final String name = pi.getNodeName();
                if (name.equals("xslt-next-is-raw")) {
                    this.nextIsRaw = true;
                    break;
                }
                this.m_contentHandler.processingInstruction(pi.getNodeName(), pi.getData());
                break;
            }
            case 4: {
                final boolean isLexH = this.m_contentHandler instanceof LexicalHandler;
                final LexicalHandler lh2 = isLexH ? ((LexicalHandler)this.m_contentHandler) : null;
                if (isLexH) {
                    lh2.startCDATA();
                }
                this.dispatachChars(node);
                if (isLexH) {
                    lh2.endCDATA();
                    break;
                }
                break;
            }
            case 3: {
                final String data2 = ((Text)node).getData();
                if (this.nextIsRaw) {
                    this.nextIsRaw = false;
                    this.m_contentHandler.processingInstruction("javax.xml.transform.disable-output-escaping", "");
                    this.dispatachChars(node);
                    this.m_contentHandler.processingInstruction("javax.xml.transform.enable-output-escaping", "");
                    break;
                }
                this.dispatachChars(node);
                break;
            }
            case 5: {
                final EntityReference eref = (EntityReference)node;
                if (this.m_contentHandler instanceof LexicalHandler) {
                    ((LexicalHandler)this.m_contentHandler).startEntity(eref.getNodeName());
                    break;
                }
                break;
            }
        }
    }
    
    public void traverse(Node pos) throws SAXException {
        final Node top = pos;
        while (pos != null) {
            this.startNode(pos);
            Node nextNode = pos.getFirstChild();
            while (nextNode == null) {
                this.endNode(pos);
                if (top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (nextNode != null) {
                    continue;
                }
                pos = pos.getParentNode();
                if (pos == null || top.equals(pos)) {
                    if (pos != null) {
                        this.endNode(pos);
                    }
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
    }
    
    public void traverse(Node pos, final Node top) throws SAXException {
        while (pos != null) {
            this.startNode(pos);
            Node nextNode = pos.getFirstChild();
            while (nextNode == null) {
                this.endNode(pos);
                if (top != null && top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (nextNode != null) {
                    continue;
                }
                pos = pos.getParentNode();
                if (pos == null || (top != null && top.equals(pos))) {
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
    }
}

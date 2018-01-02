// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xml.utils;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.EntityReference;
import org.w3c.dom.ProcessingInstruction;
import org.xml.sax.Attributes;
import org.w3c.dom.Element;
import org.xml.sax.ext.LexicalHandler;
import org.w3c.dom.Comment;
import org.w3c.dom.Text;
import org.apache.xml.dtm.ref.dom2dtm.DOM2DTM;
import org.xml.sax.SAXException;
import org.w3c.dom.Node;
import java.io.File;
import org.xml.sax.Locator;
import org.xml.sax.helpers.LocatorImpl;
import org.xml.sax.ContentHandler;

public class TreeWalker
{
    private ContentHandler m_contentHandler;
    protected DOMHelper m_dh;
    private LocatorImpl m_locator;
    boolean nextIsRaw;
    
    public ContentHandler getContentHandler() {
        return this.m_contentHandler;
    }
    
    public void setContentHandler(final ContentHandler ch) {
        this.m_contentHandler = ch;
    }
    
    public TreeWalker(final ContentHandler contentHandler, final DOMHelper dh, final String systemId) {
        this.m_contentHandler = null;
        this.m_locator = new LocatorImpl();
        this.nextIsRaw = false;
        (this.m_contentHandler = contentHandler).setDocumentLocator(this.m_locator);
        if (systemId != null) {
            this.m_locator.setSystemId(systemId);
        }
        else {
            try {
                this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
            }
            catch (SecurityException ex) {}
        }
        this.m_dh = dh;
    }
    
    public TreeWalker(final ContentHandler contentHandler, final DOMHelper dh) {
        this.m_contentHandler = null;
        this.m_locator = new LocatorImpl();
        this.nextIsRaw = false;
        (this.m_contentHandler = contentHandler).setDocumentLocator(this.m_locator);
        try {
            this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
        }
        catch (SecurityException ex) {}
        this.m_dh = dh;
    }
    
    public TreeWalker(final ContentHandler contentHandler) {
        this.m_contentHandler = null;
        this.m_locator = new LocatorImpl();
        this.nextIsRaw = false;
        this.m_contentHandler = contentHandler;
        if (this.m_contentHandler != null) {
            this.m_contentHandler.setDocumentLocator(this.m_locator);
        }
        try {
            this.m_locator.setSystemId(System.getProperty("user.dir") + File.separator + "dummy.xsl");
        }
        catch (SecurityException ex) {}
        this.m_dh = new DOM2Helper();
    }
    
    public void traverse(Node pos) throws SAXException {
        this.m_contentHandler.startDocument();
        final Node top = pos;
        while (null != pos) {
            this.startNode(pos);
            Node nextNode = pos.getFirstChild();
            while (null == nextNode) {
                this.endNode(pos);
                if (top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (null != nextNode) {
                    continue;
                }
                pos = pos.getParentNode();
                if (null == pos || top.equals(pos)) {
                    if (null != pos) {
                        this.endNode(pos);
                    }
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
        this.m_contentHandler.endDocument();
    }
    
    public void traverse(Node pos, final Node top) throws SAXException {
        this.m_contentHandler.startDocument();
        while (null != pos) {
            this.startNode(pos);
            Node nextNode = pos.getFirstChild();
            while (null == nextNode) {
                this.endNode(pos);
                if (null != top && top.equals(pos)) {
                    break;
                }
                nextNode = pos.getNextSibling();
                if (null != nextNode) {
                    continue;
                }
                pos = pos.getParentNode();
                if (null == pos || (null != top && top.equals(pos))) {
                    nextNode = null;
                    break;
                }
            }
            pos = nextNode;
        }
        this.m_contentHandler.endDocument();
    }
    
    private final void dispatachChars(final Node node) throws SAXException {
        if (this.m_contentHandler instanceof DOM2DTM.CharacterNodeHandler) {
            ((DOM2DTM.CharacterNodeHandler)this.m_contentHandler).characters(node);
        }
        else {
            final String data = ((Text)node).getData();
            this.m_contentHandler.characters(data.toCharArray(), 0, data.length());
        }
    }
    
    protected void startNode(final Node node) throws SAXException {
        if (this.m_contentHandler instanceof NodeConsumer) {
            ((NodeConsumer)this.m_contentHandler).setOriginatingNode(node);
        }
        if (node instanceof Locator) {
            final Locator loc = (Locator)node;
            this.m_locator.setColumnNumber(loc.getColumnNumber());
            this.m_locator.setLineNumber(loc.getLineNumber());
            this.m_locator.setPublicId(loc.getPublicId());
            this.m_locator.setSystemId(loc.getSystemId());
        }
        else {
            this.m_locator.setColumnNumber(0);
            this.m_locator.setLineNumber(0);
        }
        switch (node.getNodeType()) {
            case 8: {
                final String data = ((Comment)node).getData();
                if (this.m_contentHandler instanceof LexicalHandler) {
                    final LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
                    lh.comment(data.toCharArray(), 0, data.length());
                }
            }
            case 11: {}
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
                if (null == ns) {
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
                }
                else {
                    this.m_contentHandler.processingInstruction(pi.getNodeName(), pi.getData());
                }
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
                }
                break;
            }
            case 3: {
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
                }
                break;
            }
        }
    }
    
    protected void endNode(final Node node) throws SAXException {
        switch (node.getNodeType()) {
            case 1: {
                String ns = this.m_dh.getNamespaceOfNode(node);
                if (null == ns) {
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
            }
            case 5: {
                final EntityReference eref = (EntityReference)node;
                if (this.m_contentHandler instanceof LexicalHandler) {
                    final LexicalHandler lh = (LexicalHandler)this.m_contentHandler;
                    lh.endEntity(eref.getNodeName());
                }
                break;
            }
        }
    }
}

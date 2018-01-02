// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xalan.stree;

import org.apache.xml.utils.WrappedRuntimeException;
import org.xml.sax.SAXException;
import org.xml.sax.ContentHandler;
import org.w3c.dom.DOMException;
import org.apache.xalan.templates.WhiteSpaceInfo;
import org.apache.xalan.templates.StylesheetRoot;
import org.apache.xalan.transformer.TransformerImpl;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Element;
import org.apache.xpath.XPathContext;
import org.w3c.dom.Node;

public class Parent extends Child
{
    protected int m_posInChildList;
    protected int m_childCount;
    boolean m_isComplete;
    Child m_last;
    Child m_first;
    
    public Parent(final DocumentImpl doc) {
        super(doc);
        this.m_childCount = 0;
        this.m_isComplete = false;
    }
    
    public Node appendChild(final Node newChild) throws DOMException {
        final Child child = (Child)newChild;
        final DocumentImpl doc = super.m_doc;
        child.m_parent = this;
        ++this.m_childCount;
        if (child.m_uid == 0) {
            child.m_uid = ++super.m_doc.m_docOrderCount;
        }
        child.m_level = (short)(super.m_level + 1);
        if (this.m_first == null) {
            this.m_first = child;
        }
        else {
            child.m_prev = this.m_last;
            this.m_last.m_next = child;
        }
        this.m_last = child;
        if (child.getNodeType() == 1) {
            final SourceTreeHandler sh = doc.getSourceTreeHandler();
            if (sh != null && sh.m_shouldCheckWhitespace) {
                final TransformerImpl transformer = sh.getTransformerImpl();
                if (transformer != null) {
                    final StylesheetRoot stylesheet = transformer.getStylesheet();
                    try {
                        final ElementImpl elem = (ElementImpl)child;
                        if (doc.m_xpathContext == null) {
                            doc.m_xpathContext = new XPathContext(doc);
                        }
                        final WhiteSpaceInfo info = stylesheet.getWhiteSpaceInfo(doc.m_xpathContext, elem);
                        boolean shouldStrip;
                        if (info == null) {
                            shouldStrip = sh.getShouldStripWhitespace();
                        }
                        else {
                            shouldStrip = info.getShouldStripSpace();
                        }
                        sh.setShouldStripWhitespace(shouldStrip);
                    }
                    catch (TransformerException ex) {}
                }
            }
        }
        return newChild;
    }
    
    public void dispatchCharactersEvent(final ContentHandler ch) throws SAXException {
        for (Node child = this.getFirstChild(); child != null; child = child.getNextSibling()) {
            final int t = child.getNodeType();
            if (t != 8 && t != 7) {
                ((SaxEventDispatch)child).dispatchCharactersEvent(ch);
            }
        }
    }
    
    public Child getChild(final int i) throws ArrayIndexOutOfBoundsException, NullPointerException {
        if (i < 0) {
            return null;
        }
        if (i >= this.m_childCount && !this.isComplete()) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                        if (i < this.m_childCount) {
                            break;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
        }
        if (i < this.m_childCount) {
            Child child = this.m_first;
            for (int pos = 0; child != null; child = child.m_next, ++pos) {
                if (pos == i) {
                    return child;
                }
            }
        }
        return null;
    }
    
    public int getChildCount() {
        if (!this.isComplete()) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
        }
        return this.m_childCount;
    }
    
    public int getChildUID(final int pos) {
        final Child child = this.getChild(pos);
        return (child != null) ? child.getUid() : -1;
    }
    
    public Node getFirstChild() {
        if (this.m_first != null) {
            return this.m_first;
        }
        if (!this.m_isComplete) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                        if (this.m_first != null) {
                            // monitorexit(super.m_doc)
                            return this.m_first;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
        }
        return this.m_first;
    }
    
    public Node getLastChild() {
        try {
            return this.getChild(this.getChildCount() - 1);
        }
        catch (Exception e) {
            throw new WrappedRuntimeException(e);
        }
    }
    
    public boolean hasChildNodes() {
        if (this.m_childCount != 0) {
            return true;
        }
        if (!this.isComplete()) {
            synchronized (super.m_doc) {
                try {
                    while (!this.isComplete()) {
                        super.m_doc.wait(100L);
                        this.throwIfParseError();
                        if (this.m_childCount != 0) {
                            break;
                        }
                    }
                }
                catch (InterruptedException ex) {
                    this.throwIfParseError();
                }
            }
            // monitorexit(super.m_doc)
        }
        return this.m_childCount != 0;
    }
    
    public boolean isComplete() {
        if (!this.m_isComplete && super.m_doc.m_exceptionThrown != null) {
            this.throwParseError(super.m_doc.m_exceptionThrown);
        }
        return this.m_isComplete;
    }
    
    public void setComplete(final boolean isComplete) {
        this.m_isComplete = isComplete;
    }
    
    protected void throwParseError(final Exception e) {
        this.m_isComplete = true;
        super.throwParseError(e);
    }
}

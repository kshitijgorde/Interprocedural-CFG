// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.tree;

import org.w3c.dom.Element;
import java.util.Locale;
import org.w3c.dom.Node;

public class TreeWalker
{
    private Node startPoint;
    private Node current;
    
    public TreeWalker(final Node initial) {
        if (initial == null) {
            throw new IllegalArgumentException(XmlDocument.catalog.getMessage(Locale.getDefault(), "TW-004"));
        }
        if (!(initial instanceof NodeBase)) {
            throw new IllegalArgumentException(XmlDocument.catalog.getMessage(Locale.getDefault(), "TW-003"));
        }
        this.current = initial;
        this.startPoint = initial;
    }
    
    public Node getCurrent() {
        return this.current;
    }
    
    public Node getNext() {
        if (this.current == null) {
            return null;
        }
        switch (this.current.getNodeType()) {
            case 1:
            case 5:
            case 9:
            case 11: {
                final Node next = this.current.getFirstChild();
                if (next != null) {
                    return this.current = next;
                }
            }
            case 2:
            case 3:
            case 4:
            case 6:
            case 7:
            case 8:
            case 10:
            case 12: {
                for (Node here = this.current; here != null && here != this.startPoint; here = here.getParentNode()) {
                    final Node next = here.getNextSibling();
                    if (next != null) {
                        return this.current = next;
                    }
                }
                return this.current = null;
            }
            default: {
                throw new InternalError(((NodeBase)this.startPoint).getMessage("TW-000", new Object[] { Short.toString(this.current.getNodeType()) }));
            }
        }
    }
    
    public Element getNextElement(final String tag) {
        for (Node next = this.getNext(); next != null; next = this.getNext()) {
            if (next.getNodeType() == 1 && (tag == null || tag.equals(next.getNodeName()))) {
                return (Element)next;
            }
        }
        this.current = null;
        return null;
    }
    
    public Element getNextElement(final String nsURI, final String localName) {
        for (Node next = this.getNext(); next != null; next = this.getNext()) {
            if (next.getNodeType() == 1 && (nsURI == null || nsURI.equals(next.getNamespaceURI())) && (localName == null || localName.equals(next.getLocalName()))) {
                return (Element)next;
            }
        }
        this.current = null;
        return null;
    }
    
    public void reset() {
        this.current = this.startPoint;
    }
    
    public Node removeCurrent() {
        if (this.current == null) {
            throw new IllegalStateException(((NodeBase)this.startPoint).getMessage("TW-001"));
        }
        final Node toRemove = this.current;
        final Node parent = this.current.getParentNode();
        Node retval = null;
        if (parent == null) {
            throw new IllegalStateException(((NodeBase)this.startPoint).getMessage("TW-002"));
        }
        for (Node here = this.current; here != null && here != this.startPoint; here = here.getParentNode()) {
            retval = here.getNextSibling();
            if (retval != null) {
                this.current = retval;
                break;
            }
        }
        parent.removeChild(toRemove);
        return retval;
    }
}

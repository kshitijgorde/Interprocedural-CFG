// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.html.HTMLTableCellElement;
import org.w3c.dom.html.HTMLTableSectionElement;
import org.w3c.dom.html.HTMLTableRowElement;
import org.w3c.dom.html.HTMLOptionElement;
import org.w3c.dom.html.HTMLAreaElement;
import org.w3c.dom.html.HTMLObjectElement;
import org.w3c.dom.html.HTMLAppletElement;
import org.w3c.dom.html.HTMLImageElement;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.html.HTMLAnchorElement;
import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLElement;
import org.w3c.dom.Element;
import org.w3c.dom.html.HTMLCollection;

class HTMLCollectionImpl implements HTMLCollection
{
    static final short ANCHOR = 1;
    static final short FORM = 2;
    static final short IMAGE = 3;
    static final short APPLET = 4;
    static final short LINK = 5;
    static final short OPTION = 6;
    static final short ROW = 7;
    static final short ELEMENT = 8;
    static final short AREA = -1;
    static final short TBODY = -2;
    static final short CELL = -3;
    private short _lookingFor;
    private Element _topLevel;
    
    HTMLCollectionImpl(final HTMLElement topLevel, final short lookingFor) {
        if (topLevel == null) {
            throw new NullPointerException("HTM011 Argument 'topLevel' is null.");
        }
        this._topLevel = topLevel;
        this._lookingFor = lookingFor;
    }
    
    public final int getLength() {
        return this.getLength(this._topLevel);
    }
    
    public final Node item(final int index) {
        if (index < 0) {
            throw new IllegalArgumentException("HTM012 Argument 'index' is negative.");
        }
        return this.item(this._topLevel, new CollectionIndex(index));
    }
    
    public final Node namedItem(final String name) {
        if (name == null) {
            throw new NullPointerException("HTM013 Argument 'name' is null.");
        }
        return this.namedItem(this._topLevel, name);
    }
    
    private int getLength(final Element topLevel) {
        int length;
        synchronized (topLevel) {
            length = 0;
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (this.collectionMatch((Element)node, null)) {
                        ++length;
                    }
                    else if (this.recurse()) {
                        length += this.getLength((Element)node);
                    }
                }
            }
        }
        return length;
    }
    
    private Node item(final Element topLevel, final CollectionIndex index) {
        synchronized (topLevel) {
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (this.collectionMatch((Element)node, null)) {
                        if (index.isZero()) {
                            return node;
                        }
                        index.decrement();
                    }
                    else if (this.recurse()) {
                        final Node result = this.item((Element)node, index);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    private Node namedItem(final Element topLevel, final String name) {
        synchronized (topLevel) {
            Node node;
            for (node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (this.collectionMatch((Element)node, name)) {
                        return node;
                    }
                    if (this.recurse()) {
                        final Node result = this.namedItem((Element)node, name);
                        if (result != null) {
                            return result;
                        }
                    }
                }
            }
            return node;
        }
    }
    
    protected boolean recurse() {
        return this._lookingFor > 0;
    }
    
    protected boolean collectionMatch(final Element elem, final String name) {
        boolean match;
        synchronized (elem) {
            match = false;
            switch (this._lookingFor) {
                case 1: {
                    match = (elem instanceof HTMLAnchorElement && elem.getAttribute("name").length() > 0);
                    break;
                }
                case 2: {
                    match = (elem instanceof HTMLFormElement);
                    break;
                }
                case 3: {
                    match = (elem instanceof HTMLImageElement);
                    break;
                }
                case 4: {
                    match = (elem instanceof HTMLAppletElement || (elem instanceof HTMLObjectElement && ("application/java".equals(elem.getAttribute("codetype")) || elem.getAttribute("classid").startsWith("java:"))));
                    break;
                }
                case 8: {
                    match = (elem instanceof HTMLFormControl);
                    break;
                }
                case 5: {
                    match = ((elem instanceof HTMLAnchorElement || elem instanceof HTMLAreaElement) && elem.getAttribute("href").length() > 0);
                    break;
                }
                case -1: {
                    match = (elem instanceof HTMLAreaElement);
                    break;
                }
                case 6: {
                    match = (elem instanceof HTMLOptionElement);
                    break;
                }
                case 7: {
                    match = (elem instanceof HTMLTableRowElement);
                    break;
                }
                case -2: {
                    match = (elem instanceof HTMLTableSectionElement && elem.getTagName().equals("tbody"));
                    break;
                }
                case -3: {
                    match = (elem instanceof HTMLTableCellElement);
                    break;
                }
            }
            if (match && name != null) {
                if (elem instanceof HTMLAnchorElement && name.equals(elem.getAttribute("name"))) {
                    return true;
                }
                match = name.equals(elem.getAttribute("id"));
            }
        }
        return match;
    }
}

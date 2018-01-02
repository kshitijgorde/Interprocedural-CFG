// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.CoreDocumentImpl;
import java.util.Locale;
import org.w3c.dom.html.HTMLElement;
import org.apache.xerces.dom.ElementImpl;

public class HTMLElementImpl extends ElementImpl implements HTMLElement
{
    HTMLElementImpl(final HTMLDocumentImpl owner, final String tagName) {
        super(owner, tagName.toUpperCase(Locale.ENGLISH));
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setId(final String id) {
        this.setAttribute("id", id);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setTitle(final String title) {
        this.setAttribute("title", title);
    }
    
    public String getLang() {
        return this.getAttribute("lang");
    }
    
    public void setLang(final String lang) {
        this.setAttribute("lang", lang);
    }
    
    public String getDir() {
        return this.getAttribute("dir");
    }
    
    public void setDir(final String dir) {
        this.setAttribute("dir", dir);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setClassName(final String className) {
        this.setAttribute("class", className);
    }
    
    int getInteger(final String value) {
        try {
            return Integer.parseInt(value);
        }
        catch (NumberFormatException except) {
            return 0;
        }
    }
    
    boolean getBinary(final String name) {
        return this.getAttributeNode(name) != null;
    }
    
    void setAttribute(final String name, final boolean value) {
        if (value) {
            this.setAttribute(name, name);
        }
        else {
            this.removeAttribute(name);
        }
    }
    
    public Attr getAttributeNode(final String attrName) {
        return super.getAttributeNode(attrName.toLowerCase(Locale.ENGLISH));
    }
    
    public Attr getAttributeNodeNS(final String namespaceURI, final String localName) {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            return super.getAttributeNodeNS(namespaceURI, localName);
        }
        return super.getAttributeNode(localName.toLowerCase(Locale.ENGLISH));
    }
    
    public String getAttribute(final String attrName) {
        return super.getAttribute(attrName.toLowerCase(Locale.ENGLISH));
    }
    
    public String getAttributeNS(final String namespaceURI, final String localName) {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            return super.getAttributeNS(namespaceURI, localName);
        }
        return super.getAttribute(localName.toLowerCase(Locale.ENGLISH));
    }
    
    public final NodeList getElementsByTagName(final String tagName) {
        return super.getElementsByTagName(tagName.toUpperCase(Locale.ENGLISH));
    }
    
    public final NodeList getElementsByTagNameNS(final String namespaceURI, final String localName) {
        if (namespaceURI != null && namespaceURI.length() > 0) {
            return super.getElementsByTagNameNS(namespaceURI, localName.toUpperCase(Locale.ENGLISH));
        }
        return super.getElementsByTagName(localName.toUpperCase(Locale.ENGLISH));
    }
    
    String capitalize(final String value) {
        final char[] chars = value.toCharArray();
        if (chars.length > 0) {
            chars[0] = Character.toUpperCase(chars[0]);
            for (int i = 1; i < chars.length; ++i) {
                chars[i] = Character.toLowerCase(chars[i]);
            }
            return String.valueOf(chars);
        }
        return value;
    }
    
    String getCapitalized(final String name) {
        final String value = this.getAttribute(name);
        if (value != null) {
            final char[] chars = value.toCharArray();
            if (chars.length > 0) {
                chars[0] = Character.toUpperCase(chars[0]);
                for (int i = 1; i < chars.length; ++i) {
                    chars[i] = Character.toLowerCase(chars[i]);
                }
                return String.valueOf(chars);
            }
        }
        return value;
    }
    
    public HTMLFormElement getForm() {
        for (Node parent = this.getParentNode(); parent != null; parent = parent.getParentNode()) {
            if (parent instanceof HTMLFormElement) {
                return (HTMLFormElement)parent;
            }
        }
        return null;
    }
}

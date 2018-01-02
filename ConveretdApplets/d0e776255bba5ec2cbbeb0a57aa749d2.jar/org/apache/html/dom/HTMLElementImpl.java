// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.html.dom;

import org.w3c.dom.Node;
import org.w3c.dom.html.HTMLFormElement;
import org.w3c.dom.NodeList;
import org.w3c.dom.Attr;
import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.html.HTMLElement;
import org.apache.xerces.dom.ElementImpl;

public class HTMLElementImpl extends ElementImpl implements HTMLElement
{
    HTMLElementImpl(final HTMLDocumentImpl htmlDocumentImpl, final String s) {
        super(htmlDocumentImpl, s.toUpperCase());
    }
    
    public String getId() {
        return this.getAttribute("id");
    }
    
    public void setId(final String s) {
        this.setAttribute("id", s);
    }
    
    public String getTitle() {
        return this.getAttribute("title");
    }
    
    public void setTitle(final String s) {
        this.setAttribute("title", s);
    }
    
    public String getLang() {
        return this.getAttribute("lang");
    }
    
    public void setLang(final String s) {
        this.setAttribute("lang", s);
    }
    
    public String getDir() {
        return this.getAttribute("dir");
    }
    
    public void setDir(final String s) {
        this.setAttribute("dir", s);
    }
    
    public String getClassName() {
        return this.getAttribute("class");
    }
    
    public void setClassName(final String s) {
        this.setAttribute("class", s);
    }
    
    int getInteger(final String s) {
        try {
            return Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
    
    boolean getBinary(final String s) {
        return this.getAttributeNode(s) != null;
    }
    
    void setAttribute(final String s, final boolean b) {
        if (b) {
            this.setAttribute(s, s);
        }
        else {
            this.removeAttribute(s);
        }
    }
    
    public Attr getAttributeNode(final String s) {
        return super.getAttributeNode(s.toLowerCase());
    }
    
    public Attr getAttributeNodeNS(final String s, final String s2) {
        if (s != null && s.length() > 0) {
            return super.getAttributeNodeNS(s, s2);
        }
        return super.getAttributeNode(s2.toLowerCase());
    }
    
    public String getAttribute(final String s) {
        return super.getAttribute(s.toLowerCase());
    }
    
    public String getAttributeNS(final String s, final String s2) {
        if (s != null && s.length() > 0) {
            return super.getAttributeNS(s, s2);
        }
        return super.getAttribute(s2.toLowerCase());
    }
    
    public final NodeList getElementsByTagName(final String s) {
        return super.getElementsByTagName(s.toUpperCase());
    }
    
    public final NodeList getElementsByTagNameNS(final String s, final String s2) {
        if (s != null && s.length() > 0) {
            return super.getElementsByTagNameNS(s, s2.toUpperCase());
        }
        return super.getElementsByTagName(s2.toUpperCase());
    }
    
    String capitalize(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length > 0) {
            charArray[0] = Character.toUpperCase(charArray[0]);
            for (int i = 1; i < charArray.length; ++i) {
                charArray[i] = Character.toLowerCase(charArray[i]);
            }
            return String.valueOf(charArray);
        }
        return s;
    }
    
    String getCapitalized(final String s) {
        final String attribute = this.getAttribute(s);
        if (attribute != null) {
            final char[] charArray = attribute.toCharArray();
            if (charArray.length > 0) {
                charArray[0] = Character.toUpperCase(charArray[0]);
                for (int i = 1; i < charArray.length; ++i) {
                    charArray[i] = Character.toLowerCase(charArray[i]);
                }
                return String.valueOf(charArray);
            }
        }
        return attribute;
    }
    
    public HTMLFormElement getForm() {
        for (Node node = this.getParentNode(); node != null; node = node.getParentNode()) {
            if (node instanceof HTMLFormElement) {
                return (HTMLFormElement)node;
            }
        }
        return null;
    }
}
